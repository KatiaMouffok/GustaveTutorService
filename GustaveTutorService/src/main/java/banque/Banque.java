/**
 * Banque.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package banque;

public interface Banque extends java.rmi.Remote {
    public boolean effectuerPaiement(java.lang.String numeroCarte, java.lang.String dateExpiration, int codeVerification, java.lang.String nomTitulaire, double montant) throws java.rmi.RemoteException;
    public java.lang.String getDevise(java.lang.String identifiant) throws java.rmi.RemoteException;
    public void recevoirPaiement(java.lang.String nomDesti, double montant) throws java.rmi.RemoteException;
    public boolean virement(java.lang.String nomTitulaire, double montant) throws java.rmi.RemoteException;
}
