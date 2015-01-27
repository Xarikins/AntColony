import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/**
 *  class AntSimGUI
 *
 *  main window for ant simulation
 *  contains:
 *      1.  a control panel for setting up and running the simulation
 *      2.  a graphical view of the ant colony
 */
public class AntSimGUI extends JFrame {

    /*************
     *  attributes
     ************/

    // view for colony
    private ColonyView colonyView;

    // scroll pane for colonyView
    private JScrollPane colonyPane;

    // panel containing buttons for controlling simulation
    private ControlPanel controlPanel;

    // layout for positioning child components
    private SpringLayout layout;

    // user's screen width
    private int screenWidth;

    // user's screen height
    private int screenHeight;

    // list of event listeners for this view
    private LinkedList simulationEventListenerList;


    /***************
     *  constructors
     **************/

    /**
     *  create a new AntSimGUI
     */
    public AntSimGUI() {
        // call superclass constructor and set title of window
        super("Ant Simulation GUI");

        // create anonymous listener to allow user to close window and end sim
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // get user's screen width and height
        screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // set layout
        getContentPane().setLayout(new BorderLayout());

        // set the size of the window
        resizeGUI();

        // create event listener list
        simulationEventListenerList = new LinkedList();

        // show window
        setVisible(true);

        // validate all components
        validate();
    }


    /**********
     *  methods
     *********/

    /**
     *  initialize this GUI
     *
     *  a control panel and scrollable pane for displaying the specified
     *  ColonyView will be created and added to this GUI
     *
     *  @param  colonyView      the ColonyView to be displayed
     */
    public void initGUI(ColonyView colonyView) {
        // create button control panel
        controlPanel = new ControlPanel();

        // set up colony view with default dimensions
        colonyPane = new JScrollPane(colonyView);
        colonyPane.setPreferredSize(new Dimension(800, 600));

        // add control panel and colony view
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(colonyPane, BorderLayout.CENTER);
        // Roughly center the scroll pane
		colonyPane.getViewport().setViewPosition(new Point(400, 600));


        // validate all components
        validate();
    }


    /**
     *  set window size based on user's screen settings
     *
     *  initial size will be smaller than the dimensions of the user's screen
     *  once sized, the window is maximized to fill the screen
     */
    private void resizeGUI() {
        // set window size
        if (screenWidth >= 1280)
            setSize(1024, 768);
        else if (screenWidth >= 1024)
            setSize(800, 600);
        else if (screenWidth >= 800)
            setSize(640, 480);

        // maximize window
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }


    /**
     *  set the current simulation time
     *
     *  @param  time        String indicating simulation time in terms of days and turns
     */
    public void setTime(String time) {
        controlPanel.setTime(time);
    }


    /**
     *  add an event listener to this view
     *
     *  @param  listener        listener interested in this view's events
     */
    public void addSimulationEventListener(SimulationEventListener listener) {
        simulationEventListenerList.add(listener);
    }


    /**
     *  remove an event listener from this view
     *
     *  @param  listener        listener to be removed
     */
    public void removeSimulationEventListener(SimulationEventListener listener) {
        simulationEventListenerList.remove(listener);
    }


    /**
     *  fire a simulation event
     *
     *  @param  eventType       the type of event that occurred (see the
     *                          SimulationEvent class for allowable types)
     */
    private void fireSimulationEvent(int eventType) {
        // create event
        SimulationEvent simEvent = new SimulationEvent(this, eventType);

        // inform all listeners
        for (Iterator itr = simulationEventListenerList.iterator(); itr.hasNext(); ) {
            ((SimulationEventListener)itr.next()).simulationEventOccurred(simEvent);
        }

    }


    /**
     *  inner class ControlPanel
     *
     *  contains buttons for controlling the simulation, and displays the
     *  simulation time
     */
    private class ControlPanel extends JPanel {

        /*************
         *  attributes
         ************/

        // button for setting up a normal simulation
        private JButton normalSetupButton;

        // button for running the simulation continuously
        private JButton runButton;

        // button for running the simulation one turn at a time
        private JButton stepButton;

        // label for displaying the time in the simulation
        private JLabel timeLabel;

        // event handler for button press events
        private ButtonHandler buttonHandler;


        /***************
         *  constructors
         **************/

        /**
         *  create a new control panel
         */
        public ControlPanel() {
            // call superclass constructor
            super();

            // create handler for button press events
            buttonHandler = new ButtonHandler();

            // initialize child components
            initComponents();

            // position child components
            layoutComponents();
        }


        /**
         *  create child components
         */
        private void initComponents() {
            // setup button
            normalSetupButton = new JButton("Normal Setup");
            normalSetupButton.addActionListener(buttonHandler);
            normalSetupButton.setToolTipText("Set up simulation for normal execution");

            // button for running simulation continuously
            runButton = new JButton("Run");
            runButton.addActionListener(buttonHandler);
            runButton.setToolTipText("Run the simulation continuously");

            // button for running simulation one turn at a time
            stepButton = new JButton("Step");
            stepButton.addActionListener(buttonHandler);
            stepButton.setToolTipText("Step through the simulation one turn at a time");

            // label for displaying simulation time
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        }


        /**
         *  position child components and add them to this view
         */
        private void layoutComponents() {
            this.add(normalSetupButton);
            this.add(runButton);
            this.add(stepButton);
            this.add(timeLabel);
        }


        /**
         *  set the current simulation time
         *
         *  @param  time        String indicating simulation time in terms of days and turns
         */
        public void setTime(String time) {
            timeLabel.setText("     " + time);
        }


        /**
         *  inner class ButtonHandler
         *
         *  responsible for handling button press events from the control panel
         */
        private class ButtonHandler implements ActionListener {

            /**********
             *  methods
             *********/

            /**
             *  respond to a button action
             *
             *  fires a simulation event appropriate for the button that is pressed
             */
            public void actionPerformed(ActionEvent e) {
                // get the button that was pressed
                JButton b = (JButton)e.getSource();

                // fire appropriate event
                if (b.getText().equals("Normal Setup")) {
                    // set up for normal simulation
                    fireSimulationEvent(SimulationEvent.NORMAL_SETUP_EVENT);
                } else if (b.getText().equals("Queen Test")) {
                    // set for testing the queen ant
                    fireSimulationEvent(SimulationEvent.QUEEN_TEST_EVENT);
                } else if (b.getText().equals("Scout Test")) {
                    // set for testing the scout ant
                    fireSimulationEvent(SimulationEvent.SCOUT_TEST_EVENT);
                } else if (b.getText().equals("Forager Test")) {
                    // set for testing the forager ant
                    fireSimulationEvent(SimulationEvent.FORAGER_TEST_EVENT);
                } else if (b.getText().equals("Soldier Test")) {
                    // set for testing the soldier ant
                    fireSimulationEvent(SimulationEvent.SOLDIER_TEST_EVENT);
                } else if (b.getText().equals("Run")) {
                    // run the simulation continuously
                    fireSimulationEvent(SimulationEvent.RUN_EVENT);
                } else if (b.getText().equals("Step")) {
                    // run the simulation one turn at a time
                    fireSimulationEvent(SimulationEvent.STEP_EVENT);
                }
            }
        }
    }
}
