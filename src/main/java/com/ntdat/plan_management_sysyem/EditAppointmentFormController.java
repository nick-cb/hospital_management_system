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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ntdat.plan_management_sysyem.utils.ImageConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
   private TextArea editApp_diagnosis;

   @FXML
   private TextArea editApp_treatment;

   @FXML
   private ComboBox<String> editApp_doctor;

   @FXML
   private ComboBox<String> editApp_specialized;

   @FXML
   private ComboBox<String> editApp_status;

   @FXML
   private HBox doctor_list;

   @FXML
   private TextField patient_phone;

   @FXML
   private VBox patientVBox;

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private Integer selectedDoctorID;

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

   public void showDoctorList() {
      var specialize = editApp_specialized.getSelectionModel().getSelectedItem();

      String sql = specialize == null
              ? "SELECT * FROM doctor WHERE deleted_at IS NULL"
              : "SELECT * FROM doctor WHERE deleted_at IS NULL AND specialized = '" + specialize + "'";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();
         doctor_list.getChildren().clear();
         var previousSelectedExist = false;
         while (result.next()) {
            // Create card for each doctor
            VBox card = new VBox();
            card.getStyleClass().add("shadow radius bg-white");
            var path = result.getString("image");
            if (path == null || path.isEmpty()) {
               path = ImageConfig.defaultImage;
            } else {
               path = ImageConfig.formatPathRead(path);
            }
            Image image = new Image("File:" + path);
            ImageView imageView = new ImageView(image);
            Circle circle = new Circle();
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            circle.setRadius(Math.min(imageView.getFitWidth(), imageView.getFitHeight() / 2));
            circle.setCenterX(imageView.getFitWidth() / 2);
            circle.setCenterY(imageView.getFitHeight() / 2);
            imageView.setClip(circle);
            // apply light shadow to imageview
//            imageView.setEffect(new javafx.scene.effect.DropShadow(20, javafx.scene.paint.Color.BLACK));
            Label name = new Label(result.getString("full_name"));
            name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
            Label specialized = new Label(result.getString("specialized"));
            specialized.setStyle("-fx-font-size: 12px;");
            Label mobileNumber = new Label(emptyPlaceholder(result.getString("mobile_number")));
            mobileNumber.setStyle("-fx-font-size: 12px;");
            VBox detail = new VBox();
            detail.getChildren().addAll(specialized, mobileNumber);
            detail.setSpacing(5);
            detail.setAlignment(Pos.CENTER);
            card.getChildren().addAll(imageView, name, detail);
            card.setAlignment(Pos.CENTER);
            card.setSpacing(5);
            card.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-background-radius: 20px;");
            // apply shadow effect with black color with opacity of 0.5
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(0.0);
            dropShadow.setOffsetY(0.0);
            dropShadow.setColor(Color.rgb(0, 0, 0, 0.5));
            card.setEffect(dropShadow);
            // make card 100 width
            card.setMinWidth(250);
            card.setId(result.getString("id"));
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                  var doctorCardList = doctor_list.getChildren();
                  for (var doctorCard : doctorCardList) {
                       doctorCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-background-radius: 20px;");
                  }
                  card.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090); -fx-padding: 10px; -fx-background-radius: 20px;");
                  selectedDoctorID = Integer.parseInt(card.getId());
               }
            });
            if (selectedDoctorID != null && selectedDoctorID == Integer.parseInt(card.getId())) {
                card.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090); -fx-padding: 10px; -fx-background-radius: 20px;");
                previousSelectedExist = true;
            }
            doctor_list.getChildren().add(card);
         }
         if (!previousSelectedExist) {
             selectedDoctorID = null;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public String emptyPlaceholder(String text) {
      if (text == null || text.isEmpty()) {
         return "N/A";
      } else {
         return text;
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
                          + editApp_gender.getSelectionModel().getSelectedItem() + "', mobile_number = '"
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

   public void setPatient() {
      System.out.println("RUN");
      String phone = patient_phone.getText();
      String sql = "SELECT * FROM patient WHERE mobile_number = '" + phone + "'";
      connect = database.connectDB();
      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();
         var patient = result.next();
         if (!patient) {
            alert.errorMessage("Patient not found!");
         } else {
            var path = result.getString("image");
            if (path == null || path.isEmpty()) {
               path = ImageConfig.defaultImage;
            } else {
               path = ImageConfig.formatPathRead(path);
            }
            patientVBox.getChildren().clear();
            ImageView imageView = createImageView("File:" + ImageConfig.formatPathRead(path));

            Label fullName = new Label(result.getString("full_name"));
            fullName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
            Label phoneNumber = new Label("Phone: " + result.getString("mobile_number"));
            phoneNumber.setStyle("-fx-font-size: 12px;");
            Label birthday = new Label("Birthday: " + result.getString("birthday"));
            birthday.setStyle("-fx-font-size: 12px;");
            Label gender = new Label("Gender: " + result.getString("gender"));
            gender.setStyle("-fx-font-size: 12px;");
            patientVBox.getChildren().clear();
            patientVBox.getChildren().addAll(imageView, fullName, phoneNumber, birthday, gender);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public ImageView createImageView(String path) {
      ImageView imageView = new ImageView();
      Image image = new Image(path);
      imageView.setImage(image);
      Circle circle = new Circle();
      imageView.setFitHeight(100);
      imageView.setFitWidth(100);
      circle.setRadius(Math.min(imageView.getFitWidth(), imageView.getFitHeight() / 2));
      circle.setCenterX(imageView.getFitWidth() / 2);
      circle.setCenterY(imageView.getFitHeight() / 2);
      imageView.setClip(circle);

      return imageView;
   }

   public void postInitialize() {
      showDoctorList();
//      genderList();
//      statusList();
      List<String> specializes = new ArrayList<>(Arrays.asList(Data.specialization));
      ObservableList<String> listData = FXCollections.observableList(specializes);
      editApp_specialized.setItems(listData);
      editApp_specialized.setOnAction(event -> {
            showDoctorList();
        });
      if (mode.equals("edit")) {
         displayFields();
      } else {
         patientVBox.getChildren().add(createImageView("File:" + ImageConfig.defaultImage));
      }
   }

}
