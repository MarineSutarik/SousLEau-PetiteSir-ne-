/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.cours.service;

import api.plongee.cours.domain.Cours;
import api.plongee.cours.exception.CoursIntrouvableException;
import api.plongee.cours.exception.CoursTropRemplisException;
import api.plongee.membre.exception.MembreIntrouvableException;
import java.util.Date;

/**
 *
 * @author Marine
 */
public interface GestionCours {
    
    public Cours creerCours(String nomCours, Integer niveauCible, Date dateDebut, Integer duree, Integer enseignant);
    
    public Cours participerCours(String idCours, Integer idMembre) throws MembreIntrouvableException,CoursIntrouvableException, CoursTropRemplisException;
    
}
