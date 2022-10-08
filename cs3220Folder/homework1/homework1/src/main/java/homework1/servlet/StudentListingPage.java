package homework1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework1.model.NewGroupEntry;
import homework1.model.NewStudentEntry;

@WebServlet("/StudentListingPage")
public class StudentListingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
       
    public StudentListingPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// PrintWriter to display text
		PrintWriter output = response.getWriter();
		// ArrayList holding Groups available
		ArrayList<NewGroupEntry> groupEntries = (ArrayList<NewGroupEntry>) getServletContext().getAttribute("groupEntries");
		
		//ArrayList holding students available
		ArrayList<NewStudentEntry> studentEntries = (ArrayList<NewStudentEntry>) getServletContext().getAttribute("studentEntries");
		//studentEntries.add(new NewStudentEntry("Jonathan", 2001, "ParentName", "Some@email.com",(new NewGroupEntry("some new group"))));

		output.println("<html><head><title>Student Listing Page</title></head><body>");
		// display link for 'Students' page
		output.println("<a href='NewStudentPage'>New Student</a> ");
		
		// if there are no groups then just show the "New Group" link
		if(studentEntries.isEmpty()) {
			output.println();
		}
		else {
			// create the form for the page
			output.println("<form action='NewStudentPage' method='post'>");
			// line to start the table border and cellpadding
			output.println("<table border=\"1\" cellpadding=\"2\"> ");
			// line to control the header
			output.println("<tr><th>Student</th><th>Age</th><th>Parent</th><th>Email</th></tr> ");
			for(NewStudentEntry entry:studentEntries) {
				output.println("<tr>");
				output.println("<td>"+entry.getName()+"</td>");
				output.println("<td>"+(currentYear - entry.getBirthYear())+"</td>");
				output.println("<td>"+entry.getParentName()+"</td>");
				output.println("<td>"+entry.getParentEmail()+"</td>");
				output.println("</tr>");
			}
			output.println("</select></td></tr>");
			// line to close out the table
			output.println("</table>");
			output.println("</form>");
		}
		output.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
