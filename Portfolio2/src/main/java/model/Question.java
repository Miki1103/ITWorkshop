package model;

public class Question {
    private int id;
    private String text;
    private String choices;
    private String correctAnswer;

    public Question(int id, String text, String choices, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

