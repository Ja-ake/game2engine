package engine;

public abstract class AbstractSystem implements Updatable {

    public AbstractSystem() {
        Main.updatables.add(this);
    }

    public void destroy() {
        Main.updatables.remove(this);
    }

}
