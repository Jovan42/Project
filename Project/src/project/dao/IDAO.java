package project.dao;

import java.util.ArrayList;

public interface IDAO {
	public Object get(Object o);
	public <T> ArrayList<T> getAll(Class<T> c);
	public boolean add(Object o);
	public boolean update(Object o, Object identificator);
	public boolean delete(Object o);
}
