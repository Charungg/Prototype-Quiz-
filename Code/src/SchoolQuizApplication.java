import java.util.ArrayList;
// ArrayList is used to instantiate an array which can shrink and grow in size.
import java.util.Scanner;
// Scanner is used to grab user input.


public class SchoolQuizApplication {
    // Is the main program which helps the user on getting started and a menu for the user to navigate the program.

    private Module moduleList;

    // Instantiate an array which holds Modules.

    private static Scanner console = new Scanner(System.in);
    // Instantiate a Scanner object before everything else.


    public static void main(String[] args) {
        /* INSERT instantiation of Module, Bank, Questions... objects
           THEN instantiate the File object then run a load() function.
         */
        Question userQuestion = new Question();
        Bank userBank = new Bank();
        Module userModule = new Module();

    //  FILE ... = new File();
        SchoolQuizApplication menu = new SchoolQuizApplication(userModule);
    }


    public SchoolQuizApplication(Module setmoduleListObject) {
        moduleList = setmoduleListObject;
        logIn();

    }

    public void logIn() {
        String userInput;
        boolean loggedIn = false;

        do {
            System.out.println("Enter To Log In As Student(S) Or Teacher(T): ");
            userInput = console.next();
            System.out.println(userInput);

            switch (userInput.toUpperCase()) {
                case ("S"):
                    loggedIn = true;
                    printStudentMenu();
                    break;
                case ("T"):
                    loggedIn = true;
                    printTeacherMenu();
                    break;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
        while (!loggedIn);
        // System.out.println("RESULT = " + isTeacher);

    }


    public void printTeacherMenu() {
        int userInput;
        boolean exit = false;

        do {
            System.out.println("-------Teacher Menu------- \n" +
                               "1) Show All Module And Question Bank" +
                               "2) Add Module \n" +
                               "3) Add Question Bank \n" +
                               "4) Add Question \n" +
                               "5) Remove Module \n" +
                               "6) Remove Question Bank \n" +
                               "7) Remove Question \n" +
                               "8) Change User \n" +
                               "9) Exit \n" +
                               "--------------------------");

            System.out.println("Enter A Option (1-9): ");

            try{
                userInput = console.nextInt();
                exit = processTeacherMenu(userInput);
            }

            catch(Exception e){
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);
    }


    public boolean processTeacherMenu(int teacherOption){
        switch (teacherOption){
            case (1):
                moduleList.displayModule();
                break;
            case (2):
                System.out.println("ADDING MODULE");
                createModule();
                break;
            case (3):
                System.out.println("ADDING QB");
                break;
            case (4):
                System.out.println("ADDING Q");
                break;
            case (5):
                System.out.println("R MODULE");
                break;
            case (6):
                System.out.println("R QB");
                break;
            case (7):
                System.out.println("R Q");
                break;
            case (8):
                logIn();
                break;
            case (9):
                System.out.println("E");
                System.exit(0);
                return true;
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }


    public void printStudentMenu(){
        int userInput;
        boolean exit = false;

        do {
            System.out.println("-------Student Menu------- \n" +
                    "1) Search QuestionBank \n" +
                    "2) Change User \n" +
                    "3) Exit \n" +
                    "---------------------------");

            System.out.println("Enter A Option (1-8): ");

            try{
                userInput = console.nextInt();
                exit = processStudentMenu(userInput);
            }

            catch(Exception e){
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);

    }


    public boolean processStudentMenu(int studentOption){
        switch (studentOption){
            case (1):
                System.out.println("S QB");
                break;
            case (2):
                System.out.println("C U");
                logIn();
                break;
            case (3):
                System.out.println("E");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }


    public void createModule(){
        String userInput;
        boolean moduleCreated = false;

        do{
            System.out.println("Enter A Module Identifier: ");
            userInput = console.next();
            System.out.println("YOU TYPED: " + userInput);
            System.out.println("SIZE IS: " + userInput.length());

            if ((userInput.length()) <= 7){
                moduleList.addModuleIdentifiers(userInput);
                System.out.println("CREATED");
                moduleCreated = true;
            }
            else{
                System.out.println("Invalid Input, Try Again ");
                System.out.println("Example: CS12320");
            }

        }while(!moduleCreated);
    }


}


