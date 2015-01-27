import javax.swing.*;

public class Simulation implements SimulationEventListener {

    Colony colonySimulator;
    AntSimGUI gui;
    int curTurn = 0;
    boolean queenAlive;
    boolean stepping;
    Thread thread;

    Simulation(AntSimGUI gui) {
        queenAlive = true;
        stepping = true;
        this.gui = gui;
        colonySimulator = new Colony(new ColonyView(27, 27), this);
        gui.initGUI(colonySimulator.getView());
        thread = null;
    }

    // This method will tell the colony what turn it is, and that it
    // should take a new turn now
    public void nextTurn() {
        do {
            colonySimulator.nextTurn(curTurn);
            curTurn++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } while (!stepping && queenAlive);
    }

    public void endSimulation() {
        queenAlive = false;
        if (thread != null) {
            thread.interrupt();
        }
        String[] options = {"Ok."};
        int pane = JOptionPane.showOptionDialog(gui,
                                                "The queen is dead, long live the queen!",
                                                "Simulation finished",
                                                0,
                                                JOptionPane.WARNING_MESSAGE,
                                                null,
                                                options,
                                                options[0]);
        if (pane == 0) {
            System.exit(0);
        }
    }

    public void simulationEventOccurred(SimulationEvent simEvent) {
        // normal setup button was pressed
        if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) {
            colonySimulator.initColony();
        }
        if (simEvent.getEventType() == SimulationEvent.RUN_EVENT) {
            stepping = false;
            thread = new Thread() {
                public void run() {
                    nextTurn();
                }
            };
            thread.start();
        }
        if (simEvent.getEventType() == SimulationEvent.STEP_EVENT) {
            stepping = true;
            nextTurn();
        }
    }
}
