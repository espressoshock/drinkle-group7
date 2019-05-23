package com.espressoshock.drinkle.controllers.auth;

import com.espressoshock.drinkle.daoLayer.JPADaoManager;
import com.espressoshock.drinkle.models.PrivateAccount;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import com.espressoshock.drinkle.viewLoader.ViewLoader;
import com.espressoshock.drinkle.viewLoader.ViewMetadata;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.concurrent.*;

public class AuthLogin extends EventDispatcherAdapter {


    /********* =FORGOT-PASSWORD-MODAL  */
    private static class ForgotPasswordModal{
        public static final int INIT_STAGE = 0;
        public static final int MAX_STAGE = 3;
        private static int currentStage = INIT_STAGE;
        private static final String MODAL_NAME = "forgotPasswordStage";

        public static void setCurrentStage(int stage){
            currentStage = stage;
        }

        public static int nextStage(){
            if(currentStage<MAX_STAGE)
                return ++currentStage;
            return INIT_STAGE;
        }
        public static int prevStage(){
            if(currentStage>INIT_STAGE)
               return  --currentStage;
            return INIT_STAGE;
        }
        public static void resetStage(){
            currentStage = INIT_STAGE;
        }

        public static int getCurrentStage() {
            return currentStage;
        }

        public static String getCurrentModalName(){
            return MODAL_NAME+currentStage;
        }
    }
    /********* END =FORGOT-PASSWORD-MODAL  */

    private static class AsyncCallable implements Callable<Boolean>{
        public static Boolean digestresult;
        private final String email;
        private final String password;

        public AsyncCallable(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Boolean call(){
            JPADaoManager jpaDaoManager = new JPADaoManager();
            if (jpaDaoManager.login(new PrivateAccount(email, password, null, null, null)) != null) {
                //logged
                return true;
            } else {
                //incorrect username/password
              return false;

            }
        }
    }

    @FXML
    private TextField emailTf;

    @FXML
    private HBox dialogWindow;

    @FXML
    private ToggleGroup loginType;

    @FXML
    private PasswordField passwordTf;

    @FXML
    private Label errorLbl;
    /********* =FORGOT-PASSWORD-MODAL  */
    @FXML
    private HBox forgoPasswordModal;

    @FXML
    private Pane forgotPasswordStage1;

    @FXML
    private Pane forgotPasswordStage2;


    @FXML
    private TextField recoveryEmailTF;

    @FXML
    private Text recoveryEmailErrorLbl;

    @FXML
    private TextField recoveryCodeS1;

    @FXML
    private TextField recoveryCodeS2;

    @FXML
    private TextField recoveryCodeS3;

    @FXML
    private TextField recoveryCodeS4;


    private Pane[] forgotPasswordStages;
    /********* END =FORGOT-PASSWORD-MODAL  */



    public void initialize(){
        /********* =FORGOT-PASSWORD-MODAL  */
        this.forgotPasswordStages = new Pane[]{this.forgotPasswordStage1, this.forgotPasswordStage2 };
        /********* END =FORGOT-PASSWORD-MODAL  */
        //set char length limit
        this.recoveryCodeS1.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length()<= 3 ? change : null));
        this.recoveryCodeS2.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length()<= 3 ? change : null));
        this.recoveryCodeS3.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length()<= 3 ? change : null));
        this.recoveryCodeS4.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length()<= 1 ? change : null));

    }


    @FXML
    public void openRegistrationView(MouseEvent event) {
        super.dispatchViewChangeRequest(ViewMetadata.AUTH_REGISTRATION);
    }

    @FXML
    public void login(MouseEvent event)throws Exception {
        /********* SHOW LOGIN MODAL DIALOG  */
        this.showDialog();

        /********* =NON-BLOCK ASYNC REQUEST  */
       CompletableFuture.supplyAsync( () -> {
            JPADaoManager jpaDaoManager = new JPADaoManager();
            if (jpaDaoManager.login(new PrivateAccount(emailTf.getText(), passwordTf.getText(), null, null, null)) != null) {
                //logged
                this.hideDialog();
                errorLbl.setVisible(false);
                return true;
            } else {
                //incorrect username/password
                this.hideDialog();
                errorLbl.setVisible(true);
                return false;
            }
        }).thenAccept( (status) ->{
            /********* EVENT DISPATCHER -> WITHIN SAME THREAD  */
           Platform.runLater(new Runnable(){
               @Override public void run() {
                   System.out.println(status);
                   if(status) AuthLogin:SynchContinueApp();
               }
           });
            System.out.println(status);
        });

    }

    /********* =FORGOT-PASSWORD-MODAL  */
    @FXML
    public void closeForgotPasswordModal(MouseEvent event) {
        //hide all steps
        for(int i=0;i<this.forgotPasswordStages.length;i++)
            this.forgotPasswordStages[i].setVisible(false);

        //clean textfields
        this.recoveryEmailTF.setText("");

        this.forgoPasswordModal.setVisible(false);
        ForgotPasswordModal.resetStage();
    }


    @FXML
    public void openForgotModal(MouseEvent event) {
        this.forgoPasswordModal.setVisible(true);
        this.forgotPasswordStages[ForgotPasswordModal.getCurrentStage()].setVisible(true);
    }

    @FXML
    public void forgotPasswordNext(MouseEvent event) {

       switch (ForgotPasswordModal.getCurrentStage()){
           case 0:
               if(recoveryEmailTF.getText().length()>3){
                   this.recoveryEmailErrorLbl.setVisible(false);
                   this.forgotPasswordStages[ForgotPasswordModal.currentStage].setVisible(false);
                   this.forgotPasswordStages[ForgotPasswordModal.nextStage()].setVisible(true);
               } else{
                   this.recoveryEmailErrorLbl.setVisible(true);
               }

       }

    }

    @FXML
    public void recoveryCodeConfirm(MouseEvent event) {
        if(recoveryCodeS1.getText().length()<3)
            recoveryCodeS1.getStyleClass().add("error");
        else
            recoveryCodeS1.getStyleClass().removeIf( name -> name.equals("error"));
        if(recoveryCodeS2.getText().length()<3)
            recoveryCodeS2.getStyleClass().add("error");
        else
            recoveryCodeS2.getStyleClass().removeIf( name -> name.equals("error"));
        if(recoveryCodeS3.getText().length()<3)
            recoveryCodeS3.getStyleClass().add("error");
        else
            recoveryCodeS3.getStyleClass().removeIf( name -> name.equals("error"));
        if(recoveryCodeS4.getText().length()<1)
            recoveryCodeS4.getStyleClass().add("error");
        else
            recoveryCodeS4.getStyleClass().removeIf( name -> name.equals("error"));


    }



    /********* END =FORGOT-PASSWORD-MODAL  */


    /********* =DIALOGS  */
    private void showDialog(){
      this.dialogWindow.setVisible(true);
    }

    private void hideDialog(){
        this.dialogWindow.setVisible(false);
    }
    /********* END =DIALOGS  */

    public void SynchContinueApp(){
        super.dispatchViewChangeRequest(ViewLoader.default_view);
    }

}
