import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Date;

public class ChessGameLog extends JScrollPane {
    private JTextArea textArea;

    private static final int TEXT_AREA_ROWS = 5;
    private static final int TEXT_AREA_COLUMNS = 30;

    public ChessGameLog() {
        super(new JTextArea("", TEXT_AREA_ROWS, TEXT_AREA_COLUMNS),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea = ((JTextArea) this.getViewport().getView());
    }

    public void addToLog(String s) {
        StringBuilder builder = new StringBuilder(textArea.getText());

        if (builder.length() > 0) {
            builder.append("\n");
        }

        builder.append(new Date()).append(" - ").append(s);
        textArea.setText(builder.toString());
    }

    public void clearLog() {
        textArea.setText("");
    }

    public String getLastLog() {
        int indexOfLastNewLine = textArea.getText().lastIndexOf("\n");
        if (indexOfLastNewLine < 0) {
            return textArea.getText();
        }
        return textArea.getText().substring(indexOfLastNewLine + 1);
    }

    // Getter para acceder al contenido del JTextArea
    public String getText() {
        return textArea.getText();
    }

    // Setter para modificar el contenido del JTextArea
    public void setText(String text) {
        textArea.setText(text);
    }
}
