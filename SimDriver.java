public class SimDriver {
    public static void main(String [] args) {
        AntSimGUI gui = new AntSimGUI();

        gui.addSimulationEventListener(new Simulation(gui));

    }
}
