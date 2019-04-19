<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/9
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sql:query var="rs_book" dataSource="jdbc/ebook">
    select book_id,name,author,price,amount,sales,isbn from book
</sql:query>
<sql:query var="rs_user" dataSource="jdbc/ebook">
    select user_id,account,password,mailbox from users
</sql:query>
<sql:query var="rs_order" dataSource="jdbc/ebook">
    select user_id,book_id from orders
</sql:query>
<html>
<head>
    <title>database Data</title>
</head>
<body>
        <h2>Results</h2>
        <h4>books</h4>
        <c:forEach var="row" items="${rs_book.rows}">
            ID:${row.book_id}<br/>
            Name:${row.name}<br/>
            Author:${row.author}<br/>
            Price:${row.price}<br/>
            ISBN:${row.isbn}<br/>
            Amount:${row.amount}<br/>
            Sales:${row.sales}<br/>
        </c:forEach>
        <h4>user</h4>
        <c:forEach var="row" items="${rs_user.rows}">
            ID:${row.user_id}<br/>
            Account:${row.account}<br/>
            Password:${row.password}<br/>
            Mailbox:${row.mailbox}<br/>
        </c:forEach>
</body>
</html>
