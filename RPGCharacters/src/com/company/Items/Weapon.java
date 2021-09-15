package com.company.Items;

import com.company.Attributes.Primary;
import com.company.Equipment.Slot;

public class Weapon extends Item {
    private final int damage;
    private final double attackSpeed;
    private final WeaponType weaponType;
    private final Primary primaryAttributes;

    public Weapon(String name, int requiredLevel, int damage, double attackSpeed, WeaponType weaponType, Primary primaryAttributes) {
        super(name, requiredLevel, Slot.Weapon);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.weaponType = weaponType;
        this.primaryAttributes = primaryAttributes;
    }

    public int getDamage() {
        return damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getDPSValue() {
        return this.attackSpeed * this.damage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Primary getPrimaryAttributes() {
        return primaryAttributes;
    }
}
