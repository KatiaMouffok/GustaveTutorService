package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISeance extends Remote{
	public String getMatiere()throws RemoteException ;
	public void setMatiere(String matiere) throws RemoteException;
	public IHoraire gethoraire()throws RemoteException ;
	public void setHoraire(IHoraire h) throws RemoteException;
	public IEtudiant getEtudiant()throws RemoteException;
	public void setEtudiant(IEtudiant e) throws RemoteException;
	public ITuteur getTuteur()throws RemoteException;
	public void setTuteur(ITuteur t) throws RemoteException;
	public long getPrice()throws RemoteException ;
	public void setPrice(long price) throws RemoteException;
}
