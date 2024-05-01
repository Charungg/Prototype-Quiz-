import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{
    private int amountOfAnswerChoice;

    private final ArrayList<String> answerChoice;

    private static final int maximumOfAnswerChoice = 10;

    private int correctChoiceIndex;

    // These functions below are designed specifically to instantiate single choice questions.
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



    // Now these functions below are designed for the quiz session.
    public boolean startQuizQuestion(){
        int userAnswer = -1;
        boolean validAnswer = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Single Choice Question:");
            System.out.print("    " + questionText);

            System.out.println("Answer:");
            for (int numberedChoice = 1; numberedChoice<=amountOfAnswerChoice; numberedChoice++){
                System.out.println("    " + ") " + answerChoice.get(numberedChoice - 1));
            }
            try{
                System.out.println("Enter The Correct Number Answer: ");
                userAnswer = console.nextInt();
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

        return (userAnswer - 1 )== correctChoiceIndex;
    }
}
