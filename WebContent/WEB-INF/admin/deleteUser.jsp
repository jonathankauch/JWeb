<jsp:include page="header.jsp" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Delete</h1>
				<br />
				<c:if test="${!empty res}">
					<div class="alert alert-success" role="alert">
					<p>${res}</p>
					<a href="users">Return to user list</a>
					</div>
				</c:if>
				<c:if test="${!empty error}">
					<div class="alert alert-danger" role="alert">
						<p>
							${error}
						</p>
						<a href="users">Return to user list</a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
<jsp:include page="/include/footer.jsp" />