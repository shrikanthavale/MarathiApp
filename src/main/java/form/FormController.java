package form;

import database.entity.ServiceRequest;
import database.service.ServiceRequestService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormController {

    private final ServiceRequestService serviceRequestService;
    @FXML
    private TableView<ServiceRequest> requestTable;

    @FXML
    private TableColumn<ServiceRequest, String> requestName;
    @FXML
    private TableColumn<ServiceRequest, String> requestAddress;
    @FXML
    private TableColumn<ServiceRequest, String> requestAmount;
    @FXML
    private TableColumn<ServiceRequest, String> requestStatus;
    @FXML
    private TableColumn<ServiceRequest, String> id;
    @FXML
    private TextField editUsername;
    @FXML
    private TextArea editUserAddress;
    @FXML
    private TextField editAmount;

    /**
     * Service Request controller initialization.
     *
     * @param serviceRequestService service request service.
     */
    @Autowired
    public FormController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @FXML
    public void initialize() {
        requestName.setCellValueFactory(new PropertyValueFactory<>("name"));
        requestAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        requestAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        requestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        requestTable.getItems().addAll(serviceRequestService.getServiceRequests());
    }

    @FXML
    public void saveServiceRequest() {
        final String username = editUsername.getText();
        final String address = editUserAddress.getText();
        final String amount = editAmount.getText();
        serviceRequestService.save(new ServiceRequest(username, address, Long.parseLong(amount), null));
        reload();
    }

    @FXML
    public void clearData() {
        editUsername.clear();
        editUserAddress.clear();
        editAmount.clear();
    }

    @FXML
    public void reload() {
        requestTable.getItems().clear();
        requestTable.getItems().addAll(serviceRequestService.getServiceRequests());
    }
}
