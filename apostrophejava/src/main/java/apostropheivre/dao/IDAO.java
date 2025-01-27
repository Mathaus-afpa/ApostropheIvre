package apostropheivre.dao;

import java.util.List;
import apostropheivre.exception.DaoException;
import  apostropheivre.exception.ModelException;
public interface IDAO<T> {
    T create(String fromJson) throws DaoException, ModelException;
    T find(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(String fromJson) throws DaoException;
    boolean delete(int id) throws DaoException;
}