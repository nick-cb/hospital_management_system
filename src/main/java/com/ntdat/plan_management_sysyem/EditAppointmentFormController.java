/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.AppointmentData;
import com.ntdat.plan_management_sysyem.database.Data;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.utils.AlertMessage;

/**
 * FXML Controller class
 *
 * @author thanh
 */
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.ntdat.plan_management_sysyem.utils.AppConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditAppointmentFormController implements Initializable {
   public String mode = "edit";
   public AppointmentData appointmentData;
   @FXML
   private Button editApp_updateBtn;

   @FXML
   private TextField editApp_appointmentID;

   @FXML
   private TextField editApp_fullName;

   @FXML
   private ComboBox<String> editApp_gender;

   @FXML
   private TextField editApp_mobileNumber;

   @FXML
   private TextArea editApp_address;

   @FXML
   private TextArea editApp_description;

   @FXML
   private TextField editApp_diagnosis;

   @FXML
   private TextField editApp_treatment;

   @FXML
   private ComboBox<String> editApp_doctor;

   @FXML
   private ComboBox<String> editApp_specialized;

   @FXML
   private ComboBox<String> editApp_status;

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;

   private AlertMessage alert = new AlertMessage();
   private ManagerMainFormController managerMainFormController = new ManagerMainFormController();

   public void displayFields() {
      editApp_appointmentID.setText(appointmentData.getId().toString());
//      editApp_fullName.setText(Data.temp_appName);
//      editApp_gender.getSelectionModel().select(Data.temp_appGender);
//      editApp_mobileNumber.setText(Data.temp_appMobileNumber);
//      editApp_address.setText(Data.temp_appAddress);
//      editApp_description.setText(Data.temp_appDescription);
      editApp_diagnosis.setText(appointmentData.getDiagnosis());
      editApp_treatment.setText(appointmentData.getTreatment());
//      editApp_doctor.getSelectionModel().select(appointmentData.getDoctorName());
//      editApp_specialized.getSelectionModel().select(Data.temp_appSpecialized);
//      editApp_status.getSelectionModel().select(Data.temp_appStatus);
   }

   public void doctorList() {
      String sql = "SELECT * FROM doctor WHERE deleted_at IS NULL";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();
         ObservableList listData = FXCollections.observableArrayList();
         while (result.next()) {
            listData.add(result.getString("code"));
         }

         editApp_doctor.setItems(listData);
         specializedList();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void specializedList() {
      String sql = "SELECT * FROM doctor WHERE deleted_at IS NULL AND id = '"
              + editApp_doctor.getSelectionModel().getSelectedItem() + "'";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();
         ObservableList listData = FXCollections.observableArrayList();
         if (result.next()) {
            listData.add(result.getString("specialized"));
         }
         editApp_specialized.setItems(listData);

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void genderList() {
      List<String> genderL = new ArrayList<>();

      for (String data : Data.gender) {
         genderL.add(data);
      }

      ObservableList listData = FXCollections.observableList(genderL);
      editApp_gender.setItems(listData);
   }

   public void statusList() {
      List<String> statusL = new ArrayList<>();

      for (String data : Data.status) {
         statusL.add(data);
      }

      ObservableList listData = FXCollections.observableList(statusL);
      editApp_status.setItems(listData);
   }

   public void updateBtn() {
      connect = database.connectDB();

      if (editApp_appointmentID.getText().isEmpty()
              || editApp_fullName.getText().isEmpty()
              || editApp_gender.getSelectionModel().getSelectedItem() == null
              || editApp_mobileNumber.getText().isEmpty()
              || editApp_address.getText().isEmpty()
              || editApp_description.getText().isEmpty()
              || editApp_diagnosis.getText().isEmpty()
              || editApp_treatment.getText().isEmpty()
              || editApp_doctor.getSelectionModel().getSelectedItem() == null
              || editApp_specialized.getSelectionModel().getSelectedItem() == null
              || editApp_status.getSelectionModel().getSelectedItem() == null) {
         alert.errorMessage("Please fill all blank fields");
      } else {
         Date date = new Date();
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());

//         if (Data.path == null || "".equals(Data.path)) {
            String updateData = "UPDATE appointment SET "
                    + " description = '" + editApp_description.getText() + "',"
                    + " diagnosis = '" + editApp_diagnosis.getText()  + "',"
                    + " treatment = '" + editApp_treatment.getText() + "',"
                    + " doctor_id = '" + editApp_doctor.getSelectionModel().getSelectedItem() + "',"
                    + " status = '" + editApp_status.getSelectionModel().getSelectedItem() + "'"
                    + " WHERE id = '" + editApp_appointmentID.getText() + "'";
            try {
               if (alert.confirmationMessage("Are you sure you want to Update Appointment ID: " + editApp_appointmentID.getText() + "?")) {
                  prepare = connect.prepareStatement(updateData);
                  prepare.executeUpdate();
               } else {
                  alert.errorMessage("Cancelled.");
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         /*} else {
            try {
               if (alert.confirmationMessage("Are you sure you want to Update Doctor ID: "
                       + editApp_appointmentID.getText() + "?")) {
                  String path = Data.path;
                  path = path.replace("\\", "\\\\");
                  Path transfer = Paths.get(path);

                  Path copy = Paths.get(AppConfig.root_dir + "\\src\\main\\resources\\com\\ntdat\\plan_management_sysyem\\images\\"
                       + editApp_appointmentID.getText() + ".png");
                  
                  Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                  String insertImage = copy.toString();
                  insertImage = insertImage.replace("\\", "\\\\");

                  String updateData = "UPDATE appointment SET name = '"
                          + editApp_fullName.getText() + "', gender = '"
                          + editApp_gender.getSelectionModel().getSelectedItem() + "', moblie_number = '"
                          + editApp_mobileNumber.getText() + "', image = '"
                          + insertImage + "', address = '"
                          + editApp_address.getText() + "', description = '"
                          + editApp_description.getText() + "', diagnosis = '"
                          + editApp_diagnosis.getText() + "', treatment = '"
                          + editApp_treatment.getText() + "', doctor = '"
                          + editApp_doctor.getSelectionModel().getSelectedItem() + "', specialized = '"
                          + editApp_specialized.getSelectionModel().getSelectedItem() + "', status = '"
                          + editApp_status.getSelectionModel().getSelectedItem() + "', date_modify = '"
                          + String.valueOf(sqlDate) + "' "
                          + "WHERE appointment = '" + editApp_appointmentID.getText() + "'";
                  
                  prepare = connect.prepareStatement(updateData);
                  prepare.executeUpdate();
                  
               } else {
                  alert.errorMessage("Cancelled.");
               }
            } catch (Exception e) {
               e.printStackTrace();
            }

         }*/
      }
      displayFields();
   }

   public void cancelBtn() {
      displayFields();
   }

   public void setManagerMainFormController(ManagerMainFormController managerMainFormController) {
      this.managerMainFormController = managerMainFormController;
   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {

   }

   public void postInitialize() {
      doctorList();
//      genderList();
//      statusList();

      if (mode.equals("edit")) {
         displayFields();
      }
   }

}
