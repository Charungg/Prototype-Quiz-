// HashMap is a dictionary used to reference to a specific thing.
// Useful in this example to keep track of bank identifiers associated to which module.
import java.util.HashMap;
// Used to grab user input.
import java.util.Scanner;

public class Bank {

    private Module moduleList; // Holds the module object used to abstract information about the module.

    // Using a HashMap to reference each bank identifiers is associated to what element of the moduleIdentifiers ArrayList
    private HashMap<String,Integer> bankIdentifiers;

    // Constructor to instantiate the bank object.
    public Bank(Module setModuleList) {
        moduleList = setModuleList;
        bankIdentifiers = new HashMap<String,Integer>();
        System.out.println("Bank CREATED");
    }

    // Used to display how many bank identifier there is and associated to which module identifiers.
    public void displayBank(){
        // If bank is empty display to the user that there is no bank identifiers.
        if (bankIdentifiers.isEmpty()){
            System.out.println("There Is No Bank");
        }

        // Displays the user each bank identifiers and their corresponding module.
        else{
            System.out.println("-----Display Bank-----");
            for (String i:bankIdentifiers.keySet()){
                System.out.println(getModuleIdentifier(bankIdentifiers.get(i)) + "-->" + i);

            }
            System.out.println("---------------------");
        }
    }


    // Used to add the user inputted bank identifier and from which module identifiers into the bank identifiers HashMap.
    public void createBank() {

        // Returns the user inputted bank identifier.
        String userInputBankIdentifier = userInputBankIdentifier();
        // Returns the index position from a moduleIdentifiers ArrayList which is associated to the bank identifier.
        int userModuleIndex = setModuleIdentifier();

        // Adds the bank identifiers with the index position of a module identifier into the bank identifiers HashMap.
        bankIdentifiers.put(userInputBankIdentifier,userModuleIndex);
        System.out.println(bankIdentifiers);
    }

    // Designed to return a minimum of 15 character bank identifier inputted by the user.
    public String userInputBankIdentifier() {
        String userInputBank;
        // bankNameValid is used to check if the user has inputted in a correct format.
        boolean bankNameValid = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("Enter A Bank Identifier (Maximum 15 characters): ");
            userInputBank = console.next();

            // User input must be minimum of 15 characters long.
            if (userInputBank.length() <= 15) {
                System.out.println("Bank Name Is Valid");
                bankNameValid = true;

            // An example of a bank identifier will be shown if it's inputted wrong.
            } else {
                System.out.println("Too many Characters! ");
                System.out.println("Example: QuestionBank01");
            }
        } while (!bankNameValid);

        // Returns a minimum of 15 character string.
        return userInputBank;
    }

    // Checks whether a user input module identifier exist within the moduleIdentifier ArrayList.
    // If module identifiers exists then it returns the index position of module identifier.
    public int setModuleIdentifier(){
        String userInputModuleIdentifier;
        // Used to check whether the module exist.
        boolean moduleFound = false;
        // Used to return a module identifier index position from the moduleIdentifier ArrayList from the module object.
        int moduleIdentifierIndex;
        Scanner console = new Scanner(System.in);


        do {
            System.out.println("Enter A Existing Module Identifier To Link To From Bank Identifier: ");
            moduleList.displayModule();
            userInputModuleIdentifier = console.next();

            // Goes through all module identifiers from the moduleIdentifiers and see if any matches with the user input.
            for (moduleIdentifierIndex = 0; moduleIdentifierIndex < moduleList.getModuleIdentifiersSize(); moduleIdentifierIndex++) {
                if (userInputModuleIdentifier.equals(moduleList.getModuleIdentifiers(moduleIdentifierIndex))){
                    System.out.println("Found Existing Module Identifier");
                    moduleFound = true;
                    break;
                }
            }

            if (!moduleFound){
                System.out.println("Module Identifier Could Not Be Found, Try Again");
            }

        }while(!moduleFound);

        return moduleIdentifierIndex;
    }

    // Return bank identifiers in a HashMap format.
    public HashMap<String,Integer> getBankIdentifiers(){
        return bankIdentifiers;
    }

    // Return module identifier from a given index number
    public String getModuleIdentifier(int indexNumber){
        return moduleList.getModuleIdentifiers(indexNumber);
    }
}


