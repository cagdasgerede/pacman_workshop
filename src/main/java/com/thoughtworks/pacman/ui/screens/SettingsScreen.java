package com.thoughtworks.pacman.ui.screens;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.ImageLoader;
import com.thoughtworks.pacman.ui.Screen;
import javax.swing.JComboBox;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;

public class SettingsScreen extends JFrame implements ActionListener {

   JPanel panel;
   JLabel message, up_label, down_label, right_label, left_label, pacman_label,speed_label, ghostSpeed_label, ghost_color;
   JButton submit, cancel;




   SettingsScreen() {



      //ghost color
      ghost_color = new JLabel();
      ghost_color.setText("Ghosts Color");

      //Player Speed
      speed_label = new JLabel();
      speed_label.setText("Player Speed");

      //Ghost Speed
      ghostSpeed_label = new JLabel();
      ghostSpeed_label.setText("Ghost Speed");

      //Enter player shape
      pacman_label = new JLabel();
      pacman_label.setText("Pacman Shape");

      //Enter Key
      up_label    = new JLabel();
      up_label.setText("Up Key :");

      down_label  = new JLabel();
      down_label.setText("Down Key :");

      left_label  = new JLabel();
      left_label.setText("Left Key :");

      right_label = new JLabel();
      right_label.setText("Right Key :");

      String[] choices      = { "Num8","Num6", "Num2","Num4","UP KEY","RIGHT KEY","LEFT KEY","DOWN KEY"};

      String[] pacmanShapes = { "triangle", "square", "oval", "circle"};

      String[] pacmanSpeed  = { "low", "medium", "high"};

      String[] ghostColors  = { "red", "blue", "orange", "pink"};


      final JComboBox<String> cb_up = new JComboBox<String>(choices);
      final JComboBox<String> cb_down = new JComboBox<String>(choices);
      final JComboBox<String> cb_left = new JComboBox<String>(choices);
      final JComboBox<String> cb_right = new JComboBox<String>(choices);


      final JComboBox<String> ps = new JComboBox<String>(pacmanShapes);

      final JComboBox<String> pSpeed = new JComboBox<String>(pacmanSpeed);

      final JComboBox<String> gSpeed = new JComboBox<String>(pacmanSpeed);

      final JComboBox<String> gColor = new JComboBox<String>(ghostColors);


      // Submit
      submit = new JButton("SUBMIT");
      panel = new JPanel(new GridLayout(14, 3));


      gColor.setVisible(true);
      panel.add(ghost_color);
      panel.add(gColor);

      gSpeed.setVisible(true);
      panel.add(ghostSpeed_label);
      panel.add(gSpeed);

      pSpeed.setVisible(true);
      panel.add(speed_label);
      panel.add(pSpeed);

      ps.setVisible(true);
      panel.add(pacman_label);
      panel.add(ps);

      cb_up.setVisible(true);
      panel.add(up_label);
      panel.add(cb_up);

      cb_down.setVisible(true);
      panel.add(down_label);
      panel.add(cb_down);

      cb_left.setVisible(true);
      panel.add(left_label);
      panel.add(cb_left);

      cb_right.setVisible(true);
      panel.add(right_label);
      panel.add(cb_right);

      message = new JLabel();
      panel.add(message);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle(" SETTINGS PAGE  ");
      setSize(450,350);
      setVisible(true);
   }
   public static void main(String[] args) {
      new SettingsScreen();
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      Component[] components = panel.getComponents();

      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

         for (Component comp : components) {
            if (comp instanceof JComboBox) {
               JComboBox textField = (JComboBox) comp;
               writer.write( textField.getSelectedItem() + "\n");
            }
         }      

         writer.close();

         Window win = SwingUtilities.getWindowAncestor(components[0]);
         win.dispose();


      }catch (IOException e) {
         System.out.println("error");
      }
   }
}