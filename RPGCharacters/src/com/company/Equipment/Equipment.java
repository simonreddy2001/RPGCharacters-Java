package com.company.Equipment;

import com.company.Items.Armor;
import com.company.Items.Item;
import com.company.Items.Weapon;

import java.util.HashMap;
public class Equipment {

    private final HashMap<Slot, Item> slotTypeItems;


    public Equipment() {
        this.slotTypeItems = new HashMap<>();
    }

    /**
     * Obtain slotType Item
     *
     * @return HashMap (consisting hero's equipments)
     */
    public HashMap<Slot, Item> getSlotTypeItem() {
        return slotTypeItems;
    }

    public Weapon getWeaponItem() {
        return (Weapon) slotTypeItems.getOrDefault(Slot.Weapon, null);
    }

    /**
     * Obtaining an armor in a specific slot Type.
     *
     * @param slotType - slotType
     * @return Null otherWise
     */
    public Armor getArmorItem(Slot slotType) {
        return (Armor) slotTypeItems.getOrDefault(slotType, null);
    }

}
