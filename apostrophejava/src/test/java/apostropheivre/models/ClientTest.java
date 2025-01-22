package apostropheivre.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client cliTest = new Client("Ndf", "Prenom", "1 Adresse", "54000", "Ville", "email@adresse.com");

    @BeforeAll
    static void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // ----------- TESTS NOM DE FAMILLE -------------------------
    @Test
    void setNdfNullAssertFalse() {

        try {
            cliTest.setNom(null);
        } catch (Exception e) {}
        assertFalse((cliTest.getNom() == null));
    }

    @Test
    void setNdfLettresAssertTrue() {

        try {
            cliTest.setNom("NdfTest");
        } catch (Exception e) {}
        assertEquals("NdfTest", cliTest.getNom());
    }

    @Test
    void setNdfNdfCharabiaAssertTrue() {
        try {
            cliTest.setNom("NdféûÄñ");
        } catch (Exception e) {}
        assertEquals("NdféûÄñ", cliTest.getNom());
    }

    @Test
    void setNdfChiffreAssertFalse() {
        try {
            cliTest.setNom("Ndf4");
        } catch (Exception e) {}
        assertNotEquals("Ndf4", cliTest.getNom());
    }

    @Test
    void setNdfVideAssertFalse() {

        try {
            cliTest.setNom("");
        } catch (Exception e) {}
        assertNotEquals("", cliTest.getNom());
    }

    @Test
    void setNdfEspaceAssertFalse() {
        try {
            cliTest.setNom(" ");
        } catch (Exception e) {}
        assertNotEquals(" ", cliTest.getNom());
    }

    @Test
    void setNdfTiretAssertFalse() {
        try {
            cliTest.setNom("-");
        } catch (Exception e) {}
        assertNotEquals("-", cliTest.getNom());
    }

    @Test
    void setNdfLettresPuisEspaceAssertFalse() {
        try {
            cliTest.setNom(("Test "));
        } catch (Exception e) {}
        assertNotEquals("Test ", cliTest.getNom());
    }

    @Test
    void setNdfLettresPuisTiretAssertFalse() {
        try {
            cliTest.setNom(("Test-"));
        } catch (Exception e) {}
        assertNotEquals("Test-", cliTest.getNom());
    }

    @Test
    void setNdfNdfComposeAssertTrue() {
        try {
            cliTest.setNom(("Ndf-Composé du Test"));
        } catch (Exception e) {}
        assertEquals("Ndf-Composé du Test", cliTest.getNom());
    }

    // ----------- TESTS PRENOM ---------------------------------
    @Test
    void setPrenomNullAssertFalse() {

        try {
            cliTest.setPrenom(null);
        } catch (Exception e) {}
        assertFalse((cliTest.getPrenom() == null));
    }

    @Test
    void setPrenomLettresAssertTrue() {
        try {
            cliTest.setPrenom("PrenomTest");
        } catch (Exception e) {}
        assertEquals("PrenomTest", cliTest.getPrenom());
    }

    @Test
    void setPrenomCharabiaAssertTrue() {
        try {
            cliTest.setPrenom("PrenoméûÄñ");
        } catch (Exception e) {}
        assertEquals("PrenoméûÄñ", cliTest.getPrenom());
    }

    @Test
    void setPrenomChiffreAssertFalse() {
        try {
            cliTest.setPrenom("Prenom4");
        } catch (Exception e) {
        }
        assertNotEquals("Prenom4", cliTest.getPrenom());
    }

    @Test
    void setPrenomVideAssertFalse() {
        try {
            cliTest.setPrenom("");
        } catch (Exception e) {}
        assertNotEquals("", cliTest.getPrenom());
    }

    @Test
    void setPrenomEspaceAssertFalse() {
        try {
            cliTest.setPrenom(" ");
        } catch (Exception e) {}
        assertNotEquals(" ", cliTest.getPrenom());
    }

    @Test
    void setPrenomTiretAssertFalse() {
        try {
            cliTest.setPrenom("-");
        } catch (Exception e) {}
        assertNotEquals("-", cliTest.getPrenom());
    }

    @Test
    void setPrenomLettresPuisEspaceAssertFalse() {
        try {
            cliTest.setPrenom("Prénom ");
        } catch (Exception e) {}
        assertNotEquals("Prénom ", cliTest.getPrenom());
    }

    @Test
    void setPrenomLettresPuisTiretAssertFalse() {
        try {
            cliTest.setPrenom("Prénom-");
        } catch (Exception e) {}
        assertNotEquals("Prénom-", cliTest.getPrenom());
    }

    @Test
    void setPrenomPrenomComposeAssertTrue() {
        try {
            cliTest.setPrenom("Prénom Composé-duTest");
        } catch (Exception e) {}
        assertEquals("Prénom Composé-duTest", cliTest.getPrenom());
    }

    // ----------- TESTS ADRESSE --------------------------------

    @Test
    void setAdresseNullAssertFalse() {

        try {
            cliTest.setAdresse(null);
        } catch (Exception e) {}
        assertFalse((cliTest.getAdresse() == null));
    }

    @Test
    void setAdresseLettresAssertTrue() {

        try {
            cliTest.setAdresse("AdresseTest");
        } catch (Exception e) {}
        assertEquals("AdresseTest", cliTest.getAdresse());
    }

    @Test
    void setAdresseCharabiaAssertTrue() {
        try {
            cliTest.setAdresse("AdresseéûÄñ");
        } catch (Exception e) {}
        assertEquals("AdresseéûÄñ", cliTest.getAdresse());
    }

    @Test
    void setAdresseChiffreAssertFalse() {
        try {
            cliTest.setAdresse("1");
        } catch (Exception e) {}
        assertNotEquals("1", cliTest.getAdresse());
    }

    @Test
    void setAdresseVideAssertFalse() {

        try {
            cliTest.setAdresse("");
        } catch (Exception e) {}
        assertNotEquals("", cliTest.getAdresse());
    }

    @Test
    void setAdresseEspaceAssertFalse() {
        try {
            cliTest.setAdresse(" ");
        } catch (Exception e) {}
        assertNotEquals(" ", cliTest.getAdresse());
    }

    @Test
    void setAdresseTiretAssertFalse() {
        try {
            cliTest.setAdresse("-");
        } catch (Exception e) {}
        assertNotEquals("-", cliTest.getAdresse());
    }

    @Test
    void setAdresseLettresPuisEspaceAssertFalse() {
        try {
            cliTest.setAdresse(("Test "));
        } catch (Exception e) {}
        assertNotEquals("Test ", cliTest.getAdresse());
    }

    @Test
    void setAdresseLettresPuisTiretAssertFalse() {
        try {
            cliTest.setAdresse(("Test-"));
        } catch (Exception e) {}
        assertNotEquals("Test-", cliTest.getAdresse());
    }

    @Test
    void setAdresseAdresseComposeAssertTrue() {
        try {
            cliTest.setAdresse(("221B, Baker Street du-14'juillet"));
        } catch (Exception e) {}
        assertEquals("221B, Baker Street du-14'juillet", cliTest.getAdresse());
    }

    // ----------- TESTS CODE POSTAL ----------------------------

    @Test
    void setCodePostalNullAssertFalse() {
        try {
            cliTest.setCodePostal(null);
        } catch (Exception e) {}
        assertNotNull(cliTest.getCodePostal());
    }

    @Test
    void setCodePostalALaSuiteAssertTrue() {
        try {
            cliTest.setCodePostal("12345");
        } catch (Exception e) {}
        assertEquals("12345", cliTest.getCodePostal());
    }

    @Test
    void setCodePostalEspace23AssertTrue() {
        try {
            cliTest.setCodePostal("12 345");
        } catch (Exception e) {}
        assertEquals("12345", cliTest.getCodePostal());
    }

    @Test
    void setCodePostalEspace34AssertTrue() {
        try {
            cliTest.setCodePostal("123 45");
        } catch (Exception e) {}
        assertNotEquals("123 45", cliTest.getCodePostal());
    }

    @Test
    void setCodePostalMoinsDe5AssertFalse() {
        try {
            cliTest.setCodePostal("1234");
        } catch (Exception e) {}
        assertNotEquals("1234", cliTest.getCodePostal());
    }

    @Test
    void setCodePostalPlusDe5AssertFalse() {
        try {
            cliTest.setCodePostal("123456");
        } catch (Exception e) {}
        assertNotEquals("123456", cliTest.getCodePostal());
    }

    // ----------- TESTS VILLE ----------------------------------

    @Test
    void setVilleNullAssertFalse() {
        try{
            cliTest.setVille(null);
        } catch (Exception e) {}
        assertNotNull((cliTest.getVille()));
    }

    @Test
    void setVilleLettresAssertTrue() {
        try {
            cliTest.setVille("VilleTest");
        } catch (Exception e) {}
        assertEquals("VilleTest", cliTest.getVille());
    }

    @Test
    void setVilleCharabiaAssertTrue() {
        try {
            cliTest.setVille("VilleéûÄñ");
        } catch (Exception e) {}
        assertEquals("VilleéûÄñ", cliTest.getVille());
    }

    @Test
    void setVilleChiffreAssertFalse() {
        try {
            cliTest.setVille("Ville4");
        } catch (Exception e) {
        }
        assertNotEquals("Ville4", cliTest.getVille());
    }

    @Test
    void setVilleVideAssertFalse() {
        try {
            cliTest.setVille("");
        } catch (Exception e) {}
        assertNotEquals("", cliTest.getVille());
    }

    @Test
    void setVilleEspaceAssertFalse() {
        try {
            cliTest.setVille(" ");
        } catch (Exception e) {}
        assertNotEquals(" ", cliTest.getVille());
    }

    @Test
    void setVilleTiretAssertFalse() {
        try {
            cliTest.setVille("-");
        } catch (Exception e) {}
        assertNotEquals("-", cliTest.getVille());
    }

    @Test
    void setVilleLettresPuisEspaceAssertFalse() {
        try {
            cliTest.setVille("Ville ");
        } catch (Exception e) {}
        assertNotEquals("Ville ", cliTest.getVille());
    }

    @Test
    void setVilleLettresPuisTiretAssertFalse() {
        try {
            cliTest.setVille("Ville-");
        } catch (Exception e) {}
        assertNotEquals("Ville-", cliTest.getVille());
    }

    @Test
    void setVilleComposeAssertTrue() {
        try {
            cliTest.setVille("Ville Composée-duTest");
        } catch (Exception e) {}
        assertEquals("Ville Composée-duTest", cliTest.getVille());
    }

    // ----------- TESTS EMAIL ----------------------------------

    @Test
    void setEmailNullAssertFalse() {
        try{
            cliTest.setEmail(null);
        } catch (Exception e) {}
        assertNotNull((cliTest.getEmail()));
    }

    @Test
    void setEmailLettresAssertFalse() {
        try {
            cliTest.setEmail("EmailTest");
        } catch (Exception e) {}
        assertNotEquals("EmailTest", cliTest.getEmail());
    }

    @Test
    void setEmailChiffreAssertFalse() {
        try {
            cliTest.setEmail("123456");
        } catch (Exception e) {
        }
        assertNotEquals("123456", cliTest.getEmail());
    }

    @Test
    void setEmailVideAssertFalse() {
        try {
            cliTest.setEmail("");
        } catch (Exception e) {}
        assertNotEquals("", cliTest.getEmail());
    }

    @Test
    void setEmailEspaceAssertFalse() {
        try {
            cliTest.setEmail(" ");
        } catch (Exception e) {}
        assertNotEquals(" ", cliTest.getEmail());
    }

    @Test
    void setEmailArobaseTiretAssertFalse() {
        try {
            cliTest.setEmail("email@test");
        } catch (Exception e) {}
        assertNotEquals("-", cliTest.getEmail());
    }

    @Test
    void setEmailPointAssertFalse() {
        try {
            cliTest.setEmail("email.test");
        } catch (Exception e) {}
        assertNotEquals("email.test", cliTest.getEmail());
    }

    @Test
    void setEmailCourtAssertFalse() {
        try {
            cliTest.setEmail("email@test.c");
        } catch (Exception e) {}
        assertNotEquals("email@test.c", cliTest.getEmail());
    }

    @Test
    void setEmailEntierAssertTrue() {
        try {
            cliTest.setEmail("e-mai_l@test.com");
        } catch (Exception e) {}
        assertEquals("e-mai_l@test.com", cliTest.getEmail());
    }
}