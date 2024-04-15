import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Question {

    private Bank bankList;

    protected ArrayList<String> questionsIdentifiers;

    protected ArrayList<String> questionText;

    protected ArrayList<ArrayList<ArrayList>> listOfQuestions;


    // Constructor to instantiate the question object.
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new ArrayList<String>();
        questionText = new ArrayList<String>();

        // Creates the actual list to hold questionBank
        listOfQuestions = new ArrayList<>();

        // questionBank itself
        listOfQuestions.add(new ArrayList<ArrayList>());

        // Data such as the questionType and questionIndex
        // I think
//        listOfQuestions.get(0).add(new ArrayList());


    }

    public void setQuestionBank(String questionType, int questionIndex){
        listOfQuestions.get(0).add(new ArrayList());
        listOfQuestions.get(0).get(0).add("SCQ");
        listOfQuestions.get(0).get(0).add(0);
        listOfQuestions.get(0).add(new ArrayList());
        listOfQuestions.get(0).get(1).add("SCQ");
        listOfQuestions.get(0).get(1).add(1);
        listOfQuestions.get(0).add(new ArrayList());
        listOfQuestions.get(0).get(2).add("FTB");
        listOfQuestions.get(0).get(2).add(0);
        System.out.println(listOfQuestions);


    }


    public void createQuestion(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        setQuestionIdentifier();
    }

    public void userQuestionType(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        int userSelectQuestionType = 0;
        boolean identifiedQuestionType = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter Question Type (1 or 2): ");
            System.out.println("1) Single Choice Question ");
            System.out.println("2) Fill The Blanks ");

            try{
                userSelectQuestionType = console.nextInt();
                if (userSelectQuestionType >= 1 && userSelectQuestionType <=2){
                    identifiedQuestionType = true;
                }

                else{
                    System.out.println("Invalid Input, Please Try Again");
                }

            }
            catch (Exception e){
                System.out.println("Invalid Input, Please Try Again");
            }

        }while(!identifiedQuestionType);

        switch (userSelectQuestionType){
            case 1:
                System.out.println("SingleChoiceQuestion");
                singleChoiceQuestionList.createSingleChoiceQuestion();
                break;

            case 2:
                System.out.println("FillTheBlanks");
                break;
        }
    }


    public void setQuestionIdentifier(){
        String userInputModuleIdentifier;
        String userInputBankIdentifier;
        String moduleName;
        boolean questionIdentifierFound = false;
        HashMap<String,Integer> bankIdentifiers = bankList.getBankIdentifiers();
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Existing Module Identifier: ");
            userInputModuleIdentifier = console.next();

            System.out.println("Enter A Existing Bank Identifier: ");
            userInputBankIdentifier = console.next();

            for (String i: bankIdentifiers.keySet()){
                moduleName = bankList.getModuleIdentifier(bankIdentifiers.get(i));

                if (moduleName.equals(userInputModuleIdentifier) && i.equals(userInputBankIdentifier)){
                    questionIdentifierFound = true;
                    break;
                }
            }

        }while(!questionIdentifierFound);
        questionsIdentifiers.add((userInputModuleIdentifier + ":" + userInputBankIdentifier));
        System.out.println(questionsIdentifiers);
    }



    public void setQuestionText(){
        String userInputQuestionText;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userInputQuestionText = console.next();
        questionText.add(userInputQuestionText);
    }

}

