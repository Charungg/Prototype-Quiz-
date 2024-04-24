import java.util.Scanner;
import java.util.ArrayList;

public class SingleChoiceQuestion extends Question{

    private ArrayList<String> answerChoice;

    private static final int amountOfQuestionChoice = 10;

    private ArrayList<Integer> correctChoice;


    public SingleChoiceQuestion(Bank setBankList) {
        super(setBankList);
        answerChoice = new ArrayList<>(amountOfQuestionChoice);
        correctChoice = new ArrayList<Integer>();
    }





}
