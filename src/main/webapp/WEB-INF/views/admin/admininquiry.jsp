<%--
  Created by IntelliJ IDEA.
  User: Gram17
  Date: 2024-02-06
  Time: 오후 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/admin/admin.css">
</head>
<body>

    <jsp:include page="/WEB-INF/views/admin/adminheader.jsp"/>

    <div class="admin-common-wrap">
        <div class="admin-common-snb">
            <div class="snb-header">
                <h2>
                    문의관리
                </h2>
            </div>
            <dl>
                <dt class="menu_toggle">문의관리</dt>
                <dd class="active">
                    <a href="/admin/user">문의 정보관리</a>
                </dd>
                <dd class="">
                    <a href="/admin/register">문의 등록하기</a>
                </dd>
            </dl>
        </div>
        <div class="admin-common-content">
            <div class="admin-common-title">
                <h1>문의 관리</h1>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/admin/adminfooter.jsp"/>

</body>
</html>
