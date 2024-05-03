// ArrayList is a collection used to store specific data types (must be specified)
// that will grow and shrink in size.
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// HashMap is a dictionary used to reference to a specific thing.
// Useful in this example to keep track of bank identifiers associated to which module.
import java.util.HashMap;

// Used to grab user input and manipulate user input.
import java.util.Scanner;



public class Bank {

    private final Module moduleList; // Holds the module object used to extract information about the module.

    // Using a HashMap to store module identifiers as keys (String) and each key will have a value of an ArrayList
    // which contains bank identifiers that belongs to the key (module identifier).
    // Used a HashMap here is valid because keys cannot be duplicate whereas values.
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
                // These loops grab a new key name for each iteration that exist within the bankIdentifier HashMap.
                for (String keyName : bankIdentifiers.keySet()){
                    // Displays key name without any spaces before it.
                    System.out.println(keyName);
                    // Within the key name another loop will happen which displays all
                    // corresponding value of the key through index.
                    for (int i=0; i<bankIdentifiers.get(keyName).size(); i++){
                        // Display value of the key with four spaces before it to indicate it's a value not key.
                        System.out.println("    " + bankIdentifiers.get(keyName).get(i));
                    }
                }
            System.out.println("---------------------");
        }
    }

    // Use this for FR something!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    // Designed to display just the values (bank identifier) of the given key name (module identifier).
    // The function will be passed a module name which will run a function beforehand that checks if it's valid .
    public void displayBankFromModule(String moduleName){
        // Loops through the value of bankIdentifier HashMap of the given arguments of module identifier
        // therefore displaying only bank identifier that exist from the given module identifier.
        for (String bankName: bankIdentifiers.get(moduleName)){
            System.out.println(bankName);
        }
    }


    // Used to add the user inputted bank identifier and from which module identifiers into the bank identifiers HashMap.
    public void createBank() {

        // Returns the user inputted bank identifier.
        String userInputBankIdentifier = setBankIdentifier();
        // Returns the index position from a moduleIdentifiers ArrayList which is associated to the bank identifier.
        String userInputModuleIdentifier = setModuleIdentifier();
        if (bankIdentifiers.get(userInputModuleIdentifier) == null){
            bankIdentifiers.put(userInputModuleIdentifier, new ArrayList<>());
        }

        bankIdentifiers.get(userInputModuleIdentifier).add(userInputBankIdentifier);
        System.out.println(bankIdentifiers);
    }

    // Designed to return a minimum of 15 character bank identifier inputted by the user.
    public String setBankIdentifier() {
        String bankIdentifier;
        // bankNameValid is used to check if the user has inputted in a correct format.
        boolean bankNameValid = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("Enter A Bank Identifier (Maximum 15 characters): ");
            bankIdentifier = console.next();

            // User input must be minimum of 15 characters long.
            if (bankIdentifier.length() <= 15) {
                System.out.println("Bank Name Is Valid");
                bankNameValid = true;

            // An example of a bank identifier will be shown if it's inputted wrong.
            } else {
                System.out.println("Too many Characters! ");
                System.out.println("Example: QuestionBank01");
            }
        } while (!bankNameValid);

        // Returns a minimum of 15 character string.
        return bankIdentifier;
    }

    // Checks whether a user input module identifier exist within the moduleIdentifier ArrayList.
    // If module identifiers exists then it returns the index position of module identifier.
    public String setModuleIdentifier(){
        String moduleIdentifier;
        // Used to check whether the module exist.
        boolean moduleFound = false;
        // Used to return a module identifier index position from the moduleIdentifier ArrayList from the module object.
        int moduleIdentifierIndex;
        Scanner console = new Scanner(System.in);


        do {
            System.out.println("Enter A Existing Module: ");
            moduleIdentifier = console.next();

            // Goes through all module identifiers from the moduleIdentifiers and see if any matches with the user input.
            if (moduleList.moduleIdentifierExist(moduleIdentifier)){
                    System.out.println("Found Existing Module Identifier");
                    moduleFound = true;
                    break;
            }

            else{
                System.out.println("Module Identifier Could Not Be Found, Try Again");
            }

        }while(!moduleFound);

        return moduleIdentifier;
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



    // These functions below are designed in mind of removing values (bank identifier) from bankIdentifier
    // and remove key (module identifier) if it is empty from bankIdentifier.
    public void removeBank(Question questionList){
        String moduleName;
        String bankName;

        // Grabs user input of module identifier and bank identifier
        moduleName = setModuleIdentifier();
        bankName = setBankIdentifier();

        // After grabbing inputs it will be passed to a function and passing arguments of module and bank user input
        // of which the function will return true if module and bank identifier correspond to each other and exist
        // there if boolean function is true it means the input module and bank is valid.
        if (moduleAndBankIdentifiersExist(moduleName,bankName)){

            // Before removing bank identifiers it needs to have access to the Question object and checking if the question
            // identifier (by concatenating module and bank identifier) does not exist because bank identifier
            // must not exist within the questionIdentifier within question object.
            if (questionList.isQuestionIdentifierEmpty(moduleName + ":" +bankName)) {

                // After valid checking module and bank Identifier and question identifier does not exist then it
                // will remove the bank identifier from the corresponding module identifier.
                bankIdentifiers.get(moduleName).remove(bankName);
                System.out.println("Bank Removed");
                // After removing a bank identifier, it will remove the module identifier from the HashMap
                // if module identifier does not have any bank identifier.
                removeModuleIfEmpty(moduleName);
            }

            else{
                System.out.println("Bank Cannot Be Removed Since It's Not Empty");
            }
        }
    }


    // Removes module identifier key from bankIdentifier HashMap if it does not contain
    // any values (bank identifier).
    public void removeModuleIfEmpty(String moduleName){
        // Checks if a module contains no bank identifier.
        if (bankIdentifiers.get(moduleName).isEmpty()){
            // If true then it removes the module from the bankIdentifier HashMap.
            bankIdentifiers.remove(moduleName);
            System.out.println("Removed Bank Since It's Empty");
        }
    }


    // Checks if module identifier is empty/not exist within the HashMap bankIdentifier.
    public boolean isModuleEmpty(String moduleName){
        return bankIdentifiers.get(moduleName) == null;
    }



    // Functions below are designed to save and load the Bank class.
    public void saveBank(FileWriter file){
        try{
            for (String moduleName: bankIdentifiers.keySet()){
                file.write(moduleName + ":\n");
                for (String bankName: bankIdentifiers.get(moduleName)){
                    file.write("    " + bankName + "\n");
                }
            }
            file.close();
        }

        catch(IOException e){
            System.out.println("Saving Module Error Occurred: ");
            e.printStackTrace();
        }
    }
}


