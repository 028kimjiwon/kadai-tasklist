package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;
/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class edit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
            Task m = em.find(Task.class,Integer.parseInt(request.getParameter("id")));


            em.close();

            // セッションスコープ上の不要になったデータを削除

            request.setAttribute("message",m);
            request.setAttribute("_token",request.getSession().getId());


            request.getSession().setAttribute("message_id", m.getId());

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
            rd.forward(request, response);
        }
    }




