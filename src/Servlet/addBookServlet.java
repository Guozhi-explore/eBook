package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "addBookServlet",value = "/addBookServlet")
public class addBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            Connection connect = DBConfig.getConnection();

            String name=request.getParameter("name");
            String author =request.getParameter("author");
            String isbn=request.getParameter("isbn");
            int price =parseInt(request.getParameter("price"));
            int amount=parseInt(request.getParameter("amount"));

            String sql1 = "insert into book values(0,?,?,?,?,0,?,'摘要在mongodb')";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,isbn);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5,amount);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
