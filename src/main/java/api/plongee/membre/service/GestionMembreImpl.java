/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.membre.service;

import api.plongee.membre.domain.Adresse;
import api.plongee.membre.domain.Enseignant;
import api.plongee.membre.domain.Membre;
import api.plongee.membre.domain.President;
import api.plongee.membre.domain.Secretaire;
import api.plongee.membre.enumeration.TypeMembre;
import static api.plongee.membre.enumeration.TypeMembre.*;
import api.plongee.membre.domain.Paiement;
import api.plongee.membre.exception.MembreIntrouvableException;
import api.plongee.membre.repo.AdresseRepo;
import api.plongee.membre.repo.EnseignantRepo;
import api.plongee.membre.repo.MembreRepo;
import api.plongee.membre.repo.PaiementRepo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marine
 */
@Service 
public class GestionMembreImpl  implements GestionMembre{
    @Autowired
    private MembreRepo membreRepo;
    
    @Autowired
    private AdresseRepo adresse;
    
    @Autowired
    private PaiementRepo paiement;
    
    @Autowired
    private EnseignantRepo enseignant;
    
    
    @Override
    public Membre creerMembre(String nom, String prenom, String adresseMail, String login, String password, Date dateDebutCertificat, Integer niveauExpertise, String numLicence, String pays, String ville, TypeMembre type) {
      Adresse a = new Adresse( pays, ville);
        a = adresse.save(a);
         Membre m = null;
        switch (type){
            case Membre :
                 m = new Membre(nom, prenom, adresseMail, login,password, null, null,  niveauExpertise, numLicence, a);
                 break;
            case Secretaire :
                 m = new Secretaire(nom, prenom, adresseMail, login,password, null, null,  niveauExpertise, numLicence, a);
                 break;
            case President :
                m = new President(nom, prenom, adresseMail, login,password, null, null,  niveauExpertise, numLicence, a);
                 break;
            case Enseignant :
                 m = new Enseignant(nom, prenom, adresseMail, login,password, null, null,  niveauExpertise, numLicence, a);
                 break;
                 
        }
        m  = membreRepo.save(m);
        
        return m;
    }

    @Override
    public Membre updateMembre(Integer idMembre, Membre m)  throws MembreIntrouvableException {
        System.out.println("id = "+idMembre);
        Membre membreActuel = this.membreRepo.getOne(idMembre);
        if (membreActuel==null) throw new MembreIntrouvableException();
        
        membreActuel.setAdresse(m.getAdresse());
        membreActuel.setAdresseMail(m.getAdresseMail());
        membreActuel.setDateDebutCertificat(m.getDateDebutCertificat());
        membreActuel.setLogin(m.getLogin());
        membreActuel.setNiveauExpertise(m.getNiveauExpertise());
        membreActuel.setNom(m.getNom());
        membreActuel.setNumLicence(m.getNumLicence());
        membreActuel.setPassword(m.getPassword());
        membreActuel.setPrenom(m.getPrenom());
        membreActuel.setaPaye(m.getAPaye()); 
        return this.membreRepo.save(membreActuel);
    }

    @Override
    public void deleteMembre(Integer idMembre) throws MembreIntrouvableException {
        Membre membreActuel = this.membreRepo.getOne(idMembre);
        if (membreActuel==null) throw new MembreIntrouvableException();
        else this.membreRepo.delete(membreActuel);
    }

    @Override
    public Membre seconnecter(String login, String password) throws MembreIntrouvableException {
        Membre m =  membreRepo.findMembreByLogin(login);
        if (!m.getPassword().equals(password.trim()) || m ==null )
            throw new MembreIntrouvableException();
        
        return m;
    }

    @Override
    public void payerCotisation(String IBAN, float somme,Integer idMembre) throws MembreIntrouvableException {
         Membre m = this.membreRepo.getOne(idMembre);
        if (m==null) throw new MembreIntrouvableException();
        Paiement p = new Paiement(IBAN,somme, m.getIdMembre());
        paiement.save(p);
       
        m.setaPaye(Calendar.getInstance().getTime());
        membreRepo.save(m);
    }

    @Override
    public List<Membre> consulterCotisation() {
        ArrayList<Membre> r = new   ArrayList<Membre> ();
        for( Membre m : membreRepo.findAll()){
            r.add(m);
        }
        return r;
    }

    @Override
    public Map<String, String> consulterStatistiques() {
        HashMap<String,String> h = new HashMap<String,String>();
    
   //nombre de membre
    String k = "nombre de membre";
    String v = membreRepo.count()+" membres";
    h.put(k, v);
    
    //nombre d'enseignant
     k = "nombre d'enseignant";
     v = enseignant.count()+" enseignants";
    h.put(k, v);
    
    
    //nombre de cotisation prévue
     k = "nombre  de cotisation prévue";
     v =membreRepo.getNombreCotisationsPrevues()+" cotisations";
    h.put(k, v);
    
    
    //nombre de cotisation réglées 
     k = "nombre de cotisation réglées";
     v =membreRepo.getNombreCotisationsRegles()+" cotisations";
    h.put(k, v);
    
    return h;
        
    }
    
    @Override
    public void donnerCertificat(Integer idMembre) throws MembreIntrouvableException{
        Membre m = membreRepo.getOne(idMembre);
        m.setDateDebutCertificat(Calendar.getInstance().getTime());
        membreRepo.save(m);
    }

    @Override
    public Membre afficherMembre(Integer idMembre ) throws MembreIntrouvableException{
        Membre m =  this.membreRepo.getOne(idMembre);
        if (m==null) throw new MembreIntrouvableException();
        return m;
    }

  
    
}
