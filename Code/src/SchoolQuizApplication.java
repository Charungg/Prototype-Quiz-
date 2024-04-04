import java.util.ArrayList;
// ArrayList is used to instantiate an array which can shrink and grow in size.
import java.util.Scanner;
// Scanner is used to grab user input.


public class SchoolQuizApplication {
    // Is the main program which helps the user on getting started and a menu for the user to navigate the program.

    ArrayList<Module> associateModule;
    // Instantiate an array which holds Modules.

    static Scanner console = new Scanner(System.in);
    // Instantiate a Scanner object.


    public static void main(String[] args) {
        /* INSERT instantiation of Module, Bank, Questions... objects
           THEN instantiate the File object then run a load() function.
         */
        SchoolQuizApplication menu = new SchoolQuizApplication();
    }


    public SchoolQuizApplication() {
        logIn();
    }


    public void logIn() {
        String userInput;
        boolean loggedIn = false;

        do {
            System.out.println("Enter To Log In As Student(S) Or Teacher(T): ");
            userInput = console.next();
            System.out.println(userInput);

            switch (userInput) {
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
                               "1) Add Module \n" +
                               "2) Add Question Bank \n" +
                               "3) Add Question \n" +
                               "4) Remove Module \n" +
                               "5) Remove Question Bank \n" +
                               "6) Remove Question \n" +
                               "7) Change User \n" +
                               "8) Exit \n" +
                               "--------------------------");

            System.out.println("Enter A Option (1-8): ");

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
                System.out.println("ADDING MODULE");
                break;
            case (2):
                System.out.println("ADDING QB");
                break;
            case (3):
                System.out.println("ADDING Q");
                break;
            case (4):
                System.out.println("R MODULE");
                break;
            case (5):
                System.out.println("R QB");
                break;
            case (6):
                System.out.println("R Q");
                break;
            case (7):
                logIn();
                break;
            case (8):
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
}


