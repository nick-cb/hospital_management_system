module com.ntdat.plan_management_sysyem {
   requires javafx.controls;
   requires javafx.fxml;
   requires java.sql;
   requires org.kordamp.ikonli.core;
   requires org.kordamp.ikonli.javafx;
   requires org.kordamp.ikonli.fontawesome;
   requires java.base;

   opens com.ntdat.plan_management_sysyem to javafx.fxml;
   opens com.ntdat.plan_management_sysyem.database to javafx.base;

   exports com.ntdat.plan_management_sysyem;
}
