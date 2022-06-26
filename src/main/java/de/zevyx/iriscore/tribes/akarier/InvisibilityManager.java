package de.zevyx.iriscore.tribes.akarier;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityManager {

    private static final List<Player> invisible = new ArrayList<>();

    public InvisibilityManager() {

    }

    public List<Player> getInvisible() {
        return invisible;
    }


}
