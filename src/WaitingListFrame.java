import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class WaitingListFrame extends JPanel implements ActionListener, MouseListener, FocusListener {

    JPanel headerPanel;
    JPanel controlPanel;
    JPanel pagePanel;
    JPanel topPanel;
    JPanel tablePanel;
    JLabel titleLabel;
    JTable waitingListTable;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    Color backgroundColor;
    Color mainTextColor;
    Color sidebarBgColor;

    public WaitingListFrame() {

        backgroundColor = new Color(243, 241, 231);
        mainTextColor = new Color(55, 55, 51);
        sidebarBgColor = new Color(126, 93, 46);

        pagePanel = new JPanel(new BorderLayout());
        pagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pagePanel.setBackground(backgroundColor);
        pagePanel.setFocusable(true);


        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(backgroundColor);
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);

        titleLabel = new JLabel();

        // titleLabel
        {
            titleLabel.setText("Waiting List");
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
            topPanel.add(controlPanel, BorderLayout.WEST);
        }

        // action button on the right
        {
            JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
            addPanel.setBackground(backgroundColor);
            topPanel.add(addPanel, BorderLayout.EAST);
            pagePanel.add(topPanel, BorderLayout.NORTH);
        }

        // tablePanel
        {
            String[] columns = {"Uni ID", "Name", "Book ISBN", "Request Date", "Student Status"};
            tableModel = new DefaultTableModel(columns, 0);
            waitingListTable = new JTable(tableModel);
            waitingListTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            waitingListTable.setBackground(backgroundColor);
            waitingListTable.setSelectionBackground(backgroundColor);
            waitingListTable.setRowHeight(30);
            waitingListTable.setShowVerticalLines(false);
            waitingListTable.setDefaultEditor(Object.class, null);
            waitingListTable.setFocusable(false);

            scrollPane = new JScrollPane(waitingListTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(backgroundColor);

            JTableHeader header = waitingListTable.getTableHeader();
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
            waitingListTable.addMouseListener(this);

            tableModel.setRowCount(0);

            if (Main.libraryManagement.waitingList != null) {
                Main.libraryManagement.waitingList.fillTableFromHeap(0, tableModel);
            }

            waitingListTable.revalidate();
            waitingListTable.repaint();


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

            for (int i = 0; i < waitingListTable.getColumnCount(); i++) {
                waitingListTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        }
        this.add(pagePanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // ── MouseListener ──────────────────────────────────────────────────────

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pagePanel.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // ── FocusListener ──────────────────────────────────────────────────────

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    public void refreshTableData() {
        if (tableModel != null) {
            tableModel.setRowCount(0);

            if (Main.libraryManagement != null && Main.libraryManagement.waitingList != null) {
                Main.libraryManagement.waitingList.fillTableFromHeap(0, tableModel);
            }
            waitingListTable.revalidate();
            waitingListTable.repaint();
        }
    }

}