package kr.bizak.cauldron;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.StringTokenizer;

/* Git commit Test */

public class GUI extends JFrame {
    public GUI() {
        /* Create Container */
        setLayout(new BorderLayout());

        /* BorderLayout West Area */
        /* Select Herb Section Panel */
        JPanel herbPanel = new JPanel();
        herbPanel.setLayout(new GridLayout(3,5));

        /* Search File List */
        int count = 0;
        File dir = null;
        String path = "src/image/icon/herb/";
        File[] file = new File[0];
        dir = new File(path);
        file = dir.listFiles();
        count=file.length;
//        try{
//            dir = new File(path);
//            file = dir.listFiles();
//            count=file.length;
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }



        /* Create Compoments */
        JPanel[] herbCompoments = new JPanel[count];
        JLabel[] herb = new JLabel[count];
        JPanel[] selection = new JPanel[count];
        JButton[] addButtons = new JButton[count];
        JButton[] cancelButtons = new JButton[count];
        ImageIcon herbIcon;
        ImageIcon add = new ImageIcon("src/image/icon/add.png");
        ImageIcon cancel = new ImageIcon("src/image/icon/cancel.png");

        for (int i = 0; i < count; i++) {
            /* Herb Icon */
            herb[i] = new JLabel();
            herb[i].setLayout(new BorderLayout());
            herbIcon = new ImageIcon(String.valueOf(file[i]));
            herb[i].setIcon(herbIcon);

            herb[i].setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3)));
            /* Select Button */
            selection[i] = new JPanel();
            selection[i].setLayout(new GridLayout(2,1));

            String herbName = String.valueOf(file[i]);
            herbName = herbName.substring(path.length());
            herbName = herbName.substring(0,herbName.length()-4);

            addButtons[i] = new JButton(herbName + " add");
            cancelButtons[i] = new JButton(herbName + " cancle");
            selection[i].add(addButtons[i]);
            selection[i].add(cancelButtons[i]);

            selection[i].setBorder(new TitledBorder(new LineBorder(Color.BLUE, 3)));

            /* Add Panel */
            herbCompoments[i] = new JPanel();
            herbCompoments[i].setLayout(new BorderLayout());

            herbCompoments[i].add(herb[i], BorderLayout.CENTER);
            herbCompoments[i].add(selection[i], BorderLayout.SOUTH);
            herbPanel.add(herbCompoments[i]);
        }

        /* BorderLayout Center Area */
        /* Create Cauldron Section Panel */
        JPanel cauldronPanel = new JPanel();
        cauldronPanel.setLayout(new BorderLayout());
        ImageIcon calldronImage = new ImageIcon("src/image/icon/cauldron.png");
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

        resultArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3)));

        southPanel.add(resultArea);

        /* Combination */
        herbPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));
        cauldronPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));
        southPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));


        this.add(herbPanel,BorderLayout.WEST);
        this.add(cauldronPanel,BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);

        /* Set Frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cauldron");
        this.pack();
        setVisible(true);
    }
}