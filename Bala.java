
import java.util.*;
public class Bala extends Ant {

    Bala(ColonyNode node) {
        location = node;
        lastTurn = -1;

    }

    Bala() {

    }

    public void nextTurn(int curTurn) {
        // check if ants in the list have already taken their turn
        if (lastTurn == curTurn)
            return;

        // when age > 1year, die
        if ((curTurn - birth) > 10 * 365) {
            die();
            return;
        }

        lastTurn = curTurn;
        ArrayList<Ant> nonBala = location.getNonBalaAnts();
        // if ant has not taken turn, and nonBala ants are present, fight friendly ants
        if (nonBala.size() > 0) {
            fight();
        }

        else {
            Random moveGen = new Random();
            ArrayList<ColonyNode> adjacentList = location.getAdjacentNodes();
            ColonyNode destination;
            // Set destination to a random adjacent visible node
            destination = adjacentList.get(moveGen.nextInt(adjacentList.size()));
            move(destination);
        }
    }

    public void move(ColonyNode node) {
        location.removeAnt(this);
        location = node;
        location.addAnt(this);

    }

    public void fight() {
        ArrayList<Ant> nonBala = location.getNonBalaAnts();
        Random kill = new Random();
        int half = kill.nextInt(2);
        if (half == 0) {
            nonBala.get(0).die();
        }
    }
}
