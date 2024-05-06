/** @author Charlie Cheung
 * Question class is designed for mainly storing and saving question and queries of question.
 * */

// FileWriter allows the object to write in the module text file.
// IOException is the catch for FileWriter
import java.io.FileWriter;
import java.io.IOException;

// ArrayList is an array which can grow and shrink in size.
// Useful in this scenario because you can zero to many modules.
import java.util.ArrayList;

// Scanner for user input.
// InputMismatchException is the Exception for Scanner.
import java.util.Scanner;
import java.util.InputMismatchException;

// HashMap is used to store many unique key that can contain many non-unique values.
import java.util.HashMap;

public class Question {

    private final Bank bankList; // Used to extra information about the bank.

    // HashMap which contains the question identifiers as keys which can holds zero
    // to many Question child class which are the question specific type, such as
    // SingleChoiceQuestion or FillTheBlanks.
    private final HashMap<String,ArrayList<Question>> questionsIdentifiers;

    protected String questionText; // Is used for subclass to have inherited question text
    // without duplicated code on each subclass.


    /** Constructor to instantiate the question class.
     * @param setBankList in order to have access to bank object.
     * */
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new HashMap<>();
    }


    /** Method as placeholder for child class to override and be used in parent class which uses override */
    public void displayQuestion() {
    }


    /** Method to create a question object of a specific type (SingleChoice or FillTheBlanks) and adds it into the HashMap questionIdentifier. */
    public final void createQuestion(){
        String uniqueIdentifier;
        boolean createMoreQuestion;

        // Uses the parent class to set the question unique ID.
        uniqueIdentifier = askUserQuestionIdentifier();

        // Adds if unique identifier is not within the hashmap.
        addUniqueIdentifierIfNew(uniqueIdentifier);

        // The do while loop is designed for to create a question, in this case it's either a single choice question
        // or a fill the blanks question. Then moreQuestion question will determine whether the user want to create
        // more question either by a yes or no answer which will return true or false correspondingly.

        do{
            // Asks the user to select a question type and instantiate it and add it to HashMap.
            createQuestionType(uniqueIdentifier);

            // Determines if the user wants to create more questions
            createMoreQuestion = moreQuestion();

            // Displays question details of the question that just got instantiated.
            questionsIdentifiers.get(uniqueIdentifier).getLast().displayQuestion();

        }while(createMoreQuestion);

        System.out.println("Question Created");
    }


    /** Method to set question identifier in HashMap key.
     * @return user input question identifier.
     * */
    public final String askUserQuestionIdentifier(){
        String uniqueIdentifier;
        String userInputModuleIdentifier;
        String userInputBankIdentifier;
        boolean questionIdentifierCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            // User input of module identifier.
            System.out.println("Enter A Existing Module Identifier: ");
            userInputModuleIdentifier = console.next();


            // User input of bank identifier.
            System.out.println("Enter A Existing Bank Identifier: ");
            userInputBankIdentifier = console.next();


            if(bankList.moduleAndBankIdentifiersExist(userInputModuleIdentifier, userInputBankIdentifier)){
                questionIdentifierCreated = true;
            }

        }while(!questionIdentifierCreated);

        uniqueIdentifier = userInputModuleIdentifier + ":" + userInputBankIdentifier;

        return uniqueIdentifier;
    }


    // Checks whether the valid unique identifier already has an existing arrayList within the hashMap.
    // If the uniqueIdentifier does not contain an ArrayList then it will instantiate a new one which is tied to it.

    /** Method to add unique identifier to question identifier if it doesn't exist as key.
     * @param uniqueIdentifier is a user inputted unique question identifier.
     * */
    public void addUniqueIdentifierIfNew(String uniqueIdentifier){
        if (questionsIdentifiers.get(uniqueIdentifier) == null){
            questionsIdentifiers.put(uniqueIdentifier,new ArrayList<>());
        }
    }


    /** Method for user to add question text. */
    public void setUserInputQuestionText(){
        String userQuestionText;
        Scanner console = new Scanner(System.in);
        // Allows the user to enter question text which contains spaces rather than one continues text.
        // However, this does not consider if the user enters a new line.
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userQuestionText = console.next();
        questionText = userQuestionText;
    }


    /** Method to create a question of a specific type decided by the user and added to the question identifier values in HashMap.
     * @param  uniqueIdentifier is the current question identifier use to add question to.
     * */
    public final void createQuestionType(String uniqueIdentifier){
        int questionType;
        boolean validQuestionType;

        validQuestionType = false;
        do{
            questionType = askUserQuestionType();
            // Switch case is used here for scalability if the user ever needs to add more question type.
            switch(questionType){
                // Each case will instantiate a question object of a specific type.
                case (1):
                    questionsIdentifiers.get(uniqueIdentifier).add(new SingleChoiceQuestion(bankList));
                    validQuestionType = true;
                    break;
                case (2):
                    questionsIdentifiers.get(uniqueIdentifier).add(new FillTheBlanks(bankList));
                    validQuestionType = true;
                    break;
                default:
                    System.out.println("Invalid Input, Enter A Integer From 1 - 2");
            }
        }while(!validQuestionType);
    }


    /** Method used to gather the user desired question type.
     * @return the amount of question the user wants to partake.
     * */
    public final int askUserQuestionType(){
        int questionType = -1;
        Scanner console = new Scanner(System.in);

        try{
            System.out.println("""
                                --------------------------
                                Enter A Question Type (1-2)\s
                                1) Single Choice Question\s
                                2) Fill The Blanks\s
                                --------------------------
                                """);
            questionType = console.nextInt();
        }
        catch (InputMismatchException e){
            console.nextLine();
            System.out.println("Invalid Input, Try Again");
        }

        return questionType;
    }


    /** Method to ask the user if they want to add more question.
     * @return return true if they user wants to add more question else false.
     * */
    public boolean moreQuestion(){
        String moreQuestionAnswerInput;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Do You Want To Add More Question (Y/N): ");
            moreQuestionAnswerInput = console.next();

            if (moreQuestionAnswerInput.equalsIgnoreCase("y")){
                return true;
            }

            else if (moreQuestionAnswerInput.equalsIgnoreCase("n")){
                return false;
            }

            else{
                System.out.println("Please Enter Y Or N ");
            }

        }while(true);
    }



    // These functions below are designed for removing questions.

    /** Method to select a question bank to remove a question. */
    public void selectQuestionBankToRemoveQuestion(){
        String uniqueIdentifier;
        uniqueIdentifier = askUserQuestionIdentifier();

        // Create a temporarily ArrayList which holds the ArrayList of questions from a specific question identifier.
        ArrayList<Question> questionObjects;
        // By passing the uniqueIdentifier through the questionIdentifier it will return ArrayList of the Questions associated to it.
        removeQuestion(questionsIdentifiers.get(uniqueIdentifier));

        // Removes the unique identifier from the HashMap if it contains zero questions.
        if (questionsIdentifiers.get(uniqueIdentifier) != null){
            removeQuestionIdentifierIfEmpty(uniqueIdentifier);
        }
    }


    /** Method to remove question */
    public void removeQuestion(ArrayList<Question> questionObjects){
        int questionSelection;
        boolean questionRemoved = false;
        Scanner console = new Scanner(System.in);

        if (!(questionObjects == null)){
            do{
                System.out.println("Enter A Question Number To Be Deleted: ");
                // Loop starts at one used to display question number.
                for (int index = 1 ; index<=questionObjects.size(); index++){
                    System.out.println("--------------------" + index + "--------------------");
                    questionObjects.get(index - 1).displayQuestion();
                    System.out.println();
                }

                try{
                    questionSelection = console.nextInt();

                    // If the question the user selected exist within the ArrayList then it will be deleted.
                    if (questionSelection >= 1 && questionSelection <= questionObjects.size()){
                        questionObjects.remove(questionSelection - 1);
                        System.out.println("Question Removed");
                        questionRemoved = true;
                    }

                    else{
                        System.out.println("Enter Question Number Does Not Exist");
                    }
                }

                catch(InputMismatchException e){
                    console.nextLine();
                    System.out.println("Please Enter An Integer");
                }
            }while(!questionRemoved);
        }
        else{
            System.out.println("Question Identifier Does Not Contain Any Question");
        }
    }

    /** Method check if question identifier exist within the HashMap questionIdentifier.
     * @param uniqueIdentifier is user selected question identifier.
     * @return true if selected question identifier ArrayList in questionIdentifier does not exist else false.
     * */
    public boolean isQuestionIdentifierExist(String uniqueIdentifier){
        return questionsIdentifiers.get(uniqueIdentifier) ==null;
    }


    /** Method to remove question identifier from key in HashMap if it does not contain any question.
     * @param uniqueIdentifier is a question identifier to be checked.
     * */
    public void removeQuestionIdentifierIfEmpty(String uniqueIdentifier){
        // Checks if argument question identifier has no question.
        if (questionsIdentifiers.get(uniqueIdentifier).isEmpty()){
            // If true then it will remove question identifier from HashMap.
            questionsIdentifiers.remove(uniqueIdentifier);
        }
    }


    /** Method to get an ArrayList of questions from a specific question identifier.
     * @param uniqueIdentifier is a question identifier which can extract ArrayList of questions from bankIdentifier.
     * @return an ArrayList of questions of different type (SingleChoiceQuestion or FillTheBlanks).
     * */
    public ArrayList<Question> getQuestionList(String uniqueIdentifier){
        System.out.println(questionsIdentifiers.get(uniqueIdentifier));
        return questionsIdentifiers.get(uniqueIdentifier);
    }



    // Methods below are designed to save and load the Question class.

    /** Method to save question identifier into question text file.
     * @param file in order to have access to question file. */
    public void saveQuestion(FileWriter file){
        try{
            // Loops through all HashMap key which contains question identifier.
            for (String questionName: questionsIdentifiers.keySet()){

                // Write module name and colon to signify it's a question identifier.
                file.write(questionName + ":\n");

                // Loops through all HashMap key which contains question object.
                for (Question questionObject : questionsIdentifiers.get(questionName)){
                    questionObject.saveQuestion(file);
                }
                file.write("\n");
            }
            file.close();
        }

        catch(IOException e){
            System.out.println("Saving Question Error Occurred: ");
        }
    }


    /** Method to load the question identifiers within the question text file.
     * @param reader used to have access to read from question text file and where it's left off.*/
    public void loadQuestion(Scanner reader) {
        String textFileLine;
        String questionName;
        String className;
        boolean allQuestionLoaded;

        // Keeps looping as long text file has lines to read.
        while (reader.hasNextLine()) {
            textFileLine = reader.nextLine();
            System.out.println(textFileLine);

            // Question identifiers are identifier through the text file
            // by having a colon in the line.
            if (textFileLine.contains(":")) {

                // Removes the last character of the line which removes the ending
                // colon, so it will only remain a question identifier.
                questionName = textFileLine.substring(0, textFileLine.length() - 1);

                // questionIdentifier is instantiated with question identifier
                // and an empty ArrayList.
                questionsIdentifiers.put(questionName, new ArrayList<>());

                // This switch case is used to determine the next line in text file is indicating
                // which question type and passes the file reader object to the question type.
                // In order to instantiate the question.
                allQuestionLoaded = false;
                do{
                    className = reader.nextLine();
                    switch (className) {
                        case ("SingleChoiceQuestion"):
                            questionsIdentifiers.get(questionName).add(new SingleChoiceQuestion(bankList, reader));
                            break;
                        case ("FillTheBlanks"):
                            questionsIdentifiers.get(questionName).add(new FillTheBlanks(bankList, reader));
                            break;
                        case (""):
                            allQuestionLoaded = true;
                            break;
                        default:
                            System.out.println("Error Question Type Found");
                    }

                }while(!allQuestionLoaded && reader.hasNextLine());

            }
        }
    }
}



