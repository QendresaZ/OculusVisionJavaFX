/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.healthRecords;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.alerts.AlertsMaker;
import oculusvisionjavafx.entities.HealthRecords;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.entities.Stafi;
import oculusvisionjavafx.klinika.KlinikaException;
import oculusvisionjavafx.utilis.PersistenceManager;
import oculusvisionjavafx.utilis.ValidationUtils;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Qendresa
 */
public class HealthRecordsGUIController implements Initializable {

    @FXML
    private JFXTextField idTextField;
    @FXML
    private ComboBox<Pacienti> pacientiIDComboBox;
    @FXML
    private ComboBox<Stafi> stafiIDComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private JFXTextArea pershkrimiTextArea;
    @FXML
    private TableColumn<HealthRecords, Integer> col_id;
    @FXML
    private TableColumn<HealthRecords, Pacienti> col_pacientiID;
    @FXML
    private TableColumn<HealthRecords, Stafi> col_stafiID;
    @FXML
    private TableColumn<HealthRecords, Date> col_data;
    @FXML
    private TableColumn<HealthRecords, String> col_pershkrimi;
    @FXML
    private TableView<HealthRecords> table;

    
    private HealthRecordsRepository healthRep;
    private EntityManager em;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = PersistenceManager.getEntityManager();
        healthRep = new HealthRecordsRepository();
        TypedQuery<Pacienti> pacientetQuery = em.createNamedQuery("Pacienti.findAll", Pacienti.class);
        TypedQuery<Stafi> ilacetQuery = em.createNamedQuery("Stafi.findAll", Stafi.class);
        pacientiIDComboBox.setItems(FXCollections.observableList(pacientetQuery.getResultList()));
        stafiIDComboBox.setItems(FXCollections.observableArrayList(ilacetQuery.getResultList()));
        initColumns();
        table.setItems(getHealthData());
        initTableContextMenu();
    }    

    @FXML
    private void ruajBtn(ActionEvent event) {
        Pacienti pacienti = pacientiIDComboBox.getValue();
        Stafi stafi = stafiIDComboBox.getValue();
//        int pacSelektuar
//                = pacientiIDComboBox.getSelectionModel().selectedIndexProperty().get();
//        Pacienti pacienti = pacientiIDComboBox.getItems().get(pacSelektuar);
//        System.out.println("Pacienti " + pacienti);
//        int stafiSelektuar
//                = stafiIDComboBox.getSelectionModel().selectedIndexProperty().get();
//        Stafi stafi = stafiIDComboBox.getItems().get(stafiSelektuar);
//        System.out.println(stafi);
        LocalDate data = datePicker.getValue();
        java.util.Date dataDate = Date.from(data.
                    atStartOfDay(ZoneId.systemDefault()).toInstant());
        String pershkrimi = pershkrimiTextArea.getText();
        
            try {
                if (ValidationUtils.isEmptyOrNull(pershkrimi)) {
                throw new KlinikaException("Pershkrimi eshte i zbrazet");
                }   
            
            } catch (KlinikaException ex) {
                AlertsMaker.showErrorMessage(ex);
            }

            if (!idTextField.getText().trim().isEmpty()) {
                Integer id = Integer.parseInt(idTextField.getText());
                if (healthRep.findById(id) != null) {
                    healthRep.update(id, pacienti, stafi,
                            dataDate, pershkrimi);
                    notificationPopupInfo("HealthRecords - U Rifreskua",
                            "HealthRecords - id: " + id);
                    refreshData();
                    clearFields();
                }
            } else {  
                    HealthRecords healthRecords = new HealthRecords();
                    healthRecords.setDataRek(Date.valueOf(data));
                    healthRecords.setPacientiID(pacienti);
                    healthRecords.setStafiID(stafi);
                    healthRecords.setPershkrimi(pershkrimi);

                    healthRep.add(healthRecords);
                    AlertsMaker.showSimpleAlert("Mesazh", "Te dhenat u ruajten");
                    table.setItems(getHealthData());
                }
    }

    @FXML
    private void fshijBtn(ActionEvent event) {
        clearFields();
    }
    
    public void initColumns() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_pacientiID.setCellValueFactory(new PropertyValueFactory<>("PacientiID"));
        col_stafiID.setCellValueFactory(new PropertyValueFactory<>("StafiID"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("dataRek"));
        col_pershkrimi.setCellValueFactory(new PropertyValueFactory<>("pershkrimi"));
    }

    private ObservableList<HealthRecords> data;
    private HealthRecordsController healthCon;

    public ObservableList<HealthRecords> getHealthData() {

        healthCon = new HealthRecordsController();

        data = FXCollections.observableArrayList(healthCon.getHealthRecords());

        if (data == null) {
            return FXCollections.observableArrayList();
        } else {

            return data;

        }
    }

    private void initTableContextMenu() {
        MenuItem editOption = new MenuItem("Ndrysho");
        editOption.setOnAction((ActionEvent event) -> {
            HealthRecords healthRecords = table.getSelectionModel().getSelectedItem();
            
            idTextField.setText(String.valueOf(healthRecords.getId()));
            datePicker.setValue(healthRecords.getDataRek().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            pershkrimiTextArea.setText(healthRecords.getPershkrimi());
            pacientiIDComboBox.setValue(healthRecords.getPacientiID());
            stafiIDComboBox.setValue(healthRecords.getStafiID());
        });

        MenuItem deleteOption = new MenuItem("Fshij");
        deleteOption.setOnAction(((event) -> {
            HealthRecords item = table.getSelectionModel().getSelectedItem(); 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
            alert.setTitle("Konfirmimi per fshirje");
            alert.setHeaderText("Konfirmoni Fshirjen");
            alert.setContentText("Shenimet me id: " + item.getId());
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
                healthRep.delete(item);
                notificationPopupInfo("Shenimet jane fshirë",
                        "Shenimet jane fshirë me sukses");
            } else {
                notificationPopupInfo("Anulimi i fshirjes",
                        "Fshirja e shenimeve u anulua");
            }
            refreshData();
        }));

        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(editOption, deleteOption);
        table.setContextMenu(menu);
    }

    private void clearFields() {
        pacientiIDComboBox.setValue(null);
        stafiIDComboBox.setValue(null);
        datePicker.setValue(null);
        pershkrimiTextArea.clear();
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
        data = getHealthData();
        table.setItems(data);
        table.refresh();
    }
}
