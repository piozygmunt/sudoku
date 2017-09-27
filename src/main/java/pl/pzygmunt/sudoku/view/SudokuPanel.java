package pl.pzygmunt.sudoku.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pl.pzygmunt.sudoku.commons.Constants;
import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * Klasa tworz�ca wizualn� plansze do gry w postaci 81 p�l.
 * 
 * @author Piotr Zygmunt
 */
class SudokuPanel extends JPanel
{
	/** Numer wersji klasy. */
	private static final long serialVersionUID = 1L;
	/** Liczba paneli jakie chcemy utworzy�. */
	private final static int PANELS_AMOUNT = 3;
	/** Dwuwymiarowa tablica p�l do gry. */
	private final VField[][] fields; // Array of fields.
	/** Dwuwymiarowa tablica paneli. */
	private final JPanel[][] panels; // Panels holding the fields.
	/** Informacja o zaznaczonym aktualnie polu. */
	private Point selectedField;

	/** 
	 * Konstruktor podstawowy. Dodaje panele pomocnicze oraz pola do gry. 
	 */
	public SudokuPanel()
	{
		super(new GridLayout(PANELS_AMOUNT, PANELS_AMOUNT));
		panels = new JPanel[PANELS_AMOUNT][PANELS_AMOUNT];
		for (int y = 0; y < PANELS_AMOUNT; y++)
		{
			for (int x = 0; x < PANELS_AMOUNT; x++)
			{
				panels[y][x] = new JPanel(new GridLayout(PANELS_AMOUNT,PANELS_AMOUNT));
				panels[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(panels[y][x]);
			}
		}

		fields = new VField[Constants.SUDOKU_SIZE][Constants.SUDOKU_SIZE];
		for (int y = 0; y < Constants.SUDOKU_SIZE; y++)
		{
			for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
			{
				fields[y][x] = new VField(x, y);
				panels[y / PANELS_AMOUNT][x / PANELS_AMOUNT].add(fields[y][x]);
			}
		}
		selectedField = null;
	}

	/**
	 * Ustawienie warto�ci dla ka�dego z p�l. Parametrem wywo�ania jest makieta tworzona przez model.
	 *
	 *@param dp Referencja na makiete stworzona przez model.
	 */
	public void setGame(final DataPack dp)
	{
		for (int y = 0; y < Constants.SUDOKU_SIZE; y++)
		{
			for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
			{
				fields[y][x].setBackground(Color.WHITE);
				fields[y][x].setNumber(dp.values[y][x], false);
			}
		}
	}

	/**
	 * Zaznacza poprawne i b��dne pola. Jako parametr wywo�ania przyjmuje
	 * makiete w ktorej znajduje si� tablica warto�ci logicznych kt�re 
	 * okre�laj� poprawno�� wype�nionych ju� p�l u�ytkownika.
	 * 
	 * @param dp Referencja na makiete.
	 */

	public void setGameCheck(final DataPack dp)
	{
		for (int y = 0; y < Constants.SUDOKU_SIZE; y++)
		{
			for (int x = 0; x < Constants.SUDOKU_SIZE; x++)
			{
				fields[y][x].setBackground(Color.WHITE);
				if (fields[y][x].getForeground().equals(Color.BLUE))
				{
					fields[y][x].setBackground(dp.isCorrect[y][x] ? Color.GREEN : Color.RED);
				}
			}
		}
	}

	/**
	 * Dodanie Listenera do ka�dego z p�l na planszy.
	 *
	 *@param sudokuListener Referencja na obiekt typu SudokuListener.
	 */
	public void setController(final SudokuListener sudokuListener)
	{
		for (JPanel[] row : panels)
		{
			for (JPanel panel : row)
			{
				panel.addMouseListener(sudokuListener);
			}
		}
	}

	/** 
	 * Zaznaczenie wybranego pola na planszy. 
	 * 
	 * @param pkt Referencja na obiekt typu Point- oznaczaj�cy pole do zaznaczenia.
	 */
	public void setSelectedField(final Point pkt)
	{
		final int newPktPosX = (int) pkt.getX();
		final int newPktPosY = (int) pkt.getY();

		if (pkt.equals(selectedField))
		{
			fields[newPktPosY][newPktPosX].setBackground(Color.WHITE);
			selectedField = null;
		} 
		else
		{
			if (selectedField != null)
			{
				fields[(int) selectedField.getY()][(int) selectedField.getX()].setBackground(Color.WHITE);
			}
			fields[newPktPosY][newPktPosX].setBackground(Color.CYAN);
			selectedField = pkt;
		}

	}

	/** 
	 * Zmiana warto�� zaznaczonego pola. 
	 * 
	 * @param value Nowa warto�� dla pola.
	 */
	public void setValueOfSelectedField(final int value)
	{
		final int positionX = (int) selectedField.getX();
		final int positionY = (int) selectedField.getY();

		fields[positionY][positionX].setNumber(value, true);
	}

	/** 
	 * Odznaczenie wszystkich p�l - r�wnie� tych modyfikowanych przez setGameCheck. 
	 */
	public void clearSelection()
	{
		for (VField[] row : fields)
		{
			for (VField field : row)
			{
				field.setBackground(Color.WHITE);
			}
		}
		selectedField = null;
	}

	/** 
	 * Wykasowanie warto�ci z zaznaczonego pola. 
	 */
	public void clearSelectedField()
	{
		final int positionX = (int) selectedField.getX();
		final int positionY = (int) selectedField.getY();

		fields[positionY][positionX].setNumber(Constants.INITIAL_VALUE, true);
		fields[positionY][positionX].setForeground(Color.BLACK);
	}
}
