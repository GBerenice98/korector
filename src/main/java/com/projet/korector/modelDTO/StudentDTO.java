package com.projet.korector.modelDTO;



public class StudentDTO extends UserDTO {
    private String  github ;


    private String id_Classe;


    public StudentDTO(String userName,String firstName,String lastName, String email, String password, String github, String id_Classe)  {
        super (userName, firstName,lastName,email,password);

        this.github = github;
        this.id_Classe = id_Classe;

    }

    public String getGithub() {
        return github;
    }


    public String getId_Classe() {
        return id_Classe;
    }



    public void setGithub(String github) {
        this.github = github;
    }



    public void setId_Classe(String id_Classe) {
        id_Classe = id_Classe;
    }

}
