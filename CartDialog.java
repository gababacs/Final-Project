package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CartDialog extends JDialog {
    private DefaultListModel<String> model;
    private JList<String> cartList;
    private ArrayList<CartItem> items;
    private JLabel totalLabel;
    private RentalSystem system;

    public CartDialog(JFrame parent, ArrayList<CartItem> items, RentalSystem system) {
        super(parent, "Your Cart", true);
        this.items = items;
        this.system = system;

        setSize(400, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        // -------------------
        //  Iitialize totalLabel first
        totalLabel = new JLabel("", SwingConstants.CENTER);

        // Create list model and JList
        model = new DefaultListModel<>();
        cartList = new JList<>(model);
        add(new JScrollPane(cartList), BorderLayout.CENTER);

        // -------------------
        // Bottom panel with Remove & Checkout
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton removeBtn = new JButton("Remove Selected");
        removeBtn.addActionListener(e -> removeSelected());
        bottomPanel.add(removeBtn, BorderLayout.NORTH);

        bottomPanel.add(totalLabel, BorderLayout.CENTER);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.addActionListener(e -> checkout());
        bottomPanel.add(checkoutBtn, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // -------------------
        // Populate the list and update total
        refreshList();
    }

    // Refresh JList and total
    private void refreshList() {
        model.clear();
        for (CartItem item : items) {
            model.addElement(item.getBike().getName() + " | " + item.getHours() + " hr(s) | Php" + item.getSubtotal());
        }
        updateTotal();
    }

    // Update total label
    private void updateTotal() {
        if (totalLabel == null) return; // safety check
        double total = 0;
        for (CartItem item : items) total += item.getSubtotal();
        totalLabel.setText("Tentative Total: Php" + total);
    }

    // Remove selected item
    private void removeSelected() {
        int index = cartList.getSelectedIndex();
        if (index != -1) {
            system.returnBike(index); // mark bike as available
            items.remove(index);
            refreshList();
        }
    }

    // Checkout workflow
    private void checkout() {
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] message = {
            "Full Name:", nameField,
            "Contact Number:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Checkout", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            if (name.isEmpty() || !contact.matches("\\d{10,}")) {
                JOptionPane.showMessageDialog(this, "Invalid name/contact", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double total = items.stream().mapToDouble(CartItem::getSubtotal).sum();
            new Receipt(name, contact, items.toArray(new CartItem[0]), total);

            // Clear cart after checkout
            items.clear();
            refreshList();
            dispose();
        }
    }
}