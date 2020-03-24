import java.sql.*;
import java.util.ArrayList;

public class UserPostgresDao<T extends User> implements UserDbService<T> {

    private Connection conn;
    private Statement stmt;

    public UserPostgresDao() {
        String user = "postgres";
        String password = "postgres";
        String dbName = "test";
        String uri = "jdbc:postgresql://localhost/" + dbName;

        try {
            conn = DriverManager.getConnection(uri, user, password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public Boolean create(T entity) {
        return null;
    }

    @Override
    public T readOne(int id) {
        return null;
    }

    @Override
    public ArrayList<T> readAll() {
        ArrayList<User> results = new ArrayList<>();

        try {
            stmt.execute("select * from users");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                results.add(
                        new User(
                                rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("scanuser"),
                                rs.getString("scandocument")
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return (ArrayList<T>) results;
    }

    @Override
    public Boolean update(int id, String username, String email, String scanUser, String scanDocument) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }
}
