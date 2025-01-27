package apostropheivre;

import apostropheivre.utils.Log;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

	// Paramètres de connexion à la base de données
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/apostropheivrelogin";
	private static final String JDBC_USER = "root"; // Utilisateur MySQL
	private static final String JDBC_PASSWORD = "groot"; // Remplacer par votre mot de passe MySQL

	// Méthode pour valider l'utilisateur en vérifiant le mot de passe
	public static boolean validateUser(String login, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		boolean isValid = false;
		String query = "SELECT password FROM users WHERE login = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement stmt = connection.prepareStatement(query)) {

			// Ajouter le paramètre de la requête
			stmt.setString(1, login);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Récupérer le hash stocké en base
					String storedPassword = rs.getString("password");
					isValid = passwordEncoder.matches(password, storedPassword);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	// Méthode pour obtenir le rôle d'un utilisateur
	public static String getRole(String login) {
		String role = null;
		String query = "SELECT roles.role FROM users "
				+ "JOIN roles ON users.role = roles.id "
				+ "WHERE users.login = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, login);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					role = rs.getString("role");
				}
			}
		} catch (SQLException e) {
			Log.error(e.getMessage(), e.getCause());
		}
		return role;
	}

	// Ajout d'un nouvel utilisateur
	public static boolean addUser(String mail, String login, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		String query = "INSERT INTO users (mail, login, password, role) VALUES (?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement stmt = connection.prepareStatement(query)) {

			// Remplissage des paramètres
			stmt.setString(1, mail);
			stmt.setString(2, login);
			stmt.setString(3, hashedPassword);
			stmt.setInt(4, 3);

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			Log.error(e.getMessage(), e.getCause());
		}
		return false;
	}

	// Méthode qui permet de vérifier la disponibilité d'un email et d'un identifiant
	public static boolean isEmailOrUsernameTaken(String mail, String login) {
		String query = "SELECT COUNT(*) FROM users WHERE mail = ? OR login = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement stmt = connection.prepareStatement(query)) {

			stmt.setString(1, mail);
			stmt.setString(2, login);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // Retourne true si un utilisateur existe déjà
				}
			}
		} catch (SQLException e) {
			Log.error(e.getMessage(), e.getCause());
		}
		return false;
	}

}