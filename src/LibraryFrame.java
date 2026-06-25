import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LibraryFrame extends JFrame implements ActionListener {

    JPanel menuPanel;
    JLabel menuPanelTitle;
    JButton booksShowButton;
    JButton borrowersShowButton;
    JButton waitingListsShowButton;
    Color backgroundColor;
    Color mainTextColor;
    Color sidebarBgColor;
    ImageIcon brownBookIcon;
    ImageIcon brownBorrowerIcon;
    ImageIcon brownWaitingListIcon;

    public LibraryFrame(JPanel booksPanel){

        backgroundColor =new Color(243, 241, 231);
        mainTextColor =new Color(55, 55, 51);
        sidebarBgColor =new Color(126, 93, 46);

        ImageIcon BookIcon=new ImageIcon(getClass().getResource("/Images/LibraryIcon.png"));
        this.setIconImage(BookIcon.getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1100, 820));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        menuPanelTitle=new JLabel("Library Management");
        menuPanelTitle.setFont(new Font("Segoe UI",Font.ITALIC,28));
        menuPanelTitle.setForeground(backgroundColor);
        menuPanelTitle.setBounds(12, 20, 350, 40);

        ImageIcon orWhiteBookIcon=new ImageIcon(getClass().getResource("/Images/book.png"));
        Image imgWhiteBook = orWhiteBookIcon.getImage();
        Image newWhiteBookImg = imgWhiteBook.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon whiteBookIcon = new ImageIcon(newWhiteBookImg);

        ImageIcon orBrownBookIcon=new ImageIcon(getClass().getResource("/Images/book1.png"));
        Image imgBrownBook = orBrownBookIcon.getImage();
        Image newBrownBookImg = imgBrownBook.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        brownBookIcon = new ImageIcon(newBrownBookImg);

        booksShowButton=new JButton("Books");
        booksShowButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        booksShowButton.setBackground(sidebarBgColor);
        booksShowButton.setForeground(backgroundColor);
        booksShowButton.setIcon(whiteBookIcon);
        booksShowButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        booksShowButton.setHorizontalAlignment(SwingConstants.LEFT);
        booksShowButton.setIconTextGap(10);
        booksShowButton.setBounds(15, 100, 270, 40);
        booksShowButton.setFocusable(false);
        booksShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        booksShowButton.addActionListener(this);
        booksShowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon originalBorrowerIcon=new ImageIcon(getClass().getResource("/Images/borrower.png"));
        Image imgBorrower = originalBorrowerIcon.getImage();
        Image newBorrowerImg = imgBorrower.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon borrowerIcon = new ImageIcon(newBorrowerImg);

        ImageIcon orBrownBorrowerIcon=new ImageIcon(getClass().getResource("/Images/borrower1.png"));
        Image imgBrownBorrower = orBrownBorrowerIcon.getImage();
        Image newBrownBorrowerImg = imgBrownBorrower.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        brownBorrowerIcon = new ImageIcon(newBrownBorrowerImg);

        borrowersShowButton=new JButton("Borrowers");
        borrowersShowButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        borrowersShowButton.setBackground(sidebarBgColor);
        borrowersShowButton.setForeground(backgroundColor);
        borrowersShowButton.setIcon(borrowerIcon);
        borrowersShowButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        borrowersShowButton.setHorizontalAlignment(SwingConstants.LEFT);
        borrowersShowButton.setIconTextGap(10);
        borrowersShowButton.setBounds(15, 180, 270, 40);
        borrowersShowButton.setFocusable(false);
        borrowersShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        borrowersShowButton.addActionListener(this);
        borrowersShowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        ImageIcon originalListIcon=new ImageIcon(getClass().getResource("/Images/waitingList.png"));
        Image imgList = originalListIcon.getImage();
        Image newListImg = imgList.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon listIcon = new ImageIcon(newListImg);

        ImageIcon orBrownWaitingListIcon=new ImageIcon(getClass().getResource("/Images/waitingList1.png"));
        Image imgBrownWaitingList = orBrownWaitingListIcon.getImage();
        Image newBrownWaitingListImg = imgBrownWaitingList.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        brownWaitingListIcon = new ImageIcon(newBrownWaitingListImg);

        waitingListsShowButton=new JButton("Waiting Lists");
        waitingListsShowButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        waitingListsShowButton.setBackground(sidebarBgColor);
        waitingListsShowButton.setForeground(backgroundColor);
        waitingListsShowButton.setIcon(listIcon);
        waitingListsShowButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        waitingListsShowButton.setHorizontalAlignment(SwingConstants.LEFT);
        waitingListsShowButton.setIconTextGap(10);
        waitingListsShowButton.setBounds(15, 260, 270, 40);
        waitingListsShowButton.setFocusable(false);
        waitingListsShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        waitingListsShowButton.addActionListener(this);
        waitingListsShowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuPanel=new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(sidebarBgColor);
        menuPanel.setPreferredSize(new Dimension(270,0));
        menuPanel.setBorder(BorderFactory.createEmptyBorder());
        menuPanel.setOpaque(true);
        menuPanel.add(menuPanelTitle);
        menuPanel.add(booksShowButton);
        menuPanel.add(borrowersShowButton);
        menuPanel.add(waitingListsShowButton);
        this.add(menuPanel,BorderLayout.WEST);
        this.add(booksPanel,BorderLayout.CENTER);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    booksShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                    booksShowButton.setBackground(backgroundColor);
                    booksShowButton.setForeground(sidebarBgColor);
                    booksShowButton.setIcon(brownBookIcon);
                }

                @Override
                public void windowClosing(WindowEvent e)  {
                    dispose();
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });

        }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==booksShowButton){
            booksShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            booksShowButton.setBackground(backgroundColor);
            booksShowButton.setForeground(sidebarBgColor);
            booksShowButton.setIcon(brownBookIcon);

            resetButtonState(borrowersShowButton, new ImageIcon(getClass().getResource("/Images/borrower.png")));
            resetButtonState(waitingListsShowButton, new ImageIcon(getClass().getResource("/Images/waitingList.png")));

            // Switch to books

            Container contentPane = this.getContentPane();

            BorderLayout layout = (BorderLayout) contentPane.getLayout();
            Component center = layout.getLayoutComponent(BorderLayout.CENTER);

            if (center != null) {
                contentPane.remove(center);
                contentPane.add(new BooksFrame().pagePanel, BorderLayout.CENTER);
                contentPane.revalidate();
                contentPane.repaint();
            }
            //this.add(new BooksFrame().pagePanel, BorderLayout.CENTER);
        }

        if(e.getSource()==borrowersShowButton){
            borrowersShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            borrowersShowButton.setBackground(backgroundColor);
            borrowersShowButton.setForeground(sidebarBgColor);
            borrowersShowButton.setIcon(brownBorrowerIcon);

            resetButtonState(booksShowButton, new ImageIcon(getClass().getResource("/Images/book.png")));
            resetButtonState(waitingListsShowButton, new ImageIcon(getClass().getResource("/Images/waitingList.png")));

            // Switch to borrowers

            Container contentPane = this.getContentPane();

            BorderLayout layout = (BorderLayout) contentPane.getLayout();
            Component center = layout.getLayoutComponent(BorderLayout.CENTER);

            if (center != null) {

                contentPane.remove(center);

                if (Main.libraryManagement.borrowersFrame == null) {
                    Main.libraryManagement.borrowersFrame = new BorrowersFrame();
                }

                BorrowersFrame currentBorrowers =Main.libraryManagement.borrowersFrame;
                contentPane.add(currentBorrowers.pagePanel, BorderLayout.CENTER);


//                contentPane.add(new BorrowersFrame().pagePanel, BorderLayout.CENTER);
                contentPane.revalidate();
                contentPane.repaint();
            }

//            this.add(new BorrowersFrame(libraryManagement).pagePanel, BorderLayout.CENTER);
        }

        if(e.getSource()==waitingListsShowButton){
            waitingListsShowButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            waitingListsShowButton.setBackground(backgroundColor);
            waitingListsShowButton.setForeground(sidebarBgColor);
            waitingListsShowButton.setIcon(brownWaitingListIcon);

            resetButtonState(booksShowButton, new ImageIcon(getClass().getResource("/Images/book.png")));
            resetButtonState(borrowersShowButton, new ImageIcon(getClass().getResource("/Images/borrower.png")));

//            WaitingListFrame waitingListFrame=new WaitingListFrame();
            Container contentPane = this.getContentPane();

            BorderLayout layout = (BorderLayout) contentPane.getLayout();
            Component center = layout.getLayoutComponent(BorderLayout.CENTER);

            if (center != null) {
                contentPane.remove(center);
                contentPane.add(new WaitingListFrame().pagePanel, BorderLayout.CENTER);
                contentPane.revalidate();
                contentPane.repaint();
            }

        }

    }

    private void resetButtonState(JButton button, ImageIcon originalWhiteIcon) {
        Image img = originalWhiteIcon.getImage();
        Image newImg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

        button.setBackground(sidebarBgColor);
        button.setForeground(backgroundColor);
        button.setIcon(new ImageIcon(newImg));
        button.setBorder(null);
    }





}
