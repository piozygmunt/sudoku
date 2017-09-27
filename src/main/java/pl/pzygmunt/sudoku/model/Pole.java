package pl.pzygmunt.sudoku.model;

import java.util.*;

import pl.pzygmunt.sudoku.commons.Constants;

/**
 * Klasa reprezentuj¹ca pojedyncze pole na planszy do gry.
 *
 *@author Piotr Zygmunt
 */
class Pole
{
	/** Lista setow. Kazdy set zawiera liczby : w rzedzie , w kolumnie, w kwadracie w ktorym znajduje siê to pole.*/
	private final List<Set<Integer>> fieldSets;
	/** Wartosc danego pola - domyslnie zero. */
	private int fieldValue = Constants.INITIAL_VALUE;
	/** Czy jest to pole u¿ytkownika - z mo¿liw¹ modyfikacj¹. */
	private boolean isUserField;
	/** Czy wartoœæ w tym polu jest poprawna. */
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
	 * Dodanie do listy kolejnych setów. 
	 *
	 * @param fieldSet Referencja na obiekt klasy Set, ktory chcemy dodaæ do Listy setów.
	 */
	public void addSet(final Set<Integer> fieldSet)
	{
		fieldSets.add(fieldSet);
	}

	/** 
	 * Sprawdzenie czy podana liczba jest mo¿liwa w tym polu. 
	 * 
	 * @param value Sprawdzana wartoœæ.
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
	 * Ustawienie nowej wartoœci pola. 
	 * 
	 * @param fieldValue Nowa wartoœæ pola.
	 */
	public void setValue(final int fieldValue)
	{
		// jesli poprzednia byla poprawna usuwamy z setów
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
	 * Ustawiamy czy jest to pole u¿ytkownika. 
	 * 
	 * @param isUserField Wartoœæ boolowska ,true jeœli pole u¿ytkownika.
	 */
	public void setIsUserField(final boolean isUserField)
	{
		this.isUserField = isUserField;
	}

	/** 
	 * Sprawdzamy czy jest to pole u¿ytkownika. 
	 */
	public boolean isUserField()
	{
		return isUserField;
	}

	/** 
	 * Zwracamy aktualna wartoœæ pola 
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
	 * Sprawdzamy ile mamy poprawnych pól w wierszu w którym znajduje siê dane pole. 
	 */
	public int getAmountOfNumbers()
	{
		return fieldSets.get(0).size();
	}
}
