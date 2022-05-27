package nttdata.javat1.game;

import java.io.Serializable;

/**
 * Clase Player
 * 
 * Esta clase es dónde se guarda la puntuación del jugador y su nombre.
 * 
 * @author Carlos González Ruiz
 *
 */
public class Player implements Comparable<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Constantes */
	private final int ATTEMPTS = 3;
	
	/** Atributos */
	private String name;
	private int score = 0;
	private Ball balls[] = new Ball[ATTEMPTS];
	
	public Player(String name) {
		this.name = name;
		
		// Inicializar atributo 'balls'
		for (int i = 0; i < ATTEMPTS; ++i) {
			balls[i] = new Ball();
		}
	}
	
	/**
	 * Getter del atributo 'name'.
	 * 
	 * @return atributo 'name'
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter del atributo 'name'.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter del atributo 'score'.
	 * 
	 * @return atributo 'score'
	 */
	public int getScore() {
		// Reinicializar score.
		score = 0;
		
		// Sumar scores.
		for (int i = 0; i < ATTEMPTS; ++i) {
			score += balls[i].getScore();
		}
		
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
	 * Getter del atributo 'balls'.
	 * 
	 * @return atributo 'balls'
	 */
	public Ball[] getBalls() {
		return balls;
	}
	
	/**
	 * Setter del atributo 'balls'.
	 * 
	 * @param balls
	 */
	public void setBalls(Ball[] balls) {
		this.balls = balls;
	}
	
	@Override
	public int compareTo(Object o) {
		return ((Player)(o)).score - score;
	}
}
