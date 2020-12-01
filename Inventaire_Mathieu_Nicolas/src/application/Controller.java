package application;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import Entite.Inventaire;
import Model.GestionException;
import Model.Model;
import Service.csvService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller implements csvService{

	@FXML
	private TextField txtInventaire;

	@FXML
	private TextField txtArticle;

	@FXML
	private TextField txtNumero_lot;

	@FXML
	private TextField txtNumero_serie;

	@FXML
	private TextField txtLieu_stockage;

	@FXML
	private TextField txtEmplacement;

	@FXML
	private TextField txtQuantite;

	@FXML
	private Label lblRetour;

	@FXML
	private Button btnValider;

	@FXML
	private Button btnAnnuler;

	GestionException exception = new GestionException();
	Model model = new Model();

	public void valider(ActionEvent e){


		try {

			this.checkForm();

			this.checkInteger();

			Inventaire inventaireObj = model.traitementData(Integer.parseInt(txtInventaire.getText()), txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(), txtLieu_stockage.getText(), txtEmplacement.getText(), Integer.parseInt(txtQuantite.getText()));

			csvService.createCsvFile(txtInventaire.getText());

			csvService.writeToCsvFile(inventaireObj, txtInventaire.getText());
      
      List<String[]>lecture  = csvService.readFromCsvFile(inventaireObj.getNumero_inventaire());

		}catch(IOException e1){
			
			System.out.println(e1.getMessage());
			

		}catch(Exception e1) {

			
			this.lblRetour.setText(e1.getMessage());
			
		}

	}

	private void checkInteger() throws Exception{

		try {

			exception.checkNumercic(txtInventaire.getText());

		} catch (NumberFormatException e1) {

			throw new Exception ("Le champ inventaire n'est pas au bon format");

			
		}

		try {

			exception.checkNumercic(txtQuantite.getText());
	
		} catch (NumberFormatException e1) {

			throw new Exception ("Le champ quantite n'est pas au bon format");
		
		}
	}


	private void checkForm() throws Exception{

		try {
			exception.controleChamp(txtInventaire.getText());
		} catch (NumberFormatException e1) {
			throw new Exception ("Le champ inventaire est vide");

		}

		try {
			exception.controleChamp(txtArticle.getText());
		} catch (NumberFormatException e1) {
			throw new Exception ("Le champ article est vide");

		}

		try {
			exception.controleChamp(txtLieu_stockage.getText());
		} catch (NumberFormatException e1) {
			throw new Exception ("Le champ lieu de stockage est vide");

		}

		try {
			exception.controleChamp(txtQuantite.getText());
		} catch (NumberFormatException e1) {
			throw new Exception ("Le champ quantite est vide");

		}

	}


	public void annuler(ActionEvent e) {

		txtInventaire.setText("");
		txtArticle.setText("");
		txtNumero_lot.setText("");
		txtNumero_serie.setText("");
		txtLieu_stockage.setText("");
		txtEmplacement.setText("");
		txtQuantite.setText("");

	}










}





