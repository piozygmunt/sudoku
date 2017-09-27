package pl.pzygmunt.sudoku.model;

import java.awt.Point;

import pl.pzygmunt.sudoku.commons.Constants;
import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * Klasa reprezentujaca plansze do gry w Sudoku. 
 * 
 * @author Piotr Zygmunt
 */
final class Plansza
{
	/** Punkt reprezentujacy zaznaczone pole. */
	private Point selectedField;
	/** Dwuwymiarowa tablica pol do gry */
	private Pole gameFields[][];

	/** 
	 * Funkcja sprawdzajaca czy s� spe�nione warunki ko�ca gry na planszy. 
	 */
	public boolean isGameOver()
	{
		// czy w kazdym wierszu mamy po 9 poprawnie wstawionych liczb
		for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
		{
			if (!(gameFields[x][0].getAmountOfNumbers() == Constants.SUDOKU_SIZE))
			{
				return false;
			}
		}
		return true;

	}

	/** 
	 * Funkcja zwracaj�ca zaznaczone pole w postaci obiektu klasy Point. 
	 */
	public Point getSelectedField()
	{
		return selectedField;
	}

	/** 
	 * Funkcja zmieniaj�ca zaznaczone pole. Lub odznaczaj�ca aktualnie zaznaczone. 
	 * 
	 * @param pkt Obiekt typu Point - nowe pole do zaznaczenia.
	 */
	public void setSelectedField(final Point pkt)
	{

		if (pkt == null || pkt.equals(selectedField))
		{
			selectedField = null;
			return;
		} 
		else
		{
			this.selectedField = pkt;
		}

	}

	/** 
	 * Funkcja zmieniaj�ca warto�� w zaznaczonym polu. 
	 *
	 * @param value Nowa warto�� dla zaznaczeonego pola.
	 */
	public void setValueOfSelectedField(final int value)
	{
		final int positionX = (int) selectedField.getX();
		final int positionY = (int) selectedField.getY();
		
		gameFields[positionY][positionX].setValue(value);
	}

	/** 
	 * Funkcja zwracaj�ca warto�ci wszystkich p�l w postaci klasy DataPack. 
	 */
	public DataPack getValues()
	{
		DataPack dp = new DataPack();
		dp.values = new int[Constants.SUDOKU_SIZE][Constants.SUDOKU_SIZE];
		
		for (int y = 0; y < Constants.SUDOKU_SIZE; y++)
		{
			for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
			{
				dp.values[y][x] = gameFields[y][x].getValue();
			}
		}
		
		return dp;
	}

	/** 
	 * Funkcja zwracaj�ca poprawno�� wszystkich wype�nionych p�l u�ytkownika. 
	 */
	public DataPack getValidaty()
	{
		DataPack dp = new DataPack();
		dp.isCorrect = new boolean[Constants.SUDOKU_SIZE][Constants.SUDOKU_SIZE];
		
		for (int y = 0; y < Constants.SUDOKU_SIZE; y++)
		{
			for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
			{
				if (gameFields[y][x].isUserField() && gameFields[y][x].isModified())
				{
					dp.isCorrect[y][x] = gameFields[y][x].isCorrect();
				}
			}
		}
		
		return dp;
	}

	/**
	 * Funkcja sprawdzaj�ca czy mo�liwa jest zmiana warto�ci aktualnie zaznaczonego pola.
	 */
	public boolean isChangingOfSelectedFieldPossible()
	{
		if (selectedField == null)
		{
			return false;
		}
		
		final int positionY = (int) selectedField.getY();
		final int positionX = (int) selectedField.getX();

		return gameFields[positionY][positionX].isUserField();
	}

	/** 
	 * Funkcja usuwaj�ca warto�� z zaznaczonego pola. 
	 */
	public void clearSelectedField()
	{
		final int positionY = (int) selectedField.getY();
		final int positionX = (int) selectedField.getX();

		gameFields[positionY][positionX].setValue(Constants.INITIAL_VALUE);
	}
}
