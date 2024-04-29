import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{
    private int amountOfAnswerChoice;

    private ArrayList<String> answerChoice;

    private static final int maximumOfAnswerChoice = 10;

    private int correctChoiceIndex;


    public SingleChoiceQuestion(Bank setBankList) {
        super(setBankList);
        answerChoice = new ArrayList<>(maximumOfAnswerChoice);
        createSingleChoiceQuestion();
    }


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


    public void createSingleChoiceQuestion(){
        setQuestionText();
        setAmountOfAnswerChoice();
        setAnswerChoice();
        setCorrectAnswer();
    }


    public void setAmountOfAnswerChoice(){
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


    public void setAnswerChoice(){
        String answerChoiceText;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        for (int questionNumber=1; questionNumber<=amountOfAnswerChoice; questionNumber++){
            System.out.println("Enter Answer Choice " + questionNumber);
            answerChoiceText = console.next();
            answerChoice.add(answerChoiceText);
        }
    }


    // Not made withe error checking in mind, wanted to test.
    // So fix data types and range after.

    // Working on it atm.
    public void setCorrectAnswer(){
        int correctAnswerElementPosition;
        boolean answerInputValid = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter The Correct Answer (1-" + amountOfAnswerChoice + "): ");
            for (int choiceIndex=0; choiceIndex<amountOfAnswerChoice; choiceIndex++){
                System.out.println((choiceIndex+1) + ") " + answerChoice.get(choiceIndex));
            }

            try{
                correctAnswerElementPosition = console.nextInt();
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





}
