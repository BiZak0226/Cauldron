package kr.bizak.cauldron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener{
    Event actionEvent = new Event();
    Recipe actionRecipe = new Recipe();

    /* ========#========# BorderLayout North Area #========#======== */
    JTextField creditView;
    JLabel creditLabel;
    JPanel creditPanel;

    /* ========#========# BorderLayout West Area #========#======== */
    JScrollPane materialScrollPanel;
    JPanel materialPanel;

    /* ========#========# BorderLayout Center Area #========#======== */
    JPanel cauldronPanel;
    ImageIcon calldronImage;
    JLabel cauldronLabel;
    JButton caulB;

    /* ========#========# BorderLayout South Area #========#======== */
    JPanel southPanel;
    JTextArea resultArea;

    public GUI() {
        /* Create Container */
        setLayout(new BorderLayout());

        /* ========#========# BorderLayout North Area #========#======== */

        /* Credit Panel */
        creditPanel= new JPanel();
        creditPanel.setLayout(new BorderLayout());

        creditLabel= new JLabel();
        ImageIcon creditIcon = new ImageIcon("src/image/icon/system/credits.png");
        creditLabel.setIcon(creditIcon);

        creditView = new JTextField();
        creditView.setEditable(false);
        creditView.setText("1,000,000" + " $");

        creditPanel.add(creditLabel, BorderLayout.WEST);
        creditPanel.add(creditView, BorderLayout.CENTER);



        /* ========#========# BorderLayout West Area #========#======== */

        /* Select material Section Panel */
        materialScrollPanel= new JScrollPane();
        materialPanel= new JPanel();

        /* Search File List */
        File dir;
        String path = "src/image/icon/material/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        GridLayout gridLayout;
        if (file.length/3 < 2){
            gridLayout = new GridLayout(3,3);
        }else{
            gridLayout = new GridLayout((file.length/3)+1,3);
        }
        materialPanel.setLayout(gridLayout);
        int grid = gridLayout.getColumns()*gridLayout.getRows();

        /* Create Compoments */
        JPanel[] materialCompoments = new JPanel[grid];
        JButton[] materialButtons = new JButton[grid];
        JTextField[] materialNameField = new JTextField[grid];

        ImageIcon materialIcon;

        /* Set Compoments*/
        for (int i = 0; i < grid; i++) {
            /* Get material Name */
            String materialName;
            if (i < file.length){
                materialName = String.valueOf(file[i]);
                materialName = materialName.substring(path.length());
                materialName = materialName.substring(0,materialName.length()-4);
            }else{ materialName = ""; }

            /* material DisplayName */
            materialNameField[i] = new JTextField();
            materialNameField[i].setEditable(false);
            materialNameField[i].setText(materialName);
            materialNameField[i].setHorizontalAlignment(JTextField.CENTER);
            materialNameField[i].setPreferredSize(new Dimension(100, 20));

            /* material Icon */
            if (i < file.length){
                materialIcon = new ImageIcon(String.valueOf(file[i]));
            }else{
                materialIcon = new ImageIcon("src/image/icon/system/herb_empty.png");
            }
            materialButtons[i] = new JButton(materialIcon);
            materialButtons[i].setBackground(Color.WHITE);
            materialButtons[i].setBorderPainted(false);
            materialButtons[i].setPreferredSize(new Dimension(101,102));
            materialButtons[i].addActionListener(actionEvent);

            /* Add Panel */
            materialCompoments[i] = new JPanel();
            materialCompoments[i].setLayout(new BorderLayout());

            materialCompoments[i].add(materialButtons[i], BorderLayout.CENTER);
            materialCompoments[i].add(materialNameField[i], BorderLayout.NORTH);
            materialPanel.add(materialCompoments[i]);
        }

        materialScrollPanel.setPreferredSize(new Dimension(materialPanel.getPreferredSize().width+18, 350));
        materialScrollPanel.setViewportView(materialPanel);
        materialScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        /* ========#========# BorderLayout Center Area #========#======== */

        /* Create Cauldron Section Panel */
        cauldronPanel = new JPanel();
        cauldronPanel.setLayout(new BorderLayout());
        calldronImage = new ImageIcon("src/image/icon/system/cauldron.png");
        cauldronLabel = new JLabel();
        cauldronLabel.setIcon(calldronImage);
        caulB = new JButton("cauldron");
        caulB.setBorderPainted(false);
        caulB.setPreferredSize(new Dimension(300, 50));
        caulB.addActionListener(this);

        cauldronPanel.add(cauldronLabel, BorderLayout.NORTH);
        cauldronPanel.add(caulB, BorderLayout.SOUTH);



        /* ========#========# BorderLayout South Area #========#======== */

        /* Panel */
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout());
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setSize(new Window(this).getWidth(), 100);

        southPanel.add(resultArea);



        /* Combination */
        this.add(creditPanel, BorderLayout.NORTH);
        this.add(materialScrollPanel, BorderLayout.WEST);
        this.add(cauldronPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);



        /* Set Frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cauldron");
        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionRecipe.setEssence(actionEvent.getEssence());
        actionRecipe.Recipe();
        resultArea.setText(resultArea.getText() + actionEvent.getEssence().toString() + "\n");
        actionEvent.clearEssence();
    }





}