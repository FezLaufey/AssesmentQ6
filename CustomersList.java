import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.util.*;

public class CustomersList extends JFrame{
    private JTable customerTable;
    private JButton selectButton;

    public CustomersList(int i) {
        setTitle("Customer List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table to list customers
        customerTable = new JTable();
        loadCustomers();

        add(new JScrollPane(customerTable), BorderLayout.CENTER);

        // Select button
        selectButton = new JButton("Select Customer");
        selectButton.addActionListener(e -> openAddress());
        add(selectButton, BorderLayout.SOUTH);
    }

    public CustomersList() {
            //TODO Auto-generated constructor stub
        }
    
        private void loadCustomers() {
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Short Name", "Full Name"}, 0);
            try (Connection conn = Database.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT customer_id, short_name, full_name FROM Customers")) {
    
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getInt("customer_id"), rs.getString("short_name"), rs.getString("full_name")});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            customerTable.setModel(model);
        }
    
        private void openAddress() {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a customer!");
                return;
            }
    
            int customer_id = (int) customerTable.getValueAt(selectedRow, 0);
            new Address(customer_id).setVisible(true);
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new CustomersList().setVisible(true));
    }
}