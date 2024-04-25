import java.util.Scanner;
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{
    private int amountOfAnswerChoice;

    private ArrayList<String> answerChoice;

    private static final int amountOfQuestionChoice = 10;

    private int correctChoice;


    public SingleChoiceQuestion(Bank setBankList) {
        super(setBankList);
        answerChoice = new ArrayList<>(amountOfQuestionChoice);
        createSingleChoiceQuestion();
    }


    public void displayQuestion(){
        System.out.println(questionText);
        for (String answer: answerChoice){
            System.out.println(answer);
        }
        System.out.println(correctChoice);
    }


    public void createSingleChoiceQuestion(){
        setQuestionText();
        amountOfAnswerChoice = setAmountOfAnswerChoice();
        setAnswerChoice(amountOfAnswerChoice);
        setCorrectAnswer();

    }


    public int setAmountOfAnswerChoice(){
        int amountOfAnswerChoice = 0;
        boolean amountValid = false;
        Scanner console = new Scanner(System.in);

        do{

            System.out.println("Enter Amount Of Answer Choice (1-10): ");
            try{
                amountOfAnswerChoice = console.nextInt();
                if (amountOfAnswerChoice >=1 && amountOfAnswerChoice <= 10){
                    amountValid = true;
                }

            }

            catch(Exception e){
                console.nextLine();
                System.out.println("Please Enter A Integer");
            }


        }while(!amountValid);

        return amountOfAnswerChoice;
    }


    public void setAnswerChoice(int amountOfAnswerChoice){
        String answerChoiceText;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        for (int i=0; i<amountOfAnswerChoice; i++){
            System.out.println("Enter Answer Choice" + "1: ");
            answerChoiceText = console.next();
            answerChoice.add(answerChoiceText);

        }
    }


    // Not made withe error checking in mind, wanted to test.
    // So fix data types and range after.
    public void setCorrectAnswer(){
        int correctAnswerIndexPosition;
        Scanner console = new Scanner(System.in);

        for (String answer: answerChoice){
            System.out.println(answer);
        }

        System.out.println("Enter The Correct Answer: ");
        correctAnswerIndexPosition = console.nextInt();

        correctChoice = correctAnswerIndexPosition;
    }





}
