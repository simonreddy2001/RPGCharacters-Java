package com.company.heroes;

import com.company.Attributes.Primary;
import com.company.Items.Armor;
import com.company.Items.Weapon;
import com.company.Exception;

public class Rogue extends Hero {

    public Rogue(String name) {
        super(name, new Primary(8, 2, 6, 1));
    }

    @Override
    public void levelUp(int level) {
        increaseHeroLevel(level);
        updateHero(level, 3, 1, 4, 1);
    }

    @Override
    public boolean equip(Weapon weapon) throws Exception {
        switch (weapon.getWeaponType()) {
            case Dagger, Sword -> {
                equipWeapon(weapon);
                return true;
            }
            default -> throw new Exception("Rogue cannot equip anything other than DAGGER or SWORD");
        }
    }

    @Override
    public boolean equip(Armor armor) throws Exception {
        switch (armor.getArmorType()) {
            case Leather, Mail -> {
                equipArmor(armor);
                return true;
            }
            default -> throw new Exception("Rogue cannot equip anything other than LEATHER or MAIL armor");
        }
    }

    @Override
    public double calculateDamage() {
        double roundDPS = calculateDPS(getTotalAttributes().getDexterity());
        return Math.round(roundDPS*100.0)/100.0;
    }
}
