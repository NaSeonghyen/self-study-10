package com.seoil.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seoil.dao.MovieDAO;
import com.seoil.dto.MovieVO;

@WebServlet("/moviedelete.do")
public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "movie\\movieDelete.jsp";
		int code = Integer.parseInt(request.getParameter("code"));
		
		MovieDAO movieDAO = MovieDAO.getInstance();
		MovieVO mVO = movieDAO.selectProductByCode(code);
		request.setAttribute("movie", mVO);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code = Integer.parseInt(request.getParameter("code"));
		MovieDAO movieDAO = MovieDAO.getInstance();
		movieDAO.deleteMovie(code); 
		response.sendRedirect("movielist.do");
	}
}
