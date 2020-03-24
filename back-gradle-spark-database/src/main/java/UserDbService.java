import java.util.ArrayList;

public interface UserDbService<T> {
    Boolean create(T entity);
    T readOne(int id);
    ArrayList<T> readAll();
    Boolean update(int id, String username, String email, String scanUser, String scanDocument);
    Boolean delete(int id);
}
