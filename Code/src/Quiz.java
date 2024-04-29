import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
public class Quiz {

    private Bank bankList;

    private Question questionList;

    private ArrayList<Question> listOfQuestions;

    private int amountOfQuestion;

    private int currentQuestion;

    private int amountOfQuestionDone;

    private int correctAnswers;

    public Quiz(Question setQuestionList, Bank setBankList){
        questionList = setQuestionList;
        bankList = setBankList;
        listOfQuestions = new ArrayList<>();
    }


    public void searchQuestionBank(){
        String moduleName;

        moduleName = listModuleBanks();

        selectBank(moduleName);
        System.out.println(listOfQuestions);

        setAmountOfQuestion();
        System.out.println(amountOfQuestion);

        shuffleQuestions();
        System.out.println(listOfQuestions);
    }


    public String listModuleBanks() {
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


    public void selectBank(String moduleName){
        String bankName;
        boolean bankSelected = false;
        Scanner console = new Scanner(System.in);

        do{
            System.out.println("Enter A Bank Which Links To The Module");
            bankName = console.next();

            if (bankList.moduleAndBankIdentifiersExist(moduleName, bankName)){
                listOfQuestions = questionList.getQuestionList(moduleName + ":" + bankName);
                bankSelected = true;
            }

        }while(!bankSelected);
    }


    public void setAmountOfQuestion(){
        int userAmount;
        int questionBankSize;
        boolean amountNonNegative = false;
        Scanner console = new Scanner(System.in);

        questionBankSize = listOfQuestions.size();

        do{
            System.out.println("Enter Number Of Question To Display (1-" + questionBankSize + "): ");
            try{
                userAmount = console.nextInt();

                if (userAmount <=questionBankSize && userAmount >= 0){
                    amountOfQuestion = userAmount;
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


    public void shuffleQuestions(){
        Collections.shuffle(listOfQuestions);
        while (amountOfQuestion != listOfQuestions.size()){
            listOfQuestions.remove(amountOfQuestion);
        }
    }
    public void startQuiz(){}

    public void nextQuestion(){}

    public void previousQuestion(){}

    public void endQuiz(){}
}
