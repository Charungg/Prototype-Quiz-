// HashMap is a dictionary used to reference to a specific thing.
// Useful in this example to keep track of bank identifiers associated to which module.
import java.util.*;
// Used to grab user input.


public class Bank {

    private final Module moduleList; // Holds the module object used to abstract information about the module.

    // Using a HashMap to reference each bank identifiers is associated to what element of the moduleIdentifiers ArrayList
    private final HashMap<String, ArrayList<String>> bankIdentifiers;


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
            System.out.println("Enter A Existing Module: ");
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

    // This checks whether the given argument of module name and bank name exist within bank identifier hash map.
    public boolean moduleAndBankIdentifiersExist(String moduleNameExist, String bankNameExist){
        // This is a for loop that goes all the module names first.
        for (String moduleName: bankIdentifiers.keySet()){
            // Checks whether the given module names matches up with a key.
            if (moduleName.equals(moduleNameExist)) {
                // The hash map I created also holds an arrayList of bank name as many bank identifier can be associated
                // with one module, which is why beneath it's a loop that goes through all the element within the arrayList
                // which contains the bank names.
                for (int index=0; index<bankIdentifiers.get(moduleName).size(); index++){
                    // Checks whether the module key contains a bank name value matches with the given bank name.
                    if (bankIdentifiers.get(moduleName).get(index).equals(bankNameExist)){
                        System.out.println("Question Identifier Exist");
                        // Both module and bank name has been found therefore returning a pass verification.
                        return true;
                    }
                }

                // Bank name cannot be found therefore returns a failed verification.
                System.out.println("Bank Name Does Not Exist");
                return false;
            }
        }
        // Module name cannot be found therefore returns a failed verification.
        System.out.println("Module Name Does Not Exist");
        return false;
    }

    // Test this.
    public void removeBank(Question questionList){
        String moduleNameInput;
        String bankNameInput;

        moduleNameInput = userInputModuleIdentifier();
        bankNameInput = userInputBankIdentifier();
        if (moduleAndBankIdentifiersExist(moduleNameInput,bankNameInput)){
            if (questionList.isQuestionIdentifierEmpty(moduleNameInput + ":" +bankNameInput)) {
                bankIdentifiers.get(moduleNameInput).remove(bankNameInput);
                System.out.println("Bank Removed");
                removeBankIfEmpty(moduleNameInput);
            }

            else{
                System.out.println("Bank Cannot Be Removed Since It's Not Empty");
            }
        }
    }


    public void removeBankIfEmpty(String moduleName){
        if (bankIdentifiers.get(moduleName).isEmpty()){
            bankIdentifiers.remove(moduleName);
            System.out.println("Removed Bank Since It's Empty");
        }
    }


    public boolean isModuleEmpty(String moduleName){
        return bankIdentifiers.get(moduleName) == null;
    }
}


