package pl.pzygmunt.sudoku.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import pl.pzygmunt.sudoku.commons.Constants;

/**
 * Klasa reprezentuj�ca wizualn� posta� pojedynczego pola do gry
 * 
 * @author Piotr Zygmunt
 */
class VField extends JLabel
{
	/** Numer wersji klasy. */
	private static final long serialVersionUID = 1L;
	/** Wsp�rz�dna X-owa w grze. */
	private final int x;
	/** Wsp�rz�dna y-owa w grze. */
	private final int y;

	/**
	 * Konstruktor podstawowy. Jako parametry wywo�ania przyjmuje dwie warto�ci -
	 * wsp�rz�dne x i y w grze. Ustawia r�wnie� opcje ka�dego z p�l.
	 * 
	 * @param x Wsp�rzedna X-owa.
	 * @param y Wsp�rzedna Y-owa.
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
	 * Ustawia warto�� pola oraz kolor czcionki w zale�no�ci czy pole jest
	 * modyfikowalne czy jest to pole u�ytkownika.
	 * 
	 * @param number Nowa warto��.
	 * @param userInput Czy pole to pole u�ytkownika.
	 */
	public void setNumber(final int number, final boolean userInput)
	{
		setForeground(userInput ? Color.BLUE : Color.BLACK);
		setText(number !=  Constants.INITIAL_VALUE ? number + "" : "");
	}

	/** 
	 * Zwraca wsp�lrz�dna X w grze.
	 */
	public int getFieldX()
	{
		return x;
	}

	/** 
	 * Zwraca wsp�rz�dn� Y w grze. 
	 */
	public int getFieldY()
	{
		return y;
	}
}
