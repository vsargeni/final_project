package rocket.app.view;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private TextField txtMortgagePayment;
	@FXML
	private TextField txtRate;
	@FXML
	private Label labelIncome;
	@FXML
	private Label labelExpenses;
	@FXML
	private Label labelCreditScore;
	@FXML
	private Label labelHouseCost;
	@FXML
	private Label labelDownPayment;
	@FXML
	private Label labelMortgagePayment;
	@FXML
	private Label labelRate;
	@FXML
	private Label labelError;
	@FXML
	private Button btnCalcPayment;
	ObservableList<String> listComboBox = FXCollections.observableArrayList("15 Years","30 Years");
	@FXML
	private ComboBox cmbTerm;
	
	
	public void initialize(URL location, ResourceBundle resources){
	    cmbTerm.getItems().removeAll(cmbTerm.getItems());
	    cmbTerm.getItems().addAll("15 Years", "30 Years");
	}
	
	
	
	private TextField txtNew;
	
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(txtNew.getText());
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdPayment(Double.parseDouble(txtHouseCost.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()) - Integer.parseInt(txtDownPayment.getText()));
		lq.setIncome(Integer.parseInt(txtIncome.getText()));
		lq.setExpenses(Integer.parseInt(txtExpenses.getText()));
		
		try{
			lq.setdRate(RateBLL.getRate(Integer.parseInt(txtCreditScore.getText())));
		} catch (RateException exception){
			lq.setdRate(-1);
			labelError.setText("Payment Exception, Error");
		}
		if (cmbTerm.getSelectionModel().getSelectedItem().toString() == "30 Years"){
			lq.setiTerm(30);
		} else {
			lq.setiTerm(15);
		}
		
		a.setLoanRequest(lq);		
		mainApp.messageSend(lq);
	}
	
}

