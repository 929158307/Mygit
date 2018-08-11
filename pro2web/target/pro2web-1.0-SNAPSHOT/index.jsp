<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/addUser.action" method="post">
    添加：
    <table width="100%" border=1>
        <tr>

            <td>用户：<input type="text" name="user.name"/></td>
        </tr>
        <tr>
            <td>密码：<input type="text" name="user.password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="添加"/></td>
        </tr>
    </table>
</form>
查询
    <form action="${pageContext.request.contextPath }/findUser.action" method="post">
        查询：
        <table width="100%" border=1>
            <tr>
                <td>用户：<input type="text" name="user.id"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="查询"/></td>
            </tr>
        </table>


    </table>
</form>
</body>

</html>