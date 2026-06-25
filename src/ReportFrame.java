import javax.swing.*;
import java.awt.*;

public class ReportFrame extends JDialog {

    public ReportFrame(JFrame parent) {
        super(parent, "Report", true);

        Color backgroundColor = new Color(243, 241, 231);
        Color mainTextColor   = new Color(55, 55, 51);
        Color sidebarBgColor  = new Color(126, 93, 46);

        this.setSize(new Dimension(500, 400));
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel titleLabel = new JLabel("Library Report");
        titleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 35));
        titleLabel.setForeground(mainTextColor);
        headerPanel.add(titleLabel);

        // Content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(backgroundColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Fetch data
        int availableBooks = Main.books.getAvailableBooksCount();
        BookNode mostReadBook = Main.books.getMostReadBook();
        String mostReadAuthor = Main.books.getMostReadAuthor();

        String mostReadBookText = (mostReadBook != null)
                ? mostReadBook.getTitle() + " (" + mostReadBook.getBorrowCount() + " borrows)"
                : "No data";

        // Row 1 - Available Books
        gbc.gridy = 0; gbc.gridx = 0;
        JLabel availableLabel = new JLabel("Available Books:");
        availableLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        availableLabel.setForeground(sidebarBgColor);
        contentPanel.add(availableLabel, gbc);

        gbc.gridx = 1;
        JLabel availableValue = new JLabel(String.valueOf(availableBooks));
        availableValue.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        availableValue.setForeground(mainTextColor);
        contentPanel.add(availableValue, gbc);

        // Row 2 - Most Read Book
        gbc.gridy = 1; gbc.gridx = 0;
        JLabel mostReadBookLabel = new JLabel("Most Read Book:");
        mostReadBookLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mostReadBookLabel.setForeground(sidebarBgColor);
        contentPanel.add(mostReadBookLabel, gbc);

        gbc.gridx = 1;
        JLabel mostReadBookValue = new JLabel(mostReadBookText);
        mostReadBookValue.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        mostReadBookValue.setForeground(mainTextColor);
        contentPanel.add(mostReadBookValue, gbc);

        // Row 3 - Most Read Author
        gbc.gridy = 2; gbc.gridx = 0;
        JLabel mostReadAuthorLabel = new JLabel("Most Read Author:");
        mostReadAuthorLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mostReadAuthorLabel.setForeground(sidebarBgColor);
        contentPanel.add(mostReadAuthorLabel, gbc);

        gbc.gridx = 1;
        JLabel mostReadAuthorValue = new JLabel(mostReadAuthor);
        mostReadAuthorValue.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        mostReadAuthorValue.setForeground(mainTextColor);
        contentPanel.add(mostReadAuthorValue, gbc);

        // Close Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        closeButton.setBackground(sidebarBgColor);
        closeButton.setForeground(backgroundColor);
        closeButton.setFocusable(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().setBackground(backgroundColor);
        this.setVisible(true);
    }
}