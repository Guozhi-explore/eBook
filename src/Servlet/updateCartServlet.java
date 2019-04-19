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

@WebServlet(name = "updateCartServlet",value = "/updateCartServlet")
public class updateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            Connection connect = DBConfig.getConnection();

            int user_id=parseInt(request.getParameter("user_id"));
            int book_id=parseInt(request.getParameter("book_id"));
            int update_num=parseInt(request.getParameter("update_num"));    //获取参数

            String sql1="select * from cart where user_id=? and book_id=?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql1);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2,book_id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())                   //这本书已经在购物车中，需要改变数量
            {
                if(update_num==-1){                    //这里指的是不能直接传值替代而需要相加的情况，比如在浏览页点击加入购物车，而购物车原来就有这本书
                    String sql2="update cart\n" +
                            "set number=number+1\n" +
                            "where user_id=? and book_id=?;";
                    preparedStatement=connect.prepareStatement(sql2);
                    preparedStatement.setInt(1,user_id);
                    preparedStatement.setInt(2,book_id);
                    preparedStatement.execute();
                }
                String sql2="update cart\n" +
                        "set number=?\n" +
                        "where user_id=? and book_id=?;";
                preparedStatement=connect.prepareStatement(sql2);
                preparedStatement.setInt(1,update_num);
                preparedStatement.setInt(2,user_id);
                preparedStatement.setInt(3,book_id);
                preparedStatement.execute();
            }
            else{                           //这本书不在购物车中，需要直接插入
                String sql3="insert into cart values(?,?,?);";
                preparedStatement=connect.prepareStatement(sql3);
                preparedStatement.setInt(1,user_id);
                preparedStatement.setInt(2,book_id);
                preparedStatement.setInt(3,update_num);
                preparedStatement.execute();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
