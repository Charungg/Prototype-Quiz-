// HashMap is used to store many unique key that can contain many non-unique values.
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can store user score of a specific question identifier.
import java.util.ArrayList;

// Used to get user input.
import java.util.Scanner;

public class Scoreboard {
    private final HashMap<String,ArrayList<String>> questionIdentifierScore;

    public Scoreboard(){
        questionIdentifierScore = new HashMap<>();
    }


    public void submitScore(String username, String questionIdentifier, int score, int amountOfQuestion){
        if (questionIdentifierScore.get(questionIdentifier) == null){
            questionIdentifierScore.put(questionIdentifier, new ArrayList<>());
        }

        questionIdentifierScore.get(questionIdentifier).add(username);
        questionIdentifierScore.get(questionIdentifier).add(String.valueOf(score));
        questionIdentifierScore.get(questionIdentifier).add(String.valueOf(amountOfQuestion));
        System.out.println(questionIdentifierScore);
    }


    public void displayScoresFromSpecificQuestionBank(){
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


    public void displayScoreDetails(ArrayList<String> scoreDetails){
        System.out.println(scoreDetails);
        for (int detailIndex = 0; detailIndex<scoreDetails.size(); detailIndex = detailIndex +3){
            System.out.print("User:" + scoreDetails.get(detailIndex) + "  ");

            System.out.print("Score:" + scoreDetails.get(detailIndex+1));

            System.out.println("/" + scoreDetails.get(detailIndex+2));
        }
    }

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
