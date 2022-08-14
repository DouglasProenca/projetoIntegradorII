package br.senac.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public interface DAO {

    public boolean delete(int id);

    public ArrayList<?> getAll();

    //public boolean save(Object save);

    //public boolean alter(Object Alter);

    public ArrayList<?> getBy(String key);
}
