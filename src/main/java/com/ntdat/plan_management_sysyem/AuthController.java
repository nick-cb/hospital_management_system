package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.utils.AlertMessage;
import com.ntdat.plan_management_sysyem.utils.Users;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.database.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AuthController implements Initializable {

   @FXML
   private AnchorPane login_form;

   @FXML
   private TextField login_username;

   @FXML
   private PasswordField login_password;

   @FXML
   private TextField login_showPassword;

   @FXML
   private CheckBox login_checkBox;

   @FXML
   private Button login_loginBtn;

   @FXML
   private ComboBox<?> login_user;

   @FXML
   private Hyperlink login_registerHere;

   @FXML
   private AnchorPane register_form;

   @FXML
   private TextField register_email;

   @FXML
   private TextField register_username;

   @FXML
   private PasswordField register_password;

   @FXML
   private TextField register_showPassword;

   @FXML
   private CheckBox register_checkBox;

   @FXML
   private Hyperlink register_loginHere;

   @FXML
   private void switchTo(String fxml, String breadcrumb) throws IOException {
      App.setRoot(fxml, breadcrumb);
   }

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;

   private final AlertMessage alert = new AlertMessage();

   public void loginAccount() {

      if (login_username.getText().isEmpty()
              || login_password.getText().isEmpty()) {
         alert.errorMessage("Incorrect Username/Password");
      } else {

         String sql = "SELECT * FROM manager WHERE username = ? AND password = ?";

         connect = database.connectDB();

         try {

            if (!login_showPassword.isVisible()) {
               if (!login_showPassword.getText().equals(login_password.getText())) {
                  login_showPassword.setText(login_password.getText());
               }
            } else {
               if (!login_showPassword.getText().equals(login_password.getText())) {
                  login_password.setText(login_showPassword.getText());
               }
            }

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_username.getText());
            prepare.setString(2, login_password.getText());
            result = prepare.executeQuery();

            if (result.next()) {
               // TO GET THE USERNAME
               Data.manager_username = login_username.getText();
               Data.manager_id = Integer.parseInt(result.getString("id"));

               // IF CORRECT USERNAME AND PASSWORD
               alert.successMessage("Login Successfully!");
               // LINK MAIN FORM FOR ADMIN
               switchTo("ManagerMainForm", "Admin Portal");
               login_loginBtn.getScene().getWindow().hide();
            } else {
               // IF WRONG USERNAME OR PASSWORD
               alert.errorMessage("Incorrect Username/Password");
            }

         } catch (IOException | NumberFormatException | SQLException e) {
            e.printStackTrace();
         }

      }

   }

   public void loginShowPassword() {

      if (login_checkBox.isSelected()) {
         login_showPassword.setText(login_password.getText());
         login_showPassword.setVisible(true);
         login_password.setVisible(false);
      } else {
         login_password.setText(login_showPassword.getText());
         login_showPassword.setVisible(false);
         login_password.setVisible(true);
      }

   }

   public void registerAccount() {

      if (register_email.getText().isEmpty()
              || register_username.getText().isEmpty()
              || register_password.getText().isEmpty()) {
         // LETS CREATE OUR ALERT MESSAGE
         alert.errorMessage("Please fill all blank fields");
      } else {

         // WE WILL CHECK IF THE USERNAME THAT USER ENTERED IS ALREADY EXIST TO OUR DATABASE 
         String checkUsername = "SELECT * FROM manager WHERE username = '"
                 + register_username.getText() + "'";

         connect = database.connectDB();

         try {

            if (!register_showPassword.isVisible()) {
               if (!register_showPassword.getText().equals(register_password.getText())) {
                  register_showPassword.setText(register_password.getText());
               }
            } else {
               if (!register_showPassword.getText().equals(register_password.getText())) {
                  register_password.setText(register_showPassword.getText());
               }
            }

            prepare = connect.prepareStatement(checkUsername);
            result = prepare.executeQuery();

            if (result.next()) {
               alert.errorMessage(register_username.getText() + " is already exist!");
            } else if (register_password.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
               alert.errorMessage("Invalid Password, at least 8 characters needed");
            } else {
               // TO INSERT THE DATA TO OUR DATABASE
               String insertData = "INSERT INTO manager (email, username, password, birthday) VALUES(?,?,?,?)";

               Date date = new Date();
               java.sql.Date sqlDate = new java.sql.Date(date.getTime());

               prepare = connect.prepareStatement(insertData);
               prepare.setString(1, register_email.getText());
               prepare.setString(2, register_username.getText());
               prepare.setString(3, register_password.getText());
               prepare.setString(4, String.valueOf(sqlDate));

               prepare.executeUpdate();

               alert.successMessage("Registered Successfully!");
               registerClear();

               // TO SWITCH THE FORM INTO LOGIN FORM
               login_form.setVisible(true);
               register_form.setVisible(false);
            }
         } catch (SQLException e) {
         }
      }
   }

   public void registerClear() {
      register_email.clear();
      register_username.clear();
      register_password.clear();
      register_showPassword.clear();
   }

   public void registerShowPassword() {

      if (register_checkBox.isSelected()) {
         register_showPassword.setText(register_password.getText());
         register_showPassword.setVisible(true);
         register_password.setVisible(false);
      } else {
         register_password.setText(register_showPassword.getText());
         register_showPassword.setVisible(false);
         register_password.setVisible(true);
      }

   }

   public void userList() {
      List<String> listU = new ArrayList<>();

      for (String data : Users.user) {
         listU.add(data);
      }
      ObservableList listData = FXCollections.observableList(listU);
      login_user.setItems(listData);
   }

   public void switchPage() throws IOException {

      if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {
         switchTo("Authentication", "");

      } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {
         switchTo("DoctorPage", "");

      } else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {
         switchTo("PatientPage", "");
      }

      login_user.getScene().getWindow().hide();

   }

   public void switchForm(ActionEvent event) {

      if (event.getSource() == login_registerHere) {
         // REGISTRATION FORM WILL SHOW
         login_form.setVisible(false);
         register_form.setVisible(true);
      } else if (event.getSource() == register_loginHere) {
         // LOGIN FORM WILL SHOW
         login_form.setVisible(true);
         register_form.setVisible(false);
      }
   }

   @Override
   public void initialize(URL url, ResourceBundle rb) {
      userList();
   }
}
