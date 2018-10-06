<%@include file="/templates/public/inc/header.jsp" %>
<%@include file="/templates/public/inc/menu.jsp" %>
		<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">
				<h2>Organization</h2>
				<div class="box">
					<div class="box_h1">Organizing Committee</div>
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
					<div class="box_h1">Correspondence</div>
					<p>
						If you have any inquiry, you can contact with the general
						secretary via e-mail.<br> Dr. Hiroki Nagai (Kogakuin
						University)<br> 2665-1 Nakano-machi, Hachioji, Tokyo,
						192-0015 Japan
					</p>
					E-mail: <a href="mailto:isat16@sc.kogakuin.ac.jp">isat16@sc.kogakuin.ac.jp</a>
					<div>
						<div></div>
						<div></div>
					</div>
					<p class="p">&nbsp;</p>
				</div>
			</div>
<%@include file="/templates/public/inc/footer.jsp" %>  