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

@WebServlet(urlPatterns="/NewStudentPage", loadOnStartup=1)
public class NewStudentPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewStudentPage() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	// create a list to track the groups available
    	List<NewGroupEntry> groupEntries = new ArrayList<NewGroupEntry>();
    	
    	// create a list to track the groups available
    	List<NewStudentEntry> studentEntries = new ArrayList<NewStudentEntry>();
    	
    	// set the attribute of groupEntries
    	getServletContext().setAttribute("groupEntries", groupEntries);
    	
    	// set the attribute of studentEntries
    	getServletContext().setAttribute("studentEntries", studentEntries);
    }
    
    private NewGroupEntry getGroup(int id) {
    	// get all groups that are available
    	ArrayList<NewGroupEntry> groupEntries = (ArrayList<NewGroupEntry>) getServletContext().getAttribute("groupEntries");
    	// loop through all groups that are available
    	for(NewGroupEntry currentGroup:groupEntries) {
    		// if the group id's match
    		if(currentGroup.getId() == id) {
    			// return the current group that has matching id
    			return currentGroup;
    		}
    	}
    	return null;
    		
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// PrintWriter to display text
		PrintWriter output = response.getWriter();
		
		// ArrayList holding Groups available
		List<NewGroupEntry> groupEntries = (ArrayList<NewGroupEntry>) getServletContext().getAttribute("groupEntries");
		
		output.println("<html><head><title>Add Student</title></head><body>");
		// create the form for the page
		output.println("<form action='NewStudentPage' method='post'>");
		// line to start the table border and cellpadding
		output.println("<table border=\"1\" cellpadding=\"2\"> ");
		// line to control first row in table (Name)
		output.println("<tr><th>Name</th><td><input type='text' name='nameInput'></td></tr> ");
		// line to control second row in table (Birth Year)
		output.println("<tr><th>Birth Year</th><td><input name='birthYearInput'></td></tr> ");
		// line to control third row in table (Parent Name)
		output.println("<tr><th>Parent Name</th><td><input type='text' name='parentNameInput'></td></tr> ");
		// line to control fourth row in table (Parent Email)
		output.println("<tr><th>Parent Email</th><td><input type='text' name='parentEmailInput'></td></tr>");
		// line to control fifth row in table (Group)
		output.println("<tr><th>Group</th><td><select name='groupDropdown'>");
		for(NewGroupEntry entry:groupEntries) {
			if(entry.getNumOfStudents()<5 && entry.getName()!="No Group") {
				output.println("<option value="+entry.getId()+">"+entry.getName()+"</option>");
			}
		}
		output.println("<option value='0'> </option></select></td></tr>");
		// line to control sixth row in table (button)
		output.println("<tr><td colspan='2'><button name='addButton'>Add</button></td></tr>");
		// line to close out the table
		output.println("</table>");
		output.println("</form>");
		// line to close out the page
		output.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create a new student with correct group
		String name = request.getParameter("nameInput");
		int birthYear = Integer.parseInt(request.getParameter("birthYearInput"));
		String parentName = request.getParameter("parentNameInput");
		String parentEmail = request.getParameter("parentEmailInput");
		// check to make determine if student has group or not
		NewGroupEntry group=null;
		if(Integer.parseInt(request.getParameter("groupDropdown")) == 0) {
			group= new NewGroupEntry("No Group");
		}
		else {
			group = getGroup(Integer.parseInt(request.getParameter("groupDropdown")));
		}
		group.addStudent();
		NewStudentEntry entry = new NewStudentEntry(name, birthYear, parentName, parentEmail, group);
		
		// get and save the groupEntries attribute
		ArrayList<NewStudentEntry> studentEntries = (ArrayList<NewStudentEntry>) getServletContext().getAttribute("studentEntries");
		// add the entry to groupEntries
		studentEntries.add(entry);
		
		// redirect user to GroupListingPage
		response.sendRedirect("StudentListingPage");
		
	}

}
