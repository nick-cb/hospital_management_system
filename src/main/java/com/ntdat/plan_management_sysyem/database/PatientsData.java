/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntdat.plan_management_sysyem.database;

/**
 *
 * @author thanh
 */
import com.ntdat.plan_management_sysyem.utils.ImageConfig;

import java.sql.Date;

public class PatientsData {

   private Integer id;
   private Integer patientID;
   private String password;
   private String fullName;
   private String mobileNumber;
   private String address;
   private String image;
   private String description;
   private String diagnosis;
   private String treatment;
   private String doctor;
   private String specialized;
   private String gender;
   private Date birthday;
   private Date modifiedAt;
   private Date deletedAt;
   private String status;
   private int totalAppointment = 0;
   private Double totalSpent = 0.0;

   public PatientsData(Integer id, Integer patientID, String password, String fullName, String mobileNumber,
                       String gender, String address, String image, String description, String diagnosis, String treatment,
                       String doctor, String specialized, Date birthday, Date modifiedAt,
                       Date deletedAt, String status, int totalAppointment, Double totalSpent) {
      this.id = id;
      this.patientID = patientID;
      this.password = password;
      this.fullName = fullName;
      this.mobileNumber = mobileNumber;
      this.gender = gender;
      this.address = address;
      this.image = image;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.doctor = doctor;
      this.specialized = specialized;
      this.birthday = birthday;
      this.modifiedAt = modifiedAt;
      this.deletedAt = deletedAt;
      this.status = status;
      this.totalAppointment = totalAppointment;
      this.totalSpent = totalSpent;
   }

   public PatientsData(Integer id, Integer patientID, String fullName, String gender,
                       String mobileNumber, String address, String status, Date birthday,
                       Date modifiedAt, Date deletedAt) {
      this.id = id;
      this.patientID = patientID;
      this.fullName = fullName;
      this.gender = gender;
      this.mobileNumber = mobileNumber;
      this.address = address;
      this.status = status;
      this.birthday = birthday;
      this.modifiedAt = modifiedAt;
      this.deletedAt = deletedAt;
   }

   public PatientsData(Integer id, Integer patientID, String fullName, String gender,
            String description, String diagnosis, String treatment,
            String doctor, String image, Date birthday) {
      this.id = id;
      this.patientID = patientID;
      this.fullName = fullName;
      this.gender = gender;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.doctor = doctor;
      this.image = image;
      this.birthday = birthday;
   }

   public PatientsData(Integer id, Integer patientID, String description,
            String diagnosis, String treatment, Date birthday) {
      this.id = id;
      this.patientID = patientID;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.birthday = birthday;
   }

   public Integer getId() {
      return id;
   }

   public Integer getPatientID() {
      return patientID;
   }

   public String getPassword() {
      return password;
   }

   public String getFullName() {
      return fullName;
   }

   public String getGender() {
      return gender;
   }
   
   public String getDescription() {
      return description;
   }
   
   public String getDiagnosis() {
      return diagnosis;
   }

   public String getMobileNumber() {
      if (mobileNumber == null) {
         return "";
      }
      return mobileNumber;
   }

   public String getAddress() {
      if (address == null) {
         return "";
      }
      return address;
   }

   public String getImage() {
      if (image == null) {
         return ImageConfig.defaultImage;
      }
      return image;
   }

   public String getTreatment() {
      return treatment;
   }

   public String getDoctor() {
      return doctor;
   }

   public String getSpecialized() {
      return specialized;
   }

   public Date getBirthday() {
      if (birthday == null) {
         return new Date(0);
      }
      return birthday;
   }

   public Date getModifiedAt() {
      return modifiedAt;
   }

   public Date getDeletedAt() {
      return deletedAt;
   }

   public String getStatus() {
      return status;
   }

    public int getTotalAppointment() {
        return totalAppointment;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }
}
