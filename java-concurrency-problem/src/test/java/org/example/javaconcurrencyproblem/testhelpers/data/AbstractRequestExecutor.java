package org.example.javaconcurrencyproblem.testhelpers.data;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

/**
 * @author : Rene Choi
 * @since : 2024/01/27
 */
public abstract class AbstractRequestExecutor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected static ExtractableResponse<Response> doPost(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().post(urlPath)
                .then().log().all()
                .extract();
    }

    protected static ExtractableResponse<Response> doPostWithCreated(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
            .when().post(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value())
            .extract();
    }

    protected static <T> ExtractableResponse<Response> doPost(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
                .body(requestBody)
                .when().post(urlPath)
                .then().log().all()
                .extract();
    }

    protected static <T> ExtractableResponse<Response> doPostWithOk(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
                .body(requestBody)
                .when().post(urlPath)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    protected static <T> ExtractableResponse<Response> doPostAsJsonWithOk(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
            .body(parseJsonWithObjectMapper(requestBody))
            .when().post(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    protected static <T> ExtractableResponse<Response> doPostWithCreated(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
            .body(requestBody)
            .when().post(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value())
            .extract();
    }


    protected static ExtractableResponse<Response> doPostWithJson(RequestSpecification requestSpecification, String urlPath, String jsonRequestBody) {
        return requestSpecification
            .body(jsonRequestBody)
            .when().post(urlPath)
            .then().log().all()
            .extract();
    }

    protected static ExtractableResponse<Response> doPostWithJsonWithOk(RequestSpecification requestSpecification, String urlPath, String jsonRequestBody) {
        return requestSpecification
            .body(jsonRequestBody)
            .when().post(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    protected static ExtractableResponse<Response> doPostWithJsonWithCreated(RequestSpecification requestSpecification, String urlPath, String jsonRequestBody) {
        return requestSpecification
            .body(jsonRequestBody)
            .when().post(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value())
            .extract();
    }

    protected static ExtractableResponse<Response> doGet(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().get(urlPath)
                .then().log().all()
                .extract();
    }

    protected static ExtractableResponse<Response> doGetWithOk(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().get(urlPath)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }


    protected static ExtractableResponse<Response> doPut(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().put(urlPath)
                .then().log().all()
                .extract();
    }

    protected static <T> ExtractableResponse<Response> doPut(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
                .body(parseJsonWithObjectMapper(requestBody))
                .when().put(urlPath)
                .then().log().all()
                .extract();
    }



    protected static <T> ExtractableResponse<Response> doPatch(RequestSpecification requestSpecification, String urlPath, T requestBody) {
        return requestSpecification
                .body(parseJsonWithObjectMapper(requestBody))
                .when().patch(urlPath)
                .then().log().all()
                .extract();
    }

    protected static <T> ExtractableResponse<Response> doPatch(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().patch(urlPath)
                .then().log().all()
                .extract();
    }

    protected static <T> ExtractableResponse<Response> doPatchWithOk(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
            .when().patch(urlPath)
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    protected static ExtractableResponse<Response> doDelete(RequestSpecification requestSpecification, String urlPath) {
        return requestSpecification
                .when().delete(urlPath)
                .then().log().all()
                .extract();
    }


    @SneakyThrows
    private static <T> String parseJsonWithObjectMapper(T requestBody) {
        return objectMapper.writeValueAsString(requestBody);
    }

}
