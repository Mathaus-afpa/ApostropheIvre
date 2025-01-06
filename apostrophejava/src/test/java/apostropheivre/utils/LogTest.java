package apostropheivre.utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.StreamHandler;
import static org.junit.jupiter.api.Assertions.*;
class LogTest {
    private ByteArrayOutputStream logCapture;
    private StreamHandler handler;
    @BeforeEach
    public void setUp() {
        logCapture = new ByteArrayOutputStream();
        handler = new StreamHandler(new PrintStream(logCapture), new java.util.logging.SimpleFormatter());
        Log.getLogger().addHandler(handler); // Ajouter le handler au logger
        Log.getLogger().setUseParentHandlers(false); // Désactive les handlers par défaut pour éviter les redondances
    }
    @Test
    void info() {
        // Act
        Log.getLogger().info("Test info message");
        handler.flush(); // S'assurer que tout est écrit dans le buffer
        // Assert
        String logged = logCapture.toString();
        assertTrue(logged.contains("Test info message"), "Le message INFO n'a pas été logué correctement.");
    }
    @Test
    public void testWarning() {
        // Act
        Log.getLogger().warning("Test warning message");
        handler.flush();
        // Assert
        String logged = logCapture.toString();
        assertTrue(logged.contains("Test warning message"), "Le message WARNING n'a pas été logué correctement.");
    }
    @Test
    public void testError() {
        // Act
        Throwable throwable = new RuntimeException("Test exception");
        Log.getLogger().log(Level.SEVERE, "Test error message", throwable);
        handler.flush();
        // Assert
        String logged = logCapture.toString();
        assertTrue(logged.contains("Test error message"), "Le message ERROR n'a pas été logué correctement.");
        assertTrue(logged.contains("Test exception"), "L'exception associée n'a pas été loguée correctement.");
    }
}