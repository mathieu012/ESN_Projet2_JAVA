package application;
import java.io.IOException;
import java.util.Date;


import Entite.Inventaire;
import Model.GestionException;
import Model.Model;
import Service.csvService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller implements csvService{

	@FXML
	private TextField txtIventaire;

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

	public void valider(ActionEvent e) throws IOException {
		
		int inventaire;
		int quantite;
		
		try {
			exception.controleChamp(txtIventaire.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ inventaire est vide");
		     return;
		    }
		
		try {
			exception.controleChamp(txtArticle.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ article est vide");
		     return;
		    }
		
		try {
			exception.controleChamp(txtLieu_stockage.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ lieu de stockage est vide");
		     return;
		    }
		
		try {
			exception.controleChamp(txtQuantite.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ quantite est vide");
		     return;
		    }	

		
		try {
			inventaire = exception.checkNumercic(txtIventaire.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ inventaire n'est pas au bon format");
		     return;
		    }
		
		try {
			quantite = exception.checkNumercic(txtQuantite.getText());
		    } catch (NumberFormatException e1) {
		     lblRetour.setText("La champ quantite n'est pas au bon format");
		     return;
		    }
		
			model.traitementData(inventaire, txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(), txtLieu_stockage.getText(), txtEmplacement.getText(), quantite);
			
			//Conversion
			Inventaire inventaireObj = model.getInventaire();
			
			csvService.createCsvFile(txtIventaire.getText());
			
			
			//String line = txtArticle.getText() + ";" + txtNumero_lot.getText() + ";" + txtNumero_serie.getText() + ";" + txtLieu_stockage.getText() + ";" + txtEmplacement.getText() + ";" + txtQuantite.getText();
			
			
			csvService.writeToCsvFile(inventaireObj, txtIventaire.getText());
			
			System.out.println(model.getInventaire().getDate());
	}
			
			


	public void annuler(ActionEvent e) {

	}
	

		
			
	
		
		
		
	
	
}





