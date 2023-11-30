
package banque;

import java.util.HashMap;
import java.util.Map;


public class Banque {
    private Map<String, Compte> comptes;

    public Banque() {
        comptes = new HashMap<>();
        initialiserComptes();
    }

    // Initialiser quelques comptes pour les tests
    private void initialiserComptes() {
    	String [] names ={"Merieme Kacimi","Ahmen Massinissa","Katia Mouffok"};
    	for (int i = 1; i <= 3; i++) {
            Compte compte = new Compte (names[i-1],2000*i,"USD",new CartePaiement("23456"+i,"20/01",0000,names[i-1]));
            String key = names[i-1];
            comptes.put(key, compte);
        }
    
    }
    public void recevoirPaiement(String nomDesti, double montant) {
   		 
   		 Compte compte = comptes.get(nomDesti);
   		 
   		 compte.alimenterSolde(montant);
   		System.out.println("Le Solde de "+compte.getIdentifiant()+ " aprés l'alimentation :" +compte.getSolde());

    }
    public boolean virement(String nomTitulaire, double montant) {
    	if (comptes.containsKey(nomTitulaire)){
    		
    	
  		 Compte compte = comptes.get(nomTitulaire);
  		 if (compte!=null )
  		 return compte.effectuerPaiement(montant);
  		 return false;}
    	return false;

   }
  /*  public void voir() {
    	System.out.println("Contenu du HashMap :");
    	for (String key : comptes.keySet()) {
            Compte value = comptes.get(key);
            System.out.println("Key: " + key + ", Compte: " + value.getIdentifiant()+ " "+ value.getSolde()+ " "+ value.getCartePaiement().getNumeroCarte()+ " "+ value.getCartePaiement().getCodeVerification());
        }
		
    }*/
    public String getDevise(String identifiant) {
    	if (comptes.containsKey(identifiant)){
    	Compte c = comptes.get(identifiant);
    	return c.getDevise();}
    	else return "EUR";
    }

    
 
    // Effectue un paiement et met à jour le solde du compte
    public boolean effectuerPaiement(
    		String numeroCarte, String dateExpiration, int codeVerification, String nomTitulaire
    		,double montant) {
    	if (comptes.containsKey(nomTitulaire)) {
    		System.out.print("C");
    	}
    	 if (comptes.containsKey(nomTitulaire)) {
    		 
    		 Compte compte = comptes.get(nomTitulaire);
    	
    		 if (
    				 nomTitulaire.equals(compte.getCartePaiement().getNomTitulaire()) &&
    				 numeroCarte.equals(compte.getCartePaiement().getNumeroCarte()) &&
    				 codeVerification==compte.getCartePaiement().getCodeVerification() &&
    				 dateExpiration.equals(compte.getCartePaiement().getDateExpiration())
    			 ) {
            	 
                 return compte.effectuerPaiement(montant);

    		 }
    		 else {
            	 System.out.println("Données de carte incorrectes");
            	 return false;
    		 }
         }else {
        	 System.out.println("Compte Innéxistant");
        	 return false;

         }
            
    }
}


