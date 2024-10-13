/** @author Charlie Cheung
 * FillTheBlanks class is a used to create a question of Question class as a child class.
 * */

// Allows the program to write to text files.
import java.io.FileWriter;

// IOException is used as an exception for program.
import java.io.IOException;

// Used to get user input.
import java.util.Scanner;

// ArrayList is used to store multiple item where storage can grow and shrink in accordance of the items.
import java.util.ArrayList;

public class FillTheBlanks extends Question{

    private int amountOfBlanks; // Used to store the amount of blanks in the question.

    private final ArrayList<String> answerForBlanks; // Used to store multiple answer for blank.


    /** Constructor to instantiate the FillTheBlanks class.
     * @param setBankList in order to have access to bank object.
     * */
    public FillTheBlanks(Bank setBankList) {
        super(setBankList);
        answerForBlanks = new ArrayList<>();
        createFillTheBlanks();
    }


    /** Method to display fill the blanks question details. */
    public void displayQuestion(){
        System.out.println("Question Text: ");
        System.out.println("    " + questionText);


        System.out.println("Amount Of Blanks: \n" + "    " + amountOfBlanks);

        System.out.println("Answers for Blanks: ");
        for (String answer: answerForBlanks){
            System.out.println("    " + answer);
        }
    }


    /** Method used by the question object to create a new fill the blanks question. */
    public void createFillTheBlanks() {
        setUserInputQuestionTextUnderScore();
        setUserInputAmountOfBlanks();
        setUserInputAnswersBlanks();
    }


    /** Method for user input of question text must contain blanks. */
    public void setUserInputQuestionTextUnderScore(){
        boolean questionTextContainsUnderScores = false;
        Scanner console = new Scanner(System.in);
        // Allows the question text to contain spaces but not new lines.
        console.useDelimiter("\\n");

        do{

            System.out.println("Please enter ___ (3 underscores) to represent a blank");
            setUserInputQuestionText();

            // Question text must contain blank else it's just a question text without answers to be filled.
            if (questionText.contains("___")){
                questionTextContainsUnderScores = true;
            }

            else{
                System.out.println("Question Text Must Contains ___");
            }

        }while(!questionTextContainsUnderScores);
    }


    /** Method used to store the amount of blanks in user input question text. */
    public void setUserInputAmountOfBlanks(){
        String underScores = "___";
        // sentenceSize has been reduced by 3 because last 2 characters of questionText will never contain three underscores.
        // so -2 on the length and another -1 to reference the index rather than the element.
        int sentenceSize = questionText.length() - 2;
        // Loop is designed to grab the question text and read three character at a time, moving one to the right iteration.
        for (int index = 0; index<sentenceSize; index++){
            // Check if the three character currently selected has three underscores representing a blank.
            if (questionText.substring(index,index+3).equals(underScores)){
                amountOfBlanks = amountOfBlanks + 1;
            }
        }
    }


    /** Method used to store a user input answer for each blank in question text. */
    public void setUserInputAnswersBlanks(){
        String answerText;
        Scanner console = new Scanner(System.in);
        // Allows the user to input answer for blanks with spaces as long it's not new line.
        console.useDelimiter("\\n");

        // Loops through each blank and ask for user input of answer for that blank.
        for (int gapNumber = 1; gapNumber<=amountOfBlanks; gapNumber++){
            System.out.println("Enter Answer For Blank " + gapNumber + ":");
            answerText = console.next();
            answerForBlanks.add(answerText);
        }
    }



    // Functions below are designed specifically for quiz session.

    /** Method to start quiz for fill the blanks question.
     @return true if question is answered correct else false.
     */
    public boolean startQuizQuestion(){
        ArrayList<String> userAnswerList = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        System.out.println("Fill The Blanks Question:");
        System.out.println("    " + questionText);

        for (int blankNumber = 1; blankNumber<=amountOfBlanks; blankNumber++){
            System.out.println("Enter The Correct Number Answer For Blank " + blankNumber + ": ");
            userAnswerList.add(console.next());
        }

        // Returns true if the user input answer is correct for all blanks else return false.
        return userAnswerList.equals(answerForBlanks);
    }


    // Methods below used save/load fill the blanks question from question.txt.

    /** Method to save fill the blanks question to question.txt.
     * @param file in order to have access to question file.
     * */
    public void saveQuestion(FileWriter file){
        try{
            file.write("FillTheBlanks\n");
            file.write(questionText + "\n");
            file.write(amountOfBlanks + "\n");
            for (String answerBlank : answerForBlanks){
                file.write(answerBlank + "\n");
            }
        }

        catch(IOException e){
            System.out.println("Saving Fill The Blanks Failed: ");
        }
    }


    /** Constructor used to load the fill the blanks question from question.txt.
     * @param reader in order to carry on reading next line in question.txt.
     * @param setBankList in order to have access to bank object.
     * */
    public FillTheBlanks (Bank setBankList,Scanner reader){
        super(setBankList);
        answerForBlanks = new ArrayList<>();

        questionText = reader.nextLine();
        // Reads the next line in question.txt and convert it to integer rather than string.
        amountOfBlanks = Integer.parseInt(reader.nextLine());

        for (int setAnswer = 0; setAnswer<amountOfBlanks; setAnswer++){
            answerForBlanks.add(reader.nextLine());
        }
    }
}
