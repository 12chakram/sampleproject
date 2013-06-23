<%@page import="com.eng.gp.project.domain.ProjectTrackingItem"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>

<link rel="stylesheet" href="jquery-ui.css" />
<link rel="stylesheet" href="bootstrap.css" />

<script src="jquery-ui-1.9.2.custom.min.js"></script>
<script src="jquery-ui.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/static.jsp" %>
<html>
	<head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8">
	  <meta name="decorator" content="app" />
		<title>Alarm Library</title>
	<script language="javascript">
		function hasOptions(obj) {
			if (obj != null && obj.options != null) {
				return true;
			}
			return false;
		}
	
		function moveSelectedOptions(from, to) {
			if (!hasOptions(from)) {
				return;
			}
			for ( var i = 0; i < from.options.length; i++) {
				var o = from.options[i];
				if (o.selected) {
					if (!hasOptions(to)) {
						var index = 0;
					} else {
						var index = to.options.length;
					}
					to.options[index] = new Option(o.text, o.value);
				}
			}
			for ( var i = (from.options.length - 1); i >= 0; i--) {
				var o = from.options[i];
				if (o.selected) {
					from.options[i] = null;
				}
			}
			from.selectedIndex = -1;
			to.selectedIndex = -1;
		}
	
		function moveAllSelectedOptions(from, to) {
			if (!hasOptions(from)) {
				return;
			}
			for ( var i = 0; i < from.options.length; i++) {
				var o = from.options[i];
	
				if (!hasOptions(to)) {
					var index = 0;
				} else {
					var index = to.options.length;
				}
				to.options[index] = new Option(o.text, o.value);
	
			}
			for ( var i = (from.options.length - 1); i >= 0; i--) {
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
			$("#dialog-form").dialog({
				autoOpen : false,
				height : 500,
				width : 1000,
			});
	
			$("#create-user").button().click(function() {
				$("#dialog-form").dialog("open");
			});
	
			$("#cancelbu").click(function() {
				$("#dialog-form").dialog("close");
			});
	
			$("#datepicker").datepicker({
				showOn : "button",
				buttonImage : "dateicon.png",
				buttonImageOnly : true,
				minDate : 0
			});
	
			$("#tdatepicker").datepicker({
				showOn : "button",
				buttonImage : "dateicon.png",
				buttonImageOnly : true,
				minDate : 0,
	
			});
	
		});
	
		$("#datepicker").datepicker({
			showOn : "button",
			buttonImage : "dateicon.png",
			buttonImageOnly : true,
			minDate : 0,
	
		});
	</script>
