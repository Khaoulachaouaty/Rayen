package Classes;

public class Client {
    private String nomComplet;
    private String adresse;
    private String numeroTelephone;
    private String numCin;
    private String mail;
    private String matriculeVoitureLouee;

    

    public Client(String nomComplet, String adresse, String numeroTelephone, String numCin,
			String matriculeVoitureLouee, String mail) {
		super();
		this.nomComplet = nomComplet;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
		this.numCin = numCin;
		this.matriculeVoitureLouee = matriculeVoitureLouee;
        this.mail = mail;
	}



    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getNumCin() {
        return numCin;
    }

    public void setNumCin(String numCin) {
        this.numCin = numCin;
    }

    public String getMatriculeVoitureLouee() {
        return matriculeVoitureLouee;
    }

    public void setMatriculeVoitureLouee(String matriculeVoitureLouee) {
        this.matriculeVoitureLouee = matriculeVoitureLouee;
    }



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}
    
    
}
