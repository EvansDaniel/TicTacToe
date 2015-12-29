import objectdraw.*;
import java.awt.*; 

// Author - Daniel Evans 

public class Fish_BG implements Drawable157 {

  private FramedOval body;
  private FilledOval eye;
  private FramedArc fin;
  private FramedArc tail;
  private Line[] newtail;


  public Fish_BG (int size, Location p, DrawingCanvas dc) {
  
   /* newtail = new Line[25];
    
    for( i = 0; i < 26; i++) { 
      
      newtail[i] = new Line(p.getX()+size, p.getY()+size/4, 
                            p.getX()+5/4*size, p.getY()+2*i,dc);
                            
   */                            
    
    Text win = new Text("Player 2 wins",p.getX()+50,p.getY()+75,dc);
    win.setFontSize(25);
    
    body = new FramedOval(p, size, size/2, dc);
    body.setColor(Color.ORANGE);
    
    eye = new FilledOval(p, size/10, size/10, dc);
    eye.move(size/8, size/5);
    
    tail = new FramedArc(p.getX()+size,p.getY()+size/10,size/3,size/3,90,180,dc);
    
    fin = new FramedArc(p.getX(),p.getY(),size/6,size/6,270,180,dc);
    fin.move(size/2,size/4-size/12);

  }

  public Fish_BG (int size, double x, double y, DrawingCanvas dc) {
    this (size,new Location(x,y),dc);
  }

  public Fish_BG (double x, double y, DrawingCanvas dc) {
    this (100, new Location(x,y),dc);
  }

  public void move (double dx, double dy) {
    body.move (dx, dy); 
    eye.move (dx, dy); 
    tail.move (dx, dy); 
    fin.move (dx, dy); 
  }

  public void moveTo (double x, double y) {
  
      move (x-body.getX(), y-body.getY()); 

  }
  
  public void moveTo(Location p) {
    
    move (p.getX()-body.getX(), p.getY()-body.getY());
  }  


  public void setColor (Color c ) {

    body.setColor (c);
    tail.setColor (c);
    fin.setColor (c);
    eye.setColor (c); 

 }

  public double getX () {
    return body.getX(); 
  }

  public double getY () {
    return body.getY(); 
  }

  public Location getLocation () {
    return body.getLocation(); 
  }

  public Color getColor () {
    return body.getColor();   
  }

   public boolean contains (Location p) {
    return body.contains(p);
     
      
  }

  public double getWidth () {
    return body.getWidth()+tail.getWidth()/2;
  }
 
  public double getHeight () {
    return body.getHeight();
  }
  
  public void hide() {
    
    body.hide();
    tail.hide();
    fin.hide();
    eye.hide();
  }
  
  public void show() {
    
    body.show();
    tail.show();
    fin.show();
    eye.show();
  }
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  


