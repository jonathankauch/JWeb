<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>
<body>
<header>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <nav class="navbar navbar-default">
                                <div class="container-fluid">
                                    <!-- Brand and toggle get grouped for better mobile display -->
                                    <div class="navbar-header">
                                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"><span class="sr-only">Toggle
navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                                            <span class="icon-bar"></span></button>
                                        <a class="navbar-brand" href="home">WeaponShop</a></div>
                                    <!-- Collect the nav links, forms, and other content for toggling -->
                                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                        <ul class="nav navbar-nav navbar-right">
                                            <li><a href="products">Products</a></li>
                                            <li><a href="all_news">News</a></li>
                                            <li><a href="newsletter">Newsletter</a></li>
											<c:if test="${empty sessionScope.sessionUser}">
                                            	<li><a href="connexion">Connexion</a></li>
                                                <li><a href="register">Register</a></li>
                                            </c:if>
                                        </ul>
                                    </div>
                                    <!-- /.navbar-collapse -->
                                </div>
                                <!-- /.container-fluid -->
                            </nav>
                        </div>
                    </div>
                </div>
            </header>
    <div class="container">
        <div class="row">
           <c:if test="${!empty sessionScope.sessionUser}">
           	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
           	<div class="col-md-12">
               <div class="alert alert-success" role="alert">
                   <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.email}</p>
               </div>
           	</div>
       		</c:if>
       		<c:if test="${!empty form.error}">
       		<div class="col-md-12">
               <div class="alert alert-danger" role="alert">
                   <p class="danger">${form.error['check']}</p>
               </div>
           	</div>
           	</c:if>
	       <div class="col-md-offset-4 col-md-4">
	           <form method="post" action="<c:url value="/connexion" />">
		           <div class="text-center">
		               <h2>Connexion</h2>
		           </div>
		           <label for="email">Email <span class="requis">*</span></label> 
		           <input type="email" id="email" name="email" class="form-control" value="<c:out value="${user.email}"/>" size="20" maxlength="60" />
		           <span class="erreur">${form.error['email']}</span> <br />
		           <label for="password">Password<span class="requis">*</span></label> <input
		           type="password" id="password" name="password" value="" class="form-control" size="20"
		           maxlength="20" /> <span class="erreur">${form.error['password']}</span>
		           <br />
		           <div class="checkbox">
		               <label for="save">
		                   <input type="checkbox" id="save" name="memoire" />
		                   Se souvenir de moi
		               </label>
		           </div>
		           <input type="submit" value="Connexion" class="btn btn-success" /> <br />
	       		</form>
	   		</div>
		</div>
	</div>
<jsp:include page="/include/footer.jsp" />