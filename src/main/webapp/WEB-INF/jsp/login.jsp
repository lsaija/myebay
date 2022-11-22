<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
	<head>
	  <meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="${pageContext.request.contextPath}/assets/css/signin.css" rel="stylesheet">
	</head>
	
	<body class="text-center">
		<main class="form-signin">
	
			<form class="form-signin" name='login' action="${pageContext.request.contextPath}/login" method='POST' novalidate="novalidate">
	
		   	     <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none':'' }" role="alert">
					 ${successMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
			   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
				  ${errorMessage}
				</div>
				
				<div class="alert alert-info alert-dismissible fade show ${infoMessage==null?'d-none': ''}" role="alert">
				  ${infoMessage}
				</div>
				
				
			  	<img class="mb-4 " src="${pageContext.request.contextPath}/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		    	
		    	
			  	<div class="form-floating">
			      <input type="text" name="username" class="form-control" id="inputUsername" placeholder="username">
			      <label for="inputUsername">Username</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
			      <label for="inputPassword">Password</label>
			    </div>
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <input type="hidden" name="idAnnuncioWithNoAuth" value="${idAnnuncioWithNoAuth }">
			    <button class="w-100 btn btn-lg" style="background-color:GreenYellow" type="submit">Sign in</button>
			  
                
			    <div>
			    <div class="w-50 p-3" style="background-color: #;"></div>
			   
			  </div>
			
			      <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/home">Home</a>
			      
			      <div>
			      
			      
      
			    <div class="w-50 p-3" style="background-color: #;"></div>
			   
			  </div>
			   <a href="${pageContext.request.contextPath}/utente/registrazione" class="text-danger icon-link">Registrati subito </a>
	   
			  <div>
			    <div class="w-50 p-3" style="background-color: #;"></div>
			   
			  </div>
			    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
			  
			  
			  
			</form>
		</main>
	</body>
</html>