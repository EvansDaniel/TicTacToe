import java.awt.*;

import objectdraw.*;

// Author - Daniel Evans 

interface Drawable {

  
boolean       contains(Location point);

void          move(double dx, double dy);

   
void          moveTo(double x, double y);
   
void          moveTo(Location point);

   
void          hide();
   
void          show();
   
void          setColor(Color c);

   
double        getX();      
   
double        getY();     
   
Location      getLocation();

   
double        getWidth();
   
double        getHeight();
}
