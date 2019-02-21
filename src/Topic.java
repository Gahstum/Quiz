import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Topic
{
    private String topic;
    private List<Question> questions = new ArrayList<Question>();

    public Topic(String topicname)
    {
        topic = topicname;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String pTopic)
    {
        topic = pTopic;
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
            //split questions and answers and add them into their fitting array
            question = line.split("#")[0];
            answer = line.split("#")[1];
            questions.add(new Question(question,answer));
        }

        br.close();
    }
}
