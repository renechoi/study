package org.example.javaconcurrencyproblem.testhelpers.executor;

import static io.restassured.RestAssured.*;
import static org.springframework.http.MediaType.*;

import org.example.javaconcurrencyproblem.testhelpers.data.AbstractRequestExecutor;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author : Rene Choi
 * @since : 2024/02/24
 */
public class DefaultStockPortfolioApiExecutor extends AbstractRequestExecutor {

  private static final String URL_PATH = "/stock-portfolio/default";

  private static RequestSpecification getRequestSpecification(int port) {
    return given().log().all().port(port).contentType(APPLICATION_JSON_VALUE);
  }


  public static ExtractableResponse<Response> createPortfolio() {
    return doPostWithCreated(getRequestSpecification(DynamicPortHolder.getPort()), URL_PATH );
  }


  public static ExtractableResponse<Response> createAtomicPortfolio() {
    return doPostWithCreated(getRequestSpecification(DynamicPortHolder.getPort()), URL_PATH + "/atomic" );
  }





}
