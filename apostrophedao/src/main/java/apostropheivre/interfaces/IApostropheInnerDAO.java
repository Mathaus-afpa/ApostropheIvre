package apostropheivre.interfaces;
import apostropheivre.exceptions.DaoException;
import java.util.List;
public interface IApostropheInnerDAO<T> {
    T create(String fromJson) throws DaoException;
    T find(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(String fromJson) throws DaoException;
    boolean delete(int id) throws DaoException;
}