package com.thoughtworks.pacman.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextLayout;

public class RectangleButton {
    
    public void draw(String nameOfButton, Color colorOfTheButton, Rectangle drawnRectangle, Graphics2D graphics){
        graphics.setColor(colorOfTheButton);
        graphics.fill(drawnRectangle);
        if(isDigit(nameOfButton)){
            drawScore(nameOfButton, drawnRectangle, graphics);
           
        }else{
            drawButtons(nameOfButton, drawnRectangle, graphics);
        }
    }

    public void drawScore(String score, Rectangle drawnRectangle, Graphics2D graphics){
        graphics.setColor(Color.WHITE);
        graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 80));
        int textWidth = (int) graphics.getFontMetrics().getStringBounds(score, graphics).getWidth();
        TextLayout tl = new TextLayout(score, new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                graphics.getFontRenderContext());
        int textHeight = (int) tl.getBounds().getHeight();
        graphics.drawString(score, drawnRectangle.x + drawnRectangle.width / 2 - textWidth / 2, drawnRectangle.y + drawnRectangle.height / 2 + textHeight / 2 -50);
    }

    public void drawButtons(String nameOfButton, Rectangle drawnRectangle, Graphics2D graphics){
        graphics.setColor(Color.BLACK);
        graphics.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
        int textWidth = (int) graphics.getFontMetrics().getStringBounds(nameOfButton, graphics).getWidth();
        TextLayout tl = new TextLayout(nameOfButton, new java.awt.Font("Yu Gothic UI Semibold", 1, 14),
                graphics.getFontRenderContext());
        int textHeight = (int) tl.getBounds().getHeight();
        graphics.drawString(nameOfButton, drawnRectangle.x + drawnRectangle.width / 2 - textWidth / 2, drawnRectangle.y + drawnRectangle.height / 2 + textHeight / 2);
    }

    public static boolean isDigit(String text){
        return text.chars().allMatch(Character::isDigit);
    }


}
