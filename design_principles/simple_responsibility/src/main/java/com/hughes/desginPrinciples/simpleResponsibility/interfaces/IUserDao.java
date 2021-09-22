package com.hughes.desginPrinciples.simpleResponsibility.interfaces;

/**
 * @author hughes-T
 * @since 2021/9/22 9:35
 */
public interface IUserDao {

    String qryUserName();

    String qryUserAddress();

    void modUserName();

    void modUserAddress();
}
