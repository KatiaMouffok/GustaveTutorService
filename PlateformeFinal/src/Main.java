
import classes.Plateforme;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
        menu();
        LocateRegistry.createRegistry(PORT);
        Plateforme plateforme = new Plateforme();
        Naming.rebind("plateforme", plateforme);
        
        String[] boundNames = Naming.list("rmi://localhost/");
        for (String name : boundNames) {
            System.out.println("Bound name: " + name);
        }
        	;
		Label methodeLabel = new Label("Connexion :");
	     GridPane.setConstraints(methodeLabel, 0, 0);
	     
	     Label emailLabel = new Label("Veuillez saisir votre email:");
	     GridPane.setConstraints(emailLabel, 0, 1);
	     
	     TextField emailInput = new TextField();
	     GridPane.setConstraints(emailInput, 1, 1);
	     
	     Label mdpLabel = new Label("Veuillez saisir votre mot de passe:");
	     GridPane.setConstraints(mdpLabel, 0, 2);
	     
	     TextField mdpInput = new TextField();
	     GridPane.setConstraints(mdpInput, 1, 2);
	     
	     
	     Label typeLabel = new Label("Vous etes :");
	     GridPane.setConstraints(typeLabel, 0, 3);
	     ObservableList<String> choices = FXCollections.observableArrayList("Etudiant", "Tuteur");
	     ChoiceBox<String> choiceBox = new ChoiceBox<>(choices);
	     GridPane.setConstraints(choiceBox, 1, 3);
	     Button connexionButton = new Button("Connexion");
		 GridPane.setConstraints(connexionButton, 1, 4);
		 
		 Label pasdecompteLabel = new Label("Vous n'avez pas de compte :");
	     GridPane.setConstraints(pasdecompteLabel, 0, 5);
		 Button inscriptionButton = new Button("Inscription");
		 GridPane.setConstraints(inscriptionButton, 1, 5);
		 
		 Label resultLabel = new Label("");
	     GridPane.setConstraints(resultLabel, 1,6);
		 
		 connexionButton.setOnAction(event->{
			 String email = emailInput.getText();
	         String mdp = mdpInput.getText();
	        
			 String selectedOption =choiceBox.getValue();
			 if (selectedOption=="Etudiant") {
		    		
					try {
					IEtudiant e = plateforme.loginEtudiant(email, mdp);
					if (e==null) {
						resultLabel.setText("Identifiants Erronées");
					}else {
						resultLabel.setText("Connexion en cours");
						primaryStage1.close();
						connectedEtudiant(e);
					}
					
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
	    	}else if (selectedOption=="Tuteur") {
	    		try {
					ITuteur t = plateforme.login(email, mdp);
					if (t==null) {
						resultLabel.setText("Identifiants Erronées");
					}else {
						resultLabel.setText("Connexion en cours");
						primaryStage1.close();
						connectedTuteur( t);
					}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		    });
		 
	     GridPane grid = new GridPane();
		    grid.setPadding(new Insets(10, 10, 10, 10));
		    grid.setVgap(8);
		    grid.setHgap(10);
	     grid.getChildren().addAll(
		     		methodeLabel,
		     		emailLabel, emailInput,
		     		mdpLabel,mdpInput,
		     		typeLabel,choiceBox,
		     		connexionButton, 
		     		pasdecompteLabel,inscriptionButton,
		     		resultLabel
		      );
		      
		     Scene scene = new Scene(grid, 800, 400);
		     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
		     primaryStage1.setScene(scene);
		     primaryStage1.show();
	}
	/***************************************************************************************/
	/***************************************************************************************/
	public void connectedEtudiant(IEtudiant e) throws Exception {
		Stage primaryStage1 = new Stage();
		Label methodeLabel = new Label("Connected Student:");
	     GridPane.setConstraints(methodeLabel, 0, 0);
	     
	     
	     
	     
	     
		GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);
     grid.getChildren().addAll(
	     		methodeLabel
	      );
	      
	     Scene scene = new Scene(grid, 800, 400);
	     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
	}
	
	/***************************************************************************************/
	/***************************************************************************************/
	
    public void connectedTuteur(ITuteur t) throws Exception {
    	Stage primaryStage1 = new Stage();
    	Label methodeLabel = new Label("Connected Tutor :");
	     GridPane.setConstraints(methodeLabel, 0, 0);
    	GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);
     grid.getChildren().addAll(
	     		methodeLabel
	      );
	      
	     Scene scene = new Scene(grid, 800, 400);
	     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
		
	}
    /***************************************************************************************/
    /***************************************************************************************/
    public void inscriptionTuteur() throws Exception {
    	Stage primaryStage1 = new Stage();
    	Label methodeLabel = new Label("Connexion :");
	     GridPane.setConstraints(methodeLabel, 0, 0);
    	GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);
     grid.getChildren().addAll(
	     		methodeLabel
	      );
	      
	     Scene scene = new Scene(grid, 800, 400);
	     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
		
	}
    /***************************************************************************************/
    /***************************************************************************************/
    public void InscriptionEtudiant() throws Exception {
    	Stage primaryStage1 = new Stage();
    	Label methodeLabel = new Label("Connexion :");
	     GridPane.setConstraints(methodeLabel, 0, 0);
    	GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);
     grid.getChildren().addAll(
	     		methodeLabel
	      );
	      
	     Scene scene = new Scene(grid, 800, 400);
	     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
		
	}
    public void menu()throws Exception{
    	Stage stage = new Stage();
    	MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu rechercheMenu = new Menu("Recherche Tuteur");
        Menu meetingMenu = new Menu("Prise de rendez-vous");
        
       
        // Create MenuItems
        MenuItem matiereItem = new MenuItem("Recherche par Matiére");
        MenuItem nomItem = new MenuItem("Recherche par nom");
        MenuItem prixItem = new MenuItem("Recherche par prix");
       
        
        MenuItem newRItem = new MenuItem("Prise de nouveau rendez-vous");
        MenuItem oldRItem = new MenuItem("Mes rendez-vous");
       
        
        // Add menuItems to the Menus
        rechercheMenu.getItems().addAll(nomItem, matiereItem, prixItem);
        meetingMenu.getItems().addAll(newRItem, oldRItem);
       
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(rechercheMenu, meetingMenu);
       
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root, 350, 200);
       
        stage.setTitle("Compte Etudiant");
        stage.setScene(scene);
        stage.show();
    }
	
	
	
} 