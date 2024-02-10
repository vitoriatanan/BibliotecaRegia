module Biblioteca.Regia {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.junit.jupiter.api;

    opens BibliotecaRegia.Controller to javafx.fxml, javafx.graphics;
    exports BibliotecaRegia;
    exports BibliotecaRegia.Controller;
}