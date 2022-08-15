package br.senac.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public interface DAO {

    /**
     * @author Douglas Proença
     * @param id
     * @return true case sucess on delete, or false case insucess
     */
    public boolean delete(int id);

    /**
     * @author Douglas Proença
     * @return arraylist of object
     */
    public ArrayList<?> getAll();

    /**
     *
     * @param p
     * @return
     */
//    public boolean save (Object<> );
    //public boolean alter(Object Alter);
    /**
     * @author Douglas Proença
     * @param key
     * @return arraylist of object
     */
    public ArrayList<?> getBy(String key);
}
