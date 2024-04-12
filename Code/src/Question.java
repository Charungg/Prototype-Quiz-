import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Question {

    Bank bankList;

    ArrayList<String> questionsIdentifiers;

    ArrayList<String> questionText;



    public Question() {
        questionsIdentifiers = new ArrayList<String>();
        questionText = new ArrayList<String>();
        System.out.println("Question CREATED");
    }


    public void createQuestion(Bank setBankList){
        bankList = setBankList;

        setQuestionIdentifier();
        setQuestionText();
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
    }

    public void setQuestionText(){
        String userInputQuestionText;
        Scanner console = new Scanner(System.in);

        System.out.println("Enter A Question Text: ");
        userInputQuestionText = console.next();
        System.out.println("QT="+userInputQuestionText);
        questionText.add(userInputQuestionText);
    }

}

