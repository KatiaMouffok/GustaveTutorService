import classes.Tuteur;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import shared.Horaire;
import shared.IEtudiant;
import shared.IHoraire;
import shared.IPlateforme;
import shared.ISeance;
import shared.ITuteur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
	 public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
	      
	        launch(args);
}
/***************************************************************************************/
/***************************************************************************************/
		@Override
public void start(Stage primaryStage1) throws Exception {
	        IPlateforme plateform;
	        plateform = (IPlateforme) Naming.lookup("plateforme");
	       
	             if( plateform.getTuteurs().isEmpty()) {
	        //creer les domaines d'experience du tuteur
	        List<String> domaines = new ArrayList<String>();
	        domaines.add("Math");
	       
	        //creation et inscription de tuteur1 dans la plateforme
	        ITuteur t1 = new Tuteur("tuteur1","t1@gm.com","massi1999",domaines);
	        plateform.inscrireTuteur(t1);

	        //creation et inscription de tuteur2 dans la plateforme
	        List<String> domaines2 = new ArrayList<String>();
domaines2.add("Espagnol");
	        ITuteur t2 = new Tuteur("tuteur2","t2@gm.com","massi1999",domaines2);
	        
	        plateform.inscrireTuteur(t2);

	        //creation des horaires de disponibilités
	        IHoraire h1 = new Horaire("17/06/2024",10,14);
	        IHoraire h2 = new Horaire("18/06/2024",10,14);
	       
	       
	        ISeance c =plateform.programmerSeance();
	        
	        t2.addToListMesSeancesTutorat(c, 20,h2);
	       
	             }
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
		     
		     
		     Button connexionButton = new Button("Connexion");
			 GridPane.setConstraints(connexionButton, 1, 4);
			 
			 Label pasdecompteLabel = new Label("Vous n'avez pas de compte :");
		     GridPane.setConstraints(pasdecompteLabel, 0, 5);
			 Button inscriptionButton = new Button("Inscription");
			 GridPane.setConstraints(inscriptionButton, 1, 5);
			 
			 Label resultLabel = new Label("");
		     GridPane.setConstraints(resultLabel, 1,6);
		     
		     
			 inscriptionButton.setOnAction(event ->{
				 try {
					 InscriptionTuteur(plateform);
					primaryStage1.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 });
			 
			 
			 connexionButton.setOnAction(event->{
				 String email = emailInput.getText();
		         String mdp = mdpInput.getText();
		        
				 
			    		
						try {
						ITuteur e = plateform.login(email, mdp);
						if (e==null) {
							resultLabel.setText("Identifiants Erronées");
						}else {
							resultLabel.setText("Connexion en cours");
							primaryStage1.close();
							connectedTuteur(e,plateform);
						}
						
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
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
			     		connexionButton, 
			     		pasdecompteLabel,inscriptionButton,
			     		resultLabel
			      );
			      
			     Scene scene = new Scene(grid, 900, 600);
			     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
			     primaryStage1.setScene(scene);
			     primaryStage1.show();

	      


	    }
	 /***************************************************************************************/
		/***************************************************************************************/
		
	    @SuppressWarnings("unlikely-arg-type")
		public void connectedTuteur(ITuteur t, IPlateforme p) throws Exception {
	    	Stage primaryStage1 = new Stage();
	    	Label methodeLabel = new Label("Tuteur :"+t.getNom());
		     GridPane.setConstraints(methodeLabel, 0, 0);
	    	
	     
	     
	     
	     TextField dEInput = new TextField("Domaine D'expertise");
	     GridPane.setConstraints(dEInput, 0, 1);
	     Button dEButton = new Button("Ajouter Un domaine d'expertise");
	     dEButton.setOnAction(e->{
	    	 String domaine=dEInput.getText();
	    	 try {
	    		 p.getTuteurs().get(t.getEmail()).addDomaineExpertise(domaine);


			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	     	 
	     });
		 
	     
	     TextField dateInput = new TextField("Date DD/MM/YY");
	     GridPane.setConstraints(dateInput, 0, 2);
	     TextField debutInput = new TextField("Heure HH");
	     GridPane.setConstraints(debutInput, 1, 2);
	     TextField finInput = new TextField("Heure HH");
	     GridPane.setConstraints(finInput, 2, 2);
	     TextField priceInput = new TextField("Price");
	     GridPane.setConstraints(priceInput, 3, 2);
	     Button horaireButton = new Button("Ajouter Une Seance de disponibilité :");
	     GridPane.setConstraints(horaireButton, 4, 2);
	     Label resultLabel = new Label("");
	     GridPane.setConstraints(resultLabel, 1, 3);
	     horaireButton.setOnAction(e->{
	    	 String date=dateInput.getText();
	         int d = Integer.valueOf(debutInput.getText());
	         int f = Integer.valueOf(finInput.getText());
	         long price = Long.valueOf(priceInput.getText());
	    	 try {
	    		ISeance c =p.programmerSeance();
	    		IHoraire h= (IHoraire) new Horaire(date,d,f);
	    		p.getTuteurs().get(t.getEmail()).addToListMesSeancesTutorat(c, price ,h);
				
				resultLabel.setText(p.getTuteurs().get(t.getEmail()).getSeancesDisponibles().get(t.getSeancesDisponibles().size()-1).gethoraire().getDate());
				
	    	 } catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	     	 
	     });
	     GridPane.setConstraints(dEButton, 1, 1);
		 GridPane grid = new GridPane();
		    grid.setPadding(new Insets(10, 10, 10, 10));
		    grid.setVgap(8);
		    grid.setHgap(10);
	     Button expertButton = new Button("Afficher La liste de mes domaines d'expertise :");
	     GridPane.setConstraints(expertButton, 0, 4);
	     expertButton.setOnAction(event->{
	    	
	    	 try {
	    		 ObservableList<String> domaines= FXCollections.observableArrayList( p.getTuteurs().get(t.getEmail()).getDomainesExpertise());
	        	 ListView<String> listView = new ListView<String>(domaines);
	        	 GridPane.setConstraints(listView, 0, 5);
	        	 grid.getChildren().add(listView);
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	    	
	     });
	     Button seancesButton = new Button("Afficher La liste de mes seances disponnible :");
	     GridPane.setConstraints(seancesButton, 1, 4);
	   
	     seancesButton.setOnAction(event->{
	    	
	    	 try {
	    		 ObservableList<ISeance> seances= FXCollections.observableArrayList( p.getTuteurs().get(t.getEmail()).getSeancesDisponibles());
	    		 ObservableList<String> horaires =FXCollections.observableArrayList();
	    		 for (ISeance c : seances) {
	    			 horaires.add(c.gethoraire().getDate()+" de "+c.gethoraire().getHeureDebut()+" a "+c.gethoraire().getHeureFin()+" . Prix :"+ c.getPrice());
	    		 }
	        	 ListView<String> listView = new ListView<String>(horaires);
	        	 GridPane.setConstraints(listView, 1, 5);
	        	 grid.getChildren().add(listView);
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	    	
	     });
	     Button seancesIButton = new Button("Afficher La liste de mes seances indisponnible :");
	     GridPane.setConstraints(seancesIButton, 2, 4);
	     seancesIButton.setOnAction(event->{
	     	
	    	 try {
	    		 ObservableList<ISeance> seances= FXCollections.observableArrayList( p.getTuteurs().get(t.getEmail()).getMesSeancesTutorat());
	    		 ObservableList<String> horaires =FXCollections.observableArrayList();
	    		 for (ISeance c : seances) {
	    			 if(c.getEtudiant()!=null)
	    			 horaires.add(c.gethoraire().getDate()+" de "+c.gethoraire().getHeureDebut()+" a "+c.gethoraire().getHeureFin()+" . Prix :"+ c.getPrice());
	    		 }
	        	 ListView<String> listView = new ListView<String>(horaires);
	        	 GridPane.setConstraints(listView, 2, 5);
	        	 grid.getChildren().add(listView);
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	    	
	     });
	     Button attentesButton = new Button("Afficher La liste d'attente :");
	     GridPane.setConstraints(attentesButton, 3, 4);
	     attentesButton.setOnAction(event->{
	     	
	    	 try {
	    		 ObservableList<IEtudiant> etudiants= FXCollections.observableArrayList( p.getTuteurs().get(t.getEmail()).getListeAttente());
	    		 ObservableList<String> etds =FXCollections.observableArrayList();
	    		 for (IEtudiant c : etudiants) {
	    			 
	    			 etds.add(c.getNom());
	    		 }
	        	 ListView<String> listView = new ListView<String>(etds);
	        	 GridPane.setConstraints(listView, 3, 5);
	        	 grid.getChildren().add(listView);
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	    	
	     });
	     
	  grid.getChildren().addAll(
		     		methodeLabel,
		     		dEInput,dEButton, 
		     		dateInput, debutInput,finInput,priceInput,
		     		horaireButton,
		     		resultLabel,
		     		expertButton,seancesButton,seancesIButton,attentesButton
		      );
		      
		     Scene scene = new Scene(grid, 900, 600);
		     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
		     primaryStage1.setScene(scene);
		     primaryStage1.show();
			
		}
   
	    /***************************************************************************************/
	    /***************************************************************************************/
	    
		public void InscriptionTuteur(IPlateforme p) throws Exception {
	    	Stage primaryStage1 = new Stage();
	    	Label methodeLabel = new Label("Inscription :");
		     GridPane.setConstraints(methodeLabel, 0, 0);
	    	GridPane grid = new GridPane();
	    	
	    	Label nomLabel = new Label("Veuillez saisir votre email:");
		     GridPane.setConstraints(nomLabel, 0, 1);
		     
		     TextField nomInput = new TextField();
		     GridPane.setConstraints(nomInput, 1, 1);
	    	
	    	Label emailLabel = new Label("Veuillez saisir votre email:");
		     GridPane.setConstraints(emailLabel, 0, 2);
		     
		     TextField emailInput = new TextField();
		     GridPane.setConstraints(emailInput, 1, 2);
		     
		     Label mdpLabel = new Label("Veuillez saisir votre mot de passe:");
		     GridPane.setConstraints(mdpLabel, 0, 3);
		     
		     TextField mdpInput = new TextField();
		     GridPane.setConstraints(mdpInput, 1, 3);
		     
		     Label domaineLabel = new Label("Veuillez saisir vos domaines d'expertise (Separé par une virgule):");
		     GridPane.setConstraints(domaineLabel, 0, 4);
		     
		     TextField domaineInput = new TextField();
		     GridPane.setConstraints(domaineInput, 1, 4);
		     
		     Button inscriptionButton = new Button("Inscription");
			 GridPane.setConstraints(inscriptionButton, 1, 5);
	    	inscriptionButton.setOnAction(event->{
	    		String nom = nomInput.getText();
	    		String email = emailInput.getText();
		         String mdp = mdpInput.getText();
		         String domaine = domaineInput.getText();
		         ArrayList listdomaines = new ArrayList(Arrays.asList(domaine.split(",")));
		         try {
						ITuteur t1 = new Tuteur(nom,email,mdp,listdomaines);
						p.inscrireTuteur(t1);
						 PauseTransition pause = new PauseTransition(Duration.seconds(3));
			     			ProgressIndicator progressIndicator = new ProgressIndicator();
	  			        grid.add(progressIndicator, 1, 6);
			     			pause.setOnFinished(e -> {
			     			    Platform.runLater(() -> {
			     			       primaryStage1.close();
			     			      try {
									p.login(email, mdp);
									connectedTuteur(t1, p);
								} catch (RemoteException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
									
			     			    });
			     			});
			     		pause.play();
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
 		        
	    	});
	    	
	    	
	    	
		    grid.setPadding(new Insets(10, 10, 10, 10));
		    grid.setVgap(8);
		    grid.setHgap(10);
	     grid.getChildren().addAll(
		     		methodeLabel,
		     		nomLabel,nomInput,
		     		mdpLabel,mdpInput,
		     		emailLabel,emailInput,
		     		domaineLabel,domaineInput,
		     		inscriptionButton
		      );
		      
		     Scene scene = new Scene(grid, 900, 600);
		     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel");
		     primaryStage1.setScene(scene);
		     primaryStage1.show();
			
		}
	
}