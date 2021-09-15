package com.company.heroes;

        import com.company.Attributes.Primary;
        import com.company.Attributes.Secondary;
        import com.company.Equipment.*;
        import com.company.Items.Armor;
        import com.company.Items.Item;
        import com.company.Items.Weapon;
        import com.company.Exception;
        import java.util.stream.IntStream;

public abstract class Hero {
    private final String name;
    private int level;

    private final Primary baseAttributes;
    private Primary equipmentAttributes;
    private Primary totalAttributes;

    private Secondary secondaryAttributes = new Secondary();
    private final Equipment eItem = new Equipment();


    /**
     * (Constructor) Initialize Hero info (name, level, baseAttributes, equipmentAttributes & totalAttributes.
     *
     * @param name           - hero name
     * @param baseAttributes - hero baseAttributes (type of primaryAttributes)
     */
    public Hero(String name, Primary baseAttributes) {
        this.name = name;
        this.level = 1;
        this.baseAttributes = baseAttributes;
        this.equipmentAttributes = new Primary(0, 0, 0, 0);
        this.totalAttributes = new Primary(0, 0, 0, 0);


        updateTotalAttributes();
    }


    protected abstract void levelUp(int level);

    public abstract boolean equip(Weapon weapon) throws Exception;

    public abstract boolean equip(Armor armor) throws Exception;

    public abstract double calculateDamage();


    /**
     * Calculates the hero DPS based on weapon and param.
     *
     * @param param - (double)
     * @return (1) if hero doesn't have a weapon
     */
    protected double calculateDPS(double param) {
        return eItem.getWeaponItem() == null ? 1 : eItem.getWeaponItem().getDPSValue() * (1 + (param / 100));
    }

    /**
     * Checks hero level & if it is not greater than 0, than it will add up.
     *
     * @param level (int) hero level.
     */
    protected void increaseHeroLevel(int level) {
        if (level <= 0) {
            throw new IllegalArgumentException("Level gain has to be more than 0");
        }
        this.level += level;
    }


    /**
     * Equips a hero with an armor and checks if it meets the armor level requirements.
     * The armor will be equipped as specified in the item slot
     * and it will update the hero's equipment attributes once it is equipped.
     *
     * @param armor - armor
     * @return True if armor is equipped
     * @throws Exception throws Exception OTHERWISE
     */

    protected boolean equipArmor(Armor armor) throws Exception {

        if (armor.getRequiredLevel() <= this.level) {
            switch (armor.getSlotType()) {
                case Legs -> eItem.getSlotTypeItem().put(Slot.Legs, armor);
                case Head -> eItem.getSlotTypeItem().put(Slot.Head, armor);
                case Body -> eItem.getSlotTypeItem().put(Slot.Body, armor);
            }
            setEquipAttributes();
            return true;
        } else {
            throw new Exception("Armor with a higher level requirement cannot be equipped.");
        }
    }


    /**
     * Equips a hero with a weapon, also checks whether the weapon requiredLevel is too high.
     *
     * @param weapon - weapon
     * @return True if a weapon is equipped
     * @throws Exception throws Exception OTHERWISE
     */
    protected boolean equipWeapon(Weapon weapon) throws Exception {
        if (weapon.getRequiredLevel() <= this.level) {
            eItem.getSlotTypeItem().put(Slot.Weapon, weapon);
            setEquipAttributes();
            return true;
        } else {
            throw new Exception("WEAPONS with a higher level requirement cannot be equipped.");
        }
    }

    /**
     * Resets equipmentAttributes values first and then updates totalAttributes & equipmentAttributes.
     */
    private void setEquipAttributes() {
        //before updating reset fields
        equipmentAttributes = new Primary(0, 0, 0, 0);

        eItem.getSlotTypeItem().forEach(this::updateEquipAttributes);
        updateTotalAttributes();
    }

    /**
     * Increases the equipment attributes of each item in the hero's equipment,
     * based on the weapon's or armor's primary attributes.
     *
     * @param key   - itemKeys
     * @param value - actualItemValues
     */

    private void updateEquipAttributes(Slot key, Item value) {
        if (key == Slot.Weapon) {
            Weapon weapon = eItem.getWeaponItem();
            if (weapon != null) {
                equipmentAttributes.increaseVitality(weapon.getPrimaryAttributes().getVitality());
                equipmentAttributes.increaseDexterity(weapon.getPrimaryAttributes().getDexterity());
                equipmentAttributes.increaseIntelligence(weapon.getPrimaryAttributes().getIntelligence());
                equipmentAttributes.increaseStrength(weapon.getPrimaryAttributes().getStrength());
            }
        } else {
            //Armor if it isn't a weapon
            Armor armor = eItem.getArmorItem(key);
            if (armor != null) {
                equipmentAttributes.increaseVitality(armor.getPrimaryAttributes().getVitality());
                equipmentAttributes.increaseDexterity(armor.getPrimaryAttributes().getDexterity());
                equipmentAttributes.increaseIntelligence(armor.getPrimaryAttributes().getIntelligence());
                equipmentAttributes.increaseStrength(armor.getPrimaryAttributes().getStrength());
            }
        }
    }

    /**
     * Updates hero baseAttributes + hero totalAttributes.
     *
     * @param level        - heroLevel
     * @param vitality     - heroVitality
     * @param strength     - heroStrength
     * @param dexterity    - heroDexterity
     * @param intelligence - heroIntelligence
     */
    protected void updateHero(int level, int vitality, int strength, int dexterity, int intelligence) {
        IntStream.range(0, level).forEach(i -> {
            baseAttributes.increaseDexterity(dexterity);
            baseAttributes.increaseIntelligence(intelligence);
            baseAttributes.increaseStrength(strength);
            baseAttributes.increaseVitality(vitality);
        });

        updateTotalAttributes();
    }

    private void updateTotalAttributes() {

        //clear out first!
        totalAttributes = new Primary(0, 0, 0, 0);

        totalAttributes.increaseStrength(baseAttributes.getStrength() + equipmentAttributes.getStrength());
        totalAttributes.increaseDexterity(baseAttributes.getDexterity() + equipmentAttributes.getDexterity());
        totalAttributes.increaseIntelligence(baseAttributes.getIntelligence() + equipmentAttributes.getIntelligence());
        totalAttributes.increaseVitality(baseAttributes.getVitality() + equipmentAttributes.getVitality());

        updateSecondaryAttributes();
    }

    private void updateSecondaryAttributes() {
        secondaryAttributes.setHealth(totalAttributes.getVitality());
        secondaryAttributes.setArmorRating(totalAttributes.getDexterity(), totalAttributes.getStrength());
        secondaryAttributes.setElementalResistance(totalAttributes.getIntelligence());
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Primary getTotalAttributes() {
        return totalAttributes;
    }

    public Secondary getSecondaryAttributes() {
        return secondaryAttributes;
    }

    public void setSecondaryAttributes(Secondary secondaryAttributes) {
        this.secondaryAttributes = secondaryAttributes;
    }

    public Primary getBaseAttributes() {
        return baseAttributes;
    }

    public Primary getEquipmentAttributes() {
        return equipmentAttributes;
    }


}