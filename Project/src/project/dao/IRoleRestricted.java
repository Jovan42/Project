package project.dao;

import java.util.ArrayList;

import project.enums.UserRole;

public interface IRoleRestricted {
	public  <T> ArrayList<T> get(UserRole role, Object o, Class<T> c );
}
