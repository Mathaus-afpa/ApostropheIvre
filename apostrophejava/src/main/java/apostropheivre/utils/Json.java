package apostropheivre.utils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
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
}