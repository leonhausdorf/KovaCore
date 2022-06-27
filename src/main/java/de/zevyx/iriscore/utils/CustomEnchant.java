package de.zevyx.iriscore.utils;

import de.zevyx.iriscore.manager.EnchantmentManager;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomEnchant {

    public static final Enchantment AUTOSMELT = new EnchantmentManager("autosmelt", "Autosmelt", 1);
    public static final Enchantment DOUBLEJUMP = new EnchantmentManager("doublejump", "DoubleJump", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(AUTOSMELT);
        boolean registeredDJ = Arrays.stream(Enchantment.values()).toList().contains(DOUBLEJUMP);

        if (!registered)
            registerEnchantment(AUTOSMELT);
        if (!registeredDJ)
            registerEnchantment(DOUBLEJUMP);
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(enchantment);
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
    }

}
