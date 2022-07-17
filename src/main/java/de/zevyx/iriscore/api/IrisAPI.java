package de.zevyx.iriscore.api;

public class IrisAPI {

    private static IrisAPI instance;

    private final DukatenAPI dukatenAPI;

    public IrisAPI() {
        instance = this;
        dukatenAPI = new DukatenAPI();
    }

    public static IrisAPI getInstance() {
        return instance;
    }
    public DukatenAPI getDukatenAPI() {
        return dukatenAPI;
    }

    public InventoryBuilder getInventoryBuilder() {
        return new InventoryBuilder();
    }
}
