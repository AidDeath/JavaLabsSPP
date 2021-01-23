<%@ page import="java.util.ArrayList"%>
<%@ page import="SPPLab.Order" %>
<%@ page import="SPPLab.OrderReader" %>
<%@ page import="SPPLab.Customer" %>
<%@ page import="SPPLab.Employee" %>
<%@ page import="SPPLab.IOrder" %>
<%@ page import="SPPLab.OrderException" %>
<%@ page import="SPPLab.ServiceOrder" %>
<%@ page import="SPPLab.WarrantyOrder" %>
<%@ page import="SPPLab.Vehicle" %>
<%@ page import="java.util.Enumeration" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Автосервис "Последнее пристанище"</title>
  </head>
  <body>
  <p1>Текущая дата: <%= new java.util.Date()%></p1>

  <h1> Автосервис "Последнее пристанище"</h1>
  <!-- Декларируем переменную count-->
  <%! private int count = 0; %>
  <%!
    private ArrayList<Order> OrderList = OrderReader.ReadFromFile("c:\\apache-tomcat-8.5.59\\ServiceOrders.txt"); %>
  <%
      String reqParams = "";
      for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();)
          reqParams += e.nextElement();
      System.out.println(reqParams);

      if (reqParams.contains("OrderNo") || reqParams.contains("Cost"))
          if ( !request.getParameter("Cost").isEmpty() && !request.getParameter("OrderNo").isEmpty() &&
          OrderReader.tryParseInt(request.getParameter("OrderNo")) && OrderReader.tryParseInt(request.getParameter("Cost")))
            OrderList.add(new ServiceOrder(Integer.parseInt(request.getParameter("OrderNo")),
            request.getParameter("Brand"),
            request.getParameter("Model"),
            request.getParameter("EmployeeName"),
            request.getParameter("EmployeePosition"),
            Float.parseFloat(request.getParameter("Cost"))));
          else out.println("<p style=\"font-weight: bold; background-color: red;\">Неверный ввод!</p>");

    out.println("<h3> Размер списка заказов :"+ OrderList.size()+"</h3>");
    out.println("<ul>");
    for (Order order : OrderList) {

      out.println("<li>" + order.ToString() + "</li>");
      String str = order.ToString();
    }
    out.println("</ul>");


  %>

  <h2>Добавить новый заказ</h2>
  <form method="post" style="font-size: 14px">
    <table style="width: 50%">
      <tr>
        <td>Номер заказа</td>
        <td><input type="text" name="OrderNo"/></td>
      </tr>
      <tr>
        <td>Марка автомобиля</td>
        <td><input type="text" name="Brand"/></td>
      </tr>
      <tr>
        <td>Модель автомобиля</td>
        <td><input type="text" name="Model"/></td>
      </tr>
      <tr>
        <td>Закрепленный мастер</td>
        <td><input type="text" name="EmployeeName"/></td>
      </tr>
      <tr>
        <td>Должность мастера</td>
        <td><input type="text" name="EmployeePosition"/></td>
      </tr>
      <tr>
        <td>Стоимость по заказу</td>
        <td><input type="text" name="Cost"/></td>
      </tr>
    </table>
    <input type="submit" value="Добавить заказ" />
  </form>
  </body>
</html>
