import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;


/**
 *  class ColonyNodeView
 *
 *  provides a graphical view for a single node in the ant colony
 */
public class ColonyNodeView extends JPanel {
    /************
     *  constants
     ***********/

    // default font for text
    private final Font NODE_FONT = new Font("Verdana", Font.BOLD, 10);

    // background color if node has been revealed
    private final Color OPEN_NODE_COLOR = new Color(200, 178, 55);

    // background color if queen is present
    private final Color QUEEN_NODE_COLOR = new Color(128, 128, 128);

    // color for text
    private final Color LABEL_COLOR = Color.BLACK;

    // colors depicting concentration of pheromone
    //      ranking is violet (low) to red (high)
    private final Color PHEROMONE_1000 = new Color(220, 79, 79);    // red
    private final Color PHEROMONE_800 = new Color(255, 204, 50);    // orange
    private final Color PHEROMONE_600 = new Color(255, 255, 101);   // yellow
    private final Color PHEROMONE_400 = new Color(105, 171, 100);   // green
    private final Color PHEROMONE_200 = new Color(79, 79, 252);     // blue
    private final Color PHEROMONE_0 = new Color(169, 78, 202);      // violet


    /*************
     *  attributes
     ************/

    // layout for positioning components
    private SpringLayout layout;

    // displays node ID
    private JLabel IDLabel;

    // displays whether the queen is present
    private JLabel queenLabel;

    // displays number of foragers
    private JLabel foragerLabel;

    // displays number of scouts
    private JLabel scoutLabel;

    // displays number of soldiers
    private JLabel soldierLabel;

    // displays number of enemy Bala ants
    private JLabel balaLabel;

    // displays amount of food present
    private JLabel foodLabel;

    // displays level of pheromone present
    private JLabel pheromoneLabel;

    // icon to indicate presence of queen ant
    private JLabel queenIcon;

    // icon to indicate presence of 1 or more Bala ants
    private JLabel balaIcon;

    // icon to indicate presence of 1 or more soldier ants
    private JLabel soldierIcon;

    // icon to indicate presence of 1 or more scout ants
    private JLabel scoutIcon;

    // icon to indicate presence of 1 or more forager ants
    private JLabel foragerIcon;

    // image for queen ant
    private ImageIcon queenImage;

    // image for scout ant
    private ImageIcon scoutImage;

    // image for forager ant
    private ImageIcon foragerImage;

    // image for soldier ant
    private ImageIcon soldierImage;

    // image for Bala ant
    private ImageIcon balaImage;


    /***************
     *  constructors
     **************/

    /**
     *  create a new ColonyNodeView
     */
    public ColonyNodeView() {
        super();

        // create and set layout for child components
        layout = new SpringLayout();
        this.setLayout(layout);

        // initialize child components
        initComponents();

        // position and display child components
        layoutComponents();

        // set background color
        setBackground(OPEN_NODE_COLOR);

        // set border
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        // set size
        setPreferredSize(new Dimension(ColonyView.NODE_SIZE, ColonyView.NODE_SIZE));
    }


    /**********
     *  methods
     *********/


    /**
     *  initialize child components
     */
    private void initComponents() {
        // displays ID of this view
        IDLabel = new JLabel("");
        IDLabel.setFont(NODE_FONT);
        IDLabel.setForeground(LABEL_COLOR);

        // displays presence of queen ant
        queenLabel = new JLabel("");
        queenLabel.setFont(NODE_FONT);
        queenLabel.setForeground(LABEL_COLOR);

        // displays number of foragers
        foragerLabel = new JLabel("F");
        foragerLabel.setFont(NODE_FONT);
        foragerLabel.setForeground(LABEL_COLOR);

        // displays number of scouts
        scoutLabel = new JLabel("Sc");
        scoutLabel.setFont(NODE_FONT);
        scoutLabel.setForeground(LABEL_COLOR);

        // displays number of soldiers
        soldierLabel = new JLabel("S");
        soldierLabel.setFont(NODE_FONT);
        soldierLabel.setForeground(LABEL_COLOR);

        // displays number of Balas
        balaLabel = new JLabel("B");
        balaLabel.setFont(NODE_FONT);
        balaLabel.setForeground(LABEL_COLOR);

        // displays amount of food present
        foodLabel = new JLabel("Food:");
        foodLabel.setFont(NODE_FONT);
        foodLabel.setForeground(LABEL_COLOR);

        // displays level of pheromone present
        pheromoneLabel = new JLabel("Ph:");
        pheromoneLabel.setFont(NODE_FONT);
        pheromoneLabel.setForeground(LABEL_COLOR);

        // indicates presence of queen ant
        queenIcon = new JLabel(new ImageIcon("images/queen.gif"));
        queenIcon.setVisible(false);

        // indicates presence of scout ant
        scoutIcon = new JLabel(new ImageIcon("images/scout.gif"));
        scoutIcon.setVisible(false);

        // indicates presence of forager ant
        foragerIcon = new JLabel(new ImageIcon("images/forager.gif"));
        foragerIcon.setVisible(false);

        // indicates presence of soldier ant
        soldierIcon = new JLabel(new ImageIcon("images/soldier.gif"));
        soldierIcon.setVisible(false);

        // indicates presence of Bala ant
        balaIcon = new JLabel(new ImageIcon("images/bala.gif"));
        balaIcon.setVisible(false);
    }


