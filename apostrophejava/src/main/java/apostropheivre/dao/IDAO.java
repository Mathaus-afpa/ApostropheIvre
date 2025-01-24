package apostropheivre.dao;
import apostropheivre.exception.DaoException;
import apostropheivre.exception.ModelException;
import java.util.List;
public interface IDAO<T> {
    T create(String fromJson) throws DaoException;
    T find(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(String fromJson) throws DaoException;
    boolean delete(int id) throws DaoException;
}