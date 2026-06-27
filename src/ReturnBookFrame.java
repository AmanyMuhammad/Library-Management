
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class ReturnBookFrame extends JFrame implements ActionListener {

    JPanel headerPanel;
    JPanel returnPagePanel;
    JLabel returnLabel;
    JTextField uniIDField;
    JTextField bookISBNField;
    JButton returnButton;
    DefaultTableModel tableModel;


    ReturnBookFrame(DefaultTableModel tableModel) throws IOException {
        Color mainTextColor=new Color(55, 55, 51);
        Color backgroundColor =new Color(243, 241, 231);
        Color sidebarBgColor =new Color(126, 93, 46);


        this.tableModel=tableModel;
        this.setSize(new Dimension(650, 600));
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        returnLabel = new JLabel("Return Book");
        returnLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
        returnLabel.setForeground(mainTextColor);
        headerPanel.add(returnLabel);

        returnPagePanel = new JPanel(new GridBagLayout());
        returnPagePanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(8, 15, 8, 15);


        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        //ID Label
        gbc.gridx = 0;
        JLabel IDLabel = new JLabel("Uni ID");
        IDLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        IDLabel.setForeground(sidebarBgColor);
        returnPagePanel.add(IDLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        //  IDField
        gbc.gridx = 0;
        uniIDField = new JTextField("Uni ID");
        uniIDField.setPreferredSize(new Dimension(260, 42));
        uniIDField.setFont(new Font("Segoe_UI", Font.PLAIN, 20));
        uniIDField.setBackground(backgroundColor);
        uniIDField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        uniIDField.setEditable(true);
        returnPagePanel.add(uniIDField, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;

        //ISBN Label
        gbc.gridx = 0;
        JLabel ISBNLabel = new JLabel("Book ISBN");
        ISBNLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        ISBNLabel.setForeground(sidebarBgColor);
        returnPagePanel.add(ISBNLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        //  ISBN Field
        gbc.gridx = 0;
        bookISBNField = new JTextField("Book ISBN");
        bookISBNField.setPreferredSize(new Dimension(260, 42));
        bookISBNField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        bookISBNField.setBackground(backgroundColor);
        bookISBNField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        returnPagePanel.add(bookISBNField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(35, 15, 10, 15);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        returnButton.setPreferredSize(new Dimension(180, 45));
        returnButton.setBackground(sidebarBgColor);
        returnButton.setForeground(backgroundColor);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.addActionListener(this);
        returnButton.setFocusable(false);
        returnPagePanel.add(returnButton, gbc);

        activeField(uniIDField, uniIDField.getText());
        activeField(bookISBNField, bookISBNField.getText());


        this.add(headerPanel,BorderLayout.NORTH);
        this.add(returnPagePanel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == returnButton){

            String Return=Main.libraryManagement.returnBook(Integer.parseInt(uniIDField.getText()),Long.parseLong(bookISBNField.getText()));
            Main.libraryManagement.waitingListFrame.refreshTableData();
            Main.libraryManagement.borrowersFrame.refreshTableData();


            ArrayList<BorrowerNode> records = Main.libraryManagement.borrowerSearch(Integer.parseInt(uniIDField.getText()));
            long returnedISBN = Long.parseLong(bookISBNField.getText());

            for (BorrowerNode record : records) {
                if (record.getBookISBN() == returnedISBN && record.getRecordStatus().equals("RETURNED")) {
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (tableModel.getValueAt(i, 0).equals(record.getStudentID())
                                && tableModel.getValueAt(i, 2).equals(record.getBookISBN())
                                && tableModel.getValueAt(i, 3).toString().equals(record.getBorrowDate().toString())) {
                            tableModel.setValueAt("RETURNED", i, 6);
                            break;
                        }
                    }
                    break;
                }
            }


            if(Return.equals("The book returned successfully and processed waiting list!")){
                JOptionPane.showMessageDialog(this,"The book returned successfully and processed waiting list!","Success",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }

            if(Return.equals("Error: No active borrow record found for this student and book!")){
                JOptionPane.showMessageDialog(this,"Error: No active borrow record found for this student and book!!","Warning",JOptionPane.WARNING_MESSAGE);
                this.dispose();
            }
        }
    }

    void activeField(JTextField field,String text) {

        field.setText(text);
        field.setForeground(Color.LIGHT_GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.LIGHT_GRAY);
                }
            }

        });

    }
}
