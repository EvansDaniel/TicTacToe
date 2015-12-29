import java.awt.event.*;
import javax.swing.*;
import objectdraw.*;
import java.awt.*;

// Author - Daniel Evans 

public class TicTacToeGame extends WindowController implements ActionListener { 
  
  private JPanel southPanel;
  private JPanel mainCenter;
  private JPanel mainSouth;

  private String[] letters = {"A","B","C"
                           ,"D","E","F"
                           ,"G","H","I"};
  private Text[] letterDisplay = new Text[9];
  private FramedOval O;
  private TicTacToe ticTacToe;
  private JLabel player1;
  private JLabel player2;
  
  private boolean draw     = false;
  
  // Will be used to hold values that specify who won the game 
  private int[][] win = new int[2][9]; 
  private JButton[][] pBtn = new JButton[2][9];
  private X[] x = new X[9];
  private FramedOval[] fo = new FramedOval[9];
  private Container contentPane;
  private JButton playAgain;
  
  private Drawable157 bigFish;
  private Drawable157 smallFish;
  
  public static void main(String[] args) {
    new TicTacToeGame().startController(875,600);
  }
  
  public void begin() { 

    FilledRect background = new FilledRect(0,0
                                          ,canvas.getWidth()
                                          ,canvas.getHeight()
                                          ,canvas);
    background.setColor(new Color(12,133,173));
    
    // Go back and try to connect the location of the letters
    // and the X/O's with the ticTacToe
    ticTacToe = new TicTacToe(canvas.getWidth()/2
                                       ,80,300
                                       ,canvas);
    ticTacToe.move(-ticTacToe.getLineLength()/2,0);
    
    // Sets the win array values something . Some of them will be changed
    // later to one based on what buttons the players press.
    for(int j = 0;j<2;j++){
      for(int i=0; i<9;i++){ 
        win[j][i] = (j+1)*(i+1)*100;
      }
    }    
    
    // Creates the letters A,B,C...I in the tic tac toe
    int x;
    int y = 120;
    int k = 0;
    for(int i=0; i<3; i++){
      
      x=333;
      
      for(int j=0; j<3; j++) {
        
        // The letters array holds the values A,B,C...I 
        letterDisplay[k] = new Text(letters[3*i+j], x,y, canvas);
        letterDisplay[k].setColor(new Color(212,51,15));
        letterDisplay[k].setFontSize(30);
        x+=100;
        k++;
      }
    y+=100;
    }
    
    player1 = new JLabel("Player 1 (X)");
    player2 = new JLabel("Player 2 (O)");
    player1.setForeground(Color.RED);
    JLabel instructions = new JLabel("Clicking the buttons that correspond "
    + "to the letters in the tic tac toe produces each player's "
    + "respective X or O in that spot."); 
    
    // Creates 2d array of buttons, first dimension corresponds to the player
    // Second dimension corresponds to the buttons of that player
    for(int i=0;i<pBtn.length;i++) {
      for(int j=0;j<9;j++) {
        pBtn[i][j] = new JButton();
        pBtn[i][j].setText(letters[j]);
      }
    }
    JPanel main      = new JPanel(new GridLayout(3,9,3,0)); 
    JPanel mainNorth = new JPanel();
    mainCenter       = new JPanel();
    mainSouth        = new JPanel();
    southPanel       = new JPanel(); 
    
    // Adds components to the 
    mainCenter.add(player1);
    mainSouth.add(player2);
    mainNorth.add(instructions);
    for(int i=0; i<9;i++){ 
      mainCenter.add(pBtn[0][i]);
      mainSouth.add(pBtn[1][i]);
    }

    // Adds panels to the main panel
    main.add(mainNorth);
    main.add(mainCenter);
    main.add(mainSouth);

    for(int j = 0;j<2;j++)
      for(int i=0; i<9;i++) 
        pBtn[j][i].addActionListener(this);
    
    contentPane = getContentPane();
    contentPane.add(main, BorderLayout.NORTH);
    contentPane.add(southPanel, BorderLayout.SOUTH);
    southPanel.setVisible(false);
    contentPane.validate(); 
  }    
  public void actionPerformed(ActionEvent evt) {

    // player 1 buttons work only if it 
    // is his or her turn(player1 text is red)
    // which controls whose turn it is 
    if(player1.getForeground().equals(Color.RED)) {
      if (evt.getSource() == pBtn[0][0]) {
        
        // This is used to compare values of the array in the if statement below
        // If the combinations below are equal(they will all be one)
        // player 1 or 2 
        win[0][0] = 1;
        
        // Removes button from canvas so the player can't click it again
        pBtn[0][0].setVisible(false);
        pBtn[1][0].setVisible(false);
        
        // Removes the letter (A,B,...I) from the tic tac toe
        letterDisplay[0].hide();
        // Places an x at the corresponding letter place 
        new X(ticTacToe.getHorizStartX()-125,ticTacToe.getVertStartY()+25
             ,50,canvas);
        
        // Colors indicate whose turn it is
        // Red indicates that it is that players turn 
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        //for(int i=0; i<win.length;i++)
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                                    
        }     
      }
      else if (evt.getSource() == pBtn[0][1]) {
        
        win[0][1] = 1;
        
        pBtn[0][1].setVisible(false);
        pBtn[1][1].setVisible(false);
      
        letterDisplay[1].hide();
      
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()-25,ticTacToe.getVertStartY()+25
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] ) 
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);                
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                     
        }
      }
      else if (evt.getSource() == pBtn[0][2]) {
        
        win[0][2] = 1;
        
        pBtn[0][2].setVisible(false);
        pBtn[1][2].setVisible(false);
      
        letterDisplay[2].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()+75,ticTacToe.getVertStartY()+25
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);      
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                  
        }
      }
      else if (evt.getSource() == pBtn[0][3]) {
        
        win[0][3] = 1;
        
        pBtn[0][3].setVisible(false);
        pBtn[1][3].setVisible(false);
      
        letterDisplay[3].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()-125,ticTacToe.getVertStartY()+125
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);         
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                            
        }
      }
      else if (evt.getSource() == pBtn[0][4]) {
        
        win[0][4] = 1;
        
        pBtn[0][4].setVisible(false);
        pBtn[1][4].setVisible(false);
      
        letterDisplay[4].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()-25,ticTacToe.getVertStartY()+125
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);         
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                     
        }
      }
      else if (evt.getSource() == pBtn[0][5]) {
        
        win[0][5] = 1;
        
        pBtn[0][5].setVisible(false);
        pBtn[1][5].setVisible(false);
      
        letterDisplay[5].hide();
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()+75,ticTacToe.getVertStartY()+125
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);    
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                              
        }
      }
      else if (evt.getSource() == pBtn[0][6]) {
        
        win[0][6] = 1;
        
        pBtn[0][6].setVisible(false);
        pBtn[1][6].setVisible(false);
      
        letterDisplay[6].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()-125,ticTacToe.getVertStartY()+225
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);   
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                                      
        }
      }
      else if (evt.getSource() == pBtn[0][7]) {
        
        win[0][7] = 1;
        
        pBtn[0][7].setVisible(false);
        pBtn[1][7].setVisible(false);
      
        letterDisplay[7].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()-25,ticTacToe.getVertStartY()+225
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        { 
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);     
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                                
        }
      }
      else if (evt.getSource() == pBtn[0][8]) {
        
        win[0][8] = 1;
        
        pBtn[0][8].setVisible(false);
        pBtn[1][8].setVisible(false);
      
        letterDisplay[8].hide();
      
        player2.setForeground(Color.RED);
        player1.setForeground(Color.BLACK);
        
        new X(ticTacToe.getHorizStartX()+75,ticTacToe.getVertStartY()+225
             ,50,canvas);
        
        if(win[0][0]==win[0][1] && win[0][1]==win[0][2] ||
           win[0][3]==win[0][4] && win[0][4]==win[0][5] ||
           win[0][6]==win[0][7] && win[0][7]==win[0][8] ||
           win[0][0]==win[0][3] && win[0][3]==win[0][6] ||
           win[0][1]==win[0][4] && win[0][4]==win[0][7] ||
           win[0][2]==win[0][5] && win[0][5]==win[0][8] ||
           win[0][0]==win[0][4] && win[0][4]==win[0][8] ||
           win[0][2]==win[0][4] && win[0][4]==win[0][6] )
        {  
          Text winner = new Text("Player 1 wins!", 337,30,canvas);
          winner.setFontSize(30);    
          bigFish = new Fish_BG(250,new Location(500,300),canvas);                              
        }
      }
    }
    
    // Actions performed for player 2 buttons 
    if(player2.getForeground().equals(Color.RED)) {
      if (evt.getSource() == pBtn[1][0]) {
        
        win[1][0] = 1;
        
        pBtn[0][0].setVisible(false);
        pBtn[1][0].setVisible(false);
      
        letterDisplay[0].hide();
      
        player1.setForeground(Color.RED); 
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()-125
                      ,ticTacToe.getVertStartY()+25
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30); 
          smallFish = new Fish_JD(200,100,100,canvas);                          
        }  

      }
      else if (evt.getSource() == pBtn[1][1]) {
      
        win[1][1] = 1;
        
        pBtn[0][1].setVisible(false);
        pBtn[1][1].setVisible(false);
      
        letterDisplay[1].hide();   
      
        player1.setForeground(Color.RED); 
        player2.setForeground(Color.BLACK);  
        
        new FramedOval(ticTacToe.getHorizStartX()-25
                      ,ticTacToe.getVertStartY()+25
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);      
          smallFish = new Fish_JD(200,100,100,canvas);                      
        }  
      
      }
      else if (evt.getSource() == pBtn[1][2]) {
      
        win[1][2] = 1;
        
        pBtn[0][2].setVisible(false);
        pBtn[1][2].setVisible(false);
      
        letterDisplay[2].hide();  
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()+75
                      ,ticTacToe.getVertStartY()+25
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);            
          smallFish = new Fish_JD(200,100,100,canvas);              
        }  
      }
      else if (evt.getSource() == pBtn[1][3]) {
      
        win[1][3] = 1;
        
        pBtn[0][3].setVisible(false);
        pBtn[1][3].setVisible(false);
      
        letterDisplay[3].hide();    
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()-125
                      ,ticTacToe.getVertStartY()+125
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);       
          smallFish = new Fish_JD(200,100,100,canvas);                   
        }  
      }
      else if (evt.getSource() == pBtn[1][4]) {
      
        win[1][4] = 1;
        
        pBtn[0][4].setVisible(false);
        pBtn[1][4].setVisible(false);
      
        letterDisplay[4].hide();
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()-25
                      ,ticTacToe.getVertStartY()+125
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);         
          smallFish = new Fish_JD(200,100,100,canvas);                   
        }  
      }
      else if (evt.getSource() == pBtn[1][5]) {
      
        win[1][5] = 1;
        
        pBtn[0][5].setVisible(false);
        pBtn[1][5].setVisible(false);
      
        letterDisplay[5].hide();    
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()+75
                      ,ticTacToe.getVertStartY()+125
                      ,50,50,canvas);

        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);            
          smallFish = new Fish_JD(200,100,100,canvas);                
        }  
      }
      else if (evt.getSource() == pBtn[1][6]) {
      
        win[1][6] = 1;
        
        pBtn[0][6].setVisible(false);
        pBtn[1][6].setVisible(false);
      
        letterDisplay[6].hide();    
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()-125
                      ,ticTacToe.getVertStartY()+225
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        { 
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);            
          smallFish = new Fish_JD(200,100,100,canvas);                
        }  
      }
      else if (evt.getSource() == pBtn[1][7]) {
      
        win[1][7] = 1;
        
        pBtn[0][7].setVisible(false);
        pBtn[1][7].setVisible(false);
      
        letterDisplay[7].hide();
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()-25
                      ,ticTacToe.getVertStartY()+225
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )
        {  
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);                         
          smallFish = new Fish_JD(200,100,100,canvas);
        }  
      }
      else if (evt.getSource() == pBtn[1][8]) {
      
        win[1][8] = 1;
        
        pBtn[0][8].setVisible(false);
        pBtn[1][8].setVisible(false);
      
        letterDisplay[8].hide();
      
        player1.setForeground(Color.RED);
        player2.setForeground(Color.BLACK);
        
        new FramedOval(ticTacToe.getHorizStartX()+75
                      ,ticTacToe.getVertStartY()+225
                      ,50,50,canvas);
        
        if(win[1][0]==win[1][1] && win[1][1]==win[1][2] ||
           win[1][3]==win[1][4] && win[1][4]==win[1][5] ||
           win[1][6]==win[1][7] && win[1][7]==win[1][8] ||
           win[1][0]==win[1][3] && win[1][3]==win[1][6] ||
           win[1][1]==win[1][4] && win[1][4]==win[1][7] ||
           win[1][2]==win[1][5] && win[1][5]==win[1][8] ||
           win[1][0]==win[1][4] && win[1][4]==win[1][8] ||
           win[1][2]==win[1][4] && win[1][4]==win[1][6] )  
        {  
          Text winner = new Text("Player 2 wins!", 337,30,canvas);
          winner.setFontSize(30);
          smallFish = new Fish_JD(200,100,100,canvas);
        }
          
      }
    }
  }
}
