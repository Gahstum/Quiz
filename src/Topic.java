public class Topic
{
    private String topic;
    private Question[] questions = {new Question("frage","antwort")};

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
}
