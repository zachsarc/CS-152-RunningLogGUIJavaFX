## This JavaFX application is a graphical running log that allows users to calculate their pace per mile based on race type and completion time.
### This Exercise is apart of Week 7 of [CS-152_Java2](https://github.com/zachsarc/CS-152_Java2)
```java
package com.example.javafxpractice;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RunningLogGUI extends Application {
    @Override
    public void start(Stage stage) {
        // Set up layout and scene
        try {
            stage.setTitle("Running Log");
            Label tl = new Label("Choose Race");
            tl.setStyle("-fx-label-padding: 20");
            BorderPane root = new BorderPane(null, tl, null, null, null); // Only using the top label node

            // Create radio buttons inside an hBox
            final ToggleGroup raceGroup = new ToggleGroup();
            RadioButton rb1 = new RadioButton("1 Mile");
            rb1.setToggleGroup(raceGroup);
            rb1.setSelected(true); // By default, I want this to be selected

            RadioButton rb2 = new RadioButton("5k");
            rb2.setToggleGroup(raceGroup);

            RadioButton rb3 = new RadioButton("10k");
            rb3.setToggleGroup(raceGroup);

            RadioButton rb4 = new RadioButton("Half-Marathon");
            rb4.setToggleGroup(raceGroup);

            RadioButton rb5 = new RadioButton("Marathon");
            rb5.setToggleGroup(raceGroup);

            HBox raceOptions = new HBox(30, rb1, rb2, rb3, rb4, rb5); // Add nodes to the HBox
            raceOptions.setStyle("-fx-padding: 20; -fx-alignment: center;");

            // Allowing HBox nodes to grow accordingly
            HBox.setHgrow(rb1, Priority.ALWAYS);
            HBox.setHgrow(rb2, Priority.ALWAYS);
            HBox.setHgrow(rb3, Priority.ALWAYS);
            HBox.setHgrow(rb4, Priority.ALWAYS);
            HBox.setHgrow(rb5, Priority.ALWAYS);

            Label hoursLabel = new Label("Hours");
            TextField hoursTA = new TextField();
            hoursTA.setPrefSize(300, 50);
            VBox hoursBox = new VBox(5, hoursLabel, hoursTA);

            Label minutesLabel = new Label("Minutes");
            TextField minutesTA = new TextField();
            minutesTA.setPrefSize(300, 50);
            VBox minutesBox = new VBox(5, minutesLabel, minutesTA);

            Label secondsLabel = new Label("Seconds");
            TextField secondsTA = new TextField();
            secondsTA.setPrefSize(300, 50);
            VBox secondsBox = new VBox(5, secondsLabel, secondsTA);

            Label errorLabel = new Label(); // Error label
            errorLabel.setStyle("-fx-text-fill: RED;");
            errorLabel.setVisible(false);
            errorLabel.setManaged(false);
            HBox timeOptions = new HBox(5, hoursBox, minutesBox, secondsBox, errorLabel);

            VBox centerBox = new VBox(15, raceOptions, timeOptions);
            centerBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

            // Alignment for RadioBox and User time
            hoursBox.setAlignment(Pos.CENTER);
            minutesBox.setAlignment(Pos.CENTER);
            secondsBox.setAlignment(Pos.CENTER);
            timeOptions.setAlignment(Pos.CENTER);


            Button calculateButton = new Button("Calculate"); // Create the calculate button
            Text displayResult = new Text("Calculated Pace: "); // Create text area to display calculated result
            calculateButton.setPrefSize(100, 50);

            // Wrap the button and text in an HBox
            HBox bottomBox = new HBox(20, calculateButton, displayResult);
            bottomBox.setStyle("-fx-padding: 20;");
            bottomBox.setAlignment(Pos.CENTER);
            root.setCenter(centerBox); // Place the centerBox in the center of the border pane
            root.setBottom(bottomBox); // Place the bottomBox in the bottom of the border pane

            // Border Pane Alignment
            BorderPane.setAlignment(tl, Pos.TOP_CENTER);

            calculateButton.setOnAction(event -> {
                // Logic time!
                String hourText = hoursTA.getText();
                String minuteText = minutesTA.getText();
                String secondsText = secondsTA.getText();
                int hours = 0;
                int minutes = 0;
                int seconds = 0;
                try {
                    hours = Integer.parseInt(hourText);
                    minutes = Integer.parseInt(minuteText);
                    seconds = Integer.parseInt(secondsText);
                    errorLabel.setText(""); // Clear any old error
                    errorLabel.setVisible(false);
                    errorLabel.setManaged(false);

                    // Get race type
                    String raceType = ((RadioButton) raceGroup.getSelectedToggle()).getText();

                    // Define a distance variable
                    double distance = 0.0;

                    switch (raceType) {
                        case "1 Mile":
                            distance = 1.0;
                            break;
                        case "5k":
                            distance = 3.106;
                            break;
                        case "10k":
                            distance = 6.211;
                            break;
                        case "Half-Marathon":
                            distance = 13.109;
                            break;
                        case "Marathon":
                            distance = 26.219;
                            break;
                    }

                    int totalSeconds = hours * 3600 + minutes * 60 + seconds;
                    double paceInSecs = totalSeconds / distance;
                    int paceHours = (int) paceInSecs / 3600;
                    int paceMinutes = (int) ((paceInSecs % 3600) / 60);
                    int paceSeconds = (int) (paceInSecs % 60);


                    displayResult.setText("Calculated Pace: " + paceHours + "h " + paceMinutes + "min " + paceSeconds + "s per mile");

                } catch (NumberFormatException e) {
                    errorLabel.setText("Please enter only valid integers");
                    errorLabel.setVisible(true);
                    errorLabel.setManaged(true);
                    displayResult.setText("");
                }
            });

            //Setup scene
            Scene scene = new Scene(root, 800, 600);

            // Set up the stage
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
```
