
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Question {

    private final Bank bankList;

    private final HashMap<String,ArrayList<Question>> questionsIdentifiers;

    protected String questionText;


    // Constructor to instantiate the question object.
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new HashMap<>();
    }


    public void displayQuestion(){
        for (String questionObject: questionsIdentifiers.keySet()){
            System.out.println(questionObject);
        }
    }


    public final void createQuestion(){
        String uniqueIdentifier;
        String moreQuestionAnswer;
        boolean createMoreQuestion = true;
        Scanner console = new Scanner(System.in);

        uniqueIdentifier = setQuestionIdentifier();
        uniqueIdentifierExist(uniqueIdentifier);

        setQuestionType(uniqueIdentifier);
        questionsIdentifiers.get(uniqueIdentifier).getLast().displayQuestion();
    }


    public final String setQuestionIdentifier(){
        String uniqueIdentifier;
        String userInputModuleIdentifier;
        String userInputBankIdentifier;
        boolean questionIdentifierCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Existing Module Identifier: ");
            userInputModuleIdentifier = console.next();


            System.out.println("Enter A Existing Bank Identifier: ");
            userInputBankIdentifier = console.next();


            if(bankList.moduleAndBankIdentifiersExist(userInputModuleIdentifier, userInputBankIdentifier)){
                questionIdentifierCreated = true;
            }

        }while(!questionIdentifierCreated);

        uniqueIdentifier = userInputModuleIdentifier + ":" + userInputBankIdentifier;
        questionsIdentifiers.put(uniqueIdentifier, new ArrayList<>());

        return uniqueIdentifier;
    }


    public void uniqueIdentifierExist(String uniqueIdentifier){
        if (questionsIdentifiers.get(uniqueIdentifier) == null){
            questionsIdentifiers.put(uniqueIdentifier,new ArrayList<>());
            System.out.println("New Question Identifier");
        }
    }


    public void setQuestionText(){
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        questionText = console.next();
    }


    public final void setQuestionType(String uniqueIdentifier){
        int questionType;
        boolean validQuestionType;

        validQuestionType = false;
        do{
            questionType = setQuestionType();
            switch(questionType){
                case (1):
                    questionsIdentifiers.get(uniqueIdentifier).add(new SingleChoiceQuestion(bankList));
                    validQuestionType = true;
                    break;
                case (2):
                    questionsIdentifiers.get(uniqueIdentifier).add(new SingleChoiceQuestion(bankList)); // FTB EDITION LATER
                    validQuestionType = true;
                    break;
                default:
                    System.out.println("Invalid Input, Enter A Integer From 1 - 2");
            }
        }while(!validQuestionType);
    }


    public final int setQuestionType(){
        int questionType = -1;
        Scanner console = new Scanner(System.in);

        try{
            System.out.println("""
                                --------------------------
                                Enter A Question Type (1-2)\s
                                1) Single Choice Question\s
                                2) Fill The Blanks\s
                                --------------------------
                                """);
            questionType = console.nextInt();
        }
        catch (Exception e){
            console.nextLine();
            System.out.println("Invalid Input, Try Again");
        }
        return questionType;
    }
}



