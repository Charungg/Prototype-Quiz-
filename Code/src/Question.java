import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Question {

    private Bank bankList;

    protected ArrayList<String> questionsIdentifiers;

    protected ArrayList<ArrayList<String>> questionText;

    protected ArrayList<ArrayList<ArrayList>> listOfQuestions;


    // Constructor to instantiate the question object.
    public Question(Bank setBankList){
        bankList = setBankList;
        questionsIdentifiers = new ArrayList<String>();
        questionText = new ArrayList<>();

        // Creates the actual list to hold questionBank
        listOfQuestions = new ArrayList<>();

        // questionBank itself
//        listOfQuestions.add(new ArrayList<ArrayList>());

        // Data such as the questionType and questionIndex
        // I think
//        listOfQuestions.get(0).add(new ArrayList());
    }

    public void displayQuestion(){
        if (questionsIdentifiers.isEmpty()){
            System.out.println("There Is No Question");
        }
        else{
            System.out.println("----Display Module----");
            for (int i=0; i<questionsIdentifiers.size(); i++){
                System.out.println(questionsIdentifiers.get(i));
            }
            System.out.println("---------------------");
        }
    }

    public void setQuestionBank(String questionType, int questionIndexYAxis){
        int size = listOfQuestions.size()-1;

//        listOfQuestions.get(0).add(new ArrayList());
//        listOfQuestions.get(0).get(0).add("SCQ");
//        listOfQuestions.get(0).get(0).add(0);
//        listOfQuestions.get(0).add(new ArrayList());
//        listOfQuestions.get(0).get(1).add("SCQ");
//        listOfQuestions.get(0).get(1).add(1);
//        listOfQuestions.get(0).add(new ArrayList());
//        listOfQuestions.get(0).get(2).add("FTB");
//        listOfQuestions.get(0).get(2).add(0);
//        System.out.println(listOfQuestions);





    }


    public void createQuestion(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        String userInputContinue;
        boolean createMoreQuestion = false;
        Scanner console = new Scanner(System.in);

        do{
            setQuestionIdentifier();
            setQuestionType(singleChoiceQuestionList, fillTheBlanksList);


            System.out.println("Do You Want To Add More Question? (y/n)");

            try{
                userInputContinue = console.next();


            }

            catch (Exception e){
                System.out.println("Invalid Input, Try Again");
            }




        }while(!createMoreQuestion);




    }

    public void setQuestionType(SingleChoiceQuestion singleChoiceQuestionList, FillTheBlanks fillTheBlanksList){
        int userSelectQuestionType = 0;
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
                System.out.println("Invalid Input, Please Try Again");
            }

        }while(!identifiedQuestionType);

    }


    public void setQuestionIdentifier(){
        String userInputModuleIdentifier;
        String userInputBankIdentifier = null;
        boolean questionIdentifierCreated = false;
        Scanner console = new Scanner(System.in);

        do{
            boolean moduleIdentifierValid = false;
            boolean bankIdentifierValid = false;

            System.out.println("Enter A Existing Module Identifier: ");
            userInputModuleIdentifier = console.next();
            moduleIdentifierValid = bankList.moduleIdentifierExist(userInputModuleIdentifier);

            if (moduleIdentifierValid){
                System.out.println("Enter A Existing Bank Identifier: ");
                userInputBankIdentifier = console.next();
                bankIdentifierValid = bankList.bankIdentifierExist(userInputModuleIdentifier,userInputBankIdentifier);
            }

            if(moduleIdentifierValid && bankIdentifierValid){
                questionsIdentifiers.add(userInputModuleIdentifier + ":" + userInputBankIdentifier);
                questionIdentifierCreated = true;
            }


        }while(!questionIdentifierCreated);

    }



    public void setQuestionText(int indexPosition){
        String userInputQuestionText;
        Scanner console = new Scanner(System.in);
        console.useDelimiter("\\n");

        System.out.println("Enter A Question Text: ");
        userInputQuestionText = console.next();
        questionText.get(indexPosition).add(userInputQuestionText);
    }

}

