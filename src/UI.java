import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.scene.control.PasswordField;

public class UI extends Application {

    private static int rows = 6;
    private static int cols = 5;
    private Rectangle[][] tiles = new Rectangle[rows][cols];
    static String correctWord;

    public class Cell {
        Rectangle box;
        Text character;
        public Cell() {
            box = new Rectangle(55,55);
            box.setFill(Color.WHITESMOKE);
            box.setStrokeWidth(1.5);
            box.setStroke(Color.LIGHTGRAY);

            character = new Text("waow");
            character.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wordel");

        // Wordle tiles
        GridPane wordGrid = new GridPane();
        wordGrid.setAlignment(Pos.TOP_CENTER);
        wordGrid.setTranslateY(-30);
        wordGrid.setHgap(5);
        wordGrid.setVgap(5);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle tile = new Rectangle(55, 55);
                tile.setFill(Color.WHITESMOKE);
                tile.setStrokeWidth(1.5);
                tile.setStroke(Color.LIGHTGRAY);
                tiles[row][col] = tile;
                wordGrid.add(tile, col, row);
            }
        }

        // Keyboard AHHHHHH
        HBox topKeyboardRow = new HBox(6); // top keyboard row
        topKeyboardRow.setAlignment(Pos.CENTER);
        char[] topKeyboardCharacters = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'};
        for (char topKeyboardCharacter : topKeyboardCharacters) {
            Button button = new Button(String.valueOf(topKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(topKeyboardCharacter);
            // button.setText(buttonText);
            button.setOnAction(e -> System.out.println(buttonText.toLowerCase()));
            topKeyboardRow.getChildren().add(button);
        }

        HBox middleKeyboardRow = new HBox(6); // middle keyboard row
        middleKeyboardRow.setAlignment(Pos.CENTER);
        char[] middleKeyboardCharacters = {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'};
        for (char middleKeyboardCharacter : middleKeyboardCharacters) {
            Button button = new Button(String.valueOf(middleKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(middleKeyboardCharacter);
            // button.setText(buttonText);
            button.setOnAction(e -> System.out.println(buttonText.toLowerCase()));
            middleKeyboardRow.getChildren().add(button);
        }

        HBox bottomKeyboardRow = new HBox(6); // bottom keyboard row
        bottomKeyboardRow.setAlignment(Pos.CENTER);
        Button enter = new Button("ENTER"); // enter key
        enter.setPrefSize(74, 60);
        enter.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 12px; -fx-font-weight: bold; -fx-font-style: italic;");
        String enterText = "ENTER";
        enter.setOnAction(e -> System.out.println("okay i just entered but this does special thing"));
        bottomKeyboardRow.getChildren().add(enter);
        char[] bottomKeyboardCharacters = {'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        for (char bottomKeyboardCharacter : bottomKeyboardCharacters) {
            Button button = new Button(String.valueOf(bottomKeyboardCharacter));
            button.setPrefSize(45, 60);
            button.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 19px; -fx-font-weight: bold;");
            String buttonText = String.valueOf(bottomKeyboardCharacter);
            // button.setText(buttonText);
            button.setOnAction(e -> System.out.println(buttonText.toLowerCase()));
            bottomKeyboardRow.getChildren().add(button);
        }
        Button backspace = new Button("⌫"); // backspace key
        backspace.setPrefSize(74, 60);
        backspace.setStyle("-fx-background-color: #d3d6da; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        String backspacetext = "BACKSPACE";
        backspace.setOnAction(e -> System.out.println("okay i just backspaced but this does special thing"));
        bottomKeyboardRow.getChildren().add(backspace);

        VBox keyboard = new VBox(10, topKeyboardRow, middleKeyboardRow, bottomKeyboardRow); // complete keyboard
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setTranslateY(-40);

        // Main layout of children of the guessing root
        VBox rootGuessing = new VBox(20, wordGrid, keyboard);
        rootGuessing.setAlignment(Pos.CENTER);
        rootGuessing.setPadding(new Insets(20));
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

        // Actually setting up the root
        VBox rootChooser = new VBox(20, hello,  chosenWord);
        rootChooser.setAlignment(Pos.CENTER);

        // Pre-maturely set scene and stage
        Scene scene = new Scene(rootChooser, 640, 740);
        primaryStage.setScene(scene);

        // Okay, this changes the root if proper word is given
        chosenWord.setOnAction(e -> {
            String givenWord = chosenWord.getText();
            WordBank checkBank = new WordBank();
            boolean isValid = checkBank.checkWord(givenWord);
            if (!isValid) {
                chosenWord.setText("");
                hello.setText("Invalid input, try again.");
            } else {
                System.out.println(chosenWord.getText());
                correctWord = chosenWord.getText();
                scene.setRoot(rootGuessing);
            }
        });

        // icon bc why not
        Image icon = new Image("wordel icon LOL.png");
        primaryStage.getIcons().add(icon);

        // showtime
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}