package nttdata.javat1.game;

import java.io.Serializable;
import java.util.Random;

import nttdata.javat1.game.UI.Alignment;

/**
 * Clase Ball
 * 
 * Esta clase es dónde se lanzará una bola dado un nivel.
 * 
 * @author Carlos González Ruiz
 *
 */
public class Ball implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Constantes */
	public final float DISTANCE_MAX = 25 + (float)Math.random() * 50;
	public final float DISTANCE_INCREMENT_MIN = 0.01f + (float)Math.random() * 1.11f;
	public final float DISTANCE_INCREMENT_MAX = 0.11f + (float)Math.random() * 5.55f;
	public final float POINTS_BOUNCE = 7;
	public final float POINTS_BONUS = 17;
	public final float BONUS_DISTANCE = -5;
	
	/** Atributos */
	private float distance = 0;
	private int score = 0;
	private int bounces = 0;
	private int bonuses = 0;
	
	/**
	 * Función para lanzar la bola.
	 * 
	 * Sistema de puntuación:
	 * 	- Rebote cuando el valor de los decimales es menor a 0.25 -> + 7 puntos
	 * 	- Bonus cuando el valor de los decimales es mayor a 0.85 -> + 17 puntos
	 * 	- Agujero cuando se llega a mas de DISTANCE_MAX -> fin del juego
	 * 
	 * Los números aleatorios viene dados por la semilla pasada cómo parámetro.
	 * Los limites, sin embargo, vienen dados por Math.random, cuya semilla no puede ser
	 * especificada.
	 * 
	 */
	public void launch(int seed, UI ui) {
		// Establecer semilla
		Random random = new Random();
		random.setSeed(seed);
		
		// Bucle hasta que la distancia sea superior a DISTANCE_MAX
		while (distance < DISTANCE_MAX) {
			float decimals = distance - (float)Math.floor(distance);
			
			// Comprobar si es un bonus 
			if (decimals >= 0.85f) {
				++bonuses;
				score += POINTS_BONUS;
				
				distance -= BONUS_DISTANCE;
				
				ui.showText(Alignment.CENTER, "¡Bonus! +" + POINTS_BONUS + " puntos");
			// Comprobar si es un rebote
			} else if (decimals <= 0.25f) {
				++bounces;
				score += POINTS_BOUNCE;
				
				ui.showText(Alignment.CENTER, "¡Rebote! +" + POINTS_BOUNCE + " puntos");
			}
			
			distance += DISTANCE_INCREMENT_MIN + Math.random() * (DISTANCE_INCREMENT_MAX - DISTANCE_INCREMENT_MIN);
		}
	}
	
	/**
	 * Getter del atributo 'distance'.
	 * 
	 * @return atributo 'distance'
	 */
	public float getDistance() {
		return distance;
	}
	
	/**
	 * Setter del atributo 'distance'.
	 * 
	 * @param distance
	 */
	public void setScore(float distance) {
		this.distance = distance;
	}
	
	/**
	 * Getter del atributo 'score'.
	 * 
	 * @return atributo 'score'
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Setter del atributo 'score'.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Getter del atributo 'bounces'.
	 * 
	 * @return atributo 'bounces'
	 */
	public int getBounces() {
		return bounces;
	}
	
	/**
	 * Setter del atributo 'bounces'.
	 * 
	 * @param bounces
	 */
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	
	/**
	 * Getter del atributo 'bonuses'.
	 * 
	 * @return atributo 'bonuses'
	 */
	public int getBonuses() {
		return bonuses;
	}
	
	/**
	 * Setter del atributo 'bonuses'.
	 * 
	 * @param bounces
	 */
	public void setBonuses(int bonuses) {
		this.bonuses = bonuses;
	}
}
