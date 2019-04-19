package Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "updateUserServlet",value = "/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            String account=request.getParameter("account");
            String password=request.getParameter("password");
            String mailbox=request.getParameter("mailbox");
            String status=request.getParameter("status");
            Boolean ismanager=Boolean.parseBoolean(request.getParameter("ismanager"));     //从前端axios的json参数中获取

            Connection connect = DBConfig.getConnection();

            //判断用户名是否已经存在
            String sql1="select * from users where account=?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setString(1,account);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                out.println(0);
                return;
            }

            String sql="insert into users values(0,?,?,?,?,?)";
            preparedStatement = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,mailbox);
            preparedStatement.setString(4,status);
            preparedStatement.setBoolean(5,ismanager);
            preparedStatement.execute();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next())
                out.println(resultSet.getInt(1));
            out.flush();
            out.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
