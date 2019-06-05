import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    //declaration of gui objects

    private int xsize = 6;
    private int ysize = 7;
    int[][] MY_MATRIX = new int[xsize][ysize];

    int player=1;

    private JFrame frame;
    private JLabel[][] slots;
    private JButton[] buttons;

    public GUI() {

        frame = new JFrame("connect four");
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                MY_MATRIX[i][j] = 0;
            }
        }


        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(xsize + 1, ysize));

        slots = new JLabel[xsize][ysize];
        buttons = new JButton[ysize];

        for (int i = 0; i < ysize; i++) {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {

                            int SELECTED_COLUMN = Integer.parseInt(e.getActionCommand());
                            if(Core.fill(MY_MATRIX,SELECTED_COLUMN,player)){
                                switch (Core.getWinner(MY_MATRIX)){
                                    case 1:System.out.println("Red wins! You can continue!");break;
                                    case -1:System.out.println("Blue wins! You can continue!");break;
                                    case 2:System.out.println("Nice job my wise friends! lets try another game");break;
                                }
                                player=player*(-1);
                                updateBoard();}
                            }



                    });
            panel.add(buttons[i]);
        }
        for (int row = 0; row < xsize; row++) {
            for (int column = 0; column < ysize; column++) {

                slots[row][column] = new JLabel();
                slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
                slots[row][column].setBorder(new LineBorder(Color.black));
                panel.add(slots[row][column]);
            }
        }

        //jframe stuff
        frame.setContentPane(panel);
        frame.setSize(
                700, 600);
        frame.setVisible(
                true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateBoard() {
        //keeps the gui in sync with the logic and grid
        for (int row = 0; row < xsize; row++) {
            for (int column = 0; column < ysize; column++) {


                if (MY_MATRIX[row][column] == 1) {
                   // System.out.println("row" + row + "col" + column);
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.red);
                }
                if (MY_MATRIX[row][column] == -1) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.blue);
                }
            }
        }
    }

    public static void main(String[] args) {
        GUI Gui = new GUI();
    }
}
