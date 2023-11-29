package shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Horaire extends UnicastRemoteObject implements IHoraire, Serializable {
    private String date;
    private int debut;
    private int fin;
    private boolean disp;
    
    public Horaire() throws RemoteException{
    	
    }

    public Horaire( String d, int start,int end) throws RemoteException {
        this.date = d;
        this.debut = start;
        this.fin = end;
      
        
    }


    public String getDate() {
        return date;
    }

    public int getHeureDebut() {
        return debut;
    }



    public int getHeureFin() {
        return fin;
    }


    public void setIndisponible(){
        this.disp = false;
    }
    public void setDisponible(){
        this.disp = true;
    }


    public boolean estDisponible(){
        return this.disp;
    }
    public String toString() {
        return date + " de " + debut + "h"  + " à " + fin + "h" ;
    }
}
