/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntdat.plan_management_sysyem.database;

import java.sql.Date;

/*
* Data store
* TODO: Using a persistent storage instead of this
* */
public class Data {

   public static Integer manager_id;
   public static String manager_username;
   public static String path;

   public static String doctor_id;
   public static String doctor_name;

   public static Integer patient_id;

   public static String[] gender = {"Male", "Female", "Others"};

   public static String[] status = {"Active", "Inactive", "Confirm"};

   public static String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

   public static String edit_patient_mode = "edit";
   public static Integer temp_PatientID;
   public static String temp_name;
   public static String temp_gender;
   public static String temp_number;
   public static String temp_address;
   public static String temp_status;
   public static String temp_date;
   public static String temp_path;
   public static Date temp_birthday;
   public static String temp_password;

   public static String edit_doctor_mode = "add";
   public static String temp_doctorID;
   public static String temp_doctorName;
   public static String temp_doctorEmail;
   public static String temp_doctorPassword;
   public static String temp_doctorSpecialized;
   public static String temp_doctorGender;
   public static String temp_doctorMobileNumber;
   public static String temp_doctorImagePath;
   public static Double temp_doctorSalary;
   public static String temp_doctorAddress;
   public static String temp_doctorStatus;

   public static String temp_appID;
   public static String temp_appName;
   public static String temp_appGender;
   public static String temp_appMobileNumber;
   public static String temp_appAddress;
   public static String temp_appDescription;
   public static String temp_appDiagnosis;
   public static String temp_appTreatment;
   public static String temp_appDoctor;
   public static String temp_appSpecialized;
   public static String temp_appStatus;

}
