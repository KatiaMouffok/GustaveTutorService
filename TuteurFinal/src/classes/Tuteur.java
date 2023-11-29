package classes;

import shared.IEtudiant;
import shared.IHoraire;
import shared.ISeance;
import shared.ITuteur;
import shared.Utilisateur;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tuteur extends Utilisateur implements Serializable,ITuteur {
    private List<String> domainesExpertise;
    private List<IEtudiant> listeAttente;
    private List<ISeance> mesSeancesTutorat;
    

    public Tuteur(String nom,String email,String password, List<String> domainesExpertise) throws RemoteException {
    	super(nom,email,password,"Tuteur");
        this.domainesExpertise = domainesExpertise;

        this.listeAttente = new ArrayList<IEtudiant>();
        this.mesSeancesTutorat=new ArrayList<ISeance>();
    }
    
    public void Tuteur(String nom,String email,String password, List<String> domainesExpertise) throws RemoteException {

    	Tuteur(nom,email,password,domainesExpertise);
    }

    public List<String> getDomainesExpertise() throws RemoteException{
        return this.domainesExpertise;
    }
    public void addDomaineExpertise(String d)throws RemoteException {
    	List<String>maListe =this.domainesExpertise;
    	maListe.add(d);
    	this.domainesExpertise=maListe;
    	System.out.println(this.getNom());
    	
    }
    
    public List<IEtudiant> getListeAttente()throws RemoteException{
        return this.listeAttente;
    }
    public void addToListAttente (IEtudiant e)throws RemoteException {
    	if (!listeAttente.contains(e)) {
    		this.listeAttente.add(e);
        	System.out.println(" l'etudiant "+ e.getNom()+" est ajouté a la liste d'attente");
    	}else System.out.println("L'etudian fait deja partie de la liste d'attente");
    	
    }
    
    public void notifierEtudiants(ISeance c) throws RemoteException {
        for(IEtudiant e:listeAttente){
            e.addNotification(this.getNom() + " est désormais disponible pour le "+ c.gethoraire().getDate()+" entre "+c.gethoraire().getHeureDebut()+" et "+c.gethoraire().getHeureFin());
            
        }
    }
    
    public List<ISeance> getMesSeancesTutorat()throws RemoteException{
        return this.mesSeancesTutorat;
    }
  
    public void addToListMesSeancesTutorat (ISeance s, long p, IHoraire h) throws RemoteException {
    	s.setTuteur(this);
    	s.setPrice(p);
    	s.setHoraire(h);
    	this.mesSeancesTutorat.add(s);
    	if (s.getEtudiant()==null) {
    		notifierEtudiants(s);
    	}

    }
    public List<ISeance> getSeancesDisponibles() throws RemoteException {
    	List<ISeance> listeResultat = new ArrayList<ISeance>();

        for (ISeance element : this.mesSeancesTutorat) {
        	
            if (element.getEtudiant()==null) {
                listeResultat.add(element);
            }
        }
        return listeResultat;
    }
    

    // Méthode pour libérer le tuteur et indiquer qu'il est disponible
    public void libererTuteurParSeance(ISeance c) throws RemoteException {
        c.setEtudiant(null);
        notifierEtudiants(c);     
    }

    public boolean seanceDisponible(ISeance c) throws RemoteException {
        if (c.getEtudiant()!=null) {return false;}
        else return true;
    }




}
