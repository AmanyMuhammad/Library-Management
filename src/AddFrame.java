
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class AddFrame extends JFrame implements ActionListener {
    JPanel headerPanel;
    JPanel addPagePanel;
    JLabel addLabel;
    JTextField ISBNField;
    JTextField titleField;
    JTextField authorField;
    JTextField copiesNumField;
    JButton addButton;
    JButton editButton;
    DefaultTableModel tableModel;
    int id;
    int row;


    AddFrame(DefaultTableModel tableModel) throws IOException {
        Color mainTextColor=new Color(55, 55, 51);
        Color backgroundColor =new Color(243, 241, 231);
        Color sidebarBgColor =new Color(126, 93, 46);


        this.tableModel=tableModel;
        this.setSize(new Dimension(650, 600));
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        addLabel = new JLabel("Add Book");
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
        JLabel ISBNLabel = new JLabel("ISBN");
        ISBNLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        ISBNLabel.setForeground(sidebarBgColor);
        addPagePanel.add(ISBNLabel, gbc);

        // Title Label
        gbc.gridx = 1;
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        titleLabel.setForeground(sidebarBgColor);
        addPagePanel.add(titleLabel, gbc);


        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        //  ISBNField
        gbc.gridx = 0;
        ISBNField = new JTextField("ISBN");
        ISBNField.setPreferredSize(new Dimension(260, 42));
        ISBNField.setFont(new Font("Segoe_UI", Font.PLAIN, 20));
        ISBNField.setBackground(backgroundColor);
        ISBNField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        ISBNField.setEditable(true);
        addPagePanel.add(ISBNField, gbc);

        //  titleField
        gbc.gridx = 1;
        titleField = new JTextField("Title");
        titleField.setPreferredSize(new Dimension(260, 42));
        titleField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        titleField.setBackground(backgroundColor);
        titleField.setForeground(Color.LIGHT_GRAY);
        titleField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(titleField, gbc);


        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;

        //Author Label
        gbc.gridx = 0;
        JLabel authorLabel = new JLabel("Author");
        authorLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        authorLabel.setForeground(sidebarBgColor);
        addPagePanel.add(authorLabel, gbc);

        //Copies Number Label
        gbc.gridx = 1;
        JLabel copiesNumLabel = new JLabel("Copies Number");
        copiesNumLabel.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        copiesNumLabel.setForeground(sidebarBgColor);
        addPagePanel.add(copiesNumLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        //  authorField
        gbc.gridx = 0;
        authorField = new JTextField("Author");
        authorField.setPreferredSize(new Dimension(260, 42));
        authorField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        authorField.setBackground(backgroundColor);
        authorField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(authorField, gbc);

        //  copiesNumField
        gbc.gridx = 1;
        copiesNumField = new JTextField("Copies Number");
        copiesNumField.setPreferredSize(new Dimension(260, 42));
        copiesNumField.setFont(new Font("Segoe_UI", Font.PLAIN, 18));
        copiesNumField.setBackground(backgroundColor);
        copiesNumField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));
        addPagePanel.add(copiesNumField, gbc);


        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(35, 15, 10, 15);

        addButton = new JButton("ADD");
        addButton.setFont(new Font("Segoe_UI", Font.PLAIN, 24));
        addButton.setPreferredSize(new Dimension(180, 45));
        addButton.setBackground(sidebarBgColor);
        addButton.setForeground(backgroundColor);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        addPagePanel.add(addButton, gbc);


        activeField(ISBNField,ISBNField.getText());
        activeField(titleField, titleField.getText());
        activeField(authorField,authorField.getText());
        activeField(copiesNumField,copiesNumField.getText());

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(addPagePanel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
//        if(e.getSource()==addButton){
//            try {
//                inventory.addItem(nameField,categoryBox,priceField,quantityField,minQuantityField,this,tableModel);
//                inventory.checkPausedTasks();
//            }catch (MyException ex){
//                JOptionPane.showMessageDialog(this,ex.getMessage());
//                writeExceptionInFile(ex.getMessage());
//            }
//
//        }
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
