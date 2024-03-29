import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
// -------------------------------------------------------------------------
/**
 * Represents the north menu-bar that contains various controls for the game.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessMenuBar
    extends JMenuBar{
    // ----------------------------------------------------------
    /**
     * Create a new ChessMenuBar object.
     */
    //Code Smell: LONG METHOD. El método ChessMenuBar() era algo extenso. 
    //Técnica: EXTRACT METHOD. Se le extrajeron 2 métodos.
    public ChessMenuBar() {
        String[] menuCategories = { "File", "Options", "Help" };
        String[] menuItemLists = { "New game/restart,Exit", "Toggle graveyard,Toggle game log", "About" };
        for (int i = 0; i < menuCategories.length; i++) {
            JMenu currMenu = createMenu(menuCategories[i], menuItemLists[i]);
            this.add(currMenu);
        }
    }

    private JMenu createMenu(String category, String itemList) {
        JMenu menu = new JMenu(category);
        String[] items = itemList.split(",");
        for (String item : items) {
            JMenuItem menuItem = createMenuItem(item);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenuItem createMenuItem(String item) {
        JMenuItem menuItem = new JMenuItem(item);
        menuItem.addActionListener(new MenuListener());
        return menuItem;
    }

    /**
     * Listener for the north menu bar.
     *
     * @author Ben Katz (bakatz)
     * @author Myles David II (davidmm2)
     * @author Danielle Bushrow (dbushrow)
     * @version 2010.11.17
     */
    /* private class MenuListener
        implements ActionListener
    {
        
        @Override
        public void actionPerformed( ActionEvent event ){
            String buttonName = ( (JMenuItem)event.getSource() ).getText();
            if ( buttonName.equals( "About" ) ){
                aboutHandler();
            }
            else if ( buttonName.equals( "New game/restart" ) ){
                restartHandler();
            }
            else if ( buttonName.equals( "Toggle game log" ) ){
                toggleGameLogHandler();
            }
            else if ( buttonName.equals( "Exit" ) ){
                exitHandler();
            }
            else
            {
                toggleGraveyardHandler();
            }
        }
    } */
    // ----------------------------------------------------------
    /**
     * Takes an appropriate action if the about button is clicked.
     */
    public void aboutHandler(){
        JOptionPane.showMessageDialog(
            this.getParent(),
            "YetAnotherChessGame v1.0 by:\nBen Katz\nMyles David\n"
                + "Danielle Bushrow\n\nFinal Project for CS2114 @ VT" );
    }
    /**
     * Takes an appropriate action if the restart button is clicked.
     */
    public void restartHandler(){
        ( (ChessPanel)this.getParent() ).getGameEngine().reset();
    }
    /**
     * Takes an appropriate action if the exit button is clicked.
     * Uses Tony Allevato's code for exiting a GUI app without System.exit()
     * calls.
     */
    public void exitHandler(){
        JOptionPane.showMessageDialog( this.getParent(), "Thanks for leaving"
            + ", quitter! >:(" );
        Component possibleFrame = this;
        while ( possibleFrame != null && !( possibleFrame instanceof JFrame ) ){
            possibleFrame = possibleFrame.getParent();
        }
        JFrame frame = (JFrame)possibleFrame;
        frame.setVisible( false );
        frame.dispose();
    }
    /**
     * Takes an appropriate action if the toggle graveyard button is clicked.
     */
    public void toggleGraveyardHandler(){
        ( (ChessPanel)this.getParent() ).getGraveyard( 1 ).setVisible(
            !( (ChessPanel)this.getParent() ).getGraveyard( 1 ).isVisible() );
        ( (ChessPanel)this.getParent() ).getGraveyard( 2 ).setVisible(
            !( (ChessPanel)this.getParent() ).getGraveyard( 2 ).isVisible() );
    }
    /**
     * Takes an appropriate action if the toggle game log button is clicked.
     */
    public void toggleGameLogHandler(){
        ( (ChessPanel)this.getParent() ).getGameLog().setVisible(
            !( (ChessPanel)this.getParent() ).getGameLog().isVisible() );
        ( (ChessPanel)this.getParent() ).revalidate();
    }
}
