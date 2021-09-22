package com.hughes.desginPrinciples.simpleResponsibility.method;

/**
 * @author hughes-T
 * @since 2021/9/22 9:10
 */
public class User {

    private String name;

    private String address;


    public void modUserInfo(String... fields){
        this.name = fields[0];
        this.address = fields[1];
    }

    public void modUserName(String username){
        this.name = username;
    }

    public void modAddress(String address){
        this.address = address;
    }

}
