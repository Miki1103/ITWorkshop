package model;

import java.util.List;

public class Game {
    private int id;
    private Character playerCharacter;
    private Character enemyCharacter;
    private List<Question> questions; 
    private int currentIndex;

    public Game(int id, Character playerCharacter, Character enemyCharacter, Question firstQuestion, List<Question> questions) {
        this.id = id;
        this.playerCharacter = playerCharacter;
        this.enemyCharacter = enemyCharacter;
        this.questions = questions;
        this.currentIndex = 0; // 最初の質問を指すインデックス
    }

    public int getId() {
        return id;
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    public Character getEnemyCharacter() {
        return enemyCharacter;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentIndex); 
    }

    public boolean isGameOver() {
        return !playerCharacter.isAlive() || !enemyCharacter.isAlive();
    }

    public String getGameOverReason() {
        if (!playerCharacter.isAlive()) {
            return "Player has been defeated.";
        } else if (!enemyCharacter.isAlive()) {
            return "Enemy has been defeated.";
        }
        return null;
    }

    public void checkAnswer(String answer) {
        // 前方一致で回答をチェック
        if (answer.trim().startsWith(getCurrentQuestion().getCorrectAnswer().trim())) {
            enemyCharacter.reduceHp(5);
        } else {
            playerCharacter.reduceHp(5);
        }
    }

    public boolean hasNextQuestion() {
        return currentIndex < questions.size() - 1;
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentIndex++; 
        }
    }

    public List<Question> getQuestions() {
        return questions; 
    }

    public int getCurrentIndex() {
        return currentIndex; // 現在の質問インデックスを返す
    }
}



