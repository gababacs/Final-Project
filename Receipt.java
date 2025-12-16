package FinalProject;

import javax.swing.*;

public class Receipt {
    public Receipt(String name, String contact, CartItem[] items, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Contact: ").append(contact).append("\n\n");
        for (CartItem item : items) {
            sb.append(item.getBike().getName())
              .append(" | ")
              .append(item.getHours()).append(" hr(s) | Php")
              .append(item.getSubtotal()).append("\n");
        }
        sb.append("\nTotal: Php").append(total);

        JOptionPane.showMessageDialog(null, sb.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}

