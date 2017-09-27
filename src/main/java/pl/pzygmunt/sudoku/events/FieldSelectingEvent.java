package pl.pzygmunt.sudoku.events;

/**
 * Klasa reprezentujaca zdarzenia zaznaczenia pola.
 *
 *@author Piotr Zygmunt
 */
public final class FieldSelectingEvent extends ApplicationEvent
{
	/** Wspó³rzedna X-owa pola */
	private final int x;
	/** Wspó³rzêdna Y-owa pola */
	private final int y;

	/**
	 * Konstruktor podstawowy klasy - przyjmuje wartoœci wspó³rzêdnych nowo
	 * zaznaczonego pola.
	 * 
	 * @param x Wspólrzêdna X-owa.
	 * @param y Wspólrzêdna Y-owa.
	 */
	public FieldSelectingEvent(final int x, final int y)
	{
		this.x = x;
		this.y = y;

	}

	/** 
	 * Zwracamy wspolrzedna X 
	 */
	public int getPositionX()
	{
		return x;
	}

	/** 
	 * Zwracamy wsó³rzêdn¹ Y 
	 */
	public int getPositionY()
	{
		return y;
	}
}
