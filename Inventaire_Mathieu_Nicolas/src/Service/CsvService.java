package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

import Entite.Inventaire;

public interface CsvService {
	
	public static final String separator = ";";
	public static final String path = "src/file/";
	public static final String fileType = ".csv";
	
	
	public static void writeToCsvFile(Inventaire inventaire, String fileName) throws FileAlreadyExistsException {
		if (CsvService.fileExist(fileName) == false) {
			CsvService.createCsvFile(fileName);
		}
		
		String line = inventaire.getArticle() + CsvService.separator + inventaire.getNumeroLot() + CsvService.separator + inventaire.getNumeroSerie() + CsvService.separator + inventaire.getLieuStockage() +  CsvService.separator  + inventaire.getEmplacement() +  CsvService.separator  + inventaire.getQuantite();
		
		try(FileWriter fw = new FileWriter(path + fileName + fileType, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(line);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}

	
	public static List<String[]> readFromCsvFile(String fileName){
	    try (BufferedReader reader = new BufferedReader(new FileReader(path + fileName + fileType))){
	        List<String[]> list = new ArrayList<>();
	        String line = "";
	        while((line = reader.readLine()) != null){
	            String[] array = line.split(CsvService.separator);
	            list.add(array);
	        }
	        return list;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }  
	}
	
	public static boolean fileExist(String fileName) throws FileAlreadyExistsException {
		File f = new File(path + fileName + fileType);

	      if(f.exists()){
	          System.out.println("File existed");
	          return true;
	      }else{
	    	  System.out.println("File not found!");
	    	  return false;
	    	  //throw new FileAlreadyExistsException("l'inventaire N° " + fileName + " n'a pas été trouve");
	      }
	      
	      

	}
	
	public static void createCsvFile(String fileName) {
		File fichier = new File(path + fileName + fileType);
	      try {
			if (fichier.createNewFile()) {
			    System.out.println("Le fichier a été créé");
				throw new FileSystemException("L'inventaire N° " + fileName + " a été creer");
			}else{
			    System.out.println("Erreur, Impossible de créer ce fichier");
			    throw new FileSystemException("Le fichier N° " + fileName + "ne peut pas créer le fichier");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
