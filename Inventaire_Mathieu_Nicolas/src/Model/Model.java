package Model;

import java.io.IOException;
import java.util.Date;

import Entite.Inventaire;

public class Model {

	Inventaire inventaire;

	public void traitementData(String numero_inventaire,Date date,String article,String numeroLot,String numeroSerie,String lieuStockage,String emplacement,String quantite) throws IOException {
		/** Description */
		
		if(numero_inventaire.equals("") || article.equals("") || lieuStockage.equals("") || quantite.equals("")) {
			
			throw new IOException("Certaine données ne sont pas renseigné"); 
		}
		
		int numero_inventaireInt = Integer.valueOf(numero_inventaire);
		int numeroLotInt = Integer.valueOf(numero_inventaire);
		int numeroSerieInt = Integer.valueOf(numero_inventaire);
		int quantiteInt = Integer.valueOf(numero_inventaire);

		inventaire = new Inventaire(numero_inventaireInt,date,article,numeroLotInt,numeroSerieInt,lieuStockage,emplacement,quantiteInt);
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}
	
}
