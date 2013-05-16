import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import javax.swing.*;
public class FraktalTask  extends SwingWorker<BufferedImage, BufferedImage> {
	
    private static final int anzIter = 1000;
    public static final int xDimension = 1000;
    private BufferedImage image;
    private double lox, loy, rux, ruy;
    GUI gui;
   
    public FraktalTask(int w, double ilox, double iloy, double irux, double iruy, GUI gui)
    {
        lox = ilox;
        loy = iloy;
        rux = irux;
        ruy = iruy;
        int h = (int) ((w / ((rux-lox)/(ruy-loy))) + 0.5);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        this.gui=gui;
    }  
    public int getWidth()
    {
        return (image == null) ? 0 : image.getWidth();
    }
    public int getHeight()
    {
        return (image == null) ? 0 : image.getHeight();
    }
    int farbe(double x, double y)
    {
        double zx = 0.0, zy = 0.0;
        int iter;
        for (iter = 0; (iter < anzIter) && (zx*zx+zy*zy < 4.0); iter++)
        {
            double zx2 = zx;
            zx = zx*zx-zy*zy + x;
            zy = 2.0*zx2*zy + y;
        }
        if (iter >= anzIter) return 0x000000;
        else return (int)(4096.0*(((double)anzIter-(double)iter)/(double)anzIter)+0.5) % 256;
    }
	@Override
	protected BufferedImage doInBackground() throws Exception {
		int w = getWidth(), h = getHeight();
        double xschritt = (rux-lox)/(double)w;
        double yschritt = (ruy-loy)/(double)h;

        double x = lox;
        for (int i = 0; i < w; i++, x += xschritt)
        {
        	
            setProgress(((i+1)*100)/w);
            double y = loy;
            for (int j = 0; j < h; j++, y += yschritt)
            {
              
            	image.setRGB(i,j,farbe(x,y));
            	publish(image);
                //
            }
            Thread.sleep(5);
        }
        
		return image;
	}
	@Override
	protected void process(List<BufferedImage> chunks){
         BufferedImage image=chunks.get(0);
         gui.setImage(image);
    }
	
}
