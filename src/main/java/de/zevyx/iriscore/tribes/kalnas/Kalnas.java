package de.zevyx.iriscore.tribes.kalnas;

public class Kalnas {

    public CraftingManager craftingManager;
    public PickaxeManager pickaxeManager;

    public Kalnas() {
        craftingManager = new CraftingManager();
        pickaxeManager = new PickaxeManager();
    }

    public CraftingManager getInvisibilityManager() {
        return craftingManager;
    }

    public PickaxeManager getPickaxeManager() {
        return pickaxeManager;
    }
}
