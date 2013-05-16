import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			final GUI g = new GUI();
			SwingUtilities.invokeAndWait(new Runnable() { 
			      public void run() { 
			            g.createAndShowGUI(); }
			      });
			
			 g.task = new FraktalTask(Fraktal.xDimension, -2.1, -1.25, 0.65, 1.25, g);
			 g.task.addPropertyChangeListener(
				     new PropertyChangeListener() {
				         public  void propertyChange(PropertyChangeEvent evt) {
				             if ("progress".equals(evt.getPropertyName())) {
				                 g.progressBar.setValue((Integer)evt.getNewValue());
				             }
				         }
				     });
 	 
		}
		catch(CancellationException e)
		{
			System.out.println("Cancelled");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		

	}

}
