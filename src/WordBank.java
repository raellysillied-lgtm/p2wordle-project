import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordBank {
    String fileLocation = "words.txt";
    public boolean checkWord(String word) {
        String targetWord = word.trim().toLowerCase();
        if (targetWord.length() != 5) {
            System.out.println("Word isn't 5 characters.");
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().toLowerCase().equals(targetWord)) {
                    System.out.println("Word is valid.");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file."); // this shouldn't ever happen realistically
            e.printStackTrace();
        }
        System.out.println("Word invalid (not in bank).");
        return false;
    }
}
