import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class UserInterfaceI extends JFrame {

    private JTextArea textArea;

    public UserInterfaceI() {
        // Set up the JFrame
        setTitle("User Interface I");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a text area
        textArea = new JTextArea();
        textArea.setEditable(false); // Non-editable by the user
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        // Menu items
        JMenuItem item1 = new JMenuItem("Print Date/Time");
        JMenuItem item2 = new JMenuItem("Save to File");
        JMenuItem item3 = new JMenuItem("Change Background");
        JMenuItem item4 = new JMenuItem("Exit");

        // Add menu items to the menu
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Set up actions for menu items
        item1.addActionListener(e -> printDateTime());
        item2.addActionListener(e -> saveToFile());
        item3.addActionListener(e -> changeBackgroundColor());
        item4.addActionListener(e -> System.exit(0));
    }

    // Method to print the current date and time
    private void printDateTime() {
        LocalDateTime now = LocalDateTime.now();
        textArea.setText(now.toString());
    }

    // Method to save the contents of the text area to a file
    private void saveToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "Text saved to log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to change background color to a random green hue
    private void changeBackgroundColor() {
        Random rand = new Random();
        float hue = rand.nextFloat() * 0.3f; // Green hue range
        Color randomGreen = Color.getHSBColor(hue, 1.0f, 1.0f);
        getContentPane().setBackground(randomGreen);
        JOptionPane.showMessageDialog(this, String.format("Background color set to hue %.2f", hue));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterfaceI ui = new UserInterfaceI();
            ui.setVisible(true);
        });
    }
}
