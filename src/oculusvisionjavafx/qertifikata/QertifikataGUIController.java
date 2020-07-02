/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.qertifikata;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.alerts.AlertsMaker;
import oculusvisionjavafx.entities.Llojiqertifikates;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.klinika.KlinikaException;
import oculusvisionjavafx.utilis.PersistenceManager;
import oculusvisionjavafx.utilis.ValidationUtils;
import oculusvisionjavafx.entities.Qertifikata;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PrefixSelectionComboBox;

/**
 * FXML Controller class
 *
 * @author Qendresa
 */
public class QertifikataGUIController implements Initializable {

    @FXML
    private AnchorPane ditePushimiPicker;
    @FXML
    private JFXTextField idTextField;
    @FXML
    private JFXTextArea shenimetTextArea;
    @FXML
    private DatePicker dataLeshimitPicker;
    @FXML
    private JFXTextField semundjetTextField;
    @FXML
    private JFXTextArea simptomatTextArea;
    @FXML
    private JFXButton ruajBtn;
    @FXML
    private JFXButton fshijBtn;
    @FXML
    private PrefixSelectionComboBox<Pacienti> PacientiIDComboBox;
    @FXML
    private TableColumn<Qertifikata, Integer> col_ID;
    @FXML
    private TableColumn<Qertifikata, Pacienti> col_pacienti;
    @FXML
    private TableColumn<Qertifikata, String> col_shenimet;
    @FXML
    private TableColumn<Qertifikata, Date> col_dataL;
    @FXML
    private TableColumn<Qertifikata, String> col_semundjet;
    @FXML
    private TableColumn<Qertifikata, String> col_simptomat;
    @FXML
    private TableColumn<Qertifikata, Llojiqertifikates> col_llojiqertif;
    @FXML
    private TableView<Qertifikata> table;

