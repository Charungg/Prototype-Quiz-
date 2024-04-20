// HashMap is a dictionary used to reference to a specific thing.
// Useful in this example to keep track of bank identifiers associated to which module.
import java.util.*;
// Used to grab user input.


public class Bank {

    private Module moduleList; // Holds the module object used to abstract information about the module.

    // Using a HashMap to reference each bank identifiers is associated to what element of the moduleIdentifiers ArrayList
    private HashMap<String, ArrayList<String>> bankIdentifiers;


    // Constructor to instantiate the bank object.
    public Bank(Module setModuleList) {
        moduleList = setModuleList;
        bankIdentifiers = new HashMap<>();
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
                for (String keyName : bankIdentifiers.keySet()){
                    System.out.println(keyName);
                    for (int i=0; i<bankIdentifiers.get(keyName).size(); i++){
                        System.out.println("    " + bankIdentifiers.get(keyName).get(i));
                    }
                }

            System.out.println("---------------------");
        }
    }


    // Used to add the user inputted bank identifier and from which module identifiers into the bank identifiers HashMap.
    public void createBank() {

        // Returns the user inputted bank identifier.
        String userInputBankIdentifier = userInputBankIdentifier();
        // Returns the index position from a moduleIdentifiers ArrayList which is associated to the bank identifier.
        String userInputModuleIdentifier = userInputModuleIdentifier();
        if (bankIdentifiers.get(userInputModuleIdentifier) == null){
            bankIdentifiers.put(userInputModuleIdentifier, new ArrayList<>());
        }

        bankIdentifiers.get(userInputModuleIdentifier).add(userInputBankIdentifier);
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
    public String userInputModuleIdentifier(){
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
            for (moduleIdentifierIndex = 0; moduleIdentifierIndex < moduleList.getModuleIdentifierSize(); moduleIdentifierIndex++) {
                if (userInputModuleIdentifier.equals(moduleList.getModuleIdentifierElement(moduleIdentifierIndex))){
                    System.out.println("Found Existing Module Identifier");
                    moduleFound = true;
                    break;
                }
            }

            if (!moduleFound){
                System.out.println("Module Identifier Could Not Be Found, Try Again");
            }

        }while(!moduleFound);

        return userInputModuleIdentifier;
    }

    public boolean moduleIdentifierExist(String moduleName){
        if (bankIdentifiers.get(moduleName) != null){
            return true;
        }

        else{
            System.out.println("Module Identifier Does Not Exist");
            return false;
        }
    }

    public boolean bankIdentifierExist(String moduleName, String bankName){
        for (int i=0; i< bankIdentifiers.get(moduleName).size(); i++){
            if (bankIdentifiers.get(moduleName).get(i).equals(bankName)){
                return true;
            }
        }

        System.out.println("Bank Identifier Does Not Exist");
        return false;
    }
}


