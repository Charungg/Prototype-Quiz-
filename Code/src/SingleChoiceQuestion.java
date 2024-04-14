import java.util.Scanner;
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{

    private Question questionList;

    private ArrayList<ArrayList<String>> answerOptions;

    private int vertexCount = 10;

    public ArrayList<Integer> correctAnswer;


    public SingleChoiceQuestion(Bank setBankList) {
        super(setBankList);
        questionText = new ArrayList<String>();
        answerOptions = new ArrayList<>(vertexCount);
        correctAnswer = new ArrayList<Integer>();
    }


    public void createSingleChoiceQuestion(){
        setQuestionIdentifier();
        setQuestionText();
        int amountOfAnswerChoice;
        amountOfAnswerChoice = setAmountOfAnswerOption();
        setAnswerOptions(amountOfAnswerChoice);
        setCorrectAnswer(amountOfAnswerChoice);


//        System.out.println("End Of Process");
//        System.out.println(questionsIdentifiers);
//        System.out.println(questionText);
//        System.out.println(answerOptions);
//        System.out.println(correctAnswer);
//        System.out.println("Done");


    }

    public int setAmountOfAnswerOption(){
        int amountOfAnswerOption = 0;
        boolean amountIsValid = false;
        Scanner console = new Scanner (System.in);

        do{
            System.out.println("Enter Amount Of Question Choice (1-10): ");
            try{
                amountOfAnswerOption = console.nextInt();
                if (amountOfAnswerOption > 1 && amountOfAnswerOption <= 10){
                    amountIsValid = true;
                }
                else{
                    System.out.println("Invalid Input, Please Try Again");
                }
            }

            catch(Exception e){
                System.out.println("Invalid Input, Please Try Again");
            }

        }while(!amountIsValid);
        return amountOfAnswerOption;
    }

    public void setAnswerOptions(int amountOfAnswerChoice){
        String userInputAnswerChoice;
        Scanner console = new Scanner (System.in);
        console.useDelimiter("\\n");
        // Instantiate a new element into the array.
        answerOptions.add(new ArrayList<String>());

        for (int i=0; i<amountOfAnswerChoice; i++){
            System.out.println("Enter Answer Choice " + (i+1) + ": ");
            userInputAnswerChoice = console.next();
            answerOptions.get(answerOptions.size()-1).add(userInputAnswerChoice);
        }

        System.out.println(answerOptions);
    }

    public void setCorrectAnswer(int amountOfAnswerChoice){
        Scanner console = new Scanner((System.in));
        int correctAnswerIndex = 0;
        boolean correctAnswerGiven = false;
        do{
            System.out.println("Enter The Correct Answer Choice (1-" + amountOfAnswerChoice + "): ");
            try{
                correctAnswerIndex = console.nextInt();
                if (correctAnswerIndex >=1 && correctAnswerIndex <= amountOfAnswerChoice){
                    correctAnswerGiven = true;
                }
                else{
                    System.out.println("Invalid Input, Please Try Again");
                }
            }

            catch (Exception e){
                System.out.println("Invalid Input, Please Try Again");
            }

        }while(!correctAnswerGiven);
        correctAnswer.add(correctAnswerIndex-1);
    }
}
