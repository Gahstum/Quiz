import java.util.Scanner;

public class Quiz
{

    private Scanner scanner = new Scanner(System.in);
    private String userName, userAnswer;
    private Question[] questions = {new Question("Question", "Answer")};
    private String questionsRecap = "";

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
    private void startQuiz()
    {
        //ask questions and stuff here
        System.out.println("Good luck and here comes your first question: ");
        for (int i = 0; i < questions.length; i++)
        {
            System.out.println(questions[i].getQuestion());
            String answerString = scanner.next();
            if (questions[i].getAnswer().equalsIgnoreCase(answerString))
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questions[i].getQuestion() + " and your answer: " + answerString + " was correct!" + "\n";
                System.out.println("Right!");
            } else
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questions[i].getQuestion() +
                        " and your answer: " + answerString + " was incorrect. The answer is " + questions[i].getAnswer();
                System.out.println("Wrong!");
            }
        }
        System.out.println(questionsRecap);
    }

    //check if the Input was yes or no to start or end the quiz.
    private void checkYesNo(String input)
    {

        while (input.isBlank())
        {
            System.out.println("Type 'yes' to continue or 'no' to leave.");
            userAnswer = scanner.nextLine();
            checkYesNo(userAnswer);
        }
        if (input.equalsIgnoreCase("yes"))
        {
            startQuiz();
        } else
        {
            System.out.println("Goodbye " + userName + " see you soon.");
        }
    }
}
