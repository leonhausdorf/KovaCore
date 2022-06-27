package de.zevyx.iriscore.api;

public class IrisAPI {

    private static IrisAPI instance;

    private final DatabaseAPI databaseAPI;
    private final DukatenAPI dukatenAPI;

    public IrisAPI() {
        instance = this;
        databaseAPI = new DatabaseAPI();
        dukatenAPI = new DukatenAPI();
    }

    public static IrisAPI getInstance() {
        return instance;
    }

    public DatabaseAPI getDatabaseAPI() {
        return databaseAPI;
    }

    public DukatenAPI getDukatenAPI() {
        return dukatenAPI;
    }

    public InventoryBuilder getInventoryBuilder() {
        return new InventoryBuilder();
    }
}
