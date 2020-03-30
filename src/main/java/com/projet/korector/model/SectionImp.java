package com.projet.korector.model;

public class SectionImp {
    private long id;
    private String name;
    //private Set<TeacherImp> teachers;
    //private Set<StudentImp> students;



    public SectionImp(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SectionImp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
