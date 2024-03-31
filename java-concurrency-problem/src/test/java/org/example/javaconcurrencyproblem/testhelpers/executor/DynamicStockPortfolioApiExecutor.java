package org.example.javaconcurrencyproblem.testhelpers.executor;

import static io.restassured.RestAssured.*;
import static org.springframework.http.MediaType.*;

import org.example.javaconcurrencyproblem.api.domain.BuyOrderRequest;
import org.example.javaconcurrencyproblem.testhelpers.context.UrlDynamicContext;
import org.example.javaconcurrencyproblem.testhelpers.data.AbstractRequestExecutor;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author : Rene Choi
 * @since : 2024/02/24
 */
public class DynamicStockPortfolioApiExecutor extends AbstractRequestExecutor {

  private static final String URL_PATH = "/stock-portfolio" + "/" + UrlDynamicContext.apiType;

  private static RequestSpecification getRequestSpecification(int port) {
    return given().log().all().port(port).contentType(APPLICATION_JSON_VALUE);
  }



  public static ExtractableResponse<Response> fetchAmount(Long id) {
    return doGet(getRequestSpecification(DynamicPortHolder.getPort()), URL_PATH + "/" + id);
  }

  public static ExtractableResponse<Response> fetchAmountWithOk(Long id) {
    return doGetWithOk(getRequestSpecification(DynamicPortHolder.getPort()), URL_PATH + "/" + id);
  }


  public static ExtractableResponse<Response> buyStock(Long portfolioId, BuyOrderRequest buyOrderRequest) {
    return doPost(getRequestSpecification(DynamicPortHolder.getPort()), URL_PATH + "/" + portfolioId + "/buy", buyOrderRequest);
  }

}
