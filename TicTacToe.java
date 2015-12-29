import objectdraw.*;
import java.awt.Color;

Author-Daniel Evans 

public class TicTacToe {

  double _horizStartX;
  double _vertStartY;
  double _lineLength;
  Line upperHorizLine;
  Line lowerHorizLine;
  Line leftVertLine;
  Line rightVertLine;
  Location locStart;
  

  public TicTacToe (double horizStartX, double vertStartY, double lineLength
                   ,DrawingCanvas canvas)   {
                   
    _horizStartX = horizStartX;
    _vertStartY  = vertStartY;
    _lineLength  = lineLength;
    locStart = new Location(horizStartX,vertStartY);
    
    upperHorizLine = new Line(horizStartX
                             ,vertStartY+.33*lineLength
                             ,horizStartX+lineLength
                             ,vertStartY+.33*lineLength
                             ,canvas);
                                  
    
    lowerHorizLine = new Line(horizStartX, vertStartY+.66*lineLength
                             ,horizStartX+lineLength
                             ,vertStartY+.66*lineLength
                             ,canvas); 

    leftVertLine   = new Line(horizStartX+.33*lineLength
                             ,vertStartY
                             ,horizStartX+.33*lineLength
                             ,vertStartY+lineLength
                             ,canvas); 
    
    rightVertLine  = new Line((horizStartX+.66*lineLength)
                              ,vertStartY
                              ,horizStartX+.66*lineLength
                              ,vertStartY+lineLength
                              ,canvas); 
                              
    upperHorizLine.setColor(Color.RED); 
    lowerHorizLine.setColor(Color.RED);
    leftVertLine.setColor(Color.RED);
    rightVertLine.setColor(Color.RED);
  }
  
  public double getHorizStartX() {
    return _horizStartX;
  }
  public Location getLocationStart() {
    return locStart;
  }  
  public double getVertStartY() {
    return _vertStartY;
  }
  public double getLineLength() {
    return _lineLength;
  }
  public void move(double dx, double dy) {
    upperHorizLine.move(dx,dy);
    lowerHorizLine.move(dx,dy);
    leftVertLine.move(dx,dy);
    rightVertLine.move(dx,dy);
  }
}
    
    
