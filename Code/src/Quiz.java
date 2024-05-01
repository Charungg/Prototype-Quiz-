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

    private ArrayList<Integer> correctAnswers;

    public Quiz(Question setQuestionList, Bank setBankList){
        questionList = setQuestionList;
        bankList = setBankList;
        listOfQuestions = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }


    public void quizSession(){
        String moduleName;

        moduleName = listModuleBanks();

        selectBank(moduleName);
        System.out.println(listOfQuestions);

        setAmountOfQuestion();
        System.out.println(amountOfQuestion);

        shuffleQuestions();
        System.out.println(listOfQuestions);

        startQuiz();
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


    public void shuffleQuestions(){
        Collections.shuffle(listOfQuestions);
        while (amountOfQuestion != listOfQuestions.size()){
            listOfQuestions.remove(amountOfQuestion);
        }
    }
    public void startQuiz(){
        currentQuestion = 0;
        boolean quizSessionOn = true;
        Question questionObject;

        do{
            questionObject = listOfQuestions.get(currentQuestion);
            question(questionObject);
            quizSessionOn = userNavigation();
        }while(quizSessionOn);


    }


    public void question(Question questionObject){
        boolean isAnswerCorrect = false;
        switch((questionObject.getClass()).toString()){
            case ("class SingleChoiceQuestion"):
                isAnswerCorrect = ((SingleChoiceQuestion) questionObject).startQuizQuestion();
                break;
            case ("class FillTheBlanks"):
                isAnswerCorrect = ((FillTheBlanks) questionObject).startQuizQuestion();
                break;
        }

        if (isAnswerCorrect){
            correctAnswers.set(currentQuestion,1);
        }

        else{
            correctAnswers.set(currentQuestion,0);
        }
    }

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

    public boolean nextQuestion(){
        if (currentQuestion < amountOfQuestion){
            currentQuestion = currentQuestion + 1;
            System.out.println("Next Question");
            return true;
        }
        else{
            System.out.println("This Is The Last Question");
            return false;
        }
    }

    public boolean previousQuestion(){
        if (currentQuestion > 1){
            currentQuestion = currentQuestion - 1;
            System.out.println("Previous Question");
            return true;
        }
        else{
            System.out.println("This Is The First Question");
            return false;
        }
    }

    public void endQuiz(){
        System.out.println("correctAnswers = " + correctAnswers);
    }

    // TBC I know the amount of questions there is, is one less.
    // CurrentQuestions does not take into account of index number -1 factor I think.
}
