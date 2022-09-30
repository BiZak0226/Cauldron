package kr.bizak.cauldron;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class GUI extends JFrame {
    public GUI() {
        /* Create Container */

        setLayout(new BorderLayout());


        /* BorderLayout North Area */
        /* Credit Panel */
        JPanel creditPanel = new JPanel();
        creditPanel.setLayout(new BorderLayout());

        JLabel creditLabel = new JLabel();
        ImageIcon creditIcon = new ImageIcon("src/image/icon/system/credits.png");
        creditLabel.setIcon(creditIcon);

        JTextField creditView = new JTextField();
        creditView.setEditable(false);
        creditView.setText("1,000,000" + " $");

        creditPanel.add(creditLabel, BorderLayout.WEST);
        creditPanel.add(creditView, BorderLayout.CENTER);



        /* BorderLayout West Area */
        /* Select Herb Section Panel */
        JScrollPane herbScrollPanel = new JScrollPane();
        JPanel herbPanel = new JPanel();

        /* Search File List */
        File dir;
        String path = "src/image/icon/herb/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        GridLayout gridLayout;
        if (file.length%4 == 0){
            gridLayout = new GridLayout((file.length/4),4);
        }else{
            gridLayout = new GridLayout((file.length/4)+1,4);
        }
        herbPanel.setLayout(gridLayout);
        int grid = gridLayout.getColumns()*gridLayout.getRows();

        /* Create Compoments */
        JPanel[] herbCompoments = new JPanel[grid];
        JLabel[] herb = new JLabel[grid];
        JPanel[] selection = new JPanel[grid];
        JButton[] addButtons = new JButton[grid];
        JButton[] cancelButtons = new JButton[grid];
        JTextField[] herbNameField = new JTextField[grid];

        ImageIcon herbIcon;
        ImageIcon add = new ImageIcon("src/image/icon/system/add.png");
        ImageIcon cancel = new ImageIcon("src/image/icon/system/cancel.png");

        /* Set Compoments*/
        for (int i = 0; i < grid; i++) {
            int num=i;
            if (i >= file.length){
                num= file.length-1;
            }
            /* Get Herb Name */
            String herbName = String.valueOf(file[num]);
            herbName = herbName.substring(path.length());
            herbName = herbName.substring(0,herbName.length()-4);

            if(herbName.equals("herb_empty")){

            }

            /* Herb Icon */
            herb[i] = new JLabel();
            herb[i].setLayout(new BorderLayout());
            herbIcon = new ImageIcon(String.valueOf(file[num]));
            herb[i].setIcon(herbIcon);
//            herb[i].setSize(100,100);
            herb[i].setPreferredSize(new Dimension(101,102));

            /* Herb DisplayName */
            herbNameField[i] = new JTextField();
            herbNameField[i].setEditable(false);
            herbNameField[i].setText(herbName);
            herbNameField[i].setHorizontalAlignment(JTextField.CENTER);
            herbNameField[i].setPreferredSize(new Dimension(100, 15));

            /* Select Button */
            selection[i] = new JPanel();
            selection[i].setLayout(new GridLayout(1,2));

            addButtons[i] = new JButton(add);
            addButtons[i].setBorderPainted(false);
            addButtons[i].setPreferredSize(new Dimension(30,30));
            addButtons[i].setBackground(Color.BLACK);

            cancelButtons[i] = new JButton(cancel);
            cancelButtons[i].setBorderPainted(false);
            cancelButtons[i].setPreferredSize(new Dimension(30,30));
            cancelButtons[i].setBackground(Color.BLACK);

            selection[i].add(addButtons[i]);
            selection[i].add(cancelButtons[i]);
            selection[i].setPreferredSize(new Dimension(100, 30));

            /* Add Panel */
            herbCompoments[i] = new JPanel();
            herbCompoments[i].setLayout(new BorderLayout());

            herbCompoments[i].add(herb[i], BorderLayout.NORTH);
            herbCompoments[i].add(herbNameField[i], BorderLayout.CENTER);
            herbCompoments[i].add(selection[i], BorderLayout.SOUTH);
            herbPanel.add(herbCompoments[i]);
        }
        herbScrollPanel.setPreferredSize(new Dimension(herbPanel.getPreferredSize().width+18, 500));
        herbScrollPanel.setViewportView(herbPanel);
        herbScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        /* BorderLayout Center Area */
        /* Create Cauldron Section Panel */
        JPanel cauldronPanel = new JPanel();
        cauldronPanel.setLayout(new BorderLayout());
        ImageIcon calldronImage = new ImageIcon("src/image/icon/system/cauldron.png");
        JLabel cauldronLabel = new JLabel();
        cauldronLabel.setIcon(calldronImage);
        JButton calB = new JButton();
        calB.setBorderPainted(false);
        calB.setPreferredSize(new Dimension(300, 100));

        cauldronPanel.add(cauldronLabel, BorderLayout.CENTER);
        cauldronPanel.add(calB, BorderLayout.SOUTH);



        /* BorderLayout South Area */
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setSize(new Window(this).getWidth(), 100);

        southPanel.add(resultArea);

        /* Combination */
        this.add(creditPanel, BorderLayout.NORTH);
        this.add(herbScrollPanel, BorderLayout.WEST);
        this.add(cauldronPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        /* Set Frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cauldron");
        this.pack();
        setVisible(true);
    }
}