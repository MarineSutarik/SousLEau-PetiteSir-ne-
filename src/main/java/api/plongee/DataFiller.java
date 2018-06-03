/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.plongee;

import api.plongee.cours.domain.Cours;
import api.plongee.cours.domain.GeoPoint2D;
import api.plongee.cours.domain.Piscine;
import api.plongee.membre.enumeration.TypeMembre;
import api.plongee.membre.domain.Membre;
import api.plongee.cours.service.GestionCours;
import api.plongee.membre.service.GestionMembre;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pour remplir la BD
 * @author marin
 */
@Component
public class DataFiller implements CommandLineRunner{

     @Autowired
     GestionMembre gestionMembre;
     
     @Autowired
     GestionCours gestionCours;
     
     @Override
     @Transactional
    public void run(String... strings) throws Exception {
         Membre ma = gestionMembre.creerMembre("RIGAL", "Anais", "thuglife@gourgandine.fr", "thug", "life", null,null, 1, "564654AD54", "France", "Sulpice", TypeMembre.President);
         Membre me =  gestionMembre.creerMembre("TOURNIE", "Vivien", "viv@gourgandine.fr", "viv", "life", null,null, 1, "564654uAD54", "France", "Toulouse", TypeMembre.Membre);
        Membre m =  gestionMembre.creerMembre("SUTARIK", "Marine", "marine@gourgandine.fr", "mar", "life", null,null, 1, "564u654AD54", "Slovaquie", "Nowhere", TypeMembre.Secretaire);
        gestionMembre.payerCotisation("pihjp", 30, m.getIdMembre());
        gestionMembre.donnerCertificat(m.getIdMembre());
        m = gestionMembre.creerMembre("Z", "Gilles", "z@gil.fr", "gil", "concepts", null,null, 1, "564654uAD54", "France", "Perché", TypeMembre.Enseignant);
        
       List<Piscine> l = gestionCours.recupererPiscines();
        Cours c = gestionCours.creerCours("Vidage de masque", 1, Calendar.getInstance().getTime(), 40, m.getIdMembre(),l.get(1).getId());
        gestionCours.participerCours(c.getIdCours(), ma.getIdMembre());
        gestionCours.participerCours(c.getIdCours(), me.getIdMembre());
        

	
    }
}
