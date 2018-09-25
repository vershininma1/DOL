<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.dol.entity.Status" %>
<%ResourceBundle mybundle = ResourceBundle.getBundle("Bundle_Ru");%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/bubble_style.css"/>
    <title><%=mybundle.getString("list_title")%></title>
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
    <div class="container" style="max-width: 250px;  float: right;">
<input  class="form-control" type="button" value="<%=mybundle.getString("list_logout")%>" onclick="location.href='logout';" />
        </div>
<div class="container">
    <h3> <%=mybundle.getString("list_search_parameters")%></h3>
    <form role="form" action="list" method="post">
            <%String StartDate= request.getParameter("startdate");
                if(StartDate==null){StartDate="";}
            %>
            <%String EndDate= request.getParameter("enddate");
                if(EndDate==null){EndDate="";}
            %>
        <label><%=mybundle.getString("list_start_date")%> <input class="form-control" type="date" value="<%=StartDate%>" name="StartDate1"> </label>
            <label>  <%=mybundle.getString("list_end_date")%> <input class="form-control" type="date"  value="<%=EndDate%>"  name="EndDate1"> </label>
        <label for="SearchName1">   <%=mybundle.getString("list_name")%>
            <%String SearchName= request.getParameter("searchname");
            if(SearchName==null){SearchName="";}
            %>
            <input class="form-control" type="text" id="SearchName1" value="<%=SearchName%>" name="SearchName1" />
        </label>
        <label for="SearchSetName1"><%=mybundle.getString("list_set")%>
            <%String SearchSetName= request.getParameter("searchsetname");
                if(SearchSetName==null){SearchSetName="";}
            %>
            <input class="form-control" type="text" id="SearchSetName1" value="<%=SearchSetName%>" name="SearchSetName1" />
        </label>

        <label for="SearchSetName1"><%=mybundle.getString("list_status")%>
            <% String SelectedStatus= request.getParameter("searchstatusname");
            String Selected="";
            if(SelectedStatus.equals("")){Selected="selected";}
            %>
        <select class="form-control" name="searchStatusName1">
            <option <%=Selected%> value=""></option>
            <c:forEach items="${statuses3}" var="status3" >
                <%
                    Selected="";
                    if(!SelectedStatus.equals("")) {
                        Status ourstatus = (Status) (pageContext.getAttribute("status3", PageContext.PAGE_SCOPE));
                        int ourStatusId = ourstatus.getStatus_ID();
                        int ourId=-1;
                        try {
                            ourId= Integer.valueOf(SelectedStatus);
                        }catch (NumberFormatException  e){}
                        if (ourStatusId ==ourId ) {
                            Selected = "selected";
                        }
                    }
                %>
                <option  <%=Selected%> value="${status3.status_ID}">${status3}</option>
            </c:forEach>
        </select>
        </label>
        <input class="form-control" type="submit" value="<%=mybundle.getString("list_search")%>" />
    </form>
</div>
<div class="container">
    <h3><%=mybundle.getString("list_all_contracts")%></h3>
    <input class="form-control" type="button" value="<%=mybundle.getString("list_add")%>" onclick="location.href='add';" />
    <table class="table table-bordered table-hover">
        <thead>

        <tr>
            <th><%=mybundle.getString("list_table_date")%></th>
            <th><%=mybundle.getString("list_table_name")%></th>
            <th><%=mybundle.getString("list_table_set")%></th>
            <th><%=mybundle.getString("list_table_status")%></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contracts}" var="contract">
            <tr>
                <td> <fmt:formatDate type="date" value="${contract.date}" /></td>
               <td> ${contract.userLogin} </td>
                <td> ${contract.mySet.name} </td>
                <td> ${contract.status} </td>
                <td>    <a href="view?contractnumber=${contract.id}"><%=mybundle.getString("list_view")%></a> </td>
            </tr>
        </c:forEach>
        <tbody>
            </table>
</div>
</div>
</body>
</html>