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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Etudiant extends Utilisateur implements IEtudiant, Serializable {
	
  	
    private List<ISeance> mesSeances;
	private List<String> notifications;


	public Etudiant(String n, String e, String p) throws RemoteException {
		super(n, e, p,"Etudiant");
		this.mesSeances = new ArrayList<ISeance>(); 
		this.notifications = new LinkedList<String>();
		
	}
	
    /*****************************************************************************************/
	
	@Override
	public void addNotification(String n) throws RemoteException {
		this.notifications.add(n);
		System.out.println("Notification ajoutée : "+n);
	}

	@Override
	public List<String> getNotifications() throws RemoteException {
		return this.notifications;
	}
	
    /*****************************************************************************************/

	public List<ISeance> getMesSeances() throws RemoteException{
		// TODO Auto-generated method stub
		return this.mesSeances;
	}
    
	public void reserverSeance(ISeance s, String m) throws RemoteException {
		
		if (s.getTuteur().getDomainesExpertise().contains(m)) {
			s.setEtudiant(this);
			s.setMatiere(m);
			this.mesSeances.add(s);
			
		}
		
		
	}

}
