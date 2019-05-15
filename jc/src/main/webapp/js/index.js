//$(function() {
//	var basepath = "/jc/template/";
//	var arr_tpl = ["index_navbar_template.html", "index_page_template.html",
//			"index_toolbar_template.html", "detail_navbar_template.html",
//			"login_page_template.html","client_page_template.html"];
//
//	for (var i = 0; i < arr_tpl.length; i++) {
//		$UM.initTemplate(basepath + arr_tpl[i]);
//	}
//	$(".pages_all").append($UM.template.apply("login_page_template"));
//	$MF.App.init({
//		viewSelector : ".view-main",
//		data : null
//	});
//});
//
//$MF.extend({
//	login_page_init : function(app, page, params) {
//		$("body").delegate(".loginBtn","click",function() {
//			$.ajax({
//					url : "toIndex",
//					method : "POST",
//					data : {
//						id : "123"
//					},
//					success : function(data) {
//						console.info(data);
//						if (data == "true") {
//							page.view.router.loadContent($UM.page.initPage("index"));
//						}
//					}
//			});	
//		});
//		
//		$("body").delegate(".toClient","click",function() {
//			$.ajax({
//				url : "client",
//				method : "POST",
//				data : {
//					userId : "66"
//				},
//				success : function(data) {
//					page.view.router.loadContent($UM.page.initPage("client"));
//					$(".client_page").append($UM.template.apply("client_page_template",{userId:data}));
//				}
//			});		
//		});
//	},
//	index_page_init : function(app, page, params) {
//		$(".index_navbar-inner").append($UM.template.apply("index_navbar_template"));
//		$(".index_page").append($UM.template.apply("index_page_template"));
//		$(".index_toolbar-inner").append($UM.template.apply("index_toolbar_template"));
//		
//		$("body").delegate(".toDetailPage","click",function() {
//			page.view.router.loadContent($UM.page.initPage("detail"));
//		});
//		
//		$(".index_navbar-inner").delegate(".index_back", "click", function() {
//			$UM.page.back(page, page.view);
//		});
//		
//	},
//	detail_page_init : function(app, page, params) {
//		$(".detail_navbar-inner").append($UM.template.apply("detail_navbar_template"));
//		
//		$(".detail_navbar-inner").delegate(".detail_back", "click", function() {
//			$UM.page.back(page, page.view);
//		});
//	}
//});
