package com.seoil.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.seoil.dao.MovieDAO;
import com.seoil.dto.MovieVO;

@WebServlet("/movieupdate.do")
public class MovieUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MovieUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "movie\\movieUpdate.jsp";
		int code = Integer.parseInt(request.getParameter("code"));
		
		MovieDAO movieDAO = MovieDAO.getInstance();
		MovieVO mVO = movieDAO.selectProductByCode(code);
		request.setAttribute("movie", mVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String saveDirectory = request.getServletContext()
				.getRealPath("images");
		int maxPostSize = 5*1024*1024; //5KB
		String encType="UTF-8";
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request, saveDirectory,
				maxPostSize, encType, policy);
		
		MovieVO mVO = new MovieVO();
		mVO.setCode(Integer.parseInt(multi.getParameter("code")));
		mVO.setTitle(multi.getParameter("mtitle"));
		mVO.setPrice(Integer.parseInt(multi.getParameter("price")));
		mVO.setDirector(multi.getParameter("director"));
		mVO.setActor(multi.getParameter("actor"));
		mVO.setSynopsis(multi.getParameter("synopsis"));
		if(multi.getFilesystemName("poster") == null){
			mVO.setPoster(multi.getParameter("nonmakeImg"));
		}else{
			mVO.setPoster(multi.getFilesystemName("poster"));
		}
		
		MovieDAO productDAO = MovieDAO.getInstance();
		productDAO.updateProduct(mVO);
		response.sendRedirect("movielist.do");
	}
}
