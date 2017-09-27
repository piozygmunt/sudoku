package pl.pzygmunt.sudoku.events;

/**
 * Klasa reprezentujaca zdarzenia zmiany warto�ci w zaznaczonym polu.
 *
 *@author Piotr Zygmunt
 */
public final class ButtonNumberEvent extends ApplicationEvent
{
	/** Nowa warto�� */
	private final int value;

	/** 
	 * Konstruktor podstawowy klasy 
	 * 
	 * @param value Nowa warto�� pola.
	 */
	public ButtonNumberEvent(final int value)
	{
		this.value = value;
	}

	/**
	 * Zwraca zaznaczon� warto��.
	 */
	public int getValue()
	{
		return value;
	}
}
