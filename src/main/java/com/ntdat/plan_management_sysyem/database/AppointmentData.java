/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntdat.plan_management_sysyem.database;

/**
 *
 * @author thanh
 */

import java.sql.Date;

public class AppointmentData {

   private Integer id;
   private Integer appointmentID;
   private Integer patientID;
   private String name;
   private String gender;
   private String description;
   private String diagnosis;
   private String treatment;
   private Long mobileNumber;
   private String address;
   private Date createdAt;
   private Date modifiedAt;
   private Date deletedAt;
   private String status;
   private Integer doctorID;
   private String specialized;
   private Date scheduledAt;
   private String doctorName;
   private String doctorPhone;
   private String doctorSpecialized;
   private String patientName;
   private String patientPhone;
   private String patientGender;
   private Date paidAt;
   private Integer totalDays;
   private Double totalPrice;

   public AppointmentData(Integer id, Integer appointmentID, String name, String gender,
                          Long mobileNumber, String description, String diagnosis, String treatment, String address,
                          Integer doctorID, String specialized,
                          Date createdAt, Date modifiedAt, Date deletedAt, String status, Date scheduledAt) {
      this.id = id;
      this.appointmentID = appointmentID;
      this.name = name;
      this.gender = gender;
      this.mobileNumber = mobileNumber;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.address = address;
      this.doctorID = doctorID;
      this.specialized = specialized;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
      this.deletedAt = deletedAt;
      this.status = status;
      this.scheduledAt = scheduledAt;

   }

   public AppointmentData(Integer appointmentID, String name, String gender,
                          Long mobileNumber, String description, String diagnosis,
                          String treatment, String address, Date createdAt, Date modifiedAt,
                          Date deletedAt, String status, Date scheduledAt, String doctorName,
                          String doctorPhone, String doctorSpecialized, String patientName,
                          String patientPhone, String patientGender, Date paidAt,
                          Integer totalDays, Double totalPrice) {

      this.appointmentID = appointmentID;
      this.name = name;
      this.gender = gender;
      this.mobileNumber = mobileNumber;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.address = address;
      this.createdAt = createdAt;
      this.modifiedAt = modifiedAt;
      this.deletedAt = deletedAt;
      this.status = status;
      this.scheduledAt = scheduledAt;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.doctorSpecialized = doctorSpecialized;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.patientGender = patientGender;
        this.paidAt = paidAt;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
   }

   public AppointmentData(Integer appointmentID, String name,
                          String description, Date scheduledAt, String status) {
      this.appointmentID = appointmentID;
      this.name = name;
      this.description = description;
      this.scheduledAt = scheduledAt;
      this.status = status;
   }

   public AppointmentData(Integer appointmentID, String description,
           String diagnosis, String treatment, Integer doctorID, Date scheduledAt) {
      this.appointmentID = appointmentID;
      this.description = description;
      this.diagnosis = diagnosis;
      this.treatment = treatment;
      this.doctorID = doctorID;
      this.scheduledAt = scheduledAt;
   }

   public Integer getId() {
      return id;
   }

   public Integer getAppointmentID() {
      return appointmentID;
   }

   public String getName() {
      return name;
   }

   public String getGender() {
      return gender;
   }

   public Long getMobileNumber() {
      return mobileNumber;
   }

   public String getDescription() {
      return description;
   }

   public String getDiagnosis() {
      return diagnosis;
   }

   public String getTreatment() {
      return treatment;
   }

   public String getAddress() {
      return address;
   }

   public Integer getDoctorID() {
      return doctorID;
   }

   public String getSpecialized() {
      return specialized;
   }

   public Date getCreatedAt() {
      return createdAt;
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

   public Date getScheduledAt() {
      return scheduledAt;
   }

   public String getDoctorName() {
       return doctorName;
   }

   public String getDoctorPhone() {
        if (doctorPhone == null) {
           return "";
        }
       return doctorPhone;
   }

   public String getDoctorSpecialized() {
      if (doctorSpecialized == null) {
         return "";
      }

      return doctorSpecialized;
   }

   public String getPatientName() {
       return patientName;
   }

   public String getPatientPhone() {
      if (patientPhone == null) {
         return "";
      }
      return patientPhone;
   }

   public String getPatientGender() {
        if (patientGender == null) {
           return "";
        }
        return patientGender;
   }

   public Date getPaidAt() {
         return paidAt;
   }

   public Integer getTotalDays() {
        return totalDays;
   }

   public Double getTotalPrice() {
         return totalPrice;
   }
}
