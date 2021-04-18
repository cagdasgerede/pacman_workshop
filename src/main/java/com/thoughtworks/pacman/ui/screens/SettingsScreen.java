package com.thoughtworks.pacman.ui.screens;

import java.awt.Color;
import java.awt.Image;
import java.awt.TextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.thoughtworks.pacman.core.Settings;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsScreen extends JFrame implements ActionListener{
   private static final Image SETTINGS_SCREEN_IMAGE = ImageLoader.loadImage(Screen.class, "settingsScreen.png");
   private static final int WIDTH_SCREEN = 500;
   private static final int HEIGHT_SCREEN = 547;
   
   private static final int HEIGHT_BUTTON = 24;
   private static final int HEIGHT_LABEL_SPACE = 10+HEIGHT_BUTTON;
   private static final int HEIGHT_INFOTEXT = HEIGHT_BUTTON;
   private static final int HEIGHT_TEXT = HEIGHT_BUTTON;
   private static final int WIDTH_BUTTON = 24;
   private static final int WIDTH_SPACE = 10;
   private static final int WIDTH_LABEL_SPACE = WIDTH_SPACE+WIDTH_BUTTON;
   private static final int WIDTH_INFOTEXT = 4*WIDTH_BUTTON;
   private static final int WIDTH_TEXT = 3*WIDTH_BUTTON;

   private static final int MARGIN_HEIGHT_SCREEN = 120;
   private static final int MARGIN_HEIGHT_INFOTEXT = MARGIN_HEIGHT_SCREEN;
   private static final int MARGIN_WIDTH_SCREEN = 95;
   private static final int MARGIN_WIDTH_INFOTEXT = MARGIN_WIDTH_SCREEN;
   private static final int MARGIN_WIDTH_PREBUTTON = MARGIN_WIDTH_INFOTEXT+WIDTH_INFOTEXT+WIDTH_LABEL_SPACE;
   private static final int MARGIN_WIDTH_TEXT = MARGIN_WIDTH_PREBUTTON+WIDTH_LABEL_SPACE;
   private static final int MARGIN_WIDTH_NEXTBUTTON = MARGIN_WIDTH_TEXT+WIDTH_TEXT+WIDTH_SPACE;

   private static final String UP_PNG_PATH = "upButton.png";
   private static final String DOWN_PNG_PATH = "downButton.png";
   private static final String NEXT_PNG_PATH = "nextButton.png";
   private static final String PREVIOUS_PNG_PATH = "previousButton.png";

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
   private JPanel pacmPanel = new JPanel();
  // private Image pacmanCustom_Image = ImageLoader.loadImage(Screen.class, UP_PNG_PATH);
  // private Image ghostCustom_Image = ImageLoader.loadImage(Screen.class, UP_PNG_PATH);
   private Settings settings;
   private JTextField pacmanSpeedInfo_Text,ghostSpeedInfo_Text,upKeyInfo_Text,downKeyInfo_Text,rightKeyInfo_Text,leftKeyInfo_Text,pacmanColorInfo_Text,ghostColorInfo_Text;
   private JTextField pacmanSpeed_Text,ghostSpeed_Text,upKey_Text,downKey_Text,rightKey_Text,leftKey_Text;
   private Color[] pacmanColorOptions = {Color.yellow,Color.red,Color.blue,Color.green,new Color(255,204,51),new Color(153,153,153),new Color(102,51,0),new Color(102,0,153)};
   private String[] gameKeyList = {"UpArrow","DownArrow","RightArrow","LefttArrow","W","S","A","D","X","C","V","Num8","Num4","Num2","Num6"};
   private int cursorColor=0,upKeyCursor=0,downKeyCursor=1,rightKeyCursor=2,leftKeyCursor=3;
   private int pacmanSpeed=20,ghostSpeed=20;

   SettingsScreen() {
    super("Settings Menu");
    setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
    settings = new Settings();
    this.setContentPane(new JLabel(new ImageIcon(SETTINGS_SCREEN_IMAGE)));
    if(settings.buildCorrect())
       buildSettings(settings);
       
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
    this.add(ghostSpeedUp_Button);
    this.add(ghostSpeedDown_Button);
    this.add(pacmanColorNext_Button);
    this.add(pacmanColorPrevious_Button);
    this.add(ghostColorNext_Button);
    this.add(ghostColorPrevious_Button);
    this.add(upKeyNext_Button);
    this.add(upKeyPrevious_Button);
    this.add(downKeyNext_Button);
    this.add(downKeyPrevious_Button);
    this.add(rightKeyNext_Button);
    this.add(rightKeyPrevious_Button);
    this.add(leftKeyNext_Button);
    this.add(leftKeyPrevious_Button);

    pacmanSpeed_Text = new JTextField(pacmanSpeed+"");
    ghostSpeed_Text = new JTextField(ghostSpeed+"");

    pacmanSpeed_Text.setBounds(MARGIN_WIDTH_TEXT+WIDTH_BUTTON,(MARGIN_HEIGHT_SCREEN),WIDTH_BUTTON,HEIGHT_BUTTON);
    ghostSpeed_Text.setBounds(MARGIN_WIDTH_TEXT+WIDTH_BUTTON,(MARGIN_HEIGHT_SCREEN+HEIGHT_LABEL_SPACE),WIDTH_BUTTON,HEIGHT_BUTTON);
    pacmPanel.setBounds(MARGIN_WIDTH_TEXT+(WIDTH_BUTTON-(WIDTH_SPACE/2)),(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*2)),WIDTH_BUTTON,HEIGHT_BUTTON);
    pacmPanel.setBackground(pacmanColorOptions[cursorColor]);

    pacmanSpeed_Text.setEditable(false);
    ghostSpeed_Text.setEditable(false);
    pacmanSpeed_Text.setForeground(Color.WHITE);
    ghostSpeed_Text.setForeground(Color.WHITE);
    pacmanSpeed_Text.setOpaque(false);
    ghostSpeed_Text.setOpaque(false);
    pacmanSpeed_Text.setBorder(null);
    ghostSpeed_Text.setBorder(null);

    this.add(pacmanSpeed_Text);
    this.add(ghostSpeed_Text);
    this.add(pacmPanel);

   /*pacmanColor = new ImageIcon("0000000");*/
   /*ghostColor = new ImageIcon("0000000");*/

    upKey_Text = new JTextField(gameKeyList[upKeyCursor]);
    downKey_Text = new JTextField(gameKeyList[downKeyCursor]);
    rightKey_Text = new JTextField(gameKeyList[rightKeyCursor]);
    leftKey_Text = new JTextField(gameKeyList[leftKeyCursor]);

    upKey_Text.setBounds(MARGIN_WIDTH_TEXT,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*4)),WIDTH_TEXT,HEIGHT_TEXT);
    downKey_Text.setBounds(MARGIN_WIDTH_TEXT,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*5)),WIDTH_TEXT,HEIGHT_TEXT);
    rightKey_Text.setBounds(MARGIN_WIDTH_TEXT,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*6)),WIDTH_TEXT,HEIGHT_TEXT);
    leftKey_Text.setBounds(MARGIN_WIDTH_TEXT,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*7)),WIDTH_TEXT,HEIGHT_TEXT);

    upKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    downKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    rightKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    leftKey_Text.setAlignmentX(TextArea.CENTER_ALIGNMENT);
    upKey_Text.setBorder(null);
    downKey_Text.setBorder(null);
    rightKey_Text.setBorder(null);
    leftKey_Text.setBorder(null);
    upKey_Text.setForeground(Color.WHITE);
    downKey_Text.setForeground(Color.WHITE);
    rightKey_Text.setForeground(Color.WHITE);
    leftKey_Text.setForeground(Color.WHITE);
    upKey_Text.setEditable(false);
    downKey_Text.setEditable(false);
    rightKey_Text.setEditable(false);
    leftKey_Text.setEditable(false);
    upKey_Text.setOpaque(false);
    downKey_Text.setOpaque(false);
    rightKey_Text.setOpaque(false);
    leftKey_Text.setOpaque(false);

    this.add(upKey_Text);
    this.add(downKey_Text);
    this.add(rightKey_Text);
    this.add(leftKey_Text);

    this.add(apply_Button);
    this.add(cancel_Button);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
         
        }
    });
    setVisible(true);
    setLocation(750,100);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   public static void main(String[] args) {  
        new SettingsScreen();
   }
   
   @Override
   public void actionPerformed(ActionEvent ae) {
      System.out.println("ae "+ae.getActionCommand());
      String command = ae.getActionCommand();
      if(command.equals("pacmanSpeedUp_Button") && pacmanSpeed+10<=100)
         {
            pacmanSpeed+=10;
            pacmanSpeed_Text.setText(pacmanSpeed+"");
         }
      else if(command.equals("pacmanSpeedDown_Button") && pacmanSpeed-10>=0)
         {
            pacmanSpeed-=10;
            pacmanSpeed_Text.setText(pacmanSpeed+"");
         }
      else if(command.equals("ghostSpeedUp_Button") && ghostSpeed+10<=100)
         {
            ghostSpeed+=10;
            ghostSpeed_Text.setText(ghostSpeed+"");
         }
      else if(command.equals("ghostSpeedDown_Button") && ghostSpeed-10>=0)
         {
            ghostSpeed-=10;
            ghostSpeed_Text.setText(ghostSpeed+"");
         }
      else if(command.equals("pacmanColorNext_Button")){
         if(cursorColor==pacmanColorOptions.length-1)
            cursorColor=0;
         else cursorColor++;
            pacmPanel.setBackground(pacmanColorOptions[cursorColor]);
         }
      else if(command.equals("pacmanColorPrevious_Button")){
         if(cursorColor==0)
            cursorColor=pacmanColorOptions.length-1;
         else cursorColor--;
            pacmPanel.setBackground(pacmanColorOptions[cursorColor]);
         }
      else if(command.equals("ghostColorNext_Button")){
            //
         }
      else if(command.equals("ghostColorPrevious_Button")){
            //
         }
      else if(command.equals("APPLY")){
         try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
               writer.write(pacmanSpeed+" ");
               writer.write(ghostSpeed+" ");
               writer.write(cursorColor+" ");
               writer.write((upKeyCursor)+" ");
               writer.write((downKeyCursor)+" ");
               writer.write((rightKeyCursor)+" ");
               writer.write((leftKeyCursor)+" ");
               settings.crateCRC(pacmanSpeed+ghostSpeed+cursorColor+upKeyCursor+downKeyCursor+rightKeyCursor+leftKeyCursor);
               writer.write(settings.getCRC()+" ");
               writer.close();
            
            }catch (IOException e) {
               e.printStackTrace();  
            }
      }
      else if(command.equals("CANCEL")){
            
      }
      else{
            do{
               if(command.equals("upKeyNext_Button"))
                  if(upKeyCursor==gameKeyList.length-1) upKeyCursor=0;
                  else upKeyCursor++;
               else if(command.equals("downKeyNext_Button"))
                  if(downKeyCursor==gameKeyList.length-1) downKeyCursor=0;
                  else downKeyCursor++;
               else if(command.equals("rightKeyNext_Button"))
                  if(rightKeyCursor==gameKeyList.length-1) rightKeyCursor=0;
                  else rightKeyCursor++;
               else if(command.equals("leftKeyNext_Button"))
                  if(leftKeyCursor==gameKeyList.length-1) leftKeyCursor=0;
                  else leftKeyCursor++;
               else if(command.equals("upKeyPrevious_Button"))
                  if(upKeyCursor==0) upKeyCursor=gameKeyList.length-1;
                  else upKeyCursor--;
               else if(command.equals("downKeyPrevious_Button"))
                  if(downKeyCursor==0) downKeyCursor=gameKeyList.length-1;
                  else downKeyCursor--;
               else if(command.equals("rightKeyPrevious_Button"))
                  if(rightKeyCursor==0) rightKeyCursor=gameKeyList.length-1;
                  else rightKeyCursor--;
               else if(command.equals("leftKeyPrevious_Button"))
                  if(leftKeyCursor==0) leftKeyCursor=gameKeyList.length-1;
                  else leftKeyCursor--;

            }while(upKeyCursor==downKeyCursor || upKeyCursor==rightKeyCursor || upKeyCursor==leftKeyCursor || rightKeyCursor==leftKeyCursor || downKeyCursor==leftKeyCursor || rightKeyCursor==downKeyCursor);
            if(command.equals("upKeyNext_Button") || command.equals("upKeyPrevious_Button"))
               upKey_Text.setText(gameKeyList[upKeyCursor]+"");
            else if(command.equals("downKeyNext_Button") || command.equals("downKeyPrevious_Button"))
               downKey_Text.setText(gameKeyList[downKeyCursor]+"");
            else if(command.equals("rightKeyNext_Button") || command.equals("rightKeyPrevious_Button"))
               rightKey_Text.setText(gameKeyList[rightKeyCursor]+"");
            else if(command.equals("leftKeyNext_Button") || command.equals("leftKeyPrevious_Button"))
               leftKey_Text.setText(gameKeyList[leftKeyCursor]+"");
         }
      }


   private void InfoTexts(){
      pacmanSpeedInfo_Text = new JTextField("Pacman Speed");
      ghostSpeedInfo_Text = new JTextField("Ghost Speed");
      pacmanColorInfo_Text = new JTextField("Pacman Color");
      ghostColorInfo_Text = new JTextField("Ghost Color");
      upKeyInfo_Text = new JTextField("Up Key");
      downKeyInfo_Text = new JTextField("Down Key");
      rightKeyInfo_Text = new JTextField("Right Key");
      leftKeyInfo_Text = new JTextField("Left Key");

      pacmanSpeedInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,MARGIN_HEIGHT_INFOTEXT,WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      ghostSpeedInfo_Text .setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+HEIGHT_LABEL_SPACE),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      pacmanColorInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*2)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      ghostColorInfo_Text .setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*3)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      upKeyInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*4)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      downKeyInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*5)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      rightKeyInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*6)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);
      leftKeyInfo_Text.setBounds(MARGIN_WIDTH_INFOTEXT,(MARGIN_HEIGHT_INFOTEXT+(HEIGHT_LABEL_SPACE*7)),WIDTH_INFOTEXT,HEIGHT_INFOTEXT);

      pacmanSpeedInfo_Text.setEditable(false);
      ghostSpeedInfo_Text.setEditable(false);
      pacmanColorInfo_Text.setEditable(false);
      ghostColorInfo_Text.setEditable(false);
      upKeyInfo_Text.setEditable(false);
      downKeyInfo_Text.setEditable(false);
      rightKeyInfo_Text.setEditable(false);
      leftKeyInfo_Text.setEditable(false);

      pacmanSpeedInfo_Text.setBorder(null);
      ghostSpeedInfo_Text.setBorder(null);
      pacmanColorInfo_Text.setBorder(null);
      ghostColorInfo_Text.setBorder(null);
      upKeyInfo_Text.setBorder(null);
      downKeyInfo_Text.setBorder(null);
      rightKeyInfo_Text.setBorder(null);
      leftKeyInfo_Text.setBorder(null);

      pacmanSpeedInfo_Text.setForeground(Color.WHITE);
      pacmanSpeedInfo_Text.setOpaque(false);
      ghostSpeedInfo_Text.setForeground(Color.WHITE);
      ghostSpeedInfo_Text.setOpaque(false);
      pacmanColorInfo_Text.setForeground(Color.WHITE);
      pacmanColorInfo_Text.setOpaque(false);
      ghostColorInfo_Text.setForeground(Color.WHITE);
      ghostColorInfo_Text.setOpaque(false);
      upKeyInfo_Text.setForeground(Color.WHITE);
      upKeyInfo_Text.setOpaque(false);
      downKeyInfo_Text.setForeground(Color.WHITE);
      downKeyInfo_Text.setOpaque(false);
      rightKeyInfo_Text.setForeground(Color.WHITE);
      rightKeyInfo_Text.setOpaque(false);
      leftKeyInfo_Text.setForeground(Color.WHITE);
      leftKeyInfo_Text.setOpaque(false);
   }

