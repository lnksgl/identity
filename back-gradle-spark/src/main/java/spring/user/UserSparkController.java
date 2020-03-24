package spring.user;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import static spark.Spark.delete;
import static spark.Spark.put;
import static spark.Spark.get;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSparkController {

    static UserService userService;

    public static void main(String[] args) {
        String url = "/api/v1/user";

        get(url, (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.showAllUsers())));
        });

        get(url + "/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.readSingleUser(Long.parseLong(request.params(":id"))))));
        });

        get(url + "/username/:username", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.showUsername(request.params(":username")))));
        });

        get(url + "/current-username/:username", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.readCurrentUser(request.params(":username")))));
        });

        put(url + "/:id", (request, response) -> {
            response.type("application/json");
            UserAverageDto user = new Gson().fromJson(request.body(), UserAverageDto.class);

            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS));

        });

        delete(url + "/:id", (request, response) -> {
            response.type("application/json");
            userService.deleteUser(Long.parseLong(request.params(":id")));
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "user deleted"));
        });
    }
}
