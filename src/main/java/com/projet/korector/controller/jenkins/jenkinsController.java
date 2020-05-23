package com.projet.korector.controller.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.*;
import com.projet.korector.jenkins.Jenkins;
import com.projet.korector.jenkins.JenkinsService;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.SessionRepository;
import com.projet.korector.services.CriteriaService;
import com.projet.korector.services.SessionService;
import com.projet.korector.services.SonarResultsService;
import com.projet.korector.sonarqube.SonarQube;
import com.projet.korector.sonarqube.SonarQubeImpl;
import com.projet.korector.util.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URI;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeoutException;
import static com.projet.korector.jenkins.constants.*;

@RestController
@RequestMapping(value = "/api/jenkins")

public class jenkinsController {


    @Autowired
    private JenkinsService jenkinsService;


    @Autowired
    private SessionService service;


    @Autowired
    private CriteriaService criteriaService;

    private SessionController sessController;
    @Autowired
    private SonarResultsController sonarController;


    @GetMapping("/all")
    @RequestMapping(value = "/getOutput/{jobName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String isBuilding(@PathVariable("jobName") String jobName){
        // return jenkinsService.waitForBuild(jobName,numBuildExpected,timeOut,false);
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        return   jenkinsService.getOutPut(jobName);

    }


    @GetMapping("/all")
    @RequestMapping(value = "/waitBuild/{jobName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean waitForBuildToComplete(@PathVariable ("jobName") String jobName ) throws InterruptedException, TimeoutException, IOException {
          // return jenkinsService.waitForBuild(jobName,numBuildExpected,timeOut,false);
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        return   jenkinsService.waitForBuildFinish(jobName);

    }


