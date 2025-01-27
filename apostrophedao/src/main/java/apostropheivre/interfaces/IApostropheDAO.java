package apostropheivre.interfaces;
import java.util.List;
public interface IApostropheDAO<T> {
    T creer(String fromJson);
    T rechercherParId(int id);
    List<T> rechercherTout();
    T modifier(String fromJson);
    boolean supprimer(int id);
}