</head>
	<body>
	<h1></h1>
		
	<div class="row">
		<div class="span12">
			<div class="btn-toolbar">
				<div class="btn-group pull-right">				
				<a class="btn btn-link" href="removeAllProjects?action=deleteProjects" class="btn btn-primary">
					<span><strong>Delete Alarms</strong></span>
				</a>
				<a class="btn btn-link" href="#" class="btn btn-primary">
					<span><strong>Import Alarms</strong></span>
				</a>
				<a class="btn btn-link" href="" >
					<span><strong>Export Alarms</strong></span>
				</a>
				</div>
			</div>
		</div>
	</div>

	<header class="alert-info">
		<h3>All Projects <small>${listName}</small>
			<strong class="pull-right">
				<button id="create-user" class="btn btn-primary"><i class="icon-plus icon-white"></i>Createnewproject</button>
			</strong>
		</h3>
	</header>


	  <table  cellpadding="0" cellspacing="0" border="0" id="addList" class="table table-striped table-bordered table-condensed">
		<thead>
		  <tr>
			<th><span class="underline">ProjectId</span></th>
			<th><span class="underline">ProjectName</span></th>
			<th><span class="underline">ProjectType</span></th>
			<th><span class="underline">PremisesId</span></th>
			<th><span class="underline">StartDate</span></th>
			<th><span class="underline">EndDate</span></th>
			<th><span class="underline">Channels</span></th>
			<th><span class="underline">Status</span></th>
			<th>Actions</th>
		  </tr>
		</thead>
		 <tbody>
		 	 <c:forEach var="project" items="${allProjects}"> 
                 <tr style="background-color: ;">	
					<td>${project.projectId}</td>
                  	<td>${project.projectName}</td>
                  	<td>${project.projectTypeId}</td>
                  	<td>${project.premisesId}</td>
                  	<td>${project.startDate}</td>
                  	<td>${project.endDate}</td>
                  	<td>${project.channels}</td>
                  	<td>${project.projectStatus}</td>
                  	<td>
                  		<a class="btn btn-link" href="deleteProjectByProjectId?projectId=${project.projectId}" class="btn btn-primary">
                  			<span><strong>Delete Alarms</strong></span>
                  		</a>
                  	</td>
                  </tr>
                </c:forEach>
		</tbody>
		</table>
		<br>
		<!-- Modal -->
		<div id="actionModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="modalLabel">default</h3>
			</div>
			<div class="modal-body">
				<p class="alerting-msg">default</p>
			</div>
			<div class="modal-footer">	
				<a href="${deleteUrl}" class="btn btn-primary" id="action-submit">
					<span><strong>Default</strong></span>
				</a>
				<button class="btn" data-dismiss="modal" aria-hidden="true"><span><strong>Close</strong></span></button>	
			</div>
		</div>
		
	<div id="dialog-form" title="Create new Project">
		<%-- <jsp:useBean id="projectstypes" class="com.eng.gp.project.data.ProjectTypes" scope="page"/>  --%>
		<form name='f' action="createProjectnew" method="post">
			Name the Project:<input type="text" name="projectname" id="name"
				class="text ui-widget-content ui-corner-all" required="required" />

			Select Project type:<select name="projecttype"
				class="text ui-widget-content ui-corner-all" required="required">
				<option value=""><strong>Select a ProjectType:</strong></option>
				<c:forEach var="projecttypes" items="${allProjectstypes}">
                   	<option value=${projecttypes.projectTypeId}>${projecttypes.projectType}</option>
                </c:forEach>
			</select> <br> <br> Start Date: <input type="text" id="datepicker"
				name="start" /> End Date: <input type="text" id="tdatepicker"
				name="end" /> <br> <br> <br>

			<table width="30%">
				<tr>
					<td align="right" valign="top" width="50%"><select
						name="attributes" property="attributes" multiple="true" size="4"
						style="width: 180px; height: 90px;"
						onDblClick="moveSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])">

							<%-- <c:forEach items="$(myDataList)" var="data">
								<tr>
									<td>${data.projectId}</td>
									<td>${data.ProjectName}</td>
								</tr>
							</c:forEach> --%>
							<option value="mainload">Main:Main Load</option>
							<option value="hvac">HVAC:RTU1</option>
							<option value="hvac">HVAC:RTU2</option>
							<option value="lighting">Lighting:Sales Light</option>

					</select></td>
					<td align="center" valign="top" width="50px;"><br> <input
						type="button" name="right" value="Add"
						style="margin-left: 33px; height: 25px; width: 45px; margin-top: -27px;"
						onclick="moveSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])"><br>
						<br> <input type="button" name="left" value="Remove"
						style="margin-left: 100px; height: 25px; width: 70px; margin-top: -35px;"
						onclick="moveSelectedOptions(this.form['selectedAttributes'],this.form['attributes'])">

						<input type="image" name="right" value="Add all channels"
						style="margin-left: 50px; height: 1px; width: 5px; margin-top: -43px; color: #0088cc; cursor: pointer; order-color: transparent;"
						onclick="moveAllSelectedOptions(this.form['attributes'],this.form['selectedAttributes'])"><br>
					<td align="left" valign="top" width="40%"><select
						name="selectedAttributes" multiple="true" size="4"
						style="width: 180px; margin-left: 100px; height: 90px;"
						onDblClick="moveSelectedOptions(this.form['selectedAttributes'],this.form['attributes'])">
					</select></td>
				</tr>
				<tr>
					<td width="33%"></td>
					<td width="33%"></td>
				</tr>
			</table>
				<input type="hidden" name="listInvstName" value="" id="hiddenFormFieldName"/>
				<br> <br> <input type="submit" value="save" onclick="setLenderValues(this.form['selectedAttributes']);" /> <input id="cancelbu" type="button" value="Cancle" />
		</form>
