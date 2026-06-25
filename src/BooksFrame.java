import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class BooksFrame extends JPanel implements ActionListener,MouseListener,FocusListener{

    JPanel headerPanel;
    JPanel controlPanel;
    JPanel pagePanel;
    JPanel topPanel;
    JPanel tablePanel;
    JPanel addPanel;
    JPanel showPanel;
    JLabel titleLabel;
    JTextField searchField;
    JButton searchButton;
    JButton editButton;
    JButton deleteButton;
    JButton addButton;
    JButton hideButton;
    JButton backButton;
    JTable booksTable;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    String searchedName;
    Color backgroundColor;
    Color mainTextColor;
    Color sidebarBgColor;

    public BooksFrame(){

            backgroundColor =new Color(243, 241, 231);
            mainTextColor =new Color(55, 55, 51);
            sidebarBgColor =new Color(126, 93, 46);

            pagePanel=new JPanel(new BorderLayout());
            pagePanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            pagePanel.setBackground(backgroundColor);
            pagePanel.setFocusable(true);
            pagePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pagePanel.requestFocusInWindow();
                }
            });

            tablePanel=new JPanel(new BorderLayout());
            tablePanel.setBackground(backgroundColor);
            topPanel=new JPanel(new BorderLayout());
            topPanel.setBackground(backgroundColor);

            searchField=new JTextField();
            searchButton=new JButton();
            addButton=new JButton();
            titleLabel=new JLabel();

            //addButton
            {
                addButton.setPreferredSize(new Dimension(175,35));
                ImageIcon originalIcon=new ImageIcon(getClass().getResource("/images/add.png"));

                Image img = originalIcon.getImage();
                Image newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                ImageIcon addIcon = new ImageIcon(newImg);

                addButton.setText("Add Book");
                addButton.setFont(new Font("Segoe UI",Font.PLAIN,25));
                addButton.setBackground(sidebarBgColor);
                addButton.setForeground(backgroundColor);
                addButton.setIcon(addIcon);
                addButton.setHorizontalTextPosition(SwingConstants.RIGHT);
                addButton.setHorizontalAlignment(SwingConstants.LEFT);
                addButton.setIconTextGap(5);
                addButton.setFocusable(false);
                addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                addButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        pagePanel.requestFocusInWindow();
                    }
                });
            }

             //backButton
//            backButton=new JButton("Back");
//            backButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 15));
//            backButton.setBackground(primary);
//            backButton.setForeground(textColor);
//            backButton.setFocusable(false);
//            backButton.setBounds(10,5,80,30);
//            pagePanel.add(backButton);

            //searchButton
            {
                searchButton.setText("Search");
                searchButton.setFont(new Font("Segoe UI",Font.PLAIN,25));
                searchButton.setBackground(sidebarBgColor);
                searchButton.setForeground(backgroundColor);
                searchButton.setPreferredSize(new Dimension(110,35));
                searchButton.setFocusable(false);
                searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                searchButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        pagePanel.requestFocusInWindow();
                    }
                });

            }

            //searchField
            {
                searchField.setPreferredSize(new Dimension(350, 35));
                searchField.setText("Search by book name");
                searchField.addFocusListener(this);
                searchField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                searchField.setBackground(backgroundColor);
                searchField.setForeground(Color.GRAY);
                searchField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,mainTextColor));

                searchField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (searchField.getText().equals("Search by book name")) {
                            searchField.setText("");
                            searchField.setForeground(Color.BLACK);
                        }
                    }
                });
            }

            //titleLabel
            {titleLabel.setText(" Books");
                titleLabel.setFont(new Font("Segoe_UI",Font.ITALIC,50));
                titleLabel.setForeground(mainTextColor);
                titleLabel.setPreferredSize(new Dimension(350,80));
            }

            //headerPanel
            {headerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
                headerPanel.setPreferredSize(new Dimension(800,100));
                headerPanel.setBackground(backgroundColor);
                headerPanel.add(titleLabel);
                topPanel.add(headerPanel,BorderLayout.NORTH);
            }

            //controlPanel
            {controlPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,15));
                controlPanel.setBackground(backgroundColor);
                controlPanel.add(searchField);
                controlPanel.add(searchButton);
                topPanel.add(controlPanel,BorderLayout.WEST);
            }
            //addPanel
            {addPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,15));
                addPanel.setBackground(backgroundColor);
                addPanel.add(addButton);
                topPanel.add(addPanel,BorderLayout.EAST);
                pagePanel.add(topPanel,BorderLayout.NORTH);}

            //tablePanel
            {String[] column={"ISBN","Title","Author","Copies Number","Status"};
                tableModel=new DefaultTableModel(column,0);
                booksTable =new JTable(tableModel);
                booksTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                booksTable.setBackground(backgroundColor);
                booksTable.setSelectionBackground(backgroundColor);
                booksTable.setRowHeight(30);
                booksTable.setShowVerticalLines(false);
                booksTable.setDefaultEditor(Object.class,null);
                booksTable.setFocusable(false);
                scrollPane=new JScrollPane(booksTable);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                scrollPane.getViewport().setBackground(backgroundColor);
                JTableHeader header= booksTable.getTableHeader();
                header.setReorderingAllowed(false);
//                header.setOpaque(true);
                tablePanel.add(scrollPane,BorderLayout.CENTER);
                tablePanel.setFocusable(true);
                tablePanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        tablePanel.requestFocusInWindow();
                    }
                });

                pagePanel.revalidate();
                pagePanel.repaint();
                pagePanel.add(tablePanel,BorderLayout.CENTER);
                booksTable.addMouseListener(this);

                tableModel.addRow(new Object[]{
                        "9780134685991",
                        "Effective Java",
                        "Joshua Bloch",
                        5,
                        "Available"
                });

                DefaultTableCellRenderer centerRenderer=new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

                header.setDefaultRenderer(new DefaultTableCellRenderer(){
                    @Override

                    public  Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean isFocus,int row,int column) {

                        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, isFocus, row, column);

                        label.setText(value.toString());
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));

                        label.setPreferredSize(new Dimension(label.getWidth(),30));
                        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
                        label.setBackground(backgroundColor);
                        label.setForeground(mainTextColor);

                        return  label;
                    }

                });

                booksTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                booksTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                booksTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                booksTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                booksTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer(){

                    {
                        setHorizontalAlignment(SwingConstants.CENTER);
                    }

                    @Override
                    public  Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean isFocus,int row,int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, isFocus, row, column);

                        String status = value.toString();
                        c.setFont(new Font("Segoe UI", Font.BOLD, 15));

                        switch (status) {
                            case "Available":
                                c.setForeground(new Color(0, 166, 62));
                                break;

                            case "Un Available":
                                c.setForeground(new Color(231, 0, 11));
                                break;

                            default:
                                c.setForeground(Color.BLACK);
                        }

                        return c;

                    }

                });
            }

            searchButton.addActionListener(this);
