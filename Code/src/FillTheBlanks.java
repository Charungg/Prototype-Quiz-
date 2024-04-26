import java.util.ArrayList;
import java.util.Scanner;


public class FillTheBlanks extends Question{

    private int amountOfGaps;

    private ArrayList<String> answersFillTheBlanks;

    public FillTheBlanks(Bank setBankList) {
        super(setBankList);
        answersFillTheBlanks = new ArrayList<>();
        createFillTheBlanks();
    }

    public void displayQuestion(){
        System.out.println("Question Text: ");
        System.out.println("    " + questionText);

        System.out.println("Amount Of Gaps: " + amountOfGaps);
    }

    public void createFillTheBlanks() {
        setQuestionTextUnderScore();
        System.out.println("FTB Question Text = " + questionText);
        setAmountOfGaps();

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
    public void setAmountOfGaps(){
//        String underScores = "___";
//        // sentenceSize has been reduced by 3 because last 2 characters of questionText will never contain three underscores.
//        // so -2 on the length and another -1 to reference the index rather than the element.
//        int sentenceSize = questionText.length() - 3;
//        for (int index = 0; index<sentenceSize; index++){
//            System.out.println(questionText.indexOf(index));
//        }
    }
}
