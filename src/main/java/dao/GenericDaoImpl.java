package dao;

public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

	private final String tableName;

	protected GenericDaoImpl() {
		tableName = setTableName();
	}

	protected abstract String setTableName();

	public String getTableName() {
		return tableName;
	}
}