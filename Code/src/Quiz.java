/** @author Charlie Cheung */

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can zero to many modules.
import java.util.ArrayList;

// Scanner for user input.
// InputMismatchException is the Exception for Scanner.
import java.util.Scanner;
import java.util.InputMismatchException;

// Collection is used primarily for shuffling the ArrayList consisting Question objects.
import java.util.Collections;

// Quiz class is designed for the student to search up question banks in order to partake a test of question.
public class Quiz {

    private final Bank bankList;  // Used to extract information from bank object.

    private final Question questionList;  // Used to extract information from question object.

    private long quizTimer; // Used to store the starting time when the quiz class was started.

    private ArrayList<Question> listOfQuestions; // Used to copy the Question object ArrayList of questions.

    private int amountOfQuestion; // Used to store the desired amount of question user wants to partake.

    private int currentQuestion; // Holds index position of the question user is currently on.

    private final ArrayList<Integer> correctAnswers; // Holds an ArrayList where for each index position will
                                                     // signify question x has been answered correct or not.


    // Constructor for Quiz class.
    public Quiz(Question setQuestionList, Bank setBankList){
        questionList = setQuestionList;
        bankList = setBankList;
        listOfQuestions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }


    // Method to set up the quiz before starting the quiz.
    public void setUpQuiz(){
        String moduleName;

        // Displays a list of module and the user must select one.
        moduleName = listModuleBanksAndSelect();

        // Displays a list of bank and the user must select one.
        // After that it searches the question identifier and copy the
        // ArrayList of question objects.
        selectBank(moduleName);

        // User inputs the amount of question they want to partake.
        setUserInputAmountOfQuestion();

        // Shuffles the questions around
        shuffleQuestions();

        startQuiz();
    }


    // Method to list all existing module and the user must select a module.
    public String listModuleBanksAndSelect() {
        String moduleName;
        boolean moduleBankDisplayed = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Existing Module");
            moduleName = console.next();

            if (bankList.isModuleEmpty(moduleName)){
                System.out.println("Module Does Not Exist");
            }

            else{
                System.out.println("----------Banks----------");
                bankList.displayBankFromModule(moduleName);
                System.out.println("------------------------");
                moduleBankDisplayed = true;
            }

        }while(!moduleBankDisplayed);

