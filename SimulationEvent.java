import java.util.EventObject;

/**
 *  class SimulationEvent
 *
 *  represents an event that can occur in the simulation
 */
public class SimulationEvent extends EventObject {

    /************
     *  constants
     ***********/

    // types of events

    // set up simulation for normal run
    public final static int NORMAL_SETUP_EVENT = 0;

    // set up simulation for testing queen ant
    public final static int QUEEN_TEST_EVENT = 1;

    // set up simulation for testing scout ant
    public final static int SCOUT_TEST_EVENT = 2;

    // set up simulation for testing forager ant
    public final static int FORAGER_TEST_EVENT = 3;

    // set up simulation for testing soldier ant
    public final static int SOLDIER_TEST_EVENT = 4;

    // run simulation continuously
    public final static int RUN_EVENT = 5;

    // run simulation one turn at a time
    public final static int STEP_EVENT = 6;

    // exit the simulation
    public final static int EXIT_EVENT = 7;


    /*************
     *  attributes
     ************/

    // type of event
    private int eventType;


    /***************
     *  constructors
     **************/

    /**
     *  create a new SimulationEvent
     *
     *  @param  source      Object on which event occurred
     *  @param  eventType   type of event
     */
    public SimulationEvent(Object source, int eventType) {
        super(source);
        this.eventType = eventType;
    }


    /**********
     *  methods
     *********/

    /**
     *  return the type of event this is
     *
     *  @return a valid SimulationEvent type
     */
    public int getEventType() {
        return eventType;
    }
}
