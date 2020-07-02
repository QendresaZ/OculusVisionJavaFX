/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.alergjite;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.alerts.AlertsMaker;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Ilac;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.klinika.KlinikaException;
import oculusvisionjavafx.utilis.PersistenceManager;
import oculusvisionjavafx.utilis.ValidationUtils;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PrefixSelectionComboBox;

/**
 * FXML Controller class
 *
 * @author Qendresa
 */
public class AlergjiteGUIController implements Initializable {

    @FXML
    private ToggleGroup rrezikshmeriaGroup;
    @FXML
    private JFXTextField verujturNgaTextField;
    @FXML
    private DatePicker dataNdodhjesPicker;
    @FXML
    private JFXTextArea pershkrimiShTextfField;
    @FXML
    private JFXButton ruajBtn;
    @FXML
    private JFXButton fshijBtn;
    @FXML
    private JFXTextField idTextField;
    @FXML
    private PrefixSelectionComboBox<Pacienti> pacientiComboBox;
    @FXML
    private PrefixSelectionComboBox<Ilac> IlaciComboBox;
    @FXML
    private JFXRadioButton rrezUletRadioButton;
    @FXML
    private JFXRadioButton rrezLarteRadioButton;
    @FXML
    private TableView<Alergjite> table;
    @FXML
    private TableColumn<Alergjite, Integer> col_ID;
    @FXML
    private TableColumn<Alergjite, Pacienti> col_Pacienti;
    @FXML
    private TableColumn<Alergjite, Ilac> col_Ilaci;
    @FXML
    private TableColumn<Alergjite, Integer> col_rrexiksh;
    @FXML
    private TableColumn<Alergjite, Date> col_data;
    @FXML
    private TableColumn<Alergjite, String> col_vrjt;
    @FXML
    private TableColumn<Alergjite, String> col_Pershk;

