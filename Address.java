import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address extends JFrame {
    private int customer_id;
    private JTextField shortNameField, fullNameField, address1Field, address2Field, address3Field, cityField, postalCodeField;
    private JButton saveButton, deleteButton;

    public Address(int customer_id) {
        this.customer_id = customer_id;
        setTitle("Customer Address");
        setSize(400, 400);
        setLayout(new GridLayout(9, 2));

        // Form fields
        add(new JLabel("Short Name:"));
        shortNameField = new JTextField();
        add(shortNameField);

        add(new JLabel("Full Name:"));
        fullNameField = new JTextField();
        add(fullNameField);

        add(new JLabel("Address 1:"));
        address1Field = new JTextField();
        add(address1Field);

        add(new JLabel("Address 2:"));
        address2Field = new JTextField();
        add(address2Field);

        add(new JLabel("Address 3:"));
        address3Field = new JTextField();
        add(address3Field);

        add(new JLabel("City:"));
        cityField = new JTextField();
        add(cityField);

        add(new JLabel("Postal Code:"));
        postalCodeField = new JTextField();
        add(postalCodeField);

        // Buttons
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveCustomer());
        add(saveButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteCustomer());
        add(deleteButton);

        loadCustomerDetails();
    }

    private void loadCustomerDetails() {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customers WHERE customer_id = ?")) {
            stmt.setInt(1, customer_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                shortNameField.setText(rs.getString("short_name"));
                fullNameField.setText(rs.getString("full_name"));
                address1Field.setText(rs.getString("Address1"));
                address2Field.setText(rs.getString("Address2"));
                address3Field.setText(rs.getString("Address3"));
                cityField.setText(rs.getString("City"));
                postalCodeField.setText(rs.getString("PostalCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomer() {
        // Validate fields
        if (shortNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Short Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Update customer details in the database
        String sql = "UPDATE Customers SET short_name = ?, full_name = ?, Address1 = ?, Address2 = ?, Address3 = ?, City = ?, PostalCode = ? WHERE customer_id = ?";
    
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, shortNameField.getText().trim());
            stmt.setString(2, fullNameField.getText().trim());
            stmt.setString(3, address1Field.getText().trim());
            stmt.setString(4, address2Field.getText().trim());
            stmt.setString(5, address3Field.getText().trim());
            stmt.setString(6, cityField.getText().trim());
            stmt.setString(7, postalCodeField.getText().trim());
            stmt.setInt(8, customer_id);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer details saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save customer details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void deleteCustomer() {
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    
        // Delete customer from the database
        String sql = "DELETE FROM Customers WHERE customer_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, customer_id);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the window after successful deletion
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete customer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

