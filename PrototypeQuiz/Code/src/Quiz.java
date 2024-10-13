/** @author Charlie Cheung
 * Quiz class is designed for the student to search up question banks in order to partake a test of question.
 * */

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can zero to questions.
import java.util.ArrayList;

// Scanner for user input.
// InputMismatchException is the Exception for Scanner.
import java.util.Scanner;
import java.util.InputMismatchException;

// Collection is used primarily for shuffling the ArrayList consisting Question objects.
import java.util.Collections;

public class Quiz {

    private String questionIdentifier; // Used to store quiz currently using question identifier.
    private final Bank bankList;  // Used to extract information from bank object.

    private final Question questionList;  // Used to extract information from question object.

    private final Scoreboard scoreboardList; // Used to submit score to scoreboard object.

    private long quizTimer; // Used to store the starting time when the quiz class was started.

    private ArrayList<Question> listOfQuestions; // Used to copy the Question object ArrayList of questions.

    private int amountOfQuestion; // Used to store the desired amount of question user wants to partake.

    private int currentQuestion; // Holds index position of the question user is currently on.

    private ArrayList<Integer> correctAnswers; // Holds an ArrayList where for each index position will
    // signify question x has been answered correct or not.

    private ArrayList<String> questionOrder;


    /** Constructor to instantiate the Quiz class.
     * @param setBankList in order to have access to bank object.
     * @param setQuestionList in order to have access to question object.
     * */
    public Quiz(Question setQuestionList, Bank setBankList, Scoreboard setScoreboardList){
        questionList = setQuestionList;
        bankList = setBankList;
        scoreboardList = setScoreboardList;
        listOfQuestions = new ArrayList<>();
    }


    /** Method to set up the quiz before starting the quiz. */
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


    /** Method to list all existing module and the user must select a module.
     * @return the selected module identifier.
     * */
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


    /** Method to select an existing bank name from selected module. */
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
                questionIdentifier = (moduleName + ":" + bankName);
                listOfQuestions = questionList.getQuestionList(questionIdentifier);
                if (listOfQuestions != null){
                    bankSelected = true;
                }
                else{
                    System.out.println("Question Bank Does Not Contain Any Question");
                }

            }

        }while(!bankSelected);
    }


    /** Method to get the amount of questions the user wants to partake. */
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
                    amountNonNegative = true;
                }

                else if(userAmount > questionBankSize){
                    amountOfQuestion = questionBankSize;
                    amountNonNegative = true;
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

        // Once the amount is valid then correctAnswer ArrayList size must be same as the amount.
        // This is because each item is associated with a question which can indicate if answered correctly.
        correctAnswers = new ArrayList<>();
        for (int index=0; index<amountOfQuestion; index++){
            correctAnswers.add(-1);
        }
    }


    /** Method to shuffle the ArrayList of questions so the user answer question in random order. */
    public void shuffleQuestions() {
        int arraySize = listOfQuestions.size();
        ArrayList<String> newQuestionNumberOrder = new ArrayList<>();
        ArrayList<Question> originalQuestion = new ArrayList<>();

        // Creates a temporary ArrayList which holds the original questions.
        for (int indexCopy = 0; indexCopy < arraySize; indexCopy++) {
            originalQuestion.add(listOfQuestions.get(indexCopy));
        }

        // Shuffles the questions.
        Collections.shuffle(listOfQuestions);

        // Loops through the shuffled ArrayList.
        for (int indexShuffled = 0; indexShuffled < arraySize; indexShuffled++) {
            // Loops through the original ArrayList.
            for (int indexOriginal = 0; indexOriginal < arraySize; indexOriginal++) {
                // Checks if shuffled element is same as original element.
                if (listOfQuestions.get(indexShuffled) == originalQuestion.get(indexOriginal)) {
                    // Add text Q followed by the original element.
                    newQuestionNumberOrder.add("Q" + (indexOriginal + 1));
                }
            }
        }

        // Displays the new question order.
        System.out.println("Question Order: ");
        for (int indexDisplay = 0; indexDisplay<amountOfQuestion; indexDisplay++){
            System.out.print(newQuestionNumberOrder.get(indexDisplay) + " ");
        }
        System.out.println("\n");

        questionOrder = newQuestionNumberOrder;
    }


    /** Method to start the quiz for the user to partake. */
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
            System.out.println("Question " + (questionOrder.get(currentQuestion)));
            questionObject = listOfQuestions.get(currentQuestion);
            // Reads the object class origin and starts the quiz for that question.
            startQuestion(questionObject);
            // Awaits for user input whether they want to navigate questions or end quiz.
            quizSessionOn = userNavigation();
            System.out.println();
        }while(quizSessionOn);
    }


    /** Method to check question type and instantiate the question in the correct class.
     * @param questionObject the given question object to instantiate.
     * */
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


    /** Method that allows the user to traverse between question or exist quiz session.
     * @return true if user desire to continue with quiz else false.
     * */
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


    /** Method to change current question to the next question.
     * @return true if user moved to next question else false.
     * */
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


    /** Method to change current question to the previous question.
     * @return true if user moved to previous question else false.
     * */
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


    /** Method to display user statistics from quiz session. */
    public void endQuiz(){
        String username;
        int scoreCorrect = 0;
        int unAnsweredQuestion = 0;
        // Static variable for conversion of units.
        final int millisToSecond = 1000;
        final int secondToMinute = 60;
        // Calculating the total time to finish the quiz.
        long timeTook = (System.currentTimeMillis() / millisToSecond) - quizTimer;
        Scanner console = new Scanner(System.in);

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
        System.out.println("Enter Your Username: ");
        username = console.next();

        // Submit the user quiz score to the scoreboard.
        scoreboardList.submitScore(username, questionIdentifier, scoreCorrect, amountOfQuestion);

        System.out.println("You Took " + timeTook / secondToMinute + " Minute " + timeTook % secondToMinute + " Seconds.");
        System.out.println("Your Score Is: " + scoreCorrect + " / " + amountOfQuestion + ".");
        System.out.println("You Didn't Answer " + unAnsweredQuestion + " Questions.");
    }
}
