package apostropheivre.utils;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [Json] - class
 * @author Mathaus
 */
public class Json {
	// Méthode pour convertir un objet en JSON
	public static String objectToJson(Object obj) {
		JSONObject jsonObject = new JSONObject(obj);
		return jsonObject.toString();
	}

	public static String RequeteToJson(HttpServletRequest request) throws IOException {
		StringBuilder requestBody = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				requestBody.append(line);
			}
			return requestBody.toString();
		}
	}
}