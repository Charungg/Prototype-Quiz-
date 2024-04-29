import java.util.ArrayList;
import java.util.Scanner;


public class FillTheBlanks extends Question{

    private int amountOfBlanks;

    private ArrayList<String> answerForBlanks;

    public FillTheBlanks(Bank setBankList) {
        super(setBankList);
        answerForBlanks = new ArrayList<>();
        createFillTheBlanks();
    }

    public void displayQuestion(){
        System.out.println("Question Text: ");
        System.out.println("    " + questionText);

        System.out.println("Amount Of Blanks: \n" + "    " + amountOfBlanks);

        System.out.println("Answers for Blanks: ");
        for (String answer: answerForBlanks){
            System.out.println("    " + answer);
        }
    }

    public void createFillTheBlanks() {
        setQuestionTextUnderScore();
        System.out.println("FTB Question Text = " + questionText);
        setAmountOfBlanks();
        setAnswersBlanks();

        displayQuestion();
    }

    public void setQuestionTextUnderScore(){
        boolean questionTextContainsUnderScores = false;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        do{

            System.out.println("Please enter ___ (3 underscores) to represent a blank");
            setQuestionText();

            if (questionText.contains("___")){
                questionTextContainsUnderScores = true;
            }

            else{
                System.out.println("Question Text Must Contains ___");
            }

        }while(!questionTextContainsUnderScores);
    }

    // TBC This did not work have to rework it.
    public void setAmountOfBlanks(){
        String underScores = "___";
        // sentenceSize has been reduced by 3 because last 2 characters of questionText will never contain three underscores.
        // so -2 on the length and another -1 to reference the index rather than the element.
        int sentenceSize = questionText.length() - 2;
        for (int index = 0; index<sentenceSize; index++){
            System.out.println("Sub-String: " + questionText.substring(index,index+3));
            if (questionText.substring(index,index+3).equals(underScores)){
                amountOfBlanks = amountOfBlanks + 1;
            }
        }
    }


    public void setAnswersBlanks(){
        String answerText;
        Scanner console = new Scanner(System.in);

        for (int gapNumber = 1; gapNumber<=amountOfBlanks; gapNumber++){
            System.out.println("Enter Answer For Blank " + gapNumber + ":");
            answerText = console.next();
            answerForBlanks.add(answerText);
        }
    }
}
