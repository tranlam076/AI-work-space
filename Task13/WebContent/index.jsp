<%@include file="/templates/public/inc/header.jsp" %>
<%@include file="/templates/public/inc/menu.jsp" %>
		<div class="content-right">
				<!-- <h2>Organization</h2> -->
				<div class="box">
					<div class="box_h1">Information And Notification</div>
					<c:forEach items="${listCats}" var="cat">
						<h5>
							<c:out value="${cat.name}"></c:out>
						</h5>
						<c:forEach items="${listNews}" var="news">
							<c:if test="${news.id_cat == cat.id_cat}">
								<dl>
									<dt>
										<c:out value="${news.title}"></c:out>
									</dt>
									<dd>
										<c:out value="${news.content}"></c:out>
									</dd>
								</dl>
							</c:if>
						</c:forEach>
					</c:forEach>
				</div>

				<div class="box">
					<div class="box_h1">About us</div>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laborum nobis, dolor asperiores
						consequuntur!Tempora eaque rerum sint pariatur cum dolorum aut placeat illo voluptas 
						assumenda nihil recusandae, libero provident ipsam.
					</p>
					
					<div>
						<div></div>
						<div></div>
					</div>
					<p class="p">&nbsp;</p>
				</div>
			</div>
<%@include file="/templates/public/inc/footer.jsp" %>  