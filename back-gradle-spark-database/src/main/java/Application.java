import com.google.gson.Gson;
import spark.Spark;

public class Application {
    public static UserPostgresDao userDbService = new UserPostgresDao();

    public static void main(String[] args) {
        String url = "/api/v1/user";

        Spark.get(url, (request, response) -> {
            return new Gson().toJson(
                    new Gson().toJsonTree(userDbService.readAll()));
        });
    }
}
