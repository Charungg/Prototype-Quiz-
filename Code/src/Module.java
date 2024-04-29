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
        System.out.println("Module Created");
    }

    // Used to display how many module identifier there is.
    public void displayModule(){
        // If there is no module identifiers then display empty to the user.
        if (moduleIdentifiers.isEmpty()){
            System.out.println("There Is No Module");
        }

        // Else loop and display all the module identifiers to the user.
        else{
            System.out.println("----Display Module----");
            for (String moduleIdentifier: moduleIdentifiers){
                System.out.println(moduleIdentifier);
            }
            System.out.println("---------------------");
        }
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
                System.out.println("CREATED");
                moduleCreated = true;
            }

            // Display the user a correct example of a module identifiers.
            else{
                System.out.println("Too many Characters! ");
                System.out.println("Example: CS12320");
            }

        }while(!moduleCreated);
    }

    // Returns a specific element from the moduleIdentifier.
    public String getModuleIdentifierElement(int moduleIdentifierIndex){
        return moduleIdentifiers.get(moduleIdentifierIndex);
    }

    // Returns the size of the moduleIdentifier.
    public int getModuleIdentifierSize(){
        return moduleIdentifiers.size();
    }


    public void removeModule(Bank bankList){
        String moduleNameInput;
        Scanner console = new Scanner(System.in);

        System.out.println("Enter Module Name To Be Deleted ");
        moduleNameInput = console.next();
        if (bankList.isModuleEmpty(moduleNameInput)){
            moduleIdentifiers.remove(moduleNameInput);
            System.out.println("Module Removed");
        }

        else{
            System.out.println("Module Name Cannot Be Removed Since It's Not Empty");
        }


    }
}
