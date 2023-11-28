import classes.Plateforme;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import classes.Seance;
import shared.Horaire;
public class Main {
    private static final int PORT=1099;
    public static void main(String[] args) throws RemoteException, MalformedURLException {

        LocateRegistry.createRegistry(PORT);
        Plateforme plateforme = new Plateforme();
        Naming.rebind("plateforme", plateforme);
        Seance seance = new Seance();
        Naming.rebind("seance", seance);
        
        String[] boundNames = Naming.list("rmi://localhost/");
        for (String name : boundNames) {
            System.out.println("Bound name: " + name);
        }
    }
}