package com.company;

import com.company.heroes.Hero;
import com.company.heroes.Mage;

public class Main {

    public static void main(String[] args) {
        Mage mage = new Mage("Simon");
        displayStats(mage);
    }
    /**
     * Displays basic hero info (name & level)
     *
     * @param hero - hero
     */
    public static void displayHeroInfo(Hero hero) {

        System.out.printf("Hero Name- %s\n", hero.getName());
        System.out.printf("Level- %s\n", hero.getLevel());
        System.out.println("////////////////");

    }

    /**
     * Displays info about hero primaryAttributes
     *
     * @param hero - hero.totalAttributes.
     */
    public static void displayPrimaryAttributes(Hero hero) {
        System.out.printf("Strength- %s\n", hero.getTotalAttributes().getStrength());
        System.out.printf("Dexterity- %s\n", hero.getTotalAttributes().getDexterity());
        System.out.printf("Intelligence- %s\n", hero.getTotalAttributes().getIntelligence());
        System.out.printf("Vitality- %s\n", hero.getTotalAttributes().getVitality());
        System.out.println("//////////////");
    }

    /**
     * Displays info about hero secondaryAttributes
     *
     * @param hero - hero.secondaryAttribute
     */
    public static void displaySecondaryAttributes(Hero hero) {
        System.out.printf("Health- %s\n", hero.getSecondaryAttributes().getHealth());
        System.out.printf("Armor Rating- %s\n", hero.getSecondaryAttributes().getArmorRating());
        System.out.printf("Elemental Resistance- %s\n", hero.getSecondaryAttributes().getElementalResistance());
        System.out.println("///////////");
    }

    /**
     * Displays heroDamage
     *
     * @param hero - hero.calculateDamage()
     * @return - calculated damage (double)
     */
    public static double displayDamage(Hero hero) {
        return hero.calculateDamage();
    }

    /**
     * Displays the overall hero stats.
     *
     * @param hero - hero-overall stats.
     */
    public static void displayStats(Hero hero) {

        displayHeroInfo(hero);
        displayPrimaryAttributes(hero);
        displaySecondaryAttributes(hero);
        System.out.printf("DPS- %.2f", displayDamage(hero));

    }
}
