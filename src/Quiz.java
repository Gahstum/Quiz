import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz
{

    private Scanner scanner = new Scanner(System.in);
    private String userName, userAnswer;
    private List<Question> questions = new ArrayList<Question>();
    private List<Topic> topics = new ArrayList<Topic>();
    private String questionsRecap = "";

    //Greetings and such formal stuff not the actual Quiz
    public void start()
    {
        filesFromDirectory();
        System.out.println("Hello what is your name?");
        userName = scanner.nextLine();
        System.out.println("Nice to meet you " + userName + ". \n" +
                "Do you want to participate in a Quiz?");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer) == true)
        {
            checkThema();
        }
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
        if (checkYesNo(userAnswer) == true)
        {
            System.out.printf("What Topic do you want: ");
            for (Topic item : topics)
            {
                System.out.printf(item.getTopic() + " ");
            }
            userAnswer = scanner.nextLine();
            for (Topic item : topics)
            {
                //fügt das bestimte Thema zu den Fragen hinzu
                if (userAnswer.equalsIgnoreCase(item.getTopic()))
                {
                    questions.clear();
                    questions.addAll(Arrays.asList(item.getQuestions()));
                    startQuiz();
                    break;
                }
            }
        } else if (checkYesNo(userAnswer) == false)
        {
            //fügt alle Themen zu den Fragen hinzu
            for (Topic item : topics)
            {
                questions.clear();
                try
                {
                    questions.addAll(Arrays.asList(item.getQuestions()));
                } catch (Exception e)
                {
                    System.out.println("Error: No topcis found");
                }

            }
            startQuiz();
        }
    }

    //check if the Input was yes or no to start or end the quiz.
    private Boolean checkYesNo(String input)
    {
        Boolean yes = false;
        if (input.equalsIgnoreCase("yes"))
        {
            yes = true;
        } else if (input.equalsIgnoreCase("no"))
        {
            yes = false;
        } else
        {
            System.out.println("Type 'yes' to continue or 'no'.");
            userAnswer = scanner.nextLine();
            checkYesNo(userAnswer);
        }
        return yes;
    }

    private void filesFromDirectory()
    {
        File folder = new File("./Topics");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            topics.add(new Topic(listOfFiles[i].getName()));
        }
    }
}

