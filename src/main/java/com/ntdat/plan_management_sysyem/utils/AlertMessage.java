/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntdat.plan_management_sysyem.utils;

/**
 *
 * @author thanh
 */
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertMessage {

   private Alert alert;

   public void errorMessage(String message) {
      alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Messaage");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }

   public void successMessage(String message) {
      alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Information Message");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }

   public boolean confirmationMessage(String message) {

      alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmation Message");
      alert.setHeaderText(null);
      alert.setContentText(message);
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get().equals(ButtonType.OK)) {
         return true;
      } else {
         return false;
      }

   }
}
