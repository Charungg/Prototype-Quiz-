
import java.util.ArrayList;
import java.util.Scanner;

public class Question {

    private final Bank bankList;

    private final ArrayList<String> questionsIdentifiers;

    protected final ArrayList<ArrayList<String>> questionText;


    // The bottom two line of code is used to reference where the questions are located.
    // When adding a single choice question it will be created in the single choice question object
    // and contain their own attributes specific to that type, same goes with fill the blanks questions.
    // However, the question object still needs access to all the questions.
    // By storing what type and where the question answer are located it can be passed down to their
    // corresponding objects to perform functions such as checking if user answer is correct.
    protected ArrayList<ArrayList<String>> questionType;

    protected ArrayList<ArrayList<Integer>> questionIndex;



    // Constructor to instantiate the question object.
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new ArrayList<>();
        questionText = new ArrayList<>();
    }

    public void displayQuestion(){
        if (questionsIdentifiers.isEmpty()){
            System.out.println("There Is No Question");
        }
        else{
            System.out.println("----Display Question----");
            for (String identifier : questionsIdentifiers){
                System.out.println(identifier);
            }
            System.out.println("---------------------");
        }
    }


    public void createQuestion(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        String userInputContinue;
        boolean validResponse;
        boolean createMoreQuestion = true;
        Scanner console = new Scanner(System.in);


        setQuestionIdentifier();
        questionText.add(new ArrayList<>());

        do{
            setQuestionType(singleChoiceQuestionList, fillTheBlanksList);


            System.out.println("Do You Want To Add More Question? (y/n)");

            validResponse = false;
            do{
                userInputContinue = console.next();

                switch (userInputContinue.toLowerCase()){
                    case "y":
                        validResponse = true;
                        break;

                    case "n":
                        validResponse = true;
                        createMoreQuestion = false;
                        break;

                    default:
                        System.out.println("Invalid Input, Try Again");
                        break;
                    }
            }while(!validResponse);

        }while(createMoreQuestion);
    }

    public void setQuestionType(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        int userSelectQuestionType;
        boolean identifiedQuestionType = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter Question Type (1 or 2): ");
            System.out.println("1) Single Choice Question ");
            System.out.println("2) Fill The Blanks ");

            try{
                userSelectQuestionType = console.nextInt();

                switch (userSelectQuestionType){
                    case 1:
                        System.out.println("SingleChoiceQuestion");
                        singleChoiceQuestionList.createSingleChoiceQuestion();
                        identifiedQuestionType = true;
                        break;

                    case 2:
                        System.out.println("FillTheBlanks");
                        fillTheBlanksList.createFillTheBlanks();
                        identifiedQuestionType = true;
                        break;
                }
            }
            catch (Exception e){
                console.nextLine();
                System.out.println("Invalid Input, Please Try Again");
            }

        }while(!identifiedQuestionType);

    }


    public void setQuestionIdentifier(){
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
                questionsIdentifiers.add(userInputModuleIdentifier + ":" + userInputBankIdentifier);
                questionIdentifierCreated = true;
            }


        }while(!questionIdentifierCreated);
    }


    public void setQuestionText(){
        int index = questionsIdentifiers.size()-1;

        String userInputQuestionText;
        Scanner console = new Scanner(System.in);
        // Accepts whole sentence as long it's not on a new line.
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userInputQuestionText = console.next();
        questionText.get(index).add(userInputQuestionText);
        System.out.println(questionText);
    }


}

