<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Base64"%>
<%@ page import="java.util.Base64.Encoder"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>

<%
   // 결제 승인 API 호출하기 
   String orderId = request.getParameter("orderId");
   String paymentKey = request.getParameter("paymentKey");
   String amount = request.getParameter("amount");
   String secretKey = "test_sk_ZORzdMaqN3w6xajbz0N85AkYXQGw:";
   
   
   Encoder encoder = Base64.getEncoder(); 
   byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
   String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);
   
   paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);
    
   URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
     
   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
   connection.setRequestProperty("Authorization", authorizations);
   connection.setRequestProperty("Content-Type", "application/json");
   connection.setRequestMethod("POST");
   connection.setDoOutput(true);
   JSONObject obj = new JSONObject();
   obj.put("paymentKey", paymentKey);
   obj.put("orderId", orderId);
   obj.put("amount", amount);
   
   OutputStream outputStream = connection.getOutputStream();
   outputStream.write(obj.toString().getBytes("UTF-8"));
   // 통신끝
   
   int code = connection.getResponseCode();
   boolean isSuccess = code == 200 ? true : false;
   
   InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();
   
   Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
   JSONParser parser = new JSONParser();
   JSONObject jsonObject = (JSONObject) parser.parse(reader);
   responseStream.close();
%>

<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>test</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="./assets/css/tailwind.output.css" />
<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
<script src="./assets/js/init-alpine.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" defer></script>
<script src="./assets/js/charts-lines.js" defer></script>
<script src="./assets/js/charts-pie.js" defer></script>
<style>
	p {
		word-wrap: break-word;
	}
</style>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900" :class="{ 'overflow-hidden': isSideMenuOpen }">
		<!-- Desktop sidebar -->
		<jsp:include page="_aside1.jsp"/>
		<!-- Mobile sidebar -->
		<!-- Backdrop -->
		<jsp:include page="_isSideMenuOpen.jsp"/>
		<jsp:include page="_aside2.jsp"/>
		<div class="flex flex-col flex-1 w-full">
			<jsp:include page="_header.jsp"/>
			<main class="h-full overflow-y-auto">
				<!-- START CONTAINER -->
				<div class="container px-6 mx-auto grid">
					<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
              결제 API
            </h2>
            <!-- CTA -->
            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple" href="controller.jsp?action=OrderEnd">
              <div class="flex items-center">
                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                <span>결제 성공</span>
              </div>
              <span>주문 목록 →</span>
            </a>

            <div class="max-w-2xl px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">       
    <%
    if (isSuccess) {
    %>
        <p class="mb-4 text-gray-600 dark:text-gray-400"><strong>결과 데이터 : </strong><%= jsonObject.toJSONString() %></p>
        <p class="mb-4 text-gray-600 dark:text-gray-400"><strong>orderName : </strong><%= jsonObject.get("orderName") %></p>
        <p class="mb-4 text-gray-600 dark:text-gray-400"><strong>method : </strong><%= jsonObject.get("method") %></p>
        <p class="mb-4 text-gray-600 dark:text-gray-400">
            <% if(jsonObject.get("method").equals("카드")) { out.println(((JSONObject)jsonObject.get("card")).get("number"));} %>
            <% if(jsonObject.get("method").equals("가상계좌")) { out.println(((JSONObject)jsonObject.get("virtualAccount")).get("accountNumber"));} %>
            <% if(jsonObject.get("method").equals("계좌이체")) { out.println(((JSONObject)jsonObject.get("transfer")).get("bank"));} %>
            <% if(jsonObject.get("method").equals("휴대폰")) { out.println(((JSONObject)jsonObject.get("mobilePhone")).get("customerMobilePhone"));} %>
        
        </p>
    <%
    } else {
    %>
        <h1>결제 실패</h1>
        <p class="mb-4 text-gray-600 dark:text-gray-400"><%= jsonObject.get("message") %></p>
        <span>에러코드: <%= jsonObject.get("code") %></span>
        <%
    }
    %>
    <h1>${ sodata.oName }</h1>

            </div>	
				</div>
				<!-- END CONTAINER -->
			</main>
		</div>
	</div>
</body>
</html>