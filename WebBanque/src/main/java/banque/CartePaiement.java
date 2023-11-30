package banque;

public class CartePaiement {
    private String numeroCarte;
    private String dateExpiration;
    private int codeVerification;
    private String nomTitulaire;
    

    public CartePaiement() {
        
    }
    public CartePaiement(String numeroCarte, String dateExpiration, int codeVerification, String nomTitulaire) {
        this.numeroCarte = numeroCarte;
        this.dateExpiration = dateExpiration;
        this.codeVerification = codeVerification;
        this.nomTitulaire = nomTitulaire;
    }

    public String getNomTitulaire() {
        return nomTitulaire;
    }
    
    public String getNumeroCarte() {
        return numeroCarte;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }


    public int getCodeVerification() {
        return codeVerification;
    }


    // Additional methods for validation, encryption, etc., can be added here
}
