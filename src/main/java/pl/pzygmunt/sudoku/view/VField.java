package pl.pzygmunt.sudoku.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import pl.pzygmunt.sudoku.commons.Constants;

/**
 * Klasa reprezentuj¹ca wizualn¹ postaæ pojedynczego pola do gry
 * 
 * @author Piotr Zygmunt
 */
class VField extends JLabel
{
	/** Numer wersji klasy. */
	private static final long serialVersionUID = 1L;
	/** Wspó³rzêdna X-owa w grze. */
	private final int x;
	/** Wspó³rzêdna y-owa w grze. */
	private final int y;

	/**
	 * Konstruktor podstawowy. Jako parametry wywo³ania przyjmuje dwie wartoœci -
	 * wspó³rzêdne x i y w grze. Ustawia równie¿ opcje ka¿dego z pól.
	 * 
	 * @param x Wspó³rzedna X-owa.
	 * @param y Wspó³rzedna Y-owa.
	 */
	public VField(final int x, final int y)
	{
		super("", CENTER);
		this.x = x;
		this.y = y;

		setPreferredSize(new Dimension(40, 40));
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		setOpaque(true);
	}

	/**
	 * Ustawia wartoœæ pola oraz kolor czcionki w zale¿noœci czy pole jest
	 * modyfikowalne czy jest to pole u¿ytkownika.
	 * 
	 * @param number Nowa wartoœæ.
	 * @param userInput Czy pole to pole u¿ytkownika.
	 */
	public void setNumber(final int number, final boolean userInput)
	{
		setForeground(userInput ? Color.BLUE : Color.BLACK);
		setText(number !=  Constants.INITIAL_VALUE ? number + "" : "");
	}

	/** 
	 * Zwraca wspólrzêdna X w grze.
	 */
	public int getFieldX()
	{
		return x;
	}

	/** 
	 * Zwraca wspó³rzêdn¹ Y w grze. 
	 */
	public int getFieldY()
	{
		return y;
	}
}
