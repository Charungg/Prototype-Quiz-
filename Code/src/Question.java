
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

    public final void createQuestion(){
        String uniqueIdentifier;
        boolean createMoreQuestion = false;
        Scanner console = new Scanner(System.in);

        uniqueIdentifier = setQuestionIdentifier();
        System.out.println(questionsIdentifiers);

        //START FROM HERE


    }


    // Needed
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

    public final void addQuestion(){
        Scanner console = new Scanner(System.in);


    }
}



