package de.zevyx.iriscore;

import de.zevyx.iriscore.api.IrisAPI;
import de.zevyx.iriscore.commands.GamemodeCommand;
import de.zevyx.iriscore.commands.IrisCommand;
import de.zevyx.iriscore.commands.IrisTabCompletion;
import de.zevyx.iriscore.config.MessageConfig;
import de.zevyx.iriscore.config.MySQLConfig;
import de.zevyx.iriscore.entities.SpecialVex;
import de.zevyx.iriscore.listener.*;
import de.zevyx.iriscore.manager.*;
import de.zevyx.iriscore.utils.Backpack;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class IrisCore extends JavaPlugin {

    private static IrisCore instance;

    private MessageConfig messageConfig;
    private MySQLConfig mySQLConfig;

    private InventoryManager inventoryManager;
    private PlayerManager playerManager;
    private TribeManager tribeManager;
    private EntityManager entityManager;
    private BackpackManager backpackManager;
    private CraftingManager craftingManager;

    private IrisAPI irisAPI;

    @Override
    public void onEnable() {
        instance = this;
        irisAPI = new IrisAPI();
        mySQLConfig = new MySQLConfig();

        getAPI().getDatabaseAPI().connect();

        messageConfig = new MessageConfig();
        inventoryManager = new InventoryManager();
        playerManager = new PlayerManager();
        tribeManager = new TribeManager();
        entityManager = new EntityManager();
        backpackManager = new BackpackManager();
        craftingManager = new CraftingManager();


        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpecialVex(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuildListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);

        getCommand("iris").setExecutor(new IrisCommand());
        getCommand("iris").setTabCompleter(new IrisTabCompletion());
        getCommand("gamemode").setExecutor(new GamemodeCommand());

        messageConfig.init();
        mySQLConfig.init();

        getCraftingManager().loadAllRecipes();
    }

    @Override
    public void onDisable() {
        backpackManager.save();
    }

    public static IrisCore getInstance() {
        return instance;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public MySQLConfig getMySQLConfig() {
        return mySQLConfig;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TribeManager getTribeManager() {
        return tribeManager;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CraftingManager getCraftingManager() {
        return craftingManager;
    }

    public IrisAPI getAPI() {
        return irisAPI;
    }
}
