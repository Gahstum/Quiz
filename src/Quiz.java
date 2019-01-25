import java.util.Scanner;

public class Quiz
{

    Scanner scanner = new Scanner(System.in);
    String userName,userAnswer;

    //Greetings and such formal stuff not the actual Quiz
    public void start()
    {
        System.out.println("Hello what is your name?");
        userName = scanner.nextLine();
        System.out.println("Nice to meet you " + userName + ". \n" +
                "Do you want to participate in a Quiz?");
        userAnswer = scanner.nextLine();
        checkYesNo(userAnswer);

    }

    // the actual Quiz
    public void startQuiz()
    {
        //ask questions and stuff here
        System.out.println("Good luck and here comes your first question: ");
    }

    //check if the Input was yes or no to start or end the quiz.
    public void checkYesNo(String input)
    {
        while ( input.isBlank())
        {
            System.out.println("Type 'yes' to continue or 'no' to leave.");
            userAnswer = scanner.nextLine();
            checkYesNo(userAnswer);
        }
        if ( input.equalsIgnoreCase("yes"))
        {
            startQuiz();
        }
        else
        {
            System.out.println("Goodbye " + userName + " see you soon.");
        }
    }

}
