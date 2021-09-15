package com.company.Items;


import com.company.Equipment.Slot;

public abstract class Item {
    private final String name;
    private final int requiredLevel;
    private final Slot slotType;

    public Item(String name, int requiredLevel, Slot slotType) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.slotType = slotType;
    }

    public String getName() {
        return name;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public Slot getSlotType() {
        return slotType;
    }


}
