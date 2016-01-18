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
        <form method="post" action="addNews" enctype="multipart/form-data">
            <div class="text-center">
                <h2>Add news</h2>
            </div>
            <label for="title">Title<span class="requis">*</span></label>
            <input type="text" id="title" name="title" class="form-control" value="<c:out value="${p.name}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['title']}</span>
            <br />

            <label for="article">Article<span class="requis">*</span></label>
            <textarea class="form-control" name="article" id="article" >
                ${p.description}
            </textarea>
            <span class="erreur">${form.error['article']}</span>
            <br />

            <label for="image">File</label>
            <input type="file" name="image" id="image" class="form-control" />
            <br />

            <input type="submit" value="Add news" class="btn btn-success" />
            <br />
        </form>
    </div>
</div>
</div>
<jsp:include page="/include/footer.jsp" />