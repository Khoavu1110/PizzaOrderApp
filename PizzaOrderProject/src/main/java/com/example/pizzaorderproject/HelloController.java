package com.example.pizzaorderproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class HelloController {
    private Stage window;
    private Scene scene;
    private Parent root;
    private Stack<Scene> sceneStack = new Stack<>();
    private boolean isLogin = false;
    private ShoppingCart cart = new ShoppingCart();
    @FXML
    private Label welcomeText;

    //dummy text
    String email = "JohnDoe@gmail.com";
    String password = "123456";


    //**********************************************************************************************************************
    //Login button homepage: go to log in page
    public void switchToCustomerLoginScreen(ActionEvent e) throws IOException {
        root = FXMLLoader.load((getClass().getResource("loginScreen.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    //**********************************************************************************************************************
    //Sign up homepage: go to sign up button
    public void switchToCustomerSignUpPage(ActionEvent e) throws IOException {
        root = FXMLLoader.load((getClass().getResource("signupScreen.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    //**********************************************************************************************************************
    //Menu page: cart button
    public void goToCartScreen(ActionEvent e) throws IOException {
        root = FXMLLoader.load((getClass().getResource("Cart.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        sceneStack.push(scene);
        window.show();
    }


    //**********************************************************************************************************************
    //Any page: home screen button. Pretty self-explanatory
    public void homeButton(ActionEvent e) throws IOException {
        root = FXMLLoader.load((getClass().getResource("hello-view.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        sceneStack.push(scene);
        window.show();
    }


    //**********************************************************************************************************************
    //Any page: Back button
    public void backButton(ActionEvent e) throws IOException {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop();
            if (!sceneStack.isEmpty()) {
                window.setScene(sceneStack.peek());
            }
        }
    }


//**********************************************************************************************************************
    //Homepage: Log in button (with checking for credential)
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;

    public void loginButton(ActionEvent e) throws IOException {
        // Check if any of the fields are empty
        if (emailInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
            // If any of the fields are empty, show an alert to inform the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Attention");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return; // Exit the method, preventing further execution
        }
        //Checking if the information is correct
        if (!Objects.equals(emailInput.getText(), email) || !Objects.equals(passwordInput.getText(), password)) {
            // If any of the fields are empty, show an alert to inform the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Attention");
            alert.setContentText("Incorrect email or password");
            alert.showAndWait();
            return; // Exit the method, preventing further execution
        }
        root = FXMLLoader.load((getClass().getResource("pizzaMenu.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        sceneStack.push(scene);
        window.show();
    }


//**********************************************************************************************************************
    //Homepage: Sign up button (with checking for credential)
    @FXML
    private TextField firstNameInputField, lastNameInputField, emailInputField, passwordInputField;

    public void signUpButton(ActionEvent e) throws IOException {
        // Check if any of the fields are empty
        if (firstNameInputField.getText().isEmpty() || lastNameInputField.getText().isEmpty() ||
                emailInputField.getText().isEmpty() || passwordInputField.getText().isEmpty()) {
            // If any of the fields are empty, show an alert to inform the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields");
            alert.setHeaderText("Attention");
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
            return; // Exit the method, preventing further execution
        }
        // If all fields are filled, proceed with navigating to the pizza menu scene
        Customer customer = new Customer(firstNameInputField.getText(), lastNameInputField.getText(), emailInputField.getText(), passwordInputField.getText());
        cart = new ShoppingCart();
        root = FXMLLoader.load((getClass().getResource("pizzaMenu.fxml")));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        window.setScene(scene);
        sceneStack.push(scene);
        window.show();
    }


//**********************************************************************************************************************
    //Pizza Menu: Add to cart button
    @FXML
    private ToggleGroup size, crust;
    @FXML
    private VBox toppingsCheckboxes;
    @FXML
    public void addToCart(ActionEvent event) {
        // Get the selected size and crust
        RadioButton selectedSize = (RadioButton) size.getSelectedToggle();
        RadioButton selectedCrust = (RadioButton) crust.getSelectedToggle();
        String description = "Pizza (" + size + " - " + crust + ")";

        if (selectedSize == null || selectedCrust == null) {
            // If size or crust is not selected, show an alert and return
            showAlert("Error", "Please select size and crust.");
            return;
        }

        // Create an item representing the pizza
        String size = selectedSize.getText();
        String crust = selectedCrust.getText();
        double price = calculatePizzaPrice(size, crust); // Calculate price based on size and crust

//        // Add selected toppings to the description
//        for (CheckBox checkBox : toppingsCheckboxes) {
//            if (checkBox.isSelected()) {
//                description.append(", ").append(checkBox.getText());
//            }
//        }

        Item pizza = new Item(description, price);

        // Add the pizza to the shopping cart
        cart.addItem(pizza);
        // Add the pizza to the cartItems list directly
        cartItems.add(pizza);
        // Show a confirmation alert
        showAlert("Success", "Pizza added to cart.");
        // Update the cart display
        updateCart(cart.getItems());
    }

    private double calculatePizzaPrice(String size, String crust) {
        // Initialize variables to store the price of the pizza size and crust
        double sizePrice = 0.0;
        double crustPrice = 0.0;
        // Determine the price based on the selected size
        sizePrice = switch (size) {
            case "personalPizzaSize" -> 9.99;
            case "smallPizzaSize" -> 11.99;
            case "mediumPizzaSize" -> 13.99;
            case "largePizzaSize" -> 15.99;
            default -> sizePrice;
        };
        // Determine the price based on the selected crust
        crustPrice = switch (crust) {
            case "normalCrust" -> 0.00;
            case "thinCrust" -> 1.00;
            case "stuffedCrust" -> 2.00;
            default -> crustPrice;
        };
        // Calculate the total price by adding the size price and crust price
        return sizePrice + crustPrice;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


//**********************************************************************************************************************
    //Cart Screen: List View
        @FXML
        private ListView<Item> itemList = new ListView<>();
        private final ObservableList<Item> cartItems = FXCollections.observableArrayList();
        public void initialize() {
            // Set items to the ListView
            itemList.setItems(cartItems);
        }
        public void updateCart(List<Item> items) {
            // Clear existing items and add new items from the cart
            cartItems.clear();
            for (Item item : items) {
                cartItems.add(new Item(item.getName(), item.getPrice()));
            }
        }
}