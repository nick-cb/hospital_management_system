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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PatientPageController implements Initializable {

   @FXML
   private TextField login_patientID;

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

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;

   private AlertMessage alert = new AlertMessage();

   @FXML
   private void switchTo(String fxml, String breadcrumb) throws IOException {
      App.setRoot(fxml, breadcrumb);
   }

   @FXML
   void loginAccount(ActionEvent event) {

      if (login_patientID.getText().isEmpty()
              || login_password.getText().isEmpty()) {
         alert.errorMessage("Incorrect Patient ID/Password");
      } else {

         String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND deleted_at IS NULL";
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
            String checkStatus = "SELECT status FROM patient WHERE patient_id = '"
                    + login_patientID.getText() + "' AND password = '"
                    + login_password.getText() + "' AND status = 'Confirm'";

            prepare = connect.prepareStatement(checkStatus);
            result = prepare.executeQuery();

            if (result.next()) {

               if (!login_showPassword.isVisible()) {
                  if (!login_showPassword.getText().equals(login_password.getText())) {
                     login_showPassword.setText(login_password.getText());
                  }
               } else {
                  if (!login_showPassword.getText().equals(login_password.getText())) {
                     login_password.setText(login_showPassword.getText());
                  }
               }

               alert.errorMessage("Need the confimation of the Admin!");
            } else {
               prepare = connect.prepareStatement(sql);
               prepare.setString(1, login_patientID.getText());
               prepare.setString(2, login_password.getText());

               result = prepare.executeQuery();

               if (result.next()) {
                  Data.patient_id = Integer.parseInt(login_patientID.getText());

                  alert.successMessage("Login Successfully!");
                  // LINK YOUR PATIENT MAIN FORM
                  switchTo("PatientMainForm", "");
                  // TO HIDE YOUR LOGIN FORM
                  login_loginBtn.getScene().getWindow().hide();
               } else {
                  alert.errorMessage("Incorrect Patient ID/Password");
               }
            }

         } catch (IOException | NumberFormatException | SQLException e) {
         }

      }

   }

   @FXML
   void loginShowPassword(ActionEvent event) {

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

   @Override
   public void initialize(URL url, ResourceBundle rb) {
      userList();
   }
}
