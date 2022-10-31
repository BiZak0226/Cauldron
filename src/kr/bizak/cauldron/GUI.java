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

public class GUI extends JFrame{
    SelectEvent actionEvent = new SelectEvent();
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
    JScrollPane trayScrollPanel;
    JPanel trayPanel;
    JTextArea resultTextArea;
    JScrollPane resultScrollPanel;


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

        /* Create Material Component */
        for (int i = 0; i < file.length; i++) {
            materialPanel.add(createComponent(file[i]));
        }

        System.out.println(materialPanel.getPreferredSize().width);
        System.out.println(materialPanel.getPreferredSize().width+18);

        materialScrollPanel.setPreferredSize(new Dimension(materialPanel.getPreferredSize().width+18, 350));
        materialScrollPanel.setViewportView(materialPanel);
        materialScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
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
        caulB.addActionListener(new Generate());

        JButton caulImageButton = new JButton(calldronImage);
//        caulImageButton.setEnabled(false);
        caulImageButton.setBackground(Color.WHITE);
        caulImageButton.setBorderPainted(false);


//        cauldronPanel.add(cauldronLabel, BorderLayout.NORTH);
        cauldronPanel.add(caulImageButton, BorderLayout.CENTER);
        cauldronPanel.add(caulB, BorderLayout.SOUTH);

        /* ========#========# BorderLayout South Area #========#======== */

        /* Panel */
        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        trayPanel = new JPanel();
        trayPanel.setLayout(new GridLayout(1,5));
        trayPanel.setPreferredSize(new Dimension(500+10,100));

        /* provisional component */
        ImageIcon dummyicon;
        JButton potion;
        for (int i = 0; i < 5; i++) {
            dummyicon = new ImageIcon("src/image/icon/potion/100/round-potion-empty.png");
            potion = new JButton(dummyicon);
            potion.setBackground(Color.WHITE);
            potion.setBorderPainted(false);
            trayPanel.add(potion);
        }

        trayScrollPanel = new JScrollPane();
        trayScrollPanel.setViewportView(trayPanel);


        resultTextArea = new JTextArea(8, 20);
        resultTextArea.setEditable(false);
        resultTextArea.setSize(100, 100);

        resultScrollPanel = new JScrollPane();
        resultScrollPanel.setViewportView(resultTextArea);
        resultScrollPanel.getVerticalScrollBar().setUnitIncrement(8);
        resultScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        southPanel.add(trayScrollPanel, BorderLayout.WEST);
        southPanel.add(resultScrollPanel, BorderLayout.CENTER);



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

    private Component createComponent(File file) {
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

//        materiallistPanel.setBorder(new LineBorder(Color.RED, 5,true));

        /* Create Compoment */
        int grid = materialGrid.getColumns() * materialGrid.getRows();
        for (int i = 1; i < grid+1; i++) {
            int dummyCount = i;
            if (dummyCount >= jsonObject.size()){
                dummyCount = 0;
            }
            materialObject = (JSONObject) jsonObject.get(filename+"_"+(dummyCount));

            /* material DisplayName */
            JTextField materialNameField = new JTextField();
            materialNameField.setEditable(false);
            materialNameField.setText(String.valueOf(materialObject.get("name")));
            materialNameField.setHorizontalAlignment(JTextField.CENTER);
            materialNameField.setPreferredSize(new Dimension(100, 20));

            /* material Icon */
            String iconpath = "src/image/icon/material/";
            ImageIcon materialIcon = new ImageIcon(iconpath + materialObject.get("icon"));
            JButton materialButtons = new JButton(materialIcon);
            materialButtons.setBackground(Color.WHITE);
            materialButtons.setBorderPainted(false);
            materialButtons.setToolTipText(String.valueOf(materialObject.get("name")));
            materialButtons.setPreferredSize(new Dimension(101,102));
            materialButtons.addActionListener(new Selection());
            if (dummyCount==0){
                materialButtons.setEnabled(false);
            }

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

        if (filename.equals("mineral")){
            materialName.setBackground(Color.decode("#CCCCFF"));
        }else if (filename.equals("plant")){
            materialName.setBackground(Color.decode("#BBFFBB"));

        }
        materialName.setHorizontalAlignment(JTextField.CENTER);
        materialName.setEditable(false);
        materialName.setFont(new Font(materialName.getFont().getFontName(), Font.BOLD, 20));

        material_Panel.add(materialName, BorderLayout.NORTH);
        material_Panel.add(materiallistPanel, BorderLayout.CENTER);

        return material_Panel;
    }

    private class Generate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            actionRecipe.setEssence(actionEvent.getEssence());
            actionEvent.impurity = 0;
            actionRecipe.Recipe();
            resultTextArea.setText(resultTextArea.getText() + actionEvent.getEssence().toString() + "\n");
            actionEvent.clearEssence();
        }
    }

    private class Selection implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton button = (JButton) ae.getSource();
            String materialname = button.getIcon().toString();

            actionEvent.SelectEvent(materialname);
            if (!actionEvent.getEvent().equals("")){
                resultTextArea.setText(resultTextArea.getText() + "[EVENT]:: Select <" + actionEvent.getEvent() + ">\n");
            }

        }
    }

}