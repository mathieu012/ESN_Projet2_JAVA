package Model;

import java.io.IOException;
import java.util.Date;

import Entite.Inventaire;

public class Model {

	Inventaire inventaire;

	public Inventaire conversion(String numero_inventaire,Date date,String article,String numeroLot,String numeroSerie,String lieuStockage,String emplacement,String quantite) throws IOException {
		int numero_inventaireInt = Integer.valueOf(numero_inventaire);
		int numeroLotInt = Integer.valueOf(numeroLot);
		int numeroSerieInt = Integer.valueOf(numeroSerie);
		int quantiteInt = Integer.valueOf(quantite);

		inventaire = new Inventaire(numero_inventaireInt,date,article,numeroLotInt,numeroSerieInt,lieuStockage,emplacement,quantiteInt);
		
		return inventaire;
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}
	
}
