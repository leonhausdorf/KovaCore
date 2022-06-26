package de.zevyx.iriscore.utils;

import lombok.Getter;
import org.bukkit.Sound;

public enum CooldownType {

    AKARIER_INVISIBILITY("Invisibility", 60, true, Sound.BLOCK_NOTE_BLOCK_BIT),
    TEST("TestType", 30, true, Sound.BLOCK_NOTE_BLOCK_BIT),
    TEST2("TestType2", 40, true, Sound.BLOCK_NOTE_BLOCK_BIT),
    TEST3("TestType3", 20, true, Sound.BLOCK_NOTE_BLOCK_BIT),

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
