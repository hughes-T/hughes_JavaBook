package com.hughes.desginPrinciples.simpleResponsibility.interfaces;

/**
 * @author hughes-T
 * @since 2021/9/22 9:33
 */
public class UserDaoImpl implements IUserQueryDao, IUserWriteDao {

    public String qryUserName() {
        return "张三";
    }

    public String qryUserAddress() {
        return "南京";
    }

    public void modUserName() {
    }

    public void modUserAddress() {
    }
}
