package de.zevyx.iriscore;

import de.zevyx.iriscore.api.DatabaseAPI;
import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.commands.*;
import de.zevyx.iriscore.config.MessageConfig;
import de.zevyx.iriscore.config.MySQLConfig;
import de.zevyx.iriscore.config.WorldConfig;
import de.zevyx.iriscore.entities.SpecialVex;
import de.zevyx.iriscore.listener.*;
import de.zevyx.iriscore.manager.*;
import de.zevyx.iriscore.scoreboards.TribePlayerScoreboard;
import de.zevyx.iriscore.scoreboards.TribeScoreboardListener;
import de.zevyx.iriscore.tribes.Tribes;
import de.zevyx.iriscore.utils.CustomEnchant;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class IrisCore extends JavaPlugin {

    private static IrisCore instance;

    private MessageConfig messageConfig;
    private MySQLConfig mySQLConfig;
    private WorldConfig worldConfig;

    @Getter
    private InventoryManager inventoryManager;
    @Getter
    private PlayerManager playerManager;
    @Getter
    private TribeManager tribeManager;
    @Getter
    private EntityManager entityManager;
    @Getter
    private BackpackManager backpackManager;
    @Getter
    private CraftingManager craftingManager;
    @Getter
    private WorldManager worldManager;
    @Getter
    private CooldownManager cooldownManager;
    @Getter
    private ScoreboardManager scoreboardManager;

    @Getter
    private PortalManager portalManager;
    @Getter
    private ParticleManager particleManager;
    @Getter
    private TablistManager tablistManager;

    private IrisAPI irisAPI;

    public static IrisCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        irisAPI = new IrisAPI();
        mySQLConfig = new MySQLConfig();

        messageConfig = new MessageConfig();
        worldConfig = new WorldConfig();
        cooldownManager = new CooldownManager();
        inventoryManager = new InventoryManager();
        playerManager = new PlayerManager();
        tribeManager = new TribeManager();
        entityManager = new EntityManager();
        backpackManager = new BackpackManager();
        craftingManager = new CraftingManager();
        worldManager = new WorldManager();
        // scoreboardManager = new ScoreboardManager();
        portalManager = new PortalManager();
        particleManager = new ParticleManager();
        tablistManager = new TablistManager();


        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpecialVex(), this);
        Bukkit.getPluginManager().registerEvents(new HologramManager(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingManager(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);

        Bukkit.getPluginManager().registerEvents(new de.zevyx.iriscore.tribes.kalnas.CraftingManager(), this);
        Bukkit.getPluginManager().registerEvents(new de.zevyx.iriscore.tribes.akarier.CraftingManager(), this);

        getCommand("iris").setExecutor(new IrisCommand());
        getCommand("iris").setTabCompleter(new IrisTabCompletion());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("gamemode").setTabCompleter(new GamemodeCommand());
        getCommand("world").setExecutor(new WorldCommand());
        getCommand("world").setTabCompleter(new WorldTabCompletion());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("addenchantment").setExecutor(new AddEnchantingCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        messageConfig.init();
        mySQLConfig.init();
        worldConfig.init();
        getPortalManager().init();

        getParticleManager().start();

        getCraftingManager().loadAllRecipes();
        Tribes.getKalnas().getCraftingManager().registerCrafting();
        Tribes.getAkarier().getCraftingManager().registerCrafting();

        CustomEnchant.register();

        getWorldManager().loadAllWorlds();
        getBackpackManager().load();

        TribePlayerScoreboard tribeScoreboard = new TribePlayerScoreboard();
        tribeScoreboard.setDefaultSidebarScore(6, " ");
        tribeScoreboard.setDefaultSidebarScore(5, "§7Dein Tribe       ");
        tribeScoreboard.setDefaultSidebarScore(4, "  §8» §7Kein Tribe");
        tribeScoreboard.setDefaultSidebarScore(3, " ");
        tribeScoreboard.setDefaultSidebarScore(2, "§7Dukaten       ");
        tribeScoreboard.setDefaultSidebarScore(1, "  §8» §e0");
        tribeScoreboard.setDefaultSidebarScore(0, " ");

        Bukkit.getPluginManager().registerEvents(new TribeScoreboardListener(tribeScoreboard), this);
    }

    @Override
    public void onDisable() {
        backpackManager.save();
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public MySQLConfig getMySQLConfig() {
        return mySQLConfig;
    }

    public WorldConfig getWorldConfig() {
        return worldConfig;
    }

    public IrisAPI getAPI() {
        return irisAPI;
    }
}
