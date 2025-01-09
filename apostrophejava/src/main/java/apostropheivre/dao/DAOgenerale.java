package apostropheivre.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class DAOgenerale<T> {

    public abstract int create(T obj);

    public abstract String update(T obj, Integer pId);

    public abstract String delete(Integer pId);

    public abstract T find(Integer pId);

    public abstract List<T> findAll();

}
