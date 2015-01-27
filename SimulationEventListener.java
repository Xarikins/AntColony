import java.util.EventListener;

/**
 *	interface SimulationEventListener
 *
 *	encapsulates functionality required for responding to a SimulationEvent
 *
 *	any class that needs to respond directly to an event in the simulation must
 *	implement this interface
 */
public interface SimulationEventListener extends EventListener
{
	
	/**
	 *	respond to a SimulationEvent
	 */
	void simulationEventOccurred(SimulationEvent simEvent);
	
}