    /**
     *  lay out child components and add them to this view
     */
    private void layoutComponents() {
        // position components

        // ID label
        layout.putConstraint(SpringLayout.WEST, IDLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 5, SpringLayout.NORTH, this);

        // forager label
        layout.putConstraint(SpringLayout.WEST, foragerLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, foragerLabel, 25, SpringLayout.NORTH, this);

        // scout label
        layout.putConstraint(SpringLayout.WEST, scoutLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scoutLabel, 35, SpringLayout.NORTH, this);

        // soldier label
        layout.putConstraint(SpringLayout.WEST, soldierLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, soldierLabel, 45, SpringLayout.NORTH, this);

        // Bala label
        layout.putConstraint(SpringLayout.WEST, balaLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, balaLabel, 55, SpringLayout.NORTH, this);

        // food label
        layout.putConstraint(SpringLayout.WEST, foodLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, foodLabel, 65, SpringLayout.NORTH, this);

        // pheromone label
        layout.putConstraint(SpringLayout.WEST, pheromoneLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pheromoneLabel, 75, SpringLayout.NORTH, this);

        // queen icon
        layout.putConstraint(SpringLayout.WEST, queenIcon, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, queenIcon, 0, SpringLayout.NORTH, this);

        // Bala icon
        layout.putConstraint(SpringLayout.WEST, balaIcon, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, balaIcon, 15, SpringLayout.NORTH, this);

        // soldier icon
        layout.putConstraint(SpringLayout.WEST, soldierIcon, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, soldierIcon, 35, SpringLayout.NORTH, this);

        // scout icon
        layout.putConstraint(SpringLayout.WEST, scoutIcon, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, scoutIcon, 55, SpringLayout.NORTH, this);

        // forager icon
        layout.putConstraint(SpringLayout.WEST, foragerIcon, 60, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, foragerIcon, 75, SpringLayout.NORTH, this);

        // add to view
        this.add(IDLabel);
        this.add(foragerLabel);
        this.add(scoutLabel);
        this.add(soldierLabel);
        this.add(balaLabel);
        this.add(foodLabel);
        this.add(pheromoneLabel);
        this.add(queenIcon);
        this.add(scoutIcon);
        this.add(foragerIcon);
        this.add(soldierIcon);
        this.add(balaIcon);
    }


    /**
     *  make this view visible
     *
     *  use this method to reveal the contents of the node represented by this view
     */
    public void showNode() {
        setVisible(true);
    }


    /**
     *  hide this view
     *
     *  this method is used to hide the contents of the node represented by this view
     *  should only be used during initialization of the simulation
     */
    public void hideNode() {
        setVisible(false);
    }


    /**
     *  set the value for the ID label
     *
     *  @param  id      String that uniquely identifies the node represented by this view
     */
    public void setID(String id) {
        IDLabel.setText(id);
    }


    /**
     *  display whether or not the queen ant is present in the node represented by this view
     *
     *  if queen is present this view's background color is dark gray
     *
     *  if the queen is not present this view's background color is the color of a normal node
     *
     *  @param  queenPresent    true if queen is in the node represented by this view
     *                          false otherwise
     */
    public void setQueen(boolean queenPresent) {
        if (queenPresent) {
            setBackground(QUEEN_NODE_COLOR);
        } else {
            setBackground(OPEN_NODE_COLOR);
        }
    }


