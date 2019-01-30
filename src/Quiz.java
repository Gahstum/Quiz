import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz
{

    private Scanner scanner = new Scanner(System.in);
    private String userName, userAnswer;
    //private Question[] questions;
    private List<Question> questions = new ArrayList<Question>();
    private Topic[] topics = {new Topic("Mathe")};
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
        int i = 0;
        for (Question questionItem : questions)
        {
            System.out.println(questionItem.getQuestion());
            String answerString = scanner.next();
            if (questionItem.getAnswer().equalsIgnoreCase(answerString))
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questionItem.getQuestion() + " and your answer: " + answerString + " was correct!" + "\n";
                System.out.println("Right!");
            } else
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questionItem.getQuestion() +
                        " and your answer: " + answerString + " was incorrect. The answer is " + questionItem.getAnswer();
                System.out.println("Wrong!");
            }
            i++;
        }
        System.out.println(questionsRecap);
    }

    //fügt ein oder alle Themen den Fragen hinzu
    private void checkThema()
    {
        System.out.println("Do you want a specific topic?");
        System.out.println("Type 'yes' to continue or 'no' to  for all topics.");
        userAnswer = scanner.nextLine();
        if (userAnswer.equalsIgnoreCase("yes"))
        {
            System.out.println("What Topic do you want: Themen-listen");
            userAnswer = scanner.nextLine();
            for (int i = 0; i < topics.length; i++)
            {
                //fügt das bestimte Thema zu den Fragen hinzu
                if (userAnswer.equalsIgnoreCase(topics[i].getTopic()))
                {
                    questions.clear();
                    questions.addAll(Arrays.asList(topics[i].getQuestions()));
                    startQuiz();
                    break;
                }
            }
        } else
        {
            //fügt alle Themen zu den Fragen hinzu
            for (int i = 0; i < topics.length; i++)
            {
                questions.clear();
                try
                {
                    questions.addAll(Arrays.asList(topics[i].getQuestions()));
                } catch (Exception e)
                {
                    System.out.println("Error: No topcis found");
                }

            }
            startQuiz();
        }
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
            checkThema();
        } else
        {
            System.out.println("Goodbye " + userName + " see you soon.");
        }
    }
}
