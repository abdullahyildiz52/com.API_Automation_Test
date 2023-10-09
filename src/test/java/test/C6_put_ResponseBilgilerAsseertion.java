package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C6_put_ResponseBilgilerAsseertion {
    /*
     https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki Json formatindaki body ile
      bir PUT request gonderdigimizde
     {
     “title”: “Ahmet”,
     “body”: “Merhaba”,
     “userId”: 10,
     “id”: 70
      }
      donen Response’un,
     status code’unun 200,
     ve content type’inin application/json; charset=utf-8, ve Server isimli Header’in degerinin cloudflare,
     ve status Line’in HTTP/1.1 200 OK

     */
    @Test
    public void put01() {
        // 1 EndPoint ve ResquestBody hazırla
        String url = "https://jsonplaceholder.typicode.com/posts/70";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Abdullah");
        requestBody.put("body", "Yıldız");
        requestBody.put("userId", "10");
        requestBody.put("id", "52");

        // 2 expected data hazırlama

        //3 Response kaydetme
        // sorgumuzda bır requwest body gönderıyorsak  datanın formatını belırtmemeız gerekır
        // bunu response objesındekı given() methodundan hemen sonra pre-condition olarak girmeliyiz
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody.toString()).put(url);

        response.prettyPrint();

        // 4 test
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");

    }
}
