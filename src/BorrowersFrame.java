import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class BorrowersFrame extends JPanel implements ActionListener, MouseListener, FocusListener {

    JPanel headerPanel;
    JPanel controlPanel;
    JPanel pagePanel;
    JPanel topPanel;
    JPanel tablePanel;
    JPanel showPanel;
    JLabel titleLabel;
    JTextField searchField;
    JButton searchButton;
    JButton borrowButton;
    JButton editButton;
    JButton deleteButton;
    JButton hideButton;
    JTable borrowersTable;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    Color backgroundColor;
    Color mainTextColor;
    Color sidebarBgColor;

    public BorrowersFrame() {
        backgroundColor = new Color(243, 241, 231);
        mainTextColor   = new Color(55, 55, 51);
        sidebarBgColor  = new Color(126, 93, 46);

        pagePanel = new JPanel(new BorderLayout());
        pagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pagePanel.setBackground(backgroundColor);
        pagePanel.setFocusable(true);
        pagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pagePanel.requestFocusInWindow();
            }
        });

        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(backgroundColor);
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);

        searchField  = new JTextField();
        searchButton = new JButton();
        borrowButton = new JButton();
        titleLabel   = new JLabel();

        // borrowButton
        {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/add.png"));
            Image img = originalIcon.getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon borrowIcon = new ImageIcon(newImg);

            borrowButton.setPreferredSize(new Dimension(175, 35));
            borrowButton.setText("Borrow");
            borrowButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            borrowButton.setBackground(sidebarBgColor);
            borrowButton.setForeground(backgroundColor);
            borrowButton.setIcon(borrowIcon);
            borrowButton.setHorizontalTextPosition(SwingConstants.RIGHT);
            borrowButton.setHorizontalAlignment(SwingConstants.LEFT);
            borrowButton.setIconTextGap(5);
            borrowButton.setFocusable(false);
            borrowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            borrowButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pagePanel.requestFocusInWindow();
                }
            });
        }

        // searchButton
        {
            searchButton.setText("Search");
            searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            searchButton.setBackground(sidebarBgColor);
            searchButton.setForeground(backgroundColor);
            searchButton.setPreferredSize(new Dimension(110, 35));
            searchButton.setFocusable(false);
            searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            searchButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pagePanel.requestFocusInWindow();
                }
            });
        }

        // searchField
        {
            searchField.setPreferredSize(new Dimension(350, 35));
            searchField.setText("Search by borrower name");
            searchField.addFocusListener(this);
            searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            searchField.setBackground(backgroundColor);
            searchField.setForeground(Color.GRAY);
            searchField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, mainTextColor));
            searchField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (searchField.getText().equals("Search by borrower name")) {
                        searchField.setText("");
                        searchField.setForeground(Color.BLACK);
                    }
                }
            });
        }

        // titleLabel
        {
            titleLabel.setText(" Borrowers");
            titleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 50));
            titleLabel.setForeground(mainTextColor);
            titleLabel.setPreferredSize(new Dimension(350, 80));
        }

        // headerPanel
        {
            headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            headerPanel.setPreferredSize(new Dimension(800, 100));
            headerPanel.setBackground(backgroundColor);
            headerPanel.add(titleLabel);
            topPanel.add(headerPanel, BorderLayout.NORTH);
        }

        // controlPanel (search bar on the left)
        {
            controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
            controlPanel.setBackground(backgroundColor);
            controlPanel.add(searchField);
            controlPanel.add(searchButton);
            topPanel.add(controlPanel, BorderLayout.WEST);
        }

        // action button on the right
        {
            JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
            addPanel.setBackground(backgroundColor);
            addPanel.add(borrowButton);
            topPanel.add(addPanel, BorderLayout.EAST);
            pagePanel.add(topPanel, BorderLayout.NORTH);
        }

        // tablePanel
        {
            String[] columns = {"ID", "Name", "Email", "Phone", "Borrowed Books"};
            tableModel    = new DefaultTableModel(columns, 0);
            borrowersTable = new JTable(tableModel);
            borrowersTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            borrowersTable.setBackground(backgroundColor);
            borrowersTable.setSelectionBackground(backgroundColor);
            borrowersTable.setRowHeight(30);
            borrowersTable.setShowVerticalLines(false);
            borrowersTable.setDefaultEditor(Object.class, null);
            borrowersTable.setFocusable(false);

            scrollPane = new JScrollPane(borrowersTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(backgroundColor);

            JTableHeader header = borrowersTable.getTableHeader();
            header.setReorderingAllowed(false);

            tablePanel.add(scrollPane, BorderLayout.CENTER);
            tablePanel.setFocusable(true);
            tablePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    tablePanel.requestFocusInWindow();
                }
            });

            pagePanel.revalidate();
            pagePanel.repaint();
            pagePanel.add(tablePanel, BorderLayout.CENTER);
            borrowersTable.addMouseListener(this);

            // Sample row
            tableModel.addRow(new Object[]{"B001", "Alice Johnson", "alice@example.com", "555-1234", 2});

            // Center renderer for all columns
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Header renderer
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean isFocus, int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(
                            table, value, isSelected, isFocus, row, column);
                    label.setText(value.toString());
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
                    label.setPreferredSize(new Dimension(label.getWidth(), 30));
                    label.setFont(new Font("Segoe UI", Font.BOLD, 15));
                    label.setBackground(backgroundColor);
                    label.setForeground(mainTextColor);
                    return label;
                }
            });

            for (int i = 0; i < borrowersTable.getColumnCount(); i++) {
                borrowersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        searchButton.addActionListener(this);
        borrowButton.addActionListener(this);

        this.add(pagePanel);
        this.setVisible(true);
    }

    public void showItemInfo(int row) {
        if (showPanel != null) {
            pagePanel.remove(showPanel);
        }

        ImageIcon originalHideIcon = new ImageIcon(getClass().getResource("/Images/hide.png"));
        Image imgHide = originalHideIcon.getImage();
        Image newHideImg = imgHide.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon hideIcon = new ImageIcon(newHideImg);

        hideButton = new JButton();
        hideButton.setIcon(hideIcon);
        hideButton.setHorizontalAlignment(SwingConstants.LEFT);
        hideButton.setBounds(330, 5, 40, 30);
        hideButton.setBackground(backgroundColor);
        hideButton.setFocusable(false);
        hideButton.setBorderPainted(false);
        hideButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hideButton.addActionListener(this);

        showPanel = new JPanel();
        showPanel.setLayout(null);
        showPanel.setBackground(backgroundColor);
        showPanel.setPreferredSize(new Dimension(380, 500));
        showPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, mainTextColor));

        // ID
        JLabel idLabel = new JLabel("ID : ");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        idLabel.setBounds(30, 40, 350, 40);

        JLabel idValueLabel = new JLabel(borrowersTable.getValueAt(row, 0).toString());
        idValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        idValueLabel.setBounds(80, 40, 350, 40);

        // Name
        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        nameLabel.setBounds(30, 100, 350, 40);

        JLabel nameValueLabel = new JLabel(borrowersTable.getValueAt(row, 1).toString());
        nameValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        nameValueLabel.setBounds(115, 100, 350, 40);

        // Email
        JLabel emailLabel = new JLabel("Email : ");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        emailLabel.setBounds(30, 160, 350, 40);

        JLabel emailValueLabel = new JLabel(borrowersTable.getValueAt(row, 2).toString());
        emailValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        emailValueLabel.setBounds(115, 160, 350, 40);

        // Phone
        JLabel phoneLabel = new JLabel("Phone : ");
        phoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        phoneLabel.setBounds(30, 220, 350, 40);

        JLabel phoneValueLabel = new JLabel(borrowersTable.getValueAt(row, 3).toString());
        phoneValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        phoneValueLabel.setBounds(120, 220, 350, 40);

        // Borrowed Books
        JLabel borrowedLabel = new JLabel("Borrowed : ");
        borrowedLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        borrowedLabel.setBounds(30, 280, 350, 40);

        JLabel borrowedValueLabel = new JLabel(borrowersTable.getValueAt(row, 4).toString());
        borrowedValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        borrowedValueLabel.setBounds(170, 280, 350, 40);

        // Delete button
        ImageIcon originalDeleteIcon = new ImageIcon(getClass().getResource("/Images/delete1.png"));
        Image imgDelete = originalDeleteIcon.getImage();
        Image newDeleteImg = imgDelete.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon deleteIcon = new ImageIcon(newDeleteImg);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        deleteButton.setIcon(deleteIcon);
        deleteButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
        deleteButton.setIconTextGap(5);
        deleteButton.setBounds(30, 500, 150, 50);
        deleteButton.setBackground(new Color(231, 0, 11));
        deleteButton.setForeground(backgroundColor);
        deleteButton.setFocusable(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Edit button
        ImageIcon originalEditIcon = new ImageIcon(getClass().getResource("/Images/edit1.png"));
        Image imgEdit = originalEditIcon.getImage();
        Image newEditImg = imgEdit.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon editIcon = new ImageIcon(newEditImg);

        editButton = new JButton("Edit");
        editButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        editButton.setIcon(editIcon);
        editButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        editButton.setHorizontalAlignment(SwingConstants.LEFT);
        editButton.setIconTextGap(5);
        editButton.setBounds(200, 500, 150, 50);
        editButton.setBackground(sidebarBgColor);
        editButton.setForeground(backgroundColor);
        editButton.setFocusable(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteButton.addActionListener(this);
        editButton.addActionListener(this);

        showPanel.add(idLabel);
        showPanel.add(idValueLabel);
        showPanel.add(nameLabel);
        showPanel.add(nameValueLabel);
        showPanel.add(emailLabel);
        showPanel.add(emailValueLabel);
        showPanel.add(phoneLabel);
        showPanel.add(phoneValueLabel);
        showPanel.add(borrowedLabel);
        showPanel.add(borrowedValueLabel);
        showPanel.add(deleteButton);
        showPanel.add(editButton);
        showPanel.add(hideButton);

        pagePanel.add(showPanel, BorderLayout.EAST);
        pagePanel.revalidate();
        pagePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == borrowButton) {
            try {
                BorrowFrame borrowFrame = new BorrowFrame(tableModel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == editButton) {
            // TODO: open EditFrame for borrowers
            // EditFrame editFrame = new EditFrame(tableModel);
        }

        if (e.getSource() == hideButton) {
            pagePanel.remove(showPanel);
            pagePanel.revalidate();
            pagePanel.repaint();
        }

        if (e.getSource() == deleteButton) {
            int row = borrowersTable.getSelectedRow();
            if (row != -1) {
                tableModel.removeRow(row);
                pagePanel.remove(showPanel);
                pagePanel.revalidate();
                pagePanel.repaint();
            }
        }

        if (e.getSource() == searchButton) {
            // TODO: implement search/filter logic
        }
    }

    // ── MouseListener ──────────────────────────────────────────────────────

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int row = borrowersTable.getSelectedRow();
            if (row != -1) {
                showItemInfo(row);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pagePanel.requestFocusInWindow();
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e)  {}
    @Override public void mouseExited(MouseEvent e)   {}

    // ── FocusListener ──────────────────────────────────────────────────────

    @Override
    public void focusGained(FocusEvent e) {
        if (searchField.getText().equals("Search by borrower name")) {
            searchField.setText("");
            searchField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
            searchField.setForeground(Color.GRAY);
            searchField.setText("Search by borrower name");
        }
    }
}