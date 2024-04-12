// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can zero to many modules.
import java.util.ArrayList;
// Used to grab user input.
import java.util.Scanner;

// 
public class Module {
    private ArrayList<String> moduleIdentifiers;


    public Module(){
        moduleIdentifiers = new ArrayList<String>();
        System.out.println("Module Created");

    }


    public void displayModule(){
        if (moduleIdentifiers.size()==0){
            System.out.println("There Is No Module");
        }

        else{
            System.out.println("----Display Module----");
            for (int i=0; i<moduleIdentifiers.size(); i++){
                System.out.println(moduleIdentifiers.get(i));
            }
            System.out.println("---------------------");
        }
    }

    public void createModule(){
        String userInput;
        boolean moduleCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Module Identifier (Maximum 7 Characters): ");
            userInput = console.next();

            if ((userInput.length()) <= 7){
                moduleIdentifiers.add(userInput);
                System.out.println("CREATED");
                moduleCreated = true;
            }
            else{
                System.out.println("Too many Characters! ");
                System.out.println("Example: CS12320");
            }

        }while(!moduleCreated);
    }


    public String getModuleIdentifiers(int i){
        return moduleIdentifiers.get(i);
    }

    public int getModuleIdentifiersSize(){
        return moduleIdentifiers.size();
    }
}
