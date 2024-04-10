import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Question {

    private Bank bankList;

    private ArrayList<String> questionsIdentifiers;

    private ArrayList<String>[][] questionTextAndOptions;

    private ArrayList<Integer> correctAnswer;



    public Question(Bank setBankList) {
        bankList = setBankList;
        questionsIdentifiers = new ArrayList<String>();
        System.out.println("Question CREATED");
    }

    public void createQuestion(){
        String userInput;
        boolean questionCreated = false;
        Scanner console = new Scanner(System.in);

        setQuestionIdentifier();
        setQuestionTextAndOptions();
        setCorrectAnswer();


    }

    public void setQuestionIdentifier(){
        String userInputModuleIdentifier;
        String userInputBankIdentifier;
        String moduleName;
        boolean questionIdentifierFound = false;
        HashMap<String,Integer> bankIdentifiers = bankList.getBankIdentifiers();
        Scanner console = new Scanner(System.in);

        do{
            boolean moduleFound = false;
            boolean bankFound = false;

            System.out.println("Enter a existing Module Identifier ");
            userInputModuleIdentifier = console.next();

            System.out.println("Enter a existing Bank Identifier ");
            userInputBankIdentifier = console.next();

            for (String i: bankIdentifiers.keySet()){
                moduleName = bankList.getModuleIdentifier(bankIdentifiers.get(i));
                System.out.println("TESTING");
                System.out.println(bankIdentifiers.get(i)); // 0
                System.out.println(i); //Donkey

                System.out.println(bankList.getModuleIdentifier(0)); //Shrek

                if (moduleName.equals(userInputModuleIdentifier) && i.equals(userInputBankIdentifier)){
                    questionIdentifierFound = true;
                    break;
                }
            }

        }while(!questionIdentifierFound);

        questionsIdentifiers.add((userInputModuleIdentifier + ":" + userInputBankIdentifier));
    }

    public void setQuestionTextAndOptions(){

    }

    public void setCorrectAnswer(){

    }


}

