package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class RentalGUI extends JFrame {

    private RentalSystem system;
    private ArrayList<CartItem> cartItems;
    private JButton[] addButtons;
    private static final DecimalFormat df = new DecimalFormat("Php 0.00");

    // defining constants for vertical struts & colors
    public static final int MAX_VERTICAL_STRUT = 10, MIN_VERTICAL_STRUT = 5; 
    private static final Color BG_COLOR = new Color(247, 239, 229);
    private static final Color HEADER_COLOR = new Color(58, 79, 122);
    private static final Color FOOTER_COLOR = new Color(227, 100, 20);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color PRIMARY_BTN = new Color(58, 134, 255);
    private static final Color ADD_BTN = new Color(76, 175, 80);


    public RentalGUI() {
        system = new RentalSystem();
        cartItems = new ArrayList<>();

        setTitle("Bike My Type");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_COLOR);

        add(createHeader(), BorderLayout.NORTH);
        add(createBikeGrid(), BorderLayout.CENTER);
        add(createCartButton(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ---------------- HEADER ----------------
    private JPanel createHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("BIKE MY TYPE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Tired of those boring, plain rides? Browse through our available rides with their own personalities! Who knows, you might just find Your Type!", SwingConstants.CENTER);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
        panel.add(title);
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));

        return panel;
    }

    // ---------------- BIKE GRID ----------------
    private JPanel createBikeGrid() {
        JPanel outer = new JPanel(new GridBagLayout());
        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));

        outer.setBackground(BG_COLOR);
        grid.setBackground(BG_COLOR);

        BikeClasses[] bikes = system.getBikes();
        addButtons = new JButton[bikes.length];

        for (int i = 0; i < bikes.length; i++) {
            int index = i;
            BikeClasses bike = bikes[index];

            JPanel bikePanel = new JPanel();
            bikePanel.setLayout(new BoxLayout(bikePanel, BoxLayout.Y_AXIS));
            bikePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // ---- NAME & RATE ----
            JLabel nameLabel = new JLabel(bike.getName(), SwingConstants.CENTER);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel rateLabel = new JLabel(
                df.format(bike.getHourlyRate()) + " / hour",
                SwingConstants.CENTER
            );
            rateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // ---- HOURS CONTROLS ----
            JPanel hourPanel = new JPanel();
            JButton minusBtn = new JButton("-");
            JLabel hourLabel = new JLabel("1");
            JButton plusBtn = new JButton("+");

            hourLabel.setPreferredSize(new Dimension(20, 20));
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel subtotalLabel = new JLabel(
                "Subtotal: " + df.format(bike.getHourlyRate()),
                SwingConstants.CENTER
            );
            subtotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            minusBtn.addActionListener(e -> {
                int hours = Integer.parseInt(hourLabel.getText());
                if (hours > 1) {
                    hours--;
                    hourLabel.setText(String.valueOf(hours));
                    subtotalLabel.setText(
                        "Subtotal: " + df.format(hours * bike.getHourlyRate())
                    );
                }
            });

            plusBtn.addActionListener(e -> {
                int hours = Integer.parseInt(hourLabel.getText()) + 1;
                hourLabel.setText(String.valueOf(hours));
                subtotalLabel.setText(
                    "Subtotal: " + df.format(hours * bike.getHourlyRate())
                );
            });

            minusBtn.setBackground(Color.LIGHT_GRAY);
            plusBtn.setBackground(Color.LIGHT_GRAY);
            minusBtn.setFocusPainted(false);
            plusBtn.setFocusPainted(false);


            hourPanel.add(minusBtn);
            hourPanel.add(hourLabel);
            hourPanel.add(plusBtn);

            // ---- ADD TO CART ----
            JButton addBtn = new JButton("Add to Cart");
            addButtons[index] = addBtn;
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            addBtn.setBackground(ADD_BTN);
            addBtn.setForeground(Color.WHITE);
            addBtn.setFocusPainted(false);

            addBtn.addActionListener(e -> {
                if (system.isRented(index)) {
                    JOptionPane.showMessageDialog(
                        this,
                        "This bike is already rented.",
                        "Unavailable",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                int hours = Integer.parseInt(hourLabel.getText());

                cartItems.add(new CartItem(bike, hours));
                system.rentBike(index);
                addBtn.setEnabled(false);

                JOptionPane.showMessageDialog(
                    this,
                    bike.getName() + " added to cart!\n" +
                    "Total: " + df.format(hours * bike.getHourlyRate())
                );
            });

            // ---- VIEW DETAILS ----
            JButton viewBtn = new JButton("View Details");
            viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewBtn.addActionListener(e -> showBikeDetails(bike));

            // ---- ADD COMPONENTS TO PANEL ----
            bikePanel.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
            bikePanel.add(nameLabel);
            bikePanel.add(rateLabel);
            bikePanel.add(Box.createVerticalStrut(MIN_VERTICAL_STRUT));
            bikePanel.add(hourPanel);
            bikePanel.add(subtotalLabel);
            bikePanel.add(Box.createVerticalStrut(MIN_VERTICAL_STRUT));
            bikePanel.add(addBtn);
            bikePanel.add(Box.createVerticalStrut(MIN_VERTICAL_STRUT));
            bikePanel.add(viewBtn);
            bikePanel.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));

            bikePanel.setBackground(CARD_COLOR);
            bikePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            grid.add(bikePanel);
        }

        outer.add(grid);
        return outer;
    }

    // ---------------- SHOW BIKE DETAILS ----------------
    private void showBikeDetails(BikeClasses bike) {
        JDialog detailsDialog = new JDialog(this, bike.getName(), true);
        detailsDialog.setSize(400, 300);
        detailsDialog.setLayout(new BoxLayout(detailsDialog.getContentPane(), BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + bike.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><p style='width:300px'>" + bike.getDescription() + "</p></html>");
        descLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel rateLabel = new JLabel("Rate: " + df.format(bike.getHourlyRate()) + " / hour");
        rateLabel.setAlignmentX(CENTER_ALIGNMENT);

        detailsDialog.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
        detailsDialog.add(nameLabel);
        detailsDialog.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
        detailsDialog.add(descLabel);
        detailsDialog.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
        detailsDialog.add(rateLabel);
        detailsDialog.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));

        detailsDialog.setLocationRelativeTo(this);
        detailsDialog.setVisible(true);
    }

    // ---------------- CART BUTTON ----------------
    private JPanel createCartButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton cartBtn = new JButton("VIEW CART");
        cartBtn.setPreferredSize(new Dimension(100, 40));
        cartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        cartBtn.addActionListener(e -> {
            CartDialog dialog = new CartDialog(this, cartItems, system);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });
        cartBtn.setBackground(PRIMARY_BTN);
        cartBtn.setForeground(Color.WHITE);

        panel.add(cartBtn);
        panel.add(Box.createVerticalStrut(MAX_VERTICAL_STRUT));
        return panel;
    }

    // ---------------- RE-ENABLE BUTTON ----------------
    public void enableAddButton(int index) {
        addButtons[index].setEnabled(true);
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RentalGUI::new);
    }
}