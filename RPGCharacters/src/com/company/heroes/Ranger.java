package com.company.heroes;

import com.company.Attributes.Primary;
import com.company.Items.Armor;
import com.company.Items.Weapon;
import com.company.Exception;
import com.company.Items.WeaponType;

public class Ranger extends Hero {

    public Ranger(String name) {
        super(name, new Primary(8, 1, 7, 1));
    }

    @Override
    public void levelUp(int level) {
        increaseHeroLevel(level);
        updateHero(level, 2, 1, 5, 1);
    }

    @Override
    public boolean equip(Weapon weapon) throws Exception {
        if (weapon.getWeaponType() == WeaponType.Bow) {
            equipWeapon(weapon);
            return true;
        }
        throw new Exception("Ranger cannot equip anything other than BOW");
    }

    @Override
    public boolean equip(Armor armor) throws Exception {
        switch (armor.getArmorType()) {
            case Leather, Mail -> {
                equipArmor(armor);
                return true;
            }
            default -> throw new Exception("Ranger cannot equip anything other than LEATHER or MAIL armor");
        }
    }

    @Override
    public double calculateDamage() {
        double roundDPS = calculateDPS(getTotalAttributes().getDexterity());
        return Math.round(roundDPS * 100.0)/100.0;
    }
}

