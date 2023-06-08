import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Listener for the north menu bar.
 */
public class MenuListener implements ActionListener {
    /**
     * Takes an appropriate action based on which menu button is clicked.
     *
     * @param event the mouse event from the source
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String buttonName = ((JMenuItem) event.getSource()).getText();
        ChessMenuBar menuBar = (ChessMenuBar) ((JMenuItem) event.getSource()).getParent().getParent();
        
        if (buttonName.equals("About")) {
            menuBar.aboutHandler();
        } else if (buttonName.equals("New game/restart")) {
            menuBar.restartHandler();
        } else if (buttonName.equals("Toggle game log")) {
            menuBar.toggleGameLogHandler();
        } else if (buttonName.equals("Exit")) {
            menuBar.exitHandler();
        } else {
            menuBar.toggleGraveyardHandler();
        }
    }
}