<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ResourceBundle" %>
<%ResourceBundle mybundle = ResourceBundle.getBundle("Bundle_Ru");%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/bubble_style.css"/>
    <title><%=mybundle.getString("view_title")%></title>
</head>
<body>
<%String errorClass="hidden_div";
    String ourError= (String) request.getAttribute("ourError");
    if(!(ourError==null||ourError.equals(""))) {
        errorClass = "";
    }
%>
<div class="<%=errorClass%>">
    <div class="container">
        <div class="speech-bubble">
            <h2><%=ourError%></h2>
        </div>
    </div>
</div>
<div class="container">
    <h3> <%=mybundle.getString("view_contract_view")%></h3>
<table class="table table-bordered table-hover">
    <tr><th><%=mybundle.getString("view_date")%></th><th><fmt:formatDate type="date" value="${contract.date}" /></th></tr>
    <tr><th><%=mybundle.getString("view_name")%></th><th>${contract.userLogin}</th></tr>
    <tr><th><%=mybundle.getString("view_passport")%></th><th>${contract.passportID}</th></tr>
    <tr><th><%=mybundle.getString("view_set")%></th><th>${contract.mySet}</th></tr>
    <tr><th><%=mybundle.getString("view_status")%></th><th>${contract.status.name}</th></tr>
</table>
<h3><%=mybundle.getString("view_scan")%></h3>
<img alt="scan" src="scan?im=${contract.id}"  width="200" height="200"/>
<input class="form-control" type="button" value="<%=mybundle.getString("view_back")%>" onclick="location.href='list?searchname=&searchsetname=&searchstatusname=&startdate=&enddate=';" />
</div>
</body>
</html>