package pl.pzygmunt.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.Timer;

import pl.pzygmunt.sudoku.controller.Controller;
import pl.pzygmunt.sudoku.model.Model;
import pl.pzygmunt.sudoku.events.ApplicationEvent;
import pl.pzygmunt.sudoku.events.TimeEvent;
import pl.pzygmunt.sudoku.view.View;

/**
 * Glowna klasa programu. Zawiera model, widok, controller oraz timer.
 * 
 * @author Piotr Zygmunt
 */
public class Sudoku
{

	public static void main(String[] args)
	{
		final Model model = new Model();
		final BlockingQueue<ApplicationEvent> bq = new LinkedBlockingQueue<>();
		final View view = new View(bq);
		final Controller controller = new Controller(model, view, bq);
		/* utworzenie timera wraz z odpowiadaj¹cym mu actionListenerem
		   który uaktywnia siê co sekunde  */
		final Timer timer = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					bq.put(new TimeEvent());
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}	
			}
		});
		timer.start();
		controller.dzialaj();
	}
}
