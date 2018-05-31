/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.cours.service;

import api.plongee.cours.service.GestionCours;
import api.plongee.cours.domain.Cours;
import api.plongee.cours.domain.Creneau;
import api.plongee.membre.domain.Membre;
import api.plongee.cours.domain.Participant;
import api.plongee.cours.exception.CoursIntrouvableException;
import api.plongee.cours.exception.CoursTropRemplisException;
import api.plongee.membre.exception.MembreIntrouvableException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.plongee.cours.repository.CreneauRepository;
import api.plongee.cours.repository.CoursRepo;
import api.plongee.cours.repository.ParticipantRepo;
import api.plongee.membre.service.GestionMembre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marine
 */
@Service 
public class GestionCoursImpl implements GestionCours{

    @Autowired
    CoursRepo coursRepo;
    
    @Autowired
    CreneauRepository creneauRepo;
    
    @Autowired
    GestionMembre gestionMembre;
    
    @Autowired
    ParticipantRepo participantRepo;
    
    /**
     *
     * @param nomCours
     * @param niveauCible
     * @param dateDebut
     * @param duree
     * @param enseignant
     * @return
     */
    @Override
    public Cours creerCours(String nomCours, Integer niveauCible, Date dateDebut, Integer duree,  Integer enseignant) {
        Creneau creneau = new Creneau(dateDebut, duree);
        creneau=creneauRepo.save(creneau);
        Cours c = new Cours (nomCours, niveauCible,creneau, enseignant, new Participant[4] );
        Cours insert = coursRepo.save(c);
        return insert;
    }

    @Override
    public Cours participerCours(String idCours, Integer idMembre) throws MembreIntrouvableException,CoursIntrouvableException, CoursTropRemplisException {
       Membre m = gestionMembre.afficherMembre(idMembre);
       Cours c = coursRepo.findOne(idCours);
       if (c==null) throw new CoursIntrouvableException();
       Participant p = new Participant(m.getIdMembre(), m.getNom(), m.getPrenom());
       c.addParticipant(p);
       coursRepo.save(c);
       participantRepo.save(p);
       return c;
    }

    @Override
    public List<Cours> afficherCours(Integer idMembre) throws MembreIntrouvableException, CoursIntrouvableException {
        Participant p = participantRepo.findOne(idMembre);
        List<Cours> c = coursRepo.findAllByParticipants(participantRepo.findOne(idMembre));
        if(c==null)throw new CoursIntrouvableException();
        return c;
    }
    
}
