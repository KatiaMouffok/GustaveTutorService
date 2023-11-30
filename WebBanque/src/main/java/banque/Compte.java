package banque;

public class Compte {
    private String identifiant;
    private double solde;
    private String devise;
    private CartePaiement cartePaiement;  // Ajout de la référence à la carte de paiement

    public Compte(String identifiant, double solde, String devise, CartePaiement cartePaiement) {
        this.identifiant = identifiant;
        this.solde = solde;
        this.devise = devise;
        this.cartePaiement = cartePaiement;
    }

    public String getIdentifiant() {
        return identifiant;
    }
   

    public double getSolde() {
        return solde;
    }

    public void alimenterSolde(double value) {
         this.solde=this.solde+value;
    }
    public void consommerSolde(double value) {
        this.solde= this.solde-value;
    }
    

    public String getDevise() {
        return devise;
    }

    public CartePaiement getCartePaiement() {
        return cartePaiement;
    }

    
    public boolean verifierCarte(CartePaiement carte, double montant) {
        if (carte==this.cartePaiement) {
             return effectuerPaiement(montant);
        } else {
            System.out.println("Coordonnées érronées");
            return false;
        }
    }
    
 // Méthode pour effectuer un paiement
    public boolean effectuerPaiement( double montant) {
        if (verifierFondsSuffisants(montant)) {
            consommerSolde(montant);
            System.out.println("Paiement de " + montant + " " + devise + " effectué avec succès.");
            System.out.println("Le Solde de "+ this.getIdentifiant()+" aprés le paiement:" +solde);
            return true;
        } else {
            System.out.println("Fonds insuffisants pour effectuer le paiement.");
            return false;
        }
    }

    // Vérifie la disponibilité des fonds pour une transaction
    private boolean verifierFondsSuffisants(double montant) {
        return solde >= montant;
    }
   
    
}
