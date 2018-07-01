<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>Project Manager</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	    <div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="#" class="navbar-brand">Project Manager</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/logout">Logout</a></li>
					<li><a href="/show-my-projects">My Projects</a></li>
					<li><a href="/show-guest-projects">Guest Projects</a></li>
					<li><a href="/new-project">New Project</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<c:choose>
	<c:when test="${mode=='MODE_NEW_PROJECT' }">
			<div class="container text-center">
				<h3>New Project</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-project">
					<input type="hidden" name="id" value="${project.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${project.name }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Description</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${project.description }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Deadline</label>
						<div class="col-md-7">
							<input type="date" class="form-control" name="deadline"
								value="${project.deadline }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Create" />
					</div>
				</form>
			</div>
		</c:when>
		
		<c:when test="${mode=='MODE_EDIT_PROJECT' }">
			<div class="container text-center">
				<h3>Edit Project</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-project">
					<input type="hidden" name="id" value="${project.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${project.name }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Description</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${project.description }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Deadline</label>
						<div class="col-md-7">
							<input type="date" class="form-control" name="deadline"
								value="${project.deadline }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Edit" />
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_CONTRIBUTORS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>Contributors in ${project.name}</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="add-contributor">
				<input type="hidden" name="id" value="${project.id }" />
				<c:if test="${ not empty error}">
					<div class="alert alert-danger">
						<c:out value ="${ error}"></c:out>
					</div>
				</c:if>
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Add" />
					</div>
				</form>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Username</th>
								<th>First Name</th>
								<th>Second Name</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users }">
								<tr>
									<td>${user.user.username}</td>
									<td>${user.user.firstname}</td>
									<td>${user.user.secondname}</td>
									<td><a href="/delete-contributor?id=${user.id }&id_proj=${project.id}"><span
											class="glyphicon glyphicon-trash"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_MY_PROJECTS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>My Projects</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Deadline</th>
								<th>Delete</th>
								<th>Edit</th>
								<th>Contributors</th>
								<th>Tasks</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${projects }">
								<tr>
									<td>${project.name}</td>
									<td>${project.description}</td>
									<td>${project.deadline}</td>
									<td><a href="/delete-project?id=${project.id }"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="/edit-project?id=${project.id }"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a href="/contributors?id=${project.id }"><span
											class="glyphicon glyphicon-user"></span></a></td>
									<td><a href="/show-tasks?id=${project.id }"><span
											class="glyphicon glyphicon-list-alt"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_GUEST_PROJECTS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>Guest Projects</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Deadline</th>	
								<th>Tasks</th>	
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${projects }">
								<tr>
									<td>${project.project.name}</td>
									<td>${project.project.description}</td>
									<td>${project.project.deadline}</td>
									<td><a href="/show-tasks?id=${project.id }"><span
											class="glyphicon glyphicon-list-alt"></span></a></td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_SHOW_MY_TASKS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>Tasks in ${project.name}</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="/new-task?id=${project.id }">
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="New Task" />
					</div>
				</form>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Status</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="task" items="${tasks }">
								<tr>
									<td>${task.name}</td>
									<td>${task.description}</td>
									 <td><input class="form-check-input" type="checkbox" name="status" checked="${task.status }" disabled>
									</td>
									<td><a href="/delete-task?id=${task.id }&id_proj=${project.id}"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="/edit-task?id=${task.id }&id_proj=${project.id}"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_NEW_TASK' }">
			<div class="container text-center">
				<h3>New Task</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-task">
					<input type="hidden" name="idP" value="${project.id }" />
					<input type="hidden" name="id" value="${task.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${task.name }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Description</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${task.description }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Create" />
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode=='MODE_EDIT_TASK' }">
			<div class="container text-center">
				<h3>Edit Task</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-task">
					<input type="hidden" name="idP" value="${project.id }" />
					<input type="hidden" name="id" value="${task.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Name</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${task.name }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Description</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${task.description }" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Status</label>
						<div class="col-md-7">
							<input class="form-check-input" type="checkbox" name="status">
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>
		</c:choose>
<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>