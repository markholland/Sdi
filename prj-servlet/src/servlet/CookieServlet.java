package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet(
		urlPatterns={"/CookieServlet","/servlet/servlet.CookieServlet"})
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Cookie> cookies;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CookieServlet() {
		super();
		// TODO Auto-generated constructor stub
		cookies = new ArrayList<Cookie>();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("Nombre");
		String valor = request.getParameter("Valor");
		cookies.add(new Cookie(nombre, valor));
		response.setContentType("text/html");
		PrintWriter out = new PrintWriter (response.getWriter());
		out.println("<html>");
		out.println("<head><title>Cookie Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>Nuevo cookie:</h1>");
		out.println("Cookie["+nombre+"] = "+valor);
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = new PrintWriter (response.getWriter());
		out.println("<html>");
		out.println("<head><title>Cookie Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>Lista de cookies:</h1>");
		for(Cookie C : cookies) {
			out.println("Cookie["+C.getName()+"] = "+C.getValue()+"<br>");
		}
		out.println("</body></html>");
		out.close();
	}

}
