package Entite;

import java.util.Date;

public class Inventaire {
	
	private int numero_inventaire;
	private Date date;
	private String article;
	private String numeroLot;
	private String numeroSerie;
	private String lieuStockage;
	private String emplacement;
	private int quantite;
	
	public Inventaire(int numero_inventaire, Date date,String article,String numeroLot,String numeroSerie,String lieuStockage,String emplacement,int quantite) {
		
		super();
		this.numero_inventaire = numero_inventaire;
		this.date = date;
		this.article = article;
		this.numeroLot = numeroLot;
		this.numeroSerie = numeroSerie;
		this.lieuStockage = lieuStockage;
		this.emplacement = emplacement;
		this.quantite = quantite;
		
	}

	public int getNumero_inventaire() {
		return numero_inventaire;
	}

	public void setNumero_inventaire(int numero_inventaire) {
		this.numero_inventaire = numero_inventaire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getNumeroLot() {
		return numeroLot;
	}

	public void setNumeroLot(String numeroLot) {
		this.numeroLot = numeroLot;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getLieuStockage() {
		return lieuStockage;
	}

	public void setLieuStockage(String lieuStockage) {
		this.lieuStockage = lieuStockage;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	

}
