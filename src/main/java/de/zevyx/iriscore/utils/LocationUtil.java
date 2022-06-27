package de.zevyx.iriscore.utils;

import org.bukkit.Location;

import java.util.ArrayList;

public class LocationUtil {
    public static ArrayList<Location> generateCircleHorizontal(Location middle, double radius, int amount) {
        double alpha = (2 * Math.PI) / amount;

        ArrayList<Location> locs = new ArrayList<>();

        for (int count = 0; count != amount; count++) {
            double beta = alpha * count;

            double x = radius * Math.cos(beta);
            double z = radius * Math.sin(beta);

            Location loc = new Location(middle.getWorld(), x + middle.getX(), middle.getY(), middle.getZ() + z);
            locs.add(loc);
        }

        return locs;
    }


    //TODO: Change direction with Yaw and Pitch
    public static ArrayList<Location> generateCircleVertical(Location middle, double radius, int amount, boolean stayX) {
        double alpha = (2 * Math.PI) / amount;
        ArrayList<Location> locs = new ArrayList<>();

        if (stayX) {
            for (int count = 0; count != amount; count++) {
                double beta = alpha * count;

                double z = radius * Math.cos(beta);
                double y = radius * Math.sin(beta);

                locs.add(new Location(middle.getWorld(), middle.getX(), middle.getY() + y, middle.getBlockZ() + z));
            }
        } else {
            for (int count = 0; count != amount; count++) {
                double beta = alpha * count;

                double x = radius * Math.cos(beta);
                double y = radius * Math.sin(beta);

                locs.add(new Location(middle.getWorld(), x + middle.getX(), middle.getY() + y, middle.getBlockZ()));
            }
        }
        return locs;
    }


    public static ArrayList<Location> generateHelix(Location bottom, double radius, double height) {
        ArrayList<Location> locs = new ArrayList<>();

        int counter = 0;
        for (double y = 0; y <= height; y += 0.05) {
            double x = radius * Math.cos(counter);
            double z = radius * Math.sin(counter);

            locs.add(new Location(bottom.getWorld(), bottom.getX() + x, bottom.getY() + y, bottom.getZ() + z));
            counter += 0.05;
        }
        return locs;
    }
}
