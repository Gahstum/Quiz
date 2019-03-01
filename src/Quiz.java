import java.io.*;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

class Quiz
{

    private Scanner scanner = new Scanner(System.in);
    private String userName, userAnswer;
    private List<Question> questions = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();
    private String questionsRecap = "";
    private int right = 0;
    private int wrong = 0;
    //Vorlauf des Quiz mit Begruessung und Abfrage des Namens
    public void start() throws IOException
    {

        filesFromDirectory();
        System.out.println("Hello what is your name?");
        userName = scanner.nextLine();
        if ( userName.isEmpty())
        {
            userName = "Anonymus";
        }
        System.out.println("Nice to meet you " + userName + ". \n" +
                "Do you want to participate in a Quiz?");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer))
        {
            checkTopic();
        }
        else
        {
            System.out.println("Goodbye " + userName);
        }
    }

    // the actual Quiz
    private void startQuiz() throws IOException
    {
        //genauere Abfragen wie das Quiz ablaufen soll
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
                right++;
            } else
            {
                questionsRecap = questionsRecap + "The question " + (i + 1) + " was: " + questionItem.getQuestion() +
                        " and your answer: " + answerString + " was incorrect. The answer is " + questionItem.getAnswer() + "\n";
                System.out.println("Wrong!");
                wrong++;
            }
            i++;

        }
        System.out.println(questionsRecap);
        saveStatistic();
        System.out.println("Do you want to add a question?");
        if (checkYesNo(scanner.nextLine()))
        {
            addQuestion();
        }
        System.out.println("Do you want to ask WolframAlpha a question?");
        if (checkYesNo(scanner.nextLine()))
        {
            System.out.println("Please enter your question");
            String question = scanner.nextLine();
            WolframAlpha(question);
        }
        System.out.println("Do you want to see the statistic of all time Quiz questions?");
        if (checkYesNo(scanner.nextLine()))
        {
            System.out.println("Questions asked: "+ (wrong+right));
            System.out.println("Questions right: "+ right);
            System.out.println("Questions wrong: "+ wrong);
        }
        System.out.println("Do you want to do a Quiz again?");
        if (checkYesNo(scanner.nextLine()))
        {
            // Um den alten Recap zu leeren
            questionsRecap = "";
            try
            {
                filesFromDirectory();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            startQuiz();
        } else
        {
            System.out.println("Goodbye " + userName);
        }
    }

    //fügt ein Thema oder alle Themen den Fragen hinzu
    private void checkTopic() throws IOException
    {

        System.out.println("Do you want a specific topic?");
        System.out.println("Type 'yes' to continue or 'no' to  for all topics.");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer))
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
                //fügt das bestimmte Thema zu den Fragen hinzu
                if (userAnswer.equalsIgnoreCase(item.getTopic()))
                {
                    questions.clear();
                    questions.addAll(item.getQuestions());
                    shuffleQuestions();
                    startQuiz();
                    break;
                }
            }
        } else if (!checkYesNo(userAnswer))
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

    //schaut ob die Eingabe yes oder no ist.
    private Boolean checkYesNo(String input)
    {
        Boolean yes;
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
            yes = checkYesNo(userAnswer);

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
        if (checkYesNo(userAnswer))
        {
            System.out.println("Please enter the topic you would like to add to.");
            selectedTopic = scanner.nextLine() + "-Questions.txt";
        } else if (!checkYesNo(userAnswer))
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
            if (!newTopic.isEmpty())
            {
                FileWriter writer = new FileWriter(new File("./Topics" + "/" + newTopic), true);
                writer.write(newQestion + " # " + newAnswer);
                writer.close();
            } else if (!selectedTopic.isEmpty())
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
        if (checkYesNo(scanner.nextLine()))
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

    private void saveStatistic() throws IOException
    {
        File stats = new File("./Topics" + "/" + "Statistic.txt");
        if (stats.exists())
        {
            FileReader fr = new FileReader("./Topics" + "/" + "Statistic.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null)
            {
                String rightString;
                String wrongString;
                rightString = line.split("#")[0];
                wrongString = line.split("#")[1];
                right = right + Integer.parseInt(rightString);
                wrong = wrong + Integer.parseInt(wrongString);
            }
            br.close();
        }
        try
        {
            FileWriter writer = new FileWriter(stats, false);
            writer.write(right +"#");
            writer.write(wrong+ "#");

            writer.close();

        } catch (IOException e)
        {
            System.out.println("Error while trying to add a question");
            e.printStackTrace();
        }
    }

    private void WolframAlpha(String userQuestion)
    {
        String answer = null;
        userQuestion = userQuestion.replace(' ', '+');
        try
        {

            String URL = "http://api.wolframalpha.com/v1/result?appid=A6QQ9U-79LAQWXLP8&i=";
            URL = URL + userQuestion;
            URL url = new URL(URL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            if ((answer = reader.readLine()) != null)
            {
                System.out.println(answer);
            }
            reader.close();
        } catch (Exception e)
        {
            System.out.println("No answer found");
        }
        System.out.println("Should we save this answer and question?");
        userAnswer = scanner.nextLine();
        if (checkYesNo(userAnswer))
        {
            try
            {
                FileWriter writer = new FileWriter(new File("./Topics" + "/" + "WolframAlpha-Questions.txt"), true);
                writer.write(userQuestion + " # " + answer);
                writer.close();

            } catch (IOException e)
            {
                System.out.println("Error while trying to add a question");
                e.printStackTrace();
            }
        }
    }
}

