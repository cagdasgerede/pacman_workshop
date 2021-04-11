package com.thoughtworks.pacman.ui.screens;

import java.awt.Color;
import java.awt.Image;
import java.awt.TextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingsScreen extends JFrame implements ActionListener{

   private static final int WIDTH = 300;
   private static final int HEIGHT = 500;

   private static final int BUTTON_WIDTH = 25;
   private static final int BUTTON_HEIGHT = 25;

   private static final int HIGH_LABEL_SPACE = 10+BUTTON_HEIGHT;
   private static final int WEIGHT_LABEL_SPACE = 25+BUTTON_WIDTH;

   private JButton pacmanSpeedUp_Button = new JButton();
   private JButton pacmanSpeedDown_Button = new JButton();
   private JButton ghostSpeedUp_Button = new JButton();
   private JButton ghostSpeedDown_Button = new JButton();
   private JButton pacmanColorNext_Button = new JButton();
   private JButton pacmanColorPrevious_Button = new JButton();
   private JButton ghostColorNext_Button = new JButton();
   private JButton ghostColorPrevious_Button = new JButton();
   private JButton upKeyNext_Button = new JButton();
   private JButton upKeyPrevious_Button = new JButton();
   private JButton downKeyNext_Button = new JButton();
   private JButton downKeyPrevious_Button = new JButton();
   private JButton rightKeyNext_Button = new JButton();
   private JButton rightKeyPrevious_Button = new JButton();
   private JButton leftKeyNext_Button = new JButton();
   private JButton leftKeyPrevious_Button = new JButton();

   private JButton apply_Button = new JButton("APPLY");
   private JButton cancel_Button = new JButton("CANCEL");
   private static final String UP_PNG_PATH = "upButton.png";
   private static final String DOWN_PNG_PATH = "downButton.png";
   private static final String NEXT_PNG_PATH = "nextButton.png";
   private static final String PREVIOUS_PNG_PATH = "previousButton.png";
   private JPanel pacmPanel = new JPanel();
  // private Image pacmanCustom_Image = ImageLoader.loadImage(Screen.class, UP_PNG_PATH);
  // private Image ghostCustom_Image = ImageLoader.loadImage(Screen.class, UP_PNG_PATH);
   private JTextField pacmanSpeedInfo_Text,ghostSpeedInfo_Text,upKeyInfo_Text,downKeyInfo_Text,rightKeyInfo_Text,leftKeyInfo_Text,pacmanColorInfo_Text,ghostColorInfo_Text;
   private JTextField pacmanSpeed_Text,ghostSpeed_Text,upKey_Text,downKey_Text,rightKey_Text,leftKey_Text;
   private Color[] pacmanColorOptions = {Color.yellow,Color.red,Color.blue,Color.green,new Color(255,204,51),new Color(153,153,153),new Color(102,51,0),new Color(102,0,153)};
   private String[] gameKeyList = {"UpArrow","DownArrow","RightArrow","LefttArrow","W","S","A","D","X","C","V","Num8","Num4","Num2","Num6"};
   private int cursorColor=0,upKeyCursor=0,downKeyCursor=1,rightKeyCursor=2,leftKeyCursor=3;
   private int pacmanSpeed=0,ghostSpeed=0;
  // private ImageIcon ghostColor;

   SettingsScreen() {
    super("Settings Menu");
    setSize(WIDTH, HEIGHT);
    InfoTexts();
    this.add(pacmanSpeedInfo_Text);
    this.add(ghostSpeedInfo_Text);
    this.add(pacmanColorInfo_Text);
    this.add(ghostColorInfo_Text);
    this.add(upKeyInfo_Text);
    this.add(downKeyInfo_Text);
    this.add(rightKeyInfo_Text);
    this.add(leftKeyInfo_Text);

    setButtons();
    this.add(pacmanSpeedUp_Button);
    this.add(pacmanSpeedDown_Button);
    pacmanSpeed_Text = new JTextField(pacmanSpeed);
    pacmanSpeed_Text.setBounds(220,15,BUTTON_WIDTH,BUTTON_HEIGHT);
    pacmanSpeed_Text.setEditable(false);
    this.add(pacmanSpeed_Text);

    this.add(ghostSpeedUp_Button);
    this.add(ghostSpeedDown_Button);
    ghostSpeed_Text = new JTextField(ghostSpeed);
    ghostSpeed_Text.setBounds(220,15+HIGH_LABEL_SPACE,BUTTON_WIDTH,BUTTON_HEIGHT);
    ghostSpeed_Text.setEditable(false);
    this.add(ghostSpeed_Text);

    this.add(pacmanColorNext_Button);
    this.add(pacmanColorPrevious_Button);

    pacmPanel.setBounds(175,15+(HIGH_LABEL_SPACE*2),BUTTON_WIDTH,BUTTON_HEIGHT);
    pacmPanel.setBackground(pacmanColorOptions[cursorColor]);
    this.add(pacmPanel);
   /*pacmanColor = new ImageIcon("0000000");*/

    this.add(ghostColorNext_Button);
    this.add(ghostColorPrevious_Button);

    /*ghostColor = new ImageIcon("0000000");*/

    this.add(upKeyNext_Button);
    this.add(upKeyPrevious_Button);
    this.add(downKeyNext_Button);
    this.add(downKeyPrevious_Button);
    this.add(rightKeyNext_Button);
    this.add(rightKeyPrevious_Button);
    this.add(leftKeyNext_Button);
    this.add(leftKeyPrevious_Button);
    upKey_Text = new JTextField(gameKeyList[upKeyCursor]);
    upKey_Text.setBounds(155,15+(HIGH_LABEL_SPACE*4),WEIGHT_LABEL_SPACE+20,BUTTON_HEIGHT);
    upKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    upKey_Text.setEditable(false);
    downKey_Text = new JTextField(gameKeyList[downKeyCursor]);
    downKey_Text.setBounds(155,15+(HIGH_LABEL_SPACE*5),WEIGHT_LABEL_SPACE+20,BUTTON_HEIGHT);
    downKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    downKey_Text.setEditable(false);
    rightKey_Text = new JTextField(gameKeyList[rightKeyCursor]);
    rightKey_Text.setBounds(155,15+(HIGH_LABEL_SPACE*6),WEIGHT_LABEL_SPACE+20,BUTTON_HEIGHT);
    rightKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    rightKey_Text.setEditable(false);
    leftKey_Text = new JTextField(gameKeyList[leftKeyCursor]);
    leftKey_Text.setBounds(155,15+(HIGH_LABEL_SPACE*7),WEIGHT_LABEL_SPACE+20,BUTTON_HEIGHT);
    leftKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    leftKey_Text.setEditable(false);
    this.add(upKey_Text);
    this.add(downKey_Text);
    this.add(rightKey_Text);
    this.add(leftKey_Text);

    this.add(apply_Button);
    this.add(cancel_Button);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
       //     Board.setEditMenuActive(false);
        }
    });
    setLayout(null);
    setVisible(true);
    setLocation(750,100);
    toFront();

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

   }
   public static void main(String[] args) {  
        new SettingsScreen();
   }
   private void InfoTexts(){
      pacmanSpeedInfo_Text = new JTextField("Pacman Speed");
      pacmanSpeedInfo_Text.setBounds(15,15,BUTTON_WIDTH*4,BUTTON_HEIGHT);
      pacmanSpeedInfo_Text.setEditable(false);

      ghostSpeedInfo_Text = new JTextField("Ghost Speed");
      ghostSpeedInfo_Text.setBounds(15,15+HIGH_LABEL_SPACE,BUTTON_WIDTH*4,BUTTON_HEIGHT);
      ghostSpeedInfo_Text.setEditable(false);

      pacmanColorInfo_Text = new JTextField("Pacman Color");
      pacmanColorInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*2),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      pacmanColorInfo_Text.setEditable(false);

      ghostColorInfo_Text = new JTextField("Ghost Color");
      ghostColorInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*3),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      ghostColorInfo_Text.setEditable(false);

      upKeyInfo_Text = new JTextField("Up Key");
      upKeyInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*4),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      upKeyInfo_Text.setEditable(false);

      downKeyInfo_Text = new JTextField("Down Key");
      downKeyInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*5),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      downKeyInfo_Text.setEditable(false);

      rightKeyInfo_Text = new JTextField("Right Key");
      rightKeyInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*6),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      rightKeyInfo_Text.setEditable(false);

      leftKeyInfo_Text = new JTextField("Left Key");
      leftKeyInfo_Text.setBounds(15,15+(HIGH_LABEL_SPACE*7),BUTTON_WIDTH*4,BUTTON_HEIGHT);
      leftKeyInfo_Text.setEditable(false);
   }

