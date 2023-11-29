
import classes.Plateforme;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Seance;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import shared.Horaire;
import shared.IEtudiant;
import shared.IHoraire;
import shared.ISeance;
import shared.ITuteur;
public class Main extends Application{
	private static final int PORT=1099;
	
    public static void main(String[] args) throws RemoteException, MalformedURLException {
    
        launch(args);
    }
    /***************************************************************************************/
    /***************************************************************************************/
	@Override
	public void start(Stage primaryStage1) throws Exception {
        
        LocateRegistry.createRegistry(PORT);
        Plateforme plateforme = new Plateforme();
        Naming.rebind("plateforme", plateforme);
        
        String[] boundNames = Naming.list("rmi://localhost/");
        for (String name : boundNames) {
            System.out.println("Bound name: " + name);
        }
        	;
		
	}
	
	
} 