private void setButtons() {
    //String upButtonPNG = this.getClass().getClassLoader().getResource(UP_PNG_PATH).getFile();
    setButtonDefaults(pacmanSpeedUp_Button,0,"pacmanSpeedUp_Button");
    setButtonDefaults(ghostSpeedUp_Button, 0,"ghostSpeedUp_Button");

    setButtonDefaults(pacmanSpeedDown_Button, 1,"pacmanSpeedDown_Button");
    setButtonDefaults(ghostSpeedDown_Button, 1,"ghostSpeedDown_Button");

    
    apply_Button.setForeground(Color.WHITE);
    apply_Button.setOpaque(false);
    apply_Button.setContentAreaFilled(false);
    apply_Button.setFocusable(false);

    cancel_Button.setForeground(Color.WHITE);
    cancel_Button.setOpaque(false);
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

    pacmanSpeedUp_Button.setBounds(MARGIN_WIDTH_PREBUTTON, MARGIN_HEIGHT_SCREEN, WIDTH_BUTTON, HEIGHT_BUTTON);
    ghostSpeedUp_Button.setBounds(MARGIN_WIDTH_PREBUTTON, MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE), WIDTH_BUTTON, HEIGHT_BUTTON);
    pacmanColorPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*2),WIDTH_BUTTON,HEIGHT_BUTTON);
    ghostColorPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*3),WIDTH_BUTTON,HEIGHT_BUTTON);
    upKeyPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*4),WIDTH_BUTTON,HEIGHT_BUTTON);
    downKeyPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*5),WIDTH_BUTTON,HEIGHT_BUTTON);
    rightKeyPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*6),WIDTH_BUTTON,HEIGHT_BUTTON);
    leftKeyPrevious_Button.setBounds(MARGIN_WIDTH_PREBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*7),WIDTH_BUTTON,HEIGHT_BUTTON);

    pacmanSpeedDown_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,MARGIN_HEIGHT_SCREEN,WIDTH_BUTTON,HEIGHT_BUTTON);
    ghostSpeedDown_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE),WIDTH_BUTTON,HEIGHT_BUTTON);
    pacmanColorNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*2)),WIDTH_BUTTON,HEIGHT_BUTTON);
    ghostColorNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*3)),WIDTH_BUTTON,HEIGHT_BUTTON);
    upKeyNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*4)),WIDTH_BUTTON,HEIGHT_BUTTON);
    downKeyNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*5)),WIDTH_BUTTON,HEIGHT_BUTTON);
    rightKeyNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*6)),WIDTH_BUTTON,HEIGHT_BUTTON);
    leftKeyNext_Button.setBounds(MARGIN_WIDTH_NEXTBUTTON,(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*7)),WIDTH_BUTTON,HEIGHT_BUTTON);

    apply_Button.setBounds((MARGIN_WIDTH_INFOTEXT+WIDTH_LABEL_SPACE),(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*8)), WIDTH_INFOTEXT, HEIGHT_BUTTON);
    cancel_Button.setBounds((MARGIN_WIDTH_TEXT-WIDTH_SPACE),(MARGIN_HEIGHT_SCREEN+(HEIGHT_LABEL_SPACE*8)), WIDTH_INFOTEXT, HEIGHT_BUTTON);

    pacmanSpeedUp_Button.addActionListener(this);
    pacmanSpeedDown_Button.addActionListener(this);
    ghostSpeedUp_Button.addActionListener(this);
    ghostSpeedDown_Button.addActionListener(this);
    pacmanColorNext_Button.addActionListener(this);
    pacmanColorPrevious_Button.addActionListener(this);
    ghostColorNext_Button.addActionListener(this);
    ghostColorPrevious_Button.addActionListener(this);
    upKeyNext_Button.addActionListener(this);
    upKeyPrevious_Button.addActionListener(this);
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

      public void buildSettings(Settings settings) {
         ghostSpeed = settings.getGhostSpeed();
         cursorColor = settings.getPacmanColorIndex();
         pacmanSpeed = settings.getPacmanSpeed();
         int[] keyIndex = settings.getKeyIndex();
         upKeyCursor = keyIndex[0];
         downKeyCursor = keyIndex[1];
         rightKeyCursor = keyIndex[2];
         leftKeyCursor = keyIndex[3];
      }
   }