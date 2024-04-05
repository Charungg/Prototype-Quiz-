import java.util.ArrayList;
import java.util.Scanner;

public class Module {

    private Bank bankList;
    private ArrayList<String> moduleIdentifiers;
    public Module(){
        moduleIdentifiers = new ArrayList<String>();
        System.out.println("Module Created");
    }

    public void addModuleIdentifiers(String setModuleIdentifiers){
        moduleIdentifiers.add(setModuleIdentifiers);
    }

    public void addBank(){
        /**
         * Create Module (tick)
         * Then Bank
         * But For Which Module
         * This One Okay I'll Check
         * Found It
         * I Ask For Bank Unique ID
         * Check If Its length <= 15
         * Then Link That Bank To The Module
         */
        // START FROM HERE...
    }

    public void displayModule(){
        if (moduleIdentifiers.size()==0){
            System.out.println("There Is No Module");
        }

        else{
            System.out.println("-------Display-------");
            for (int i=0; i<moduleIdentifiers.size(); i++){
                System.out.println(moduleIdentifiers.get(i));
            }

            System.out.println("---------------------");
        }
    }
}
