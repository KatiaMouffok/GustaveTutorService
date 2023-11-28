package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IEtudiant extends IUtilisateur, Remote {

	public void addNotification(String n) throws RemoteException ;
	public List<String> getNotifications() throws RemoteException;
	public void reserverSeance(ISeance s, String m) throws RemoteException;
	public List<ISeance> getMesSeances() throws RemoteException;
}