private void setButtons() {
    //String upButtonPNG = this.getClass().getClassLoader().getResource(UP_PNG_PATH).getFile();
    setButtonDefaults(pacmanSpeedUp_Button,0,"pacmanSpeedUp_Button");
    setButtonDefaults(ghostSpeedUp_Button, 0,"ghostSpeedUp_Button");

    setButtonDefaults(pacmanSpeedDown_Button, 1,"pacmanSpeedDown_Button");
    setButtonDefaults(ghostSpeedDown_Button, 1,"ghostSpeedDown_Button");

    apply_Button.setContentAreaFilled(false);
    apply_Button.setFocusable(false);

    cancel_Button.setContentAreaFilled(false);
    cancel_Button.setFocusable(false);

    setButtonDefaults(pacmanColorNext_Button, 2,"pacmanColorNext_Button");
    setButtonDefaults(ghostColorNext_Button, 2,"ghostColorNext_Button");
    setButtonDefaults(pacmanColorPrevious_Button, 3,"pacmanColorPrevious_Button");
    setButtonDefaults(ghostColorPrevious_Button, 3,"ghostColorPrevious_Button");

    setButtonDefaults(upKeyNext_Button, 2,"upKeyNext_Button");
    setButtonDefaults(downKeyNext_Button, 2,"downKeyNext_Button");
    setButtonDefaults(rightKeyNext_Button, 2,"rightKeyNext_Button");
    setButtonDefaults(leftKeyNext_Button, 2,"leftKeyNext_Button");

    setButtonDefaults(upKeyPrevious_Button, 3,"upKeyPrevious_Button");
    setButtonDefaults(downKeyPrevious_Button, 3,"downKeyPrevious_Button");
    setButtonDefaults(rightKeyPrevious_Button, 3,"rightKeyPrevious_Button");
    setButtonDefaults(leftKeyPrevious_Button, 3 ,"leftKeyPrevious_Button");

    pacmanSpeedUp_Button.setBounds(130, 15, BUTTON_WIDTH, BUTTON_HEIGHT);
    pacmanSpeedDown_Button.setBounds(130+WEIGHT_LABEL_SPACE,15,BUTTON_WIDTH,BUTTON_HEIGHT);
    ghostSpeedUp_Button.setBounds(130, 15+HIGH_LABEL_SPACE, BUTTON_WIDTH, BUTTON_HEIGHT);
    ghostSpeedDown_Button.setBounds(130+WEIGHT_LABEL_SPACE,15+HIGH_LABEL_SPACE,BUTTON_WIDTH,BUTTON_HEIGHT);

    pacmanColorNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*2),BUTTON_WIDTH,BUTTON_HEIGHT);
    pacmanColorPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*2),BUTTON_WIDTH,BUTTON_HEIGHT);
    
    ghostColorNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*3),BUTTON_WIDTH,BUTTON_HEIGHT);
    ghostColorPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*3),BUTTON_WIDTH,BUTTON_HEIGHT);

    upKeyNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*4),BUTTON_WIDTH,BUTTON_HEIGHT);
    upKeyPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*4),BUTTON_WIDTH,BUTTON_HEIGHT);
    downKeyNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*5),BUTTON_WIDTH,BUTTON_HEIGHT);
    downKeyPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*5),BUTTON_WIDTH,BUTTON_HEIGHT);
    rightKeyNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*6),BUTTON_WIDTH,BUTTON_HEIGHT);
    rightKeyPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*6),BUTTON_WIDTH,BUTTON_HEIGHT);
    leftKeyNext_Button.setBounds(130+(WEIGHT_LABEL_SPACE*2),15+(HIGH_LABEL_SPACE*7),BUTTON_WIDTH,BUTTON_HEIGHT);
    leftKeyPrevious_Button.setBounds(130,15+(HIGH_LABEL_SPACE*7),BUTTON_WIDTH,BUTTON_HEIGHT);

    apply_Button.setBounds(50, 400, 80, 50);
    cancel_Button.setBounds(140, 400, 80, 50);

    pacmanSpeedUp_Button.addActionListener(this);
    pacmanSpeedDown_Button.addActionListener(this);
    ghostSpeedUp_Button.addActionListener(this);
    ghostSpeedDown_Button.addActionListener(this);
    pacmanColorNext_Button.addActionListener(this);
    pacmanColorPrevious_Button.addActionListener(this);
    ghostColorNext_Button.addActionListener(this);
    ghostColorPrevious_Button.addActionListener(this);
    upKeyNext_Button.addActionListener(this);
    upKeyNext_Button.addActionListener(this);
    downKeyNext_Button.addActionListener(this);
    downKeyPrevious_Button.addActionListener(this);
    rightKeyNext_Button.addActionListener(this);
    rightKeyPrevious_Button.addActionListener(this);
    leftKeyNext_Button.addActionListener(this);
    leftKeyPrevious_Button.addActionListener(this);
    apply_Button.addActionListener(this);
    cancel_Button.addActionListener(this);
}
private void setButtonDefaults(JButton button,int buttonProperty,String name){
    switch (buttonProperty) {
         case 0: button.setIcon(new ImageIcon(ImageLoader.loadImage(Screen.class, UP_PNG_PATH))); break;
         case 1: button.setIcon(new ImageIcon(ImageLoader.loadImage(Screen.class, DOWN_PNG_PATH))); break;
         case 2: button.setIcon(new ImageIcon(ImageLoader.loadImage(Screen.class, NEXT_PNG_PATH))); break;
         case 3: button.setIcon(new ImageIcon(ImageLoader.loadImage(Screen.class, PREVIOUS_PNG_PATH))); break;
         default: System.err.println("ERROR"); break;
      }
      button.setBorder(BorderFactory.createEmptyBorder());
      button.setContentAreaFilled(false);
      button.setFocusable(false);
      button.setActionCommand(name);
}
   @Override
   public void actionPerformed(ActionEvent ae) {
      String command = ae.getActionCommand();
      if(command.equals(pacmanSpeedUp_Button) && pacmanSpeed+10<=100)
         {
            pacmanSpeed+=10;
            pacmanSpeed_Text.setText(pacmanSpeed+"");
         }
      else if(command.equals(pacmanSpeedDown_Button) && pacmanSpeed-10>=0)
         {
            pacmanSpeed-=10;
            pacmanSpeed_Text.setText(pacmanSpeed+"");
         }
      else if(command.equals(ghostSpeedUp_Button) && ghostSpeed+10<=100)
         {
            ghostSpeed+=10;
            ghostSpeed_Text.setText(ghostSpeed+"");
         }
      else if(command.equals(ghostSpeedDown_Button) && ghostSpeed-10>=0)
         {
            ghostSpeed-=10;
            ghostSpeed_Text.setText(ghostSpeed+"");
         }
      else if(command.equals(pacmanColorNext_Button)){
         if(cursorColor==pacmanColorOptions.length-1)
               cursorColor=0;
         else cursorColor++;
            pacmPanel.setBackground(pacmanColorOptions[cursorColor]);
         }
      else if(command.equals(pacmanColorPrevious_Button)){
            if(cursorColor==0)
               cursorColor=pacmanColorOptions.length-1;
            else cursorColor--;
            pacmPanel.setBackground(pacmanColorOptions[cursorColor]);
         }
      else if(command.equals(upKeyNext_Button)){
         if(upKeyCursor==gameKeyList.length-1)
            upKeyCursor=0;
         else upKeyCursor++;
            upKey_Text.setText(gameKeyList[upKeyCursor]);
         }
      else if(command.equals(upKeyPrevious_Button)){
            if(upKeyCursor==0)
               upKeyCursor=gameKeyList.length-1;
            else upKeyCursor--;
            upKey_Text.setText(gameKeyList[upKeyCursor]);
         }
      }
      /*
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

         writer.close();

      }catch (IOException e) {
         e.printStackTrace();  
         
      }*/
   }