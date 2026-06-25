
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ReturnBookFrame extends JFrame implements ActionListener {

    JPanel headerPanel;
    JPanel returnPagePanel;
    JLabel returnLabel;
    JTextField uniIDField;
    JTextField bookISBNField;
//    JTextField borrowerNameField;
//    JTextField borrowDateField;
//    JTextField expectedReturnDateField;
    JButton returnButton;
//    JButton editButton;
//    JCheckBox graduateCheckBox;
    DefaultTableModel tableModel;
//    int id;
//    int row;
//    boolean graduated=false;


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

        //ISBN Label
        gbc.gridx = 0;
        JLabel IDLabel = new JLabel("Uni ID");
        IDLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        IDLabel.setForeground(sidebarBgColor);
        returnPagePanel.add(IDLabel, gbc);

        // Borrower's Name Label
//        gbc.gridx = 1;
//        JLabel nameLabel = new JLabel("Borrower's Name");
//        nameLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
//        nameLabel.setForeground(sidebarBgColor);
//        returnPagePanel.add(nameLabel, gbc);


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        //  ISBNField
        gbc.gridx = 0;
        uniIDField = new JTextField("Uni ID");
        uniIDField.setPreferredSize(new Dimension(260, 42));
        uniIDField.setFont(new Font("Segoe_UI", Font.PLAIN, 20));
        uniIDField.setBackground(backgroundColor);
        uniIDField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        uniIDField.setEditable(true);
        returnPagePanel.add(uniIDField, gbc);

        //  Borrower's Name Field
//        gbc.gridx = 1;
//        borrowerNameField = new JTextField("Borrower's Name");
//        borrowerNameField.setPreferredSize(new Dimension(260, 42));
//        borrowerNameField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
//        borrowerNameField.setBackground(backgroundColor);
//        borrowerNameField.setForeground(Color.LIGHT_GRAY);
//        borrowerNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
//        returnPagePanel.add(borrowerNameField, gbc);


        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;

        //Borrow Date Label
        gbc.gridx = 0;
        JLabel ISBNLabel = new JLabel("Book ISBN");
        ISBNLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        ISBNLabel.setForeground(sidebarBgColor);
        returnPagePanel.add(ISBNLabel, gbc);
//
//        //Expected Return Date Label
//        gbc.gridx = 1;
//        JLabel borrowDateLabel = new JLabel("Borrow Date");
//        borrowDateLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
//        borrowDateLabel.setForeground(sidebarBgColor);
//        returnPagePanel.add(borrowDateLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        //  Borrow Date Field
        gbc.gridx = 0;
        bookISBNField = new JTextField("Book ISBN");
        bookISBNField.setPreferredSize(new Dimension(260, 42));
        bookISBNField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        bookISBNField.setBackground(backgroundColor);
        bookISBNField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        returnPagePanel.add(bookISBNField, gbc);

        //  Expected Return Date Field
//        gbc.gridx = 1;
//        borrowDateField = new JTextField("Borrow Date");
//        borrowDateField.setPreferredSize(new Dimension(260, 42));
//        borrowDateField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
//        borrowDateField.setBackground(backgroundColor);
//        borrowDateField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
//        returnPagePanel.add(borrowDateField, gbc);
//
//        gbc.gridx=0;
//        gbc.gridy=4;
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.insets = new Insets(-5, -60, 50, 180);
//
//        graduateCheckBox=new JCheckBox("Is Graduate?");
//        graduateCheckBox.setBackground(backgroundColor);
//        returnPagePanel.add(graduateCheckBox,gbc);

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
//        activeField(borrowerNameField, borrowerNameField.getText());
//        activeField(borrowDateField, borrowDateField.getText());


        this.add(headerPanel,BorderLayout.NORTH);
        this.add(returnPagePanel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == returnButton){

            String Return=Main.libraryManagement.returnBook(Integer.parseInt(uniIDField.getText()),Long.parseLong(bookISBNField.getText()));
            BorrowerNode root= Main.libraryManagement.borrowers.getRoot();
            Main.libraryManagement.waitingListFrame.refreshTableData();
            Main.libraryManagement.borrowersFrame.refreshTableData();

            BorrowerNode borrowerNode=Main.libraryManagement.borrowers.find(root,Integer.parseInt(uniIDField.getText()));
            for (int i = 0; i <  Main.libraryManagement.borrowersFrame.borrowersTable.getRowCount(); i++) {
                if(tableModel.getValueAt(i,0).equals(borrowerNode.getStudentID())){
                    tableModel.setValueAt(borrowerNode.getRecordStatus(),i,6);
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



//            if (graduateCheckBox.isSelected()){
//                graduated=true;
//            }
//
//
//            String Add=Main.libraryManagement.addBorrower(Integer.parseInt(uniIDField.getText()),borrowerNameField.getText(),Long.parseLong(bookISBNField.getText()), LocalDate.parse(borrowDateField.getText()),graduated);
//            if(Add.equals("The book is not exist!")){
//                JOptionPane.showMessageDialog(this,"The book is not exist!","Warning",JOptionPane.WARNING_MESSAGE);
//            }
//            else if (Add.equals("The book is not available , you are added to the waiting list")) {
//                JOptionPane.showMessageDialog(this,"The book is not available , you are added to the waiting list","Warning",JOptionPane.WARNING_MESSAGE);
//            }
//            else if(Add.equals("You are exceeded the maximum limit of borrows!")){
//                JOptionPane.showMessageDialog(this,"You are exceeded the maximum limit of borrows!","Warning",JOptionPane.WARNING_MESSAGE);
//            }
//            else if(Add.equals("The borrow was successful!")){
//                LocalDate returnDate=LocalDate.parse(borrowDateField.getText()).plusDays(20);
//                tableModel.addRow(new Object[]{
//                        Integer.parseInt(uniIDField.getText().trim()),
//                        borrowerNameField.getText().trim(),
//                        Long.parseLong(bookISBNField.getText().trim()),
//                        LocalDate.parse(borrowDateField.getText().trim()),
//                        returnDate,
//                        (graduated ? "Graduate" : "Student")
//                });
//
//                JOptionPane.showMessageDialog(this,"The borrow was successful!","Success",JOptionPane.INFORMATION_MESSAGE);
//            }
//
//            this.dispose();
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
