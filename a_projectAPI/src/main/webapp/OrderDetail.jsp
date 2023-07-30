<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>후기 상세</title>
<script src="https://cdn.ckeditor.com/ckeditor5/38.1.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="./assets/css/tailwind.output.css" />
<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
<script src="./assets/js/init-alpine.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" defer></script>
<script src="./assets/js/charts-lines.js" defer></script>
<script src="./assets/js/charts-pie.js" defer></script>
<style>
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
					<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">주문 상세 페이지</h2>
					<a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple" href="controller.jsp?action=BoardAddPage&oNum=${ odata.oNum }">
		              	<div class="flex items-center">
			                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
			                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
			                </svg>
			                <span>주문 상세</span>
		              	</div>
              			<span>후기 작성 →</span>
            		</a>
					<div class="max-w-2xl px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"> 
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>주문번호 : </strong>${ odata.oNum }</p> <br>
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>상품이름 : </strong>${ odata.oName }</p> <br>
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>휴대폰번호 : </strong>${ odata.oPhone }</p> <br>
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>주소 : </strong>${ odata.oAddress }</p> <br>
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>상세주소 : </strong>${ odata.oAddress }</p> <br>
						<p class="mb-4 text-gray-600 dark:text-gray-400"><strong>상품번호 : </strong>${ odata.pNum }</p> <br>
					</div>
				</div>
				<!-- END CONTAINER -->
			</main>
		</div>
	</div>
</body>
</html>