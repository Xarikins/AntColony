import java.util.*;

public class ColonyNode {

    int food;
    int pheromone;
    int x, y;
    boolean isVisible;
    int antCount;
    ArrayList<Ant> antList;
    ArrayList<Ant> removeList;
    ArrayList<Ant> addList;
    boolean isQueen;
    ColonyNodeView nodeView;
    boolean isEntrance;
    boolean traversing;
    Colony colony;

    ColonyNode(ColonyNodeView nodeView, int x, int y) {
        Random foodGen = new Random();
        if (foodGen.nextInt(4) == 0) {
            food = foodGen.nextInt(500) + 500;
        } else food = 0;
        this.nodeView = nodeView;
        this.x = x;
        this.y = y;
        pheromone = 0;
        isVisible = false;
        antCount = 0;
        isQueen = false;
        antList = new ArrayList<Ant>();
        removeList = new ArrayList<Ant>();
        addList = new ArrayList<Ant>();
        isEntrance = false;
    }

    public void addAnt(Ant newAnt) {
        if (traversing) {
            // If we're still moving through the list, don't try to modify it during traversal
            addList.add(newAnt);
        } else {
            antList.add(newAnt);
        }
        updateView();
    }

    public void removeAnt(Ant newAnt) {
        if (traversing) {
            // If we're still moving through the list, don't try to modify it during traversal
            removeList.add(newAnt);
        } else {
            antList.remove(newAnt);
        }
        updateView();
    }

    public void setVisible(boolean val) {
        isVisible = val;
        if (val == true) {
            nodeView.showNode();
        }
        if (val == false) {
            nodeView.hideNode();
        }
    }

    public int countNumAntsOfType(Ant myAntType) {
        int antCount = 0;
        for (int i = 0; i < antList.size(); i++) {
            if (antList.get(i).getClass() == myAntType.getClass()) {
                antCount++;
            }
        }
        return antCount;
    }

    public void updateNode() {
        this.nodeView.showNode();
    }

    public void updateView() {
        int queenCount = countNumAntsOfType(new Queen());
        if (queenCount == 1) {
            isQueen = true;
            nodeView.setQueen(isQueen);
            nodeView.showQueenIcon();
        }

        nodeView.setScoutCount(countNumAntsOfType(new Scout()));
        if (countNumAntsOfType(new Scout()) > 0)
            nodeView.showScoutIcon();
        else nodeView.hideScoutIcon();

        nodeView.setForagerCount(countNumAntsOfType(new Forager()));
        if (countNumAntsOfType(new Forager()) > 0)
            nodeView.showForagerIcon();
        else nodeView.hideForagerIcon();

        nodeView.setSoldierCount(countNumAntsOfType(new Soldier()));
        if (countNumAntsOfType(new Soldier()) > 0)
            nodeView.showSoldierIcon();
        else nodeView.hideSoldierIcon();

        nodeView.setBalaCount(countNumAntsOfType(new Bala()));
        if (countNumAntsOfType(new Bala()) > 0)
            nodeView.showBalaIcon();
        else nodeView.hideBalaIcon();

        nodeView.setFoodAmount(food);
        nodeView.setPheromoneLevel(pheromone);
    }

    public void updateList() {
        for (Ant s : removeList) {
            antList.remove(s);
        }
        removeList.clear();
        for (Ant s : addList) {
            antList.add(s);
        }
        addList.clear();
    }

    public void nextTurn(int curTurn) {
        if ((curTurn != 0) && (curTurn % 10 == 0)) {
            this.setPheromone(getPheromone() / 2);
        }
        traversing = true;
        for (Ant nextAnt : antList) {
            nextAnt.nextTurn(curTurn);
        }
        traversing = false;
        updateList();
        updateView();
    }

    public void setFood(int foodCount) {
        food = foodCount;
    }

    public int getFood() {
        return food;
    }

    public void setPheromone(int pheromoneCount) {
        pheromone = pheromoneCount;
    }

    public int getPheromone() {
        return pheromone;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }

    public ArrayList<ColonyNode> getAdjacentNodes() {
        return colony.getAdjacentNodes(this);
    }

    public boolean hasQueen() {
        return isQueen;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public ArrayList<Ant> getAntsOfType(Ant type) {
        ArrayList<Ant> antType = new ArrayList<Ant>();
        for (int i = 0; i < antList.size(); i++) {
            if (antList.get(i).getClass() == type.getClass()) {
                antType.add(antList.get(i));
            }
        }
        return antType;
    }

    public ArrayList<Ant> getNonBalaAnts() {
        ArrayList<Ant> nonBala = new ArrayList<Ant>();
        for (int i = 0; i < antList.size(); i++) {
            if (antList.get(i).getClass() != new Bala().getClass()) {
                nonBala.add(antList.get(i));
            }
        }
        return nonBala;
    }

}


