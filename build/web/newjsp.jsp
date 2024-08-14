<%-- 
    Document   : CommissionCalculator
    Created on : Jul 11, 2024, 11:42:30 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="ISO-8859-1">
    <title>Commission Calculator</title>
</head>
<body>
    <h2>Commission Calculator</h2>
    <form action="NewServlet11" method="post">
        <label>Employee Type:</label>
        <select name="employeeType">
            <option value="Salaried">Salaried</option>
            <option value="Non-salaried">Non-salaried</option>
        </select><br><br>
        <label>Item Type:</label>
        <select name="itemType">
            <option value="Standard">Standard</option>
            <option value="General">General</option>
            <option value="Bonus">Bonus</option>
            <option value="Unknown">Unknown</option>
        </select><br><br>
        <label>Customer Type:</label>
        <select name="customerType">
            <option value="Regular">Regular</option>
            <option value="Walk-in">Walk-in</option>
        </select><br><br>
        <label>Item Price:</label>
        <input type="text" name="itemPrice"><br><br>
        <input type="submit" value="Calculate">
    </form>
</body>
</html>