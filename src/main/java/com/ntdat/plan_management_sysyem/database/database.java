/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntdat.plan_management_sysyem.database;

/**
 *
 * @author thanh
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class database {

   public static Connection connectDB() {

      try {

         Class.forName("com.mysql.cj.jdbc.Driver");

         Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital_system", "root", "");
         // root is the default username while "" or empty or null is the default password
         return connect;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
}
