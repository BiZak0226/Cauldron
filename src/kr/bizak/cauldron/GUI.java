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
        creditView.setText("1,000,000" + "$");

        creditPanel.add(creditLabel, BorderLayout.WEST);
        creditPanel.add(creditView, BorderLayout.CENTER);



        /* BorderLayout West Area */
        /* Select Herb Section Panel */
        JPanel herbPanel = new JPanel();

        /* Search File List */
        File dir;
        String path = "src/image/icon/herb/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        GridLayout gridLayout = new GridLayout((file.length/4)+1,4);
        herbPanel.setLayout(gridLayout);
        int grid = gridLayout.getColumns()*gridLayout.getRows();
//        System.out.println("GridLayout :: " + gridLayout.getRows() +", "+ gridLayout.getColumns());

//        try{
//            dir = new File(path);
//            file = dir.listFiles();
//            count=file.length;
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }



        /* Create Compoments */
        JPanel[] herbCompoments = new JPanel[grid];
        JLabel[] herb = new JLabel[grid];
        JPanel[] selection = new JPanel[grid];
        JButton[] addButtons = new JButton[grid];
        JButton[] cancelButtons = new JButton[grid];
        ImageIcon herbIcon;
        ImageIcon add = new ImageIcon("src/image/icon/add.png");
        ImageIcon cancel = new ImageIcon("src/image/icon/cancel.png");

//        for (int i = 0; i < count; i++) {
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

            herb[i].setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3)));
            /* Select Button */
            selection[i] = new JPanel();
            selection[i].setLayout(new GridLayout(2,1));

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

        resultArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3)));

        southPanel.add(resultArea);

        /* Combination */
        herbPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));
        cauldronPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));
        southPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));
        creditPanel.setBorder(new TitledBorder(new LineBorder(Color.YELLOW, 3)));

        this.add(creditPanel, BorderLayout.NORTH);
        this.add(herbPanel, BorderLayout.WEST);
        this.add(cauldronPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        /* Set Frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cauldron");
        this.pack();
        setVisible(true);
    }
}