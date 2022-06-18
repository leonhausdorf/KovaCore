package de.zevyx.kovacore.api;

public class KovaAPI {

    private static KovaAPI instance;

    private DatabaseAPI databaseAPI;

    public KovaAPI() {
        instance = this;
        databaseAPI = new DatabaseAPI();
    }

    public static KovaAPI getInstance() {
        return instance;
    }

    public DatabaseAPI getDatabaseAPI() {
        return databaseAPI;
    }

}