        return moduleName;
    }


    // Method to select an existing bank name from selected module.
    public void selectBank(String moduleName){
        String bankName;
        boolean bankSelected = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Bank Which Links To The Module");
            bankName = console.next();

            // Method to check if module name and bank name exist with the bank object HashMap
            // as a key and value.
            if (bankList.moduleAndBankIdentifiersExist(moduleName, bankName)){
                listOfQuestions = questionList.getQuestionList(moduleName + ":" + bankName);
                bankSelected = true;
            }

        }while(!bankSelected);
    }


    // Method to get the amount of questions the user wants to partake.
    public void setUserInputAmountOfQuestion(){
        int userAmount;
        int questionBankSize;
        boolean amountNonNegative = false;
        Scanner console = new Scanner(System.in);

        questionBankSize = listOfQuestions.size();

        do{
            System.out.println("Enter Number Of Question To Display (1-" + questionBankSize + "): ");
            try{
                userAmount = console.nextInt();

                // Checks if user input of amount question to partake is valid.
                if (userAmount >= 1 && userAmount <=questionBankSize){
                    amountOfQuestion = userAmount;

                    // Once the amount is valid then correctAnswer ArrayList size must be same as the amount.
                    // This is because each item is associated with a question which can indicate if answered correctly.
                    for (int index=0; index<amountOfQuestion; index++){
                        correctAnswers.add(-1);
                    }
                    amountNonNegative = true;
                }

                else if(userAmount > questionBankSize){
                    amountOfQuestion = questionBankSize;
                }

                else{
                    System.out.println("Invalid Input, Please Enter A Non-Negative Number");
                }
            }

            catch (InputMismatchException e){
                console.nextLine();
                System.out.println("Invalid Input, Please Enter A Integer");
            }
        }while(!amountNonNegative);

    }


    // Method to shuffle the ArrayList of questions so the user answer question in random order.
    public void shuffleQuestions(){
        Collections.shuffle(listOfQuestions);
        // While loop is removing questions from a specific index until it meets the amount of question requirement.
        while (amountOfQuestion != listOfQuestions.size()){
            listOfQuestions.remove(amountOfQuestion);
        }
    }


    // Method to start the quiz for the user to partake.
    public void startQuiz(){
        // This int variable cannot be changed.
        final int millisToSecond = 1000;
        // User starts at question 1.
        currentQuestion = 0;
        // Holds the current system time.
        quizTimer = System.currentTimeMillis() / millisToSecond;
        boolean quizSessionOn;
        Question questionObject;

        do{
            System.out.println("Question " + (currentQuestion + 1));
            questionObject = listOfQuestions.get(currentQuestion);
            // Reads the object class origin and starts the quiz for that question.
            startQuestion(questionObject);
            // Awaits for user input whether they want to navigate questions or end quiz.
            quizSessionOn = userNavigation();
            System.out.println();
        }while(quizSessionOn);
    }


    public void startQuestion(Question questionObject){
        boolean isAnswerCorrect = false;
        // Switch case is used find the question object type and start the question in quiz format.
        switch((questionObject.getClass()).toString()){
            // Each case will start the question and return true if answered correctly else false.
            case ("class SingleChoiceQuestion"):
                isAnswerCorrect = ((SingleChoiceQuestion) questionObject).startQuizQuestion();
                break;
            case ("class FillTheBlanks"):
                isAnswerCorrect = ((FillTheBlanks) questionObject).startQuizQuestion();
                break;
        }

        // These if else statements are used to indicate which question is answered correctly or false.
        if (isAnswerCorrect){
            correctAnswers.set(currentQuestion,1);
        }

        else{
            correctAnswers.set(currentQuestion,0);
        }
    }


    // Method that returns false to exit program else change current question
    // and return true to continue with quiz.
    public boolean userNavigation(){
        int userInput;
        boolean validNavigation = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("""
                    1) Next Question\s
                    2) Previous Question\s
                    3) End Quiz\s
                    Enter Numbered Function: \s
                    """);

            try{
                userInput = console.nextInt();

                switch(userInput){
                    case (1):
                        validNavigation = nextQuestion();
                        return true;
                    case (2):
                        validNavigation = previousQuestion();
                        return true;
                    case (3):
                        endQuiz();
                        validNavigation = true;
                        break;
                    default:
                        System.out.println("Invalid Input, Please Enter 1-3");
                }
            }

            catch(InputMismatchException e){
                console.nextLine();
                System.out.println("Invalid Input, Please Enter An Integer");
            }
        }while(!validNavigation);
        return false;
    }


    // Method to change current question to the next question.
    public boolean nextQuestion(){
        // Changes to the next question as long it's not currently on the last question.
        if (currentQuestion + 1 < amountOfQuestion){
            currentQuestion = currentQuestion + 1;
            System.out.println("Next Question");
            return true;
        }
        else{
            System.out.println("This Is The Last Question!!");
            return false;
        }
    }


    // Method to change current question to the previous question.
    public boolean previousQuestion(){
        // Changes to the previous question as long it's not currently on the first question.
        if (currentQuestion -1 > -1){
            currentQuestion = currentQuestion - 1;
            System.out.println("Previous Question");
            return true;
        }
        else{
            System.out.println("This Is The First Question!!");
            return false;
        }
    }

    public void endQuiz(){
        int scoreCorrect = 0;
        int unAnsweredQuestion = 0;
        // Static variable for conversion of units.
        final int millisToSecond = 1000;
        final int secondToMinute = 60;
        // Calculating the total time to finish the quiz.
        long timeTook = (System.currentTimeMillis() / millisToSecond) - quizTimer;

        // Goes through the ArrayList of answer status.
        for (int isAnswerCorrect: correctAnswers){
            switch (isAnswerCorrect){
                // Answered the question correctly.
                case (1):
                    scoreCorrect = scoreCorrect + 1;
                    break;
                // Didn't Answer the question.
                case (-1):
                    unAnsweredQuestion = unAnsweredQuestion + 1;
                    break;
                // Answered the question incorrectly.
                case (0):
                    break;
                default:
                    System.out.println("Checking Answer Error Occurred: " + isAnswerCorrect);
            }
        }

        System.out.println("You Took " + timeTook / secondToMinute + " Minute " + timeTook % secondToMinute + " Seconds.");
        System.out.println("Your Score Is: " + scoreCorrect + " / " + amountOfQuestion + ".");
        System.out.println("You Didn't Answer " + unAnsweredQuestion + " Questions.");
    }
}
