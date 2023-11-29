package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.ISeance;

public interface IPlateforme extends Remote {

    public ISeance programmerSeance() throws RemoteException ;
    public void inscrireTuteur(ITuteur t) throws RemoteException ;
    public void inscrireEtudiant(IEtudiant e) throws RemoteException ;
    public List<ITuteur> getTuteursParMatiere(String m) throws RemoteException;
    public List<ISeance> getSeancesLibre(List<ITuteur> c) throws RemoteException;
    public ITuteur login(String email,String mdp) throws RemoteException;
    public IEtudiant loginEtudiant(String email,String mdp) throws RemoteException ;    
    public Map<String,ITuteur> getTuteurs() throws RemoteException;
    public Map<String,IEtudiant> getEtudiant()throws RemoteException ;
    public void ajouterListeAttente (IEtudiant e, ITuteur t) throws RemoteException ;
}
