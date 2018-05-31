/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.cours.repository;

import api.plongee.cours.domain.Cours;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marine
 */
public class CoursRepoImpl implements CoursRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Cours> findAllByParticipantsIdMembre(Integer pidMembre) {
       String query = "nomCours:'vidage de masque'";
        List<Cours> r =  null;
        r = this.entityManager
                .createQuery(query).getResultList();
            
        return r;  
    
    }
    
}
