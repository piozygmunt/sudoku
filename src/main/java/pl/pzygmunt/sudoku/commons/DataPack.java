package pl.pzygmunt.sudoku.commons;

/**
 * Klasa s�u��ca do przekazywania warto�ci z modelu do widoku ( makieta ).
 * 
 * @author Piotr Zygmunt
 */
public final class DataPack
{
	/** Pola gry. */
	public int[][] values;
	/** Poprawnosc pol u�ytkownika. */
	public boolean[][] isCorrect;
	/** Pole do przekazywania czasu gry. */
	public int time;
}
