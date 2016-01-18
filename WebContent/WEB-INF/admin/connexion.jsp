<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="container">
        <div class="row">
           <c:if test="${!empty sessionScope.sessionAdmin}">
           	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
           	<div class="col-md-12">
               <div class="alert alert-success" role="alert">
                   <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionAdmin.email}</p>
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
	           <form method="post" action="connexion">
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