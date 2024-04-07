import java.util.Scanner;
// Scanner is used to grab user input.


public class SchoolQuizApplication {
    // Is the main program which helps the user on getting started and a menu for the user to navigate the program.

    private Module moduleList;

    private Bank bankList;

    private Question questionList;

    // Instantiate an array which holds Modules.

    public static void main(String[] args) {
        /* INSERT instantiation of Module, Bank, Questions... objects
           THEN instantiate the File object then run a load() function.
         */
        Module userModule = new Module();
        Bank userBank = new Bank(userModule);
        Question userQuestion = new Question(userBank);


        //  FILE ... = new File();
        SchoolQuizApplication menu = new SchoolQuizApplication(userModule, userBank, userQuestion);
        menu.logIn();
    }


    public SchoolQuizApplication(Module setModuleList, Bank setBankList, Question setQuestionList) {
        moduleList = setModuleList;
        bankList = setBankList;
        questionList = setQuestionList;
    }


    public void logIn() {
        String userInput;
        boolean loggedIn = false;
        Scanner console = new Scanner(System.in);

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
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("-------Teacher Menu------- \n" +
                    "1) Show All Module And Question Bank \n" +
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

            try {
                userInput = console.nextInt();
                exit = processTeacherMenu(userInput);
            } catch (Exception e) {
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);
    }


    public boolean processTeacherMenu(int teacherOption) {
        switch (teacherOption) {
            case (1):
                moduleList.displayModule();
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


    public void printStudentMenu() {
        int userInput;
        boolean exit = false;
        Scanner console = new Scanner(System.in);

        do {
            System.out.println("-------Student Menu------- \n" +
                    "1) Search QuestionBank \n" +
                    "2) Change User \n" +
                    "3) Exit \n" +
                    "---------------------------");

            System.out.println("Enter A Option (1-8): ");

            try {
                userInput = console.nextInt();
                exit = processStudentMenu(userInput);
            } catch (Exception e) {
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        } while (!exit);

    }


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
                break;
            default:
                System.out.println("Invalid Input, Please Try Again");
        }
        return false;
    }


}


