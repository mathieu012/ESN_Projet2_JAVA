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

	private String colorOk = "#A8EB12";
	private String colorAvertissement = "#FFC300";
	private String colorException = "#ff5946";

	GestionException exception = new GestionException();
	Model model = new Model();

	public void look(KeyEvent e) throws Exception {

		fileExist = CsvService.fileExist(txtInventaire.getText());

		if (fileExist == true) {
			lblRetour.setText("inventaire existant");
			lblRetour.setTextFill(Color.web(this.colorOk));
		} else {
			lblRetour.setText("Aucun inventaire conrrespondant");
			lblRetour.setTextFill(Color.web(this.colorAvertissement));

		}

		System.out.println(fileExist);

	}

	public void article(KeyEvent e) {

		if (fileExist == true) {// verification que l'article existe
			// Prendre l'article interface
			// lecture du fichier
			List<String[]> lecture = CsvService.readFromCsvFile(txtInventaire.getText());
			for (String[] strings : lecture) {

				System.out.println(strings[0] == txtArticle.getText());

				// Comparer article formulaire avec articles fichier
				if (strings[0].equals(txtArticle.getText())
				/*
				 * && strings[1].equals(txtNumero_lot.getText()) //&&
				 * strings[2].equals(txtNumero_serie.getText()) //&&
				 * strings[3].equals(txtLieu_stockage.getText()) //&&
				 * strings[4].equals(txtEmplacement.getText())
				 */) {
					// si l'article est trouv�

					// afficher les valeurs sur l'interface graphique
					txtArticle.setText(strings[0]);
					txtNumero_lot.setText(strings[1]);
					txtNumero_serie.setText(strings[2]);
					txtLieu_stockage.setText(strings[3]);
					txtEmplacement.setText(strings[4]);
					txtQuantite.setText(strings[5]);

					articleExist = true;

					lblRetour.setText("Article existant");
					lblRetour.setTextFill(Color.web(this.colorOk));
					// Sort de la boucle une fois l'article identifié
					break;
				} else {
					// sil'article n'est pas trouvé
					articleExist = false;

					lblRetour.setText("Aucun article n'est présent dans cette inventaire");
					lblRetour.setTextFill(Color.web(this.colorAvertissement));

				}
			}
		}
	}

	public void valider(ActionEvent e) {
		if (Integer.parseInt(txtQuantite.getText()) == 0) {
			
			lblRetour.setText("La quantité doit etre > 0");
			lblRetour.setTextFill(Color.web(this.colorAvertissement));
			
		} else {
			try {

				// Aucun fichier n'existe alors on construit un nouveau fichier
				if (fileExist == false) {

					this.checkForm();
					this.checkInteger();

					Inventaire inventaireObj = model.traitementData(Integer.parseInt(txtInventaire.getText()),
							txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(),
							txtLieu_stockage.getText(), txtEmplacement.getText(),
							Integer.parseInt(txtQuantite.getText()));

					CsvService.writeToCsvFile(inventaireObj, txtInventaire.getText());

					lblRetour.setText("Un nouvel inventaire N° " + txtInventaire.getText()
							+ " a été crée avec l'article " + txtArticle.getText());
					lblRetour.setTextFill(Color.web(this.colorOk));
					this.clear();

				}

				// On ins�re un nouvel article dans un inventaire d�j� existant
				if (fileExist == true && articleExist == false) {

					this.checkForm();
					this.checkInteger();

					Inventaire inventaireObj = model.traitementData(Integer.parseInt(txtInventaire.getText()),
							txtArticle.getText(), txtNumero_lot.getText(), txtNumero_serie.getText(),
							txtLieu_stockage.getText(), txtEmplacement.getText(),
							Integer.parseInt(txtQuantite.getText()));

					CsvService.writeToCsvFile(inventaireObj, txtInventaire.getText());

					lblRetour.setText("l'article " + txtArticle.getText() + " a été ajouter à l'inventaire N° "
							+ txtInventaire.getText() + " avec succés");
					lblRetour.setTextFill(Color.web(this.colorOk));
					this.clear();
				}

				// Modification des valeurs d'un article
				if (fileExist == true && articleExist == true) {

					this.checkForm();
					this.checkInteger();

					List<String[]> lecture = CsvService.readFromCsvFile(txtInventaire.getText());
					CsvService.supp(txtInventaire.getText());
					CsvService.createCsvFile(txtInventaire.getText());
					for (String[] strings : lecture) {

						if (strings[0].equals(txtArticle.getText())) {
							strings[1] = txtNumero_lot.getText();
							strings[2] = txtNumero_serie.getText();
							strings[3] = txtLieu_stockage.getText();
							strings[4] = txtEmplacement.getText();
							strings[5] = txtQuantite.getText();
						}
						CsvService.modifToCsvFile(strings, txtInventaire.getText());
					}

					lblRetour.setText("l'article " + txtArticle.getText() + " a été modifié dans l'inventaire N° "
							+ txtInventaire.getText() + " avec succés");
					lblRetour.setTextFill(Color.web(this.colorOk));
					this.clear();
				}

			} catch (Exception e1) {
				lblRetour.setText(e1.getMessage());
				lblRetour.setTextFill(Color.web(this.colorException));
			}
		}

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
		txtQuantite.setText("0");
		lblRetour.setText("");

	}

	public void clear() {

		txtInventaire.setText("");
		txtArticle.setText("");
		txtNumero_lot.setText("");
		txtNumero_serie.setText("");
		txtLieu_stockage.setText("");
		txtEmplacement.setText("");
		txtQuantite.setText("0");
		// lblRetour.setText("");

	}

}
