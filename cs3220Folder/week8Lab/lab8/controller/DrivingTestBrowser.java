package lab8.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab8.model.Question;


@WebServlet(urlPatterns="/DrivingTestBrowser", loadOnStartup=1)
public class DrivingTestBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DrivingTestBrowser() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
    	//list that adds the current list 
    	List<Question> questionsList = new ArrayList<Question>();
    	
    	//read the file with questions
    	try {
    		// BufferedReader to read the file with questions
    		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(getServletContext().getRealPath("/WEB-INF/DrivingTest.txt"))));
    		// variables to keep track of what to add to Question
	        String tracker, inDesc, inAnswerA, inAnswerB, inAnswerC;
	        int inCorrectAnswer;
	        
	        // loop until inDesc is blank
	        while( (inDesc = in.readLine()) != null ) {
	        	// get all 5 lines from the .txt file
	            inAnswerA=in.readLine();
	            inAnswerB=in.readLine();
	            inAnswerC=in.readLine();
	            inCorrectAnswer= Integer.parseInt(in.readLine());
	            // this just skips a blank line and goes to the next line
	            tracker=in.readLine();
	            
	            // create a new instance of Question
	            Question newQuestion=new Question(inDesc, inAnswerA, inAnswerB, inAnswerC, inCorrectAnswer);
	            
	            // add new question to 'questionsList'
	            questionsList.add(newQuestion);
	        }
	        // close the reader to ensure everything is read
	        in.close();
	        //System.out.println(questionsList.get(0).getDescription());
	        
	        // set the attribute of studentEntries
	        getServletContext().setAttribute("questionsList", questionsList);  
	        
    	}
    	// check to make sure file exists
    	catch (FileNotFoundException ex) {
    		ex.printStackTrace();
    	}
    	// check if there are any I/O exceptions
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get all the questions that were found in init()
    	ArrayList<Question> questionsList = (ArrayList<Question>) getServletContext().getAttribute("questionsList");

    	// int to keep track of index
		int currentIndex=0; 	
		if(request.getParameter("currentIndex")!=null) {
			currentIndex=Integer.parseInt(request.getParameter("currentIndex"));
		}
		// reset the index to zero if the index is equal to the size
		if(currentIndex==questionsList.size()) {
			request.getRequestDispatcher("/DrivingTestBrowser?currentIndex=0").forward(request, response);
			currentIndex=0;
			return;
		}
		// if the index greater than last element
		else {
			// send to lab8.jsp
			request.getRequestDispatcher("/lab8.jsp").forward(request, response);
		}
		// set the currentIndex and list as attributes
		request.setAttribute("currentIndex", currentIndex);
		request.setAttribute("questionsList", questionsList);
	}

	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	*/
}
