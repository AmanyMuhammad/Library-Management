
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class BorrowFrame extends JFrame implements ActionListener {
    JPanel headerPanel;
    JPanel addPagePanel;
    JLabel addLabel;
    JTextField bookISBNField;
    JTextField borrowerNameField;
    JTextField borrowDateField;
    JTextField expectedReturnDateField;
    JButton borrowButton;
    JButton editButton;
    DefaultTableModel tableModel;
    int id;
    int row;


    BorrowFrame(DefaultTableModel tableModel) throws IOException {
        Color mainTextColor=new Color(55, 55, 51);
        Color backgroundColor =new Color(243, 241, 231);
        Color sidebarBgColor =new Color(126, 93, 46);


        this.tableModel=tableModel;
        this.setSize(new Dimension(650, 600));
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        addLabel = new JLabel("Borrow Book");
        addLabel.setFont(new Font("Segoe UI", Font.ITALIC, 40));
        addLabel.setForeground(mainTextColor);
        headerPanel.add(addLabel);

        addPagePanel = new JPanel(new GridBagLayout());
        addPagePanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(8, 15, 8, 15);


        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        //ISBN Label
        gbc.gridx = 0;
        JLabel ISBNLabel = new JLabel("Book ISBN");
        ISBNLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        ISBNLabel.setForeground(sidebarBgColor);
        addPagePanel.add(ISBNLabel, gbc);

        // Borrower's Name Label
        gbc.gridx = 1;
        JLabel titleLabel = new JLabel("Borrower's Name");
        titleLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        titleLabel.setForeground(sidebarBgColor);
        addPagePanel.add(titleLabel, gbc);


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        //  ISBNField
        gbc.gridx = 0;
        bookISBNField = new JTextField("ISBN");
        bookISBNField.setPreferredSize(new Dimension(260, 42));
        bookISBNField.setFont(new Font("Segoe_UI", Font.PLAIN, 20));
        bookISBNField.setBackground(backgroundColor);
        bookISBNField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        bookISBNField.setEditable(true);
        addPagePanel.add(bookISBNField, gbc);

        //  Borrower's Name Field
        gbc.gridx = 1;
        borrowerNameField = new JTextField("Borrower's Name");
        borrowerNameField.setPreferredSize(new Dimension(260, 42));
        borrowerNameField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        borrowerNameField.setBackground(backgroundColor);
        borrowerNameField.setForeground(Color.LIGHT_GRAY);
        borrowerNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(borrowerNameField, gbc);


        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;

        //Borrow Date Label
        gbc.gridx = 0;
        JLabel authorLabel = new JLabel("Borrow Date");
        authorLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        authorLabel.setForeground(sidebarBgColor);
        addPagePanel.add(authorLabel, gbc);

        //Expected Return Date Label
        gbc.gridx = 1;
        JLabel copiesNumLabel = new JLabel("Expected Return Date");
        copiesNumLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        copiesNumLabel.setForeground(sidebarBgColor);
        addPagePanel.add(copiesNumLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        //  Borrow Date Field
        gbc.gridx = 0;
        borrowDateField = new JTextField("Borrow Date");
        borrowDateField.setPreferredSize(new Dimension(260, 42));
        borrowDateField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        borrowDateField.setBackground(backgroundColor);
        borrowDateField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(borrowDateField, gbc);

        //  Expected Return Date Field
        gbc.gridx = 1;
        expectedReturnDateField = new JTextField("Expectd Return Date");
        expectedReturnDateField.setPreferredSize(new Dimension(260, 42));
        expectedReturnDateField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        expectedReturnDateField.setBackground(backgroundColor);
        expectedReturnDateField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(expectedReturnDateField, gbc);


        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(35, 15, 10, 15);

        borrowButton = new JButton("Borrow");
        borrowButton.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        borrowButton.setPreferredSize(new Dimension(180, 45));
        borrowButton.setBackground(sidebarBgColor);
        borrowButton.setForeground(backgroundColor);
        borrowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        borrowButton.addActionListener(this);
        borrowButton.setFocusable(false);
        addPagePanel.add(borrowButton, gbc);


        activeField(bookISBNField, bookISBNField.getText());
        activeField(borrowerNameField, borrowerNameField.getText());
        activeField(borrowDateField, borrowDateField.getText());
        activeField(expectedReturnDateField, expectedReturnDateField.getText());

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(addPagePanel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == borrowButton){
            // adsfojjiodfsfgd
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
