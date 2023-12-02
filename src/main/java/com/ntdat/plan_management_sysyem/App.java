package com.ntdat.plan_management_sysyem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

   private static Scene scene;

   @Override
   public void start(Stage stage) throws IOException {
      scene = new Scene(loadFXML("Authentication"));
      stage.setMinWidth(340);
      stage.setMinHeight(580);
      stage.setTitle("Hospital Management System");
      stage.setScene(scene);
      stage.show();
   }

   static void setRoot(String fxml, String breadcrumb) throws IOException {
      Stage stage = new Stage();
      stage.setMinWidth(340);
      stage.setMinHeight(580);
      stage.setTitle("Hospital Management System" + (breadcrumb != null && !breadcrumb.isEmpty() ? " | " + breadcrumb : ""));
      var scene = new Scene(loadFXML(fxml));
      scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
   }

   private static Parent loadFXML(String fxml) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
      return fxmlLoader.load();
   }

   public static void main(String[] args) {
      launch();
   }

}
