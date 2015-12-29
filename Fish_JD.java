import java.awt.*;
import objectdraw.*;

// Author - Daniel Evans 

public class Fish_JD implements Drawable157 {

  private FilledOval body;
  private FramedOval bodyOutline;
  private FilledOval eye, upperLip,lowerLip;
  private Line upperFin,lowerFin,topFin;
  
  public Fish_JD (double w, double x, double y, 
                  DrawingCanvas dc)   {
    double h = w;
    body = new FilledOval(x,y,w,h,dc);
    
    Text win = new Text("Player 2 wins",x,y+100,dc);
    win.setFontSize(25);
    
    bodyOutline = new FramedOval(x,y,w,h,dc);
    
    eye = new FilledOval(x+(w/3)*2, y+h/4,w/20,h/15,dc);
    
    upperFin = new Line(x+w/5,y+h/5,x+w/2,y+h/2,dc);
    
    lowerFin = new Line(x+w/10,y+h/5,x+w/2,y+h/2,dc);
    
    lowerLip = new FilledOval(x+w/8*7,y+h/6*3,w/7,h/7,dc);
    
    upperLip = new FilledOval(lowerLip.getX(),lowerLip.getY()+h/9,w/7,h/7,dc);
    topFin = new Line(x+w/10,y+h/5,x+w/5,y+h/5,dc);

    body.setColor(Color.WHITE);
    upperLip.setColor(Color.RED);
    lowerLip.setColor(Color.RED);
    
  }

  public Fish_JD (Location p, DrawingCanvas dc)   {
    this(100, p.getX(),p.getY(),dc);
    
  }

  public void move(double dx,double dy) {
    body.move(dx,dy);
    bodyOutline.move(dx,dy);
    eye.move(dx,dy);
    upperFin.move(dx,dy);
    lowerFin.move(dx,dy);
    upperLip.move(dx,dy);
    lowerLip.move(dx,dy);
    topFin.move(dx,dy); 
  }
  
  public void hide() {
    body.hide();
    bodyOutline.hide();    
    eye.hide();
    upperFin.hide();
    lowerFin.hide();
    upperLip.hide();
    lowerLip.hide();
    topFin.hide();
  }
  
    public void show() {
    body.show();
    bodyOutline.show();    
    eye.show();
    upperFin.show();
    lowerFin.show();
    upperLip.show();
    lowerLip.show();
    topFin.show();
  }
  
  
  public void moveTo(double x, double y) {
    move(x-body.getX(),y-body.getY());
  }
  
  public void moveTo(Location p) {
    moveTo(p.getX(),p.getY());
  }
  
  public void setColor(Color c) {
    body.setColor(c);
  }

  public double getX() {
    return body.getX();
  }

  public double getY() {
    return body.getY();
  }

  public Location getLocation() {
    return body.getLocation();
  }

  public Color getColor() {
    return body.getColor();
  }

  public double getWidth() {
    return body.getWidth();
  }

  public double getHeight() {
    return body.getHeight();
  }

  public boolean contains(Location p) {
    return body.contains(p);
  }   
  
}
   

  
    

