package lv.ctco.automationschool2020;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import my.reqres.UserPage;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Test {
    private static RequestSpecification spec;
    private static RequestSpecification spec2;

    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()

                .setContentType(ContentType.JSON)
                .setProxy("proxy.ctco.lv", 8080)
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
        spec2 = new RequestSpecBuilder()

                .setContentType(ContentType.JSON)
                .setProxy("proxy.ctco.lv", 8080)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    //   @org.junit.Test

    @org.junit.jupiter.api.Test
    public void someTest() {
        User[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract().as(User[].class);
        assertEquals(users[0].getId(), 1);
        assertEquals(users[0].getUsername(), "Bret");
        assertEquals(2, users[1].getId());
    }

    @org.junit.jupiter.api.Test
    public void somePostTest() {
        Post[] posts = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(Post[].class);
        assertEquals(posts[0].getUserId(), 1);
        assertEquals(posts[0].getId(), 1);

    }

    @org.junit.jupiter.api.Test
    public void someToDosTest() {
        ToDos[] todos = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(ToDos[].class);
        assertFalse(todos[0].isCompleted());
        assertEquals(todos[0].isCompleted(), false);


    }
    @org.junit.jupiter.api.Test
    public void somePosts2Test() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users")
                .then().statusCode(201)
                .and()
                .extract()
                .response();

        String responseBodyString = response.getBody().asString();
        System.out.println(response.path("createdAt").toString());
    }

    @org.junit.jupiter.api.Test
    public void somePosts3Test() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        UserResponse useresponse = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users")
                .then().statusCode(201)
                .and()
                .extract().as(UserResponse.class);


 //       String responseBodyString = response.getBody().asString();
        System.out.println(useresponse.getCreatedAt().toString());
    }
    @org.junit.jupiter.api.Test
    public void someLoginTest() {
        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(200)
                .and()
                .extract()
                .response();;

        String responseBodyString = response.getBody().asString();

        System.out.println(response.path("token").toString());
    }


    @org.junit.jupiter.api.Test
    public void someGetSlashTest() {
        Post posts = given()
                .spec(spec)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200)
                .extract().as(Post.class);
             assertEquals(posts.getUserId(), 1);


    }

    @org.junit.jupiter.api.Test
    public void someToDos1Test() {
        ToDos todo1 = given()
                .spec(spec)
                .when()
                .get("todos/1")
                .then()
                .statusCode(200)
                .extract().as(ToDos.class);
        assertEquals(todo1.getUserId(), 1);
        assertEquals(false, todo1.completed, "expected false");


    }

    @org.junit.jupiter.api.Test
    public void someGetUser2Test() {
        UserPage userpage = given()
                .spec(spec2)
                .when()
                .get("\n" +
                        "/users?page=2")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        assertEquals(userpage.getData()[0].getId(), 7);
        assertEquals("Michael", userpage.getData()[0].getFirst_name());
  //      assertEquals(2, users[1].getId());
    }


 /**   @JsonIgnoreProperties(ignoreUnknown = true)
    static class User {
        Integer id;
        String username;
        String email;

//getters and setters


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


    }
**/


}
