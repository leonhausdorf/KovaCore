package de.zevyx.iriscore.tribes.akarier;

public class Akarier {


    private final InvisibilityManager invisibilityManager;

    public Akarier() {
        invisibilityManager = new InvisibilityManager();
    }

    public InvisibilityManager getInvisibilityManager() {
        return invisibilityManager;
    }

    public CraftingManager getCraftingManager() {
        return new CraftingManager();
    }

}
