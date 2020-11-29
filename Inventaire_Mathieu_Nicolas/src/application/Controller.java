package application;
import java.io.IOException;
import java.util.Date;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Label txtRetour;

	@FXML
	private Button btnValider;

	@FXML
	private Button btnAnnuler;
	
	

	Model model = new Model();

	public void valider(ActionEvent e) {

		try {

			//Date
			Date date = new Date();
			
			//Conversion
			model.conversion(txtIventaire.getText(), date, txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(), txtLieu_stockage.getText(), txtEmplacement.getText(), txtQuantite.getText());
			
			System.out.println(model.getInventaire().getDate());

		}catch(IOException e1) {
			
			txtRetour.setText("Cara");
			
		}catch (NumberFormatException e2) {

			txtRetour.setText("Vous n'avez renseigner aucun champ !");

		}

		//Etape 2 appel de l'objet

		//Etape 3 Stockage
	}


	public void annuler(ActionEvent e) {

	}
}
