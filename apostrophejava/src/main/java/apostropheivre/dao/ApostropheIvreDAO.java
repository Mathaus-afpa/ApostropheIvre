package apostropheivre.dao;
import apostropheivre.utils.Log;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static apostropheivre.utils.Log.ILLEGAL_SINGLETON;
/**
 * [ApostropheIvreDAO] - class
 * @author Mathaus
 */
public class ApostropheIvreDAO {
	public static final ApostropheIvreDAO getInstance() {
		return SingletonApostropheIvreDAO.INSTANCE;
	}
	private static final class SingletonApostropheIvreDAO {
		private static final ApostropheIvreDAO INSTANCE = new ApostropheIvreDAO();
	}
	private ApostropheIvreDAO() {
		if (SingletonApostropheIvreDAO.INSTANCE != null) {
			Log.error(ILLEGAL_SINGLETON, new IllegalStateException());
		}
		this.configureInstance();
	}
	//START_______________________________________________[instance]__________________________________________________//
	private Properties properties;
	private String dbSettings = "db.properties";
	private Connection connection = null;
	/**
	 * todo: doc
	 */
	private void configureInstance() {
		this.properties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(this.dbSettings)) {
			if (input == null) {
				throw new IllegalArgumentException("Fichier introuvable : " + this.dbSettings);
			}
			this.properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Erreur lors du chargement du fichier properties", e);
		}
	}
	/**
	 * todo: doc
	 */
	private void initConnection() {
		if (this.connection == null) {
			try {
				this.connection = DriverManager.getConnection(
						this.properties.getProperty("jdbc.url"),
						this.properties.getProperty("jdbc.login"),
						this.properties.getProperty("jdbc.password")
				);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * todo: doc
	 * @return
	 */
	public final Connection getConnection() {
		this.initConnection();
		return this.connection;
	}
	/**
	 * todo: doc
	 */
	public final void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}
}