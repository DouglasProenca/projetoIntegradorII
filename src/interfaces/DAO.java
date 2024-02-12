package interfaces;

import java.util.ArrayList;


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
     * @param object
     * @return true or false
     */
    public boolean save(Object object);

    /**
     *
     * @param object
     * @return true or false
     */
    public boolean alter(Object object);

    /**
     * @author Douglas Proença
     * @param key
     * @return arraylist of object
     */
    public ArrayList<?> getBy(String key);
}
