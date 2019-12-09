
package com.pranayama.ui.panel;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class basicPanel_PaintContext implements PaintContext {

    Point2D point;
    Point2D Radius;
    Color C1, C2;
    public basicPanel_PaintContext(Point2D p1 , Color c1 , Point2D p2 ,  Color c2 ){
       point = p1;
       C1    = c1;
       Radius= p2;
       C2    = c2;
    }

    @Override
    public void dispose() { }

    @Override
    public ColorModel getColorModel() {
        return ColorModel.getRGBdefault();
    }

    @Override
    public Raster getRaster(int x, int y, int w, int h) {
        WritableRaster raster = getColorModel().createCompatibleWritableRaster(w, h);

        int[] data = new int[w*h*4];
        for(int j = 0 ; j < h; j++) {
          for(int i = 0 ; i < w; i++) {
              double distance = point.distance(x + i, y + j) ;
              double radius   = Radius.distance(0, 0);
              double ratio    = distance / (radius  );
              if( ratio > 1.0) ratio = 1.0;

              int base = (j*w + i)*4;
              data[base + 0] = (int) (C1.getRed()   + ratio*(C2.getRed() - C1.getRed() ));
              data[base + 1] = (int) (C1.getGreen() + ratio*(C2.getGreen() - C1.getGreen()));
              data[base + 2] = (int) (C1.getBlue()  + ratio*(C2.getBlue() - C1.getBlue() ));
              data[base + 3] = (int) (C1.getAlpha() + ratio*(C2.getAlpha() - C1.getAlpha() ));
          }
        }
        raster.setPixels(0, 0, w, h, data);
        return raster;
    }

}
