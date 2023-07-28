package com.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : Rene
 * @since : 2023/07/28
 */
public class StringParsing {

	@Test
	public static void test(){
		String message1= "[2,\"JC0010000058-23071014-HDOU-S\", ClearCache, {}]";
		String message2= "[3,\"JC0010000058-23071014-000024-C\",{\"status\":\"Accepted\",\"data\":\"{\\\"preFee\\\":30000.0,\\\"priceInfo\\\":[{\\\"month\\\":\\\"JULY\\\",\\\"oneDayTimeTable\\\":[{\\\"startTime\\\":{\\\"hour\\\":0,\\\"minute\\\":0,\\\"second\\\":0},\\\"endTime\\\":{\\\"hour\\\":23,\\\"minute\\\":59,\\\"second\\\":59},\\\"price\\\":220.0}]},{\\\"month\\\":\\\"AUGUST\\\",\\\"oneDayTimeTable\\\":[{\\\"startTime\\\":{\\\"hour\\\":0,\\\"minute\\\":0,\\\"second\\\":0},\\\"endTime\\\":{\\\"hour\\\":23,\\\"minute\\\":59,\\\"second\\\":59},\\\"price\\\":220.0}]}]}\"}]";
		String message3= "[[2,\"JC0010000058-23071014-000028-C\",\"MeterValues\",{\"connectorId\":1,\"meterValue\":[{\"sampledValue\":[{\"context\":\"Transaction.Begin\",\"measurand\":\"Energy.Active.Import.Register\",\"unit\":\"Wh\",\"value\":\"0\"},{\"context\":\"Transaction.Begin\",\"measurand\":\"SoC\",\"unit\":\"Percent\",\"value\":\"69\"},{\"context\":\"Transaction.Begin\",\"measurand\":\"Voltage\",\"unit\":\"V\",\"value\":\"0\"},{\"context\":\"Transaction.Begin\",\"measurand\":\"Power.Active.Import\",\"unit\":\"W\",\"value\":\"0\"},{\"context\":\"Transaction.Begin\",\"measurand\":\"Current.Offered\",\"unit\":\"A\",\"value\":\"0\"},{\"context\":\"Transaction.Begin\",\"measurand\":\"Power.Offered\",\"unit\":\"W\",\"value\":\"0\"}],\"timestamp\":\"2023-07-10T14:52:50.816+09:00\"}],\"transactionId\":20230710145142441}]";

		Assertions.assertEquals("JC0010000058-23071014-HDOU-S", convert(message1));
		Assertions.assertEquals("JC0010000058-23071014-000024-C", convert(message2));
		Assertions.assertEquals("JC0010000058-23071014-000028-C", convert(message3));

	}

	public static void main(String[] args) {
		test();
	}



	public static String convert(String message){
		int start = message.indexOf("\"") + 1;
		int end = message.indexOf("\"", start);

		return message.substring(start, end);
	}

}
