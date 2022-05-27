package nttdata.javat1.game;

import nttdata.javat1.game.UI.Alignment;

/**
 * Clase Game
 * 
 * Esta clase es el núcleo del Pinball. Aquí es dónde se encontraría
 * el esqueleto del juego.
 * 
 * @author Carlos González Ruiz
 *
 */
public class Game {
	
	/** Atributos */
	private Menu menu = new Menu();
	private UI ui = menu.getUI();
	private Player player;
	
	public Game() {
		menu.setPlayer(player);
		ui = menu.getUI();
	}
	
	/**
	 * Función para la inicialización del juego.
	 * 
	 */
	public void launchAndStart() {
		// Mostrar marco superior y título.
		ui.showTop();
		
		ui.showText(Alignment.CENTER, "P  I  N  B  A  L  L");
		ui.showText(Alignment.CENTER, "-  -  -  -  -  -  -");
		ui.showText(Alignment.CENTER, "");
		
		menu.showOptions();
		
		mainLoop();
		
		ui.showTop();
	}
	
	/**
	 * Función dónde se encuentra el corazón del juego, es decir, no se cierra
	 * la aplicación hasta que se haya elegido salir del juego.
	 * 
	 */
	public void mainLoop() {
		String option = "";
		boolean exit = false;
		
		do {
			ui.showText(20, "");
			option =  ui.promptData("Elija una opción: ");
			
			switch (option) {
				case "1": // Nuevo juego
					menu.newGame();
					break;
				
				case "2": // Ver puntuaciones
					menu.seeScores();
					break;
					
				case "3": // Borrar datos
					menu.eraseData();
					break;
				
				case "4": // Información
					menu.information();
					break;
				
				case "0": // Salir del juego
					exit = true;
					break;
				
				default: // Opción no valida
					ui.showText(15, "\"" + option + "\" no es una opción válida.");
					break;
			}
		} while (!exit);
		
		// Cerrar el scanner.
		menu.getUI().getScanner().close();
	}
	
}