</div>	


		
	
	<script type="text/javascript">
		$(document).ready(function() {
			var orgUrl;
			
		    $('#addList tr').each(function(){
		   
			$(".delete-action").unbind('click').click(function () {
				orgUrl = $(this).attr("href");
				$("#action-submit").html("<span><strong>Delete</strong></span>");
				//alert($(".delete-action").attr("href"));
				$(this).attr("href","#actionModal");
				$(".alerting-msg").text("Do you want to delete alarm definition:  "+ $(this).closest('td').siblings('td.alarm-name').text());
				$("#modalLabel").text("Delete Alarm Definition");
			});
			
			$(".publish-action").unbind('click').click(function () {
				orgUrl = $(this).attr("href");
				$("#action-submit").html("<span><strong>Lock</strong></span>");
				$(this).attr("href","#actionModal");
				$(".alerting-msg").text("Do you want to lock alarm definition:  "+ $(this).closest('td').siblings('td.alarm-name').text());
				$("#modalLabel").text("Lock Alarm Definition");
			});
				
			
			$("#action-submit").click(function () {
				$(this).attr("href",orgUrl);
			});
			
		});
		   
		   
		 $(".alarm-name-url").popover({ trigger: "hover" });
		 
		 $.fn.dataTableExt.afnSortData['dom-text'] = function  ( oSettings, iColumn )
		{
			var aData = [];
			$( 'td:eq('+iColumn+') a', oSettings.oApi._fnGetTrNodes(oSettings) ).each( function () {
				aData.push( this.text );
			} );
			return aData;
		}
		//if IE
		var alarmTable;
		if( $.browser.msie){
			 alarmTable = $('#addList').dataTable( {
			//"sDom": "<'row'<'span6'T><'span6'f>r>t<'row'<'span3'l><'span3'i><'span6'p>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
			  "sLengthMenu": "_MENU_ records per page"
			},
			"aoColumnDefs": [
			{ 'bSortable': false, 'aTargets': [ 5 ] } 
		    ],
			
		    "aoColumns": [
				 {"sWidth": "300px"}, 
				 null, null, null, null, null 
			], 
			"iDisplayLength": 100
		  } );
		}
		else{ 
		    alarmTable = $('#addList').dataTable( {
			//"sDom": "<'row'<'span6'T><'span6'f>r>t<'row'<'span3'l><'span3'i><'span6'p>>",
			"sPaginationType": "bootstrap",
			"oLanguage": {
			  "sLengthMenu": "_MENU_ records per page"
			},
			"aoColumnDefs": [
			{ 'bSortable': false, 'aTargets': [ 5 ] } 
		    ],
			
		    "aoColumns": [
				 {"sWidth": "300px", "sSortDataType": "dom-text" }, 
				 null, null, null, null, null 
			], 
			/*"oTableTools": {
			  "aButtons": ["copy", "print", "csv",  "pdf"],
			  "sSwfPath": "<%=request.getContextPath()%>/static/datatables/tabletools/swf/copy_csv_xls_pdf.swf"
			},*/
			"iDisplayLength": 100
		  } );
		}
		  /* Apply the tooltips */
		$( alarmTable.fnGetNodes() ).tooltip( {
				"delay": 0,
				"track": true,
				"fade": 250
		} );
		   
		} );

		//straight from examples on site
		$.extend( true, $.fn.DataTable.TableTools.classes, {
		  "container": "btn-group",
		  "buttons": {
			"normal": "btn",
			"disabled": "btn disabled"
		  },
		  "collection": {
			"container": "DTTT_dropdown dropdown-menu",
			"buttons": {
			  "normal": "",
			  "disabled": "disabled"
			}
		  }
		} );

		// Have the collection use a bootstrap compatible dropdown
		$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
		  "collection": {
			"container": "ul",
			"button": "li",
			"liner": "a"
		  }
		} );
	
		
	</script>	
	</body>
</html>