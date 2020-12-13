package application;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;
import java.util.List;

import Entite.Inventaire;
import Model.GestionException;
import Model.Model;
import Service.CsvService;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Controller implements CsvService {

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

	private boolean fileExist;
	private boolean articleExist;

	GestionException exception = new GestionException();
	Model model = new Model();



	public void look(KeyEvent e) throws Exception {
		
		
		fileExist = CsvService.fileExist(txtInventaire.getText());
		lblRetour.setTextAlignment(TextAlignment.CENTER);
		if (fileExist == true) {
			lblRetour.setText("inventaire existant");
			lblRetour.setTextFill(Color.web("#A8EB12"));
		} else {
			lblRetour.setText("Aucun inventaire conrrespondant");
			lblRetour.setTextFill(Color.web("red"));
			
		}
		
		
		System.out.println(fileExist);

	}

	public void article(KeyEvent e) {
		if(fileExist == true) {//verification que l'article existe
			//Prendre l'article interface
			//lecture du fichier
			List<String[]> lecture = CsvService.readFromCsvFile(txtInventaire.getText());
			for (String[] strings : lecture) {System.out.println(strings[0] == txtArticle.getText());
				
				//Comparer article formulaire avec articles fichier
				if (
						   strings[0].equals(txtArticle.getText())
						&& strings[1].equals(txtNumero_lot.getText())
						&& strings[2].equals(txtNumero_serie.getText())
						&& strings[3].equals(txtLieu_stockage.getText())
						&& strings[4].equals(txtEmplacement.getText()))
				{
				//si l'article est trouv�
					
					
					//afficher les valeurs sur l'interface graphique
					txtArticle.setText(strings[0]);
					txtNumero_lot.setText(strings[1]);
					txtNumero_serie.setText(strings[2]);
					txtLieu_stockage.setText(strings[3]);
					txtEmplacement.setText(strings[4]);
					txtQuantite.setText(strings[5]);
					
					articleExist = true;
					
					lblRetour.setText("Article existant");
					lblRetour.setTextFill(Color.web("#A8EB12"));
					//Sort de la boucle une fois l'article identifié
					break;
				} else {
				//sil'article n'est pas trouvé
					articleExist = false;
					
					lblRetour.setText("Aucun article correspondant");
					lblRetour.setTextFill(Color.web("red"));
				}
				lblRetour.setTextAlignment(TextAlignment.CENTER);
			}
			



		}else {
			if(fileExist == true) {

			}else {

			}
		}



	}



	public void valider(ActionEvent e) throws Exception {

		//Aucun fichier n'existe alors on construit un nouveau fichier
		if(fileExist == false) {

			this.checkForm();

			this.checkInteger();

			Inventaire inventaireObj = model.traitementData(Integer.parseInt(txtInventaire.getText()),
					txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(),
					txtLieu_stockage.getText(), txtEmplacement.getText(), Integer.parseInt(txtQuantite.getText()));

			CsvService.writeToCsvFile(inventaireObj, txtInventaire.getText());

		}
		
		//On ins�re un nouvel article dans un inventaire d�j� existant
		if(fileExist == true && articleExist == false) {
			
			this.checkForm();
			this.checkInteger();
			
			Inventaire inventaireObj = model.traitementData(Integer.parseInt(txtInventaire.getText()),
					txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(),
					txtLieu_stockage.getText(), txtEmplacement.getText(), Integer.parseInt(txtQuantite.getText()));

			CsvService.writeToCsvFile(inventaireObj, txtInventaire.getText());
			
			
		}
		
		//Modification des valeurs d'un article 
		if(fileExist == true && articleExist == true) {
			
		}

		/*try {
			List<String[]> lecture = CsvService.readFromCsvFile(String.valueOf(inventaireObj.getNumero_inventaire()));
			for (String[] strings : lecture) {
				for (int i = 0; i < strings.length; i++) {
					System.out.println(strings[i]);
				}
			}

		} catch (IOException e1) {

			System.out.println(e1.getMessage());

		} catch (Exception e1) {

			this.lblRetour.setText(e1.getMessage());

		}*/

	}

	private void checkInteger() throws Exception {

		try {

			exception.checkNumercic(txtInventaire.getText());

		} catch (NumberFormatException e1) {

			throw new Exception("Le champ inventaire n'est pas au bon format");

		}

		try {

			exception.checkNumercic(txtQuantite.getText());

		} catch (NumberFormatException e1) {

			throw new Exception("Le champ quantite n'est pas au bon format");

		}
	}

	private void checkForm() throws Exception {

		try {
			exception.controleChamp(txtInventaire.getText());
		} catch (NumberFormatException e1) {
			throw new Exception("Le champ inventaire est vide");

		}

		try {
			exception.controleChamp(txtArticle.getText());
		} catch (NumberFormatException e1) {
			throw new Exception("Le champ article est vide");

		}

		try {
			exception.controleChamp(txtLieu_stockage.getText());
		} catch (NumberFormatException e1) {
			throw new Exception("Le champ lieu de stockage est vide");

		}

		try {
			exception.controleChamp(txtQuantite.getText());
		} catch (NumberFormatException e1) {
			throw new Exception("Le champ quantite est vide");

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
