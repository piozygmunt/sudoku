package pl.pzygmunt.sudoku.view;

import java.awt.Point;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;

import pl.pzygmunt.sudoku.commons.DataPack;

import pl.pzygmunt.sudoku.events.ApplicationEvent;

/**
 * Klasa która reprezentuje graficzny wygl¹d gry.
 * 
 * @author Piotr Zygmunt
 */
public class View
{
	/** G³ówne okno do gry. */
	private MyWindow okno;

	/** 
	 * Konstruktor widoku. Utworzenie okno, dodanie listenerów. 
	 * 
	 * @param bq Referencja na kolejke zdarzeñ.
	 */
	public View(final BlockingQueue<ApplicationEvent> bq)
	{
				okno = new MyWindow();

				ButtonListener buttonListener = new ButtonListener(bq);
				SudokuListener sudokuListener = new SudokuListener(bq);

				okno.setSudokuListener(sudokuListener);
				okno.setButtonListener(buttonListener);	
	}

	/** 
	 * Ustawia na poczatku wszystkie pola w grze. 
	 * 
	 * @param dp Referencja na makiete.
	 */
	public void setGame(final DataPack dp)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.setGame(dp);
			}
		});
	}

	/** 
	 * Zaznacza pola - poprawne(zielone) lub niepoprawne(czerwone). Zaznacza tylko pola u¿ytkownika. 
	 * 
	 * @param dp Referencja na makiete.
	 */
	public void setGameCheck(final DataPack dp)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.setGameCheck(dp);
			}
		});
	}

	/** 
	 * Zaznaczenie pola celu jego modyfikacji. 
	 *
	 * @param pkt Referencja na obiekt typu Point - nowe pole.
	 */
	public void setSelectedField(final Point pkt)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.setSelectedField(pkt);
			}
		});
	}

	/** 
	 * Ustawienie wartoœci w zaznaczonym polu. 
	 * 
	 * @param value Nowa wartoœæ.
	 */
	public void setValueOfSelectedField(final int value)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.setValueOfSelectedField(value);
			}
		});
	}

	/** 
	 * Odznaczenie wszystkich pól. 
	 */
	public void clearSelection()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.clearSelection();
			}
		});
	}

	/** 
	 * Wykasowanie wartoœci w zaznaczonym polu. 
	 */
	public void clearSelectedField()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.clearSelectedField();
			}
		});
	}

	/** 
	 * Wyswietlenie wiadomosci o zakonczeniu gry. 
	 * 
	 * @param dp Referencja na makiete.
	 */
	public void gameOver(final DataPack dp)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.gameOver(dp);
			}
		});
	}
	
	/** 
	 * Wyswietlenie aktualnego czasu gry. 
	 * 
	 * @param dp Referencja na makiete.
	 */
	public void refreshTime(final DataPack dp)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				okno.refreshTime(dp);
			}
		});
	}
}