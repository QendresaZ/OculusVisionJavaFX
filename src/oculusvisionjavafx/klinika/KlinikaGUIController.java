package oculusvisionjavafx.klinika;

/*;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import oculusvisionjavafx.alerts.AlertsMaker;
import oculusvisionjavafx.entities.Klinika;
import oculusvisionjavafx.klinika.KlinikaException;
import oculusvisionjavafx.klinika.KlinikaRepository;
import oculusvisionjavafx.utilis.PersistenceManager;

import oculusvisionjavafx.utilis.ValidationUtils;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Qendresa
 */
public class KlinikaGUIController implements Initializable {

    @FXML
    private JFXTextField emriTextField;
    @FXML
    private JFXTextField adresaTextField;
    @FXML
    private JFXTextArea pershkrimiTextField;
    @FXML
    private JFXTextField idTextField;
    @FXML
    private JFXButton ruajBtn;

    
    @FXML
    private JFXButton fshijBtn;
    @FXML
    private TableView<Klinika> table;
    @FXML
    private TableColumn<Klinika, Integer> col_ID;
    @FXML
    private TableColumn<Klinika, String> col_Emri;
    @FXML
    private TableColumn<Klinika, String> col_Adresa;
    @FXML
    private TableColumn<Klinika, String> col_per;
    
    private EntityManager em;
    private KlinikaRepository kRep;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = PersistenceManager.getEntityManager();
        kRep = new KlinikaRepository();
        initColumns();
        table.setItems(getData());
        initTableContextMenu();
        
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();
            
            if(text.matches("[a-zA-Z]*")) {
                return change;
            }
            
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        emriTextField.setTextFormatter(textFormatter);
    }
    

    @FXML
    private void ruajOnAction(ActionEvent event) {
        String emri = emriTextField.getText();
        String adresa = adresaTextField.getText();
        String pershkrimi = pershkrimiTextField.getText();

        try {
            if (ValidationUtils.isEmptyOrNull(emri)) {
                throw new KlinikaException("Emri eshte i zbrazet");
            }
            if (ValidationUtils.isEmptyOrNull(adresa)) {
                throw new KlinikaException("Adresa eshte e zbrazet");
            }

            if (ValidationUtils.isEmptyOrNull(pershkrimi)) {
                throw new KlinikaException("Pershkrimi eshte i zbrazet");
            }

          

        } catch (KlinikaException ex) {
               AlertsMaker.showErrorMessage(ex);
        }
        
        if (!idTextField.getText().trim().isEmpty()) {
            Integer id = Integer.parseInt(idTextField.getText());
            if(kRep.findById(id) != null) {
                kRep.update(id, emri, adresa,pershkrimi);
                notifictionPopupInfo("Klinika - U Rifreskua",
                        "Klinika - id: " + id);
                refreshData();
                clearFields();
            }
        } else {
                Klinika klinika = new Klinika();
                klinika.setEmri(emri);
                klinika.setAdresa(adresa);
                klinika.setPershkrimi(pershkrimi);
                
                kRep.add(klinika);
                AlertsMaker.showSimpleAlert("Mesazh", "Klinika u ruajt me sukses");
                idTextField.setText(String.valueOf(klinika.getId()));
                table.setItems(getData());
            }
        

    }

    @FXML
    private void fshijOnAction(ActionEvent event) {
        clearFields();
    }

    public void initColumns() {
        col_ID.setCellValueFactory(new PropertyValueFactory("id"));
        col_Emri.setCellValueFactory(new PropertyValueFactory("Emri"));
        col_Adresa.setCellValueFactory(new PropertyValueFactory("Adresa"));
        col_per.setCellValueFactory(new PropertyValueFactory("Pershkrimi"));
    }

    private ObservableList<Klinika> data;
    private KlinikaController klCon;

    public ObservableList<Klinika> getData() {
        klCon = new KlinikaController();
        data = FXCollections.observableArrayList(klCon.getClinicData());
        if (data == null) {
            return FXCollections.observableArrayList();
        } else {
            return data;
        }
    }
    
    private void initTableContextMenu() {
        MenuItem editOption = new MenuItem("Ndrysho");
        editOption.setOnAction((ActionEvent event) -> {
            Klinika klinika = table.getSelectionModel().getSelectedItem();
            
            idTextField.setText(String.valueOf(klinika.getId()));
            emriTextField.setText(klinika.getEmri());
            adresaTextField.setText(klinika.getAdresa());
            pershkrimiTextField.setText(klinika.getPershkrimi());
        });
        
        MenuItem deleteOption = new MenuItem("Fshij");
        deleteOption.setOnAction(((event) -> {
            Klinika item = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmimi per fshirje");
            alert.setHeaderText("Konfirmoni fshirjen");
            alert.setContentText("Klinika me id: " + item.getId());
            ButtonType buttonTypeOne = new ButtonType("Konfirmoj");
            ButtonType buttonTypeCancel = new ButtonType("Anuloj", ButtonData.CANCEL_CLOSE);
            
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == buttonTypeOne) {
                if(!idTextField.getText().isEmpty()) {
                    Integer id = Integer.parseInt(idTextField.getText());
                    if(id.equals(item.getId())) {
                        clearFields();
                    }
                }
                
                kRep.delete(item);
                notifictionPopupInfo("Alergjia eshte fshire",
                        "Alergjia eshte fshire me sukses");
            } else {
                notifictionPopupInfo("Anulimi i fshirjes",
                        "Fshirja e Alergjise u anulua");
            }
            
            refreshData();
        }));
        
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(editOption, deleteOption);
        table.setContextMenu(menu);
    }
    
    private void clearFields() {
        emriTextField.clear();
        adresaTextField.clear();
        pershkrimiTextField.clear();
        idTextField.clear();
    }
    
    private void notifictionPopupInfo(String title, String text) {
        Notifications.create()
                 .title(title)
                 .text(text)
                 .darkStyle()
                 .position(Pos.CENTER)
                 .showInformation();
    }
    
    private void refreshData() {
        data = getData();
        table.setItems(data);
        table.refresh();
    }
}
