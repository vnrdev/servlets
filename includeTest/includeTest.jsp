<html>
<head>
<title>Testing include</title>
</head>
<body>
<font color="blue"><h2>JSP include directive</h2></font><br>
<%@ include file="header.html" %><br>
This is the body. 
<hr>
<font color="blue"><h2>JSP include standard action</h2></font><br>
<jsp:include page="header.html"/>
This is the body.
<hr>
</body>
</html>