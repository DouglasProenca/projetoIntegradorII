package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Douglas
 */
public abstract interface DAO {

    /**
     * @author Douglas Proença
     * @param id
     * @return true case sucess on delete, or false case insucess
     */
    public abstract boolean delete(int id);

    /**
     * @author Douglas Proença
     * @return arraylist of object
     */
    public abstract ArrayList<?> getAll();

    /**
     *
     * @param object
     * @return true or false
     */
    public abstract boolean save(Object object);

    /**
     *
     * @param object
     * @return true or false
     */
    public abstract boolean alter(Object object);

    /**
     * @author Douglas Proença
     * @param key
     * @return arraylist of object
     */
    public abstract ArrayList<?> getBy(String key);
}
