import java.io.*;
import java.util.Scanner;

public class FileSchool {
    private final Module moduleList;

    private final Bank bankList;

    private final Question questionList;

    private final File moduleFile;

    private final File bankFile;

    private final File questionFile;

    public FileSchool(Module setModule, Bank setBank, Question setQuestion){
        moduleList = setModule;
        bankList = setBank;
        questionList = setQuestion;

        moduleFile = new File("SaveFiles", "Module File.txt");
        bankFile = new File("SaveFiles","Bank File.txt");
        questionFile = new File("SaveFiles","Question File.txt");
    }


    public void saveApp(){
        if (!moduleFile.exists()) {
            createFile();
        }

        try{
            moduleList.saveModule(new FileWriter(moduleFile));
            bankList.saveBank(new FileWriter(bankFile));
            questionList.saveQuestion(new FileWriter(questionFile));
        }

        catch(IOException e){
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }


    public void createFile(){
        try{
            new File("SaveFiles").mkdirs();
            moduleFile.createNewFile();
            bankFile.createNewFile();
            questionFile.createNewFile();

        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }





    public void loadApp(){
        try{
            if (moduleFile.exists()){
                moduleList.loadModule(new Scanner(moduleFile));
                bankList.loadBank(new Scanner(bankFile));
                questionList.loadQuestion(new Scanner(questionFile));
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Module File Cannot Be Found");
            e.printStackTrace();
        }
    }


}
