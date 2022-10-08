package homework1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomePage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// PrintWriter to display text
		PrintWriter output=response.getWriter();
		
		output.println("<html><head><title>Home Page</title></head><body>");
		// display link for 'Students' page
		output.println("<a href='StudentListingPage'>Students</a> | ");
		// display link for 'Groups' page
		output.println("<a href='GroupListingPage'>Groups</a>");
		output.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
