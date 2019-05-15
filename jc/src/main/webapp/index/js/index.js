$(function() {
	var basepath = "/jc/index/template/";
	var arr_tpl = [ "index_page_template.html"];
	for (var i = 0; i < arr_tpl.length; i++) {
		$UM.initTemplate(basepath + arr_tpl[i]);
	}
	$(".pages_all").append($UM.template.apply("index_page_template"));
	$MF.App.init({
		viewSelector : ".view-main"
	});
	
	//audio 表示音频，把这个内容添加到页面body中
	$('<audio id="chatAudio"><source src="/jc/index/img/niao.mp3" type="audio/mpeg"></audio>').appendTo('body');
	
});

$MF.extend({
	/**
	 * 首页 -登陆页面
	 * 
	 * */
	index_page_init : function(app, page, params) {
		$(".navbar_all").hide();
		$(".toolbar_all").hide();
		
		$("#userLogin").click(function(){
			var par = $(this).parents(".centent_login");
			$.ajax({
				url : "/jc/index/userLogin",
				method : "POST",
				data : {
					loginName:$(par).find("input[name=loginName]").val(),
					password:$(par).find("input[name=password]").val()
				},
				success : function(data) {
					var rtn = JSON.parse(data);
					if(rtn.flag){
						
						$(".span_error").hide();
						$(".navbar_all").show();
						$(".toolbar_all").show();
						var obj = JSON.parse(rtn.result);
						var base = "/jc/index/template/";
						var arr = [ "chat_page_template.html","chat_navbar_template.html","chat_toolbar_template.html"];
						for (var i = 0; i < arr.length; i++) {
							$UM.initTemplate(base + arr[i]);
						}
						var html = '';
						html += $UM.template.apply("chat_page_template",{userId:obj.userId,loginName:obj.loginName});
						html += '<div class="navbar-inner chat_navbar-inner">';
						html += $UM.template.apply("chat_navbar_template");
						html +=	'</div>';
						page.view.router.loadContent(html);
						$(".toolbar_all").append($UM.template.apply("chat_toolbar_template"));
						
					}else{
						$(".span_error").html(rtn.result);
						$(".span_error").show();
					}
				}
			});		
		});
	},
	
	/**
	 * 人员列表
	 * 
	 * */
	userList_page_init : function(app, page, params){
		
	},
	
	/**
	 * 聊天页面
	 * */
	chat_page_init : function(app, page, params) {
		var websocket;
		if ('WebSocket' in window) {
			console.log("此浏览器支持websocket");
//			websocket = new WebSocket("ws://39.105.176.157:80/jc/webServer/"+$("#userId").val()+"/"+$("#loginName").val());
			websocket = new WebSocket("ws://localhost:8081/jc/webServer/"+$("#userId").val()+"/"+$("#loginName").val());		
		} else if ('MozWebSocket' in window) {
			console.log("此浏览器只支持MozWebSocket");
		} else {
			console.log("此浏览器只支持SockJS");
		}
		websocket.onopen = function(evnt) {
			console.log(evnt);
			console.log("链接服务器成功!")
		};
		websocket.onmessage = function(evnt) {
			var obj = JSON.parse(evnt.data);
			var html = '';
			html += '<div class="message message-received message-last">';
			html += '<div class="message-avatar" style="background-image: url(&quot;https://loremflickr.com/70/70/people?lock=9&quot;);"></div>';
			html += '<div class="message-content msg-rece">';
			html += '<div class="message-name">'+obj.sendUserName+'</div>';
			html += '<div class="message-bubble">';
			html += '<div class="message-text">'+obj.message+'</div>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			$("#msg").append(html);
			$('#chatAudio')[0].play();//'#chatAudio'在这个区域里的第一个资源文件
		};
		websocket.onerror = function(evnt) {
			
		};
		websocket.onclose = function(evnt) {
			console.log("与服务器断开了链接!");
		}

		/* ? $('#close').bind('click', function() {
		     websocket.close();
		 }); */

		function send() {
			var html = '';
			html += '<div class="message message-sent message-last" >'
			html += '<div class="message-content">'
			html += '<div class="message-bubble">'
			html += '<div class="message-text">'
			html += $("#message").val();
			html += '</div>'
			html += '</div>'
			html += '</div>'
			html += '</div>'
			$("#msg").append(html);
			if (websocket != null) {
				var message = document.getElementById('message').value;
				websocket.send(message);
			} else {
				console.log('未与服务器链接.');
			}
		}
		$("body").delegate("#send","click",function() {
			if($("#message").val()!=""){
				send();
				$("#message").val("");
			}
		});
	}
});
