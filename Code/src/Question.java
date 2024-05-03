
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class Question {

    private final Bank bankList;

    private final HashMap<String,ArrayList<Question>> questionsIdentifiers;

    protected String questionText;


    // Constructor to instantiate the question object.
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new HashMap<>();
    }


    // Used to display all question identifier along with each question associated with the question identifier.
    public void displayQuestion() {
        if (questionsIdentifiers.isEmpty()){
            System.out.println("There Is No Question");
        }

        else{
            // This loops through each question identifier by going through the key set of the hashmap.
            for (String questionObjectKey : questionsIdentifiers.keySet()) {
                System.out.println("--------------------" + questionObjectKey + "--------------------");

                // For every key getting looped through it will then loop through the value set of that specific question identifier.
                // For example once getting a key, CS12320:QuestionBank01 it will go through the value of that key.
                // But the key is a ArrayList which holds onto multiple questions which will be looped through and running the .displayQuestion
                // function which will display details of each question.
                for (Question questionObjectValue : questionsIdentifiers.get(questionObjectKey)){
                    questionObjectValue.displayQuestion();
                    System.out.println();
                }
                System.out.println("---------------------------------------------------------------");
            }
        }
    }

    public final void createQuestion(){
        String uniqueIdentifier;
        boolean createMoreQuestion;

        // Uses the parent class to set the question unique ID.
        uniqueIdentifier = setQuestionIdentifier();

        // Checks whether the unique identifier already within the hashmap.
        uniqueIdentifierExist(uniqueIdentifier);

        // The do while loop is designed for to create a question, in this case it's either a single choice question
        // or a fill the blanks question. Then moreQuestion question will determine whether the user want to create
        // more question either by a yes or no answer which will return true or false correspondingly.

        do{
            setQuestionType(uniqueIdentifier);
            createMoreQuestion = moreQuestion();
            questionsIdentifiers.get(uniqueIdentifier).getLast().displayQuestion();

        }while(createMoreQuestion);

        displayQuestion();
    }


    public final String setQuestionIdentifier(){
        String uniqueIdentifier;
        String userInputModuleIdentifier;
        String userInputBankIdentifier;
        boolean questionIdentifierCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Existing Module Identifier: ");
            userInputModuleIdentifier = console.next();


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
    public void uniqueIdentifierExist(String uniqueIdentifier){
        if (questionsIdentifiers.get(uniqueIdentifier) == null){
            questionsIdentifiers.put(uniqueIdentifier,new ArrayList<>());
            System.out.println("New Question Identifier");
        }
    }


    public void setQuestionText(){
        String userQuestionText;
        Scanner console = new Scanner(System.in);
        // Allows the user to enter question text which contains spaces rather than one continues text.
        // However, this does not consider if the user enters a new line.
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userQuestionText = console.next();
        questionText = userQuestionText;
    }


    public final void setQuestionType(String uniqueIdentifier){
        int questionType;
        boolean validQuestionType;

        validQuestionType = false;
        do{
            questionType = setQuestionType();
            switch(questionType){
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


    public final int setQuestionType(){
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
    public void removeQuestion(){
        int questionSelection;
        String uniqueIdentifier;
        boolean questionRemoved = false;
        Scanner console = new Scanner(System.in);

        // Gets the unique question identifier.
        uniqueIdentifier = setQuestionIdentifier();

        // Create a temporarily ArrayList which holds the ArrayList of questions from a specific question identifier.
        // This is done to improve clarity of the code because without it many code will be repeated to get the ArrayList of question ID.
        ArrayList<Question> questionObjects;
        // By passing the uniqueIdentifier through the questionIdentifier it will return ArrayList of the Questions associated to it.
        questionObjects = questionsIdentifiers.get(uniqueIdentifier);

        do{
            System.out.println("Enter A Question Number To Be Deleted: ");
            for (int index = 1 ; index<=questionObjects.size(); index++){
                System.out.println("--------------------" + index + "--------------------");
                questionObjects.get(index - 1).displayQuestion();
                System.out.println();
            }

            try{
                questionSelection = console.nextInt();
                if (questionSelection >= 1 && questionSelection <= questionObjects.size()){
                    questionsIdentifiers.get(uniqueIdentifier).remove(questionSelection - 1);
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

        // Removes the unique identifier from the HashMap if it contains zero questions.
        removeQuestionIdentifierIfEmpty(uniqueIdentifier);
    }

    // Checks if question identifier exist within the HashMap questionIdentifier Key
    public boolean isQuestionIdentifierEmpty(String uniqueIdentifier){
        return questionsIdentifiers.get(uniqueIdentifier).isEmpty();
    }


    // Removes question identifier from key in HashMap if it does not contain any question.
    public void removeQuestionIdentifierIfEmpty(String uniqueIdentifier){
        // Checks if argument question identifier has no question.
        if (isQuestionIdentifierEmpty(uniqueIdentifier)){
            // If true then it will remove question identifier from HashMap.
            questionsIdentifiers.remove(uniqueIdentifier);
            System.out.println("Seems Like It's Empty, Removed Question Identifier");
        }
    }


    // Used for the Quiz object to copy the ArrayList of Questions from a specific question identifier
    // which will be used for the student to answer.
    public ArrayList<Question> getQuestionList(String uniqueIdentifier){
        return questionsIdentifiers.get(uniqueIdentifier);
    }



    // Functions below are designed to save and load the Question class.
    public void saveQuestion(FileWriter file){
        try{
            file.close();
        }

        catch(IOException e){
            System.out.println("Saving Module Error Occurred: ");
            e.printStackTrace();
        }
    }
}



