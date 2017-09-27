package pl.pzygmunt.sudoku.events;

/**
 * Klasa reprezentujaca zdarzenia zmiany wartoœci w zaznaczonym polu.
 *
 *@author Piotr Zygmunt
 */
public final class ButtonNumberEvent extends ApplicationEvent
{
	/** Nowa wartoœæ */
	private final int value;

	/** 
	 * Konstruktor podstawowy klasy 
	 * 
	 * @param value Nowa wartoœæ pola.
	 */
	public ButtonNumberEvent(final int value)
	{
		this.value = value;
	}

	/**
	 * Zwraca zaznaczon¹ wartoœæ.
	 */
	public int getValue()
	{
		return value;
	}
}
