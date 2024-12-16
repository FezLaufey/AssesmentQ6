import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Initialize the application
        System.out.println("Starting Customer Management Application...");

        // Launch the Swing GUI
        SwingUtilities.invokeLater(() -> {
            CustomersList gui = new CustomersList(0); // Replace with your JFrame class
            gui.setVisible(true); // Make the GUI visible
        });
    }
}
