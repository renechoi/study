package org.example.javaconcurrencyproblem.testhelpers.parser;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.javaconcurrencyproblem.api.domain.AtomicStockPortfolio;
import org.example.javaconcurrencyproblem.api.domain.StockPortfolio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : Rene Choi
 * @since : 2024/02/23
 */
public class ExtractableResponseParser {

  private static final ObjectMapper objectMapper = new ObjectMapper();


  public static String  parseSimpleId(ExtractableResponse<Response> response) {
    return response.body().jsonPath().getString("id");
  }


  public static StockPortfolio parsePortfolio(ExtractableResponse<Response> response) {
    return response.as(StockPortfolio.class);
  }


  public static AtomicStockPortfolio parseAtomicPortfolio(ExtractableResponse<Response> response) {
    return response.as(AtomicStockPortfolio.class);
  }



  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TestPageableResponse<T> {
    private int total;
    private List<T> content;
    private TestPageable pageable;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestPageable {
      private TestPageableSort sort;
      private int page;
      private int size;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestPageableSort {
      private List<TestPageableOrder> orders;

      @Data
      @NoArgsConstructor
      @AllArgsConstructor
      public static class TestPageableOrder {
        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
      }
    }
  }

  public static TestPageableResponse<Object> parseAsPageableObject(ExtractableResponse<Response> response) {
    try {
      return objectMapper.readValue(response.asString(), new TypeReference<TestPageableResponse<Object>>() {});
    } catch (IOException e) {
      throw new RuntimeException("Error parsing response as pageable object", e);
    }
  }

  public static <T> List<T> parseListFromResponse(ExtractableResponse<Response> response, Class<T> contentClass) {
    try {
      return objectMapper.readValue(response.asString(), objectMapper.getTypeFactory().constructCollectionType(List.class, contentClass));
    } catch (IOException e) {
      throw new RuntimeException("Error parsing list from response", e);
    }
  }



}
