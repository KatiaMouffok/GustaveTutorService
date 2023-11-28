package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ITuteur extends IUtilisateur, Remote {

	public List<String> getDomainesExpertise() throws RemoteException;
    public void addDomaineExpertise(String d)throws RemoteException ;
    public List<IEtudiant> getListeAttente()throws RemoteException;
    public void addToListAttente (IEtudiant e)throws RemoteException;
    public void notifierEtudiants(ISeance c) throws RemoteException;
    public List<ISeance> getMesSeancesTutorat()throws RemoteException;
  
    public void addToListMesSeancesTutorat (ISeance s, long p, IHoraire h) throws RemoteException ;
    public List<ISeance> getSeancesDisponibles() throws RemoteException ;

    // Méthode pour libérer le tuteur et indiquer qu'il est disponible
    public void libererTuteurParSeance(ISeance c) throws RemoteException;

    public boolean seanceDisponible(ISeance c) throws RemoteException;

}
