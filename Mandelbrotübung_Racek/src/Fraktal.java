import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Fraktal extends JPanel
{
    public static final int xDimension = 1000;
    private BufferedImage image;
    private double lox, loy, rux, ruy;
    public Fraktal(int w, double ilox, double iloy, double irux, double iruy)
    {
        super();
        setOpaque(true);
        lox = ilox;
        loy = iloy;
        rux = irux;
        ruy = iruy;
        int h = (int) ((w / ((rux-lox)/(ruy-loy))) + 0.5);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }
    public int getWidth()
    {
        return (image == null) ? 0 : image.getWidth();
    }
    public int getHeight()
    {
        return (image == null) ? 0 : image.getHeight();
    }
    public void setImage(BufferedImage image){
    	this.image=image;
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.drawImage(image,0,0,getWidth(),getHeight(),this);
    }
    
}