//            backButton.addActionListener(this);
            addButton.addActionListener(this);

            this.add(pagePanel);
            this.setVisible(true);

        }

    public void showItemInfo(int row){
            if(showPanel!=null){
                pagePanel.remove(showPanel);
            }

            ImageIcon originalHideIcon=new ImageIcon(getClass().getResource("/images/hide.png"));

            Image imgHide = originalHideIcon.getImage();
            Image newHideImg = imgHide.getScaledInstance(30, 20, java.awt.Image.SCALE_SMOOTH);
            ImageIcon hideIcon = new ImageIcon(newHideImg);

            hideButton=new JButton();
            hideButton.setIcon(hideIcon);
            hideButton.setHorizontalAlignment(SwingConstants.LEFT);
            hideButton.setBounds(330,5,40,30);
            hideButton.setBackground(backgroundColor);
            hideButton.setFocusable(false);
            hideButton.setBorderPainted(false);
            hideButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            hideButton.addActionListener(this);


            showPanel=new JPanel();
            showPanel.setLayout(null);
            showPanel.setBackground(backgroundColor);
            showPanel.setPreferredSize(new Dimension(380,500));
            showPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, mainTextColor));


            //ISBN
            JLabel ISBNLabel = new JLabel("ISBN : ");
            ISBNLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
            ISBNLabel.setBounds(30, 40, 350, 40);

            JLabel ISBNValueLabel = new JLabel(booksTable.getValueAt(row, 0).toString());
            ISBNValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            ISBNValueLabel.setBounds(110, 40, 350, 40);

            //title
            JLabel titleLabel=new JLabel("Title : ");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
            titleLabel.setBounds(30,100,350,40);

            JLabel titleValueLabel=new JLabel(booksTable.getValueAt(row,1).toString());
            titleValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            titleValueLabel.setBounds(105,100,350,40);

              //author
            JLabel authorLabel=new JLabel("Author : ");
            authorLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
            authorLabel.setBounds(30,160,350,40);

            JLabel authorValueLabel=new JLabel(booksTable.getValueAt(row,2).toString());
            authorValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            authorValueLabel.setBounds(135,160,350,40);

            //copiesNumber
            JLabel copiesNumberLabel=new JLabel("Copies Number : ");
            copiesNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
            copiesNumberLabel.setBounds(30,220,350,40);

            JLabel copiesNumberValueLabel=new JLabel(booksTable.getValueAt(row,3).toString());
            copiesNumberValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            copiesNumberValueLabel.setBounds(230,220,350,40);

              //status
            JLabel statusLabel=new JLabel("Status : ");
            statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
            statusLabel.setBounds(30,280,350,40);

            JLabel statusValueLabel=new JLabel(booksTable.getValueAt(row,4).toString());
            statusValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            statusValueLabel.setBounds(120,280,350,40);

            if (statusValueLabel.getText().contains("Available"))
                statusValueLabel.setForeground(new Color(0,166,62));
            if (statusValueLabel.getText().contains("Un Available"))
                statusValueLabel.setForeground(new Color(231,0,11));


            ImageIcon originalDeleteIcon=new ImageIcon(getClass().getResource("/images/delete1.png"));

            Image img = originalDeleteIcon.getImage();
            Image newDeleteImg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            ImageIcon deleteIcon = new ImageIcon(newDeleteImg);

            deleteButton=new JButton("Delete");
            deleteButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            deleteButton.setIcon(deleteIcon);
            deleteButton.setHorizontalTextPosition(SwingConstants.RIGHT);
            deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
            deleteButton.setIconTextGap(5);
            deleteButton.setBounds(30,500,150,50);
            deleteButton.setBackground(new Color(231,0,11));
            deleteButton.setForeground(backgroundColor);
            deleteButton.setFocusable(false);
            deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            ImageIcon originalEditIcon=new ImageIcon(getClass().getResource("/images/edit1.png"));

            Image imgEdit = originalEditIcon.getImage();
            Image newEditImg = imgEdit.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            ImageIcon editIcon = new ImageIcon(newEditImg);

            editButton=new JButton("Edit");
            editButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
            editButton.setIcon(editIcon);
            editButton.setHorizontalTextPosition(SwingConstants.RIGHT);
            editButton.setHorizontalAlignment(SwingConstants.LEFT);
            editButton.setIconTextGap(5);
            editButton.setBounds(200,500,150,50);
            editButton.setBackground(sidebarBgColor);
            editButton.setForeground(backgroundColor);
            editButton.setFocusable(false);
            editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            deleteButton.addActionListener(this);
            editButton.addActionListener(this);

            showPanel.add(ISBNLabel);
            showPanel.add(ISBNValueLabel);
            showPanel.add(titleLabel);
            showPanel.add(titleValueLabel);
            showPanel.add(authorLabel);
            showPanel.add(authorValueLabel);
            showPanel.add(copiesNumberLabel);
            showPanel.add(copiesNumberValueLabel);
            showPanel.add(statusLabel);
            showPanel.add(statusValueLabel);
            showPanel.add(deleteButton);
            showPanel.add(editButton);
            showPanel.add(hideButton);
            pagePanel.add(showPanel,BorderLayout.EAST);
            pagePanel.revalidate();
            pagePanel.repaint();

        }


        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==addButton){
                try {
                    AddFrame addFrame=new AddFrame(tableModel);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            if(e.getSource()==editButton){
                try {
                    EditFrame editFrame=new EditFrame(tableModel);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            if(e.getSource()==hideButton){
                pagePanel.remove(showPanel);
                pagePanel.revalidate();
                pagePanel.repaint();
            }

//            if (e.getSource()==deleteButton){
//                int row=itemsTable.getSelectedRow();
//                int id=Integer.parseInt(itemsTable.getValueAt(row,0).toString());
//                try {
//                    inventory.deleteAnItem(id);               //
//                    tableModel.removeRow(row);
//                    pagePanel.remove(showPanel);
//                    pagePanel.revalidate();
//                    pagePanel.repaint();
//                } catch (IOException ex) {
//
//                    writeExceptionInFile(ex.getMessage());
//                } catch (MyException ex) {
//
//                    writeExceptionInFile(ex.getMessage());
//                }
//            }
//
//            if (e.getSource()==editButton){
//                int row=itemsTable.getSelectedRow();
//                int id=Integer.parseInt(itemsTable.getValueAt(row,0).toString());
//
//                AddFrame edit=new AddFrame(id,row,tableModel,this);
//
//            }
//
//            if(e.getSource()==searchButton){
//
//                searchedName=searchField.getText();
//                searchedCategory= categoryFilter.getSelectedItem().toString();
//                searchedStatus=statusFilter.getSelectedItem().toString();
//
//                inventory.filter(searchedName,searchedCategory,searchedStatus,tableModel);        //
//            }
//
//            if (e.getSource()==backButton){
//                SupervisorForm supervisorForm=new SupervisorForm();
//                supervisorForm.setVisible(true);
//                this.dispose();
//            }

        }

        public void refreshShowPanel(){
//            int row=itemsTable.getSelectedRow();
//
//            if (row!=-1){
//                showItemInfo(row);
//            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount()==2){
                int row= booksTable.getSelectedRow();
                if (row != -1) {
                    showItemInfo(row);
                }
            }
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

        @Override
        public void focusGained(FocusEvent e) {
            if (searchField.getText().equals("Search by book name")){
                searchField.setText("");
                searchField.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (searchField.getText().isEmpty()) {
                searchField.setForeground(Color.GRAY);
                searchField.setText("Search by book name");
            }
        }

}
