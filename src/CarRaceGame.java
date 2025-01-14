
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CarRaceGame extends JFrame implements ActionListener, ChangeListener {
    private JPanel control;
    private JPanel view;
    private JButton rank;
    private JButton start;
    private JButton team_choice;
    private JButton team1, team2, team3, team4;
    private JTextField scoreField,rankField;
    private int colorFlag;
    private int x = 400;
    private int y = 400;
    private int speed = 25;
    private Random random = new Random();
    private int randomSquareX;
    private int randomSquareY = 0;
    private int randomSquareSpeed = 3;
    private boolean isEnd=false,isStart=false;
    private int score;
    private ArrayList<Integer> ranking = new ArrayList<>();

    public CarRaceGame() {
        // set the title and size of the window
        setTitle("GUILab Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Divide the window to parts: control and view
        control = new JPanel();
        view = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
// 				for the use of mode 1
                super.paintComponent(g);
//				change the shape here to the car
                // your own car
                g.setColor(Color.BLACK);
                g.fillOval(x-3,y-3,20,20);
                g.fillOval(x+30,y-3,20,20);
                g.fillOval(x-3,y+60,20,20);
                g.fillOval(x+30,y+60,20,20);
                switch(colorFlag){
                    case 1:
                        g.setColor(Color.BLUE);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        break;
                    case 4:
                        g.setColor(Color.PINK);
                        break;
                    default:
                        g.setColor(Color.CYAN);
                        break;
                }
                g.fillOval(x, y, 50, 80);

                // your rival car
                g.setColor(Color.BLACK);
                g.fillOval(randomSquareX-3,randomSquareY-3,20,20);
                g.fillOval(randomSquareX+30,randomSquareY-3,20,20);
                g.fillOval(randomSquareX-3,randomSquareY+60,20,20);
                g.fillOval(randomSquareX+30,randomSquareY+60,20,20);
                g.setColor(Color.RED);
                g.fillOval(randomSquareX, randomSquareY,50,80);

            }
        };

        scoreField = new JTextField("0000000000000");
        rankField = new JTextField("Play to have the score ranking");
        // Create buttons and slider
        start = new JButton("Start games");
        rank = new JButton("Score Ranking");
        team1= new JButton("1");
        team2= new JButton("2");
        team3= new JButton("3");
        team4= new JButton("4");
        team1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                colorFlag=1;
            }

        });
        team2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                colorFlag=2;
            }

        });
        team3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                colorFlag=3;
            }

        });
        team4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                colorFlag=4;
            }
        });
//		team_choice = new JButton("Choose the team");
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isEnd=false;
                x = 400;
                y=400;
                requestFocusInWindow();
                isStart=true;
                score=0;
            }

        });



        // put the control and view panel on the frame
        add(control, BorderLayout.NORTH);
        view.setBackground(Color.WHITE);
        add(view, BorderLayout.CENTER);

        //put the 3 buttons on the panel control
        control.add(start);
        control.add(new JLabel("Team:"));
//		control.add(team_choice);
        control.add(team1);
        control.add(team2);
        control.add(team3);
        control.add(team4);
        control.add(new JLabel("Your Score:"));
        control.add(scoreField);
        control.add(new JLabel("Highest score:"));
        control.add(rankField);

        MyComponent component = new MyComponent();
        view.add(component);
//		control.add(speedSlider);
//		我想写个能展示现在速度的值 但是还没想好 可能不需要
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moveSquare(e);
            }
        });
        Random random = new Random();
        randomSquareX = random.nextInt(getWidth() - 50);

        // Start the timer to move the random square
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRandomSquare();
            }
        });
        timer.start();
    }



    private class MyComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // your own car
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 50, 50);
            // your rival car
            g.setColor(Color.RED);
            g.fillRect(randomSquareX, randomSquareY, 50, 50);
            setFocusable(true);
            if(x+50>randomSquareX && x<randomSquareX+50) {
                if(y<randomSquareY+70 && y+70>randomSquareY) {
                    isEnd = true;
                    ranking.add(score);
                    Collections.sort(ranking, Collections.reverseOrder());
                    if (ranking.size()>3) {
                        ranking.remove(3);
                    }
                    rankField.setText(String.valueOf(ranking));
                }
            }
            if(x<0 || x+60>800 || y<0 || y+140>600) {
                isEnd = true;
                ranking.add(score);
                Collections.sort(ranking, Collections.reverseOrder());
                if (ranking.size()>3) {
                    ranking.remove(3);
                }
                rankField.setText(String.valueOf(ranking));
            }
        }
    }

    private void moveSquare(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if(!isEnd) {
            if (keyCode == KeyEvent.VK_LEFT) {
                x -= speed;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                x += speed;
            } else if (keyCode == KeyEvent.VK_UP) {
                y -= speed;
            } else if (keyCode == KeyEvent.VK_DOWN) {
                y += speed;
            }
            repaint();
        }
    }



    private void moveRandomSquare() {
        if(!isEnd) {
            randomSquareY += randomSquareSpeed;
            if(isStart){
                score += speed/10;
            }
            scoreField.setText(String.valueOf(score));
            if (randomSquareY > getHeight()) {
                randomSquareY = 0;
                Random random = new Random();
                randomSquareX = random.nextInt(getWidth() - 50);
            }
            repaint();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void stateChanged(ChangeEvent e) {


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarRaceGame().setVisible(true);
            }
        });
    }
}