package classes;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import shared.IEtudiant;
import shared.IHoraire;
import shared.IPlateforme;
import shared.ISeance;
import shared.ITuteur;

public class Seance extends UnicastRemoteObject implements ISeance, Serializable{
    private String matiere;
    private IHoraire h;
    private long price; 
    private ITuteur t;
    private IEtudiant e;
    
    
	public Seance() throws RemoteException {
	
	}
	
	
	
	
	public String getMatiere()throws RemoteException {
		return this.matiere;
	}
	public void setMatiere(String matiere) throws RemoteException{
		this.matiere=matiere;
	}
	public IHoraire gethoraire()throws RemoteException {
		return this.h;
	}
	public void setHoraire(IHoraire h) throws RemoteException{
		this.h=h;
	}
	public long getPrice()throws RemoteException {
		return this.price;
	}
	public void setPrice(long price) throws RemoteException{
		this.price=price;
	}
	public ITuteur getTuteur()throws RemoteException {
		return this.t;
	}
	public void setTuteur(ITuteur t) throws RemoteException{
		this.t=t;
	}
	public IEtudiant getEtudiant()throws RemoteException {
		return this.e;
	}
	public void setEtudiant(IEtudiant e) throws RemoteException{
		this.e=e;
	}
	
	

}
