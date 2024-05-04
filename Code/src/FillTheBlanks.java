import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FillTheBlanks extends Question{

    private int amountOfBlanks;

    private final ArrayList<String> answerForBlanks;

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
        console.useDelimiter("\\n");

        for (int gapNumber = 1; gapNumber<=amountOfBlanks; gapNumber++){
            System.out.println("Enter Answer For Blank " + gapNumber + ":");
            answerText = console.next();
            answerForBlanks.add(answerText);
        }
    }



    // Functions below are designed specifically for quiz session.
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

        return userAnswerList.equals(answerForBlanks);
    }



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


    public FillTheBlanks (Bank setBankList,Scanner reader){
        super(setBankList);
        answerForBlanks = new ArrayList<>();

        questionText = reader.nextLine();
        amountOfBlanks = Integer.parseInt(reader.nextLine());

        for (int setAnswer = 0; setAnswer<amountOfBlanks; setAnswer++){
            answerForBlanks.add(reader.nextLine());
        }
    }
}
