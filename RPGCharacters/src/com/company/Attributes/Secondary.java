package com.company.Attributes;

public class Secondary {

    private int health;
    private int armorRating;
    private int elementalResistance;


    public int getHealth() {
        return health;
    }

    /**
     * Determines health based on vitality.
     *
     * @param vitality - hero vitality
     */
    public void setHealth(int vitality) {
        this.health = vitality * 10;
    }

    public int getArmorRating() {
        return armorRating;
    }

    /**
     * Determines armorRating based on dexterity and strength
     *
     * @param dexterity - hero dexterity
     * @param strength  - hero strength
     */
    public void setArmorRating(int dexterity, int strength) {
        this.armorRating = strength + dexterity;
    }

    public int getElementalResistance() {
        return elementalResistance;
    }

    /**
     * Determines elementalResistance based on intelligence
     *
     * @param intelligence - hero intelligence
     */
    public void setElementalResistance(int intelligence) {
        this.elementalResistance = intelligence;
    }
}
