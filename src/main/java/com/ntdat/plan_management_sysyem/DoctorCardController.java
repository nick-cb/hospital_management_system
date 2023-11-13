/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.DoctorData;

/**
 * FXML Controller class
 *
 * @author thanh
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DoctorCardController implements Initializable {

   @FXML
   private Circle doctor_circle;

   @FXML
   private Label doctor_id;

   @FXML
   private Label doctor_fullName;

   @FXML
   private Label doctor_specialization;

   @FXML
   private Label doctor_email;

   private Image image;

   public void setData(DoctorData dData) {

      if (dData.getImage() != null) {
         image = new Image("File:" + dData.getImage(), 52, 52, false, true);
         doctor_circle.setFill(new ImagePattern(image));
      }

      doctor_id.setText(dData.getDoctorID());
      doctor_fullName.setText(dData.getFullName());
      doctor_specialization.setText(dData.getSpecialized());
      doctor_email.setText(dData.getEmail());
   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }

}
