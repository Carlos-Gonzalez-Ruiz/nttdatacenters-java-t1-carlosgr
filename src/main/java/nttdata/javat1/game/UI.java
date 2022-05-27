package nttdata.javat1.game;

import java.util.Scanner;

/**
 * Clase UI (User Interface)
 * 
 * Esta clase es la que se encarga de mostrar la información por consola, de manera que
 * siempre esté "enmarcada" bajo una serie de carácteres, por motivos de estética.
 * 
 * @author Carlos González Ruiz
 *
 */
public class UI {
	
	/** Constantes */
	private final int FRAME_WIDTH = 100;
	private final char FRAME_HOR = '-';
	private final char FRAME_VER = '¦';
	private final char FRAME_CORNER = '+';
	
	/** Atributos */
	private Scanner scanner;
	
	/** Enums */
	enum Alignment {
		LEFT,
		CENTER
	}
	
	public UI() {
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Función para mostrar el marco superior.
	 * 
	 */
	public void showTop() {
		String out = "";
		
		out += FRAME_CORNER;
		for (int i = 0; i < FRAME_WIDTH; ++i) {
			out += FRAME_HOR;
		}
		out += FRAME_CORNER + "\n";
		
		System.out.print(out);
	}
	
	/**
	 * Función para mostrar texto por consola según la alineación del texto.
	 * En caso de que el texto pasado como parámetro contenga los caracteres
	 * '\r' o '\n', no se muestra nada por consola y se devuelve un Integer
	 * mayor que 0. Si todo es correcto, se devuelve 0.
	 * 
	 * Esta función llama a 'formatText(alignment, text)' para formatear el texto.
	 * 
	 * @param alignment, text
	 * @return código de error
	 */
	public int showText(Alignment alignment, String text) {
		int errorCode = 0;
		
		if (text.contains("\r") || text.contains("\n")) {
			errorCode = 1;
		} else {
			System.out.print(formatText(alignment, text)); 
		}
		
		return errorCode;
	}
	
	/**
	 * Función para mostrar texto por consola alineado a la izquierda dado un
	 * desplazamiento. En caso de que el texto pasado como parámetro contenga los
	 * caracteres '\r' o '\n', no se muestra nada por consola y se devuelve un
	 * Integer mayor que 0. Si todo es correcto, se devuelve 0.
	 * 
	 * Esta función llama a 'formatText(offset, text)' para formatear el texto.
	 * 
	 * @param alignment, text
	 * @return código de error
	 */
	public int showText(int offset, String text) {
		int errorCode = 0;
		
		if (text.contains("\r") || text.contains("\n")) {
			errorCode = 1;
		} else {
			System.out.print(formatText(offset, text)); 
		}
		
		return errorCode;
	}
	
	/**
	 * Función para formatear el texto pasado como parámetro en función
	 * de la alineación dada.
	 * 
	 * @param alignment, text
	 * @return texto formateado
	 */
	public String formatText(Alignment alignment, String text) {
		String out = "";
		
		out += FRAME_VER;
		
		switch (alignment) {
			case LEFT:
				// Añadir el texto.
				out += ' ' + text;
				
				// Añadir el resto de carácteres vacíos. 
				for (int i = out.length() - 1; i < FRAME_WIDTH; ++i) {
					out += ' ';
				}
				break;
				
			case CENTER:
				// Primera mitad con caracteres vacíos (espacios).
				for (int i = 0; i < (FRAME_WIDTH - text.length()) / 2; ++i) {
					out += ' ';
				}
				
				// Añadir texto.
				out += text;
				
				// Segunda mitad con caracteres vacíos (espacios).
				for (int i = (FRAME_WIDTH + text.length()) / 2; i < FRAME_WIDTH; ++i) {
					out += ' ';
				}
				break;
		}
		
		out += FRAME_VER + "\n";
		
		return out;
	}
	
	/**
	 * Función para formatear el texto pasado como parámetro aplicándole
	 * una alineación a la izquierda además de un desplazamiento al texto.
	 * 
	 * @param offset, text
	 * @return texto formateado
	 */
	public String formatText(int offset, String text) {
		String out = "";
		
		out += FRAME_VER;
		
		// Añadir n carácteres vacíos según el offset (desplazamiento). 
		for (int i = 0; i < offset; ++i) {
			out += ' ';
		}
		
		// Añadir el texto.
		out += text;
		
		// Añadir el resto de carácteres vacíos. 
		for (int i = out.length() - 1; i < FRAME_WIDTH; ++i) {
			out += ' ';
		}
		
		out += FRAME_VER + "\n";
		
		return out;
	}
	
	/**
	 * Función para pedir datos al usuario, de forma que respete el formato
	 * de los marcos. El programador habrá de encargarse de que transformar
	 * los datos obtenidos al tipo de dato que desee.
	 * 
	 * @param text
	 * @return datos
	 */
	public String promptData(String text) {
		String data = null;
		String out = "";
		
		out += FRAME_VER;
		
		// Añadir n carácteres vacíos según el offset (desplazamiento). 
		for (int i = 0; i < 10; ++i) {
			out += ' ';
		}
		
		// Añadir el texto.
		out += text;
		
		// Mostrar qué datos que se van a pedir. 
		System.out.print(out);
		
		// Pedir datos.
		
		data = scanner.nextLine();
		
		return data;
	}
	
	/**
	 * Getter del atributo 'input'.
	 * 
	 * @return atributo 'input'
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
	/**
	 * Setter del atributo 'scanner'.
	 * 
	 * @param scanner
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
