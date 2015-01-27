public class Ant {
    int ID;
    int lifespan;
    boolean alive;
    ColonyNode location;
    int lastTurn;
    int birth;

    public Ant(ColonyNode node) {
        ID = 0;
        lifespan = 0;
        alive = true;
        location = node;
        lastTurn = 0;

    }

    public Ant() {

    }

    public void move(ColonyNode newLocation) {
        location.removeAnt(this);
        location = newLocation;
        location.addAnt(this);
    }

    public void nextTurn(int curTurn) {

    }

    public void die() {
        location.removeAnt(this);
    }

    public void setBirthday(int curTurn) {
        birth = curTurn;
    }

    public void setID(int id) {
        this.ID = id;
    }
}
