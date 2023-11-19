/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.Data;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.utils.AlertMessage;
import com.ntdat.plan_management_sysyem.utils.Users;
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

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class DoctorPageController implements Initializable {

   @FXML
   private AnchorPane login_form;

   @FXML
   private TextField login_doctorEmail;

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
   private TextField register_fullName;

   @FXML
   private TextField register_email;

   @FXML
   private TextField register_doctorID;

   @FXML
   private PasswordField register_password;

   @FXML
   private TextField register_showPassword;

   @FXML
   private CheckBox register_checkBox;

   @FXML
   private Hyperlink register_loginHere;

   // DATABASE TOOLS
   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;

   private final AlertMessage alert = new AlertMessage();

   @FXML
   private void switchTo(String fxml, String breadcrumb) throws IOException {
      App.setRoot(fxml, breadcrumb);
   }

   @FXML
   void loginAccount() {

      if (login_doctorEmail.getText().isEmpty()
              || login_password.getText().isEmpty()) {
         alert.errorMessage("Incorrect Doctor ID/Password");
      } else {

         String sql = "SELECT * FROM doctor WHERE email = ? AND password = ? AND deleted_at IS NULL";
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

            // CHECK IF THE STATUS OF THE DOCTOR IS CONFIRM 
/*            String checkStatus = "SELECT status FROM doctor WHERE id = '"
                    + login_doctorID.getText() + "' AND password = '"
                    + login_password.getText() + "' AND status = 'Confirm'";*/

//            prepare = connect.prepareStatement(checkStatus);
//            result = prepare.executeQuery();

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_doctorEmail.getText());
            prepare.setString(2, login_password.getText());

            result = prepare.executeQuery();

            if (result.next()) {

               Data.doctor_id = result.getString("code");
               Data.doctor_name = result.getString("full_name");

               alert.successMessage("Login Successfully!");

               // LINK YOUR DOCTOR MAIN FORM
               switchTo("DoctorMainForm", "Doctor Main Form");
               // TO HIDE YOUR DOCTOR PAGE
               login_loginBtn.getScene().getWindow().hide();

            } else {
               alert.errorMessage("Incorrect Doctor ID/Password");
            }
         } catch (IOException | SQLException e) {
         }

      }

   }

   @FXML
   void loginShowPassword() {

      if (login_checkBox.isSelected()) {
         login_showPassword.setText(login_password.getText());
         login_password.setVisible(false);
         login_showPassword.setVisible(true);
      } else {
         login_password.setText(login_showPassword.getText());
         login_password.setVisible(true);
         login_showPassword.setVisible(false);
      }

   }

   @FXML
   void registerAccount() {

      if (register_fullName.getText().isEmpty()
              || register_email.getText().isEmpty()
              || register_doctorID.getText().isEmpty()
              || register_password.getText().isEmpty()) {
         alert.errorMessage("Please fill all blank fields");
      } else {

         String checkDoctorID = "SELECT * FROM doctor WHERE id = '"
                 + register_doctorID.getText() + "'"; // LETS CREATE OUR TABLE FOR DOCTOR FIRST

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

            prepare = connect.prepareStatement(checkDoctorID);
            result = prepare.executeQuery();

            if (result.next()) {
               alert.errorMessage(register_doctorID.getText() + " is already taken");
            } else if (register_password.getText().length() < 8) {
               alert.errorMessage("Invalid password, at least 8 characters needed");
            } else {

               String insertData = "INSERT INTO doctor (full_name, email, code, password, birthday, status) "
                       + "VALUES(?,?,?,?,?,?)";

               prepare = connect.prepareStatement(insertData);

               Date date = new Date();
               java.sql.Date sqlDate = new java.sql.Date(date.getTime());

               prepare.setString(1, register_fullName.getText());
               prepare.setString(2, register_email.getText());
               prepare.setString(3, register_doctorID.getText());
               prepare.setString(4, register_password.getText());
               prepare.setString(5, String.valueOf(sqlDate));
               prepare.setString(6, "Confirm");

               prepare.executeUpdate();

               alert.successMessage("Registered Succesfully!");
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
      register_fullName.clear();
      register_email.clear();
      register_doctorID.clear();
      register_password.clear();
      register_showPassword.clear();

   }

   @FXML
   void registerShowPassword() {

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

   public void registerDoctorID() {
      String doctorID = "DID-";
      int tempID = 0;
      String sql = "SELECT MAX(id) FROM doctor";

      connect = database.connectDB();

      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            tempID = result.getInt("MAX(id)");
         }

         if (tempID == 0) {
            tempID += 1;
            doctorID += tempID;
         } else {
            doctorID += (tempID + 1);
         }

         register_doctorID.setText(doctorID);
         register_doctorID.setDisable(true);

      } catch (Exception e) {
         e.printStackTrace();
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

   @FXML
   void switchForm(ActionEvent event) {

      if (event.getSource() == register_loginHere) {
         login_form.setVisible(true);
         register_form.setVisible(false);
      } else if (event.getSource() == login_registerHere) {
         login_form.setVisible(false);
         register_form.setVisible(true);
      }

   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      registerDoctorID();
      userList();
   }

}
