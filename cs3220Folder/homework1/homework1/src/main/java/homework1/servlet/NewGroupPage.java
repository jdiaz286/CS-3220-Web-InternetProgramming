package homework1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework1.model.NewGroupEntry;
import homework1.model.NewStudentEntry;

@WebServlet(urlPatterns="/NewGroupPage", loadOnStartup=1)
public class NewGroupPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewGroupPage() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	// create a list to track the groups available
    	List<NewGroupEntry> groupEntries = new ArrayList<NewGroupEntry>();
    	
    	/*
    	// default "no group" option
    	NewGroupEntry noGroup= new NewGroupEntry(" ");
    	groupEntries.add(noGroup);
		*/
    	
    	// create a list to track the groups available
    	List<NewStudentEntry> studentEntries = new ArrayList<NewStudentEntry>();
    	
    	// set the attribute of groupEntries
    	getServletContext().setAttribute("groupEntries", groupEntries);
    	
    	// set the attribute of studentEntries
    	getServletContext().setAttribute("studentEntries", studentEntries);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// PrintWriter to display text
		PrintWriter output = response.getWriter();
		
		output.println("<html><head><title>Add Group</title></head><body>");
		// create the form for the page
		output.println("<form action='NewGroupPage' method='post'>");
		// line to start the table border and cellpadding
		output.println("<table border=1 cellpadding=4> ");
		// line to control first row in table (Name)
		output.println("<tr><th>Name</th><td><input name='groupNameInput'></td></tr> ");
		// line to control second row in table (button)
		output.println("<tr><td colspan='2'><button name='addButton'>Add</button></td></tr>");
		// lines to close out the table and rest of page
		output.println("</table>");
		output.println("</form>");
		output.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the name that was used in the input
		String groupName = request.getParameter("groupNameInput");
		// create a NewGroupEntry with input name
		NewGroupEntry entry = new NewGroupEntry(groupName);
		
		// get and save the groupEntries attribute
		ArrayList<NewGroupEntry> groupEntries = (ArrayList<NewGroupEntry>) getServletContext().getAttribute("groupEntries");
		
		// add the entry to groupEntries
		groupEntries.add(entry);
		
		// redirect user to GroupListingPage
		response.sendRedirect("GroupListingPage");
	}

}
