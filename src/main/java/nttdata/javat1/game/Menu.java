package nttdata.javat1.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.TreeSet;

import nttdata.javat1.game.UI.Alignment;

/**
 * Clase Menu
 * 
 * Esta clase es la que se encarga de mostrar y gestionar los distintos menús.
 * 
 * @author Carlos González Ruiz
 *
 */
public class Menu {
	
	/** Constantes */
	private final String SAVEFILE = "scores.dat";
	
	/** Atributos */
	private UI ui = new UI();
	private Player player;
	
	/**
	 * Función para mostrar las opciones del menú principal del Pinball.
	 * 
	 */
	public void showOptions() {
		ui.showText(20, "1. Nuevo juego");
		ui.showText(20, "2. Ver puntuaciones");
		ui.showText(20, "3. Eliminar datos");
		ui.showText(20, "4. Información");
		ui.showText(20, "");
		ui.showText(20, "0. Salir del juego");
	}
	
	/**
	 * Función para comenzar una nueva partida.
	 * Llama a la función 'saveScore()' cuando termina la partida
	 * para guardar los datos.
	 * 
	 */
	public void newGame() {
		// Pedir nombre
		String name = ui.promptData("Nombre del jugador: ");
		
		// Crear jugador
		player = new Player(name);
		
		Ball[] balls = player.getBalls();
		for (int i = 0; i < balls.length; ++i) {
			
			ui.showText(15, "--- Intento nº" + i + " ---");
			
			// Pedir datos
			float seed;
			do {
				// Comprobamos que el valor introducio sea de coma flotante y no NaN.
				try {
					seed = Float.parseFloat(ui.promptData("Fuerza de la bola: "));
				} catch (NumberFormatException e) {
					seed = Float.NaN;
				}
			} while (Float.isNaN(seed));
			
			// Lanzar bola
			ui.showText(15, "Lanzando bola...");
			balls[i].launch((int)(seed), ui);
			
			// Mostrar info
			ui.showText(15, "Rebotes: " + balls[i].getBounces());
			ui.showText(15, "Bonuses: " + balls[i].getBonuses());
			ui.showText(15, "Diferencia: " + (balls[i].getDistance() - balls[i].DISTANCE_MAX));
			ui.showText(15, "Total: " + balls[i].getScore());
		}
		
		// Mostrar puntuación total y guardar datos.
		ui.showText(15, "Has obtenido una puntuación total de " + player.getScore());
		saveScore();
	}
	
	/**
	 * Función para mostrar un listado de puntuaciones.
	 * Llama a la función 'loadScore()' para obtener un listado
	 * de jugadores.
	 * 
	 */
	public void seeScores() {
		// Obtener lista de jugadores
		TreeSet<Player> list = loadScore();
		
		// Mostrar 5 primeros de la lista
		ui.showText(Alignment.CENTER, "PUNTUACIONES");
		ui.showText(Alignment.CENTER, "------------");
		
		Iterator<Player> it = list.iterator();
		for (int i = 1; i <= 5 && it.hasNext(); ++i) {
			Player element = (Player) it.next();
			ui.showText(Alignment.CENTER, i + ". " + element.getName() + ": " + element.getScore());
		}
	}
	
	/**
	 * Función para eliminar el fichero SAVEFILE para así eliminar todos sus datos.
	 * 
	 */
	public void eraseData() {
        try {
            Files.delete(FileSystems.getDefault().getPath(SAVEFILE));
            ui.showText(15, "Todos los datos han sido eliminados correctamente.");
        } catch (NoSuchFileException x) {
            //e.printStackTrace();
        } catch (IOException x) {
            //e.printStackTrace();
        }
	}
	
	/**
	 * Función para mostrar información sobre el juego.
	 * 
	 */
	public void information() {
		ui.showText(15, "");
		ui.showText(Alignment.CENTER, "INFORMACIÓN");
		ui.showText(Alignment.CENTER, "-----------");
		ui.showText(Alignment.CENTER, "Juego de Pinball hecho por Carlos González Ruiz para el taller práctico nº1 de Java.");
		ui.showText(15, "");
	}
	
	/**
	 * Función para guardar la puntuación del jugador actual dentro de SAVEFILE.
	 * 
	 */
	public void saveScore() {
		try {
			// Obtener lista orignal (treelist + loadScore)
			TreeSet<Player> list = loadScore();
			
			// Insertar datos del jugador
			list.add(player);
			
			// Escribir lista dentro de SAVEFILE
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(SAVEFILE));
			
			file.writeObject(list);
			
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Función para cargar una lsita de puntuaciones que se encuentra dentro de SAVEFILE.
	 * 
	 * @return lista de puntuaciones
	 */
	public TreeSet<Player> loadScore() {
		TreeSet<Player> list = new TreeSet<Player>();
		
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(SAVEFILE));
			
			list = (TreeSet<Player>)(file.readObject());
			
			file.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * Getter del atributo 'ui'.
	 * 
	 * @return atributo 'ui'
	 */
	public UI getUI() {
		return ui;
	}
	
	/**
	 * Setter del atributo 'ui'.
	 * 
	 * @param ui
	 */
	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	/**
	 * Getter del atributo 'player'.
	 * 
	 * @return atributo 'player'
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Setter del atributo 'player'.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
