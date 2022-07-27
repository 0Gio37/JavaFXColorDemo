module fx.georges.javafxcolor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.swing;

    opens fx.georges.javafxcolor to javafx.fxml;
    exports fx.georges.javafxcolor;
    exports fx.georges.javafxcolor.controller;
    opens fx.georges.javafxcolor.controller to javafx.fxml;
}