package com.hughes.desginPrinciples.openClosed;

import lombok.ToString;

/**
 * @author hughes-T
 * @since 2021/8/4  10:29
 */
@ToString
public class PlayerImpl implements Player {

    private final String name;
    private int experience;

    public PlayerImpl(String name) {
        this.experience = 0;
        this.name = name;
    }

    @Override
    public void addExperience(int experience) {
        this.experience = this.experience + experience;
    }

}
