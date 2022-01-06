<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lab 8 Display</title>
	</head>
	<body>
		<form action="DrivingTestBrowser" method="post">
		<p> ${questionsList.get(param.currentIndex).getDescription()}</p>
		<p> 1. ${questionsList.get(param.currentIndex).getAnswerA()}</p>
		<p> 2. ${questionsList.get(param.currentIndex).getAnswerB()}</p>
		<p> 3. ${questionsList.get(param.currentIndex).getAnswerC()}</p>
		<p> Correct Answer: ${questionsList.get(param.currentIndex).getCorrectAnswer()}</p>
		<a href="DrivingTestBrowser?currentIndex=${param.currentIndex+1 }" >Next</a>
		</form>
	</body>
</html>