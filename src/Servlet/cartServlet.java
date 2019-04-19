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

import static java.lang.Integer.parseInt;

@WebServlet(name = "cartServlet",value = "/cartServlet")
public class cartServlet extends HttpServlet {
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
            int user_id=parseInt(request.getParameter("user_id"));
            String sql="select * \n" +
                    "from book natural join cart\n" +
                    "where user_id=?;";

            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            ResultSet rs = preparedStatement.executeQuery();

            JSONArray jArray = new JSONArray();
            JSONObject jo = new JSONObject();
            while (rs.next()) {
                jo.put("book_id", rs.getInt("book_id"));
                jo.put("name", rs.getString("name"));
                jo.put("price", rs.getInt("price"));
                jo.put("amount", rs.getInt("amount"));
                jo.put("isbn", rs.getString("isbn"));
                jo.put("author", rs.getString("author"));
                jo.put("sales",rs.getInt("sales"));
                jo.put("number",rs.getInt("number"));
                jo.put("abstract",rs.getString("abstract"));
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
