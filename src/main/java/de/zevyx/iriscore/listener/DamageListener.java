package de.zevyx.iriscore.listener;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.tribes.Tribes;
import de.zevyx.iriscore.utils.CooldownType;
import de.zevyx.iriscore.utils.Util;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {


    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            IrisCore.getInstance().getCooldownManager().addCooldown(damager.getUniqueId(), CooldownType.COMBAT);
            if (e.getEntity() instanceof Player) {
                Player t = (Player) e.getEntity();
                IrisCore.getInstance().getCooldownManager().addCooldown(t.getUniqueId(), CooldownType.COMBAT);

                if (IrisCore.getInstance().getPlayerManager().getTribe(damager) == 5) {
                    IrisCore.getInstance().getCooldownManager().addCooldown(damager.getUniqueId(), CooldownType.AKARIER_INVISIBILITY);
                    if (Tribes.getAkarier().getInvisibilityManager().getInvisible().contains(damager) && damager.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        damager.removePotionEffect(PotionEffectType.INVISIBILITY);
                        Tribes.getAkarier().getInvisibilityManager().getInvisible().remove(damager);
                    }
                }

                if (IrisCore.getInstance().getPlayerManager().getTribe(t) == 2) {
                    if (t.getInventory().getItemInOffHand().getType() == Material.SHIELD && e.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) != 0) {
                            reverseDamage(damager, e.getDamage());
                            e.setCancelled(true);
                    }
                }
            }
        }
    }

    public void reverseDamage(Player p, double originalDamage) {
        double points = p.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        double toughness = p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
        PotionEffect effect = p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        int resistance = effect == null ? 0 : effect.getAmplifier();
        int epf = getEPF(p);

        p.damage(calculateDamageApplied(originalDamage, points, toughness, resistance, epf));
    }

    public double calculateDamageApplied(double damage, double points, double toughness, int resistance, int epf) {
        double withArmorAndToughness = damage * (1 - Math.min(20, Math.max(points / 5, points - damage / (2 + toughness / 4))) / 25);
        double withResistance = withArmorAndToughness * (1 - (resistance * 0.2));
        double withEnchants = withResistance * (1 - (Math.min(20.0, epf) / 25));
        return withEnchants;
    }

    public int getEPF(Player p) {
        PlayerInventory inv = p.getInventory();
        ItemStack helm = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
        ItemStack legs = inv.getLeggings();
        ItemStack boot = inv.getBoots();
        return (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);
    }

}
