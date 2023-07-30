<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,model.BoardVO,model.OrderVO,model.ProductVO"%>
<jsp:useBean id="bDAO" class="model.BoardDAO"/>
<jsp:useBean id="pDAO" class="model.ProductDAO"/>
<jsp:useBean id="oDAO" class="model.OrderDAO"/>
<jsp:useBean id="bVO" class="model.BoardVO"/>
<jsp:setProperty property="*" name="bVO"/>
<jsp:useBean id="pVO" class="model.ProductVO"/>
<jsp:setProperty property="*" name="pVO"/>
<jsp:useBean id="oVO" class="model.OrderVO"/>
<jsp:setProperty property="*" name="oVO"/>
<%
String action = request.getParameter("action");

	// 상품 목록
	if (action.equals("ProductList")) {
		ArrayList<ProductVO> pdatas = pDAO.selectAll(null);
		request.setAttribute("pdatas", pdatas);
		request.getRequestDispatcher("ProductList.jsp").forward(request, response);
	}
	// 주문 목록
	else if (action.equals("OrderList")) {
		ArrayList<OrderVO> odatas = oDAO.selectAll(null);
		request.setAttribute("odatas", odatas);
		request.getRequestDispatcher("OrderList.jsp").forward(request, response);
	}
	// 주문 상세
	else if (action.equals("OrderDetail")) {
		oVO = oDAO.selectOne(oVO);
		request.setAttribute("odata", oVO);
		request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
	}
	// 주문
	else if (action.equals("OrderAdd")) {
		pVO = pDAO.selectOne(pVO);
		request.setAttribute("pdata", pVO);
		request.getRequestDispatcher("OrderAdd.jsp").forward(request, response);
	}
	// 주문 결제
	else if (action.equals("OrderPay")) {
		request.setAttribute("odata", oVO);
		session.setAttribute("sodata", oVO);
		request.getRequestDispatcher("OrderPay.jsp").forward(request, response);
	}
	// 주문 결제 완료
	else if (action.equals("OrderEnd")) {
		oVO = (OrderVO)session.getAttribute("sodata");
		oDAO.insert(oVO);
		session.removeAttribute("sodata");
		response.sendRedirect("controller.jsp?action=OrderList");
	}
	// 후기 목록
	else if (action.equals("BoardList")) {
		ArrayList<BoardVO> bdatas = bDAO.selectAll(null);
		request.setAttribute("bdatas", bdatas);
		request.getRequestDispatcher("BoardList.jsp").forward(request, response);
	}
	// 후기 상세
	else if (action.equals("BoardDetail")) {
		bVO = bDAO.selectOne(bVO);
		request.setAttribute("bdata", bVO);
		request.getRequestDispatcher("BoardDetail.jsp").forward(request, response);
	}
	// 후기 작성 페이지
	else if (action.equals("BoardAddPage")) {
		request.setAttribute("oNum", oVO.getoNum());
		request.getRequestDispatcher("BoardAdd.jsp").forward(request, response);
	}
	// 후기 작성
	else if (action.equals("BoardAdd")) {
		bDAO.insert(bVO);
		response.sendRedirect("controller.jsp?action=BoardList");
	}
	else{
		// action 값 x
	}
%>