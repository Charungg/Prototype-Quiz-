
import java.util.ArrayList;

import java.util.Scanner;

public class Question {

    private final Bank bankList;

    protected ArrayList<String> questionsIdentifiers;

    protected ArrayList<ArrayList<String>> questionText;



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


    public void setQuestionText(int index){
        System.out.println(questionText);
        System.out.println(index);

        String userInputQuestionText;
        Scanner console = new Scanner(System.in);

        System.out.println("Enter A Question Text: ");
        userInputQuestionText = console.next();
        questionText.get(index).add(userInputQuestionText);
        System.out.println(questionText);
    }

    public int getQuestionIdentifierSize(){
        return questionsIdentifiers.size();
    }

}

