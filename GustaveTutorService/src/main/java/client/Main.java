package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

import FxtopAPI.ConvertResult;
import FxtopAPI.FxtopServicesLocator;
import FxtopAPI.FxtopServicesPortType;
import banque.Banque;
import banque.BanqueServiceLocator;
import banque.BanqueSoapBindingStub;
import client.Etudiant;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import shared.IEtudiant;
import shared.IPlateforme;
import shared.ISeance;
import shared.ITuteur;

public class Main extends Application{
	

	public static void main(String[] args) {
        launch(args);
    }
	public void start (Stage primaryStage2) throws RemoteException, ServiceException, MalformedURLException, NotBoundException {
		//firstView();
		IPlateforme plateforme;
        plateforme = (IPlateforme) Naming.lookup("plateforme");
		// TODO Auto-generated method stub
				Stage primaryStage1 = new Stage();
				primaryStage1.setTitle("Plateforme GustaveTutorService");
							    
			     GridPane grid = new GridPane();
				    grid.setPadding(new Insets(10, 10, 10, 10));
				    grid.setVgap(8);
				    grid.setHgap(10); // 10 is the spacing between elements
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
			        grid.getChildren().addAll(
			        		methodeLabel,
				     		emailLabel, emailInput,
				     		mdpLabel,mdpInput,
				     		connexionButton, 
				     		pasdecompteLabel,inscriptionButton,
				     		resultLabel
				      );
			        // Create a scene with the layout
			        Scene scene = new Scene(grid, 800, 200);

			        // Set the scene and show the stage
			        primaryStage1.setScene(scene);
			        primaryStage1.show();
		
	}
	public void firstView(IEtudiant etu,ISeance seance, String matiere,Number newVlue,ObservableList<String>tts) throws ServiceException, RemoteException{
		Stage primaryStage1 = new Stage();
		primaryStage1.initStyle(StageStyle.UTILITY);
		primaryStage1.setTitle("Methode de Paiement");
		Label methodeLabel = new Label("Veuillez choisir le mode de paiement :");
	     GridPane.setConstraints(methodeLabel, 0, 0);
        // Create a list of choices
        ObservableList<String> choices = FXCollections.observableArrayList("Virement", "Carte Bancaire");

        // Create a ChoiceBox and set its items
        ChoiceBox<String> choiceBox = new ChoiceBox<>(choices);
        GridPane.setConstraints(choiceBox, 1, 0);
        // Handle ChoiceBox events
      
        Button selectButton = new Button("Valider");
	    GridPane.setConstraints(selectButton, 1, 2);
	    selectButton.setOnAction(event->{
	    	String selectedOption =choiceBox.getValue();
	    	if (selectedOption=="Virement") {
	    		try {
					thirdView(etu,seance,matiere,newVlue,tts);
					
				} catch (RemoteException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
	    		try {
					secondView(etu,seance,matiere,newVlue,tts);
				} catch (RemoteException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    });
        // Create a layout and add the ChoiceBox to it
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10); // 10 is the spacing between elements
        
        grid.getChildren().addAll(
        		methodeLabel,choiceBox,
        		selectButton
	      );
        // Create a scene with the layout
        Scene scene = new Scene(grid, 300, 200);

        // Set the scene and show the stage
        primaryStage1.setScene(scene);
        primaryStage1.show();
	}
	
	public void secondView(IEtudiant etu,ISeance s, String matiere,Number newValue,ObservableList<String>ttrs) throws ServiceException, RemoteException  {
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);

	     Label deviseLabel = new Label("Veuillez saisir la devise:");
	     GridPane.setConstraints(deviseLabel, 0, 0);
	     TextField deviseInput = new TextField();
	     GridPane.setConstraints(deviseInput, 1, 0);

	     Label cardNumberLabel = new Label("Card Number:");
	     GridPane.setConstraints(cardNumberLabel, 0, 1);
	     TextField cardNumberInput = new TextField();
	     GridPane.setConstraints(cardNumberInput, 1, 1);

	     Label expirationDateLabel = new Label("Expiration Date:");
	     GridPane.setConstraints(expirationDateLabel, 0, 2);
	     DatePicker expirationDatePicker = new DatePicker(LocalDate.of(2020, 1, 1)); // Custom converter for MM/YY format
	     GridPane.setConstraints(expirationDatePicker, 1, 2);
	     
	     Label cvvLabel = new Label("CVV:");
	     GridPane.setConstraints(cvvLabel, 0, 3);
	     TextField cvvInput = new TextField();
	     GridPane.setConstraints(cvvInput, 1, 3);
	     
	     Label holderLabel = new Label("Nom du propriétaire:");
	     GridPane.setConstraints(holderLabel, 0, 4);
	     TextField holderInput = new TextField();
	     GridPane.setConstraints(holderInput, 1, 4);

	     Button convertButton = new Button("Convertir et Payer");
	     GridPane.setConstraints(convertButton, 1, 5);
	     
	     Label resultLabel = new Label();
	     GridPane.setConstraints(resultLabel, 1, 6);
	     Label resultLabel2 = new Label();
	     GridPane.setConstraints(resultLabel2, 1, 7);
	     
	     
	     Banque maBanque= new BanqueServiceLocator().getBanque();
  		((BanqueSoapBindingStub) maBanque).setMaintainSession(true);
  		FxtopServicesPortType serviceFx = new FxtopServicesLocator().getFxtopServicesPort();
  		
	     convertButton.setOnAction(e -> {
	    	 String devise = deviseInput.getText();
	         String cardNumber = cardNumberInput.getText();
	         String expirationDateS = expirationDatePicker.getValue().toString().substring(2,7).replace('-', '/');
	         String cvv = cvvInput.getText();
	         String holderName = holderInput.getText();
	         
	    	 try {
		    	
		        	 
		     		double r=1;
		     		
		     		ConvertResult res = serviceFx.convert("", "EUR", devise, "", "", "");
    			    ConvertResult res1 = serviceFx.convert("", "EUR", maBanque.getDevise(holderName), "", "", "");

		     		double montant = s.getPrice();
		     		double doubleRes = montant  * Double.parseDouble(res.getExchangeRate());
		     		double doubleRes1 = montant  * Double.parseDouble(res1.getExchangeRate());
		     		
		    		if(maBanque.effectuerPaiement(cardNumber, expirationDateS,Integer.parseInt(cvv), holderName , doubleRes1)) {
		    				resultLabel.setText("Succés de Paiement"); 
		    				resultLabel2.setText("Votre compte à été débité de "+ doubleRes+" "+devise);
		    				maBanque.recevoirPaiement(s.getTuteur().getNom(), s.getPrice());

			     			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			     			ProgressIndicator progressIndicator = new ProgressIndicator();
	     			        grid.add(progressIndicator, 1, 8);
			     			pause.setOnFinished(event -> {
			     			    Platform.runLater(() -> {
			     			        try {
										etu.reserverSeance(s,matiere);
				    		    		 ttrs.set(Integer.valueOf(newValue.toString()), "Reserved");

									} catch (RemoteException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			     			        // Fermer la fenêtre après l'attente
			     			        primaryStage.close();
			     			    });
			     			});
			     			pause.play();
		    	    }else {
		    	    	resultLabel.setText("Paiement échoué");
		    	    	
	    				resultLabel2.setText("Coordonnées fausses ou Compte innéxistant ou Solde Insuffisant");

		    	    	
		    	    }
		    			 
		    			
		     
		    }
		    	
	         catch (RemoteException e1) {
	    		    e1.printStackTrace();
	    		  
	    		} 
	    	 
	     });
	     
	     
	     
	     grid.getChildren().addAll(
	     		deviseLabel, deviseInput,
	      		cardNumberLabel,cardNumberInput,
	      		holderLabel,holderInput,
	      		cvvLabel, cvvInput,
	      		expirationDateLabel,expirationDatePicker,
	             convertButton,
	             resultLabel,
	             resultLabel2
	      );
	      
	     Scene scene = new Scene(grid, 800, 400);
	     primaryStage.setTitle("Web Banque");
	     primaryStage.setScene(scene);
	     primaryStage.show();

    
 }
	public void thirdView(IEtudiant etu,ISeance s, String matiere,Number newValue,ObservableList<String>ttrs) throws ServiceException, RemoteException  {
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UTILITY);
		GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(8);
	    grid.setHgap(10);

	     Label deviseLabel = new Label("Veuillez saisir la devise:");
	     GridPane.setConstraints(deviseLabel, 0, 0);
	     TextField deviseInput = new TextField();
	     GridPane.setConstraints(deviseInput, 1, 0);

	     Label holderLabel = new Label("Nom du propriétaire:");
	     GridPane.setConstraints(holderLabel, 0, 1);
	     TextField holderInput = new TextField();
	     GridPane.setConstraints(holderInput, 1, 1);

	     Button convertButton = new Button("Convertir et Payer");
	     GridPane.setConstraints(convertButton, 1, 2);
	     
	     Label resultLabel = new Label();
	     GridPane.setConstraints(resultLabel, 1, 3);
	     Label resultLabel2 = new Label();
	     GridPane.setConstraints(resultLabel2, 1, 4);
	     
	     
	     Banque maBanque= new BanqueServiceLocator().getBanque();
  		((BanqueSoapBindingStub) maBanque).setMaintainSession(true);
  		FxtopServicesPortType serviceFx = new FxtopServicesLocator().getFxtopServicesPort();
  		
	     convertButton.setOnAction(e -> {
	    	 String devise = deviseInput.getText();
	         String holderName = holderInput.getText();
	         
	    	 try {
		    	
		 		     		
		     		ConvertResult res = serviceFx.convert("", "EUR", devise, "", "", "");
	     			ConvertResult res1 = serviceFx.convert("", "EUR", maBanque.getDevise(holderName), "", "", "");
		     		double montant = s.getPrice();		     		
		     		double doubleRes = montant  * Double.parseDouble(res.getExchangeRate());
		     		double doubleRes1 = montant  * Double.parseDouble(res1.getExchangeRate());
		     		
		     		
	     			if(maBanque.virement(holderName,  doubleRes1)) {
	     				maBanque.recevoirPaiement(s.getTuteur().getNom(), s.getPrice());
		     			resultLabel.setText("Succés de Paiement" ); 
		     			resultLabel2.setText("Votre compte à été débité de montant = "+ doubleRes+" "+devise);
		     			
		     			PauseTransition pause = new PauseTransition(Duration.seconds(3));
		     			ProgressIndicator progressIndicator = new ProgressIndicator();
     			        grid.add(progressIndicator, 1, 5);
		     			pause.setOnFinished(event -> {
		     			    Platform.runLater(() -> {
		     			    	try {
									etu.reserverSeance(s,matiere);
			    		    		 ttrs.set(Integer.valueOf(newValue.toString()), "Reserved");

								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

		     			        // Fermer la fenêtre après l'attente
		     			        primaryStage.close();
		     			        
		     			    });
		     			});
		     			pause.play();

	    	    }else {
	    	    	resultLabel.setText("Paiement échoué");
    				resultLabel2.setText("Compte innéxistant ou Solde Insuffisant");

	    	    	
	    	    }
		     			    	
		         
	     }
	         catch (RemoteException e1) {
	    		    e1.printStackTrace();
	    		  
	    		} 
	    	 
	     });
	     
	     
	     
	     grid.getChildren().addAll(
	     		deviseLabel, deviseInput,
	      		holderLabel,holderInput,
	            convertButton,
	            resultLabel,
	            resultLabel2
	      );
	      
	     Scene scene = new Scene(grid, 800, 300);
	     primaryStage.setTitle("Web Banque");
	     primaryStage.setScene(scene);
	     primaryStage.show();

    
 }
	
	
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
	     primaryStage1.setTitle("Plateforme GustaveyTutorService : Iscription Etudiant");
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
		
	}
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
		    		        		 firstView(e,monTiteur.getSeancesDisponibles().get(index), matiere,newValue,ttrs);
			    		    		 
			    		    		 results.setText("");

								} catch (RemoteException | ServiceException e1) {
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
	     primaryStage1.setTitle("Plateforme GustaveyTutorService : Etudiant Connecté "+e.getNom());
	     primaryStage1.setScene(scene);
	     primaryStage1.show();
	}
	
/**************************************************************************************************/
	
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
