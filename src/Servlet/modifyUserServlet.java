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
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "modifyUserServlet",value = "/modifyUserServlet")
public class modifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            Connection connect = DBConfig.getConnection();

            int user_id = parseInt(request.getParameter("user_id"));
            String status = request.getParameter("status");


            String sql1 = "update users\n" +
                    "set status=?\n" +
                    "where user_id=?;";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, user_id);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
