
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
        boolean createMoreQuestion;

        // Uses the parent class to set the question unique ID.
        uniqueIdentifier = setQuestionIdentifier();

        // Checks whether the unique identifier already within the hashmap.
        uniqueIdentifierExist(uniqueIdentifier);

        // The do while loop is designed for to create a question, in this case it's either a single choice question
        // or a fill the blanks question. Then moreQuestion question will determine whether the user want to create
        // more question either by a yes or no answer which will return true or false correspondingly.

        do{
            setQuestionType(uniqueIdentifier);
            createMoreQuestion = moreQuestion();
            questionsIdentifiers.get(uniqueIdentifier).getLast().displayQuestion();

        }while(createMoreQuestion);

        System.out.println("TESTING TWO QUESTION OUTPUT");
        questionsIdentifiers.get(uniqueIdentifier).get(0).displayQuestion();
        questionsIdentifiers.get(uniqueIdentifier).get(1).displayQuestion();
        System.out.println("TESTING TWO QUESTION OUTPUT");

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

    // Checks whether the valid unique identifier already has an existing arrayList within the hashMap.
    // If the uniqueIdentifier does not contain an ArrayList then it will instantiate a new one which is tied to it.
    public void uniqueIdentifierExist(String uniqueIdentifier){
        if (questionsIdentifiers.get(uniqueIdentifier) == null){
            questionsIdentifiers.put(uniqueIdentifier,new ArrayList<>());
            System.out.println("New Question Identifier");
        }
    }


    public void setQuestionText(){
        String userQuestionText;
        Scanner console = new Scanner(System.in);
        // Allows the user to enter question text which contains spaces rather than one continues text.
        // However, this does not consider if the user enters a new line.
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userQuestionText = console.next();
        questionText = userQuestionText;
    }


    public final void setQuestionType(String uniqueIdentifier){
        int questionType;
        boolean validQuestionType;

        validQuestionType = false;
        do{
            questionType = inputQuestionType();
            switch(questionType){
                case (1):
                    questionsIdentifiers.get(uniqueIdentifier).add(new SingleChoiceQuestion(bankList));
                    validQuestionType = true;
                    break;
                case (2):
                    questionsIdentifiers.get(uniqueIdentifier).add(new FillTheBlanks(bankList));
                    validQuestionType = true;
                    break;
                default:
                    System.out.println("Invalid Input, Enter A Integer From 1 - 2");
            }
        }while(!validQuestionType);
    }


    public final int inputQuestionType(){
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

    public boolean moreQuestion(){
        String moreQuestionAnswerInput;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Do You Want To Add More Question (Y/N): ");
            moreQuestionAnswerInput = console.next();

            if (moreQuestionAnswerInput.equalsIgnoreCase("y")){
                return true;
            }

            else if (moreQuestionAnswerInput.equalsIgnoreCase("n")){
                return false;
            }

            else{
                System.out.println("Please Enter Y Or N ");
            }

        }while(true);
    }
}



