package pl.pzygmunt.sudoku.view;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * G³ówne okno aplikacji. Zawiera 2 podstawowe panele - planszy do gry oraz
 * przyciski.
 * 
 * @author Piotr Zygmunt
 */
public class MyWindow extends JFrame
{
	/** Numer wersji. */
	private static final long serialVersionUID = 1L;
	/** Wizualna reprezentacja planszy do gry w postaci panelu. */
	private final SudokuPanel sudokuPanel;
	/** Panel na którym znajduj¹ siê przyciski do kontroli rozgrywki oraz aktualny czas gry. */
	private final ButtonPanel buttonPanel;

	/** 
	 * Konstruktor podstawowy klasy MyWindow. Utworzenie panelow i dodanie ich do okna. 
	 */
	public MyWindow()
	{
		super("Sudoku");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		this.sudokuPanel = new SudokuPanel();
		add(sudokuPanel, BorderLayout.CENTER);

		this.buttonPanel = new ButtonPanel();
		add(buttonPanel, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Wpisuje wartoœci w odpowiednie pola na planszy. Wartoœæ te przesy³ane s¹
	 * z modelu za pomoc¹ makiety - DataPack.
	 *
	 *@param dp Referencja na makiete stworzona przez model.
	 */
	void setGame(final DataPack dp)
	{
		sudokuPanel.setGame(dp);
	}

	/** 
	 * Wyœwietla informacje o poprawnoœci pól u¿ytkownika przesylana z modelu za pomoca makiety.
	 * 
	 * @param dp Referencja na makiete.
	 */
	void setGameCheck(final DataPack dp)
	{
		sudokuPanel.setGameCheck(dp);
	}

	/** 
	 * Zaznaczenie konkretnego pola.
	 * 
	 * @param pkt Referencja na obiekt klasy Point.
	 */
	void setSelectedField(final Point pkt)
	{
		sudokuPanel.setSelectedField(pkt);
	}

	/** 
	 * Nadanie wartoœci zaznaczonemu polu. 
	 * 
	 * @param value Nowa wartoœæ pola.
	 */
	void setValueOfSelectedField(final int value)
	{
		sudokuPanel.setValueOfSelectedField(value);
	}

	/** Odznaczenie wszystkich pól - równie¿ tych modyfikowanych przez setGameCheck. */
	public void clearSelection()
	{
		sudokuPanel.clearSelection();
	}

	/** Wykasowanie wartoœci z zaznaczonego pola. */
	public void clearSelectedField()
	{
		sudokuPanel.clearSelectedField();
	}

	/** 
	 * Dodanie listenerów do planszy do gry. 
	 * 
	 * @param sudokuListener Referencja na obiekt klasy sudokuListener.
	 */
	public void setSudokuListener(final SudokuListener sudokuListener)
	{
		sudokuPanel.setController(sudokuListener);
	}

	/** 
	 * Dodanie listenerów do panelu z przyciskami. 
	 * 
	 * @param buttonListener Referencja na obiekt klasy buttonListener.
	 */
	public void setButtonListener(final ButtonListener buttonListener)
	{
		buttonPanel.setController(buttonListener);
	}

	/** 
	 * Wyswietlenie informacji o zakonczeniu gry. 
	 * 
	 * @param dp Referencja na makieta stworzona przez model.
	 */
	public void gameOver(final DataPack dp)
	{
		JOptionPane.showMessageDialog(null, "Koniec gry. Twoj Czas to :" + dp.time + " sekund.");
	}
	
	/**
	 *  Wyswietlenie aktualnego czasu gry. 
	 * 
	 * @param dp Makieta stworzona przez model.
	 */
	public void refreshTime(final DataPack dp)
	{
		buttonPanel.refreshTime(dp);
	}
}