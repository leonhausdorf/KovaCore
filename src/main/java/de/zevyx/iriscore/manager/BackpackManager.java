package de.zevyx.iriscore.manager;

import de.zevyx.iriscore.IrisCore;
import de.zevyx.iriscore.utils.Backpack;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BackpackManager {

    private final Map<UUID, Backpack> map;

    public BackpackManager() {
        map = new HashMap<>();

        load();
    }

    public Backpack getBackpack(UUID uuid) {

        if (map.containsKey(uuid)) {
            return map.get(uuid);
        }

        Backpack backpack = new Backpack(uuid);

        map.put(uuid, backpack);

        return map.getOrDefault(uuid, new Backpack(uuid));
    }

    public void setBackpack(UUID uuid, Backpack backpack) {
        map.put(uuid, backpack);
    }

    private void load() {
        List<String> uuids = IrisCore.getInstance().getPlayerManager().getAllBackpacks();

        uuids.forEach(s -> {
            UUID uuid = UUID.fromString(s);
            if (IrisCore.getInstance().getPlayerManager().getBackpackContents(s) != null) {
                String base64 = IrisCore.getInstance().getPlayerManager().getBackpackContents(s);

                try {
                    map.put(uuid, new Backpack(uuid, base64));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                map.put(uuid, new Backpack(uuid));
            }
        });
    }

    public void save() {
        map.forEach((uuid, backpack) -> {
            IrisCore.getInstance().getPlayerManager().setBackpackContents(uuid.toString(), backpack.toBase64());
        });
    }

}
