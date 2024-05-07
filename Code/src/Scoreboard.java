/** @author Charlie Cheung
 * Scoreboard class is used to store user result of quiz session.
 * */

// FileWriter allows the object to write in the module text file.
// IOException is the catch for FileWriter
import java.io.FileWriter;
import java.io.IOException;


// HashMap is used to store many unique key that can contain many non-unique values.
import java.util.HashMap;

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can store user score of a specific question identifier.
import java.util.ArrayList;

// Used to get user input.
import java.util.Scanner;

public class Scoreboard {
    private final HashMap<String,ArrayList<String>> questionIdentifierScore; // HashMap which stores question identifiers
                                                                             // and their user scores.

    /** Constructor to instantiate the Scoreboard class. */
    public Scoreboard(){
        questionIdentifierScore = new HashMap<>();
    }


    /** Method used to submit user score from a quiz.
     * @param username is the username of the user who partake the quiz.
     * @param questionIdentifier is the question bank that the user selected to partake.
     * @param score is the score the user scored in the quiz.
     * @param amountOfQuestion is the amount of question the user partake of the quiz.
     * */
    public void submitScore(String username, String questionIdentifier, int score, int amountOfQuestion){
        if (questionIdentifierScore.get(questionIdentifier) == null){
            questionIdentifierScore.put(questionIdentifier, new ArrayList<>());
        }

        questionIdentifierScore.get(questionIdentifier).add(username);
        questionIdentifierScore.get(questionIdentifier).add(String.valueOf(score));
        questionIdentifierScore.get(questionIdentifier).add(String.valueOf(amountOfQuestion));
    }


    /** Method to display the scoreboard from a specific question bank.  */
    public void displayScoresFromAQuestionBank(){
        String moduleName;
        String bankName;
        String questionIdentifier;
        Scanner console = new Scanner(System.in);

        System.out.println("Enter Module Identifier");
        moduleName = console.next();

        System.out.println("Enter Bank Identifier");
        bankName = console.next();

        questionIdentifier = moduleName+":"+bankName;
        if (questionIdentifierScore.get(questionIdentifier) != null){
            displayScoreDetails(questionIdentifierScore.get(questionIdentifier));
        }

        else{
            System.out.println("No Score Exist For This Question Identifier");
        }
    }


    /** Method to display score details in a specific format to the user. */
    public void displayScoreDetails(ArrayList<String> scoreDetails){
        for (int detailIndex = 0; detailIndex<scoreDetails.size(); detailIndex = detailIndex +3){
            System.out.print("User:" + scoreDetails.get(detailIndex) + "  ");

            System.out.print("Score:" + scoreDetails.get(detailIndex+1));

            System.out.println("/" + scoreDetails.get(detailIndex+2));
        }
    }


    /** Method to save all the quiz scores by the user. */
    public void saveScoreBoard(FileWriter file){
        try {
            for (String questionIdentifier : questionIdentifierScore.keySet()) {
                file.write(questionIdentifier + "\n");
                for (String userScore : questionIdentifierScore.get(questionIdentifier)){
                    file.write(userScore + "\n");
                }
            }
            file.close();
        }
        catch(IOException e){
            System.out.println("Saving Score In Scoreboard Failed: ");
        }
    }


    /** Method to load all the quiz scores by the users. */
    public void loadScoreBoard(Scanner reader){
        String lineOfText;
        String questionIdentifier = null;
        while (reader.hasNextLine()){
            lineOfText = reader.nextLine();

            if (lineOfText.contains(":")){
                questionIdentifier = lineOfText;
                questionIdentifierScore.put(questionIdentifier, new ArrayList<>());
            }

            else{
                questionIdentifierScore.get(questionIdentifier).add(lineOfText);
            }
        }
        System.out.println("Loaded Module");
    }


}
