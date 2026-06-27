import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.time.LocalDate;

public class EditBorrowerInfo extends JFrame implements ActionListener {

    JPanel headerPanel;
    JPanel addPagePanel;
    JLabel addLabel;
    JTextField UniIDField;
    JTextField nameField;
    JTextField borrowDateField;
    JButton editButton;
    DefaultTableModel tableModel;
    int row;
    BorrowerNode editedBorrower;


    EditBorrowerInfo(DefaultTableModel tableModel,int row,BorrowerNode editedBorrower) throws IOException {
        this.editedBorrower = editedBorrower;
        this.row=row;
        Color mainTextColor=new Color(55, 55, 51);
        Color backgroundColor =new Color(243, 241, 231);
        Color sidebarBgColor =new Color(126, 93, 46);

        this.tableModel=tableModel;
        this.setSize(new Dimension(650, 600));
        this.setLayout(new BorderLayout());

        if (editedBorrower == null) {
            JOptionPane.showMessageDialog(this,
                    "Borrower not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            this.dispose();
            return;
        }

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        addLabel = new JLabel("Edit Borrower Info");
        addLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
        addLabel.setForeground(mainTextColor);
        headerPanel.add(addLabel);

        addPagePanel = new JPanel(new GridBagLayout());
        addPagePanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(8, 15, 8, 15);


        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        //Uni ID Label
        gbc.gridx = 0;
        JLabel UniIDLabel = new JLabel("Uni ID");
        UniIDLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        UniIDLabel.setForeground(sidebarBgColor);
        addPagePanel.add(UniIDLabel, gbc);

        // name Label
        gbc.gridx = 1;
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        nameLabel.setForeground(sidebarBgColor);
        addPagePanel.add(nameLabel, gbc);


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        //  Uni ID Field
        gbc.gridx = 0;
        UniIDField = new JTextField(String.valueOf(editedBorrower.getStudentID()));
        UniIDField.setPreferredSize(new Dimension(260, 42));
        UniIDField.setFont(new Font("Segoe_UI", Font.PLAIN, 20));
        UniIDField.setBackground(backgroundColor);
        UniIDField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        UniIDField.setEnabled(false);
        addPagePanel.add(UniIDField, gbc);

        //  name Field
        gbc.gridx = 1;
        nameField = new JTextField(editedBorrower.getName());
        nameField.setPreferredSize(new Dimension(260, 42));
        nameField.setForeground(Color.BLACK);
        nameField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        nameField.setBackground(backgroundColor);

        nameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(nameField, gbc);


        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;

        //Borrow Date Label
        gbc.gridx = 0;
        JLabel borrowDateLabel = new JLabel("Borrow Date");
        borrowDateLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        borrowDateLabel.setForeground(sidebarBgColor);
        addPagePanel.add(borrowDateLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        //  Borrow Date field
        gbc.gridx = 0;
        borrowDateField = new JTextField(String.valueOf(editedBorrower.getBorrowDate()));
        borrowDateField.setPreferredSize(new Dimension(260, 42));
        borrowDateField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        borrowDateField.setBackground(backgroundColor);
        borrowDateField.setForeground(Color.BLACK);
        borrowDateField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(borrowDateField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(35, 15, 10, 15);

        editButton = new JButton("EDIT");
        editButton.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        editButton.setPreferredSize(new Dimension(180, 45));
        editButton.setBackground(sidebarBgColor);
        editButton.setForeground(backgroundColor);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(this);
        editButton.setFocusable(false);
        addPagePanel.add(editButton, gbc);

        activeField(UniIDField,UniIDField.getText());
        activeField(nameField, nameField.getText());
        activeField(borrowDateField, borrowDateField.getText());

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(addPagePanel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            String Edit = Main.libraryManagement.updateBorrowerInfo(editedBorrower.getStudentID(), nameField.getText(), LocalDate.parse(borrowDateField.getText().trim()));


            if (Edit.equals("The borrower info updated successfully!")) {

                if (Main.libraryManagement.borrowersFrame != null) {
                    Main.libraryManagement.borrowersFrame.refreshTableData();
                    if (this.row >= 0 && this.row < Main.libraryManagement.borrowersFrame.borrowersTable.getRowCount()) {
                        Main.libraryManagement.borrowersFrame.borrowersTable.setRowSelectionInterval(this.row, this.row);

                        Main.libraryManagement.borrowersFrame.showItemInfo(this.row);
                    }

                }

                JOptionPane.showMessageDialog(this,"The borrower info updated successfully!","Success",JOptionPane.INFORMATION_MESSAGE);

            }

            this.dispose();
        }
    }

    void activeField(JTextField field,String text) {

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

            }

        });

    }


}
