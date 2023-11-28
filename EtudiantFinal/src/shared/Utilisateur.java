package shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Utilisateur extends UnicastRemoteObject implements IUtilisateur, Serializable {
	private String nom;
	private String email;
	private String password;
	private String type;
	public Utilisateur(String n,String e,String p, String t) throws RemoteException {
		this.nom = n;
		this.email = e;
		this.password = p;
		this.type=t;
	}
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return this.nom;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public void setNom(String n) {
		// TODO Auto-generated method stub
		 this.nom=n;
	}
	@Override
	public void setEMail(String e) {
		// TODO Auto-generated method stub
		 this.email=e;
	}
	@Override
	public void setPassword(String p) {
		// TODO Auto-generated method stub
		 this.password=p;
	}
	public void setType(String s) {
		this.type=s;
	}
	public String getType() {
		return this.type;
	}

}
