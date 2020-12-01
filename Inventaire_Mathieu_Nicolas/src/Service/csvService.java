package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;

import Entite.Inventaire;

public interface csvService {
	
	public static final String separator = ";";
	public static final String path = "src/file/";
	public static final String fileType = ".csv";
	public static final String fileName ="";
	
	public static void writeToCsvFile(Inventaire inventaire, String fileName) throws FileAlreadyExistsException {
		if (csvService.fileExist(fileName) != true) {
			csvService.createCsvFile(fileName);
		}
		
		
		
		try (FileWriter writer = new FileWriter(path + fileName + fileType)){
			
			String line = inventaire.getArticle() + csvService.separator + inventaire.getNumeroLot() + csvService.separator + inventaire.getNumeroSerie() + csvService.separator + inventaire.getLieuStockage() +  csvService.separator  + inventaire.getEmplacement() +  csvService.separator  + inventaire.getQuantite();
	        writer.append(line);
	        writer.flush();
	    } catch (IOException e) {
	    	System.out.println("Impossible d'ecrire dans le fichier");
	        e.printStackTrace();
	    }
	}
	
	public static List<String[]> readFromCsvFile(String separator, String fileName){
	    try (BufferedReader reader = new BufferedReader(new FileReader(path + fileName + fileType))){
	        List<String[]> list = new ArrayList<>();
	        String line = "";
	        while((line = reader.readLine()) != null){
	            String[] array = line.split(separator);
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
	    	  throw new FileAlreadyExistsException("l'inventaire N° " + fileName + " n'a pas été trouve");     
	      }
	      
	      

	}
	
	public static void createCsvFile(String fileName) {
		File fichier = new File(path + fileName + fileType);
	      try {
			if (fichier.createNewFile()) {
			    System.out.println("Le fichier a été créé");
				throw new FileSystemException("L'inventaire N° " + fileName + " n'a pas été creer");
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
