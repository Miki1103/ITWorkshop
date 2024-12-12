package model;

public class Character {
    private int id;
    private String name;
    private int maxHp;
    private int hp;

    public Character(int id, String name, int maxHp, int hp) {
        this.id = id;
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void reduceHp(int amount) {
        this.hp = Math.max(0, this.hp - amount);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }
}

