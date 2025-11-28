package main.java;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.ArrayList;

public class Controller {
    public String correctWord;
    private int currentLetter = 1; // column + 1
    private int currentAttempt = 1; // row + 1
    private int maxAttempts;
    private String currentGuess = "";
    private Rectangle[][] tiles; // copy reference/"pointer" (heap) (row, column)
    private Text[][] characters; // same as above
    private ArrayList<String> greenChars = new ArrayList<String>();
    private Button[] topKeyboardStyle = new Button[10];  // Q-P
    private Button[] middleKeyboardStyle = new Button[9]; // A-L
    private Button[] bottomKeyboardStyle = new Button[7]; // Z-M

    public Controller(String correctWord, Rectangle[][] tiles, Text[][] characters, int maxAttempts) { // update these spare copies
        this.correctWord = correctWord.toLowerCase();
        this.tiles = tiles;
        this.characters = characters;
        this.maxAttempts = maxAttempts;
    }

    public void addCharacter (char letter) {
        if (currentLetter <= 5 && currentAttempt <= maxAttempts) {
            characters[currentAttempt - 1][currentLetter - 1].setText(String.valueOf(letter).toUpperCase());
            currentGuess += Character.toLowerCase(letter);
            currentLetter++;
            System.out.println(currentGuess);
        }
    }

    public void backspace() {
        if (currentLetter > 1 && currentAttempt <= maxAttempts) {
            currentLetter--;
            characters[currentAttempt - 1][currentLetter - 1].setText("");
            if (!currentGuess.isEmpty()) {
                currentGuess = currentGuess.substring(0, currentGuess.length() - 1);
            }
        }
    }

    public void submitGuess() {
        if (currentAttempt > maxAttempts) {
            System.out.println("ur out of guesses lmao");
            return;
        }

        if (currentGuess.length() != correctWord.length()) { // check if full word is given
            System.out.println("There are empty slots.");
            return;
        }

        WordBank checkGuess = new WordBank(); // check if word is valid in word bank
        if (!checkGuess.checkWord(currentGuess)) {
            // System.out.println("Word not in bank.");
            return;
        }

        char[] guessArray = currentGuess.toCharArray();
        char[] correctWordArray = correctWord.toCharArray();
        int[] letterInc = new int [26]; // (0 <-> A, 1 <-> B, etc, dictates number of a character in the correct word)
        for (char x : correctWordArray) { // inc each alphabetical numerical slot by 1 for each time it shows up in the answer
            letterInc[x - 'a']++; // each alphabetical character increase also increases its unicode index by one, get "distance" from 'a'
            // a = (U+)0061, b = (U+)0062, etc
        }

        for (int i = 0; i < currentGuess.length(); i++) { // first pass for greens
            char guessedChar = currentGuess.charAt(i);
            Rectangle tile = tiles[currentAttempt - 1][i];
            Text character = characters[currentAttempt - 1][i];

            if (correctWord.charAt(i) == guessedChar) {
                tile.setFill(Variables.WORDLE_GREEN); // GREEN
                tile.setStroke(Variables.WORDLE_GREEN);
                character.setFill(Color.WHITE);
                letterInc[guessedChar - 'a']--;
                greenChars.add(Character.toString(guessedChar));
                guessArray[i] = '*'; // mark character index on the guess array that is registered
                // (W|A|T|E|R -> W|*|T|E|R)
                updateKeyColor(correctWord.charAt(i), "green");
            }
        }

        for (int i = 0; i < currentGuess.length(); i++) { // check for yellows and greys
            char guessedChar = currentGuess.charAt(i);
            Rectangle tile = tiles[currentAttempt - 1][i];
            Text character = characters[currentAttempt - 1][i];

            if (guessArray[i] == '*') {
                continue; // skip, already marked as green
            }

            if (letterInc[guessedChar - 'a'] > 0) {
                tile.setFill(Variables.WORDLE_YELLOW);
                tile.setStroke(Variables.WORDLE_YELLOW);
                character.setFill(Color.WHITE);
                letterInc[guessedChar - 'a']--;
                boolean isGreen = false;
                for (String x : greenChars) {
                    char green = Character.toLowerCase(x.charAt(0));
                    if (green == guessedChar) {
                        isGreen = true;
                        break;
                    }
                }
                if (!isGreen) {
                    updateKeyColor(currentGuess.charAt(i), "yellow");
                }
            } else {
                tile.setFill(Variables.WORDLE_GRAY);
                tile.setStroke(Variables.WORDLE_GRAY);
                character.setFill(Color.WHITE);
                updateKeyColor(currentGuess.charAt(i), "gray");
            }
        }

        currentAttempt++;
        currentLetter = 1;
        currentGuess = "";
    }

    public void addKeys(Button b, int index, String keyboardRow) { // import keyboard rows
        if (keyboardRow.equals("top")) {
            topKeyboardStyle[index] = b;
        }
        if (keyboardRow.equals("middle")) {
            middleKeyboardStyle[index] = b;
        }
        if (keyboardRow.equals("bottom")) {
            bottomKeyboardStyle[index] = b;
        }
    }

    public void updateKeyColor(char selectedButton, String color) {
        selectedButton = Character.toUpperCase(selectedButton);
        color = color.toLowerCase();
        Button set = locateButton(selectedButton);
        if (set == null) {
            return;
        }
        switch (color) {
            case "green":
                set.setStyle("-fx-background-color: " + Variables.WORDLE_GREEN_HEX + "; -fx-text-fill: white; -fx-font-size: 19px; -fx-font-weight: bold;");
                break;
            case "yellow":
                set.setStyle("-fx-background-color: " + Variables.WORDLE_YELLOW_HEX + "; -fx-text-fill: white; -fx-font-size: 19px; -fx-font-weight: bold;");
                break;
            default:
                set.setStyle("-fx-background-color: " + Variables.WORDLE_GRAY_HEX + "; -fx-text-fill: white; -fx-font-size: 19px; -fx-font-weight: bold;");
                break;
        }
    }

    public Button locateButton(char letter) { // scan each array for the character
        letter = Character.toUpperCase(letter);
        for (Button b : topKeyboardStyle) {
            if (b != null && b.getText().charAt(0) == letter) {
                return b;
            }
        }
        for (Button b : middleKeyboardStyle) {
            if (b != null && b.getText().charAt(0) == letter) {
                return b;
            }
        }
        for (Button b : bottomKeyboardStyle) {
            if (b != null && b.getText().charAt(0) == letter) {
                return b;
            }
        }
        return null;
    }
}
