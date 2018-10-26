<%@include file="/templates/submission/inc/header.jsp"%>
<%@include file="/templates/public/inc/menu.jsp"%>
			
			
			<% if (request.getParameter("msg") == "success") {%>
				<script type="text/javascript">
					alert("submiss successful");
				</script>
					
			<%}%>
			
			
			<div class="content-right">
				<form action="${pageContext.request.contextPath}/submissions" method="post" id="new_sub" enctype="multipart/form-data">
					<input type="hidden" name="authorCounting" class ="author-counting" value="1">
					<div class="select-track">
						<p>Please select the track relevant for your submission and click "Continue".</p>						
						<c:forEach items="${listFields}" var="field" varStatus="theCount">
							<label class="select-track-container">
								<c:if test="${theCount.index == 0 }">
									<input type="radio" checked="checked" name="field" value="<c:out value="${field.idField}"></c:out>">
								</c:if>
								<c:if test="${theCount.index != 0 }">
									<input type="radio" name="field" value="<c:out value="${field.idField}"></c:out>">
								</c:if>
									<span><c:out value="${field.name}"></c:out></span>
							</label>
						</c:forEach>
						<input class ="btn-select-track" type="button" value="Continue" />
						<!-- end select-track -->
					</div>
					<div class="mark-information">
						<div class="show-direction-author">
							<h3 class="track-selected">
								(AI, Computational Intelligence and Data Analytics)
							</h3>
							<h3>
								Author Information
							</h3>
							<p>
								For each of the authors please fill out the form below. Some items on the form are explained here:
							</p>
							<ul>
								<li><b>Email address </b>
									will only be used for communication with the authors. It will not appear in public Web pages of this conference. The email address can be omitted for authors who are not corresponding. These authors will also have no access to the submission page.
								</li>
								<li><b>Web page </b>
									can be used on the conference Web pages, for example, for making the program. It should be a Web page of the author, not the Web page of her or his organization.
								</li>
								<li>Each author marked as a <b>corresponding author </b>
									will receive email messages from the system about thissubmission. There must be at least one corresponding author.
								</li>
							</ul>
						</div>

						<div class="author-information">
							<div class="author author1">
								<strong>Author 1</strong>
								<div class="author-container">
									First name(+):
									<input type="text" name="firstName1">
								</div>
								<div class="author-container">
									Last name (*):
									<input type="text" name="lastName1" required="required">
								</div>
								<div class="author-container">
									Email (*):
									<input type="text" name="email1" required="required">
								</div>
								<div class="author-container">
									Country (*):
									<input type="text" name="country1" required="required">
								</div>
								<div class="author-container">
									Organization (*):
									<input type="text" name="organization1" required="required">
								</div>
								<div class="author-container">
									Web page:
									<input type="text" name="webpage1">
								</div>
								<div class="author-container corresponding">
									<input type="checkbox" name="corresponding1" value="on">
									<span>corresponding author</span>
								</div>
							</div>
						</div>

						<div class="add-author-information">
							<strong>
								Click here to add more authors.
							</strong>
						</div>

						<div class="show-direction-title-abstract">
							<h3>Title and Abstract</h3>
							<p>The title and the abstract should be entered as plain text, they should not contain HTML elements.</p>
						</div>

						<div class="title-abstract-information">
							<div class="title-abstract-information-container">
								Title(*):
								<input type="text" name="title" required="required">
							</div>
							<div class="title-abstract-information-container">
								Abstract(*):
								<textarea rows="4" name="description" required="required"></textarea>
							</div>
						</div>	<!-- end title-abstract-information -->

						<div class="show-direction-keywords">
							<h3>Keywords</h3>
							<p>Type a list of keywords (also known as key phrases or key terms), <b>one per line</b> to characterize your submission. You should specify at least three keywords.</p>
						</div>	

						<div class="keywords-information">
							<div class="keywords-information-container">
								Keywords(*):
								<textarea rows="4" name="keywords" required="required"></textarea>
							</div>
						</div>	<!-- end keywords-information -->

						<div class="show-direction-uploads">
							<h3>Uploads</h3>
						</div>	
						<div class="uploads-information">
							<div class="uploads-information-container">
								<strong>Paper. </strong>
								<p> Upload your paper. The paper must be in PDF format (file extension .pdf)</p>	
							</div>
							<div class="uploads-information-container">
								<input name="file" type="file" required="required">
							</div>
						</div>	<!-- end uploads-information -->
						<input class ="btn-submit-information" type="submit" value="Submit"/>		
					</div>	<!-- end mark-information -->
				</form>
			</div>

<%@include file="/templates/public/inc/footer.jsp" %>  