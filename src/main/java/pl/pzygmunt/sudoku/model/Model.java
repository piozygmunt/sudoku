package pl.pzygmunt.sudoku.model;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * Klasa reprezentujaca model gry Sudoku. Odpowiedzialna za podstawow� funkcjonalno�� gry.
 * 
 * @author Piotr Zygmunt
 */
public class Model
{
	/** Aktualna plansza do gry */
	private Plansza gameBoard;
	/** Lista dostepnych plansz. */
	private final ArrayList<String> possibleBoards;
    /** Czas gry. */
	private final Time time;
	
	/** 
	 * Konstruktor podstawowy modelu 
	 */
	public Model()
	{
		possibleBoards = new ArrayList<>();
		possibleBoards.add("test.xml");
		possibleBoards.add("test2.xml");
		possibleBoards.add("test3.xml");
		time = new Time();
		try
		{
			newGame();
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/** 
	 * Funkcja inkrementujaca licznik czasu gry. 
	 */
	public void incrementTime()
	{
		time.increment();
	}
	
	/** 
	 * Funkcja zwracajaca aktualny czas gry jako jedno z p�l klasy DataPack - makiety.
	 */
	public DataPack getTime()
	{
		DataPack dp = new DataPack();
		dp.time = time.getGameTime();
		return dp;
	}
	
	/** 
	 * Funkcja sprawdzajaca czy s� spe�nione warunki ko�ca gry. 
	 */
	public boolean isGameOver()
	{
		return gameBoard.isGameOver();
	}

	/** 
	 * Funkcja usuwaj�ca warto�� z zaznaczonego pola. 
	 */
	public void clearSelectedField()
	{
		gameBoard.clearSelectedField();
	}

	/** 
	 * Funkcja zwracaj�ca zaznaczone pole(wsp�rz�dne) w postaci obiektu klasy Point. 
	 */
	public Point getSelectedField()
	{
		return gameBoard.getSelectedField();
	}

	/** 
	 * Funkcja zwracaj�ca poprawno�� wszystkich modyfikowanych p�l u�ytkownika w postaci obiektu DataPack. 
	 */
	public DataPack getValidaty()
	{
		return gameBoard.getValidaty();
	}

	/** 
	 * Funkcja zwracaj�ca warto�ci wszystkich wype�nionych p�l w postaci obiektu DataPack.
	 */
	public DataPack getValues()
	{
		return gameBoard.getValues();
	}

	/** 
	 * Funkcja sprawdzaj�ca czy mo�liwa jest zmiana warto�ci aktualnego pola. 
	 */
	public boolean isChangingOfSelectedFieldPossible()
	{
		return gameBoard.isChangingOfSelectedFieldPossible();
	}

	/** 
	 * Funkcja tworz�ca now� plansze. Mo�liwe nazwy plik�w w kt�rych zawarte s� plansze znajduj� si� w li�cie 
	 * possibleBoards. Nastepnie lista ta jest mieszana i wybieramy, pierwszy jej element.
	 */
	public void newGame() throws FileNotFoundException
	{
		Collections.shuffle(possibleBoards);
		readBoardFromFile(possibleBoards.get(0));
		time.reset();
	}

	/** 
	 * Funkcja wczytuj�ca plansze z danego pliku XML. 
	 * 
	 * @param FileName �ciezka do pliku.
	 */
	public void readBoardFromFile(final String FileName) throws FileNotFoundException
	{
		FileReader fileReader = new FileReader(FileName);
		XStream xstream = new XStream(new DomDriver());
		setGameBoard((Plansza) xstream.fromXML(fileReader));
	}

	/** 
	 * Funkcja ustawiaj�ca now�, cz�sciowo wype�nion� plansze. 
	 * 
	 * @param gameBoard Referencja na nowa plansza.
	 */
	private void setGameBoard(final Plansza gameBoard)
	{
		this.gameBoard = gameBoard;
	}

	/** 
	 * Funkcja zmieniaj�ca zaznaczone pole.
	 * 
	 * @param pkt Obiekt typu Point - nowy pukt do zaznaczenia.
	 */
	public void setSelectedField(final Point pkt)
	{
		gameBoard.setSelectedField(pkt);
	}

	/** 
	 * Funkcja zmieniaj�ca warto�� w zaznaczonym polu. 
	 * 
	 * @param value Nowa warto�� dla zaznaczonego pola.
	 */
	public void setValueOfSelectedField(final int value)
	{
		gameBoard.setValueOfSelectedField(value);
	}
}
