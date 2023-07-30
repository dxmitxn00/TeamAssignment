<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>후기 등록 페이지</title>
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
	/* 에디터 최소 높이 */
	.ck-editor__editable {
		min-height: 500px;
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
					<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">후기 등록</h2>
					<div class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800">
						<form action="controller.jsp" method="post">
							<input type="hidden" name="action" value="BoardAdd">
							<input type="hidden" name="oNum" value="${oNum}">
							<label class="block text-sm">
				                <span class="text-gray-700 dark:text-gray-400">후기 제목</span>
				                <input name="bTitle" class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" placeholder="Jane Doe">
			            	</label>
			            	<br>
			            	<label class="block text-sm">
				                <span class="text-gray-700 dark:text-gray-400">후기 내용</span>
				                <textarea id="editor" name="bContent" placeholder="Enter some long form content."></textarea>
	              				<!-- CKEditor -->
							    <script>
							        ClassicEditor
							            .create(document.querySelector('#editor'), {
							            	language: "ko"
							            })
							            .catch( error => {
							                console.error( error );
							            });
							    </script>
	              			</label>
	              			<div class="flex mt-6 text-sm">
		              			<input type="submit" value="글쓰기">
              				</div>
              			</form>
					</div>
				</div>
				<!-- END CONTAINER -->
			</main>
		</div>
	</div>
</body>
</html>