package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.AppointmentData;
import com.ntdat.plan_management_sysyem.database.Data;
import com.ntdat.plan_management_sysyem.database.DoctorData;
import com.ntdat.plan_management_sysyem.database.PatientsData;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.utils.AlertMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.ntdat.plan_management_sysyem.utils.ImageConfig;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class ManagerMainFormController implements Initializable {

   @FXML
   private Circle top_profile;

   @FXML
   private Label top_username;

   @FXML
   private Label date_time;

   @FXML
   private Label current_form;

   @FXML
   private Label nav_adminID;

   @FXML
   private Label nav_username;

   @FXML
   private Button dashboard_btn;

   @FXML
   private Button doctors_btn;

   @FXML
   private Button patients_btn;

   @FXML
   private Button appointments_btn;

   @FXML
   private Button payment_btn;

   @FXML
   private Button profile_btn;

   @FXML
   private AnchorPane dashboard_form;

   @FXML
   private Label dashboard_AD;

   @FXML
   private Label dashboard_TP;

   @FXML
   private Label dashboard_AP;

   @FXML
   private Label dashboard_TA;

   @FXML
   private AreaChart<?, ?> dashboad_chart_PD;

   @FXML
   private BarChart<?, ?> dashboad_chart_DD;

   @FXML
   private TableView<DoctorData> dashboad_tableView;

   @FXML
   private TableColumn<DoctorData, String> dashboad_col_doctorID;

   @FXML
   private TableColumn<DoctorData, String> dashboad_col_name;

   @FXML
   private TableColumn<DoctorData, String> dashboad_col_specialized;

   @FXML
   private TableColumn<DoctorData, String> dashboad_col_status;

   @FXML
   private AnchorPane doctors_form;

   @FXML
   private TableView<DoctorData> doctors_tableView;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_doctorID;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_name;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_gender;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_contactNumber;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_email;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_specialization;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_address;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_status;

   @FXML
   private TableColumn<DoctorData, Double> doctors_col_salary;

   @FXML
   private TableColumn<DoctorData, String> doctors_col_action;

   @FXML
   private AnchorPane patients_form;

   @FXML
   private TableView<PatientsData> patients_tableView;

   @FXML
   private TableColumn<PatientsData, String> patients_col_patientID;

   @FXML
   private TableColumn<PatientsData, String> patients_col_name;

   @FXML
   private TableColumn<PatientsData, String> patients_col_gender;

   @FXML
   private TableColumn<PatientsData, String> patients_col_contactNumber;

   @FXML
   private TableColumn<PatientsData, String> patients_col_description;

   @FXML
   private TableColumn<PatientsData, String> patients_col_date;

   @FXML
   private TableColumn<PatientsData, String> patients_col_dateModify;

   @FXML
   private TableColumn<PatientsData, String> patients_col_dateDelete;

   @FXML
   private TableColumn<PatientsData, String> patients_col_status;

   @FXML
   private TableColumn<PatientsData, String> patients_col_action;

   @FXML
   private AnchorPane appointments_form;

   @FXML
   private TableView<AppointmentData> appointments_tableView;

   @FXML
   private TableColumn<AppointmentData, String> appointments_appointmentID;

   @FXML
   private TableColumn<AppointmentData, String> appointments_name;

   @FXML
   private TableColumn<AppointmentData, String> appointments_gender;

   @FXML
   private TableColumn<AppointmentData, String> appointments_contactNumber;

   @FXML
   private TableColumn<AppointmentData, String> appointments_description;

   @FXML
   private TableColumn<AppointmentData, String> appointments_date;

   @FXML
   private TableColumn<AppointmentData, String> appointments_dateModify;

   @FXML
   private TableColumn<AppointmentData, String> appointments_dateDelete;

   @FXML
   private TableColumn<AppointmentData, String> appointments_status;

   @FXML
   private TableColumn<AppointmentData, String> appointments_action;

   @FXML
   private AnchorPane profile_form;

   @FXML
   private Circle profile_circle;

   @FXML
   private Button profile_importBtn;

   @FXML
   private Label profile_label_adminIO;

   @FXML
   private Label profile_label_name;

   @FXML
   private Label profile_label_email;

   @FXML
   private Label profile_label_dateCreated;

   @FXML
   private TextField profile_adminID;

   @FXML
   private TextField profile_username;

   @FXML
   private TextField profile_email;

   @FXML
   private ComboBox<String> profile_gender;

   @FXML
   private AnchorPane payment_form;

   @FXML
   private TableView<PatientsData> payment_tableView;

   @FXML
   private TableColumn<PatientsData, String> payment_col_patientID;

   @FXML
   private TableColumn<PatientsData, String> payment_col_name;

   @FXML
   private TableColumn<PatientsData, String> payment_col_gender;

   @FXML
   private TableColumn<PatientsData, String> payment_col_diagnosis;

   @FXML
   private TableColumn<PatientsData, String> payment_col_doctor;

   @FXML
   private TableColumn<PatientsData, String> payment_col_date;

   @FXML
   private Circle payment_circle;

   @FXML
   private Label payment_patientID;

   @FXML
   private Label payment_name;

   @FXML
   private Label payment_doctor;

   @FXML
   private Label payment_date;

   @FXML
   private Button logout_btn;

   @FXML
   private BarChart dashboard_revenue_chart;

   @FXML
   private HBox dashboard_timepicker_container;
   private MonthYearPicker fromMonthYearPicker;
   private MonthYearPicker toMonthYearPicker;

   @FXML
   private void switchTo(String fxml, String breadcrumb) throws IOException {
      App.setRoot(fxml, breadcrumb);
   }

//    DATABASE TOOLS
   private Connection connect;
   private PreparedStatement prepare;
   private Statement statement;
   private ResultSet result;

   private AlertMessage alert = new AlertMessage();

   private Image image;

   public void dashboardAD() {

      String sql = "SELECT COUNT(id) FROM doctor WHERE status = 'Active' AND deleted_at IS NULL";

      connect = database.connectDB();

      int tempAD = 0;
      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            tempAD = result.getInt("COUNT(id)");
         }
         dashboard_AD.setText(String.valueOf(tempAD));

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void dashboardTP() {

      String sql = "SELECT COUNT(id) FROM patient WHERE deleted_at IS NULL";

      connect = database.connectDB();

      int tempTP = 0;
      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            tempTP = result.getInt("COUNT(id)");
         }
         dashboard_TP.setText(String.valueOf(tempTP));

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void dashboardAP() {

      String sql = "SELECT COUNT(id) FROM patient WHERE deleted_at IS NULL AND status = 'Active'";

      connect = database.connectDB();

      int tempAP = 0;
      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            tempAP = result.getInt("COUNT(id)");
         }
         dashboard_AP.setText(String.valueOf(tempAP));

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void dashboardTA() {

      String sql = "SELECT COUNT(id) FROM appointment WHERE deleted_at IS NULL";

      connect = database.connectDB();

      int tempTA = 0;
      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            tempTA = result.getInt("COUNT(id)");
         }
         dashboard_TA.setText(String.valueOf(tempTA));

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public ObservableList<DoctorData> dashboardGetDoctorData() {

      ObservableList<DoctorData> listData = FXCollections.observableArrayList();
      String sql = "SELECT * FROM doctor WHERE deleted_at IS NULL";

      connect = database.connectDB();

      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         DoctorData dData;

         while (result.next()) {
//                DoctorData(String doctorID, String fullName, String specialized, String status)
            dData = new DoctorData(result.getString("code"),
                    result.getString("full_name"), result.getString("specialized"),
                    result.getString("status"));

            listData.add(dData);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      return listData;
   }

   public ObservableList<DoctorData> dashboardGetDoctorListData;

   public void dashboardGetDoctorDisplayData() {
      dashboardGetDoctorListData = dashboardGetDoctorData();

      dashboad_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
      dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
      dashboad_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialized"));
      dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

      dashboad_tableView.setItems(dashboardGetDoctorListData);
      dashboad_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

   }

   public void dashboardPatientDataChart() {
      dashboad_chart_PD.getData().clear();

      String selectData = "SELECT created_at, COUNT(id) FROM patient WHERE deleted_at IS NULL GROUP BY created_at ORDER BY created_at LIMIT 8";

      connect = database.connectDB();
      XYChart.Series chart = new XYChart.Series<>();

      try {
         prepare = connect.prepareStatement(selectData);
         result = prepare.executeQuery();

         while (result.next()) {
            chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
         }

         dashboad_chart_PD.getData().add(chart);

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void dashboardDoctorDataChart() {
      dashboad_chart_DD.getData().clear();

      String selectData = "SELECT created_at, COUNT(id) FROM doctor WHERE deleted_at IS NULL GROUP BY created_at ORDER BY created_at LIMIT 6";

      connect = database.connectDB();
      XYChart.Series chart = new XYChart.Series<>();

      try {
         prepare = connect.prepareStatement(selectData);
         result = prepare.executeQuery();

         while (result.next()) {
            chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
         }

         dashboad_chart_DD.getData().add(chart);

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void dashboardStackBarChart() {
      var xAxis = dashboard_revenue_chart.getXAxis();
      var yAxis = dashboard_revenue_chart.getYAxis();
      xAxis.setAnimated(false);
      dashboard_revenue_chart.getData().clear();

      var series1 = new XYChart.Series<String, Number>();
      var series2 = new XYChart.Series<String, Number>();
      var series3 = new XYChart.Series<String, Number>();

      xAxis.setLabel("Month");
      yAxis.setLabel("VNĐ");

      series1.setName("Revenue");
      series2.setName("Cost");
      series3.setName("Profit");

      var from = fromMonthYearPicker.getValue().atDay(1);
      var different = ChronoUnit.MONTHS.between(fromMonthYearPicker.getValue(), toMonthYearPicker.getValue());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

      for(int i = 0; i <= different; i++) {
         var thisMonth = from.plusMonths(i);
         var thisMonthFormatted = thisMonth.format(formatter);
         var revenue = (double) 0;
         var cost = (double) 0;

         connect = database.connectDB();
         String paymentQuery = "SELECT SUM(total_price) FROM payment WHERE paid_at BETWEEN ? AND ?";
         String costQuery = "SELECT SUM(salary) FROM doctor";
         try {
             assert connect != null;
             prepare = connect.prepareStatement(paymentQuery);
             prepare.setDate(1, java.sql.Date.valueOf(thisMonth.withDayOfMonth(1)));
             prepare.setDate(2, java.sql.Date.valueOf(thisMonth.with(TemporalAdjusters.lastDayOfMonth())));
             ResultSet result = prepare.executeQuery();
             if (result.next()) {
                 series1.getData().add(new XYChart.Data<>(thisMonthFormatted, result.getDouble(1)));
                 revenue = result.getDouble(1);
             }
         } catch (Exception e) {
            e.printStackTrace();
         }

         try {
             assert connect != null;
             prepare = connect.prepareStatement(costQuery);
             ResultSet result = prepare.executeQuery();
             if (result.next()) {
                 series2.getData().add(new XYChart.Data<>(thisMonthFormatted, result.getDouble(1)));
                    cost = result.getDouble(1);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

         series3.getData().add(new XYChart.Data<>(thisMonthFormatted, revenue - cost));
      }

      dashboard_revenue_chart.getData().addAll(series1, series2, series3);
   }

   public void dashboardRevenueTimePicker() {
      fromMonthYearPicker = new MonthYearPicker();
      fromMonthYearPicker.setValue(YearMonth.of(LocalDate.now().getYear(), 1));
      toMonthYearPicker = new MonthYearPicker();
      toMonthYearPicker.setValue(fromMonthYearPicker.getValue().plusMonths(6));

      fromMonthYearPicker.setMax(YearMonth.from(LocalDate.now()));
      fromMonthYearPicker.setMax(YearMonth.from(toMonthYearPicker.getValue().atDay(1).minusMonths(1)));
      fromMonthYearPicker.setOrder(Order.DESC);
      fromMonthYearPicker.addEventHandler(MonthYearPicker.USER_SELECTED_EVENT, (Event event) -> {
         var newValue = fromMonthYearPicker.getValue();
         var nextMonth = YearMonth.from(newValue.atDay(1).plusMonths(1));
         toMonthYearPicker.setMin(nextMonth);
         if (toMonthYearPicker.getValue().compareTo(newValue) < 0) {
            toMonthYearPicker.setValue(nextMonth);
         }
         if (ChronoUnit.MONTHS.between(newValue, toMonthYearPicker.getValue()) > 12) {
            toMonthYearPicker.setValue(newValue.plusMonths(12));
         }
         dashboardStackBarChart();
      });
      fromMonthYearPicker.showingProperty().addListener((observable, oldValue, newValue) -> {
         if (newValue) {
            fromMonthYearPicker.scrollTo();
         }
      });
      HBox fromHbox = new HBox(new Label("From:"), fromMonthYearPicker);
      fromHbox.setSpacing(15);
      fromHbox.setAlignment(Pos.CENTER_LEFT);

      toMonthYearPicker.setMax(YearMonth.from(LocalDate.now()));
      toMonthYearPicker.setMin(YearMonth.from(fromMonthYearPicker.getValue().atDay(1).plusMonths(1)));
      toMonthYearPicker.setOrder(Order.DESC);
      toMonthYearPicker.addEventHandler(MonthYearPicker.USER_SELECTED_EVENT, (Event event) -> {
         var newValue = toMonthYearPicker.getValue();
         var previousMonth = YearMonth.from(newValue.atDay(1).minusMonths(1));
         fromMonthYearPicker.setMax(previousMonth);
         if (fromMonthYearPicker.getValue().compareTo(newValue) > 0) {
            fromMonthYearPicker.setValue(previousMonth);
         }
         if (ChronoUnit.MONTHS.between(fromMonthYearPicker.getValue(), newValue) > 12) {
             fromMonthYearPicker.setValue(newValue.minusMonths(12));
         }
         dashboardStackBarChart();
      });

      HBox toHbox = new HBox(new Label("To:"), toMonthYearPicker);
      toHbox.setSpacing(15);
      toHbox.setAlignment(Pos.CENTER_LEFT);

      dashboard_timepicker_container.getChildren().addAll(fromHbox, toHbox);
   }

   public ObservableList<DoctorData> doctorGetData() {
      ObservableList<DoctorData> listData = FXCollections.observableArrayList();

      String sql = "SELECT * FROM doctor";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();
         DoctorData dData;
         while (result.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
            dData = new DoctorData(result.getInt("id"), result.getString("code"),
                    result.getString("password"), result.getString("full_name"),
                    result.getString("email"), result.getString("gender"),
                    result.getLong("moblie_number"), result.getString("specialized"),
                    result.getString("address"), result.getString("image"),
                    result.getDate("created_at"), result.getDate("modified_at"),
                    result.getDate("deleted_at"), result.getString("status"),
                    result.getDouble("salary"));
            listData.add(dData);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return listData;
   }

   private ObservableList<DoctorData> doctorListData;

   public void doctorDisplayData() {
      doctorListData = doctorGetData();

      doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
      doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
//      doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
      doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
      doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialized"));
      doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
      doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
      doctors_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

      doctors_tableView.setItems(doctorListData);
      doctors_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
   }

   public void doctorActionButton() {

      connect = database.connectDB();
      doctorListData = doctorGetData();

      Callback<TableColumn<DoctorData, String>, TableCell<DoctorData, String>> cellFactory = (TableColumn<DoctorData, String> param) -> {
         final TableCell<DoctorData, String> cell = new TableCell<DoctorData, String>() {
            public void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);

               if (empty) {
                  setGraphic(null);
                  setText(null);
               } else {
                  Button editButton = new Button("Edit");
                  Button removeButton = new Button("Delete");

                  editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  editButton.setOnAction((ActionEvent event) -> {
                     try {

                        DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                        int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                        if ((num - 1) < -1) {
                           alert.errorMessage("Please select item first");
                           return;
                        }

                        Data.temp_doctorID = pData.getDoctorID();
                        Data.temp_doctorName = pData.getFullName();
                        Data.temp_doctorEmail = pData.getEmail();
                        Data.temp_doctorPassword = pData.getPassword();
                        Data.temp_doctorSpecialized = pData.getSpecialized();
                        Data.temp_doctorGender = pData.getGender();
                        Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                        Data.temp_doctorAddress = pData.getAddress();
                        Data.temp_doctorStatus = pData.getStatus();
                        Data.temp_doctorImagePath = pData.getImage();
                        Data.temp_doctorSalary = pData.getSalary();

                        // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                        Data.edit_doctor_mode = "edit";
                        Parent root = FXMLLoader.load(getClass().getResource("EditDoctorForm.fxml"));
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                           @Override
                           public void handle(WindowEvent windowEvent) {
                              System.out.println("Closed");
                              doctorListData = doctorGetData();
                              doctors_tableView.setItems(doctorListData);
                           }
                        });
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  removeButton.setOnAction((ActionEvent event) -> {
                     DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                     int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                     if ((num - 1) < -1) {
                        alert.errorMessage("Please select item first");
                        return;
                     }

                     String deleteData = "UPDATE doctor SET deleted_at = ? WHERE id = '"
                             + pData.getDoctorID() + "'";

                     try {
                        if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                           prepare = connect.prepareStatement(deleteData);
                           Date date = new Date();
                           java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                           prepare.setString(1, String.valueOf(sqlDate));
                           prepare.executeUpdate();

                           doctorGetData();
                           alert.successMessage("Deleted Successfully!");

                        }
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  HBox manageBtn = new HBox(editButton, removeButton);
                  manageBtn.setAlignment(Pos.CENTER);
                  manageBtn.setSpacing(5);
                  setGraphic(manageBtn);
                  setText(null);
               }
            }
         };
         doctorDisplayData();
         return cell;
      };

      doctors_col_action.setCellFactory(cellFactory);
      doctors_tableView.setItems(doctorListData);

   }

   public void doctorSalaryCell() {
      doctors_col_salary.setCellFactory(new Callback<TableColumn<DoctorData, Double>, TableCell<DoctorData, Double>>() {
         @Override
         public TableCell<DoctorData, Double> call(TableColumn<DoctorData, Double> param) {
            return new TableCell<DoctorData, Double>() {
               @Override
               protected void updateItem(Double item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                     setText(null);
                  } else {
                     setText(NumberFormat.getNumberInstance().format(item));
                  }
               }
            };
         }
      });
   }

   public ObservableList<PatientsData> patientGetData() {

      ObservableList<PatientsData> listData = FXCollections.observableArrayList();

      String sql = "SELECT * FROM patient";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         PatientsData pData;

         while (result.next()) {
//                PatientsData(Integer id, Integer patientID, String password, String fullName, Long mobileNumber
//            , String address, String image, String description, String diagnosis, String treatment
//            , String doctor, String specialized, Date date, Date dateModify
//            , Date dateDelete, String status)
            pData = new PatientsData(result.getInt("id"), result.getInt("patient_id"),
                    result.getString("password"), result.getString("full_name"),
                    result.getLong("moblie_number"), result.getString("gender"),
                    result.getString("address"),
                    result.getString("image"), result.getString("description"),
                    result.getString("diagnosis"),
                    result.getString("treatment"), result.getString("doctor"),
                    result.getString("specialized"), result.getDate("created_at"),
                    result.getDate("date_modify"), result.getDate("date_delete"),
                    result.getString("status"));

            listData.add(pData);
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      return listData;
   }

   public ObservableList<PatientsData> patientListData;

   public void patientDisplayData() {
      patientListData = patientGetData();

      patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
      patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
      patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
      patients_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
      patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
      patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
      patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
      patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

      patients_tableView.setItems(patientListData);
      patients_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
   }

   public void patientActionButton() {

      connect = database.connectDB();
      patientListData = patientGetData();

      Callback<TableColumn<PatientsData, String>, TableCell<PatientsData, String>> cellFactory = (TableColumn<PatientsData, String> param) -> {
         final TableCell<PatientsData, String> cell = new TableCell<PatientsData, String>() {
            public void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);

               if (empty) {
                  setGraphic(null);
                  setText(null);
               } else {
                  Button editButton = new Button("Edit");
                  Button removeButton = new Button("Delete");

                  editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  editButton.setOnAction((ActionEvent event) -> {
                     try {

                        PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                        int num = patients_tableView.getSelectionModel().getSelectedIndex();

                        if ((num - 1) < -1) {
                           alert.errorMessage("Please select item first");
                           return;
                        }

                        Data.temp_PatientID = pData.getPatientID();
                        Data.temp_address = pData.getAddress();
                        Data.temp_name = pData.getFullName();
                        Data.temp_gender = pData.getGender();
                        Data.temp_number = pData.getMobileNumber();
                        Data.temp_status = pData.getStatus();

                        // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                        Parent root = FXMLLoader.load(getClass().getResource("EditPatientForm.fxml"));
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();

                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  removeButton.setOnAction((ActionEvent event) -> {
                     PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                     int num = patients_tableView.getSelectionModel().getSelectedIndex();

                     if ((num - 1) < -1) {
                        alert.errorMessage("Please select item first");
                        return;
                     }

                     String deleteData = "UPDATE patient SET deleted_at = ? WHERE id = '"
                             + pData.getPatientID() + "'";

                     try {
                        if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getPatientID() + "?")) {
                           prepare = connect.prepareStatement(deleteData);
                           Date date = new Date();
                           java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                           prepare.setString(1, String.valueOf(sqlDate));
                           prepare.executeUpdate();

                           doctorGetData();
                           alert.successMessage("Deleted Successfully!");

                        }
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  HBox manageBtn = new HBox(editButton, removeButton);
                  manageBtn.setAlignment(Pos.CENTER);
                  manageBtn.setSpacing(5);
                  setGraphic(manageBtn);
                  setText(null);
               }
            }
         };
         doctorDisplayData();
         return cell;
      };

      patients_col_action.setCellFactory(cellFactory);
      patients_tableView.setItems(patientListData);

   }

   public void openCreateForm() throws IOException {
      Data.edit_doctor_mode = "add";
      Parent root = FXMLLoader.load(getClass().getResource("EditDoctorForm.fxml"));
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();
      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         @Override
         public void handle(WindowEvent windowEvent) {
            doctorListData = doctorGetData();
            doctors_tableView.setItems(doctorListData);
         }
      });
   }
   public ObservableList<AppointmentData> appointmentGetData() {

      ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

      String sql = "SELECT * FROM appointment";

      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         AppointmentData aData;
         while (result.next()) {
//            AppointmentData(Integer id, Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule)
            aData = new AppointmentData(result.getInt("id"), result.getInt("appointment_id"),
                    result.getString("name"), result.getString("gender"), result.getLong("moblie_number"),
                    result.getString("description"), result.getString("diagnosis"),
                    result.getString("treatment"), result.getString("address"),
                    result.getString("doctor"), result.getString("specialized"),
                    result.getDate("created_at"), result.getDate("date_modify"),
                    result.getDate("date_delete"), result.getString("status"),
                    result.getDate("schedule"));
            listData.add(aData);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return listData;
   }

   private ObservableList<AppointmentData> appointmentListData;

   public void appointmentDisplayData() {
      appointmentListData = appointmentGetData();

      appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
      appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
      appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
      appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
      appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
      appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
      appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
      appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));

      appointments_tableView.setItems(appointmentListData);
      appointments_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
   }

   public void appointmentActionButton() {

      connect = database.connectDB();
      appointmentListData = appointmentGetData();

      Callback<TableColumn<AppointmentData, String>, TableCell<AppointmentData, String>> cellFactory = (TableColumn<AppointmentData, String> param) -> {
         final TableCell<AppointmentData, String> cell = new TableCell<AppointmentData, String>() {
            public void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);

               if (empty) {
                  setGraphic(null);
                  setText(null);
               } else {
                  Button editButton = new Button("Edit");
                  Button removeButton = new Button("Delete");

                  editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                          + "    -fx-cursor: hand;\n"
                          + "    -fx-text-fill: #fff;\n"
                          + "    -fx-font-size: 14px;\n"
                          + "    -fx-font-family: Arial;");

                  editButton.setOnAction((ActionEvent event) -> {
                     try {

                        AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                        if ((num - 1) < -1) {
                           alert.errorMessage("Please select item first");
                           return;
                        }

                        Data.temp_appID = String.valueOf(aData.getAppointmentID());
                        Data.temp_appName = aData.getName();
                        Data.temp_appGender = aData.getGender();
                        Data.temp_appAddress = aData.getAddress();
                        Data.temp_appDescription = aData.getDescription();
                        Data.temp_appDiagnosis = aData.getDiagnosis();
                        Data.temp_appTreatment = aData.getTreatment();
                        Data.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                        Data.temp_appDoctor = aData.getDoctorID();
                        Data.temp_appSpecialized = aData.getSpecialized();
                        Data.temp_appStatus = aData.getStatus();

                        // NOW LETS CREATE FXML FOR EDIT APPOINTMENT FORM
                        Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentForm.fxml"));
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();

                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  removeButton.setOnAction((ActionEvent event) -> {
                     AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                     int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                     if ((num - 1) < -1) {
                        alert.errorMessage("Please select item first");
                        return;
                     }

                     String deleteData = "UPDATE appointment SET deleted_at = ? WHERE id = '"
                             + aData.getAppointmentID() + "'";

                     try {
                        if (alert.confirmationMessage("Are you sure you want to delete Appointment ID: " + aData.getAppointmentID() + "?")) {
                           prepare = connect.prepareStatement(deleteData);
                           Date date = new Date();
                           java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                           prepare.setString(1, String.valueOf(sqlDate));
                           prepare.executeUpdate();

                           doctorGetData();
                           alert.successMessage("Deleted Successfully!");

                        }
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  });

                  HBox manageBtn = new HBox(editButton, removeButton);
                  manageBtn.setAlignment(Pos.CENTER);
                  manageBtn.setSpacing(5);
                  setGraphic(manageBtn);
                  setText(null);
               }
            }
         };
         doctorDisplayData();
         return cell;
      };

      appointments_action.setCellFactory(cellFactory);
      appointments_tableView.setItems(appointmentListData);
   }

   public ObservableList<PatientsData> paymentGetData() {

      ObservableList<PatientsData> listData = FXCollections.observableArrayList();

      String sql = "SELECT * FROM patient WHERE deleted_at IS NULL";
      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         PatientsData pData;
         while (result.next()) {
//                (Integer id, Integer patientID, String fullName, String gender
//            , String description, String diagnosis, String treatment
//            , String doctor, String image, Date date)
            pData = new PatientsData(result.getInt("id"),
                    result.getInt("patient_id"), result.getString("full_name"),
                    result.getString("gender"), result.getString("description"),
                    result.getString("diagnosis"), result.getString("treatment"),
                    result.getString("doctor"), result.getString("image"), result.getDate("created_at"));

            listData.add(pData);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return listData;
   }

   public ObservableList<PatientsData> paymentListData;

   public void paymentDisplayData() {
/*      paymentListData = paymentGetData();

      payment_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
      payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
      payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
      payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
      payment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
      payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

      payment_tableView.setItems(paymentListData);
      payment_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);*/
   }

   public void paymentSelectItems() {

      PatientsData pData = payment_tableView.getSelectionModel().getSelectedItem();
      int num = payment_tableView.getSelectionModel().getSelectedIndex();

      if ((num - 1) < -1) {
         return;
      }
      if (pData.getImage() != null) {
         String path = "File:" + pData.getImage();
         image = new Image(path, 144, 105, false, true);
         payment_circle.setFill(new ImagePattern(image));

         Data.temp_path = pData.getImage();
      }

      Data.temp_PatientID = pData.getPatientID();
      Data.temp_name = pData.getFullName();
      Data.temp_date = String.valueOf(pData.getDate());

      payment_patientID.setText(String.valueOf(pData.getPatientID()));
      payment_name.setText(pData.getFullName());
      payment_doctor.setText(pData.getDoctor());
      payment_date.setText(String.valueOf(pData.getDate()));
   }

   public void paymentCheckOutBtn() {

      try {
         Parent root = FXMLLoader.load(getClass().getResource("CheckOutPatient.fxml"));
         Stage stage = new Stage();

         stage.setTitle("Hospital Management System | Check-Out");
         stage.setScene(new Scene(root));
         stage.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void profileUpdateBtn() {
      connect = database.connectDB();
      if (profile_adminID.getText().isEmpty()
              || profile_username.getText().isEmpty()
              || profile_email.getText().isEmpty()
              || profile_gender.getSelectionModel().getSelectedItem() == null) {
         alert.errorMessage("Please fill all blank fields");
      } else {
         if (Data.path == null || "".equals(Data.path)) {
            String updateData = "UPDATE manager SET username = ?, email = ?, gender = ? "
                    + "WHERE id = " + Data.manager_id;

            try {
               prepare = connect.prepareStatement(updateData);
               prepare.setString(1, profile_username.getText());
               prepare.setString(2, profile_email.getText());
               prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());

               prepare.executeUpdate();

               profileDisplayInfo();

               alert.successMessage("Updated Successfully");
            } catch (Exception e) {
               e.printStackTrace();
            }

         } else {
            String updateData = "UPDATE manager SET username = ?, email = ?, image = ?, gender = ? "
                    + "WHERE id = " + Data.manager_id;
            try {
               prepare = connect.prepareStatement(updateData);
               prepare.setString(1, profile_username.getText());
               prepare.setString(2, profile_email.getText());

               String path = Data.path;
               path = path.replace("\\", "\\\\");
               Path transfer = Paths.get(path);
               var copyPath = ImageConfig.formatCopy(path);
               Path copy = Paths.get(copyPath);
               Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
               var newPath = ImageConfig.formatPathWrite(copy.toAbsolutePath().toString());
               prepare.setString(3, newPath);
               prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());

               prepare.executeUpdate();
               profileDisplayInfo();
               profileDisplayImages();
               alert.successMessage("Updated Successfully!");
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   public void profileDisplayImages() {

      String selectData = "SELECT * FROM manager WHERE id = " + Data.manager_id;
      connect = database.connectDB();

      String tempPath1 = "";
      String tempPath2 = "";
      try {
         prepare = connect.prepareStatement(selectData);
         result = prepare.executeQuery();

         if (result.next()) {
            tempPath1 = "File:" + ImageConfig.formatPathRead(result.getString("image"));
            tempPath2 = "File:" + ImageConfig.formatPathRead(result.getString("image"));

            if (result.getString("image") != null) {
               image = new Image(tempPath1, 1012, 22, false, true);
               top_profile.setFill(new ImagePattern(image));

               image = new Image(tempPath2, 137, 95, false, true);
               profile_circle.setFill(new ImagePattern(image));
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void profileInsertImage() {

      FileChooser open = new FileChooser();
      open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.jpeg", "*.png"));

      File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

      if (file != null) {
         Data.path = file.getAbsolutePath();

         image = new Image(file.toURI().toString(), 137, 95, false, true);
         profile_circle.setFill(new ImagePattern(image));
      }
   }

   public void profileDisplayInfo() {

      String sql = "SELECT * FROM manager WHERE id = " + Data.manager_id;
      System.out.println(Data.manager_id);
      connect = database.connectDB();

      try {
         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            profile_adminID.setText(result.getString("id"));
            profile_username.setText(result.getString("username"));
            profile_email.setText(result.getString("email"));
            profile_gender.getSelectionModel().select(result.getString("gender"));

            profile_label_adminIO.setText(result.getString("id"));
            profile_label_name.setText(result.getString("username"));
            profile_label_email.setText(result.getString("email"));
            profile_label_dateCreated.setText(result.getString("birthday"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void profileGenderList() {
      List<String> listG = new ArrayList<>();

      for (String data : Data.gender) {
         listG.add(data);
      }

      ObservableList listData = FXCollections.observableArrayList(listG);
      profile_gender.setItems(listData);
   }
   
   // MAKE SURE THAT THE ID OF EVERY COMPONENTS TO THE OTHER IS DIFFERENT
   public void switchForm(ActionEvent event) {

      if (event.getSource() == dashboard_btn) {
         dashboard_form.setVisible(true);
         doctors_form.setVisible(false);
         patients_form.setVisible(false);
         appointments_form.setVisible(false);
         payment_form.setVisible(false);
         profile_form.setVisible(false);

         dashboardAD();
         dashboardTP();
         dashboardAP();
         dashboardTA();
//         dashboardGetDoctorDisplayData();

         current_form.setText("Dashboard Form");
      } else if (event.getSource() == doctors_btn) {
         dashboard_form.setVisible(false);
         doctors_form.setVisible(true);
         patients_form.setVisible(false);
         appointments_form.setVisible(false);
         payment_form.setVisible(false);
         profile_form.setVisible(false);

         // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
         doctorDisplayData();
         doctorActionButton();
         doctorSalaryCell();

         current_form.setText("Doctor's Form");
      } else if (event.getSource() == patients_btn) {
         dashboard_form.setVisible(false);
         doctors_form.setVisible(false);
         patients_form.setVisible(true);
         appointments_form.setVisible(false);
         payment_form.setVisible(false);
         profile_form.setVisible(false);

//       TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
         patientDisplayData();
         patientActionButton();
         current_form.setText("Patient's Form");
      } else if (event.getSource() == appointments_btn) {
         dashboard_form.setVisible(false);
         doctors_form.setVisible(false);
         patients_form.setVisible(false);
         appointments_form.setVisible(true);
         payment_form.setVisible(false);
         profile_form.setVisible(false);

         // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
         appointmentDisplayData();
         current_form.setText("Appointment's Form");
      } else if (event.getSource() == payment_btn) {
         dashboard_form.setVisible(false);
         doctors_form.setVisible(false);
         patients_form.setVisible(false);
         appointments_form.setVisible(false);
         payment_form.setVisible(true);
         profile_form.setVisible(false);

         paymentDisplayData();
         current_form.setText("Payment Form");
      } else if (event.getSource() == profile_btn) {
         dashboard_form.setVisible(false);
         doctors_form.setVisible(false);
         patients_form.setVisible(false);
         appointments_form.setVisible(false);
         payment_form.setVisible(false);
         profile_form.setVisible(true);

         profileGenderList();
         profileDisplayInfo();
         profileDisplayImages();

         current_form.setText("Profile Form");
      }
   }

   public void displayAdminIDUsername() {

      String sql = "SELECT * FROM manager WHERE username = '"
              + Data.manager_username + "'";

      connect = database.connectDB();

      try {

         prepare = connect.prepareStatement(sql);
         result = prepare.executeQuery();

         if (result.next()) {
            nav_adminID.setText(result.getString("id"));
            String tempUsername = result.getString("username");
            tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
            nav_username.setText(tempUsername);
            top_username.setText(tempUsername);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void logoutBtn() {

      try {
         if (alert.confirmationMessage("Are you sure you want to logout?")) {
            Data.manager_id = Integer.parseInt("0");
            Data.manager_username = "";
            
            switchTo("Authentication", "");
            // TO HIDE YOUR MAIN FORM
            logout_btn.getScene().getWindow().hide();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void runTime() {

      new Thread() {

         public void run() {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            while (true) {
               try {

                  Thread.sleep(1000); // 1000 ms = 1s

               } catch (InterruptedException e) {
                  e.printStackTrace();
               }

               Platform.runLater(() -> {
                  date_time.setText(format.format(new Date()));
               });
            }
         }
      }.start();
   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      runTime();
      displayAdminIDUsername();

      // DASHBOARD
      dashboardAD();
      dashboardTP();
      dashboardAP();
      dashboardTA();
      dashboardRevenueTimePicker();
      dashboardStackBarChart();
   /*      dashboardGetDoctorDisplayData();
      dashboardPatientDataChart();
      dashboardDoctorDataChart();*/

      // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
      doctorDisplayData();
      doctorActionButton();
      doctorSalaryCell();

      // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
      patientDisplayData();
      patientActionButton();

      // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
      appointmentDisplayData();
      appointmentActionButton();

      // TO DISPLAY IMMEDIATELY THE DATA OF PAYMENT IN TABLEVIEW
      paymentDisplayData();
      profileGenderList();
      profileDisplayInfo();
      profileDisplayImages();
   }

}
