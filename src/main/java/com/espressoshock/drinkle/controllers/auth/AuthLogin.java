package com.espressoshock.drinkle.controllers.auth;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Shake;
import animatefx.animation.Tada;
import com.espressoshock.drinkle.daoLayer.JPADaoManager;
import com.espressoshock.drinkle.models.Account;
import com.espressoshock.drinkle.models.PrivateAccount;
import com.espressoshock.drinkle.recoveryCodeGenerator.CodeGenerator;
import com.espressoshock.drinkle.recoveryCodeGenerator.EmailSender;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import com.espressoshock.drinkle.viewLoader.ViewLoader;
import com.espressoshock.drinkle.viewLoader.ViewMetadata;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.mail.MessagingException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class AuthLogin extends EventDispatcherAdapter {


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
    private Pane forgotPasswordStage3;
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
    @FXML
    private Text passwordNotMatchLbl;
    @FXML
    private TextField newPasswordConfimation;
    @FXML
    private TextField newPassword;
    @FXML
    private Pane modalLoading;
    @FXML
    private Pane resultModal;
    @FXML
    private Pane modalUpdating;
    @FXML
    private Label modalUpdatingText;
    private Pane[] forgotPasswordStages;

    /********* END =FORGOT-PASSWORD-MODAL  */


    public void initialize() {
        /********* =FORGOT-PASSWORD-MODAL  */
        this.forgotPasswordStages = new Pane[]{this.forgotPasswordStage1, this.forgotPasswordStage2, this.forgotPasswordStage3};
        /********* END =FORGOT-PASSWORD-MODAL  */
        //set char length limit
        this.recoveryCodeS1.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= 3 ? change : null));
        this.recoveryCodeS2.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= 3 ? change : null));
        this.recoveryCodeS3.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= 3 ? change : null));
        this.recoveryCodeS4.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= 1 ? change : null));

    }

    @FXML
    public void openRegistrationView(MouseEvent event) {
        super.dispatchViewChangeRequest(ViewMetadata.AUTH_REGISTRATION);
    }

    @FXML
    public void login(Event event) throws Exception {
        //check if fields have been filled properly
        if (this.validEmail(emailTf.getText()) && emailTf.getText().length() > 3 && passwordTf.getText().length() > 3) {
            /********* SHOW LOGIN MODAL DIALOG  */
            this.showDialog();

            //hide error outlines if any
            emailTf.getStyleClass().removeIf(name -> name.equals("error"));
            passwordTf.getStyleClass().removeIf(name -> name.equals("error"));

            /********* =NON-BLOCK ASYNC REQUEST  */
            CompletableFuture.supplyAsync(() -> {
                JPADaoManager jpaDaoManager = new JPADaoManager();
                if (jpaDaoManager.login(new PrivateAccount(emailTf.getText(), passwordTf.getText(), null, null, null)) != null) {
                    //logged
                    this.hideDialog();
                    errorLbl.setVisible(false);
                    //hide error outlines if any
                    emailTf.getStyleClass().removeIf(name -> name.equals("error"));
                    passwordTf.getStyleClass().removeIf(name -> name.equals("error"));

                    return true;
                } else {
                    //incorrect username/password
                    //hide loading dialog
                    this.hideDialog();
                    errorLbl.setVisible(true);
                    //add error outline
                    emailTf.getStyleClass().add("error");
                    passwordTf.getStyleClass().add("error");
                    //error animation
                    this.addErrorAnimation(emailTf);
                    this.addErrorAnimation(passwordTf);
                    this.addTitleErrorAnimation(errorLbl);

                    return false;
                }
            }).thenAccept((status) -> {
                /********* EVENT DISPATCHER -> WITHIN SAME THREAD  */
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(status);
                        if (status) AuthLogin:SynchContinueApp();
                    }
                });
                System.out.println(status);
            });
        } else {
            //fields not filled correctly

            errorLbl.setVisible(true);
            //add error outline
            emailTf.getStyleClass().add("error");
            passwordTf.getStyleClass().add("error");
            //error animation
            this.addErrorAnimation(emailTf);
            this.addErrorAnimation(passwordTf);
            this.addTitleErrorAnimation(errorLbl);
        }


    }

    /********* =FORGOT-PASSWORD-MODAL  */
    @FXML
    public void closeForgotPasswordModal(Event event) {
        //hide all steps
        for (int i = 0; i < this.forgotPasswordStages.length; i++)
            this.forgotPasswordStages[i].setVisible(false);

        //clean textfields
        this.recoveryEmailTF.setText("");
        this.recoveryCodeS1.setText("");
        this.recoveryCodeS2.setText("");
        this.recoveryCodeS3.setText("");
        this.recoveryCodeS4.setText("");
        this.newPassword.setText("");
        this.newPasswordConfimation.setText("");


        /********* =MODAL-ANIMATION  */
        FadeOut fadeOut = new FadeOut(forgoPasswordModal);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forgoPasswordModal.setVisible(false);
            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.play();
        /********* =END MODAL-ANIMATION  */

        ForgotPasswordModal.resetStage();
        ForgotPasswordModal.clear();

        //clear fields
        this.clearRecoveryCodeFields();
        //clear modals
        this.hideLoadingModal();


    }

    @FXML
    public void openForgotModal(Event event) {
        this.forgoPasswordModal.setVisible(true);

        System.out.println(ForgotPasswordModal.getCurrentStage());
        System.out.println(this.forgotPasswordStages[ForgotPasswordModal.getCurrentStage()]);
        this.forgotPasswordStages[ForgotPasswordModal.getCurrentStage()].setVisible(true);
        //animation
        new FadeIn(forgoPasswordModal).setSpeed(6d).play();
    }

    @FXML
    public void forgotPasswordNext(Event event) {

        switch (ForgotPasswordModal.getCurrentStage()) {
            case 0:
                if (this.validEmail(recoveryEmailTF.getText()) && recoveryEmailTF.getText().length() > 3 && !recoveryEmailTF.getText().isEmpty() && recoveryEmailTF.getText() != null) {
                    recoveryEmailTF.getStyleClass().removeIf(name -> name.equals("error"));
                    this.showLoadingModal();
                    /********* =NON-BLOCK ASYNC REQUEST  */
                    CompletableFuture.supplyAsync(() -> {
                        JPADaoManager jpaDaoManager = new JPADaoManager();
                        return jpaDaoManager.validEmail(recoveryEmailTF.getText());
                    }).thenAccept((status) -> {

                        if (status) {
                            System.out.println("Valid email");
                            ForgotPasswordModal.setRecoveryEmail(recoveryEmailTF.getText());
                            //generate recoveryCode
                            String rc1 = (CodeGenerator.generate(3)).toUpperCase();
                            String rc2 = CodeGenerator.generate(3).toUpperCase();
                            String rc3 = CodeGenerator.generate(3).toUpperCase();
                            String rc4 = CodeGenerator.generate(1).toUpperCase();
                            ForgotPasswordModal.setRecoveryCode(rc1 + rc2 + rc3 + rc4);
                            System.out.println(ForgotPasswordModal.getRecoveryCode());

                            //format string
                            String formattedCode = ForgotPasswordModal.getRecoveryCode().substring(0, 3) + "-";
                            formattedCode += ForgotPasswordModal.getRecoveryCode().substring(3, 6) + "-";
                            formattedCode += ForgotPasswordModal.getRecoveryCode().substring(6, 9) + "-";
                            formattedCode += ForgotPasswordModal.getRecoveryCode().substring(9);
                            //send email with code
                            try {

                                EmailSender.sendEmail(ForgotPasswordModal.getRecoveryEmail(), formattedCode);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }


                        } else {
                            System.out.println("Invalid email");
                        }

                        //clear error label
                        this.recoveryEmailErrorLbl.setVisible(false);

                        //deprecated
                      /*
                       this.forgotPasswordStages[ForgotPasswordModal.currentStage].setVisible(false);
                       this.forgotPasswordStages[ForgotPasswordModal.nextStage()].setVisible(true);*/
                        //hide loading
                        this.hideLoadingModal();
                        //update modal
                        this.loadNextModal();


                    });


                } else {
                    recoveryEmailTF.getStyleClass().add("error");
                    this.recoveryEmailErrorLbl.setVisible(true);
                    //animation
                    this.addTitleErrorAnimation(recoveryEmailErrorLbl);
                    this.addErrorAnimation(recoveryEmailTF);
                }
        }

    }

    @FXML
    public void recoveryCodeConfirm(Event event) {
        if (recoveryCodeS1.getText().length() < 3)
            this.addRecoveryCodeErrorAnimation(recoveryCodeS1);
        else
            recoveryCodeS1.getStyleClass().removeIf(name -> name.equals("error"));
        if (recoveryCodeS2.getText().length() < 3)
            this.addRecoveryCodeErrorAnimation(recoveryCodeS2);
        else
            recoveryCodeS2.getStyleClass().removeIf(name -> name.equals("error"));
        if (recoveryCodeS3.getText().length() < 3)
            this.addRecoveryCodeErrorAnimation(recoveryCodeS3);
        else
            recoveryCodeS3.getStyleClass().removeIf(name -> name.equals("error"));
        if (recoveryCodeS4.getText().length() < 1)
            this.addRecoveryCodeErrorAnimation(recoveryCodeS4);
        else
            recoveryCodeS4.getStyleClass().removeIf(name -> name.equals("error"));

        if (recoveryCodeS1.getText().length() == 3 && recoveryCodeS2.getText().length() == 3 && recoveryCodeS3.getText().length() == 3 && recoveryCodeS4.getText().length() == 1) {
            if ((recoveryCodeS1.getText() + recoveryCodeS2.getText() + recoveryCodeS3.getText() + recoveryCodeS4.getText()).equals(ForgotPasswordModal.getRecoveryCode())) {
               /* this.forgotPasswordStages[ForgotPasswordModal.currentStage].setVisible(false);
                this.forgotPasswordStages[ForgotPasswordModal.nextStage()].setVisible(true);*/
                this.loadNextModal();
                recoveryCodeS1.getStyleClass().removeIf(name -> name.equals("error"));
                recoveryCodeS2.getStyleClass().removeIf(name -> name.equals("error"));
                recoveryCodeS3.getStyleClass().removeIf(name -> name.equals("error"));
                recoveryCodeS4.getStyleClass().removeIf(name -> name.equals("error"));
            } else {
                this.addRecoveryCodeErrorAnimation(recoveryCodeS1);
                this.addRecoveryCodeErrorAnimation(recoveryCodeS2);
                this.addRecoveryCodeErrorAnimation(recoveryCodeS3);
                this.addRecoveryCodeErrorAnimation(recoveryCodeS4);
            }
        }


    }

    @FXML
    public void passwordChangeConfirm(Event event) {
        /********* PASSWORD MATCH && ->satisfy min requirements */
        if (this.newPassword.getText().equals(this.newPasswordConfimation.getText()) && this.newPassword.getText().length() > 5) {
            /********* clear password mismatch error */
            this.clearPasswordMismatchError();
            /********* updating modal text */
            this.showUpdatingModal();
            this.setUpdatingModalMessage("Updating your password...");
            /********* save plain password for thread jump */
            ForgotPasswordModal.setNewPasswordPlain(this.newPassword.getText());
            CompletableFuture.supplyAsync(() -> {
                JPADaoManager jpaDaoManager = new JPADaoManager();
                return jpaDaoManager.updatePassword(new Account(ForgotPasswordModal.getRecoveryEmail(), null, null, null, null), ForgotPasswordModal.getNewPasswordPlain());
            }).thenAccept((status) -> {
                /********* EVENT DISPATCHER -> WITHIN SAME THREAD  */
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("status:" + status);
                        AuthLogin:
                        hideUpdatingModal();
                        /********* SUCESSFULLY PASSWORD UDPDATE  */
                        if (status) {
                            /********* SHOW SUCCESS SCREEN */
                            AuthLogin:
                            showResultModal();
                            AuthLogin:
                            hideResultModal(7);

                        } else {
                            /********* UNEXPECTED ERROR */
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Password Update error");
                            alert.setHeaderText("An error occurred during the password updating");
                            alert.setContentText("Please try back again later");

                            alert.showAndWait();
                        }

                    }
                });
            });
        } else {
/********* PASSWORD DOESN'T MATCH  && not->satisfy min requirements */
            /********* display password mismatch error */
            this.displayPasswordMismatchError();

        }

    }

    /********* =DIALOGS  */
    private void showDialog() {
        this.dialogWindow.setVisible(true);
        new FadeIn(dialogWindow).setSpeed(6d).play();
    }

    private void hideDialog() {
        /********* =MODAL-ANIMATION  */
        FadeOut fadeOut = new FadeOut(dialogWindow);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogWindow.setVisible(false);
            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.play();
        /********* =END MODAL-ANIMATION  */

    }

    /********* END =FORGOT-PASSWORD-MODAL  */

    private void showLoadingModal() {
        this.modalLoading.setVisible(true);
        new FadeIn(modalLoading).setSpeed(6d).play();
    }

    private void hideLoadingModal() {

        /********* =MODAL-ANIMATION  */
        FadeOut fadeOut = new FadeOut(modalLoading);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modalLoading.setVisible(false);
            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.play();
        /********* =END MODAL-ANIMATION  */

    }

    private void showUpdatingModal() {
        this.modalUpdating.setVisible(true);
        new FadeIn(modalUpdating).setSpeed(6d).play();
    }

    private void hideUpdatingModal() {

        /********* =MODAL-ANIMATION  */
        FadeOut fadeOut = new FadeOut(modalUpdating);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                modalUpdating.setVisible(false);
            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.play();
        /********* =END MODAL-ANIMATION  */
    }

    private void showResultModal() {
        this.resultModal.setVisible(true);
        new FadeIn(resultModal).setSpeed(6d).play();
    }

    private void hideResultModal(double seconds) {

        /********* =MODAL-ANIMATION  */
        FadeOut fadeOut = new FadeOut(resultModal);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resultModal.setVisible(false);
                /********* HIDE WINDOW */
                closeForgotPasswordModal(null);
            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.setDelay(Duration.seconds(seconds)).play();
        /********* =END MODAL-ANIMATION  */
    }

    private void setUpdatingModalMessage(String text) {
        this.modalUpdatingText.setText(text);
    }

    private void clearRecoveryCodeFields() {
        recoveryCodeS1.getStyleClass().removeIf(name -> name.equals("error"));
        recoveryCodeS2.getStyleClass().removeIf(name -> name.equals("error"));
        recoveryCodeS3.getStyleClass().removeIf(name -> name.equals("error"));
        recoveryCodeS4.getStyleClass().removeIf(name -> name.equals("error"));
    }

    private void loadNextModal() {
        /********* =MODAL-ANIMATION  */
       /* FadeOut fadeOut = new FadeOut(forgotPasswordStages[ForgotPasswordModal.currentStage]);
        fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forgotPasswordStages[ForgotPasswordModal.currentStage].setVisible(false);
                forgotPasswordStages[ForgotPasswordModal.nextStage()].setVisible(true);
                new FadeIn( forgotPasswordStages[ForgotPasswordModal.getCurrentStage()]).setSpeed(6d).play();

            }
        });
        fadeOut.setSpeed(4d);
        fadeOut.play();*/
        /********* =END MODAL-ANIMATION  */
        forgotPasswordStages[ForgotPasswordModal.currentStage].setVisible(false);
        forgotPasswordStages[ForgotPasswordModal.nextStage()].setVisible(true);
        new FadeIn(forgotPasswordStages[ForgotPasswordModal.getCurrentStage()]).setSpeed(6d).play();

    }

    public void displayPasswordMismatchError() {
        /********* error label visibility */
        this.passwordNotMatchLbl.setVisible(true);
        /********* error css classes  */
        this.newPassword.getStyleClass().add("error");
        this.newPasswordConfimation.getStyleClass().add("error");
        /********* error animations added  */
        this.addTitleErrorAnimation(this.passwordNotMatchLbl);
        this.addErrorAnimation(this.newPassword);
        this.addErrorAnimation(this.newPasswordConfimation);
    }

    public void clearPasswordMismatchError() {
        this.passwordNotMatchLbl.setVisible(false);
        this.newPassword.getStyleClass().removeIf(name -> name.equals("error"));
        this.newPasswordConfimation.getStyleClass().removeIf(name -> name.equals("error"));
    }

    /********* =ANIMATIONS  */
    private void addErrorAnimation(Node node) {
        new Shake(node).play();
    }

    private void addTitleErrorAnimation(Node node) {
        new Tada(node).play();
    }


    /********* END =DIALOGS  */

    private void addRecoveryCodeErrorAnimation(Node node) {
        node.getStyleClass().add("error");
        this.addErrorAnimation(node);
    }

    /********* END =ANIMATIONS  */

    public boolean validEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public void SynchContinueApp() {
        super.dispatchViewChangeRequest(ViewLoader.default_view);
    }

    /********* =FORGOT-PASSWORD-MODAL  */
    private static class ForgotPasswordModal {
        public static final int INIT_STAGE = 0;
        public static final int MAX_STAGE = 3;
        private static final String MODAL_NAME = "forgotPasswordStage";
        private static int currentStage = INIT_STAGE;
        private static String recoveryEmail;
        private static String recoveryCode;
        private static String newPasswordPlain;

        public static int nextStage() {
            if (currentStage < MAX_STAGE)
                return ++currentStage;
            return INIT_STAGE;
        }

        public static int prevStage() {
            if (currentStage > INIT_STAGE)
                return --currentStage;
            return INIT_STAGE;
        }

        public static String getRecoveryCode() {
            return recoveryCode;
        }

        public static void setRecoveryCode(String recoveryCode) {
            ForgotPasswordModal.recoveryCode = recoveryCode;
        }

        public static void clear() {
            recoveryEmail = "";
            recoveryCode = "";
        }

        public static String getNewPasswordPlain() {
            return newPasswordPlain;
        }

        public static void setNewPasswordPlain(String newPasswordPlain) {
            ForgotPasswordModal.newPasswordPlain = newPasswordPlain;
        }

        public static String getRecoveryEmail() {
            return recoveryEmail;
        }

        public static void setRecoveryEmail(String recoveryEmail) {
            ForgotPasswordModal.recoveryEmail = recoveryEmail;
        }

        public static void resetStage() {
            currentStage = INIT_STAGE;
        }

        public static int getCurrentStage() {
            return currentStage;
        }

        public static void setCurrentStage(int stage) {
            currentStage = stage;
        }

        public static String getCurrentModalName() {
            return MODAL_NAME + currentStage;
        }
    }

    /********* END =FORGOT-PASSWORD-MODAL  */

    private static class AsyncCallable implements Callable<Boolean> {
        public static Boolean digestresult;
        private final String email;
        private final String password;

        public AsyncCallable(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Boolean call() {
            JPADaoManager jpaDaoManager = new JPADaoManager();
            //logged
            //incorrect username/password
            return jpaDaoManager.login(new PrivateAccount(email, password, null, null, null)) != null;
        }
    }

}
