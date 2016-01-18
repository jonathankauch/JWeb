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
        <form method="post" action="editNews" enctype="multipart/form-data">
            <div class="text-center">
                <h2>Edit news</h2>
            </div>
            <label for="title">Title<span class="requis">*</span></label>
            <input type="text" id="title" name="title" class="form-control" value="<c:out value="${n.title}"/>" size="20" maxlength="60" />
            <span class="erreur">${form.error['title']}</span>
            <br />
            
            <label for="article">Article<span class="requis">*</span></label>
            <textarea class="form-control" name="article" id="article" >
                ${n.article}
            </textarea>
            <span class="erreur">${form.error['article']}</span>
            <br />

            <label>File</label>
            <input type="file" name="image" id="image" class="form-control" />
            <br />
            
            <input type="hidden" name="oldimage" id="oldimage" value="${n.image}"/>
            
            <p>
            	This is your image
            </p>

			<div class="row"> 
			<p>
            <c:set var="tmp" value="${fn:substring(n.image, 0, 4)}" />
		        		<c:choose>
		        		<c:when test="${tmp == 'http'}">
		        			<td><img src="${n.image}" alt="" class="img-responsive"/></td>
		        		</c:when>
		        		<c:otherwise>
		        			<td><img src="../upload/${n.image}" alt="" class="img-responsive"/></td>
		        		</c:otherwise>
		        		</c:choose>      
		    </p>
		    </div>
            <input type="submit" value="Edit product" class="btn btn-success" />
            <br />
            
        </form>
    </div>
</div>
</div>
<jsp:include page="/include/footer.jsp" />