package de.zevyx.iriscore.utils;

import lombok.Getter;
import org.bukkit.Sound;

public enum CooldownType {

    AKARIER_INVISIBILITY("Invisibility", 60, true, Sound.UI_TOAST_IN),
    DOUBLEJUMP("Double Jump", 10, true, Sound.UI_TOAST_IN),

    VEX("Vex", 29, true, Sound.UI_TOAST_IN),
    COMBAT("Combat", 20, true, Sound.UI_TOAST_IN),

    TELEPORTATION("Teleportation", 5, true, Sound.ENTITY_ENDERMAN_TELEPORT),

    TEST("TestType", 30, true, Sound.UI_TOAST_IN),
    TEST2("TestType2", 40, true, Sound.UI_TOAST_IN),
    TEST3("TestType3", 20, true, Sound.UI_TOAST_IN),
    TEST4("TestType4", 20, true, Sound.UI_TOAST_IN),
    TEST5("TestType5", 20, true, Sound.UI_TOAST_IN),

    ;


    @Getter
    final String name;
    @Getter
    final int cooldown;
    @Getter
    final boolean shown;
    @Getter
    final Sound finishSound;

    CooldownType(String name, int cooldown, boolean shown, Sound finish) {
        this.name = name;
        this.cooldown = cooldown;
        this.shown = shown;
        this.finishSound = finish;
    }
}
