package com.espressoshock.drinkle.daoLayer;

import com.espressoshock.drinkle.daoLayer.entities.AccountDAL;
import com.espressoshock.drinkle.daoLayer.entities.JPAAAccountDal;
import com.espressoshock.drinkle.daoLayer.entities.JPAAccountDao;
import com.espressoshock.drinkle.models.Account;
import com.espressoshock.drinkle.models.BusinessAccount;
import com.espressoshock.drinkle.models.Person;
import com.espressoshock.drinkle.models.PrivateAccount;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.MessageDigest;
import javax.xml.transform.Result;

public class JPADaoManager {
    private static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("drinklePU");
    private JPAAAccountDal accountDao;

    public JPADaoManager() {
    }

    public Account login(Account account){
        System.out.println(account.getEmail()+","+ account.getPassword());
        this.accountDao = new JPAAAccountDal(this.EMF.createEntityManager());
        AccountDAL result = this.accountDao.getByKey(account.getEmail());
        /********* ACCOUNT MATCH  */
        if(result != null && compareMD5(account.getPassword(), result.getPassword()))
            if(account instanceof BusinessAccount)
                return new BusinessAccount(result.getEmail(), result.getPassword(), result.getPictureURL(), null, null); //->add decorators
            else if(account instanceof PrivateAccount) //more accounts?
                return new PrivateAccount(result.getEmail(), result.getPassword(), result.getPictureURL(), null, null); //->add decorators


        return null;
    }

    public boolean registerAccount(Account account) {
        System.out.println(account.getEmail()+","+ account.getPassword());



        return true;
    }

    private boolean compareMD5(String toEncode, String encoded){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncode.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<byteData.length;i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            System.out.println(sb.toString());

            if(encoded.equals(sb.toString()))
                return true;

        }catch (Exception e){e.printStackTrace();}

        return false;
    }
}
