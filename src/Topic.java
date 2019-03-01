import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Topic
{
    private String topic;
    private List<Question> questions = new ArrayList<Question>();

    public Topic(String topicName)
    {
        topic = topicName;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }


    public String getTopic()
    {
        return topic;
    }

    public void readQuestionFromFile(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line = "";

        while((line = br.readLine()) != null )
        {
            String question;
            String answer;
            question = line.split("#")[0];
            answer = line.split("#")[1];
            questions.add(new Question(question,answer));
        }

        br.close();
    }
}
