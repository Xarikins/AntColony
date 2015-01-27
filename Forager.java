import java.util.*;

public class Forager extends Ant {
    boolean returnToNest;
    ArrayList<ColonyNode> moveHistory;
    int retracePosition;


    public Forager(ColonyNode node) {
        location = node;
        lastTurn = -1;
        returnToNest = false;
        moveHistory = new ArrayList<ColonyNode>();
        moveHistory.add(location);

    }

    Forager() {

    }

    public ColonyNode findMostPheromone() {
        ArrayList<ColonyNode> adjacentList = location.getAdjacentNodes();
        ArrayList<ColonyNode> randomNode = new ArrayList<ColonyNode>();
        Random chooseNode = new Random();
        // remove nodes that have already been visited
        for (Iterator<ColonyNode> iterator = adjacentList.iterator(); iterator.hasNext();) {
            ColonyNode s = iterator.next();
            if (moveHistory.contains(s) || !s.isVisible()) {
                iterator.remove();
            }
        }
        // if all nodes have been visited
        if (adjacentList.size() == 0) {
            adjacentList = location.getAdjacentNodes();
        }
        // Assume that the first element has the most pheromones
        ColonyNode maxPher = adjacentList.get(0);

        // Then check all of the others to see if any of them are bigger
        for (int i = 1; i < adjacentList.size(); i++) {
            if (maxPher.isVisible() && maxPher.getPheromone() < adjacentList.get(i).getPheromone()) {
                maxPher = adjacentList.get(i);
            }
        }

        for (int j = 0; j < adjacentList.size(); j++) {
            if ((adjacentList.get(j).getPheromone() == maxPher.getPheromone()) && adjacentList.get(j).isVisible()) {
                randomNode.add(adjacentList.get(j));
            }
        }
        maxPher = randomNode.get(chooseNode.nextInt(randomNode.size()));
        return maxPher;
    }

    public void move(ColonyNode node) {
        location.removeAnt(this);
        location = node;
        location.addAnt(this);

        if (location.hasQueen() && returnToNest) {
            location.setFood(location.getFood() + 1);
            returnToNest = false;
            moveHistory.clear();
        }
        moveHistory.add(location);
    }

    public boolean foundFood() {
        if (location.getFood() > 0 && !(location.hasQueen())) {
            location.setFood(location.getFood() - 1);
            returnToNest = true;
            retracePosition = moveHistory.size() - 1;
            return true;

        } else return false;
    }


    public void nextTurn(int curTurn) {
        ColonyNode destination;
        if (lastTurn == curTurn)
            return;

        lastTurn = curTurn;
        if (returnToNest) {
            // We have found food, so we're going home
            retracePosition --;
            destination = moveHistory.get(retracePosition);
            depositPheromones();
        } else {
            // We haven't found food yet, so keep exploring
            destination = findMostPheromone();
        }

        // We're always going to move every turn
        move(destination);
        if (!(returnToNest)) {
            foundFood();
        }
        // when age > 1year, die
        if ((curTurn - birth) > 10 * 365) {
            die();
        }
    }

    public void depositPheromones() {
        if (!(location.hasQueen())) {
            if (location.getPheromone() < 1000) {
                location.setPheromone(location.getPheromone() + 10);
            }
        }
    }

    public void die() {
        if (returnToNest == true) {
            location.setFood(location.getFood() + 1);
        }
        location.removeAnt(this);
    }
}
