package com.company;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Represents a square of the grid which has a color, and it can change the color with the interaction from the user
 */
public class Square
{
    private Color color1; // instance property to track the square's color in the flood-it grid
    private JButton beam; // GUI element to display the square
    private int i = 0;
    public Square(Color color)
    {
        color1 = color; // remember the passed color in the instance property
        beam = new JButton(); // create the GUI element
        beam.setOpaque( true ); // make sure we can change the color of the button
        beam.setBorderPainted( false ); // this just has to be here... what happens when you comment it out?
        beam.setBackground(color);//set the background color which is passed when class is created
        beam.setText(String.valueOf(i));
        beam.setForeground( Color.black );
    }
    public Color getColor(){
        return color1;
    }
    public int getChanges(){
        beam.setText(String.valueOf(i));
        beam.setForeground( Color.black );
        return i;
    }
    public void changeColor(Color newColor)
    {
        beam.setBackground(newColor);
        color1 = newColor;
        i++;
    }
    /**
     * Instance method to get the GUI element - a JButton that changes colors upon clicks.
     * @return the JButton displaying this flashlight
     */
    public JButton getBeam()
    {
        return beam;
    }
}

