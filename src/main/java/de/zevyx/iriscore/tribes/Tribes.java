package de.zevyx.iriscore.tribes;

import de.zevyx.iriscore.tribes.akarier.Akarier;
import de.zevyx.iriscore.tribes.kalnas.Kalnas;

public class Tribes {

    public static Akarier akarier;
    public static Kalnas kalnas;

    public static Akarier getAkarier() {
        return new Akarier();
    }

    public static Kalnas getKalnas() {
        return new Kalnas();
    }
}
