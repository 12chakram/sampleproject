<html>

<%@include file="/WEB-INF/view/static.jsp" %>
  <head>
  <meta name="decorator" content="public" />
    <title>Login</title>
  </head>
  
  <body>

    <div class="row">
       <div class="span12">&nbsp;</div>
    </div>
    <div class="span4 offset3">
	<img src="<%=request.getContextPath()%>/static/img/gridpoint_logo_1_0.gif" alt="GridPoint" />
	<div class="row"></div>
	<div class="row"></div>
	<p></p>
    <form class="well" name='f'  action="login" method="post">

   <legend>Login</legend>
    
    <div class="control-group">
    <label class="control-label" for="j_username"><strong>User</strong></label>
    <div class="controls">
    <input type='text' class="span3" name='j_username' id='j_username' style="height: 30px;"/>
    </div>
    </div>
    
    <div class="control-group">
    <label class="control-label" for="j_password"><strong>Password<strong></label>
    <div class="controls">
    <input type='password' class="span3" name='j_password' id='j_password' style="height: 30px;"/>
    </div>
    </div>

    <div class="control-group">
    <div class="controls">
    <input class="btn btn-large" type="submit" value="Login"/>
    <input class="btn btn-large" type="submit" value="Register" style="margin-left: 90px;"/>
    </div>
    </div>

    </form>
    </div>
 
	<script type="text/javascript">
		document.getElementById('j_username').focus();
	</script>

  </body>
</html>
