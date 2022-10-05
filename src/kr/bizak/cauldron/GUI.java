package kr.bizak.cauldron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame {
    Event actionEvent = new Event();

    JTextArea resultArea;
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



        /* ========#========# BorderLayout West Area #========#======== */
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
        if (file.length/3 < 2){
            gridLayout = new GridLayout(3,3);
        }else{
            gridLayout = new GridLayout((file.length/3)+1,3);
        }
        herbPanel.setLayout(gridLayout);
        int grid = gridLayout.getColumns()*gridLayout.getRows();

        /* Create Compoments */
        JPanel[] herbCompoments = new JPanel[grid];
        JButton[] herbButtons = new JButton[grid];
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

            /* Herb DisplayName */
            herbNameField[i] = new JTextField();
            herbNameField[i].setEditable(false);
            herbNameField[i].setText(herbName);
            herbNameField[i].setHorizontalAlignment(JTextField.CENTER);
            herbNameField[i].setPreferredSize(new Dimension(100, 20));

            /* Herb Icon */
            herbIcon = new ImageIcon(String.valueOf(file[num]));
            herbButtons[i] = new JButton(herbIcon);
            herbButtons[i].setPreferredSize(new Dimension(101,102));
            herbButtons[i].addActionListener(actionEvent);

            /* Add Panel */
            herbCompoments[i] = new JPanel();
            herbCompoments[i].setLayout(new BorderLayout());

            herbCompoments[i].add(herbButtons[i], BorderLayout.CENTER);
            herbCompoments[i].add(herbNameField[i], BorderLayout.NORTH);
            herbPanel.add(herbCompoments[i]);
        }
        herbScrollPanel.setPreferredSize(new Dimension(herbPanel.getPreferredSize().width+18, 350));
        herbScrollPanel.setViewportView(herbPanel);
        herbScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        /* ========#========# BorderLayout Center Area #========#======== */





        /* Create Cauldron Section Panel */
        JPanel cauldronPanel = new JPanel();
        cauldronPanel.setLayout(new BorderLayout());
        ImageIcon calldronImage = new ImageIcon("src/image/icon/system/cauldron.png");
        JLabel cauldronLabel = new JLabel();
        cauldronLabel.setIcon(calldronImage);
        JButton caulB = new JButton("cauldron");
        caulB.setBorderPainted(false);
        caulB.setPreferredSize(new Dimension(300, 50));
        caulB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText(actionEvent.getEssence().toString());
            }
        });

        cauldronPanel.add(cauldronLabel, BorderLayout.NORTH);
        cauldronPanel.add(caulB, BorderLayout.SOUTH);



        /* ========#========# BorderLayout South Area #========#======== */
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        resultArea = new JTextArea(10, 30);
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