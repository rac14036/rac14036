import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.CancellationException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class GUI {
	JProgressBar progressBar;
	Fraktal fraktal;
	FraktalTask task;

	public void createAndShowGUI() {
		JFrame frame = new JFrame("Prime Numbers");
		Box box = new Box(BoxLayout.Y_AXIS);
		frame.add(box);
		progressBar = new JProgressBar(0, 100);
		box.add(progressBar);
			
			
		fraktal = new Fraktal(Fraktal.xDimension, -2.1, -1.25, 0.65, 1.25);
        fraktal.setPreferredSize(new Dimension(fraktal.getWidth(),fraktal.getHeight()));
        
        box.add(fraktal);
        
        JMenuBar mb = new JMenuBar();
		frame.setJMenuBar(mb);
		JMenu m = new JMenu("Menu");
		mb.add(m);
		
		JMenuItem mi2 = new JMenuItem("Start");
		m.add(mi2);
		
		mi2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
					 task.execute();
			}
		});
		JMenuItem mi = new JMenuItem("Cancel");
		m.add(mi);
		mi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				task.cancel((true));
			};
		});
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
		/**
        
        fraktal.rechnen();
        JFrame window = new JFrame();
        window.getContentPane().add(fraktal);
        window.pack();
        window.setVisible(true);
        **/
	
	}
	public void setImage(BufferedImage image){
		fraktal.setImage(image);
		fraktal.paintComponent(fraktal.getGraphics());
	}

}
