import objectdraw.*;

// Author - Daniel Evans 

public class X {

  Line negSlopeLine;
  Line posSlopeLine;

  public X (double negSlopeStartX, double negSlopeStartY
           ,double distBetwStart ,DrawingCanvas canvas) {
           
    
    
    negSlopeLine = new Line(negSlopeStartX
                                ,negSlopeStartY
                                ,negSlopeStartX+distBetwStart
                                ,negSlopeStartY+distBetwStart
                                ,canvas); 
                        
    posSlopeLine = new Line(negSlopeStartX+distBetwStart, 
                                 negSlopeStartY,
                                 negSlopeStartX,
                                 negSlopeStartY+distBetwStart,
                                 canvas);
                               
  }
}
                                 
