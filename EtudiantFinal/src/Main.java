import classes.Etudiant;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
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
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
      
    	        launch(args);
     }
    
    
    
    
    
    /***************************************************************************************/
   /***************************************************************************************/
    		@Override
   public void start(Stage primaryStage1) throws Exception {
    	     
    	IPlateforme plateforme;
        plateforme = (IPlateforme) Naming.lookup("plateforme");

        IEtudiant e1 = new Etudiant("et1","et1","et1");
        IEtudiant e2 = new Etudiant("et2","et2","et2");
        
        //inscrire l'etudiant a la plateforme
        plateforme.inscrireEtudiant(e1);
        plateforme.inscrireEtudiant(e2);
        
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
				InscriptionEtudiant( plateforme);
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
					IEtudiant e = plateforme.loginEtudiant(email, mdp);
					if (e==null) {
						resultLabel.setText("Identifiants Erronées");
					}else {
						resultLabel.setText("Connexion en cours");
						primaryStage1.close();
						connectedEtudiant(e,plateforme);
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
    	
    		
    		
    		
    		
    		
    		
    		
    		/***************************************************************************************/
    		 /***************************************************************************************/
    		
    			public void connectedEtudiant(IEtudiant e, IPlateforme p) throws Exception {
    			Stage primaryStage1 = new Stage();
    			Label methodeLabel = new Label("Recherche des seances :");
    		     GridPane.setConstraints(methodeLabel, 0, 0);
    		     GridPane grid = new GridPane();
    		     
	    		 
	    		 ListView<String> listView = new ListView<String>();
	    		 ObservableList<String> ttrs =FXCollections.observableArrayList();
	    		 ObservableList<ISeance> ttres =FXCollections.observableArrayList();
    		    /////////////////////////////////////////////////////////////////////////////////////////////////
    		     //////////////////////////RCHERCHE/////////////////////////////////////////////////////////////
    		     TextField matiereInput = new TextField("Matiere");
    		     GridPane.setConstraints(matiereInput, 0, 1);
    		    
    		     Button rechercheMatiereButton = new Button("Recherche par Matiere");
    		     GridPane.setConstraints(rechercheMatiereButton, 0, 2);
    		     
    		     
    		     rechercheMatiereButton.setOnAction(event->{
    		    	 
    		    	 ObservableList<ITuteur> tuteurs;
					
    		    	 String matiere = matiereInput.getText();
    		     	
    		    	 try {
    		    		 tuteurs = FXCollections.observableArrayList( p.getTuteurs().values());
    		    		 listView.getItems().removeAll(ttrs);
    		    		 refreshMatiere( tuteurs, matiere,   ttres, ttrs);
    		    		 
    		    		 listView.setItems(ttrs);
    		        	 
    		        	// Récupération de la ListView
    		        	
    					
    				} catch (RemoteException e1) {
    					e1.printStackTrace();
    				}
    		         
	    	
	           });
    		     
    		     
    		     
    		     Button seancesButton = new Button("Reserver");
    		     GridPane.setConstraints(seancesButton, 0, 4);
    		     Label results =new Label("");
    		     GridPane.setConstraints(results, 0, 5);
    		    grid.getChildren().add(results);
    		     // Only allowed to select single row in the ListView.
    		        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    		        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

    		            @Override
    		            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    		            	 ISeance maSeance =ttres.get(Integer.valueOf(newValue.toString()));
    		            	
    		            		ITuteur monTiteur;
								try {
									monTiteur = maSeance.getTuteur();
									int index = monTiteur.getSeancesDisponibles().indexOf(maSeance);
									System.out.println("index est "+index);
											System.out.println(ttrs.get(Integer.valueOf(newValue.toString()))!="Reserved");
	    			                
	    			                seancesButton.setOnAction(event->{
	    			                	if(ttrs.get(Integer.valueOf(newValue.toString()))!="Reserved") {
	    			    		    	 String matiere = matiereInput.getText();

	    		    		        	 try {
											e.reserverSeance(monTiteur.getSeancesDisponibles().get(index), matiere);
					    		    		 ttrs.set(Integer.valueOf(newValue.toString()), "Reserved");
					    		    		 
					    		    		 results.setText("");

										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
	    		    		        	 
	    			                	}else {
	    			                		results.setText("Deja Reservé");
	    			                	}
	    		   	        	 });		                
	    			                
	    			               
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
    							
    		            	 
    		            }
    		            
    		            

    		            
    		        });
    		        
    		        GridPane.setConstraints(listView, 0, 3);
		        	 grid.getChildren().add(listView);
    		    
    		    // 
          ////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////Reservation/////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////////////////////
  		     
    	/*	     Button seancesButton = new Button("Reserver");
    		     GridPane.setConstraints(seancesButton, 1, 2);
    		    

	        	 
	    		 ObservableList<ISeance> t=FXCollections.observableArrayList(); 
		    	 ObservableList<String> horairesToreserve =FXCollections.observableArrayList();
   
		    	 for (ITuteur t1 : tuteurs) {
	    			 for (ISeance c : t1.getSeancesDisponibles()) {
				    	 t.add(c);
		    			 horairesToreserve.add(c.gethoraire().getDate()+" de "+c.gethoraire().getHeureDebut()+" a "+c.gethoraire().getHeureFin()+"; Prix :"+ c.getPrice()+ "; TUTEUR : "+c.getTuteur().getNom());

	    			 }

	    		 }
	    		 ChoiceBox<String> choiceBoxR = new ChoiceBox<>(horairesToreserve);
       	         GridPane.setConstraints(choiceBoxR, 1, 1);
    		    
	        	 grid.getChildren().add(choiceBoxR);
	        	 seancesButton.setOnAction(event->{
	        		 ISeance maSeance =t.get(horairesToreserve.indexOf(choiceBoxR.getValue()));
	                	
						try {
							ITuteur monTiteur = maSeance.getTuteur();
							int index = monTiteur.getSeancesDisponibles().indexOf(maSeance);
			                e.reserverSeance(monTiteur.getSeancesDisponibles().get(index), "Espagnol");
			                choiceBoxR.setValue("Reservé");
			                
						
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
	        		
	        	 })*/
	             ////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////Inscription Liste Attente/////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
   	 
    		    Button inscrirNotifButton = new Button("Inscription");
        		GridPane.setConstraints(inscrirNotifButton, 2, 2);
        		ObservableList<String> nomTuteur =FXCollections.observableArrayList();
        		ObservableList<ITuteur> tuteurs= FXCollections.observableArrayList( p.getTuteurs().values());
	    		 for (ITuteur t1 : tuteurs) {
	    			 
		    			 nomTuteur.add(t1.getNom());

	    		

	    		 }    
	    		 ChoiceBox<String> choiceBox = new ChoiceBox<>(nomTuteur);
       	         GridPane.setConstraints(choiceBox, 2, 1);
       	         Label info= new Label ("Inscrivez vous a une liste d'attente");
       	         GridPane.setConstraints(info, 2, 0);
       	     
                
       	         grid.getChildren().addAll(choiceBox,info);
       	         choiceBox.setOnAction(event->{
       	        	ObservableList<ITuteur> mestuteurs;
					try {
						mestuteurs = FXCollections.observableArrayList( p.getTuteurs().values());
						for (ITuteur t1 : mestuteurs) {
		   	    			
								if (!nomTuteur.contains(t1.getNom()))
								 nomTuteur.add(t1.getNom());
						}	
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
       	        	 choiceBox.setItems(nomTuteur);
       	         });
       	         ////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////MES NOTIFICATIONS/////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
   
        		 ObservableList<String> notifications= FXCollections.observableArrayList( e.getNotifications());
	        	 ListView<String> listView3 = new ListView<String>(notifications);
	        	 GridPane.setConstraints(listView3, 2, 3);
	        	 grid.getChildren().addAll(inscrirNotifButton, listView3);
	        	 Button seancesReservesButton = new Button("Rafraichir La page");
    		     GridPane.setConstraints(seancesReservesButton , 3, 4);
 
		        	 seancesReservesButton.setOnAction(event->{
		        		
						try {
							ObservableList<ITuteur> mestuteurs;
		 					
		 						mestuteurs = FXCollections.observableArrayList( p.getTuteurs().values());
		 						for (ITuteur t1 : mestuteurs) {
		 		   	    			
		 								if (!nomTuteur.contains(t1.getNom()))
		 								 nomTuteur.add(t1.getNom());
		 						}	
		 				
		        	        	 choiceBox.setItems(nomTuteur);
			        		 ObservableList<String> notifs;
							notifs = FXCollections.observableArrayList( e.getNotifications());
							listView3.setItems(notifs);
						
							 ObservableList<String> horairesSeancesReserved =FXCollections.observableArrayList();

							for (ISeance c : e.getMesSeances()) {
								 horairesSeancesReserved.add(c.gethoraire().getDate()+" de "+c.gethoraire().getHeureDebut()+" a "+c.gethoraire().getHeureFin()+"; Prix :"+ c.getPrice()+ "; TUTEUR : "+c.getTuteur().getNom());
								 ListView<String> listView4 = new ListView<String>(horairesSeancesReserved);
					        	 GridPane.setConstraints(listView4, 3, 3);
					        	 grid.getChildren().add(listView4);
							 }
							
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        	 

		    		 
		        	 });
		        	 
	        	 inscrirNotifButton.setOnAction(event ->{
	        		 
	 	    		 
	        		 if(choiceBox.getValue()!=null) {
	        		
	        			 try {
	        				 ObservableList<String> nomTuteurB =FXCollections.observableArrayList();
	     	         		ObservableList<ITuteur> tuteursB;
	     					
	     						tuteursB = FXCollections.observableArrayList( p.getTuteurs().values());
	     						for (ITuteur t1 : tuteursB) {
	     		 	    			 
	     	 		    			 nomTuteurB.add(t1.getNom());
	     						}
	        				 System.out.println(choiceBox.getValue());
	        				 System.out.println(nomTuteurB.get(0));
	        				 System.out.println(nomTuteurB.get(0)==choiceBox.getValue());
	        				 System.out.println(nomTuteurB.indexOf(choiceBox.getValue()));
	        				 System.out.println(tuteursB.get(nomTuteurB.indexOf(choiceBox.getValue())) );
							p.ajouterListeAttente(e,tuteursB.get(nomTuteurB.indexOf(choiceBox.getValue())) );
							listView3.refresh();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        		 }
	        		 
	        	 });
	             ////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////Reserved/////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

	        	
		        	
		             ////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		        	
	        	 grid.getChildren().add(seancesReservesButton);
    		    grid.setPadding(new Insets(10, 10, 10, 10));
    		    grid.setVgap(8);
    		    grid.setHgap(10);
    	     grid.getChildren().addAll(
    		     		methodeLabel,
    		     		matiereInput,rechercheMatiereButton,
    		     		seancesButton
    		     		
    		      );
    		      
    		     Scene scene = new Scene(grid, 900, 600);
    		     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel : Etudiant Connecté "+e.getNom());
    		     primaryStage1.setScene(scene);
    		     primaryStage1.show();
    		}
    		
    	/**************************************************************************************************/
    		
    			
        		
        		/***************************************************************************************/
        		/***************************************************************************************/
        	
        	    public void InscriptionEtudiant(IPlateforme p) throws Exception {
        	    	Stage primaryStage1 = new Stage();
        	    	Label methodeLabel = new Label("Inscription :");
        	    	
        	    	
        	    	GridPane grid = new GridPane();
        	    	
        	    	Label nomLabel = new Label("Veuillez saisir votre nom:");
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
        		   
        		     
        		     Button inscriptionButton = new Button("Inscription");
        			 GridPane.setConstraints(inscriptionButton, 1, 5);
        	    	inscriptionButton.setOnAction(event->{
        	    		String nom = nomInput.getText();
        	    		String email = emailInput.getText();
        		         String mdp = mdpInput.getText();
        		         try {
        		        	 if (nom!="" && mdp!="" && email !="") {
        		        		 IEtudiant e1 = new Etudiant(nom,email,mdp);
         						p.inscrireEtudiant(e1);
         						 PauseTransition pause = new PauseTransition(Duration.seconds(3));
         			     			ProgressIndicator progressIndicator = new ProgressIndicator();
         	  			        grid.add(progressIndicator, 1, 6);
         			     			pause.setOnFinished(e -> {
         			     			    Platform.runLater(() -> {
         			     			       primaryStage1.close();
         			     			      try {
         									p.loginEtudiant(email, mdp);
         									connectedEtudiant(e1, p);
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
        		        	 }else {
        		        		 
        		        	 }
    						
    						
    					} catch (RemoteException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (Exception e2) {
    						// TODO Auto-generated catch block
    						e2.printStackTrace();
    					}
        		        
        		        
        	    	});
        	    	
        	    	
        	    	
        	    	
        		     GridPane.setConstraints(methodeLabel, 0, 0);
        		    grid.setPadding(new Insets(10, 10, 10, 10));
        		    grid.setVgap(8);
        		    grid.setHgap(10);
        	     grid.getChildren().addAll(
        		     		methodeLabel,
        		     		emailLabel, emailInput,
        		     		mdpLabel,mdpInput,
        		     		nomLabel,nomInput,
        		     		inscriptionButton
        		     		
        		      );
        		      
        		     Scene scene = new Scene(grid, 900, 600);
        		     primaryStage1.setTitle("Plateforme Interne Gustave Eiffel : Iscription Etudiant");
        		     primaryStage1.setScene(scene);
        		     primaryStage1.show();
        			
        		}

    		
    		   		
    		
    		
    		public void refreshMatiere(List<ITuteur> tuteurs, String matiere,  List<ISeance> ttres, List<String>ttrs) throws RemoteException {
    			for (ITuteur t1: tuteurs) {
	    			 if (t1.getDomainesExpertise().contains(matiere)) {
	    				 for (ISeance c : t1.getSeancesDisponibles()) {
	    					
	    						 ttres.add(c);
				    			 ttrs.add("TUTEUR : "+c.getTuteur().getNom()+" .Le "+c.gethoraire().getDate()+
				    					 " De "+c.gethoraire().getHeureDebut()+
				    					 " A "+c.gethoraire().getHeureFin()+
				    					 ". Prix : "+ c.getPrice()
				    					 );
	    					 			    	 
			    					 

		    			 }
	    			 }
	    		 }
    			
    		}
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		 
}
