package pl.pzygmunt.sudoku.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import pl.pzygmunt.sudoku.commons.Constants;
import pl.pzygmunt.sudoku.commons.DataPack;

/**
 * Konstruktor klasy reprezentuj¹cej panel z przyciskami
 * 
 * @author Piotr Zygmunt
 */
public class ButtonPanel extends JPanel
{
	/** Numer wersji. */
	private static final long serialVersionUID = 1L;
	/** Przyciski odpowiadaj¹ce za ró¿ne funkcje programu. */
	private final JButton newBtn, checkBtn, exitBtn, clearSelectionBtn;
	/** Grupa przycisków. */
	private final ButtonGroup numbersGrp;
	/** Przyciski odpowiadaj¹ce cyfrom 1-9 */
	private final JToggleButton[] numbersBtn;
	/** Pole w którym wyœwiela siê czas gry */
	private final JLabel time;

	/**
	 * Konstruktor podstawowy.
	 */
	public ButtonPanel()
	{
		super(new BorderLayout());
		// dodanie panelu pomocniczego
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.PAGE_AXIS));
		add(helpPanel, BorderLayout.NORTH);

		// dodanie panelu dla opcji ( przycisków )
		JPanel opcje = new JPanel(new FlowLayout(FlowLayout.LEADING));
		opcje.setBorder(BorderFactory.createTitledBorder(" Opcje "));
		helpPanel.add(opcje);

		// utworzenie buttonow i dodanie ich do panelu opcji
		newBtn = new JButton("New");
		newBtn.setFocusable(false);
		opcje.add(newBtn);

		checkBtn = new JButton("Check");
		checkBtn.setFocusable(false);
		opcje.add(checkBtn);

		clearSelectionBtn = new JButton("ClearS");
		clearSelectionBtn.setFocusable(false);
		opcje.add(clearSelectionBtn);

		exitBtn = new JButton("Exit");
		exitBtn.setFocusable(false);
		opcje.add(exitBtn);

		// utworzenie panelu na ktorym znajduja sie wszystkie cyfry
		JPanel liczby = new JPanel();
		liczby.setLayout(new BoxLayout(liczby, BoxLayout.PAGE_AXIS));
		liczby.setBorder(BorderFactory.createTitledBorder(" Liczby "));
		helpPanel.add(liczby);

		// utworzenie panelu pomocniczego dla liczb
		JPanel liczbyPanel = new JPanel(new GridLayout(3, 3));
		liczby.add(liczbyPanel);

		// utworzenie wszystkich butonow i dodanie ich do panelu
		numbersGrp = new ButtonGroup();
		numbersBtn = new JToggleButton[9];
		for (int i = 0; i < Constants.SUDOKU_SIZE; i++)
		{
			numbersBtn[i] = new JToggleButton("" + (i + 1));
			numbersBtn[i].setPreferredSize(new Dimension(40, 40));
			numbersBtn[i].setFocusable(false);
			numbersGrp.add(numbersBtn[i]);
			liczbyPanel.add(numbersBtn[i]);
		}
		
		JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		timePanel.setBorder(BorderFactory.createTitledBorder(" Czas gry "));
		helpPanel.add(timePanel);
		
		time = new JLabel();
		timePanel.add(time);
	
	}

	/**
	 * Funkcja dodaj¹ca nas³uchiwacza do wszystkich komponentów panelu.
	 *
	 *@param buttonListener Referencja na obiekt typu ButtonlListener.
	 */
	public void setController(final ButtonListener buttonListener)
	{
		newBtn.addActionListener(buttonListener);
		checkBtn.addActionListener(buttonListener);
		exitBtn.addActionListener(buttonListener);
		clearSelectionBtn.addActionListener(buttonListener);
		for (int i = 0; i < 9; i++)
			numbersBtn[i].addActionListener(buttonListener);
	}

    /**
     * Wyswietlenie aktualnego czasu gry.
     *
     *@param dp Referencja na makieta stworzona przez model.
     */
	public void refreshTime(final DataPack dp)
	{
		time.setText("Twoj czas gry to: " + dp.time);
	}
}