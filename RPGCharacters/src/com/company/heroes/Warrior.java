package com.company.heroes;

import com.company.Attributes.Primary;
import com.company.Items.Armor;
import com.company.Items.Weapon;
import com.company.Exception;

public class Warrior extends Hero {


    public Warrior(String name) {
        super(name, new Primary(10, 5, 2, 1));
    }

    @Override
    public void levelUp(int level) {
        increaseHeroLevel(level);
        updateHero(level, 5, 3, 2, 1);
    }


    @Override
    public boolean equip(Weapon weapon) throws Exception {
        switch (weapon.getWeaponType()) {
            case Axe, Hammer, Sword -> {
                equipWeapon(weapon);
                return true;
            }
            default -> throw new Exception("Warrior can equip only AXE, HAMMER or SWORD");
        }
    }

    @Override
    public boolean equip(Armor armor) throws Exception {
        switch (armor.getArmorType()) {
            case Mail, Plate -> {
                equipArmor(armor);
                return true;
            }
            default -> throw new Exception("Warrior cannot equip anything other than MAIL or PLATE armor");
        }
    }

    @Override
    public double calculateDamage() {
        double roundDPS = calculateDPS(getTotalAttributes().getStrength());
        return Math.round(roundDPS*100.0)/100.0;
    }
}
