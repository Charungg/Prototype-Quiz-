/** @author Charlie Cheung
 * SingleChoiceQuestion class is a used to create a question of Question class as a child class.
 * */

// Allows the program to write to text files.
import java.io.FileWriter;
// IOException is used as an exception for program.
import java.io.IOException;

// Used to get user input.
import java.util.Scanner;
// InputMismatchException is used as an Exception for Scanner if the user input a non-int.
import java.util.InputMismatchException;

// ArrayList is used to store multiple item where storage can grow and shrink in accordance of the items.
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{
    private int amountOfAnswerChoice; // Used to store the amount of answer choice.

    private final ArrayList<String> answerChoice; // Used to store multiple answer.

    private static final int maximumOfAnswerChoice = 10; // Used to ensure single choice question
    // can only have ten potential answer.

    private int correctChoiceIndex; // Stores the correct answer to question index position.

    /** Constructor to instantiate the SingleChoiceQuestion class.
     * @param setBankList in order to have access to bank object.
     * */
    public SingleChoiceQuestion(Bank setBankList) {
        super(setBankList);
        answerChoice = new ArrayList<>(maximumOfAnswerChoice);
        createSingleChoiceQuestion();
    }


    /** Method to display single choice question details. */
    public void displayQuestion(){
        System.out.println("Question Text: ");
        System.out.println("    " + questionText);

        System.out.println("Answer Option: ");
        for (int choiceIndex=1; choiceIndex<=amountOfAnswerChoice; choiceIndex++){
            System.out.println("    " + choiceIndex + ") " + answerChoice.get(choiceIndex -1));
        }

        System.out.println("Correct Answer Index: ");
        System.out.println("    " + correctChoiceIndex);
    }


    /** Method used by the question object to create a new single choice question. */
    public void createSingleChoiceQuestion(){
        setUserInputQuestionText();
        setUserInputAmountOfAnswerChoice();
        setUserInputAnswerChoice();
        setUserInputCorrectAnswerChoiceIndex();
    }


    /** Method to store user input desired amount of answer choice. */
    public void setUserInputAmountOfAnswerChoice(){
        int amountOfAnswerChoiceIs = 0;
        boolean amountValid = false;
        Scanner console = new Scanner(System.in);

        do{

            System.out.println("Enter Amount Of Answer Choice (1-10): ");
            try{
                amountOfAnswerChoiceIs = console.nextInt();
                if (amountOfAnswerChoiceIs >=1 && amountOfAnswerChoiceIs <= 10){
                    amountValid = true;
                }
            }

            catch(InputMismatchException e){
                console.nextLine();
                System.out.println("Please Enter A Integer");
            }


        }while(!amountValid);

        amountOfAnswerChoice = amountOfAnswerChoiceIs;
    }


    /** Method to store user input answer choice. */
    public void setUserInputAnswerChoice(){
        String answerChoiceText;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        // Loop starts at one used to display numbered question choice
        // and input answer choice for that choice.
        for (int questionNumber=1; questionNumber<=amountOfAnswerChoice; questionNumber++){
            System.out.println("Enter Answer Choice " + questionNumber);
            answerChoiceText = console.next();
            answerChoice.add(answerChoiceText);
        }
    }


    /** Method to store the correct answer choice index position. */
    public void setUserInputCorrectAnswerChoiceIndex(){
        int correctAnswerElementPosition;
        boolean answerInputValid = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter The Correct Answer (1-" + amountOfAnswerChoice + "): ");
            // Displays all answer choice with numbered.
            for (int choiceIndex=0; choiceIndex<amountOfAnswerChoice; choiceIndex++){
                System.out.println((choiceIndex+1) + ") " + answerChoice.get(choiceIndex));
            }

            try{
                correctAnswerElementPosition = console.nextInt();
                // User must enter an integer that's one of numbered answer choice that is the
                // correct answer.
                if(correctAnswerElementPosition >=1 && correctAnswerElementPosition <= amountOfAnswerChoice){
                    correctChoiceIndex = correctAnswerElementPosition - 1;
                    answerInputValid = true;
                }

            }

            catch(InputMismatchException e){
                console.nextLine();
                System.out.println("Invalid Input, Enter Within Range: ");
            }
        }while(!answerInputValid);
    }



    /** Method to start quiz for single choice question.
     * @return true if question is answered correct else false.
     */
    public boolean startQuizQuestion(){
        int userAnswer = -1;
        boolean validAnswer = false;
        Scanner console = new Scanner(System.in);

        do{
            // Display question text.
            System.out.println("Single Choice Question:");
            System.out.println("    " + questionText);

            System.out.println("Answer:");
            // Displays all answer choice.
            for (int numberedChoice = 1; numberedChoice<=amountOfAnswerChoice; numberedChoice++){
                System.out.println("    " + numberedChoice + ") " + answerChoice.get(numberedChoice - 1));
            }
            try{
                System.out.println("Enter The Correct Number Answer: ");
                userAnswer = console.nextInt();
                // If answer is valid within range of answer.
                if (userAnswer>=1 && userAnswer<=amountOfAnswerChoice){
                    validAnswer = true;
                }

                else{
                    System.out.println("Invalid Input, Answer Number Does Not Exist");
                }
            }
            catch(InputMismatchException e){
                console.nextLine();
                System.out.println("Invalid Input, Must Enter A Integer");
            }



        }while(!validAnswer);

        // If the user answer the question correctly it will return true else false.
        return (userAnswer - 1 )== correctChoiceIndex;
    }



    // Methods below used save/load single choice question from question.txt.

    /** Method to save single choice question to question.txt.
     * @param file in order to have access to question file.
     * */
    public void saveQuestion(FileWriter file){
        try{
            // Single choice question object attributes will be written in this format.
            file.write("SingleChoiceQuestion\n");
            file.write(questionText + "\n");
            file.write(amountOfAnswerChoice + "\n");
            for (String answerChoice : answerChoice){
                file.write(answerChoice + "\n");
            }
            file.write(correctChoiceIndex + "\n");
        }

        catch(IOException e){
            System.out.println("Saving Single Choice Question Failed: ");
        }
    }


    /** Constructor used to load the single choice question from question.txt.
     * @param reader in order to carry on reading next line in question.txt.
     * @param setBankList in order to have access to bank object.
     * */
    public SingleChoiceQuestion(Bank setBankList,Scanner reader){
        super(setBankList);
        answerChoice = new ArrayList<>();

        questionText = reader.nextLine();
        // Reads the next line in question.txt and convert it to integer rather than string.
        amountOfAnswerChoice = Integer.parseInt(reader.nextLine());

        for (int setChoice = 0; setChoice < amountOfAnswerChoice; setChoice++){
            answerChoice.add(reader.nextLine());
        }

        correctChoiceIndex = Integer.parseInt(reader.nextLine());
    }

}
