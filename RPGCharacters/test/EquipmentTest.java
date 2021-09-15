
import com.company.Attributes.Primary;
import com.company.Equipment.*;
import com.company.Items.*;
import com.company.Exception;
import com.company.heroes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    private Warrior warrior;
    private Weapon testWeaponLevel1, testWeaponAxeLevel2, testStaffLevel2;
    private Armor testPlateBody, testClothHead, testPlateArmor;

    @BeforeEach
    public void initializeHero() {
        warrior = new Warrior("Remo");
    }

    @BeforeEach
    public void initializeWeaponAndArmor() {
        testWeaponLevel1 = new Weapon("Common Axe", 1, 2, 1.1, WeaponType.Axe,
                new Primary(2, 1, 0, 0));

        testWeaponAxeLevel2 = new Weapon("Common Axe", 2, 2, 0.65, WeaponType.Axe,
                new Primary(2, 1, 0, 0));

        testStaffLevel2 = new Weapon("Common Axe", 2, 2, 0.65, WeaponType.Staff,
                new Primary(2, 1, 0, 0));


        testClothHead = new Armor("Common Plate Head Armor ", 1, Slot.Head, ArmorType.Cloth,
                new Primary(1, 0, 0, 5));

        testPlateBody = new Armor("Special Plate Body Armor", 2, Slot.Body, ArmorType.Plate,
                new Primary(1, 0, 0, 5));

        testPlateArmor = new Armor("Special Plate Body Armor", 1, Slot.Body, ArmorType.Plate,
                new Primary(1, 0, 0, 5));
    }


    @Test
    public void equip_TooHighLevel_WeaponInvalidException() {
        assertThrows(Exception.class, () -> warrior.equip(testWeaponAxeLevel2));
    }

    @Test
    public void equip_TooHighLevel_ArmorPieceInvalidException() {
        assertThrows(Exception.class, () -> warrior.equip(testPlateBody));
    }

    @Test
    public void equip_WrongWeaponType_InvalidWeaponException() {
        assertThrows(Exception.class, () -> warrior.equip(testStaffLevel2));
    }

    @Test
    public void equip_WrongArmorType_InvalidArmorException() {
        assertThrows(Exception.class, () -> warrior.equip(testClothHead));
    }

    @Test
    public void heroEquip_ValidWeaponType_True() throws Exception {
        assertTrue(warrior.equip(testWeaponLevel1));
    }

    @Test
    public void heroEquip_ValidArmorPiece_True() throws Exception {
        assertTrue(warrior.equip(testPlateArmor));
    }

    @Test
    public void calculateDPS_NoWeaponEquipped() {
        double expectedDPSValue = 1;
        assertEquals(expectedDPSValue, warrior.calculateDamage());
    }

    @Test
    public void calculateDPS_WithValidWeaponEquipped() throws Exception {
        warrior.equip(testWeaponLevel1);
        assertEquals(2.33, warrior.calculateDamage());
    }

    @Test
    public void calculateDPS_WithValidArmorAndWeaponEquipped() throws Exception {
        warrior.equip(testPlateArmor);
        warrior.equip(testWeaponLevel1);
        int [] currentWarriorValues = {warrior.getTotalAttributes().getVitality(), warrior.getTotalAttributes().getStrength(),
                warrior.getTotalAttributes().getDexterity(), warrior.getTotalAttributes().getIntelligence() };
        int [] expectedValues = {13, 6, 2, 6};
        assertArrayEquals(expectedValues, currentWarriorValues);
    }
}