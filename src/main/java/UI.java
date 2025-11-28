package main.java;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI extends Application {

    /*
    i did NOT format this well, in truth the code between main.java.UI and "controller" (its more like a controller and model
    mix tbh) is just really messy
     */

    public static int rows = 6; // guesses allowed
    private static int cols = 5; // word length lol
    private Rectangle[][] tiles = new Rectangle[rows][cols];
    private Text[][] characters = new Text[rows][cols];
    private static String correctWord;
    private Controller controller = new Controller("", tiles, characters, rows);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wordel");

        // Wordle tiles
        GridPane wordGrid = new GridPane();
        wordGrid.setAlignment(Pos.TOP_CENTER);
        wordGrid.setTranslateY(-30);
        wordGrid.setHgap(5);
        wordGrid.setVgap(5);
        for (int row = 0; row < rows; row++) { // scan each row (up to down)
            for (int col = 0; col < cols; col++) { // scan each column (left to right)
                Rectangle tile = new Rectangle(55, 55); // add box tile
                tile.setFill(Color.WHITESMOKE);
                tile.setStrokeWidth(1.5);
                tile.setStroke(Color.LIGHTGRAY);
                tiles[row][col] = tile; // save reference/"pointer"

                // Text character = new Text(Integer.toString(col) + ", " + Integer.toString(row));
                Text character = new Text(""); // add text overlay
                character.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                character.setFill(Color.BLACK);
                characters[row][col] = character; // save reference/"pointer"

                StackPane tilePane = new StackPane(); // stackpane for layering and centering
                tilePane.getChildren().addAll(tile, character);
                wordGrid.add(tilePane, col, row); // add to grid pane
            }
        }

        // Keyboard AHHHHHH
        HBox topKeyboardRow = new HBox(6); // top keyboard row
        topKeyboardRow.setAlignment(Pos.CENTER);
        char[] topKeyboardCharacters = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
        int i = 0;
        for (char topKeyboardCharacter : topKeyboardCharacters) {
            Button button = new Button(String.valueOf(topKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(topKeyboardCharacter);
            // button.setText(buttonText);
            button.setFocusTraversable(false);
            button.setOnAction(e -> {
                if (controller != null) {
                    controller.addCharacter(Character.toLowerCase(topKeyboardCharacter));
                }
            });
            if (controller != null) {
                controller.addKeys(button, i, "top");
            }
            topKeyboardRow.getChildren().add(button);
            i++;
        }

        HBox middleKeyboardRow = new HBox(6); // middle keyboard row
        middleKeyboardRow.setAlignment(Pos.CENTER);
        char[] middleKeyboardCharacters = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
        i = 0;
        for (char middleKeyboardCharacter : middleKeyboardCharacters) {
            Button button = new Button(String.valueOf(middleKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(middleKeyboardCharacter);
            // button.setText(buttonText);
            button.setFocusTraversable(false); // prevent taking in enter or space key as pressing the button (Q is first letter)
            button.setOnAction(e -> {
                if (controller != null) {
                    controller.addCharacter(Character.toLowerCase(middleKeyboardCharacter));
                }
            });
            if (controller != null) {
                controller.addKeys(button, i, "middle");
            }
            middleKeyboardRow.getChildren().add(button);
            i++;
        }

        HBox bottomKeyboardRow = new HBox(6); // bottom keyboard row
        bottomKeyboardRow.setAlignment(Pos.CENTER);
        Button enter = new Button("ENTER"); // enter key
        enter.setPrefSize(74, 60);
        enter.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 12px; -fx-font-weight: bold; -fx-font-style: italic;");
        String enterText = "ENTER";
        enter.setFocusTraversable(false);
        enter.setOnAction(e -> {
            if (controller != null) {
                controller.submitGuess();
            }
        });
        bottomKeyboardRow.getChildren().add(enter);
        char[] bottomKeyboardCharacters = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        i = 0;
        for (char bottomKeyboardCharacter : bottomKeyboardCharacters) {
            Button button = new Button(String.valueOf(bottomKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(bottomKeyboardCharacter);
            // button.setText(buttonText);
            button.setFocusTraversable(false);
            button.setOnAction(e -> {
                if (controller != null) {
                    controller.addCharacter(Character.toLowerCase(bottomKeyboardCharacter));
                }
            });
            if (controller != null) {
                controller.addKeys(button, i, "bottom");
            }
            bottomKeyboardRow.getChildren().add(button);
            i++;
        }
        Button backspace = new Button("⌫"); // backspace key
        backspace.setPrefSize(74, 60);
        backspace.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        String backspacetext = "BACKSPACE";
        backspace.setFocusTraversable(false);
        backspace.setOnAction(e -> {
            if (controller != null) {
                controller.backspace();
            }
        });
        bottomKeyboardRow.getChildren().add(backspace);

        VBox keyboard = new VBox(10, topKeyboardRow, middleKeyboardRow, bottomKeyboardRow); // complete keyboard
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setTranslateY(-40);

        // Main layout of children of the guessing root
        VBox rootGuessing = new VBox(20, wordGrid, keyboard);
        rootGuessing.setAlignment(Pos.CENTER);
        primaryStage.setResizable(false);

        // Choosing screen
        Text hello = new Text("Hello, what is the 5-letter word you want your friend to guess for?");
        hello.setFont(new Font(20));
        PasswordField chosenWord = new PasswordField();
        chosenWord.setStyle("-fx-background-color: white; -fx-border-color: grey; -fx-border-width: 2px; -fx-border-radius: 5px;");
        chosenWord.setPromptText("Enter word");
        chosenWord.setAlignment(Pos.CENTER);
        chosenWord.setPrefWidth(120);
        chosenWord.setMaxWidth(120);
        chosenWord.setPrefHeight(20);
        Button submit = new Button("GO");
        submit.setPrefSize(48, 20);
        submit.setStyle("-fx-background-color: #6aaa64; -fx-border-radius: 5px; -fx-text-fill: white; ");
        submit.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 14));
        submit.setOnAction(e -> {
            chosenWord.getOnAction().handle(null);
        });
        HBox wordGiver = new HBox(10, chosenWord, submit);
        wordGiver.setAlignment(Pos.CENTER);
        Rectangle divider = new Rectangle(320,2);
        divider.setFill(Variables.WORDLE_YELLOW);;
        Button random = new Button("Load a random word!");
        random.setPrefWidth(200);
        random.setPrefHeight(30);
        random.setStyle("-fx-background-color: BLACK; -fx-text-fill: white;");
        random.setOnAction(e -> {
            WordBank getRandom = new WordBank();
            chosenWord.setText(getRandom.randomWord());
        });

        // Actually setting up the chooser root
        VBox rootChooser = new VBox(20, hello, wordGiver, divider, random);
        rootChooser.setAlignment(Pos.CENTER);

        // Pre-maturely set scene and stage
        Scene scene = new Scene(rootChooser, 640, 740);
        primaryStage.setScene(scene);

        // Setup to take in physical keyboard inputs
        scene.setOnKeyPressed(e -> {
        KeyCode code = e.getCode();
        if (controller == null) {
            return;
        }
        switch (code) {case BACK_SPACE:
                controller.backspace();
                break;

            case ENTER:
                controller.submitGuess();
                break;

            case SPACE:
                controller.submitGuess();
                break;

            default:
                if (code.isLetterKey()) {
                    controller.addCharacter(code.getName().toLowerCase().charAt(0));
                }
                break;
        }
    });

        // Okay, this changes the root if proper word is given on the choosing screen
        chosenWord.setOnAction(e -> {
            String givenWord = chosenWord.getText();
            WordBank checkBank = new WordBank();
            boolean isValid = checkBank.checkWord(givenWord);
            if (!isValid) {
                chosenWord.setText("");
                hello.setText("Invalid input, try again.");
            } else {
                correctWord = chosenWord.getText().toLowerCase();
                System.out.println(correctWord);
                controller.correctWord = chosenWord.getText().toLowerCase(); // controlaa
                scene.setRoot(rootGuessing);
                chosenWord.setDisable(true); // disable choosing screen nodes, TODO: fix when implementing a gameplay loop
                submit.setDisable(true);
                random.setDisable(true);
            }
        });

        // icon bc why not
        try (FileInputStream image = new FileInputStream("src/main/java/resources/wordel icon LOL.png")) {
            Image icon = new Image(image);
            primaryStage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        // showtime
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}