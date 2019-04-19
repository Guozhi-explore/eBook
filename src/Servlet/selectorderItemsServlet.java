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
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(name = "selectorderItemsServlet", value="/selectorderItemsServlet")
public class selectorderItemsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            Connection connect = DBConfig.getConnection();
            Statement stmt = connect.createStatement();
            int user_id=parseInt(request.getParameter("user_id"));
            String time1=request.getParameter("time1");
            String time2=request.getParameter("time2");
            String sql="select order_time,sum(price*number)as total_price,order_id from (orders natural join order_item) natural join book where user_id=? and order_time>? and order_time<? group by order_id";

            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setString(2,time1);
            preparedStatement.setString(3,time2);
            ResultSet rs = preparedStatement.executeQuery();

            JSONArray jArray = new JSONArray();
            JSONObject jo = new JSONObject();
            while (rs.next()) {
                jo.put("time", rs.getString("order_time"));
                jo.put("total_price",rs.getString("total_price"));

                ArrayList<String> bookarray=new ArrayList<String>();
                int order_id=rs.getInt("order_id");
                String sql2="select name,number from order_item natural join book where order_id=?";
                PreparedStatement preparedStatement2 = connect.prepareStatement(sql2);
                preparedStatement2.setInt(1,order_id);
                ResultSet rs2 = preparedStatement2.executeQuery();
                while(rs2.next())
                {
                    bookarray.add( rs2.getString("name")+':'+rs2.getString("number")+'本');
                }

                jo.put("books",bookarray);
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
