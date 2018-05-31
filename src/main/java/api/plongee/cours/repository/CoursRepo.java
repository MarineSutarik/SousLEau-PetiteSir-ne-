/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.cours.repository;

import api.plongee.cours.domain.Cours;
import api.plongee.cours.domain.Participant;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marine
 */

public interface CoursRepo  extends MongoRepository<Cours, String>,CoursRepoCustom{
    
    /**
     *
     * @param pidMembre
     * @return
     */
    @Query("{\"participants.idMembre\":?0}")
    public List<Cours> findAllByParticipants(Integer pidMembre);
    
}