    @GetMapping("/all")
   @RequestMapping(value = "/run/{nomBuild}/{url}/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public boolean run(@PathVariable String nomBuild, @PathVariable String url,@PathVariable("sessionId") Long sessionId,@PathVariable("projectId") Long projectId)
   {
       Long seuilCritere;
     //  RunSonar runSonar= new RunSonar(sessionId);
       Map<String,String> sonarBuild= this.build(nomBuild,url,true);
       Set<SessionCritere> sessionsCriteria = service.getSessionCriteres(sessionId);
       Set <SessionCritere>  dynamicCriteria = new HashSet<>();
           double noteBug = 0, noteVul = 0, noteDebt = 0, noteSmell = 0, noteCoverage = 0, noteDuplications = 0, noteBlock = 0, statsNote = 0, dynamicNote = 0, finaleNote = 0;
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           System.out.println("Lancement de l'analyse");
           if (sonarBuild.containsKey("ERROR")) {
               RunSonar newRun = new RunSonar(sessionId);
               newRun.setErrorSonnar(true);
               return false;

               }


           for (SessionCritere criteriaId : sessionsCriteria) {
               System.out.println("Criteria id " + criteriaId.getId() + " Name " + criteriaId.getName() + criteriaId.getType());
               /***************** D'abord on recupere les criteres dynamiques ****************/

               if (criteriaId.getType().equalsIgnoreCase("Dynamique")) {


                   dynamicNote += ((criteriaId.getValue() * criteriaId.getHeight() /100) );

               }


               /****************  Ensuite on recupere les criteres statiques ****************/
               if (criteriaId.getName().equalsIgnoreCase("nombre de bugs")) {
                   System.out.println("Criteria id stats nbBugs : Seuil " + criteriaId.getSeuil() + "Poids : " + criteriaId.getHeight());

                   if (criteriaId.getSeuil() == 0L) {
                       seuilCritere = 20L;
                   } else {
                       seuilCritere = criteriaId.getSeuil();

                   }
                   noteBug = Double.parseDouble(sonarBuild.get("nombre de bugs")) >= seuilCritere ? 0 : 1 - Double.parseDouble(sonarBuild.get("nombre de bugs")) / seuilCritere;
                   statsNote +=  (20 * noteBug )* (( double) criteriaId.getHeight()/100.0) ;


                   //RunSonar.setNote(noteBug);
               }
               if (criteriaId.getName().equalsIgnoreCase("vulnérabilités")) {


                   if (criteriaId.getSeuil() == 0L) {
                       seuilCritere = 50L;
                   } else {
                       seuilCritere = criteriaId.getSeuil();

                   }

                   Double vul =Double.parseDouble(sonarBuild.get("vulnérabilités")) >= seuilCritere ? 0 : 1 - Double.parseDouble(sonarBuild.get("vulnérabilités")) / seuilCritere ;

                   statsNote +=   20 * vul * (( double) criteriaId.getHeight() /100.0) ;
                             }
               if (criteriaId.getName().equalsIgnoreCase("debt")) {

                   if (criteriaId.getSeuil() == 0L) {
                       seuilCritere = 480L;
                   } else {
                       seuilCritere = criteriaId.getSeuil();

                   }
                   noteDebt = Double.parseDouble(sonarBuild.get("debt")) >= seuilCritere ? 0 : 1 - Double.parseDouble(sonarBuild.get("debt")) / seuilCritere;
                   statsNote += ( 20 * noteDebt)*  ( ( double)criteriaId.getHeight()/100.0);

               }
               if (criteriaId.getName().equalsIgnoreCase("code smells")) {

                   if (criteriaId.getSeuil() == 0L) {
                       seuilCritere = 500L;
                   } else {
                       seuilCritere = criteriaId.getSeuil();

                   }

                   noteSmell = Double.parseDouble(sonarBuild.get("code smells")) >= seuilCritere ? 0 : 1 - Double.parseDouble(sonarBuild.get("code smells")) / seuilCritere;
                   statsNote += ( ( 20 * noteSmell) * (( double)criteriaId.getHeight()/100) );

               }


               if (criteriaId.getName().equalsIgnoreCase("duplications")) {
                   noteDuplications = (100 - Double.parseDouble(sonarBuild.get("duplications"))) / 100;
                   statsNote +=  ( 20 * noteDuplications)  * ( ( double) criteriaId.getHeight() / 100);


                   //  newAnalyse.setNoteDuplication(noteDuplications);
               }
               if (criteriaId.getName().equalsIgnoreCase("blocs dupliqués")) {

                   if (criteriaId.getSeuil() == 0L) {
                       seuilCritere = 100L;
                   } else {
                       seuilCritere = criteriaId.getSeuil();

                   }
                   noteBlock = Double.parseDouble(sonarBuild.get("blocs dupliqués")) >= seuilCritere ? 0 : 1 - Double.parseDouble(sonarBuild.get("blocs dupliqués")) / seuilCritere;

                   statsNote += ( 20 * noteBlock ) * ( ( double)criteriaId.getHeight()/ 100);
               }

           }


           finaleNote = (statsNote + dynamicNote);
           SonarResults sonarResults = new SonarResults(sonarBuild.get("nombre de bugs"),
                   sonarBuild.get("vulnérabilités"), sonarBuild.get("debt"),
                   sonarBuild.get("code smells"), sonarBuild.get("duplications"),
                   sonarBuild.get("blocs dupliqués"), projectId, sessionId,finaleNote, now);

           sonarController.saveResults(sonarResults);
            System.out.println("Stats" + statsNote);

            System.out.println("Final note" + finaleNote);
            return true;

               }


    private String createJob(String name, String url){


        url = url.replace(',', '/');

        String properties = "sonar.projectKey="+ name + "\n" +
                " sonar.projectName="+ name + "\n" +
                " sonar.java.binaries =/";

        XmlReader xmlReader = new XmlReader(url,properties);
        String xmlJob = xmlReader.formatXML();System.out.println("New Xml Job " + xmlJob);
   ;
        String result = jenkinsService.createJob(name, xmlJob,true);
        System.out.println("Result  " + result);

        return result;

    }

    @RequestMapping(value = "/build/{name}/{url}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private Map<String,String>build(@PathVariable String name, @PathVariable String url, boolean boo){
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        boolean isCreation = true;
        String str;
        if(!jenkinsService.isJobExist(name)) {
            String creation = createJob(name,url);
            System.out.println("Job name : " + creation);
            isCreation = false;
            if (creation.equals(name)) {

                System.out.println("La Création du job " + name + " est finie");
                //return str;
            }
            else{
                return Collections.singletonMap("ERROR",creation);
            }
        }

        System.out.println("Lancement du build");
        System.out.println("Build Fini" + jenkinsService.buildJob(name));

        if(!jenkinsService.getResultLasBuild(name).equals("SUCCESS")) {
            str ="error build";


            return Collections.singletonMap("ERROR", jenkinsService.getOutPut(name));
        } else {
            SonarQube sonarQube = new SonarQubeImpl();
            System.out.println("Sonarqube metrics" + sonarQube.getMetrics(name));

           return sonarQube.getMetrics(name);
        }
    }


}