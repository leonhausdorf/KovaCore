package de.zevyx.iriscore;

import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.commands.*;
import de.zevyx.iriscore.config.MessageConfig;
import de.zevyx.iriscore.config.MySQLConfig;
import de.zevyx.iriscore.config.WorldConfig;
import de.zevyx.iriscore.entities.SpecialVex;
import de.zevyx.iriscore.listener.*;
import de.zevyx.iriscore.manager.*;
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

    private IrisAPI irisAPI;

    public static IrisCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        irisAPI = new IrisAPI();
        mySQLConfig = new MySQLConfig();

        getAPI().getDatabaseAPI().connect();
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
        scoreboardManager = new ScoreboardManager();

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpecialVex(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new CraftingManager(), this);
        Bukkit.getPluginManager().registerEvents(new HologramManager(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);

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

        messageConfig.init();
        mySQLConfig.init();
        worldConfig.init();

        getCraftingManager().loadAllRecipes();

        CustomEnchant.register();
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
