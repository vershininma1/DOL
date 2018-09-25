<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%ResourceBundle mybundle = ResourceBundle.getBundle("Bundle_Ru");%>

<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/bubble_style.css"/>
    <title><%=mybundle.getString("login_title")%></title>
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
<div class="container" style="max-width: 400px;">
    <form role="form" action="login" method="post">
        <div class="form-group">
     <label for="login"><%=mybundle.getString("login_login")%></label>
                <input class="form-control" type="text" id="login" value="${user.login}" name="login" />
        </div>
        <div class="form-group">
              <label for="pass"><%=mybundle.getString("login_pass")%>   </label>
            <input class="form-control" type="password" id="pass" value="${user.pass}" name="pass" />
        </div>
        <input  class="form-control" type="submit" value="<%=mybundle.getString("login_ok")%>" />
    <%String error1 = request.getParameter("err");
    String errorMessage="";
    if(error1!=null){errorMessage=mybundle.getString("login_message");}
    %>
    <p><%=errorMessage%></p>
    </form>
</div>
</body>
</html>