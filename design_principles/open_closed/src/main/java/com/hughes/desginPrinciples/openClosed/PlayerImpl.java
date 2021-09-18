package com.hughes.desginPrinciples.openClosed;

/**
 * @author hughes-T
 * @since 2021/8/4  10:29
 */
public class PlayerImpl implements Player {

    private String name;

    private int experience;


    public PlayerImpl(String name) {
        this.experience = 0;
        this.name = name;
    }

    @Override
    public void addExperience(int experience) {
        this.experience = this.experience + experience;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public String getName() {
        return name;
    }

}
