package businesslogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Az emelet osztály, tárolja a pálya mezőit, és az elemeket, amik minden körben
 * aktiválódnak, vagy lépnek.
 */
public class Floor {

    private List<Field> fields; // a pálya mezői
    private List<Activateable> activateables; // a pálya aktiválható elemei
    private List<Orangutan> orangutans; // a pálya orángutánjai
    private List<Panda> pandas; // a pálya pandái

    private Orangutan currentOrangutan = null;

    /**
     * Konstruktor, inicializálja a listákat
     */
    public Floor(){
        fields = new ArrayList<>();
        activateables = new ArrayList<>();
        orangutans = new ArrayList<>();
        pandas = new ArrayList<>();
    }

    /**
     * Egy mezőt ad a pályához.
     * @param f Field
     */
    public void addField(Field f){
        fields.add(f);
    }

    /**
     * Egy Activeablet ad a pályához
     * @param a Aktiválható elem
     */
    public void add(Activateable a) {
        activateables.add(a);
    }

    /**
     * Egy orángutánt ad a pályához
     * @param o Orángután
     */
    public void add(Orangutan o){
        orangutans.add(o);
    }

    /**
     * Egy pandát ad a pályához.
     * @param p Panda
     */
    public void add(Panda p){
        pandas.add(p);
    }

    /**
     * A pálya irányításából eltávolít egy orángutánt
     * @param o az eltávolítandó orángután.
     */
    public void remove (Orangutan o){
        orangutans.remove(o);
    }

    /**
     * A pálya irányításából eltávolít egy pandát
     * @param p az eltávolítandó panda
     */
    public void remove (Panda p){
        pandas.remove(p);
    }

    /**
     * Új kör kezdődik, minden Steppable lép, és minden Activateable-nek meghívódik
     * az activate() függvénye
     */
    public void newTurn() {
        for (int i = 0; i < pandas.size(); i++) {
            Panda p = pandas.get(i);
            if (!p.isStepped() && p.getField() != null) p.step();
        }
        for (int i = 0; i < orangutans.size(); i++) {
            orangutans.get(i).setStepped(false);
        }
        for (int i = 0; i < pandas.size(); i++) {
            pandas.get(i).setStepped(false);
        }
        for (int i = 0; i < activateables.size(); i++)
            activateables.get(i).activate();
    }


    /**
     * Kilistázza a pálya összes mezőjét
     */
    public void listFields() {
        if (fields == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        System.out.println("Fields of the map:");
        for (int i = 0; i < fields.size(); i++) System.out.println(fields.get(i).toString());
    }

    /**
     * Kilistázza az aktív orángután szomszédos mezőit
     */
    public void listNeighbours() {
        if (currentOrangutan == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        Field f = currentOrangutan.getField();
        System.out.println("Neighbours of Field " + f.getId() + ":");
        for (int i = 0; i < f.getNeighbourNumber(); i++) {
            System.out.println(f.getNeighbour(i).toString());
        }
    }

    /**
     * kilistázza az összes pandát
     */
    public void listPandas() {
        if (pandas == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        for (int i = 0; i < pandas.size(); i++) {
            System.out.println(pandas.get(i).toString());
        }
    }

    /**
     * kilistázza az összes orángutánt a pályán
     */
    public void listOrangutans() {
        if (orangutans == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        for (int i = 0; i < orangutans.size(); i++) {
            System.out.println(orangutans.get(i).toString());
        }
    }

    /**
     * kilistázza az összes aktiválható elemet a pályán
     */
    public void listActivateables() {
        if (activateables == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        for (int i = 0; i < activateables.size(); i++)
            System.out.println(activateables.get(i).toString());
    }

    /**
     * Kilistázza a pálya összes szekrényét
     */
    public void listWardrobes() {
        for (int i = 0; i < fields.size(); i++) {
            FieldElement fe = fields.get(i).getFieldElement();
            if (fe != null) fe.printIfWardrobe();
        }
    }

    /**
     * Kilistázza a pálya összes exit pontját
     */
    public void listExits() {
        for (int i = 0; i < fields.size(); i++) {
            FieldElement fe = fields.get(i).getFieldElement();
            if (fe != null) fe.printIfExit();
        }
    }

    /**
     * kilistázza az összes elfogott pandát a pályán
     */
    public void listCaughtPandas() {
        if (pandas == null) {
            System.out.println("MESSAGE: Game cannot list.");
            return;
        }
        for (int i = 0; i < pandas.size(); i++) {
            Panda p = pandas.get(i);
            if (p.getCatcher() != null) {
                System.out.println(p.toString());
            }
        }
    }

    /**
     * Visszaadja az adott idjű Steppablet
     *
     * @param id a kért Steppable idje
     * @return a kapott idjű Steppable
     */
    public Steppable getSteppable(int id) {
        for (int i = 0; i < orangutans.size(); i++)
            if (orangutans.get(i).getId() == id) return orangutans.get(i);
        for (int i = 0; i < pandas.size(); i++)
            if (pandas.get(i).getId() == id) return pandas.get(i);
        return null;
    }

    /**
     * Visszaadja a kért idjű Fieldet ha létezik
     *
     * @param id a kért Field id értéke
     * @return a mező
     */
    public Field getField(int id) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getId() == id) return fields.get(i);
        }
        return null;
    }

    public Activateable getActivateable(int id) {
        for (int i = 0; i < activateables.size(); i++) {
            if (activateables.get(i).getId() == id) return activateables.get(i);
        }
        return null;
    }
}
