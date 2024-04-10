import java.util.HashMap;
import java.util.Scanner;

public class Bank {

    private Module moduleList;

    private HashMap<String,Integer> bankIdentifiers;


    public Bank(Module setModuleList) {
        moduleList = setModuleList;
        bankIdentifiers = new HashMap<String,Integer>();
        System.out.println("Bank CREATED");
    }


    public void createBank() {
        String userInputBankIdentifier = userInputBankIdentifier();
        int userModuleElement = setModuleIdentifier();

        bankIdentifiers.put(userInputBankIdentifier,userModuleElement);
        System.out.println(bankIdentifiers);
    }


    public String userInputBankIdentifier() {
        String userInputBank;
        boolean bankNameValid = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("Enter A Bank Identifier (Maximum 15 characters): ");
            userInputBank = console.next();

            if (userInputBank.length() <= 15) {
                System.out.println("Bank Name Is Valid");
                bankNameValid = true;

            } else {
                System.out.println("Too many Characters! ");
                System.out.println("Example: QuestionBank01");
            }
        } while (!bankNameValid);

        return userInputBank;
    }


    public int setModuleIdentifier(){
        String userInputModuleIdentifier;
        boolean moduleFound = false;
        int moduleIdentifierElement;
        Scanner console = new Scanner(System.in);


        do {
            System.out.println("Enter A Existing Module Identifier To Link To From Bank Identifier: ");
            moduleList.displayModule();
            userInputModuleIdentifier = console.next();

            for (moduleIdentifierElement = 0; moduleIdentifierElement < moduleList.getModuleIdentifiersSize(); moduleIdentifierElement++) {
                if (userInputModuleIdentifier.equals(moduleList.getModuleIdentifiers(moduleIdentifierElement))){
                    System.out.println("Found Existing Module Identifier");
                    moduleFound = true;
                    break;
                }
            }

            if (!moduleFound){
                System.out.println("Module Identifier Could Not Be Found, Try Again");
            }

        }while(!moduleFound);

        return moduleIdentifierElement;
    }

    public HashMap<String,Integer> getBankIdentifiers(){
        return bankIdentifiers;
    }


    public String getModuleIdentifier(int indexNumber){
        return moduleList.getModuleIdentifiers(indexNumber);
    }
}


