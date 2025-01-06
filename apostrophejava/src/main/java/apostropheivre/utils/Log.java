package apostropheivre.utils;
/**
 * [Log] - class
 * @author Mathaus
 */
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public final class Log {
	// Nom de la classe statique pour centraliser les logs
	private static final Logger LOGGER = Logger.getLogger(Log.class.getName());
	public static final String ILLEGAL_SINGLETON = "Etat interdit : Singleton duppliqué.";
	//<editor-fold defaultstate="collapsed" desc="Initialiation">
	static {
		try {
			// Configuration initiale
			LOGGER.setUseParentHandlers(false); // Évite la duplication de logs
			// Console Handler
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.INFO); // Niveau minimum des logs pour la console
			consoleHandler.setFormatter(new SimpleFormatter()); // Formatteur simple
			LOGGER.addHandler(consoleHandler);
			// File Handler (écriture dans un fichier)
			FileHandler fileHandler = new FileHandler("app.log", true); // true pour append
			fileHandler.setLevel(Level.WARNING); // Tous les niveaux enregistrés dans le fichier
			fileHandler.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fileHandler);
			// Niveau global des logs
			LOGGER.setLevel(Level.ALL);
		} catch (Exception e) {
			// En cas d'échec de configuration
			LOGGER.log(Level.SEVERE, "Failed to initialize logger handlers", e);
		}
	}
	//</editor-fold>
	// Constructeur privé pour empêcher l'instanciation
	private Log() {
		throw new UnsupportedOperationException("Classe statique.");
	}
	public static Logger getLogger() {
		return LOGGER;
	}
	//<editor-fold defaultstate="collapsed" desc="Méthodes utilitaires">
	public static void info(String message) {
		LOGGER.info(message);
	}
	public static void warning(String message) {
		LOGGER.warning(message);
	}
	public static void error(String message, Throwable throwable) {
		LOGGER.log(Level.SEVERE, message, throwable);
	}
	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="Méthodes utilitaires">
	//</editor-fold>
}