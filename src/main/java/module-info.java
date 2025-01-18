module com.example.worssembly_osinaeo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.worssembly_osinaeo to javafx.fxml;
    exports com.example.worssembly_osinaeo;
}