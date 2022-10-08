package homework1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework1.model.NewGroupEntry;
import homework1.model.NewStudentEntry;

@WebServlet("/GroupListingPage")
public class GroupListingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupListingPage() {
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
				
		
		output.println("<html><head><title>Group Listing Page</title></head><body>");
		// display link for 'group' page
		output.println("<a href='NewGroupPage'>New Group</a> ");
		
		// if there are no groups then just show the "New Group" link
		if(groupEntries.isEmpty() && studentEntries.isEmpty()) {
			output.println();
		}
		else {
			// create the form for the page
			output.println("<form action='NewStudentPage' method='post'>");
			// line to start the table border and cellpadding
			output.println("<table border=\"1\" cellpadding=\"2\"> ");
			// line to control the header
			output.println("<tr><th>Group</th><th>Members</th></tr> ");
			
			// for each row, print out each group with students
			for(NewGroupEntry entry:groupEntries) {
				output.println("<tr>");
				output.println("<td>"+entry.getName()+"</td>");
				output.println("<td>");
				// print out all students in group
				String seperator = "";
				for(NewStudentEntry student:studentEntries) {
					if(student.getGroup()==entry) {
						output.print(seperator+student.getName());
			            seperator = ", ";
					}
				}
				output.println("</td>");
				output.println("</tr>");
			}
			// print out the students with no group
			output.println("<tr>");
			output.println("<td>No Group</td><td>");
			String seperator = "";
			for(NewStudentEntry student:studentEntries) {
				if((student.getGroup()).getName()=="No Group") {
					output.print(seperator+student.getName());
		            seperator = ", ";
				}
			}
			output.println("</td></tr>");
			
			// line to close out the table
			output.println("</table>");
			output.println("</form>");
		}
	
		output.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
