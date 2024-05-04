// FileWriter allows the object to write in the module text file.
// IOException is the catch for FileWriter
import java.io.FileWriter;
import java.io.IOException;

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can zero to many modules.
import java.util.ArrayList;

// Used to grab user input.
import java.util.Scanner;

public class Module {
    private final ArrayList<String> moduleIdentifiers; // Used to hold all the unique identifiers of the module.

    // Constructor to instantiate the module object.
    public Module(){
        moduleIdentifiers = new ArrayList<>();
    }


    // Used to add a module identifiers to the attribute.
    public void createModule(){
        String userInput;
        // moduleCreated is used to check whether a module identifiers inputted by the user is valid.
        boolean moduleCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Module Identifier (Maximum 7 Characters): ");
            userInput = console.next();

            // User input must be at least 7 characters long.
            if ((userInput.length()) <= 7){
                moduleIdentifiers.add(userInput);
                System.out.println("Module Created");
                moduleCreated = true;
            }

            // Display the user a correct example of a module identifiers.
            else{
                System.out.println("Too many Characters! ");
                System.out.println("Example: CS12320");
            }

        }while(!moduleCreated);
    }


    // Return True if given parameter of moduleName exist within the moduleIdentifier.
    public boolean moduleIdentifierExist(String moduleName){
        // Loops through moduleIdentifier which gives an existing module identifier from the ArrayList.
        for (String moduleID: moduleIdentifiers){
            // If Argument of moduleName is the same as an existing module Identifier then
            // user input module name does exist within the system, so return true.
            if (moduleID.equals(moduleName)){
                return true;
            }
        }
        // After the loop and if condition is not met then that means the module name does not exist
        // therefore returning false since  argument is non-existent.
        return false;
    }


    // Design to remove the moduleIdentifier from the ArrayList which contains all
    // the module names that exist.
    public void removeModule(Bank bankList){
        String moduleNameInput;
        Scanner console = new Scanner(System.in);

        // Gets user input of a module name.
        System.out.println("Enter Module Name To Be Deleted ");
        moduleNameInput = console.next();

        // Checks if the module name not exist within Bank object which holds module names as keys
        // and their corresponding bankIdentifier
        if (bankList.isModuleEmpty(moduleNameInput)){
            // If module name does not exist in Bank object then in Module object it will remove
            // module name from moduleIdentifier, if module name does not exist within moduleIdentifier
            // then nothing will be removed.
            moduleIdentifiers.remove(moduleNameInput);
            System.out.println("Module Removed");
        }

        // If check fails then that means Bank object has the module name which means you can't remove the module
        // since it's not empty.
        else{
            System.out.println("Module Name Cannot Be Removed Since It's Not Empty");
        }
    }



    // Functions below are designed to save and load the Module class.

    // Used to save module identifiers in a text file.
    public void saveModule(FileWriter file){
        try{
            // Loops through all the module identifiers and write in text file line by line.
            for (String moduleName : moduleIdentifiers){
                file.write(moduleName + "\n");
            }
            file.close();
        }

        catch(IOException e){
            System.out.println("Saving Module Error Occurred: ");
        }
    }


    // Method to load the module identifiers within the module text file.
    // Parameter reader next line will contain module identifier.
    public void loadModule(Scanner reader){
        // Continue to add module identifier from text file as there is still text next.
        while (reader.hasNextLine()){
            moduleIdentifiers.add(reader.nextLine());
        }
        System.out.println("Loaded Module");
    }
}
