package miage.korector.login.models;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "Etudiant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Student extends User {

    @NotBlank
    @Size(max = 50)
    private String  github ;

    @NotBlank
    @Size(max = 50)
    private String id_Classe;

    public Student(String username, String email, String password, String github, String id_Classe) {
        super (username,email,password);

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
