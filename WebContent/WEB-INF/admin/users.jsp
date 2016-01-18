<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <div class="container">
    	<div class="row">
    		<div class="col-md-12">
    			<h1>Users</h1>
    			<a href="addUser">
    				<button class="btn btn-success"><i class="fa fa-plus"></i> Add new user</button>
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
		        	<th>email</th>
		        	<th>date</th>
		        	<th>last name</th>
		        	<th>first name</th>
		        	<th>address</th>
	        	</thead>
	        	<tbody>
	        		 <c:forEach items="${users}" var="u">
	        		 <tr>
	        		 	<td>${u.id}</td>
		        		<td>${u.email}</td>
		        		<td style="width:100px">${u.dateInscription}</td>
		        		<td>${u.lastName}</td>
			        	<td>${u.firstName}</td>
			        	<td>${u.address}</td>
			        	<td>
			        	<a href="editUser?id=${u.id}">
				        	<button class="btn btn-info"><i class="fa fa-pencil"></i></button>
				        </a>
			        	<a href="deleteUser?id=${u.id}">
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