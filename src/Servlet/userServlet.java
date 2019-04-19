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

@WebServlet(name = "userServlet")
public class userServlet extends HttpServlet {
    private static final long servialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            Connection connect = DBConfig.getConnection();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
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
            response.setContentType("text/html;charset=UTF-8");
            System.out.println(jsonlist);
            out.println(jsonlist);
            out.flush();
            out.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
