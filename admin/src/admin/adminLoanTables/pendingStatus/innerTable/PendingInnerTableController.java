package admin.adminLoanTables.pendingStatus.innerTable;

import customes.Lenders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servletDTO.admin.InnerTableObj;

import java.util.List;

public class PendingInnerTableController {
    ObservableList<Lenders> LendersObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Lenders, Double> InvestedAmount;
    @FXML
    private TableColumn<Lenders, String> lenderName;
    @FXML
    private TableView<Lenders> pendingInnerTable;

    public void initialize(List<Lenders> lendersObservableList) {
        InvestedAmount.setCellValueFactory(new PropertyValueFactory<Lenders, Double>("deposit"));
        lenderName.setCellValueFactory(new PropertyValueFactory<Lenders, String>("fullName"));

        LendersObservableList.addAll(lendersObservableList);
        pendingInnerTable.setItems(LendersObservableList);
    }

    public void loadTableData(InnerTableObj innerTableObj) {
        LendersObservableList.clear();
        LendersObservableList.addAll(innerTableObj.getLendersList());

        pendingInnerTable.setItems(LendersObservableList);
    }
}