package businesslogic;

import test.DepthWriter;

/**
 * Játékgép, néha sípol és megijeszti a szomszédok cellákon állókat
 */
public class GamblingMachine extends Activateable {

    public GamblingMachine() {
        DepthWriter.add();
        DepthWriter.print("GamblingMachine CTOR");
        DepthWriter.reduce();
    }

    /**
     * Aktiválódik és megijeszti a szomszédoks cellákon állókat
     * Random szám alapján aktiválódik és fejti ki hatását
     */
    public void activate() {
        DepthWriter.add();
        DepthWriter.print("GamblingMachine.activate()");

        if(random()) {
            System.out.println("Activateable " + getId() + "(GamblingMachine) was activated.");
            getField().scareNeighbours();
        }

        DepthWriter.reduce();
    }
}
