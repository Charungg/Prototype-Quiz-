import java.util.ArrayList;

public class FillTheBlanks extends Question{
    private final Question questionList;

    private final ArrayList<ArrayList<String>> answersFillTheBlanks;

    public FillTheBlanks(Bank setBankList, Question setQuestionList) {
        super(setBankList);
        questionList = setQuestionList;
        answersFillTheBlanks = new ArrayList<>();
    }

    public void createFillTheBlanks(){
       System.out.println("Please enter ___ (3 underscores) to represent a blank");
       questionList.setQuestionText();

       System.out.println(questionList.questionText);

       //TBC
       // Count how many fill in the gaps
       // Then ask what answers should be in the gaps and save them in the arrayList


    }

}
