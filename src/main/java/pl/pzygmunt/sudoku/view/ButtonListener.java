package pl.pzygmunt.sudoku.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import pl.pzygmunt.sudoku.events.*;

/**
 * Klasa nas�uchuje akcji dotycz�cych buttonPanelu ( przycisk�w ).
 * 
 * @author Piotr Zygmunt
 */
public class ButtonListener implements ActionListener
{
	/** Referencja na kolejke zdarzen. */
	private final BlockingQueue<ApplicationEvent> bq;

	/**
	 * Konstruktor podstawowy.
	 * 
	 * @param bq Referencja na kolejke zdarze�.
	 */
	public ButtonListener(final BlockingQueue<ApplicationEvent> bq)
	{
		this.bq = bq;
	}

	/**
	 * Wykonuje akcje je�li kt�ry� button zostanie wci�ni�ty.
	 * 
	 * @param evnt Zdarzenie typu action.
	 */
	public void actionPerformed(final ActionEvent evnt)
	{
		if (evnt.getActionCommand().equals("New"))
		{
			try
			{
				bq.put(new ButtonNewEvent());
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
			}
		}
		else if (evnt.getActionCommand().equals("Check"))
		{
			try
			{
				bq.put(new ButtonCheckEvent());
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
			}
		}
		else if (evnt.getActionCommand().equals("Exit"))
		{
			try
			{
				bq.put(new ButtonExitEvent());
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
			}
		}
		else if (evnt.getActionCommand().equals("ClearS"))
		{
			try
			{
				bq.put(new DeselectFieldsEvent());
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
			}
		}
		else
		{
			try
			{
				bq.put(new ButtonNumberEvent(Integer.parseInt(evnt.getActionCommand())));
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
			}
		}
	}
}
