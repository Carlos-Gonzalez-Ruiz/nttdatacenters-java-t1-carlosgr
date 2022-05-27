package nttdata.javat1;

import nttdata.javat1.game.Game;

/**
 * Taller Práctico 1 Java - Pinball
 * 
 * Este taller consiste en un juego de Pinbal hecho en Java cuya jugabilidad
 * se basa en simular la puntuación según el resultado de la trayectoria de 
 * una bola habiendo mostrado previamente un mapa del nivel.
 * 
 * @author Carlos González Ruiz
 *
 */
public class App 
{
	
	/**
	 * Punto de entrada del Pinbal.
	 * 
	 * @param args
	 */
	public static void main( String[] args )
    {
        Game game = new Game();
        
        game.launchAndStart();
    }
	
}
