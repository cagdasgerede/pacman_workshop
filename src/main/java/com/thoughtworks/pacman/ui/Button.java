package com.thoughtworks.pacman.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextLayout;

public class Button {
    

    public Graphics2D draw(String nameOfButton, Color colorOfTheButton, Rectangle drawnRectangle, Graphics2D graphics){
        graphics.setColor(colorOfTheButton);
        graphics.fill(drawnRectangle);
        if(nameOfButton.equals("PLAY") || nameOfButton.equals("QUIT") || nameOfButton.equals("RESUME") || nameOfButton.equals("RETURN TO MAIN MENU") || nameOfButton.equals("SETTINGS")){
            graphics.setColor(Color.BLACK);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds(nameOfButton, graphics).getWidth();
            TextLayout tl = new TextLayout(nameOfButton, new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString(nameOfButton, drawnRectangle.x + drawnRectangle.width / 2 - textWidth / 2, drawnRectangle.y + drawnRectangle.height / 2 + textHeight / 2);
        }else{
            graphics.setColor(Color.WHITE);
            graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 80));
            int textWidth = (int) graphics.getFontMetrics().getStringBounds(nameOfButton, graphics).getWidth();
            TextLayout tl = new TextLayout(nameOfButton, new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                    graphics.getFontRenderContext());
            int textHeight = (int) tl.getBounds().getHeight();
            graphics.drawString(nameOfButton, drawnRectangle.x + drawnRectangle.width / 2 - textWidth / 2, drawnRectangle.y + drawnRectangle.height / 2 + textHeight / 2 -50);
        }
        return graphics;
        
       
    }

}
