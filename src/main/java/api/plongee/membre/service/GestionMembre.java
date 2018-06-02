/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.membre.service;

import api.plongee.membre.domain.Membre;
import api.plongee.membre.enumeration.TypeMembre;
import api.plongee.membre.exception.MembreIntrouvableException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marine
 */
public interface GestionMembre {
   /**
     * Correspond au DSS s'inscrire
     * @param nom
     * @param prenom
     * @param adresseMail
     * @param login
     * @param password
     * @param dateDebutCertificat
     * @param niveauExpertise
     * @param numLicence
     * @param type
     * @param pays
     * @param ville
     * @return 
     */
    public Membre creerMembre ( String nom, String prenom, String adresseMail, String login, String password, Date dateDebutCertificat, Date aPaye,  Integer niveauExpertise, String numLicence, String pays, String ville, TypeMembre type);
   
    
    public Membre updateMembre(Integer idMembre, Membre m ) throws MembreIntrouvableException;
    
    public void deleteMembre(Integer idMembre) throws MembreIntrouvableException;
    
    public Membre seconnecter(String login, String password) throws MembreIntrouvableException;
    
    public void payerCotisation(String IBAN, float somme, Integer idMembre) throws MembreIntrouvableException;
    
    public List<Membre> consulterCotisation();
    
    public Map<String, String> consulterStatistiques() ;
    
    public void donnerCertificat(Integer idMembre) throws MembreIntrouvableException;
    
    public Membre afficherMembre(Integer idMembre) throws MembreIntrouvableException;
    
}
