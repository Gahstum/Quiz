import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Quiz
{

    private Scanner scanner = new Scanner(System.in);
    private String userName, userAnswer;
    private List<Question> questions = new ArrayList<Question>();
    private List<Topic> topics = new ArrayList<Topic>();
    private String questionsRecap = "";

    //Greetings and such formal stuff not the actual Quiz
    public void start() throws IOException
    {
        filesFromDirectory();
        System.out.println("Hello what is your name?");
        userName = scanner.nextLine();
        System.out.println("Nice to meet you " + userName + ". \n" +
                "Do you want to participate in a Quiz?");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer) == true)
        {
            checkTopic();
        }
    }

    // the actual Quiz
    private void startQuiz()
    {
        //ask questions and stuff here
        int numberOfQuestions = 0;
        System.out.println("How many Questions do you want?");
        while (numberOfQuestions <= 0)
        {
            try
            {
                Scanner scannerInt = new Scanner(System.in);
                numberOfQuestions = scannerInt.nextInt();
                if (numberOfQuestions > questions.size())
                {
                    System.out.println("We dont have that many questions. We only got " + questions.size() + " questions."
                            + "\n" + "Pls enter a lower number");
                    numberOfQuestions = 0;
                }
            } catch (Exception e)
            {
                System.out.println("Please enter a number above 0.");
            }
        }

        System.out.println("Good luck and here comes your first question: ");
        int i = 0;
        for (Question questionItem : questions)
        {
            if (i == numberOfQuestions)
            {
                break;
            }
            System.out.println(questionItem.getQuestion());
            String answerString = scanner.nextLine();
            if (questionItem.getAnswer().equalsIgnoreCase(answerString))
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questionItem.getQuestion() + " and your answer: " + answerString + " was correct!" + "\n";
                System.out.println("Right!");
            } else
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questionItem.getQuestion() +
                        " and your answer: " + answerString + " was incorrect. The answer is " + questionItem.getAnswer() + "\n";
                System.out.println("Wrong!");
            }
            i++;

        }
        System.out.println(questionsRecap);
        System.out.println("Do you want to add a question?");
        if (checkYesNo(scanner.nextLine()) == true)
        {
            addQuestion();
        }
        System.out.println("Do you want to do a Quiz again?");
        if (checkYesNo(scanner.nextLine()) == true)
        {
            startQuiz();
        } else
        {
            System.out.println("Goodbye " + userName);
        }
    }

    //fügt ein oder alle Themen den Fragen hinzu
    private void checkTopic()
    {

        System.out.println("Do you want a specific topic?");
        System.out.println("Type 'yes' to continue or 'no' to  for all topics.");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer) == true)
        {
            System.out.printf("What Topic do you want: \n");
            for (Topic item : topics)
            {
                System.out.printf(item.getTopic().split("-")[0] + "\n");
            }
            userAnswer = scanner.nextLine();
            userAnswer = userAnswer + "-Questions.txt";
            for (Topic item : topics)
            {
                //fügt das bestimte Thema zu den Fragen hinzu
                if (userAnswer.equalsIgnoreCase(item.getTopic()))
                {
                    questions.clear();
                    questions.addAll(item.getQuestions());
                    shuffleQuestions();
                    startQuiz();
                    break;
                }
            }
        } else if (checkYesNo(userAnswer) == false)
        {
            //fügt alle Themen zu den Fragen hinzu
            questions.clear();
            for (Topic item : topics)
            {
                try
                {
                    questions.addAll(item.getQuestions());
                } catch (Exception e)
                {
                    System.out.println("Error: No topcis found");
                }

            }
            shuffleQuestions();
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
            System.out.println("Type 'yes' or 'no'.");
            userAnswer = scanner.nextLine();
            checkYesNo(userAnswer);
        }
        return yes;
    }

    private void filesFromDirectory() throws IOException
    {
        File folder = new File("./Topics");
        File[] listOfFiles = folder.listFiles();
        topics.clear();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            topics.add(new Topic(listOfFiles[i].getName()));
        }
        for (Topic item : topics)
        {
            item.readQuestionFromFile("./Topics/" + item.getTopic());
        }

    }

    private void shuffleQuestions()
    {
        Collections.shuffle(questions);
    }

    private void addQuestion()
    {
        String selectedTopic = "", newTopic = "", newQestion, newAnswer;

        File folder = new File("./Topics");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            String topic = listOfFiles[i].getName().split("-")[0];
            System.out.println(topic);
        }
        System.out.println("Do you want to add to the listed topics? ( Yes or no )");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer) == true)
        {
            System.out.println("Please enter the topic you would like to add to.");
            selectedTopic = scanner.nextLine() + "-Questions.txt";
        } else if (checkYesNo(userAnswer) == false)
        {
            System.out.println("Please enter a new topic name.");
            newTopic = scanner.nextLine() + "-Questions.txt";
        }
        System.out.println("Please enter your question");
        newQestion = scanner.nextLine();
        System.out.println("Please enter the answer to that question");
        newAnswer = scanner.nextLine();

        try
        {
            if (!newTopic.isEmpty() == true)
            {
                FileWriter writer = new FileWriter(new File("./Topics" + "/" + newTopic), true);
                writer.write(newQestion + " # " + newAnswer);
                writer.close();
            } else if (!selectedTopic.isEmpty() == true)
            {
                FileWriter writer = new FileWriter(new File("./Topics" + "/" + selectedTopic), true);
                writer.write("\n" + newQestion + " # " + newAnswer);
                writer.close();
            }
        } catch (IOException e)
        {
            System.out.println("Error while trying to add a question");
            e.printStackTrace();
        }
        System.out.println("Do you want to add another question?");
        if (checkYesNo(scanner.nextLine()) == true)
        {
            addQuestion();
        }
        try
        {
            filesFromDirectory();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

