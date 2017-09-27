package pl.pzygmunt.sudoku.controller;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.pzygmunt.sudoku.model.Model;
import pl.pzygmunt.sudoku.events.ApplicationEvent;
import pl.pzygmunt.sudoku.events.ButtonCheckEvent;
import pl.pzygmunt.sudoku.events.ButtonExitEvent;
import pl.pzygmunt.sudoku.events.ButtonNewEvent;
import pl.pzygmunt.sudoku.events.ButtonNumberEvent;
import pl.pzygmunt.sudoku.events.DeselectFieldsEvent;
import pl.pzygmunt.sudoku.events.FieldSelectingEvent;
import pl.pzygmunt.sudoku.events.RemoveNumberEvent;
import pl.pzygmunt.sudoku.events.TimeEvent;
import pl.pzygmunt.sudoku.view.View;

/**
 * Klasa kontrolera. Klasa odpowiedzialna za wspó³dzialanie modelu i widoku.
 * Przyjmuje zdarzenia oraz odpowiednio na nie reaguje.
 * 
 * @author Piotr Zygmunt
 */
public final class Controller
{
	/** Referencja na obiekt klasy model */
	private final Model model;
	/** Referencja na obiekt klasywidok */
	private final View view;
	/** Mapa ktora dopasowuje strategie do danego zdarzeni */
	private final Map<Class<? extends ApplicationEvent>, ApplicationStrategy> eventDictionary = new HashMap<Class<? extends ApplicationEvent>, ApplicationStrategy>();
	/** Referencja na kolejke zdarzen */
	private final BlockingQueue<ApplicationEvent> bq;

	/**
	 * Konstruktor podstawowy przyjmuje referencje na : model, widok , kolejke
	 * zdarzen.
	 * 
	 * @param model Referencja na model.
	 * @param view Referencja na widok.
	 * @param bq Referencja na kolejke zdarzeñ.
	 */
	public Controller(final Model model, final View view, final BlockingQueue<ApplicationEvent> bq)
	{
		this.model = model;
		this.view = view;
		this.bq = bq;
		/* Zapelnienie mapy */
		eventDictionary.put(ButtonNewEvent.class, new ButtonNewStrategy());
		eventDictionary.put(ButtonCheckEvent.class, new ButtonCheckStrategy());
		eventDictionary.put(ButtonExitEvent.class, new ButtonExitStrategy());
		eventDictionary.put(ButtonNewEvent.class, new ButtonNewStrategy());
		eventDictionary.put(FieldSelectingEvent.class, new FieldSelectingStrategy());
		eventDictionary.put(ButtonNumberEvent.class, new ButtonNumberStrategy());
		eventDictionary.put(DeselectFieldsEvent.class, new DeselectFieldsStrategy());
		eventDictionary.put(RemoveNumberEvent.class, new RemoveNumberStrategy());
		eventDictionary.put(TimeEvent.class, new TimeStrategy());
		/* Wyswietlenie wygenerowanej gry */
		view.setGame(model.getValues());
	}

	/**
	 * Glowna funkcja kontrolera. W nieskonczonej petli oczekuje na zdarzenia.
	 * Jesli jakies jest to jest odbiera i odpowiednio reaguje ( wybiera
	 * strategie ).
	 */
	public void dzialaj()
	{
		while(true)
		{
			try
			{
				ApplicationEvent evnt = bq.take();
				ApplicationStrategy strategy = eventDictionary.get(evnt.getClass());
				strategy.execute(evnt);
			} 
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy Reprezentuje strategi
	 * na zdarzenie rozpoczêcia nowej gry.
	 */
	private final class ButtonNewStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			try
			{
				model.newGame();
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("Nie znaleziono pliku z plansza.");
				e.printStackTrace();
			}
			view.setGame(model.getValues());
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy Reprezentuje strategi
	 * na zdarzenie sprawdzenia poprawnosci aktualnej gry. Jesli cala plansza
	 * jest wypelniona poprawnie - koniec gry.
	 */
	private final class ButtonCheckStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			if (model.isGameOver())
			{
				view.gameOver(model.getTime());
				try
				{
					model.newGame();
				} 
				catch (FileNotFoundException e)
				{
					System.out.println("Nie znaleziono pliku z plansza.");
					e.printStackTrace();
				}
				view.setGame(model.getValues());
				return;
			}
			view.setGameCheck(model.getValidaty());
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy Reprezentuje strategie
	 * na zdarzenie wyjscia z aplikacji.
	 */
	private final class ButtonExitStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			System.exit(0);
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy. Reprezentuje strategie
	 * na zdarzenie zaznaczenie lub odznaczenie wybranego pola.
	 */
	private final class FieldSelectingStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			final FieldSelectingEvent event = (FieldSelectingEvent) evnt;
			final Point pkt = new Point(event.getPositionX(), event.getPositionY());
			model.setSelectedField(pkt);
			view.setSelectedField(pkt);
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy. Reprezentuje strategie
	 * na zdarzenie zmiany wartosci danego pola(zaznaczonego pola).
	 */
	private final class ButtonNumberStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			ButtonNumberEvent evrnt = (ButtonNumberEvent) evnt;
			if (model.getSelectedField() == null || !model.isChangingOfSelectedFieldPossible())
			{
				return;
			}
			model.setValueOfSelectedField(evrnt.getValue());
			view.setValueOfSelectedField(evrnt.getValue());
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy. Reprezentuje strategie
	 * na zdarzenie odznaczenia wszystkich pol na planszy.
	 */
	private final class DeselectFieldsStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			model.setSelectedField(null);
			view.clearSelection();
		}
	}

	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy. Reprezentuje strategie
	 * na zdarzenie usuniecia wartosci z danego pola(zaznaczonego pola).
	 */
	private final class RemoveNumberStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			if (model.getSelectedField() == null || !model.isChangingOfSelectedFieldPossible())
			{
				return;
			}
			model.clearSelectedField();
			view.clearSelectedField();
		}
	}
	
	/**
	 * Prywatna klasa dziedziczaca po ApplicationStrategy. Reprezentuje strategie
	 * na zdarzenie TimeEvent - inkrementowanie czas o jeden.
	 */
	private final class TimeStrategy extends ApplicationStrategy
	{
		void execute(final ApplicationEvent evnt)
		{
			model.incrementTime();
			view.refreshTime(model.getTime());
		}
	}
}