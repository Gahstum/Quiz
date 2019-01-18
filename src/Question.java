public class Question
{
    private String question;
    private String answer;
    private String userAnswer;

    public Question(String pQuestion, String pAnswer)
    {
        question = pQuestion;
        answer = pAnswer;
    }


    public String getUserAnswer()
    {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer)
    {
        this.userAnswer = userAnswer;
    }


    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }


    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }
}
