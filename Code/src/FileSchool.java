/** @author Charlie Cheung */

// Used to create files and check if file exist.
import java.io.File;
import java.io.FileNotFoundException;

// Allows the object to write in the files.
import java.io.FileWriter;
import java.io.IOException;

// In order to get user input.
import java.util.Scanner;


// FileSchool class is used to save and load module, bank and question attributes.
public class FileSchool {
    private final Module moduleList; // Used to save and load module object.

    private final Bank bankList; // Used to save and load bank object.

    private final Question questionList; // Used to save and load question object.

    private final File moduleFile; // Used to hold module file.

    private final File bankFile; // Used to hold bank file.

    private final File questionFile; // Used to hold question file.


    // Constructor for FileSchool class.
    public FileSchool(Module setModule, Bank setBank, Question setQuestion){
        moduleList = setModule;
        bankList = setBank;
        questionList = setQuestion;

        // The parent is SaveFiles which is a directory followed by the name of the file itself.
        moduleFile = new File("SaveFiles", "Module File.txt");
        bankFile = new File("SaveFiles","Bank File.txt");
        questionFile = new File("SaveFiles","Question File.txt");
    }


    // Method to save module, bank and question attributes in their corresponding text file.
    public void saveApp(){
        // Checks if the module text file exist before starting saving into them.
        // Else it would be saving into nothing which produce an error.
        if (!moduleFile.exists()) {
            // Creates the file if they don't already exist.
            createFile();
        }

        // Trys to save module, bank and question attributes to their own files.
        try{
            moduleList.saveModule(new FileWriter(moduleFile));
            bankList.saveBank(new FileWriter(bankFile));
            questionList.saveQuestion(new FileWriter(questionFile));
        }

        catch(IOException e){
            System.out.println("Error Saving Failed: ");
        }
    }


    // Method to create the files if they don't already exist.
    public void createFile(){
        try{
            // Creates a SaveFile directory to store save files.
            new File("SaveFiles").mkdirs();
            moduleFile.createNewFile();
            bankFile.createNewFile();
            questionFile.createNewFile();

        }
        catch(IOException e){
            System.out.println("An error occurred.");
        }
    }


    // Method to load module, bank and question.
    public void loadApp(){
        try{
            // As long the module file exist then it means save text file exist to load from.
            if (moduleFile.exists()){
                moduleList.loadModule(new Scanner(moduleFile));
                bankList.loadBank(new Scanner(bankFile));
                questionList.loadQuestion(new Scanner(questionFile));
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Module File Cannot Be Found");
        }
    }


}
