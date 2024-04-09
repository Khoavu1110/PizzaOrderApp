package com.example.pizzaorderingapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Homepage {
    private Scene scene;
    public Homepage() throws FileNotFoundException {
        //************************************************************************************************************************************************
        //Top portion of the screen
            //Border
        BorderStroke orderBorder = new BorderStroke(
            Color.BLACK,
            BorderStrokeStyle.SOLID,
            new CornerRadii(10),
            new BorderWidths(2)
        );
        BorderStroke buttonBorder = new BorderStroke(
            Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        );

            //Order Label
        Font orderFont = new Font("Times New Roman", 30);
        Label order = new Label("Start Your Order");
        Label or = new Label("Or");
        order.setFont(orderFont);
        or.setFont(orderFont);

            //Delivery Button
        Button delivery = new Button("Delivery");
        delivery.setFont(orderFont);
        delivery.setBackground(Background.fill(Color.rgb(187, 226, 236)));
        delivery.setBorder(new Border(buttonBorder));

            //Carry Out Button
        Button carryOut = new Button("Carry Out");
        carryOut.setFont(orderFont);
        carryOut.setBackground(Background.fill(Color.rgb(187, 226, 236)));
        carryOut.setBorder(new Border(buttonBorder));

        //HBox to store the 2 labels and 2 buttons above
        HBox orderPromptLayout = new HBox();
        HBox.setMargin(order, new Insets(5));
        HBox.setMargin(or, new Insets(5));
        HBox.setMargin(delivery, new Insets(5));
        HBox.setMargin(carryOut, new Insets(5));
        orderPromptLayout.setPadding(new Insets(5));
        orderPromptLayout.setBorder(new Border(orderBorder));
        orderPromptLayout.setBackground(Background.fill(Color.rgb(249,239,219)));
        orderPromptLayout.setAlignment(Pos.CENTER_LEFT);
        orderPromptLayout.getChildren().addAll(order, delivery, or, carryOut);

        //Restaurant Logo/Icon/HomeButton
        Button icon = new Button();
        icon.setGraphic(new ImageView(new Image(new FileInputStream("/Users/khoavu/IdeaProjects/PizzaOrderingApp/src/main/java/com/example/pizzaorderingapp/RestaurantIcon.jpeg"),64, 64, false, false)));

        //Log In Button
        Button login = new Button("Log In");

        //Sign Up
        Button signUp = new Button("Sign Up");

        //Top layout (Contain icon, prompt, log in and sign up button
        HBox topLayout = new HBox();
        HBox.setMargin(icon,new Insets(2));
        HBox.setMargin(orderPromptLayout,new Insets(2));
        topLayout.setAlignment(Pos.CENTER_LEFT);
        topLayout.getChildren().addAll(icon, orderPromptLayout);

        //************************************************************************************************************************************************
        //Overall layout of the screen
        BorderPane root = new BorderPane();
        BorderPane.setMargin(orderPromptLayout, new Insets(10));
        root.setPadding(new Insets(10));
        root.setTop(topLayout);
        scene = new Scene(root);

        //************************************************************************************************************************************************
    }

    public Scene getScene(){
        return scene;
    }
}
