import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bank {

    private Module moduleList;

    // Change it into an array which holds a bankIdentifiers and also the element of connected Module.
    private ArrayList<String> bankIdentifiers;


    public Bank(Module setModuleList) {
        moduleList = setModuleList;

        bankIdentifiers = new ArrayList<String>();
        System.out.println("Bank CREATED");
    }


    public void createBank() {
        String userInputBankIdentifier = userInputBankIdentifier();
        int userModuleElement = userInputModuleIdentifier();

        System.out.println("Bank Identifier = " + userInputBankIdentifier);
        System.out.println("In Element" + userModuleElement);
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


    public int userInputModuleIdentifier(){
        String userInputModuleIdentifier;
        boolean moduleFound = false;
        int moduleIdentifierElement;
        ArrayList<String> tempModuleList = new ArrayList<String>();
        tempModuleList = moduleList.getModuleIdentifiers();
        Scanner console = new Scanner(System.in);


        do {
            System.out.println("Enter A Existing Module Identifier To Link To From Bank Identifier: ");
            moduleList.displayModule();
            userInputModuleIdentifier = console.next();

            for (moduleIdentifierElement = 0; moduleIdentifierElement < tempModuleList.size(); moduleIdentifierElement++) {
                if (userInputModuleIdentifier.equals(tempModuleList.get(moduleIdentifierElement))){
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
}
