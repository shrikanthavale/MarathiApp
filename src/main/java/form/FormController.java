package form;

import database.dto.ServiceRequestDto;
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
import util.MarathiNumberFormatter;

import java.math.BigDecimal;
import java.text.ParseException;

@Component
public class FormController {

    private final ServiceRequestService serviceRequestService;
    @FXML
    private TableView<ServiceRequestDto> requestTable;
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
    private TableColumn<ServiceRequest, String> calculatedInterestRate;
    @FXML
    private TextField editUsername;
    @FXML
    private TextArea editUserAddress;
    @FXML
    private TextField editAmount;
    private final MarathiNumberFormatter marathiNumberFormatter;

    /**
     * Service Request controller initialization.
     *
     * @param serviceRequestService service request service.
     */
    @Autowired
    public FormController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
        marathiNumberFormatter = new MarathiNumberFormatter();
    }

    @FXML
    public void initialize() {
        requestName.setCellValueFactory(new PropertyValueFactory<>("name"));
        requestAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        requestAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        requestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        calculatedInterestRate.setCellValueFactory(new PropertyValueFactory<>("calculatedAmount"));
        requestTable.getItems().addAll(serviceRequestService.getServiceRequests());
        setRandomMarathiNumber();
    }

    @FXML
    public void saveServiceRequest() throws ParseException {
        final String username = editUsername.getText();
        final String address = editUserAddress.getText();
        final String amount = editAmount.getText();
        serviceRequestService.save(
                new ServiceRequest(
                        username,
                        address,
                        marathiNumberFormatter.extractEnglishNumber(amount),
                        null
                )
        );
        reload();
        clearData();
        setRandomMarathiNumber();
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

    private void setRandomMarathiNumber() {
        double randomNumber = ((Math.random() * 90000) + 10) / 10.0;
        editAmount.setText(
                marathiNumberFormatter.format(BigDecimal.valueOf(randomNumber), false)
        );
    }
}
