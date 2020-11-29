package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField iventaire;

	@FXML
	private TextField article;

	@FXML
	private TextField numero_lot;

	@FXML
	private TextField numero_serie;

	@FXML
	private TextField lieu_stockage;

	@FXML
	private TextField emplacement;

	@FXML
	private TextField quantite;

	@FXML
	private Button valider;

	@FXML
	private Button annuler;


	public void valider(ActionEvent e) {

	}

	public void annuler(ActionEvent e) {

	}
}
