package businesslogic;

public class Sofa extends Activateable {

    private Panda panda;

    public Sofa() {
        DepthWriter.add();
        DepthWriter dw = new DepthWriter("Sofa CTOR");
        DepthWriter.reduce();
    }

    public void setPanda(Panda panda) {
        DepthWriter.add();
        DepthWriter dw = new DepthWriter("Sofa.setPanda()");
        DepthWriter.reduce();
        this.panda = panda;
    }

    public Panda getPanda() {
        DepthWriter.add();
        DepthWriter dw = new DepthWriter("Sofa.getPanda()");
        DepthWriter.reduce();
        return panda;
    }

    public void sit(Panda p) {
        DepthWriter.add();
        DepthWriter dw = new DepthWriter("Sofa.sit()");
        DepthWriter.reduce();
    }

    public void activate() {
        DepthWriter.add();
        DepthWriter dw = new DepthWriter("Sofa.activate()");
        int i = 0;
        while (panda != null) {
            Field temp = getField().getNeighbour(i);
            if (temp == null) return;         //nincs üres szomszédos mező
            FieldElement fe = temp.getFieldElement();
            if (fe == null) {
                temp.accept(panda);
                break;
            }
            i++;
        }
        getField().sleepNeighbours(this);
        DepthWriter.reduce();
    }
}
