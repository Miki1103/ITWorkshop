
package model;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<String> choices;
    private String correctAnswer;
    private int weight;

    public Question(int id, String text, List<String> choices, String correctAnswer, int weight) {
        this.id = id;
        this.text = text;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getWeight() {
        return weight;
    }
}




