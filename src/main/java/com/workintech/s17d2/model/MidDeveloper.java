package com.workintech.s17d2.model;

public class MidDeveloper extends Developer {

    public MidDeveloper(Integer id, String name, double salary) {
        super(id, name, salary);
        setExperience(Experience.MID);
    }

    public MidDeveloper() {
        super();
        setExperience(Experience.MID);
    }
}