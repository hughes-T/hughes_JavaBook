package com.hughes.desginPrinciples.openClosed;

/**
 * @author hughes-T
 * @since 2021/8/4  10:43
 */
public class ActivePlayer extends PlayerImpl{

    public ActivePlayer(String name) {
        super(name);
    }

    public void addActiveExperience(int experience) {
        super.addExperience(experience * 2);
    }
}
