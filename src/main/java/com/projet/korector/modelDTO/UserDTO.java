package com.projet.korector.modelDTO;
import javax.persistence.*;



//@ApiModel(value = "User Model")


public class UserDTO implements Comparable<UserDTO> {


        private Long id;
        private String firstname;
        private String email;
    private String lastName;

    private String userName;
        //private String email;
        private String mdp;
        private String token;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public UserDTO() {
    }


    public UserDTO(String userName , String nom, String prenom,String email, String mdp) {
            this.id = id;
            this.lastName = nom;
            this.userName = prenom;
            this.mdp = mdp;

        }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getMdp() {
            return mdp;
        }


        public void setMdp(String mdp) {
            this.mdp = mdp;
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("id=").append(id);
            sb.append(", name='").append(lastName).append('\'');
            sb.append('}');
            return sb.toString();
        }

    @Override
    public int compareTo(UserDTO o) {
        return this.userName.compareToIgnoreCase(o.getUserName());
    }

}




