/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.membre.repo;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author marin
 */
public class MembreRepoImpl implements MembreRepoCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public String getNombreCotisationsPrevues() {
     String query = "select count(idMembre) from Membre where year(aPaye)  <> "+Calendar.getInstance().get(Calendar.YEAR)+"";
        String r = "";
        r = this.entityManager
                .createQuery(query)
                .getFirstResult()+"";
        return r;
    }

    @Override
    public String getNombreCotisationsRegles() {
        String query = "select count(idMembre) from Membre where year(aPaye) = "+Calendar.getInstance().get(Calendar.YEAR)+"";
        String r = "";
        r = this.entityManager
                .createQuery(query)
                .getFirstResult()+"";
        return r;  
    }
    
}
