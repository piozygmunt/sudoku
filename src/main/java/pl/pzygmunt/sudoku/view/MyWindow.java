package pl.pzygmunt.sudoku.view;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * G��wne okno aplikacji. Zawiera 2 podstawowe panele - planszy do gry oraz
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
	/** Panel na kt�rym znajduj� si� przyciski do kontroli rozgrywki oraz aktualny czas gry. */
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
	 * Wpisuje warto�ci w odpowiednie pola na planszy. Warto�� te przesy�ane s�
	 * z modelu za pomoc� makiety - DataPack.
	 *
	 *@param dp Referencja na makiete stworzona przez model.
	 */
	void setGame(final DataPack dp)
	{
		sudokuPanel.setGame(dp);
	}

	/** 
	 * Wy�wietla informacje o poprawno�ci p�l u�ytkownika przesylana z modelu za pomoca makiety.
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
	 * Nadanie warto�ci zaznaczonemu polu. 
	 * 
	 * @param value Nowa warto�� pola.
	 */
	void setValueOfSelectedField(final int value)
	{
		sudokuPanel.setValueOfSelectedField(value);
	}

	/** Odznaczenie wszystkich p�l - r�wnie� tych modyfikowanych przez setGameCheck. */
	public void clearSelection()
	{
		sudokuPanel.clearSelection();
	}

	/** Wykasowanie warto�ci z zaznaczonego pola. */
	public void clearSelectedField()
	{
		sudokuPanel.clearSelectedField();
	}

	/** 
	 * Dodanie listener�w do planszy do gry. 
	 * 
	 * @param sudokuListener Referencja na obiekt klasy sudokuListener.
	 */
	public void setSudokuListener(final SudokuListener sudokuListener)
	{
		sudokuPanel.setController(sudokuListener);
	}

	/** 
	 * Dodanie listener�w do panelu z przyciskami. 
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