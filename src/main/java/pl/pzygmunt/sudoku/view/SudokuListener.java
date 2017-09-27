package pl.pzygmunt.sudoku.view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

import pl.pzygmunt.sudoku.events.ApplicationEvent;
import pl.pzygmunt.sudoku.events.FieldSelectingEvent;
import pl.pzygmunt.sudoku.events.RemoveNumberEvent;

/**
 * Klasa implementuj¹ca interfejs MouseListener. Odpowiedzialna za nas³uchiwanie
 * zdarzeñ na planszy do sudoku.
 * 
 * @author Piotr Zygmunt
 */
public class SudokuListener implements MouseListener
{
	/** Kolejka zdarzeñ. */
	private final BlockingQueue<ApplicationEvent> bq;

	/**
	 * Konstruktor podstawowy.
	 *
	 *@param bq Referencja na kolejke zdarzeñ.
	 */
	public SudokuListener(final BlockingQueue<ApplicationEvent> bq)
	{
		this.bq = bq;
	}

	/**
	 *  Funkcja uruchamiana po naciœniêciu klawisza myszki na planszy sudoku.
	 *  
	 *  @param evnt Obiekt klasy MouseEvent.
	 */
	public void mousePressed(final MouseEvent evnt)
	{
		JPanel panel = (JPanel) evnt.getSource();
		Component component = panel.getComponentAt(evnt.getPoint());

		// czy kliknelismy na pole do gry
		if (component instanceof VField)
		{
			// lewy przycisk myszy
			if (evnt.getButton() == MouseEvent.BUTTON1)
			{
				VField field = (VField) component;
				try
				{
					bq.put(new FieldSelectingEvent(field.getFieldX(), field.getFieldY()));
				} 
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
				}
			}
			// prawy przycisk myszy
			if (evnt.getButton() == MouseEvent.BUTTON3)
			{
				try
				{
					bq.put(new RemoveNumberEvent());
				} 
				catch (InterruptedException ee)
				{
					// TODO Auto-generated catch block
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}
}
