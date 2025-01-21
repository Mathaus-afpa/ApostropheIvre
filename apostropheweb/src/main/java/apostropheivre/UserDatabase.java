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
	public static boolean validateUser(String username, String password) {
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
			stmt.setString(1, username);

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
	public static String getRole(String username) {
		String role = null;
		String query = "SELECT roles.role FROM users "
				+ "JOIN roles ON users.role = roles.id "
				+ "WHERE users.login = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);

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
}