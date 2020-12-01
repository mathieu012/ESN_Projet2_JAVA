package Model;

import java.io.IOException;
import java.util.Date;

import Entite.Inventaire;

public class Model {

	public Inventaire traitementData(int numero_inventaire,String article,String numeroLot,String numeroSerie,String lieuStockage,String emplacement,int quantite) throws IOException {

		Date date = new Date();

		Inventaire inventaire = new Inventaire(numero_inventaire,date,article,numeroLot,numeroSerie,lieuStockage,emplacement,quantite);

		return inventaire;
	}


}
