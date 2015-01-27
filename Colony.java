import java.util.*;
public class Colony {
    ColonyNode [][] colonyGrid;
    ColonyView colonyView;
    ColonyNodeView cnv;
    ColonyNode cn;
    Simulation sim;


    Colony(ColonyView view, Simulation sim) {
        this.sim = sim;
        colonyGrid = new ColonyNode [27][27];
        this.colonyView = view;




    }

    public void addColonyNode(ColonyNode nodeNum, int x, int y) {
        colonyGrid[x][y] = nodeNum;

    }

    public ColonyView getView() {
        return colonyView;
    }

    public void nextTurn(int curTurn) {
        for (int k = 0; k < 27; k++) {
            for (int m = 0; m < 27; m++) {
                colonyGrid[k][m].nextTurn(curTurn);
            }
        }

    }

    public void initColony() {
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                cnv = new ColonyNodeView();
                cn = new ColonyNode(cnv, i, j);
                cn.setColony(this);
                colonyView.addColonyNodeView(cnv, i, j);
                addColonyNode(cn, i, j);
                cnv.setID(i + "," + j);

                if (i == 13 && j == 13) {
                    Queen q = new Queen(cn);
                    cn.setFood(1000);
                    cn.addAnt(q);

                    for (int k = 0; k < 10; k++) {
                        q.hatch(new Soldier(cn));
                    }
                    for (int l = 0; l < 50; l++) {
                        q.hatch(new Forager(cn));
                    }
                    for (int m = 0; m < 4; m++) {
                        q.hatch(new Scout(cn));
                    }
                }
                if ((i == 12 && j == 12) || (i == 12 && j == 13) || (i == 12 && j == 14) || (i == 13 && j == 12) || (i == 13 && j == 13) || (i == 13 && j == 14) || (i == 14 && j == 12) || (i == 14 && j == 13) || (i == 14 && j == 14)) {
                    cn.setVisible(true);
                }


            }
        }
    }

    public ArrayList<ColonyNode> getAdjacentNodes(ColonyNode node) {
        int x = node.getX();
        int y = node.getY();
        ArrayList<ColonyNode> adjacentNodes;
        adjacentNodes = new ArrayList<ColonyNode>();

        for (int dx = -1; dx <= 1; ++dx) {
            for (int dy = -1; dy <= 1; ++dy) {
                if (dx != 0 || dy != 0) {
                    try {
                        adjacentNodes.add(colonyGrid[x + dx][y + dy]);
                    } catch (Exception e) {}
                }
            }
        }
        return adjacentNodes;
    }


}