    /**
     *  display the number of foragers in the node represented by this view
     *
     *  @param  numForagers     the number of foragers
     */
    public void setForagerCount(int numForagers) {
        foragerLabel.setText("F: " + numForagers);
    }


    /**
     *  display the number of scouts in the node represented by this view
     *
     *  @param  numScouts       the number of scouts
     */
    public void setScoutCount(int numScouts) {
        scoutLabel.setText("Sc: " + numScouts);
    }


    /**
     *  display the number of soldiers in the node represented by this view
     *
     *  @param  numSoldiers     the number of soldiers
     */
    public void setSoldierCount(int numSoldiers) {
        soldierLabel.setText("S: " + numSoldiers);
    }


    /**
     *  display the number of Bala ants in the node represented by this view
     *
     *  @param  numBalas        the number of Balas
     */
    public void setBalaCount(int numBalas) {
        balaLabel.setText("B: " + numBalas);
    }


    /**
     *  display the amount of food in the node represented by this view
     *
     *  @param  food        the amount of food
     */
    public void setFoodAmount(int food) {
        foodLabel.setText("Food: " + food);
    }


    /**
     *  display the level of pheromone in the node represented by this view
     *  the amount of pheromone is displayed in 2 ways:
     *      1)  numerically
     *      2)  by the background color:
     *          a)  0 units:            no color
     *          b)  1 - 199 units:      violet
     *          c)  200 - 399 units:    blue
     *          d)  400 - 599 units:    green
     *          e)  600 - 799 units:    yellow
     *          f)  800 - 999 units:    orange
     *          g)  >= 1000 units:      red
     *
     *  @param  pheromone       the amount of pheromone
     */
    public void setPheromoneLevel(int pheromone) {

        pheromoneLabel.setText("Ph: " + pheromone);

        if (pheromone >= 1000)
            setBackground(PHEROMONE_1000);
        else if (pheromone >= 800)
            setBackground(PHEROMONE_800);
        else if (pheromone >= 600)
            setBackground(PHEROMONE_600);
        else if (pheromone >= 400)
            setBackground(PHEROMONE_400);
        else if (pheromone >= 200)
            setBackground(PHEROMONE_200);
        else if (pheromone > 0)
            setBackground(PHEROMONE_0);
        else if (getBackground().equals(QUEEN_NODE_COLOR))
            setBackground(QUEEN_NODE_COLOR);
        else
            setBackground(OPEN_NODE_COLOR);
    }


    /**
     *  display the queen icon to indicate presence of queen ant
     */
    public void showQueenIcon() {
        queenIcon.setVisible(true);
    }


    /**
     *  hide the queen icon to indicate no queen ant present
     */
    public void hideQueenIcon() {
        queenIcon.setVisible(false);
    }


    /**
     *  display the Bala icon to indicate presence of 1 or more Bala ants
     */
    public void showBalaIcon() {
        balaIcon.setVisible(true);
    }


    /**
     *  hide the Bala icon to indicate no Bala ants present
     */
    public void hideBalaIcon() {
        balaIcon.setVisible(false);
    }


    /**
     *  display the soldier icon to indicate presence of 1 or more soldier ants
     */
    public void showSoldierIcon() {
        soldierIcon.setVisible(true);
    }


    /**
     *  hide the soldier icon to indicate no soldier ants present
     */
    public void hideSoldierIcon() {
        soldierIcon.setVisible(false);
    }

    /**
     *  display the scout icon to indicate presence of 1 or more scout ants
     */
    public void showScoutIcon() {
        scoutIcon.setVisible(true);
    }


    /**
     *  hide the scout icon to indicate no scout ants present
     */
    public void hideScoutIcon() {
        scoutIcon.setVisible(false);
    }

    /**
     *  display the forager icon to indicate presence of 1 or more forager ants
     */
    public void showForagerIcon() {
        foragerIcon.setVisible(true);
    }


    /**
     *  hide the forager icon to indicate no forager ants present
     */
    public void hideForagerIcon() {
        foragerIcon.setVisible(false);
    }
}
