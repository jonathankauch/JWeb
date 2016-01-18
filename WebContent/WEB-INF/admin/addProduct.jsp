<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <div class="container">
                <div class="row">
                    <c:choose>
                    <c:when test="${empty form.err && form.result != null}">
                    <div class="col-md-12">
                     <div class="alert alert-success" role="alert">
                         <p class="succes">${form.result}</p>
                     </div>
                 </div>
             </c:when>
             <c:when test="${!empty form.err && form.result != null}">
             <div class="col-md-12">
                 <div class="alert alert-danger" role="alert">
                     <p class="danger">${form.result}</p>
                 </div>
             </div>
         </c:when>
     </c:choose>
     <div class="col-md-offset-4 col-md-4">
        <form method="POST" action="addProduct" enctype="multipart/form-data">
            <div class="text-center">
                <h2>Add product</h2>
            </div>
            <label for="name">Name of the product<span class="requis">*</span></label>
            <input type="text" id="name" name="name" class="form-control" value="<c:out value="${p.name}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.err['name']}</span>
            <br />
            
            <label for="description">Description<span class="requis">*</span></label>
            <textarea class="form-control" name="description" id="description" >
                ${p.description}
            </textarea>
            <span class="erreur">${form.err['description']}</span>
            <br />
            
            <label for="price">Price</label>
            <input type="text" id="price" name="price" class="form-control" value="<c:out value="${p.price}"/>" size="20" maxlength="20" />
            <span class="erreur">${form.err['price']}</span>
            <br />

            <label for="image">File</label>
            <input type="file" name="image" id="image" class="form-control" />
            <br />
            
            <input type="submit" value="Add product" class="btn btn-success" />
            <br />
            
        </form>
    </div>
</div>
</div>
<jsp:include page="/include/footer.jsp" />