package pl.pzygmunt.sudoku.model;

import java.util.*;

import pl.pzygmunt.sudoku.commons.Constants;

/**
 * Klasa reprezentuj�ca pojedyncze pole na planszy do gry.
 *
 *@author Piotr Zygmunt
 */
class Pole
{
	/** Lista setow. Kazdy set zawiera liczby : w rzedzie , w kolumnie, w kwadracie w ktorym znajduje si� to pole.*/
	private final List<Set<Integer>> fieldSets;
	/** Wartosc danego pola - domyslnie zero. */
	private int fieldValue = Constants.INITIAL_VALUE;
	/** Czy jest to pole u�ytkownika - z mo�liw� modyfikacj�. */
	private boolean isUserField;
	/** Czy warto�� w tym polu jest poprawna. */
	private boolean isCorrect;

	/** 
	 * Podstawowy konstruktor. 
	 */
	public Pole()
	{
		fieldSets = new ArrayList<>();
		isCorrect = false;
	}

	/** 
	 * Czy pole jest zmodyfikowane. 
	 */
	public boolean isModified()
	{
		if (fieldValue == Constants.INITIAL_VALUE)
		{
			return false;
		}
		return true;
	}

	/** 
	 * Dodanie do listy kolejnych set�w. 
	 *
	 * @param fieldSet Referencja na obiekt klasy Set, ktory chcemy doda� do Listy set�w.
	 */
	public void addSet(final Set<Integer> fieldSet)
	{
		fieldSets.add(fieldSet);
	}

	/** 
	 * Sprawdzenie czy podana liczba jest mo�liwa w tym polu. 
	 * 
	 * @param value Sprawdzana warto��.
	 */
	public boolean checkIsNumberPossible(final int value)
	{
		for (Set<Integer> set : fieldSets)
		{
			if (set.contains(value))
			{
				return false;
			}
		}
		return true;
	}

	/** 
	 * Sprawdzenie czy aktualna liczba wpisana w tym polu jest poprawna. 
	 */
	public boolean checkIsValueCorrect()
	{
		isCorrect = checkIsNumberPossible(fieldValue);
		return isCorrect;
	}

	/** 
	 * Ustawienie nowej warto�ci pola. 
	 * 
	 * @param fieldValue Nowa warto�� pola.
	 */
	public void setValue(final int fieldValue)
	{
		// jesli poprzednia byla poprawna usuwamy z set�w
		if (isCorrect)
		{
			for (Set<Integer> set : fieldSets)
			{
				set.remove(this.fieldValue);
			}
		}
		this.fieldValue = fieldValue;
		if (checkIsValueCorrect())
		{
			updateSets();
		}
	}

	/** 
	 * Ustawiamy czy jest to pole u�ytkownika. 
	 * 
	 * @param isUserField Warto�� boolowska ,true je�li pole u�ytkownika.
	 */
	public void setIsUserField(final boolean isUserField)
	{
		this.isUserField = isUserField;
	}

	/** 
	 * Sprawdzamy czy jest to pole u�ytkownika. 
	 */
	public boolean isUserField()
	{
		return isUserField;
	}

	/** 
	 * Zwracamy aktualna warto�� pola 
	 */
	public int getValue()
	{
		return fieldValue;
	}

	/** 
	 * Uaktualniamy sety 
	 */
	public void updateSets()
	{
		if (this.fieldValue != Constants.INITIAL_VALUE)
		{
			for (Set<Integer> set : fieldSets)
			{
				set.add(fieldValue);
			}
		}
	}

	/** 
	 * Sprawdzamy czy wartosc jest poprawna. 
	 */
	public boolean isCorrect()
	{
		if (isCorrect)
		{
			return true;
		}
		if (checkIsValueCorrect())
		{
			updateSets();
			return true;
		}
		return false;
	}

	/** 
	 * Sprawdzamy ile mamy poprawnych p�l w wierszu w kt�rym znajduje si� dane pole. 
	 */
	public int getAmountOfNumbers()
	{
		return fieldSets.get(0).size();
	}
}
