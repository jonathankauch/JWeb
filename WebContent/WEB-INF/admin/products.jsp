<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="container">
    	<div class="row">
    		<div class="col-md-12">
    			<h1>Products</h1>
    			<a href="addProduct">
    				<button class="btn btn-success"><i class="fa fa-plus"></i> Add new product</button>
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
		        	<th>Name</th>
		        	<th>Image</th>
		        	<th>Price</th>
		        	<th>Description</th>
		        	<th>Action</th>
	        	</thead>
	        	<tbody>
	        		 <c:forEach items="${products}" var="p">
	        		 <tr>
	        		 	<td>${p.id}</td>
		        		<td>${p.name}</td>
		        		<c:set var="tmp" value="${fn:substring(p.image, 0, 4)}" />
		        		<c:choose>
		        		<c:when test="${tmp == 'http'}">
		        			<td><img src="${p.image}" alt="" width="80"/></td>
		        		</c:when>
		        		<c:otherwise>
		        			<td><img src="../upload/${p.image}" alt="" width="80"/></td>
		        		</c:otherwise>
		        		</c:choose>
		        		<td>${p.price}</td>
		        		<c:set var="descrTruncate" value="${fn:substring(p.description, 0, 250)}" />
			        	<td>${descrTruncate}...</td>
			        	<td>
			        	<a href="editProduct?id=${p.id}">
				        	<button class="btn btn-info"><i class="fa fa-pencil"></i></button>
				       	</a>
			        	<a href="deleteProduct?id=${p.id}">
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