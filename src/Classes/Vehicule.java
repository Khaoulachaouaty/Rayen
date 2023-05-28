package Classes;

public class Vehicule {
    private String marque;
    private String modele;
    private String categorie;
    private String couleur;
    private double prix;
    private String statut;
    private String matricule;   
    
    
    public Vehicule(String marque, String modele, String categorie, String couleur, double prix, String statut,
			String matricule) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.couleur = couleur;
		this.prix = prix;
		this.statut = statut;
		this.matricule = matricule;
	}


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
