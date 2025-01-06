package apostropheivre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ApostropheIvreTest {
    @Test
    public void testHelloWorld() {
        // Test que la méthode HelloWorld retourne la chaîne attendue
        String expected = "Apostrophe Ivre !";
        String actual = ApostropheIvre.HelloWorld();
        assertEquals(expected, actual, "La méthode HelloWorld ne retourne pas la valeur attendue.");
    }
}