/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.Data;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.utils.AlertMessage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

import com.ntdat.plan_management_sysyem.utils.ImageConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class EditPatientFormController implements Initializable {

   @FXML
   private TextField edit_patientID;

   @FXML
   private Label edit_patientID_label;

   @FXML
   private TextField edit_name;

   @FXML
   private ComboBox<String> edit_gender;

   @FXML
   private TextField edit_contactNumber;

   @FXML
   private TextArea edit_address;

   @FXML
   private ComboBox<String> edit_status;

   @FXML
   private ImageView editPatient_imageView;

   @FXML
   private Button editPatient_importBtn;

   @FXML
   private DatePicker edit_birthday;

   @FXML
   private TextField edit_password;

   private ManagerMainFormController managerMainFormController;

   private AlertMessage alert = new AlertMessage();

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;

   public void updateBtn() {
      connect = database.connectDB();

      try {
         if (Data.edit_patient_mode.equals("edit")) {
            editPatient();
         } else if (Data.edit_patient_mode.equals("add")) {
            createPatient();
         }
      } catch (Exception e) {
         e.printStackTrace();
         alert.errorMessage(e.getMessage());
      }

   }

   public void editPatient() throws SQLException, IOException {
      if (edit_name.getText().isEmpty()
              || edit_gender.getSelectionModel().getSelectedItem() == null
              || edit_contactNumber.getText().isEmpty()
              || edit_address.getText().isEmpty()
              || edit_status.getSelectionModel().getSelectedItem() == null
              || edit_birthday.getValue() == null) {
         alert.errorMessage("Please fill all blank fields");
      } else {
         String path = extractPathFromImageView();
         String writePath = ImageConfig.writeToDisk(path);
         String updateData = "UPDATE patient SET full_name = ?, gender = ?"
                 + ", moblie_number = ?, address = ?, status = ?, modified_at = ? "
                 + ", birthday = ?, password = ?, image = ?"
                 + " WHERE id = '"
                 + edit_patientID.getText() + "'";
         if (alert.confirmationMessage("Are you sure you want to UPDATE Patient ID: " + edit_patientID.getText()
                 + "?")) {
            prepare = connect.prepareStatement(updateData);
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(1, edit_name.getText());
            prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
            prepare.setString(3, edit_contactNumber.getText());
            prepare.setString(4, edit_address.getText());
            prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
            prepare.setString(6, String.valueOf(sqlDate));
            prepare.setString(7, String.valueOf(edit_birthday.getValue()));
            prepare.setString(8, edit_password.getText());
            prepare.setString(9, writePath);
            prepare.executeUpdate();
            this.managerMainFormController.refreshPatientTable();
            alert.successMessage("Updated Successfully!");
         } else {
            alert.errorMessage("Cancelled.");
         }
      }
   }

   public void createPatient() throws SQLException, IOException {
      if (edit_name.getText().isEmpty()
              || edit_gender.getSelectionModel().getSelectedItem() == null
              || edit_contactNumber.getText().isEmpty()
              || edit_address.getText().isEmpty()
              || edit_status.getSelectionModel().getSelectedItem() == null
              || edit_birthday.getValue() == null
              || edit_password.getText().isEmpty()) {
         alert.errorMessage("Please fill all blank fields");
      } else {
         String path = extractPathFromImageView();;
         String writePath = ImageConfig.writeToDisk(path);
         String createData = "INSERT INTO patient (password, full_name, moblie_number, gender, address,status, birthday, image) "
                 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
         prepare = connect.prepareStatement(createData);
         prepare.setString(1, edit_password.getText());
         prepare.setString(2, edit_name.getText());
         prepare.setString(3, edit_contactNumber.getText());
         prepare.setString(4, edit_gender.getSelectionModel().getSelectedItem());
         prepare.setString(5, edit_address.getText());
         prepare.setString(6, edit_status.getSelectionModel().getSelectedItem());
         prepare.setString(7, String.valueOf(edit_birthday.getValue()));
         prepare.setString(8, writePath);
         prepare.executeUpdate();
         this.managerMainFormController.refreshPatientTable();
         alert.successMessage("Updated Successfully!");
      }
   }

   private String extractPathFromImageView() {
      Image image = editPatient_imageView.getImage();
      String url = image.getUrl();
      String path = null;
      try {
         path = Paths.get(new URI(url)).toString();
      } catch (Exception e) {
         e.printStackTrace();
      }
      if (path == null) {
         path = ImageConfig.defaultImage;
      }

      return path;
   }

   // CLOSE THE EDITPATIENTFORM FXML FILE AND OPEN IT AGAIN
   public void setField() {
      edit_patientID.setText(String.valueOf(Data.temp_PatientID));
      edit_name.setText(Data.temp_name);
      edit_gender.getSelectionModel().select(Data.temp_gender);
      edit_contactNumber.setText(String.valueOf(Data.temp_number));
      edit_address.setText(Data.temp_address);
      edit_status.getSelectionModel().select(Data.temp_status);
      edit_birthday.setValue(Data.temp_birthday.toLocalDate());
      edit_password.setText(Data.temp_password);
      String path = ImageConfig.formatPathRead(Data.temp_path);
      Image image = new Image("File:" + path, 112, 121, false, true);
      editPatient_imageView.setImage(image);
   }

   public void genderList() {
      List<String> genderL = new ArrayList<>();

      for (String data : Data.gender) {
         genderL.add(data);
      }

      ObservableList listData = FXCollections.observableList(genderL);
      edit_gender.setItems(listData);
   }

   public void statusList() {
      List<String> statusL = new ArrayList<>();

      for (String data : Data.status) {
         statusL.add(data);
      }

      ObservableList listData = FXCollections.observableList(statusL);
      edit_status.setItems(listData);
   }

   public void importBtn() {

      FileChooser open = new FileChooser();
      open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*.jpg", "*.png", "*.jpeg"));

      File file = open.showOpenDialog(editPatient_importBtn.getScene().getWindow());

      if (file != null) {

         Data.path = file.getAbsolutePath();
         Image image = new Image(file.toURI().toString(), 112, 121, false, true);
         editPatient_imageView.setImage(image);
      }

   }

   public void setManagerMainFormController(ManagerMainFormController controller) {
      this.managerMainFormController = controller;
   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      if (Objects.equals(Data.edit_patient_mode, "add")) {
            edit_patientID_label.setVisible(false);
            edit_patientID.setVisible(false);
            Image image = new Image("File:" + ImageConfig.formatPathRead(ImageConfig.defaultImage), 112, 121, false, true);
            editPatient_imageView.setImage(image);
      }
      if (Objects.equals(Data.edit_patient_mode, "edit")) {
         setField();
      }
      genderList();
      statusList();
   }
}
