<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REGISTER</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
      body {
        background-color: #f8f9fa;
        font-family: 'Arial', sans-serif;
      }
      .register-form {
        background-color: #fff;
        border-radius: 15px;
        padding: 30px;
        max-width: 500px;
        margin: 50px auto;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      }
      .form-control {
        border-radius: 50px;
        padding: 15px;
        font-size: 1rem;
      }
      .form-control:focus {
        border-color: #007bff;
        box-shadow: 0 0 8px rgba(0, 123, 255, 0.25);
      }
      .register-btn {
        border-radius: 50px;
        padding: 10px 30px;
        background-color: #007bff;
        border: none;
        color: white;
        font-size: 1.1rem;
        transition: background-color 0.3s;
      }
      .register-btn:hover {
        background-color: #0056b3;
      }
      .form-header {
        text-align: center;
        margin-bottom: 30px;
      }
      .form-header h3 {
        font-weight: bold;
        color: #007bff;
      }
      .login-link {
        text-align: center;
        margin-top: 15px;
      }
      .login-link a {
        color: #007bff;
        text-decoration: none;
      }
      .login-link a:hover {
        text-decoration: underline;
      }
    </style>
  </head>
  <body>

    <div class="container">
      <div class="register-form">
        <div class="form-header">
          <h3>Register</h3>
        </div>
        <c:if test="${not empty ok}">
			    <c:choose>
			        <c:when test="${ok}">
			            <div class="alert alert-success">
			                Account Creation is Successful!
			            </div>
			        </c:when>
			        <c:otherwise>
			            <div class="alert alert-danger">
			                Account Creation Failed!
			            </div>
			        </c:otherwise>
			    </c:choose>
			</c:if>
        <form action="user" method="post">
          <!-- First Name -->
          <div class="mb-3">
            <input type="text" class="form-control" id="firstName" name="firstname" placeholder="Enter your first name" required value="${firstname }">
          </div>

          <!-- Last Name -->
          <div class="mb-3">
            <input type="text" class="form-control" id="lastName" name="lastname" placeholder="Enter your last name" required value="${lastname }">
          </div>

          <!-- Username -->
          <div class="mb-3">
            <input type="text" class="form-control" id="username" name="username" placeholder="Choose a username" required value="${username}">
          </div>
          	<c:if test="${not empty usernameAlreadyExists and usernameAlreadyExists }">
			
			<div class="alert alert-danger">
			                username is unavailable !!
			            </div>
			</c:if>
          

          <!-- Email -->
          <div class="mb-3">
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required value="${email}">
          </div>
          	<c:if test="${not empty emailAlreadyExists and emailAlreadyExists }">
			
			<div class="alert alert-danger">
			                Your Email Already Exists !!
			            </div>
			</c:if>
          

          <!-- Password -->
          <div class="mb-3">
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required value="${password }">
          </div>

          <!-- Submit Button -->
          <div class="d-grid">
            <button type="submit" class="btn register-btn">Register</button>
          </div>
          
         
          <input type="hidden" name="mode" value="REGISTER">
          
          
        </form>

        <!-- Login Link -->
        <div class="login-link">
          <p>Already have an account? <a href="login">Log in</a></p>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
