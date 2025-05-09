package com.workintech.s17d2.model;

public class JuniorDeveloper extends Developer {

    public JuniorDeveloper(Integer id, String name, double salary) {
        super(id, name, salary);
        setExperience(Experience.JUNIOR);
    }

    public JuniorDeveloper() {
        super();
        setExperience(Experience.JUNIOR);
    }
}
