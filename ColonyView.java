import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *  class ColonyView
 *
 *  provides a graphical view for the ant colony
 *
 *  this class is a container for the views for the individual nodes of the
 *  colony
 */
public class ColonyView extends JPanel {

    /************
     *  constants
     ***********/

    // size for an individual node in the colony
    public final static int NODE_SIZE = 96;

    // background color
    private final Color BACKGROUND_COLOR = new Color(200, 178, 55);


    /*************
     *  attributes
     ************/

    // layout for positioning child components
    private SpringLayout layout;


    /***************
     *  constructors
     **************/

    /**
     *  create a new ColonyView with the specified height and width
     *
     *  height is measured as the number of nodes along the Y axis
     *  width is measured as the number of nodes along the X axis
     *
     *  @param  colonyHeight            height of colony in nodes
     *  @param  colonyWidth             width of colony in nodes
     */
    public ColonyView(int colonyHeight, int colonyWidth) {
        // call superclass constructor
        super();

        // create and set layout
        layout = new SpringLayout();
        setLayout(layout);

        // set size of this view
        setPreferredSize(new Dimension(colonyWidth * NODE_SIZE, colonyHeight * NODE_SIZE));

        // set background color
        setBackground(BACKGROUND_COLOR);
    }


    /**********
     *  methods
     *********/

    /**
     *  add the specifed ColonyNode to this view
     *
     *  use this method to add individual colony node views to this view
     *
     *  since the colony is represented as a two-dimensional grid, each node
     *  view can be represented by an (x,y), or (row, column) coordinate pair
     *
     *  @param  nodeView        ColonyNodeView to be added to colony view
     *  @param  x               x coordinate of node view in colony grid
     *  @param  y               y coordinate of node view in colony grid
     */
    public void addColonyNodeView(ColonyNodeView nodeView, int x, int y) {
        // position node view
        layout.putConstraint(SpringLayout.WEST, nodeView, x * NODE_SIZE, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nodeView, y * NODE_SIZE, SpringLayout.NORTH, this);

        // add node view to layout
        this.add(nodeView);

        // contents of node should initially be hidden
        nodeView.hideNode();
    }
}
