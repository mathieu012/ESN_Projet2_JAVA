package Model;

import java.io.IOException;
import java.util.Date;

import Entite.Inventaire;

public class Model {

	Inventaire inventaire;

	public void traitementData(int numero_inventaire,String article,String numeroLot,String numeroSerie,String lieuStockage,String emplacement,int quantite) throws IOException {
	
		Date date = new Date();
		
		inventaire = new Inventaire(numero_inventaire,date,article,numeroLot,numeroSerie,lieuStockage,emplacement,quantite);
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}
	
}
