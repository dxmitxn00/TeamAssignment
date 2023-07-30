package model;

public class Util {
	/*
	 * public static void Pay() { String orderId = request.getParameter("orderId");
	 * String paymentKey = request.getParameter("paymentKey"); String amount =
	 * request.getParameter("amount"); String secretKey =
	 * "test_sk_ZORzdMaqN3w6xajbz0N85AkYXQGw:";
	 * 
	 * Encoder encoder = Base64.getEncoder(); byte[] encodedBytes =
	 * encoder.encode(secretKey.getBytes("UTF-8")); String authorizations =
	 * "Basic "+ new String(encodedBytes, 0, encodedBytes.length);
	 * 
	 * paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);
	 * 
	 * URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
	 * 
	 * HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	 * connection.setRequestProperty("Authorization", authorizations);
	 * connection.setRequestProperty("Content-Type", "application/json");
	 * connection.setRequestMethod("POST"); connection.setDoOutput(true); JSONObject
	 * obj = new JSONObject(); obj.put("paymentKey", paymentKey); obj.put("orderId",
	 * orderId); obj.put("amount", amount);
	 * 
	 * OutputStream outputStream = connection.getOutputStream();
	 * outputStream.write(obj.toString().getBytes("UTF-8")); // 통신끝
	 * 
	 * int code = connection.getResponseCode(); boolean isSuccess = code == 200 ?
	 * true : false;
	 * 
	 * InputStream responseStream = isSuccess? connection.getInputStream():
	 * connection.getErrorStream();
	 * 
	 * Reader reader = new InputStreamReader(responseStream,
	 * StandardCharsets.UTF_8); JSONParser parser = new JSONParser(); JSONObject
	 * jsonObject = (JSONObject) parser.parse(reader); responseStream.close(); }
	 */
}
