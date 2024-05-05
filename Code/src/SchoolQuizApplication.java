/**
 * Main program which helps the user on getting started and a menu for the user to navigate the program.
 * @author Charlie Cheung
 *
 * */

// Scanner is used to grab user input.
import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolQuizApplication {

    private final Module moduleList;  // Used to hold the Module object.

    private final Bank bankList;  // Used to hold the bank Object.

    private final Question questionList;  // Used to hold the Question object.

    private final Quiz quizProgram; // Used to hold the Quiz object.

    private final FileSchool fileData; //Used to hold the FileSchool object.


    // Upon starting the program it will bring the user to login as student or teacher.
    public static void main(String[] args) {
        SchoolQuizApplication menu = new SchoolQuizApplication();
        // Program starts asking the user to login as a student or teacher.
        menu.logIn();
    }

    /** Contractor that allows the application object to have access to module, bank and question objects. */
    public SchoolQuizApplication() {
        // Below I instantiated Module, Bank and Question objects, they get passed through the next instantiation.
        // For example the Bank needs to have access to the Module object to check what module identifier is valid and links to.
        Module userModule = new Module();
        Bank userBank = new Bank(userModule);
        Question userQuestion = new Question(userBank);

        // Quiz object will get the Question and Bank arguments to have access to valid Bank which will be used to gain
        // access to an ArrayList of questions.
        Quiz userQuiz = new Quiz(userQuestion,userBank);

        // Create an instance of FileSchool of which it will load any existing module, bank and question from text files.
        FileSchool userFile = new FileSchool(userModule,userBank,userQuestion);

        // Application will hold every instantiated object apart from SingleChoiceQuestion and FillTheBlanks class which is inheriting
        // from the Question class, in order to run specific functions of whatever the user navigates to.
        moduleList = userModule;
        bankList = userBank;
        questionList = userQuestion;
        quizProgram = userQuiz;
        fileData = userFile;

        // Load any previous modules, banks and questions from text files.
        fileData.loadApp();
    }


    /** Method used to determine whether the user is a student or teacher. */
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

    /** Method to display the teacher menu to the teacher user. */
    public void printTeacherMenu() {
        int userInput;
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("""
                    -------Teacher Menu-------
                    1) Search Question Bank\s
                    2) Add Module\s
                    3) Add Question Bank\s
                    4) Add Question\s
                    5) Remove Module\s
                    6) Remove Question Bank\s
                    7) Remove Question\s
                    8) Change User\s
                    9) Exit And Save\s
                    --------------------------""");

            System.out.println("Enter A Option (1-9): ");

            // User should enter a number within the range of the provided options above.
            try {
                userInput = console.nextInt();
                exit = processTeacherMenu(userInput);
            }


            // If the user enters a non-numerical character then the user will have to re-enter an option.
            catch (InputMismatchException e) {
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }


        } while (!exit);
    }


    /** Method to process teacher user input to teacher menu
     * @param teacherOption teacher's option from menu
     * @return false once completed else it will exist program.
     * */
    public boolean processTeacherMenu(int teacherOption) {
        // Each switch case represents a corresponding option from the teacher menu.
        switch (teacherOption) {
            case (1):
                bankList.searchQuestionBank();
                System.out.println();
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
                questionList.createQuestion();
                break;
            case (5):
                System.out.println("REMOVING MODULE");
                moduleList.removeModule(bankList);
                break;
            case (6):
                System.out.println("REMOVING Question Bank");
                bankList.removeBank(questionList);
                break;
            case (7):
                System.out.println("Removing Question");
                questionList.removeQuestion();
                break;
            case (8):
                logIn();
                break;
            case (9):
                fileData.saveApp();
                System.out.println("Exit And Save");
                System.exit(0);
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }


   /** Method to display the student menu to a student user. */
    public void printStudentMenu() {
        int userInput;
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("""
                    -------Student Menu-------\s
                    1) Search Question Bank\s
                    2) Start Quiz\s
                    3) Change User\s
                    4) Exit And Save\s
                    ---------------------------""");

            System.out.println("Enter A Option (1-4): ");

            // User should enter a number within the range of the provided options above.
            try {
                userInput = console.nextInt();
                exit = processStudentMenu(userInput);
            }

            // If the user enters a non-numerical character then the user will have to re-enter an option.
            catch (InputMismatchException e) {
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);

    }

    /** Method to process student user input to student menu
     *  @param studentOption student's option from menu
     *  @return false once completed else it will exit program.
     * */
    public boolean processStudentMenu(int studentOption) {
        switch (studentOption) {
            case (1):
                System.out.println("Searching Question Bank");
                bankList.searchQuestionBank();
                break;
            case (2):
                System.out.println("Starting Quiz");
                quizProgram.setUpQuiz();
                break;
            case (3):
                System.out.println("Change User");
                logIn();
                break;
            case (4):
                fileData.saveApp();
                System.out.println("Exist And Save");
                System.exit(0);
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }
}


