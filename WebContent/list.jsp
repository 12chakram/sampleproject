<!DOCTYPE html>

<%@include file="/WEB-INF/view/static.jsp" %>
<html>
	<head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8">
	  <meta name="decorator" content="app" />

	  <title>Alarm Library</title>
	</head>
	<body>
	<h1></h1>
		
	<div class="row">
		<div class="span12">
			<div class="btn-toolbar">
				<div class="btn-group">
				<a href="" class="btn btn-primary">
					<i class="icon-list"></i><br/>
					<span><strong>My Alarms</strong></span>
				</a>
				<a href="" class="btn btn-primary">
					<i class="icon-list-alt"></i><br/>
					<span><strong>All Alarms</strong></span>
				</a>
				</div>
				
				<div class="btn-group pull-right">				
				<a class="btn btn-link" href="#" class="btn btn-primary">
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
		<h3>Alarm Library <small>${listName}</small>
			<strong class="pull-right">
				<a class="btn btn-primary" href=""><i class="icon-plus icon-white"></i> New Definition</a>
			</strong>
		</h3>
	</header>


	  <table  cellpadding="0" cellspacing="0" border="0" id="addList" class="table table-striped table-bordered table-condensed">
		<thead>
		  <tr>
			<th><span class="underline">Name</span></th>
			<th><span class="underline">Status</span></th>
			<th><span class="underline">Creator</span></th>
			<th><span class="underline">Last Edited By</span></th>
			<th><span class="underline">Last Update</span></th>
			<th>Actions</th>
		  </tr>
		</thead>
					
		<tbody>
		 
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