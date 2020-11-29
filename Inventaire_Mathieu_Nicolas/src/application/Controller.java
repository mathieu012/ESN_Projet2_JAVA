package application;
import java.io.IOException;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

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
	private Button btnValider;

	@FXML
	private Button btnAnnuler;
	
	private String date;

	Model model = new Model();

	public void valider(ActionEvent e) {
		
		try {
			
			//Etape 1 conversion
			model.conversion(txtIventaire.getText(), date, txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(), txtLieu_stockage.getText(), txtEmplacement.getText(), txtQuantite.getText());
			System.out.println(model.getInventaire().getArticle());
			
		} catch (IOException e1) {
			
			
			e1.printStackTrace();
			
		}
		
		//Etape 2 appel de l'objet
		
		//Etape 3 Stockage
	}


	public void annuler(ActionEvent e) {

	}
}
