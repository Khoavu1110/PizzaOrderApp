module com.example.pizzaorderproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.pizzaorderproject to javafx.fxml;
    exports com.example.pizzaorderproject;
}