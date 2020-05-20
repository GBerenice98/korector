package com.projet.korector.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
/*Implémentation des fonctionnalités de Jenkins permettant la manipulation de Job pour compiler des projets*/

@Service
public class Jenkins implements JenkinsService {
    private String login;
    private String password;
    private String url;
    private JenkinsServer jenkinsServer;

    private Map<String,Job> listJob = new HashMap<>();

    public Jenkins(){

    }

    //Constructeur
    public Jenkins(String login, String password, String url)  {
        this.login = login;
        this.password = password;
        this.url = url;
        try {
            this.jenkinsServer = new JenkinsServer(new URI(url), login, password);
            System.out.println("CONNECTED");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Retourne la liste des jobs contenus dans le serveur
    public Map<String,Job> getListJob(){
        listJob.clear();
        try {
           listJob.putAll(jenkinsServer.getJobs());
           System.out.println(listJob.size() + " jobs: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listJob;
    }
    //Vérifie si un job existe
    @Override
    public boolean isJobExist(String name) {
        return this.getListJob().keySet().stream().anyMatch(k -> k.equals(name));
    }
    //Récupère le xml d'un job existant
    public String getJobXml(String name){
        String xml= null;
        try {
            return jenkinsServer.getJobXml(name);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    //Lance le build d'un Job
    public String buildJob(String name, boolean isCreation){
        JobWithDetails job = null;
        String result = null;
        try {
            job = jenkinsServer.getJob(name);
           System.out.println("Job name in buildJob" + job.getDisplayName());
            System.out.println("Job name is buildable" + job.isBuildable());
            job.build(true);
            //job.build();
            System.out.println("Job build avant");

            if(this.waitForBuildToComplete(600000,name,getLastBuildNumber(name) + 1,isCreation))
                return this.getResultLasBuild(name);
            else
                return "Time OUT";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    //Création d'un job
    public String createJob(String name, String xml,boolean boo){

        String result = null;
        try {
        if(isJobExist(name)){
            System.out.println("Job" + name +"existe");

            deleteJob(name);
            System.out.println("Job" + name +"supprime");

        }
        else {
            System.out.println("New job creation");
            jenkinsServer.createJob(name,xml,boo);

        }

            return jenkinsServer.getJob(name).getDisplayName();
        } catch (IOException e) {
             return e.getMessage();
        }

    }
    //Suppresion d'un job
    @Override
    public String deleteJob(String name) {
        JobWithDetails job = null;
        try{
            job = jenkinsServer.getJob(name);
        }catch(Exception e){
            return e.getMessage();
        }
        if(job !=null){
            try {
                jenkinsServer.deleteJob(name, false);
                return name;
            } catch(IOException e) {
                return e.getMessage();
            }
        }
        return "job n'existe pas";
    }
    //Retourne le résultat du dernier build
    public String getResultLasBuild(String jobName){

        JobWithDetails job = null;
        try {
            job = jenkinsServer.getJob(jobName);
            BuildWithDetails build = job.getLastBuild().details();
            return build.getResult().name();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    //Retourne l'output du dernier build
    public String getOutPut(String jobName) {
        JobWithDetails job = null;
        try {
            job = jenkinsServer.getJob(jobName);
            BuildWithDetails build = job.getLastBuild().details();

            return build.getConsoleOutputHtml();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    //patiente jusqu'à la fin d'un build
    public boolean waitForBuildToComplete( long timeOut, String jobName,int numBuildExpected,boolean isCreation) throws InterruptedException, TimeoutException, IOException {
        JobWithDetails wrkJobData = jenkinsServer.getJob(jobName);
        boolean buildCompleted = false;
        Long timeoutCounter = 0L;
        while (!buildCompleted) {
            Thread.sleep(10000);
            timeoutCounter = timeoutCounter + 5000L;
            /*if (timeoutCounter > timeOut) {
                throw new TimeoutException("The job did not complete in the expected time");
            }*/
            //When the build is in the queue, the nextbuild number didn't change.
            //When it changed, It might still be running.

            //System.out.println("TAILLE DE LISTBUILD " + wrkJobData.getAllBuilds().size());
            int numLastBuild;
            if(isCreation)
                 numLastBuild = 0;
            else
                 numLastBuild =  wrkJobData.getAllBuilds().get(0).getNumber();
            System.out.println("New Next Nbr:" + numBuildExpected + " vs " + numLastBuild);

            if (numLastBuild == numBuildExpected) {
                boolean isBuilding = wrkJobData.getAllBuilds().get(0).details().isBuilding();
                //System.out.println("is Build : " + isBuilding);
                if (!isBuilding) {
                    buildCompleted = true;
                }
            }
        }
        return buildCompleted;
    }
    // Retourne le numéro du dernier build
    @Override
    public int getLastBuildNumber(String jobName) {
        JobWithDetails job;
        try {
            job = jenkinsServer.getJob(jobName);
            return job.getLastBuild().getNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
