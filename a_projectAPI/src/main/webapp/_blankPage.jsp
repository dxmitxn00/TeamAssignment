<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Windmill Dashboard</title>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
<link rel="stylesheet" href="./assets/css/tailwind.output.css" />
<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
<script src="./assets/js/init-alpine.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" defer></script>
<script src="./assets/js/charts-lines.js" defer></script>
<script src="./assets/js/charts-pie.js" defer></script>
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
					
				</div>
				<!-- END CONTAINER -->
			</main>
		</div>
	</div>
</body>
</html>