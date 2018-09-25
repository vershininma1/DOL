<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.dol.entity.MySet" %>
<%ResourceBundle mybundle = ResourceBundle.getBundle("Bundle_Ru");%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/bubble_style.css"/>
    <title><%=mybundle.getString("add_title")%></title>
</head>
<body>
<%String errorClass="hidden_div";
    String ourError = (String) session.getAttribute("addError");
    String textError= mybundle.getString("add_error");
    if(!(ourError==null||ourError.equals(""))) {
        errorClass = "";
    }
%>
<div class="<%=errorClass%>">
    <div class="container">
        <div class="speech-bubble">
            <h2><%=textError%></h2>
        </div>
    </div>
</div>
<div class="container" style="max-width: 600px;">
    <h3> <%=mybundle.getString("add_add_contract")%></h3>
    <form  role="form" action="add" method="post"  enctype="multipart/form-data" >
        <div class="form-group">
        <label for="userLogin"><%=mybundle.getString("add_name")%></label>
        <input class="form-control" type="text" id="userLogin" value="${sessionScope.addUserLogin}" name="userLogin" required/>
        </div>
            <div class="form-group">
        <label for="passportID"><%=mybundle.getString("add_passport")%> </label>
            <input class="form-control" type="text" id="passportID" value="${sessionScope.addPassportID}" name="passportID" required pattern="[0-9]{10}" title="<%=mybundle.getString("add_passport_help")%>"/>

            </div>

        <div class="form-group">

            <label><%=mybundle.getString("add_sets")%></label>
            <%
              Object ourObj= session.getAttribute("addSetIdParam");
               String SelectedSet=(String)ourObj;
                if(ourObj==null){SelectedSet="";}
                String Selected="";
            %>
            <select class="form-control" name="SetIdParam">
                <c:forEach items="${mysets7}" var="set7">
                    <%
                        Selected="";
                        if(!(SelectedSet.equals(""))) {
                            Object qwe = pageContext.findAttribute("set7");
                            MySet ourset = (MySet) (qwe);
                            int ourSetId = ourset.getSet_ID();
                            int ourId = Integer.valueOf(SelectedSet);
                            if (ourSetId == ourId) {
                                Selected = "selected";
                            }
                        }
                    %>



                <option <%=Selected%> value="${set7.set_ID}">${set7}</option>
            </c:forEach>
        </select>
        </div>

                <div class="form-group">
            <input  name="data" type="file" required>
                </div>
        <input type="hidden" name="id" value="${contract.id}" />
        <input class="form-control"  type="submit" value="<%=mybundle.getString("add_save")%>" />
    </form>

<input class="form-control" type="button" value="<%=mybundle.getString("add_back")%>" onclick="location.href='list?searchname=&searchsetname=&searchstatusname=&startdate=&enddate=';" />
</div>
</body>
</html>