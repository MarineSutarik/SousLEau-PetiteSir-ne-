/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee.membre.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author allan
 */
@Entity
@Table(name="Adresse")
public class Adresse implements Serializable {
     
    @Id
    @GeneratedValue
    private Integer idAdresse;
    private String pays;
    private String ville;

    protected Adresse() {
    }
     public Adresse(Integer idAdresse,String pays, String ville) {
       this.idAdresse = idAdresse;
        this.pays = pays;
        this.ville = ville;
    }
    public Adresse(String pays, String ville) {
       
        this.pays = pays;
        this.ville = ville;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    
}
