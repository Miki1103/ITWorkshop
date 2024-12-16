package model;

import java.util.List;

public class Game {
    private int id;
    private Character playerCharacter;
    private Character enemyCharacter;
    private List<Question> questions; // 質問リスト
    private int currentIndex;         // 現在の質問のインデックス

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
        return questions.get(currentIndex); // 現在の質問を取得
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
        if (getCurrentQuestion().getCorrectAnswer().equals(answer)) {
            enemyCharacter.reduceHp(10); // 正解したら敵のHPを減らす
        } else {
            playerCharacter.reduceHp(10); // 間違えたらプレイヤーのHPを減らす
        }
    }

    public boolean hasNextQuestion() {
        return currentIndex < questions.size() - 1;
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentIndex++; // 次の質問に進む
        }
    }

    public List<Question> getQuestions() {
        return questions; // 質問リストを返す
    }

    public int getCurrentIndex() {
        return currentIndex; // 現在の質問インデックスを返す
    }
}



