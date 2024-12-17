package model;

public class CharacterImageManager {
    private Character character;

    // コンストラクタでキャラクターインスタンスを受け取る
    public CharacterImageManager(Character character) {
        this.character = character;
    }

    // キャラクターの状態に応じた画像パスを返すメソッド
    public String getImagePath() {
        double hpRatio = (double) character.getHp() / character.getMaxHp();

        if (hpRatio > 0.7) {
            return "images/" + character.getName() + "_healthy.gif"; // 健康な状態の画像
        } else if (hpRatio > 0.3) {
            return "images/" + character.getName() + "_injured.gif"; // 傷ついた状態の画像
        } else {
            return "images/" + character.getName() + "_critical.gif"; // 瀕死の状態の画像
        }
    }
}

