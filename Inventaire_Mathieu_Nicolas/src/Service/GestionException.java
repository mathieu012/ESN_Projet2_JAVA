package Service;

import java.io.IOException;
import java.util.List;

public class GestionException {

	public void controleChamp(String champ) throws  NumberFormatException{

		if(champ.equals("")) {

			throw new NumberFormatException("Le champ n'est pas renseigné"); 
		}
	}

	public int checkNumercic(String numeric) {
		try {

			int parse = Integer.parseInt(numeric);
			return parse;

		}catch(NumberFormatException e){
			throw new NumberFormatException("Le format n'est pas le bon");
		}
	}

	//check si le lieu de stockage est référencé dans le fichier csv
	public void checkStockage(String lieuStockage) throws Exception {
		int status = 0;
		List<String[]> lecture = CsvService.readFromCsvFile("lieuStockage");
		for (String[] strings : lecture) {

			if(strings[0].equals(lieuStockage)) {
				status = 1;
			}

		}

		if(status == 0) {
			throw new Exception("Le lieu de stockage n'est pas référencé");
		}
	}

	public void checkArticles(String articles) throws Exception {
		int status = 0;
		List<String[]> lecture = CsvService.readFromCsvFile("articles");
		for (String[] strings : lecture) {

			if(strings[0].equals(articles)) {
				status = 1;
			}

		}

		if(status == 0) {
			throw new Exception("L'article n'est pas référencé");
		}
	}
}