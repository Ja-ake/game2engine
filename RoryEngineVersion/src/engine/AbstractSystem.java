package engine;

public abstract class AbstractSystem {

    public AbstractSystem() {
        Main.systems.get(getLayer()).add(this);
    }

    public void destroy() {
        Main.systems.get(getLayer()).remove(this);
    }
    
    public int getLayer() {
        return 0;
    }

    public abstract void update();
}
