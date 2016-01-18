<%@ page pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Insert title here</title>
            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
            <link href="css/style.css" rel="stylesheet" type="text/css">
            <link href="owl-carousel/owl.carousel.css" rel="stylesheet" type="text/css">
            <link href="owl-carousel/owl.theme.css" rel="stylesheet" type="text/css">
            <link href="owl-carousel/owl.transitions.css" rel="stylesheet" type="text/css">
            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
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
            <div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="text-center">WeaponShop</h1>
                            <br />
                            <div id="owl-demo" class="owl-carousel owl-theme">
 								<c:forEach items="${products}" var="p">
								  <div class="item">
								  <c:set var="tmp" value="${fn:substring(p.image, 0, 4)}" />
					        		<c:choose>
					        		<c:when test="${tmp == 'http'}">
					        			<img src="${p.image}" alt="${p.name}" class="img-responsive" style="height:500px"/>
					        		</c:when>
					        		<c:otherwise>
					        			<img src="upload/${p.image}" alt="${p.name}" class="img-responsive" style="height:500px"/>
					        		</c:otherwise>
					        		</c:choose>
								  </div>
							 	</c:forEach>
							</div>
                        </div>
                    </div>
                </div>
               	<div class="container homepage-description">
               		<div class="row">
               			<div class="col-md-4">
               				<div class="homepage-picto">
               					<img src="img/target67.png" class="img-responsive" alt="target" />
               					<h3 class="text-center">Target</h3>
               					<p>Target and find your favorite weapon. We have the best weapon for the best price. We have almost 1000 weapons to sell</p>
               				</div>
               			</div>
               			<div class="col-md-4">
               				<div class="homepage-picto">
               					<img src="img/gun9.png" class="img-responsive" alt="gun" />
               					<h3 class="text-center">Weapon</h3>
               					<p>We have the smallest weapon to the biggest. We sell gun, machine gun, sniper, shotgun or assault rifles. We have no limit even TANK !
               				</div>
               			</div>
               			<div class="col-md-4">
               				<div class="homepage-picto">
               					<img src="img/bullet6.png" class="img-responsive" alt="bullet" />
               					<h3 class="text-center">Fast</h3>
               					<p>Our website is the fastest way to buy a weapon. You will receive your order under 48 hours. We can be delivered in all countries
               					</p>
               				</div>
               			</div>
               		</div>
               	</div>
               	<div class="container">
               		<div class="row">
               			<div class="col-md-12">
               				<h2>Lastest weapons</h2>
               				<br />
               				<br />
               				<br />
               			</div>
              			<c:forEach items="${products}" var="p">
              			<div class="col-sm-6 col-md-4">
						    <div class="thumbnail">
						      <c:set var="tmp" value="${fn:substring(p.image, 0, 4)}" />
					        		<c:choose>
					        		<c:when test="${tmp == 'http'}">
					        			<img src="${p.image}" alt="${p.name}" class="img-responsive"/>
					        		</c:when>
					        		<c:otherwise>
					        			<img src="upload/${p.image}" alt="${p.name}" class="img-responsive"/>
					        		</c:otherwise>
					        		</c:choose>
						      <div class="caption">
						        <h3>${p.name}</h3>
						        <div class="text-justify">
				        		<c:set var="descrTruncate" value="${fn:substring(p.description, 0, 250)}" />
				        			<p>
				        				${descrTruncate}...
				        			</p>
				        		</div>
						        <p><a href="product?id=${p.id}" class="btn btn-primary" role="button">See more</a></p>
						      </div>
						    </div>
						 </div>
			        </c:forEach>
               		</div>
               	</div>
               	<div class="container">
               		<div class="row">
               			<div class="col-md-12">
               				<h2>Lastest news</h2>
               				<br />
               				<br />
               				<br />
               			</div>
              			<c:forEach items="${news}" var="n">
              			<div class="col-sm-6 col-md-4">
						    <div class="thumbnail">
						      <c:set var="tmp" value="${fn:substring(n.image, 0, 4)}" />
					        		<c:choose>
					        		<c:when test="${tmp == 'http'}">
					        			<img src="${n.image}" alt="${n.title}" class="img-responsive"/>
					        		</c:when>
					        		<c:otherwise>
					        			<img src="upload/${n.image}" alt="${n.title}" class="img-responsive"/>
					        		</c:otherwise>
					        		</c:choose>
						      <div class="caption">
						        <h3>${n.title}</h3>
						        <p>Published : ${n.date}</p>
						        <div class="text-justify">
				        		<c:set var="descrTruncate" value="${fn:substring(n.article, 0, 250)}" />
				        			<p>
				        				${descrTruncate}...
				        			</p>
				        		</div>
						        <p><a href="newsDetail?id=${n.id}" class="btn btn-primary" role="button">See more</a></p>
						      </div>
						    </div>
						 </div>
			        </c:forEach>
               		</div>
               	</div>
            </div>
 <jsp:include page="/include/footer.jsp" />