package dao;

import java.sql.SQLException;

// CRUD operations
public interface GenericDAO<T> {
	T save(T t) throws SQLException;

	T get(int id) throws SQLException;

	boolean update(int idToUpdate, T t) throws SQLException;

	void delete(int id) throws SQLException;
}
