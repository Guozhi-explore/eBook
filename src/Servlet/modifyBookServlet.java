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

@WebServlet(name = "modifyBookServlet",value = "/modifyBookServlet")
public class modifyBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            Connection connect = DBConfig.getConnection();

            int book_id=parseInt(request.getParameter("book_id"));
            String name=request.getParameter("name");
            String author =request.getParameter("author");
            String isbn=request.getParameter("isbn");
            int sales=parseInt(request.getParameter("sales"));
            int price =parseInt(request.getParameter("price"));
            int amount=parseInt(request.getParameter("amount"));
            String abstrat=request.getParameter("abstract");

            String sql1 = "update book\n" +
                    "set name=?,author=?,isbn=?,price=?, sales=?,amount=? ,abstract=? where book_id=?;";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,isbn);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5,sales);
            preparedStatement.setInt(6,amount);
            preparedStatement.setString(7,abstrat);
            preparedStatement.setInt(8,book_id);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
