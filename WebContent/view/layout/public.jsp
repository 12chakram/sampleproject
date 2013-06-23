<!DOCTYPE html>
<%@ page session="false"%>
<%@include file="/WEB-INF/view/tags.jsp" %>
<html>

<head>
<%@include file="/WEB-INF/view/static.jsp" %>
  <title><d:title/></title>
  <d:head/>
</head>

<body>
<div class ="header-placeholder"></div>
<!--%@include file="/WEB-INF/view/layout/header.jsp" %-->

    <div class="container">
      <div class="row">
         <div class="span12">&nbsp;</div>
      </div>

<div class="row">
 <div class="span10 offset1">
<d:body/>
 </div>
</div>

    </div>

  <p/>
   <!-- <footer class="footer"-->
<!--%@include file="/WEB-INF/view/layout/footer.jsp" %-->
    <!--/footer-->
</body>
</html>
