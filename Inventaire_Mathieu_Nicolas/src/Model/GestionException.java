package Model;

import java.io.IOException;

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
	
}