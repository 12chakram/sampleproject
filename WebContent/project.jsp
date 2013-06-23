<%@page import="com.eng.gp.project.domain.ProjectType"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="app" />
<title>Alarm Library</title>

<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>



<link rel="stylesheet" href="jquery-ui.css" />
<link rel="stylesheet" href="bootstrap.css" />

<script src="jquery-ui-1.9.2.custom.min.js"></script>
<script src="jquery-ui.js"></script>

<script language="javascript">

function hasOptions(obj) {
       if(obj!=null && obj.options!=null) {
              return true;
       }
       return false;
}

       function moveSelectedOptions(from,to) {
              if(!hasOptions(from)) {
                     return;
              } 
              for(var i=0;i<from.options.length;i++) {
                     var o = from.options[i];
                     if(o.selected) {
                           if(!hasOptions(to)){
                                  var index = 0;
                           } else {
                                  var index=to.options.length;
                           }
                           to.options[index] = new Option( o.text, o.value);
                     }
              }
              for(var i=(from.options.length-1);i>=0;i--) {
                     var o = from.options[i];
                     if(o.selected) {
                           from.options[i] = null;
                     }
              }
              from.selectedIndex = -1;
              to.selectedIndex = -1;
       }

       function moveAllSelectedOptions(from,to) {
           if(!hasOptions(from)) {
                  return;
           } 
           for(var i=0;i<from.options.length;i++) {
                  var o = from.options[i];
                  
                        if(!hasOptions(to)){
                               var index = 0;
                        } else {
                               var index=to.options.length;
                        }
                        to.options[index] = new Option( o.text, o.value);
                  
           }
           for(var i=(from.options.length-1);i>=0;i--) {
                  var o = from.options[i];
                  
                        from.options[i] = null;
                  
           }
           from.selectedIndex = -1;
           to.selectedIndex = -1;
    }

	 function setLenderValues(from) {
    	  if(!hasOptions(from)) {
               return;
        } 
    	  var channels = "";
      	for(var i=0;i<from.options.length;i++) {
                variables = from.options[i];
               channels += variables.value + ",";
             	alert(channels);
        }
      	if(channels.length>0){
      	document.getElementById("hiddenFormFieldName").value=channels.toString();
      	}
      	else{
            alert('Please select atleast one Channel');
            return false;
           }
      }
</script>

<script>
$(function() {
$( "#dialog-form" ).dialog({
autoOpen: false,
 height: 500,
 width: 1000,
 });

 $( "#create-user" )
.button()
.click(function() {
$( "#dialog-form" ).dialog( "open" );
});

$("#cancelbu").click(function() {
	 $( "#dialog-form" ).dialog( "close" );
	 });



 
 $("#datepicker" ).datepicker({
showOn: "button",
buttonImage: "dateicon.png",
buttonImageOnly: true,
minDate: 0
});


 $( "#tdatepicker" ).datepicker({
	 showOn: "button",
	 buttonImage: "dateicon.png",
	 buttonImageOnly: true,
	 minDate: 0,
	 
	 });

 
});


$("#datepicker" ).datepicker({
	showOn: "button",
	buttonImage: "dateicon.png",
	buttonImageOnly: true,
	minDate: 0,
	
	});

</script>
</head>
<body>
	<button id="create-user" style="margin-top: 10px; margin-left: 966px;">Createnewproject</button>
	<div id="dialog-form" title="Create new Project">
		<%-- <jsp:useBean id="projectstypes" class="com.eng.gp.project.data.ProjectTypes" scope="page"/>  --%>
		<form name='projectrackform' action="createProjectnew" method="post">
			Name the Project:<input type="text" name="projectname" id="name"
				class="text ui-widget-content ui-corner-all" required="required" />

			Select Project type:<select name="projecttype" class="text ui-widget-content ui-corner-all" required="required">
				<option value=""><strong>Select a ProjectType:</strong></option>
				<c:forEach var="projecttypes" items="${allProjectstypes}">
                   	<option value=${projecttypes.projectTypeId}>${projecttypes.projectType}</option>
                </c:forEach>
			 </select><br> <br>
		 Start Date: <input type="text" id="datepicker" name="start" /> 
		 End Date: <input type="text" id="tdatepicker" name="end" /> 
		 <br> <br> <br>
		<table width="30%">
				<tr>
				<td align="right" valign="top" width="50%">
					<select name="attributes" property="attributes" multiple="true" size="4" style="width: 180px; height: 90px;"
						onDblClick="moveSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])">
							<option value="mainload">Main:Main Load</option>
							<option value="hvac">HVAC:RTU1</option>
							<option value="hvac">HVAC:RTU2</option>
							<option value="lighting">Lighting:Sales Light</option>
					</select>
				</td>
					<td align="center" valign="top" width="50px;"><br> 
					<input type="button" name="right" value="Add" style="margin-left: 33px; height: 25px; width: 45px; margin-top: -27px;"
						onclick="moveSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])"><br>
						<br>
					 <input type="button" name="left" value="Remove" style="margin-left: 100px; height: 25px; width: 70px; margin-top: -35px;"
						onclick="moveSelectedOptions(this.form['selectedAttributes'],this.form['attributes'])">
					<input type="image" name="right" value="Add all channels" style="margin-left: 50px; height: 1px; width: 5px; margin-top: -43px; color: #0088cc; cursor: pointer; order-color: transparent;"
						onclick="moveAllSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])"><br>
					<td align="left" valign="top" width="40%">
					<select name="selectedAttributes"  id="selectedAttributesId" multiple="true" size="4" style="width: 180px; margin-left: 100px; height: 90px;"
						onDblClick="moveSelectedOptions(this.form['selectedAttributes'],this.form['attributes'])">
					</select>
				</td>
				</tr>
				<tr>
					<td width="33%"></td>
					<td width="33%"></td>
				</tr>
			</table>
			   <input type="hidden" name="listInvstName" value="" id="hiddenFormFieldName"/>
				<br> <br> <input type="submit" value="save" onclick="setLenderValues(this.form['selectedAttributes']); "/> <input id="cancelbu" type="button" value="Cancle"/>
		</form>
</div>
</body>
</html>