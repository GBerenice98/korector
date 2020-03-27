package com.projet.korector.modelDTO;

public class SessionImp {

    private String name;
    //private List<Project> listProjects;
    //private User user;


    public SessionImp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SessionImp{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
