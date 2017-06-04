
<jsp:useBean id="emp" type="com.example.Person" class="com.example.Employee" scope="request">
	<jsp:setProperty name="emp" property="name" param="userName"/>
	<jsp:setProperty name="emp" property="employeeID" param="empID"/>
</jsp:useBean>

<center><h1>JSP Standard Actions</h1><center><br><br>

Your username is: <jsp:getProperty name="emp" property="name"/><br>
Your employee ID is: <jsp:getProperty name="emp" property="employeeID"/><br>
Welcome.
