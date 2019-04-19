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

@WebServlet(name = "bookServlet")
public class bookServlet extends HttpServlet {
    private static final long servialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String search=request.getParameter("search_text");
        try {
            Connection connect = DBConfig.getConnection();
            String sql1="select * from book where name like CONCAT('%',?,'%')";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setString(1,search);
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
                jo.put("abstract",rs.getString("abstract"));
                jArray.add(jo);
            }
            String jsonlist=jArray.toString();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
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
