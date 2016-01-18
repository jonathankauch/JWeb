<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <div class="container">
                <div class="row">
                    <c:choose>
                    <c:when test="${empty form.error && form.result != null}">
                    <div class="col-md-12">
                     <div class="alert alert-success" role="alert">
                         <p class="succes">${form.result}</p>
                     </div>
                 </div>
             </c:when>
             <c:when test="${!empty form.error && form.result != null}">
             <div class="col-md-12">
                 <div class="alert alert-danger" role="alert">
                     <p class="danger">${form.result}</p>
                 </div>
             </div>
         </c:when>
     </c:choose>
     <div class="col-md-offset-4 col-md-4">
        <form method="post" action="editUser">
            <div class="text-center">
                <h2>Edit user</h2>
            </div>

            <label for="name">Email<span class="requis">*</span></label>
            <input type="email" id="email" name="email" class="form-control" value="<c:out value="${u.email}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['email']}</span>
            <br />
            
            <label for="password">password<span class="requis">*</span></label>
            <input type="password" id="password" name="password" class="form-control" value="<c:out value="${u.password}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['password']}</span>
            <br />
            
            <label for="lastname">Lastname<span class="requis">*</span></label>
            <input type="text" id="lastname" name="lastname" class="form-control" value="<c:out value="${u.lastName}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['lastName']}</span>
            <br />

            <label for="firstname">Firstname<span class="requis">*</span></label>
            <input type="text" id="firstname" name="firstname" class="form-control" value="<c:out value="${u.firstName}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['firstName']}</span>
            <br />
            
            <label for="address">Address<span class="requis">*</span></label>
            <input type="text" id="address" name="address" class="form-control" value="<c:out value="${u.address}"/>" maxlength="60" />
            <span class="erreur">${form.error['email']}</span>
            <br />
            <input type="submit" value="Edit user" class="btn btn-success" />
            <br />
            
        </form>
    </div>
</div>
</div>
<jsp:include page="/include/footer.jsp" />