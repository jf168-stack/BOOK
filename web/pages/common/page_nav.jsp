<%--
  Created by IntelliJ IDEA.
  User: jiangfeng
  Date: 2020/6/28
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条开始--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                location.href = "http://localhost:8080/book_war_exploded/${requestScope.page.url}&pageNo=" + pageNo;
            })

        });
    </script>
</div>
<%--分页条结束--%>
