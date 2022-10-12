package dao;

import java.sql.SQLException;

// CRUD operations
public interface GenericDAO<T> {
	int add(T t) throws SQLException;

	T get(int id) throws SQLException;

	void update(T t) throws SQLException;

	void delete(int id) throws SQLException;
}
