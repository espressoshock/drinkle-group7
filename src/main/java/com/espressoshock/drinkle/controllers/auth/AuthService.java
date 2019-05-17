package com.espressoshock.drinkle.controllers.auth;

import com.espressoshock.drinkle.appState.AppStatePersistence;
import com.espressoshock.drinkle.appState.Current;
import com.espressoshock.drinkle.databaseLayer.ConnectionLayer;
import com.espressoshock.drinkle.models.Account;
import com.espressoshock.drinkle.models.BusinessAccount;
import com.espressoshock.drinkle.models.PrivateAccount;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService {



  private Connection connection = null;
  private Statement statement = null;
  private ResultSet resultSet = null;


  boolean loginAsPrivateAccount(String email, String password) {
    try {
      connection = ConnectionLayer.getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM drinkleg7.private_account");

      while (resultSet.next()) {

        int id = resultSet.getInt("id");
        String nameFromDB = resultSet.getString("name");
        String emailFromDB = resultSet.getString("email");
        String passwordFromDB = resultSet.getString("password");

        if (email.equals(emailFromDB) && password.equals(passwordFromDB)) {
          PrivateAccount newAccount = new PrivateAccount(emailFromDB,passwordFromDB,null,null);
          
          Current
              .environment
              .currentUser = newAccount;

          System.out.printf("id: %d, name:%s, email: %s\n", id, nameFromDB, emailFromDB);
          System.out.println(Current.environment.currentUser.toString());
          return true;
        } else {
          return false;
        }

      }
    } catch (SQLException ex) {
      System.out.println("Login exception: ");
      System.out.println(ex);
    } finally {
      ConnectionLayer.cleanUp(statement, resultSet);
    }
    return false;
  }











  static BusinessAccount loginAsCompanyAccount() {
    return null;
  }



  static void registerAsPrivateAccount(PrivateAccount account) {
    System.out.println("Create private acc");
    AppStatePersistence.saveObject(account, AppStatePersistence.userFilename);
  }

  static void registerAsCompanyAccount(BusinessAccount account) {
    System.out.println("Create company acc");
    AppStatePersistence.saveObject(account, AppStatePersistence.userFilename);
  }

}
