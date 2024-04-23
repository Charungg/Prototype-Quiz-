// Scanner is used to grab user input.
import java.util.Scanner;


// Is the main program which helps the user on getting started and a menu for the user to navigate the program.
public class SchoolQuizApplication {

    private final Module moduleList;  // Used to hold the Module object.

    private final Bank bankList;  // Used to hold the bank Object.

    private final Question questionList;  // Used to hold the Question object.

    private final SingleChoiceQuestion singleChoiceQuestionList; // Used to hold the SingleChoiceQuestion object.

    private final FillTheBlanks fillTheBlanksList; // Used to hold the FillTheBlanks object.

    // Upon starting the program it will bring the user to login as student or teacher.
    public static void main(String[] args) {
        SchoolQuizApplication menu = new SchoolQuizApplication();
        menu.logIn();
    }

    // Allows the application to have access to module, bank and question objects.
    public SchoolQuizApplication() {
        Module userModule = new Module();
        Bank userBank = new Bank(userModule);
        Question userQuestion = new Question(userBank);
        SingleChoiceQuestion userSingleChoiceQuestion = new SingleChoiceQuestion(userBank, userQuestion);
        FillTheBlanks userFillInTheBlanks = new FillTheBlanks();

        moduleList = userModule;
        bankList = userBank;
        questionList = userQuestion;
        singleChoiceQuestionList = userSingleChoiceQuestion;
        fillTheBlanksList = userFillInTheBlanks;
    }

    // Used to determine whether the user is a student or teacher.
    public void logIn() {
        String userInput;
        boolean loggedIn = false;
        Scanner console = new Scanner(System.in);

        do {
            // User should input whether they are a teacher or student.
            System.out.println("Enter To Log In As Student(S) Or Teacher(T): ");
            userInput = console.next();
            System.out.println(userInput);

            // If the user inputs the letter S then student menu will appear.
            if (userInput.equalsIgnoreCase("s")){
                loggedIn = true;
                printStudentMenu();
            }

            // If the user inputs the letter T then teacher menu will appear.
            else if (userInput.equalsIgnoreCase("t")){
                loggedIn = true;
                printTeacherMenu();
            }

            // If the user inputs neither letter then they will be prompt to try again.
            else{
                System.out.println("Invalid input, try again");
            }
        }
        // If user hasn't entered an appropriate answer then it will ask for user input again.
        while (!loggedIn);
    }

    // Displays the teacher menu to the teacher user.
    public void printTeacherMenu() {
        int userInput;
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("""
                    -------Teacher Menu-------
                    1) Show All Module And Question Bank\s
                    2) Add Module\s
                    3) Add Question Bank\s
                    4) Add Question\s
                    5) Remove Module\s
                    6) Remove Question Bank\s
                    7) Remove Question\s
                    8) Change User\s
                    9) Exit\s
                    --------------------------""");

            System.out.println("Enter A Option (1-9): ");

            // User should enter a number within the range of the provided options above.
            try {
                userInput = console.nextInt();
                exit = processTeacherMenu(userInput);
            }


            // If the user enters a non-numerical character then the user will have to re-enter an option.
            catch (Exception e) {
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }


        } while (!exit);
    }


    // After the user enters a numerical value within the teacher menu it will run the corresponding function.
    public boolean processTeacherMenu(int teacherOption) {
        // Each switch case represents a corresponding option from the teacher menu.
        switch (teacherOption) {
            case (1):
                moduleList.displayModule();
                bankList.displayBank();
                questionList.displayQuestion();
                break;
            case (2):
                System.out.println("ADDING MODULE");
                moduleList.createModule();
                break;
            case (3):
                System.out.println("ADDING QB");
                bankList.createBank();
                break;
            case (4):
                System.out.println("ADDING Q");
                questionList.createQuestion(singleChoiceQuestionList, fillTheBlanksList);
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
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }


   // Displays the student menu to a student user.
    public void printStudentMenu() {
        int userInput;
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("""
                    -------Student Menu-------\s
                    1) Search QuestionBank\s
                    2) Change User\s
                    3) Exit\s
                    ---------------------------""");

            System.out.println("Enter A Option (1-8): ");

            // User should enter a number within the range of the provided options above.
            try {
                userInput = console.nextInt();
                exit = processStudentMenu(userInput);
            }

            // If the user enters a non-numerical character then the user will have to re-enter an option.
            catch (Exception e) {
//                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);

    }

    // After the student user enters a numerical character within the range.
    // It will then process the user input to run the corresponding task.
    public boolean processStudentMenu(int studentOption) {
        switch (studentOption) {
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
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }
}


