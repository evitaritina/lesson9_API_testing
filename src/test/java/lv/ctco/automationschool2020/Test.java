package lv.ctco.automationschool2020;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    private static RequestSpecification spec;

    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()

                .setContentType(ContentType.JSON)
                .setProxy("proxy.ctco.lv", 8080)
                .setBaseUri("https://jsonplaceholder.typicode.com")
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
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
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







}
