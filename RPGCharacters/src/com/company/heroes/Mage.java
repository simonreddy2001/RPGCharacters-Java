package com.company.heroes;

import com.company.Attributes.Primary;
import com.company.Items.Armor;
import com.company.Items.ArmorType;
import com.company.Items.Weapon;
import com.company.Exception;


public class Mage extends Hero {

    public Mage(String name) {
        super(name, new Primary(5, 1, 1, 8));
    }


    @Override
    public void levelUp(int level) {
        increaseHeroLevel(level);
        updateHero(level, 3, 1, 1, 5);
    }

    @Override
    public boolean equip(Weapon weapon) throws Exception {
        switch (weapon.getWeaponType()) {
            case Staff, Wand -> {
                equipWeapon(weapon);
                return true;
            }
            default -> throw new Exception("Mage cannot equip anything other than STAFF or WAND");
        }
    }

    @Override
    public boolean equip(Armor armor) throws Exception {
        if (armor.getArmorType() == ArmorType.Cloth) {
            equipArmor(armor);
            return true;
        }
        throw new Exception("Mage can only wear CLOTH armor.");

    }

    @Override
    public double calculateDamage() {
        double roundDPS = calculateDPS(getTotalAttributes().getIntelligence());
        return Math.round(roundDPS*100.0)/100.0;
    }

}

