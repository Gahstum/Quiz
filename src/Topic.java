import java.io.*;

public class Topic
{
    private String topic;
    private Question[] questions = {new Question("question","answer")};

    public Topic(String themenname)
    {
        topic = themenname;
    }

    public Question[] getQuestions()
    {
        return questions;
    }

    public void setQuestions(Question[] questions)
    {
        this.questions = questions;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String thema)
    {
        topic = thema;
    }

    public void readQuestionFromFile(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String zeile = "";

        while( (zeile = br.readLine()) != null )
        {
            System.out.println(zeile);
        }

        br.close();
    }
}
