<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/7
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="width: 100%;height: 100% ">
    <div style="z-index: 1;position: absolute;left: 1px;top: 1px"><img src="back.jpg" style="width: 100%;height: 100%;"><img/></div>
    <div style="width: 800px;height: 500px; border: 1px solid #000000;margin-left: 10%;margin-top: 3% ;z-index: 10;position: relative;border-radius: 10px">
        <table>
            <tr>
                <th>ID</th>
                <th>书名</th>
                <th>作者</th>
                <th>isbn</th>
                <th>价格</th>
                <th>库存</th>
                <th>销量</th>
                <th>简介</th>
            </tr>
            <c:forEach items="${books}" var="book" >//这里使用的是jstl标签以及el表达式显示用户信息
                <tr>
                    <th>${book.book_id }</th>
                    <th>${book.name }</th>
                    <th>${book.author }</th>
                    <th>${book.isbn }</th>
                    <th>${book.price }</th>
                    <th>${book.amount }</th>
                    <th>${book.sales}</th>
                    <th>${book.abstract}</th>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
