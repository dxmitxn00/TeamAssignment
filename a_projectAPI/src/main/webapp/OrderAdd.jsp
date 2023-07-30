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
					<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">주문 페이지</h2>
					<div class="max-w-2xl px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"> 
						<form action="controller.jsp" method="post">
							<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="hidden" name="action" value="OrderPay">
							<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="hidden" name="pNum" value="${ pdata.pNum }" readonly>
							상품이름: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" name="pName" value="${ pdata.pName }" readonly><br>
							상품가격: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" name="pPrice" value="${ pdata.pPrice }" readonly><br>
							주문자 이름: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" name="oName"><br>
							핸드폰 번호: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" name="oPhone"><br>	
					</div>
					<div class="max-w-2xl px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"> 
							<span id="guide" style="color: #999; display: none"></span>
							<input class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
							우편번호: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" id="postcode" readonly><br>
							배송 지번 주소: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" id="jibunAddress" readonly><br>
							배송 도로명 주소: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" id="roadAddress" name="oAddress" readonly><br>
							법정동명: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" id="extraAddress" readonly><br>
							배송 상세주소: <input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" type="text" name="oAddressDet"><br>
					</div>
					<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
					<script>
					//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
					function execDaumPostcode() {
						new daum.Postcode({
							oncomplete : function(data) {
								// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			
								// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
								// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
								var roadAddr = data.roadAddress; // 도로명 주소 변수
								var extraRoadAddr = ''; // 참고 항목 변수
			
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
									extraRoadAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
									&& data.apartment === 'Y') {
										extraRoadAddr += (extraRoadAddr !== '' ? ', '
													+ data.buildingName
													: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraRoadAddr !== '') {
									extraRoadAddr = '(' + extraRoadAddr + ')';
								}
			
								// 우편번호와 주소 정보를 해당 필드에 넣는다.
								document.getElementById("postcode").value = data.zonecode;
								document.getElementById("roadAddress").value = roadAddr;
								document.getElementById("jibunAddress").value = data.jibunAddress;
			
								// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
								if (roadAddr !== '') {
									document.getElementById("extraAddress").value = extraRoadAddr;
								} else {
									document.getElementById("extraAddress").value = '';
								}
			
								var guideTextBox = document.getElementById("guide");
								// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
								if (data.autoRoadAddress) {
									var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
									guideTextBox.innerHTML = '(예상 도로명 주소 : '
															+ expRoadAddr + ')';
									guideTextBox.style.display = 'block';
			
								} else if (data.autoJibunAddress) {
									var expJibunAddr = data.autoJibunAddress;
									guideTextBox.innerHTML = '(예상 지번 주소 : '
														+ expJibunAddr + ')';
									guideTextBox.style.display = 'block';
								} else {
									guideTextBox.innerHTML = '';
									guideTextBox.style.display = 'none';
								}
							}
						}).open();
					}
					</script>
							<input class="flex items-center justify-center w-full px-4 py-2 text-sm font-medium leading-5 text-white text-gray-700 transition-colors duration-150 border border-gray-300 rounded-lg dark:text-gray-400 active:bg-transparent hover:border-gray-500 focus:border-gray-500 active:text-gray-500 focus:outline-none focus:shadow-outline-gray" type="submit" value="결제하기">
						</form>
				</div>
				<!-- END CONTAINER -->
			</main>
		</div>
	</div>
</body>
</html>