    private EntityManager em;
    private QertifikataRepository qertifikataRp;
    @FXML
    private PrefixSelectionComboBox<Llojiqertifikates> llojiqertifikatesComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = PersistenceManager.getEntityManager();
        qertifikataRp = new QertifikataRepository();
        TypedQuery<Pacienti> pacientetQuery = em.createNamedQuery("Pacienti.findAll", Pacienti.class);
        PacientiIDComboBox.setItems(FXCollections.observableList(pacientetQuery.getResultList()));
        TypedQuery<Llojiqertifikates> llojiQertifQuery = em.createNamedQuery("Llojiqertifikates.findAll", Llojiqertifikates.class);
        llojiqertifikatesComboBox.setItems(FXCollections.observableList(llojiQertifQuery.getResultList()));
        initColumns();
        table.setItems(getQertifikataData());
        initTableContextMenu();
        
    }

    @FXML
    private void ruajOnAction(ActionEvent event) {
//        int pacSelektuar
//                = PacientiIDComboBox.getSelectionModel().selectedIndexProperty().get();
        Pacienti pacienti = PacientiIDComboBox.getValue();
        Llojiqertifikates llojiQertifikates = llojiqertifikatesComboBox.getValue();
        LocalDate data = dataLeshimitPicker.getValue();
        java.util.Date dataDate = Date.from(data.
                    atStartOfDay(ZoneId.systemDefault()).toInstant());
        String shenimet = shenimetTextArea.getText();
        String simptomat = simptomatTextArea.getText();
        String semundjet = semundjetTextField.getText();

        try {
            if (ValidationUtils.isEmptyOrNull(shenimet)) {
                throw new KlinikaException("Shenimet eshte e zbrazet");
            }
            if (ValidationUtils.isEmptyOrNull(simptomat)) {
                throw new KlinikaException("Simptomat nuk jane shenuar");
            }
        } catch (KlinikaException ex) {
            AlertsMaker.showErrorMessage(ex);
        }
        
        if(!idTextField.getText().trim().isEmpty()) {
            Integer id = Integer.parseInt(idTextField.getText());
            if(qertifikataRp.findById(id) != null) {
                qertifikataRp.update(id, pacienti, shenimet, dataDate, simptomat,semundjet,
                                        llojiQertifikates);
                notificationPopupInfo("Qertifikata - U Rifreskua ", 
                                        "Qertifikata - id: " + id);
                refreshData();
                clearFields();
            }
        } else {
                Qertifikata qertifikata = new Qertifikata();
                qertifikata.setDataLeshimit(Date.valueOf(data));
                qertifikata.setSimptomat(simptomat);
                qertifikata.setShenimet(shenimet);
                qertifikata.setSemundjet(semundjet);
                qertifikata.setPacientiID(pacienti);
                qertifikata.setLlojiQertifikatesID(llojiQertifikates);
                qertifikataRp.add(qertifikata);
                clearFields();
                refreshData();
                AlertsMaker.showSimpleAlert("Mesazh", "Qertifikata u ruajt me sukses");
                table.setItems(getQertifikataData());
        }
    }

    @FXML
    private void fshijOnAction(ActionEvent event) {
        clearFields();
    }

    public void initColumns() {
        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_pacienti.setCellValueFactory(new PropertyValueFactory<>("PacientiID"));
        col_dataL.setCellValueFactory(new PropertyValueFactory<>("dataLeshimit"));
        col_shenimet.setCellValueFactory(new PropertyValueFactory<>("shenimet"));
        col_simptomat.setCellValueFactory(new PropertyValueFactory<>("simptomat"));
        col_semundjet.setCellValueFactory(new PropertyValueFactory<>("semundjet"));
        col_llojiqertif.setCellValueFactory(new PropertyValueFactory<>("llojiQertifikatesID"));
        
    }
    
    private ObservableList<Qertifikata> data;
    private QertifikataController qrtCon;
    
    public  ObservableList<Qertifikata> getQertifikataData() {
        
        qrtCon = new QertifikataController();
        
        data = FXCollections.observableArrayList(qrtCon.getQertifikatat());
        
        if(data == null){
            return  FXCollections.observableArrayList();
        }else{
            
            return data;
        }
    }
    
     private void initTableContextMenu() {
        MenuItem editOption = new MenuItem("Ndrysho");
        editOption.setOnAction((ActionEvent event) -> {
            Qertifikata qertifikata = table.getSelectionModel().getSelectedItem();
            // Shfaqim te dhenat ne fusha
            idTextField.setText(String.valueOf(qertifikata.getId()));
           dataLeshimitPicker.setValue(qertifikata.getDataLeshimit().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
           shenimetTextArea.setText(qertifikata.getShenimet());
           simptomatTextArea.setText(qertifikata.getSimptomat());
           semundjetTextField.setText(qertifikata.getSemundjet());
           PacientiIDComboBox.setValue(qertifikata.getPacientiID());
           
        });

        MenuItem deleteOption = new MenuItem("Fshij");
        deleteOption.setOnAction(((event) -> {
            Qertifikata item = table.getSelectionModel().getSelectedItem(); 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
            alert.setTitle("Konfirmimi per fshirje");
            alert.setHeaderText("Konfirmoni Fshirjen");
            alert.setContentText("Qertifikata me id: " + item.getId());
            ButtonType buttonTypeOne = new ButtonType("Konfirmoj");
            ButtonType buttonTypeCancel = new ButtonType("Anuloj", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                if (!idTextField.getText().isEmpty()) {
                    Integer id = Integer.parseInt(idTextField.getText());
                    if (id.equals(item.getId())) {
                        clearFields();
                    }
                }
                qertifikataRp.delete(item);
                notificationPopupInfo("Qertifikata është fshirë",
                        "Qertifikata është fshirë me sukses");
            } else {
                notificationPopupInfo("Anulimi i fshirjes",
                        "Fshirja e Qertifikates u anulua");
            }
            refreshData();
        }));

        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(editOption, deleteOption);
        table.setContextMenu(menu);
    }

    private void clearFields() {
        PacientiIDComboBox.setValue(null);
        dataLeshimitPicker.setValue(null);
        idTextField.clear();
        shenimetTextArea.clear();
        semundjetTextField.clear();
        simptomatTextArea.clear();
    }

    private void notificationPopupInfo(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .darkStyle()
                .position(Pos.CENTER)
                .showInformation();
    }

    private void refreshData() {
        data = getQertifikataData();
        table.setItems(data);
        table.refresh();
    }
}
