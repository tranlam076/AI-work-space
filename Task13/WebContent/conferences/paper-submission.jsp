<%@include file="/templates/conference/inc/header.jsp"%>
<%@include file="/templates/public/inc/menu.jsp"%>

			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 content-right">
				<form action="submission_new_z.cgi" method="post" id="new_sub" enctype="multipart/form-data">
					<div class="select-track">
						<p>Please select the track relevant for your submission and click "Continue".</p>
						<label class="select-track-container">
							<input type="radio" checked="checked" name="radio">
							<span>AI, Computational Intelligence and Data Analytics</span>
						</label>
						<label class="select-track-container">
							<input type="radio" name="radio">
							<span>Communications and Networking</span>
						</label>
						<label class="select-track-container">
							<input type="radio" name="radio">
							<span>Software Engineering and Information Systems</span>
						</label>
						<label class="select-track-container">
						<input type="radio" name="radio">
							<span>Image, Language, and Speech Processing</span>
						</label>
						<label class="select-track-container">
							<input type="radio" name="radio">
							<span>Computational Science</span>
						</label>
						<input class ="btn-select-track" type="button" value="Continue" />
					</div>	<!-- end select-track -->
			
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
									<input type="text" name="firstname1">
								</div>
								<div class="author-container">
									Last name (*):
									<input type="text" name="lastname1">
								</div>
								<div class="author-container">
									Email (*):
									<input type="text" name="email1">
								</div>
								<div class="author-container">
									Country (*):
									<input type="text" name="country1">
								</div>
								<div class="author-container">
									Organization (*):
									<input type="text" name="organization1">
								</div>
								<div class="author-container">
									Web page:
									<input type="text" name="webpage1">
								</div>
								<div class="author-container">
									<input type="checkbox" name="corresponding1" value="on">
									<span>corresponding author</span>
								</div>
							</div>

							<div class="author">
								<strong>Author 2</strong>
								<div class="author-container">
									First name(+):
									<input type="text" name="firstname2">
								</div>
								<div class="author-container">
									Last name (*):
									<input type="text" name="lastname2">
								</div>
								<div class="author-container">
									Email (*):
									<input type="text" name="email2">
								</div>
								<div class="author-container">
									Country (*):
									<input type="text" name="country2">
								</div>
								<div class="author-container">
									Organization (*):
									<input type="text" name="organization2">
								</div>
								<div class="author-container">
									Web page:
									<input type="text" name="webpage2">
								</div>
								<div class="author-container">
									<input type="checkbox" name="corresponding2" value="on">
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
								<input type="text" name="title">
							</div>
							<div class="title-abstract-information-container">
								Abstract(*):
								<textarea rows="4" name="abstract"></textarea>
							</div>
						</div>	<!-- end title-abstract-information -->

						<div class="show-direction-keywords">
							<h3>Keywords</h3>
							<p>Type a list of keywords (also known as key phrases or key terms), <b>one per line</b> to characterize your submission. You should specify at least three keywords.</p>
						</div>	

						<div class="keywords-information">
							<div class="keywords-information-container">
								Keywords(*):
								<textarea rows="4" name="keywords"></textarea>
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
								<input name="uploads" type="file">
							</div>
						</div>	<!-- end uploads-information -->
						<input class ="btn-submit-information" type="submit" value="Submit"/>		
					</div>	<!-- end mark-information -->
				</form>
			</div>
		</div>

<%@include file="/templates/public/inc/footer.jsp" %>  