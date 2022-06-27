package de.zevyx.iriscore.utils;

import de.zevyx.iriscore.manager.EnchantmentManager;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomEnchant {

    public static final Enchantment AUTOSMELT = new EnchantmentManager("autosmelt", "Autosmelt", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).toList().contains(AUTOSMELT);

        if (!registered)
            registerEnchantment(AUTOSMELT);
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
