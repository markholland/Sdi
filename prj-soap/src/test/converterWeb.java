package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.example.www.eurocal.Eurocal_PortType;
import org.example.www.eurocal.Eurocal_ServiceLocator;

/**
 * Servlet implementation class converterWeb
 */
@WebServlet("/converterWeb")
public class converterWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public converterWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String currency = request.getParameter("currency");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String convert = request.getParameter("convert");
		
		Eurocal_ServiceLocator service = new Eurocal_ServiceLocator();
		Eurocal_PortType stub;
		double converted = 0.0;

		try { 
			stub = service.geteurocalSOAP();
		} catch(ServiceException e) {
			e.printStackTrace();
			return;
		}
		out.println(ServletUtilities.headWithTitle("Currency Converter"));
		out.println("<BODY BGCOLOR=\"#FDF5E6\"><H1 ALIGN=\"CENTER\">Currency Converter</H1><CENTER>");
		try {
			if(convert.equals("0")) {
				converted = stub.convertirAEuros(currency, amount);
				String c = String.format("%.2f", converted);
				out.println(amount+" "+currency+" is "+c+" in Euros");
			} else if(convert.equals("1")){
				converted = stub.convertirEuros(currency, amount);
				out.println(amount+" Euros is " +converted+" in  "+currency);
			}
			
		} catch (RemoteException e) {
			System.out.println("Servicio no disponible o fuera de línea. ");
			//return;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		} 
		
		out.println("</CENTER></BODY></HTML>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
