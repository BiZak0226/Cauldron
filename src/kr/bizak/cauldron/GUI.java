package kr.bizak.cauldron;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

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

//    MaterialDAO materalData = new MaterialDAO();

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
        String path = "src/config/material/";
        File[] file;
        dir = new File(path);
        file = dir.listFiles();

        materialPanel.setLayout(new BoxLayout(materialPanel, BoxLayout.Y_AXIS));
//        materialPanel.setLayout(new GridLayout(file.length, 1));

        for (int i = 0; i < file.length; i++) {
            materialPanel.add(createCompoment(file[i]));
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

    private Component createCompoment(File file) {
        JPanel material_Panel = new JPanel();

        material_Panel.setLayout(new BorderLayout());
        JPanel materiallistPanel = new JPanel();
        GridLayout materialGrid;

        Reader reader;
        JSONObject jsonObject = null;
        JSONObject materialObject;

        /* Load JSON */
        try {
            reader = new FileReader(file);
            jsonObject = (JSONObject)new JSONParser().parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Get Key */
        String[] filenamearr = String.valueOf(file).split("\\\\");
        String filename = filenamearr[filenamearr.length-1];
        filename = filename.substring(0,filename.length()-5);

        materialGrid = new GridLayout(jsonObject.size()/3+1, 3);
        materiallistPanel.setLayout(materialGrid);

        /* Create Compoment */
        for (int i = 0; i < jsonObject.size(); i++) {
            materialObject = (JSONObject) jsonObject.get(filename+"_"+(i+1));

            System.out.println(materialObject.get("name"));

            /* material DisplayName */
            JTextField materialNameField = new JTextField();
            materialNameField.setEditable(false);
            materialNameField.setText(String.valueOf(materialObject.get("name")));
            materialNameField.setHorizontalAlignment(JTextField.CENTER);
            materialNameField.setPreferredSize(new Dimension(100, 20));

            /* material Icon */
            String iconpath = "src/image/icon/material/";
            ImageIcon materialIcon = new ImageIcon(iconpath + String.valueOf(materialObject.get("icon")));
            JButton materialButtons = new JButton(materialIcon);
            materialButtons.setBackground(Color.WHITE);
            materialButtons.setBorderPainted(false);
            materialButtons.setPreferredSize(new Dimension(101,102));
            materialButtons.addActionListener(actionEvent);

            /* Add Panel */
            JPanel materialCompoments = new JPanel();
            materialCompoments.setLayout(new BorderLayout());

            materialCompoments.add(materialNameField, BorderLayout.NORTH);
            materialCompoments.add(materialButtons, BorderLayout.CENTER);
            materiallistPanel.add(materialCompoments);
        }


        /* Set material_Panel */
        JTextField materialName = new JTextField();
        materialName.setText(filename.toUpperCase());
        materialName.setBackground(Color.decode("#AAAAFF"));
        materialName.setHorizontalAlignment(JTextField.CENTER);
        materialName.setEditable(false);
        material_Panel.add(materialName, BorderLayout.NORTH);
        material_Panel.add(materiallistPanel, BorderLayout.CENTER);

        return material_Panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionRecipe.setEssence(actionEvent.getEssence());
        actionRecipe.Recipe();
        resultArea.setText(resultArea.getText() + actionEvent.getEssence().toString() + "\n");
        actionEvent.clearEssence();
    }





}