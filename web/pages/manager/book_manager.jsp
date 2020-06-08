<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteBook").click(function () {
				return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text());
			})
		})
	</script>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}">修改</a></td>
					<td><a class="deleteBook" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--1.总页码数<=5 范围为1-总页码数--%>
				<c:when test="${requestScope.page.pageTotal <= 5}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
				</c:when>
				<%--1.总页码数>5--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--当前页为1,2,3的情况 范围为1-5--%>
						<c:when test="${requestScope.page.pageNo <= 3}">
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="5"></c:set>
						</c:when>
						<%--当前页为最后3位时，范围pageTotal-5到pageTotal--%>
						<c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
							<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
						</c:when>
						<%--当前页为中间时，范围为pageNo-2 到 pageNo+2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
							<c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<%--根据范围遍历输出页码--%>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<%--当前页--%>
				<c:if test="${i==requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<%--非当前页--%>
				<c:if test="${i!=requestScope.page.pageNo}">
					<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--页码输出的结束--%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script type="text/javascript">
				$(function () {
					//跳转到制定的页码
					$("#searchPageBtn").click(function () {
						//获取页码数
						var pageNo = $("#pn_input").val();
						//利用js地址栏对象location的href属性
						location.href = "http://localhost:8080/book_war_exploded/manager/bookServlet?action=page&pageNo=" + pageNo;
					})

				});
			</script>
		</div>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>