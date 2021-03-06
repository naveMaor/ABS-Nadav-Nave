package admin.adminLoanTables.riskStatus.innerTable;

import Money.operations.Payment;
import customes.Lenders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servletDTO.admin.InnerTableObj;

import java.util.List;

public class riskInnerTableController {

    ObservableList<Lenders> LendersObservableList = FXCollections.observableArrayList();
    ObservableList<Payment> PaymentObservableList = FXCollections.observableArrayList();
    private int amountNotPayed = 0;
    private int numNotPayed = 0;
    @FXML
    private TableColumn<Lenders, Double> InvestedAmount;
    @FXML
    private TableColumn<Lenders, String> lenderName;
    @FXML
    private TableView<Lenders> pendingInnerTable;
    @FXML
    private TableView<Payment> borrowerPayements;
    @FXML
    private TableColumn<Payment, Double> fund;
    @FXML
    private TableColumn<Payment, Double> interest;
    @FXML
    private TableColumn<Payment, Double> paymentAmount;
    @FXML
    private TableColumn<Payment, Integer> yaz;
    @FXML
    private Label amountNotPayedLable;
    @FXML
    private Label numNotPayedLable;
    @FXML
    private TableColumn<Payment, Boolean> payed;

    public void initialize() {
        InvestedAmount.setCellValueFactory(new PropertyValueFactory<Lenders, Double>("deposit"));
        lenderName.setCellValueFactory(new PropertyValueFactory<Lenders, String>("fullName"));
        fund.setCellValueFactory(new PropertyValueFactory<Payment, Double>("fundPortion"));
        interest.setCellValueFactory(new PropertyValueFactory<Payment, Double>("interestPortion"));
        paymentAmount.setCellValueFactory(new PropertyValueFactory<Payment, Double>("fundPlusInterest"));
        yaz.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("paymentYaz"));
        payed.setCellValueFactory(new PropertyValueFactory<Payment, Boolean>("isPayed"));
    }


    private void customiseFactory(TableColumn<Payment, Boolean> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Payment, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Payment> currentRow = getTableRow();

                    if (!isEmpty()) {

                        if (!item)
                            currentRow.setStyle("-fx-background-color:red");
                        else
                            currentRow.setStyle("-fx-background-color:green");
                    }
                }
            };
        });
    }

    private void setNotPayedData(List<Payment> paymentObservableList) {
        amountNotPayed = 0;
        numNotPayed = 0;
        for (Payment payment : paymentObservableList) {
            if (!payment.getIsPayed()) {
                ++numNotPayed;
                amountNotPayed += payment.getFundPlusInterest();
            }
        }
    }

    public void loadTableData(InnerTableObj innerTableObj) {
        LendersObservableList.clear();
        LendersObservableList.addAll(innerTableObj.getLendersList());
        PaymentObservableList.clear();
        PaymentObservableList.addAll(innerTableObj.getPaymentList());


        pendingInnerTable.setItems(LendersObservableList);
        borrowerPayements.setItems(PaymentObservableList);
        customiseFactory(payed);
        setNotPayedData(innerTableObj.getPaymentList());
        amountNotPayedLable.textProperty().set(String.valueOf(amountNotPayed));
        numNotPayedLable.textProperty().set(String.valueOf(numNotPayed));
    }
}
