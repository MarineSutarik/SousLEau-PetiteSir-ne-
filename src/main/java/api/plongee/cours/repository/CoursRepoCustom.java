/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.cours.repository;

import api.plongee.cours.domain.Cours;
import java.util.List;

/**
 *
 * @author Marine
 */
public interface CoursRepoCustom {
    public List<Cours> findAllByParticipantsIdMembre(Integer pidMembre);
}
