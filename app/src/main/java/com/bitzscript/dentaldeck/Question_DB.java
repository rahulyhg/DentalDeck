package com.bitzscript.dentaldeck;

/**
 * Created by naimesh on 2/7/2017.
 */
public class Question_DB {
    private int id;
    private String question;
    private String answer1, answer2, answer3, answer4,answer5,answer6,explain;
    private String true_ans,subcode;
    public Question_DB(){
        id=0;
        question="";
        answer1="";
        answer2="";
        answer3="";
        answer4="";
        answer5="";
        answer6="";
        explain="";
        true_ans="";
        subcode ="";

    }
    public Question_DB(int id,String question,String answer1,String answer2,String answer3,String answer4,String answer5,
                       String answer6, String true_ans,String explain,String subcode)
    {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.true_ans = true_ans;
        this.explain = explain;
        this.subcode = subcode;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void  setQuestion(String question)
    {
        this.question = question;
    }
    public void setAnswer1(String answer1)
    {
        this.answer1 = answer1;
    }
    public void setAnswer2(String answer2)
    {
        this.answer2 = answer2;
    }
    public void setAnswer3(String answer3)
    {
        this.answer3 = answer3;
    }
    public void setAnswer4(String answer4)
    {
        this.answer4 = answer4;
    }
    public void setAnswer5(String answer5)
    {
        this.answer5 = answer5;
    }
    public void setAnswer6(String answer6)
    {
        this.answer6 = answer6;
    }
    public void setTrue_ans(String true_ans)
    {
        this.true_ans= true_ans;
    }
    public void setExplain(String explain){ this.explain = explain;}
    public void setSubcode (String subcode) { this.subcode = subcode;}
    public int getId()
    {
        return id;
    }
    public String getQuestion()
    {
        return question;
    }
    public String getAnswer1()
    {
        return answer1;
    }
    public String getAnswer2()
    {
        return answer2;
    }
    public String getAnswer3()
    {
        return answer3;
    }
    public String getAnswer4()
    {
        return answer4;
    }
    public String getAnswer5()
    {
        return answer5;
    }
    public String getAnswer6()
    {
        return answer6;
    }
    public String getExplain() { return  explain;}
    public String getTrue_ans()
    {
        return true_ans;
    }
    public String getSubcode() { return subcode;}
}
