//package engine;
//
//import customes.Client;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Service;
//import javafx.concurrent.Task;
//import loan.Loan;
//
//import java.util.List;
//
//public class scrambleService extends Service<ObservableList<Loan>> {
//    private Engine engine=Engine.getInstance();
//    private String clientName;
//    private int minInterest;
//    private int minYaz;
//    private int maxOpenLoans;
//    private int maxOwnership;
//    private List<String> existChosenCategories = FXCollections.observableArrayList();
//    private List<Loan> userFilteredLoanList =  FXCollections.observableArrayList();
//
//
//    public String getClientName() {
//        return clientName;
//    }
//
//    public Client getClient() {
//        return engine.getDatabase().getClientByname(clientName);
//    }
//
//    public void setClientName(String clientName) {
//        this.clientName = clientName;
//    }
//
//
//    public int getMinInterest() {
//        return minInterest;
//    }
//
//    public void setMinInterest(int minInterest) {
//        this.minInterest = minInterest;
//    }
//
//    public int getMinYaz() {
//        return minYaz;
//    }
//
//    public void setMinYaz(int minYaz) {
//        this.minYaz = minYaz;
//    }
//
//    public int getMaxOpenLoans() {
//        return maxOpenLoans;
//    }
//
//    public void setMaxOpenLoans(int maxOpenLoans) {
//        this.maxOpenLoans = maxOpenLoans;
//    }
//
//    public ObservableList<String> getExistChosenCategories() {
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(existChosenCategories);
//        return observableList;
//    }
//
//    public void setExistChosenCategories(ObservableList<String> existChosenCategories) {
//        this.existChosenCategories = existChosenCategories;
//    }
//
//    public int getMaxOwnership() {
//        return maxOwnership;
//    }
//
//    public void setMaxOwnership(int maxOwnership) {
//        this.maxOwnership = maxOwnership;
//    }
//
//    public scrambleService(String clientName, int minInterest, int minYaz, int maxOpenLoans, ObservableList<String> existChoosenCategories,int maxOwnership) {
//        this.clientName = clientName;
//        this.minInterest = minInterest;
//        this.minYaz = minYaz;
//        this.maxOpenLoans = maxOpenLoans;
//        this.existChosenCategories = existChoosenCategories;
//        this.maxOwnership = maxOwnership;
//    }
//
//    public void getLoans() {
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//        observableList.addAll(existChosenCategories);
//        setExistChosenCategories
//        ObservableList<Loan> observableList = FXCollections.observableArrayList();
//        observableList.addAll(userFilteredLoanList);
//        return observableList;
//        userFilteredLoanList = engine.O_getLoansToInvestList(clientName,minInterest,minYaz,maxOpenLoans,existChoosenCategories,maxOwnership);
//
//    }
//    @Override
//    protected Task<ObservableList<Loan>> createTask() {
//        return new Task<ObservableList<Loan>>() {
//
////            final String clientName = getClientName();
////            final int minInterest = getMinInterest();
////            final int minYaz = getMinYaz();
////            final int maxOpenLoans = getMaxOpenLoans();
////            final ObservableList<String>  existChoosenCategories= getExistChosenCategories();
////            @Override
////            protected ObservableList<Loan> call() throws Exception {
////                updateProgress(0, 500); //0% progress
////
////                Thread.sleep(1505);
////
////                updateProgress(30, 500); //6% progress
////
////                Thread.sleep(1505);
////
////                updateProgress(100, 500); //20% progress
////
////                Thread.sleep(1505);
////                updateProgress(150, 500); //30% progress
////
////                Thread.sleep(1505);
////                updateProgress(200, 500); //40% progress
////
////                double clientBalance =engine.getDatabase().getClientByname(clientName).getMyAccount().getCurrBalance();
////
////                Thread.sleep(1505);
////                updateProgress(500, 500); //100% task complete
////
////
////            }
////        };
////    }
//
////    @Override
////    public void start() {
////        setClientName(clientName);
////        setMaxOpenLoans(maxOpenLoans);
////        setMinInterest(minInterest);
////        setMinYaz(minYaz);
////        ObservableList<String> observableList = FXCollections.observableArrayList();
////        observableList.addAll(existChosenCategories);
////        setExistChosenCategories(observableList);
////        super.start();
////    }
//}
