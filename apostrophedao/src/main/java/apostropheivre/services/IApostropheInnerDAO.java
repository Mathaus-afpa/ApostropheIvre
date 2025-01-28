package apostropheivre.services;
import apostropheivre.exceptions.DaoException;
import apostropheivre.exceptions.ModelException;
import apostropheivre.exceptions.NullDataException;

import java.util.List;
public interface IApostropheInnerDAO<T> {
    T create(String fromJson) throws DaoException;
    T find(int id) throws DaoException, NullDataException;
    List<T> findAll() throws DaoException;
    T update(String fromJson) throws DaoException;
    boolean delete(int id) throws DaoException, NullDataException, ModelException;
}