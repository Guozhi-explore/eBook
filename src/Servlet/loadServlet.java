package Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.Integer.parseInt;

@WebServlet(name = "loadServlet",value = "/loadServlet")
public class loadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            Connection connect = DBConfig.getConnection();
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            Statement stmt = connect.createStatement();
            String account=request.getParameter("account");
            String password=request.getParameter("password");
            String sql="select * from users where account=? and password=?;";

            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();

            JSONArray jArray = new JSONArray();
            JSONObject jo = new JSONObject();
            while (rs.next()) {
                jo.put("user_id", rs.getInt("user_id"));
                jo.put("account", rs.getString("account"));
                jo.put("password", rs.getString("password"));
                jo.put("status", rs.getString("status"));
                jo.put("mailbox",rs.getString("mailbox"));
                jo.put("ismanager", rs.getInt("ismanager"));
                jArray.add(jo);
            }
            String jsonlist=jArray.toString();
            response.setCharacterEncoding("UTF-8");
            System.out.println(jsonlist);
            out.println(jsonlist);
            out.flush();
            out.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
