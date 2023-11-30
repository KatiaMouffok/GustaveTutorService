package banque;

public class BanqueProxy implements banque.Banque {
  private String _endpoint = null;
  private banque.Banque banque = null;
  
  public BanqueProxy() {
    _initBanqueProxy();
  }
  
  public BanqueProxy(String endpoint) {
    _endpoint = endpoint;
    _initBanqueProxy();
  }
  
  private void _initBanqueProxy() {
    try {
      banque = (new banque.BanqueServiceLocator()).getBanque();
      if (banque != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)banque)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (banque != null)
      ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public banque.Banque getBanque() {
    if (banque == null)
      _initBanqueProxy();
    return banque;
  }
  
  public boolean effectuerPaiement(java.lang.String numeroCarte, java.lang.String dateExpiration, int codeVerification, java.lang.String nomTitulaire, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.effectuerPaiement(numeroCarte, dateExpiration, codeVerification, nomTitulaire, montant);
  }
  
  public java.lang.String getDevise(java.lang.String identifiant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.getDevise(identifiant);
  }
  
  public void recevoirPaiement(java.lang.String nomDesti, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.recevoirPaiement(nomDesti, montant);
  }
  
  public boolean virement(java.lang.String nomTitulaire, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.virement(nomTitulaire, montant);
  }
  
  
}