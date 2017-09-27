package pl.pzygmunt.sudoku.events;

/**
 * Klasa reprezentujaca zdarzenia zaznaczenia pola.
 *
 *@author Piotr Zygmunt
 */
public final class FieldSelectingEvent extends ApplicationEvent
{
	/** Wsp�rzedna X-owa pola */
	private final int x;
	/** Wsp�rz�dna Y-owa pola */
	private final int y;

	/**
	 * Konstruktor podstawowy klasy - przyjmuje warto�ci wsp�rz�dnych nowo
	 * zaznaczonego pola.
	 * 
	 * @param x Wsp�lrz�dna X-owa.
	 * @param y Wsp�lrz�dna Y-owa.
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
	 * Zwracamy ws�rz�dn� Y 
	 */
	public int getPositionY()
	{
		return y;
	}
}
