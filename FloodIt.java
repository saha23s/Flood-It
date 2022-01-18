package com.company;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class FloodIt
{
    private final int rownum;
    private final int colnum;
    private Square[][] lights; // 2D array (like a list) to store the squares

    public FloodIt(int row, int col)
    {
        rownum = row;
        colnum = col;
        // this is the initialization code for swing to create the window that will pop up
        // the argument to the constructor sets the title that shows at the top of the window
        JFrame frame = new JFrame("FloodIt");

        // invoke an instance method to set behavior for clicking the X on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the width and height of the window (in pixels)
        frame.setSize(600,600);

        // set the layout of the main area to be a grid
        frame.getContentPane().setLayout( new GridLayout(row, col) );

        // add lights to the frame
        createAndAddSquaresToFrame(frame);

        // show the window
        frame.setVisible( true );

    }

    public void createAndAddSquaresToFrame(JFrame frame )
    {
        // initialize the array where we will store the squares
        lights = new Square[rownum][colnum];

        for ( int r = 0; r < rownum; r++ )
        {
            for ( int c = 0; c< colnum; c++ )
            {
                lights[r][c] = new Square(randomColor());
                frame.getContentPane().add(lights[r][c].getBeam());
                lights[r][c].getBeam().addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent e )
                    {
                        floodwithClicks(e);
                        // this method will be invoked when the button is clicked
                    }
                });
            }
        }
    }
    public Color randomColor(){
        Color[] ranDom ={Color.pink, Color.green, Color.yellow, Color.blue, Color.cyan, Color.magenta};
        return ranDom[(int)Math.floor(Math.random()*ranDom.length)];
    }
    public void floodwithClicks(ActionEvent e){
        Color firstsqaurecolor = lights[0][0].getColor();
        JButton clickedButton= (JButton)e.getSource();
        Color clickedColor = clickedButton.getBackground();
        if(clickedColor != firstsqaurecolor) {
            floodfill(clickedColor, firstsqaurecolor,0, 0);
            // when a square will be clicked, floodfill method will be invoked(if the clicked button color is
        }
        if(gamestatus()){
            int winclicks = lights[0][0].getChanges();
            double sum =0, average;
            System.out.println("Congratulations! You won with " + winclicks +" clicks!");
            for (int i = 0; i < rownum; i++) {
                for (int j = 0; j < colnum; j++) {
                    sum = sum + lights[i][j].getChanges();
                }
            }
            average = sum / (rownum*colnum);
            System.out.println("The average color changes per square was "+ average +".");
        }
    }

    public int floodfill(Color newColor, Color original, int row, int col){
        Color color1=lights[row][col].getColor();
        if (color1 == original) {//tests every time if the color of current square match the first color of the first square
            lights[row][col].changeColor(newColor);//change to the clicked color
            lights[row][col].getChanges();
            if (getSquare(row, col + 1)) floodfill(newColor, original, row, col + 1);
            if (getSquare(row - 1, col)) floodfill(newColor, original, row - 1, col);
            if (getSquare(row, col - 1)) floodfill(newColor, original, row, col - 1);
            if (getSquare(row + 1, col)) floodfill(newColor, original, row + 1, col);
        }
        return 0;
    }

    public boolean getSquare(int row, int col){
        if(row>=0 && row<rownum){
            if(col>=0 && col<colnum){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public boolean gamestatus(){
        Color color = lights[0][0].getColor();
        for (int i = 0; i < rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                if(lights[i][j].getColor() != color) return false;
            }
        }
        return true;
    }

    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FloodIt(12,12);
            }
        });
    }
}
