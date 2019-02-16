class Question
{
    private String question;
    private String answer;
    private String userAnswer;
    private boolean correct = false;

    public boolean isCorrect()
    {
        return correct;
    }

    public void setCorrect(boolean correct)
    {
        this.correct = correct;
    }

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
