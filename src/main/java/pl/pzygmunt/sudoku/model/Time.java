package pl.pzygmunt.sudoku.model;

/**
 * klasa reprezentujaca czas gry
 * 
 * @author Piotr Zygmunt
 */
public class Time
{
	/** Czas w sekundach od rozpoczecia aktualnej gry.*/
	private int gameTime;
	
	/** 
	 * Funkcja zwraca aktualny czas gry. 
	 */
	public int getGameTime() 
	{
		return gameTime;
	}
	
	/** 
	 * Rozpoczyna ponowne odliczanie czasu od 0.
	 */
	public void reset()
	{
		gameTime = 0;
	}
	
	 /** 
	  * Inkrementuje licznik czasu.
	  */
	public void increment()
	{
		gameTime++;
	}
}