package com.company.Items;

import com.company.Attributes.Primary;
import com.company.Equipment.Slot;

public class Armor extends Item {
    private final ArmorType armorType;
    private final Primary primaryAttributes;

    public Armor(String name, int requiredLevel, Slot slotType, ArmorType armorType, Primary primaryAttributes) {
        super(name, requiredLevel, slotType);
        this.armorType = armorType;
        this.primaryAttributes = primaryAttributes;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public Primary getPrimaryAttributes() {
        return primaryAttributes;
    }


}