    private EntityManager em;
    private AlergjiteRepository alergjiaRp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = PersistenceManager.getEntityManager();
        alergjiaRp = new AlergjiteRepository();
        TypedQuery<Pacienti> pacientetQuery = em.createNamedQuery("Pacienti.findAll", Pacienti.class);
        TypedQuery<Ilac> ilacetQuery = em.createNamedQuery("Ilac.findAll", Ilac.class);
        pacientiComboBox.setItems(FXCollections.observableList(pacientetQuery.getResultList()));
        IlaciComboBox.setItems(FXCollections.observableArrayList(ilacetQuery.getResultList()));
        initColumns();
        table.setItems(getAlergjiteData());
        initTableContextMenu();
    }

    @FXML
    private void ruajOnAction(ActionEvent event) {
        int pacSelektuar
                = pacientiComboBox.getSelectionModel().selectedIndexProperty().get();
        Pacienti pacienti = pacientiComboBox.getItems().get(pacSelektuar);
        System.out.println("Pacienti " + pacienti);
        int ilaciSelektuar
                = IlaciComboBox.getSelectionModel().selectedIndexProperty().get();
        Ilac ilaci = IlaciComboBox.getItems().get(ilaciSelektuar);
        System.out.println(ilaci);
        int rrezikshmeria = rrezUletRadioButton.isSelected() ? 1 : 2;
        System.out.println(rrezikshmeria);
        LocalDate data = dataNdodhjesPicker.getValue();
        java.util.Date dataDate = Date.from(data.
                    atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(data);
        String verejtuarNga = verujturNgaTextField.getText();
        System.out.println(verejtuarNga);
        String pershkrimi = pershkrimiShTextfField.getText();
        System.out.println(pershkrimi);
            try {
                if (ValidationUtils.isEmptyOrNull(verejtuarNga)) {
                throw new KlinikaException("VerejturNga eshte i zbrazet");
                }   
                
                if (ValidationUtils.isEmptyOrNull(pershkrimi)) {
                throw new KlinikaException("Pershkrimi eshte i zbrazet");
                }
            } catch (KlinikaException ex) {
                AlertsMaker.showErrorMessage(ex);
            }

            if (!idTextField.getText().trim().isEmpty()) { // e testojme nese ka id ( nese ka id kuptojme qe entiteti veq egziston edhe duhet me bo update
                Integer id = Integer.parseInt(idTextField.getText());
                if (alergjiaRp.findById(id) != null) {
                    alergjiaRp.update(id, rrezikshmeria,
                            dataDate, verejtuarNga, pershkrimi, pacienti, ilaci);
                    notificationPopupInfo("Alergjia - U Rifreskua",
                            "Alergjia - id: " + id);
                    refreshData();
                    clearFields();
                }
            } else { // nese nuk ka id (Fieldi ne gui) 
                    Alergjite alergjia = new Alergjite();
                    alergjia.setDataNodhjes(Date.valueOf(data));
                    alergjia.setIlacID(ilaci);
                    alergjia.setPacientiID(pacienti);
                    alergjia.setPershkrimiShtes(pershkrimi);
                    alergjia.setRrezikshmeria(rrezikshmeria);
                    alergjia.setVerejtuarNga(verejtuarNga);

                    alergjiaRp.add(alergjia);
                    AlertsMaker.showSimpleAlert("Mesazh", "Te dhenat u ruajten");
                    table.setItems(getAlergjiteData());
                }
        
    }

    @FXML
    private void fshijOnAction(ActionEvent event) {
        clearFields();
    }
        
    public void initColumns() {
        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Pacienti.setCellValueFactory(new PropertyValueFactory<>("PacientiID"));
        col_Ilaci.setCellValueFactory(new PropertyValueFactory<>("IlacID"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("dataNodhjes"));
        col_rrexiksh.setCellValueFactory(new PropertyValueFactory<>("rrezikshmeria"));
        col_vrjt.setCellValueFactory(new PropertyValueFactory<>("verejtuarNga"));
        col_Pershk.setCellValueFactory(new PropertyValueFactory<>("pershkrimiShtes"));
    }

    private ObservableList<Alergjite> data;
    private AlergjiteController algjCon;

    public ObservableList<Alergjite> getAlergjiteData() {

        algjCon = new AlergjiteController();

        data = FXCollections.observableArrayList(algjCon.getAlergjite());

        if (data == null) {
            return FXCollections.observableArrayList();
        } else {

            return data;

        }
    }

    private void initTableContextMenu() {
        MenuItem editOption = new MenuItem("Ndrysho");
        editOption.setOnAction((ActionEvent event) -> {
            Alergjite alergjia = table.getSelectionModel().getSelectedItem();
            // Shfaqim te dhenat ne fusha
            idTextField.setText(String.valueOf(alergjia.getId()));
            int rrezikshmeria = alergjia.getRrezikshmeria();
            if (rrezikshmeria == 1) {
                rrezUletRadioButton.setSelected(true);
            } else {
                rrezLarteRadioButton.setSelected(true);
            }
//            rrezikshmeriaGroup.set
            verujturNgaTextField.setText(alergjia.getVerejtuarNga());
            dataNdodhjesPicker.setValue(alergjia.getDataNodhjes().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            pershkrimiShTextfField.setText(alergjia.getPershkrimiShtes());
            pacientiComboBox.setValue(alergjia.getPacientiID());
            IlaciComboBox.setValue(alergjia.getIlacID());
        });

        MenuItem deleteOption = new MenuItem("Fshij");
        deleteOption.setOnAction(((event) -> {
            Alergjite item = table.getSelectionModel().getSelectedItem(); // marrim elementin e selektuar
            Alert alert = new Alert(AlertType.CONFIRMATION); // Dialog Box per konfirmim
            alert.setTitle("Konfirmimi per fshirje");
            alert.setHeaderText("Konfirmoni Fshirjen");
            alert.setContentText("Alergjia me id: " + item.getId());
            ButtonType buttonTypeOne = new ButtonType("Konfirmoj");
            ButtonType buttonTypeCancel = new ButtonType("Anuloj", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                if (!idTextField.getText().isEmpty()) {
                    Integer id = Integer.parseInt(idTextField.getText());
                    if (id.equals(item.getId())) {
                        clearFields();
                    }
                }
                alergjiaRp.delete(item);
                notificationPopupInfo("Alergjia është fshirë",
                        "Alergjia është fshirë me sukses");
            } else {
                notificationPopupInfo("Anulimi i fshirjes",
                        "Fshirja e Alergjise u anulua");
            }
            refreshData();
        }));

        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(editOption, deleteOption);
        table.setContextMenu(menu);
    }

    private void clearFields() {
        pacientiComboBox.setValue(null);
        IlaciComboBox.setValue(null);
        dataNdodhjesPicker.setValue(null);
        rrezikshmeriaGroup.selectToggle(rrezUletRadioButton);
        pershkrimiShTextfField.clear();
        verujturNgaTextField.clear();
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
        data = getAlergjiteData();
        table.setItems(data);
        table.refresh();
    }
}
