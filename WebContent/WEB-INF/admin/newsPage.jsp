<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="container">
    	<div class="row">
    		<div class="col-md-12">
    			<h1>News</h1>
    			<a href="addNews">
    				<button class="btn btn-success"><i class="fa fa-plus"></i> Add new article</button>
    			</a>
    		</div>
    		<c:if test="${!empty res}">
	    		<div class="alert alert-success" role="alert">
	    			<p>${res}</p>
				</div>
			</c:if>
    	</div>
    </div>
    <div class="container">
        <div class="row">
        <div class="col-md-12">
	        <table class="table">
	        	<thead>
	        		<th>#</th>
		        	<th>Title</th>
		        	<th>Image</th>
		        	<th>date</th>
		        	<th>article</th>
		        	<th>Action</th>
	        	</thead>
	        	<tbody>
	        		 <c:forEach items="${news}" var="n">
	        		 <tr>
	        		 	<td>${n.id}</td>
		        		<td>${n.title}</td>
		        		<c:set var="tmp" value="${fn:substring(n.image, 0, 4)}" />
		        		<c:choose>
		        		<c:when test="${tmp == 'http'}">
		        			<td><img src="${n.image}" alt="" width="80"/></td>
		        		</c:when>
		        		<c:otherwise>
		        			<td><img src="../upload/${n.image}" alt="" width="80"/></td>
		        		</c:otherwise>
		        		</c:choose>
		        		<td style="width:100px">${n.date}</td>
		        		<c:set var="descrTruncate" value="${fn:substring(n.article, 0, 250)}" />
			        	<td>${descrTruncate}...</td>
			        	<td>
			        	<a href="editNews?id=${n.id}">
				        	<button class="btn btn-info"><i class="fa fa-pencil"></i></button>
				        </a>
			        	<a href="deleteNews?id=${n.id}">
			        		<button class="btn btn-danger"><i class="fa fa-close"></i></button>
			        	</a>
			        	</td>
			        </tr>
			        </c:forEach>
	        	</tbody>
	        </table>
	        </div>
        </div>
    </div>
<jsp:include page="/include/footer.jsp" />