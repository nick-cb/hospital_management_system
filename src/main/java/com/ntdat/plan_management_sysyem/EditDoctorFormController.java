/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ntdat.plan_management_sysyem;

import com.ntdat.plan_management_sysyem.database.Data;
import com.ntdat.plan_management_sysyem.database.database;
import com.ntdat.plan_management_sysyem.utils.AlertMessage;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import com.ntdat.plan_management_sysyem.utils.AppConfig;
import com.ntdat.plan_management_sysyem.utils.ImageConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class EditDoctorFormController implements Initializable {

    @FXML
    private Label editDoctor_doctorIDLabel;

    @FXML
    private TextField editDoctor_doctorID;

    @FXML
    private TextField editDoctor_fullName;

    @FXML
    private TextField editDoctor_email;

    @FXML
    private TextField editDoctor_password;

    @FXML
    private ComboBox<String> editDoctor_specialized;

    @FXML
    private ComboBox<String> editDoctor_gender;

    @FXML
    private TextField editDoctor_mobileNumber;

    @FXML
    private ImageView editDoctor_imageView;

    @FXML
    private Button editDoctor_importBtn;

    @FXML
    private TextArea editDoctor_address;

    @FXML
    private ComboBox<String> editDoctor_status;

    @FXML
    private Button editDoctor_updateBtn;

    @FXML
    private Button editDoctor_cancelBtn;

    @FXML
    private TextField editDoctor_salary;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void importBtn() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*.jpg", "*.png", "*.jpeg"));

        File file = open.showOpenDialog(editDoctor_importBtn.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 112, 121, false, true);
            editDoctor_imageView.setImage(image);
        }

    }

    public void displayDoctorData() {

        String sql = "SELECT * FROM doctor WHERE id = '"
                + editDoctor_doctorID.getText() + "'";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                editDoctor_fullName.setText(result.getString("full_name"));
                editDoctor_email.setText(result.getString("email"));
                editDoctor_password.setText(result.getString("password"));
                editDoctor_specialized.getSelectionModel().select(result.getString("specialized"));
                editDoctor_gender.getSelectionModel().select(result.getString("gender"));
                editDoctor_mobileNumber.setText(result.getString("moblie_number"));
                editDoctor_address.setText(result.getString("address"));
                editDoctor_status.getSelectionModel().select(result.getString("status"));

                image = new Image("File:" + result.getString("image"), 112, 121, false, true);
                editDoctor_imageView.setImage(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTextFieldNullish(TextInputControl textField) {
        return Optional.ofNullable(textField.getText()).orElse("").isEmpty();
    }

    public void editDoctor() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if (Data.path == null || "".equals(Data.path)) {
            String updateData = "UPDATE doctor SET full_name = '"
                    + editDoctor_fullName.getText() + "', email = '"
                    + editDoctor_email.getText() + "', password = '"
                    + editDoctor_password.getText() + "', specialized = '"
                    + editDoctor_specialized.getSelectionModel().getSelectedItem() + "', gender = '"
                    + editDoctor_gender.getSelectionModel().getSelectedItem() + "', moblie_number = '"
                    + editDoctor_mobileNumber.getText() + "', address = '"
                    + editDoctor_address.getText() + "', status = '"
                    + editDoctor_status.getSelectionModel().getSelectedItem() + "', modified_at = '"
                    + String.valueOf(sqlDate) + "' " + ", salary = "
                    + Double.parseDouble(editDoctor_salary.getText())
                    + " WHERE code = '" + editDoctor_doctorID.getText() + "'";
            try {
                if (alert.confirmationMessage("Are you sure you want to Update Doctor ID: " + editDoctor_doctorID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();
                    alert.successMessage("Update doctor successfully!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (alert.confirmationMessage("Are you sure you want to Update Doctor ID: "
                        + editDoctor_doctorID.getText() + "?")) {
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get(ImageConfig.formatCopy(path));

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                    String insertImage = ImageConfig.formatPathWrite(copy.toAbsolutePath().toString());

                    String updateData = "UPDATE doctor SET full_name = '"
                            + editDoctor_fullName.getText() + "', email = '"
                            + editDoctor_email.getText() + "', password = '"
                            + editDoctor_password.getText() + "', specialized = '"
                            + editDoctor_specialized.getSelectionModel().getSelectedItem() + "', gender = '"
                            + editDoctor_gender.getSelectionModel().getSelectedItem() + "', moblie_number = '"
                            + editDoctor_mobileNumber.getText() + "', image = '"
                            + insertImage + "', address = '"
                            + editDoctor_address.getText() + "', status = '"
                            + editDoctor_status.getSelectionModel().getSelectedItem() + "' "
                            + ", salary = "
                            + Double.parseDouble(editDoctor_salary.getText()) + " "
                            + "WHERE code = '" + editDoctor_doctorID.getText() + "'";

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();
                    alert.successMessage("Update doctor successfully!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void createDoctor() {
        Date date = new Date();

        String createData = "INSERT INTO doctor (full_name, email, password, specialized, gender, moblie_number, address, status, image, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
           prepare = connect.prepareStatement(createData, Statement.RETURN_GENERATED_KEYS);
           prepare.setString(1, editDoctor_fullName.getText());
           prepare.setString(2, editDoctor_email.getText());
           prepare.setString(3, editDoctor_password.getText());
           prepare.setString(4, editDoctor_specialized.getSelectionModel().getSelectedItem());
           prepare.setString(5, editDoctor_gender.getSelectionModel().getSelectedItem());
           prepare.setString(6, editDoctor_mobileNumber.getText());
           prepare.setString(7, editDoctor_address.getText());
           prepare.setString(8, editDoctor_status.getSelectionModel().getSelectedItem());
           if (Data.path != null && !Data.path.isEmpty()) {
               Path transfer = Paths.get(Data.path);
               Path copy = Paths.get(ImageConfig.formatCopy(Data.path));
               Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
               String insertImage = ImageConfig.formatPathWrite(copy.toAbsolutePath().toString());
               prepare.setString(9, insertImage);
           } else {
               prepare.setString(9, ImageConfig.formatPathWrite(ImageConfig.defaultImage));
           }
            prepare.setDouble(10, Double.parseDouble(editDoctor_salary.getText()));
           prepare.executeUpdate();
           ResultSet result = prepare.getGeneratedKeys();
           if (result.next()) {
               int id = result.getInt(1);
               this.editDoctor_doctorID.setText(String.valueOf(id));
               Data.edit_doctor_mode = "edit";
           }
           alert.successMessage("Create doctor successfully!");
            Stage stage = (Stage) editDoctor_updateBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
           alert.errorMessage(e.getMessage());
           e.printStackTrace();
        }
    }

    public void updateBtn() {
        connect = database.connectDB();

        if (isTextFieldNullish(editDoctor_fullName)
                || isTextFieldNullish(editDoctor_email)
                || isTextFieldNullish(editDoctor_password)
                || editDoctor_specialized.getSelectionModel().getSelectedItem() == null
                || editDoctor_gender.getSelectionModel().getSelectedItem() == null
                || isTextFieldNullish(editDoctor_mobileNumber)
                || isTextFieldNullish(editDoctor_address)
                || isTextFieldNullish(editDoctor_salary)
                || editDoctor_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
            return;
        }

        if (Objects.equals(Data.edit_doctor_mode, "add")) {
            createDoctor();
        } else {
            editDoctor();
        }

        displayDoctorData();
    }

    public void cancelBtn() {
        Stage stage = (Stage) editDoctor_cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setField() {
        editDoctor_doctorID.setText(Data.temp_doctorID);
        editDoctor_fullName.setText(Data.temp_doctorName);
        editDoctor_salary.setText(new DecimalFormat("#").format(Data.temp_doctorSalary));
        editDoctor_email.setText(Data.temp_doctorEmail);
        editDoctor_password.setText(Data.temp_doctorPassword);
        editDoctor_specialized.getSelectionModel().select(Data.temp_doctorSpecialized);
        editDoctor_gender.getSelectionModel().select(Data.temp_doctorGender);
        editDoctor_mobileNumber.setText(Data.temp_doctorMobileNumber);
        editDoctor_address.setText(Data.temp_doctorName);
        editDoctor_status.getSelectionModel().select(Data.temp_doctorStatus);

        if (Data.temp_doctorImagePath != null) {
            image = new Image("File:" + ImageConfig.formatPathRead(Data.temp_doctorImagePath), 112, 121, false, true);
            editDoctor_imageView.setImage(image);
        } else {
            image = new Image("File:" + ImageConfig.formatPathRead(ImageConfig.defaultImage), 112, 121, false, true);
            editDoctor_imageView.setImage(image);
        }
    }

    public void specializationList() {
        List<String> specializationL = new ArrayList<>();

        for (String data : Data.specialization) {
            specializationL.add(data);
        }

        ObservableList listData = FXCollections.observableList(specializationL);
        editDoctor_specialized.setItems(listData);
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editDoctor_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editDoctor_status.setItems(listData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Objects.equals(Data.edit_doctor_mode, "add")) {
            this.editDoctor_doctorID.setVisible(false);
            this.editDoctor_doctorIDLabel.setVisible(false);
        }
        if (Objects.equals(Data.edit_doctor_mode, "edit")) {
            setField();
        } else {
            image = new Image("File:" + ImageConfig.formatPathRead(ImageConfig.defaultImage), 112, 121, false, true);
            editDoctor_imageView.setImage(image);
        }
        specializationList();
        genderList();
        statusList();
    }

}
