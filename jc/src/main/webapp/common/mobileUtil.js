(function(window, $, undefined) {
    var _$UM = {};
    _$UM.template = function() {
        var source, compiled, _sourceTemplates = {}, _compiledTemplates = {};
        return {
            set : function(name, template) {
                _sourceTemplates[name] = template;
            },
            get : function(name) {
                if (name !== undefined) {
                    compiled = _compiledTemplates[name];
                    if (!compiled) {
                        source = _sourceTemplates[name];
                        if(source !== undefined){
                            compiled = _.template(source);
//                            compiled = window.Template7.compile(source);
                            
                            _compiledTemplates[name] = compiled;
                        }else{
                            console.error("source Template is not defined !");
                        }
                    }
                    return compiled;
                }
//                return window.Template7.compile('');
                return _.template('');
            },
            apply : function(name, data) {
                return this.get(name)(data);
            }
        };
    }();

    _$UM.initTemplate = function(url) {
        //        $.get(url).fail(function() {
        //          console.log('Failed to load template - ' + url);
        //      }).done(function(response) {
        //            var paths = url.split("/");
        //          var templateName = paths[paths.length - 1].split(".")[0];
        //         _$UM.template.set(templateName,response);
        //      });
        $.ajax({//
            url : url,
            async : false,
            method : "POST",
            success : function(data, status, xhr) {
                var paths = url.split("/");
                var templateName = paths[paths.length - 1].split(".")[0];
                _$UM.template.set(templateName, data);
            }
        });

    };

    _$UM.page = function() { 
        return {
            initPage : function(pagename) {//初始化新page navbar的框架元素
                var html = '';
                html += this.createNavbar(pagename);
                html += this.createPage(pagename);
                return html;
            },
            back : function(page,view){
                var navbar = page.fromPage.navbarInnerContainer;
                if($(navbar).hasClass("navbar-on-left")){
                    $(navbar).removeClass("navbar-on-left")
                };
                if($(navbar).hasClass("cached")){
                    $(navbar).removeClass("cached")
                };
                view.router.back({url:page.fromPage.url});
            },
            createPopup : function(name){
                var html = '';
                html += '<div class="popup '+name+'_popup">';
                html += this.createView(name);
                html += '</div>';
                
                return html;
            },
            createView : function(name){
                var html = '';
                html += '<div class="view '+name+'_view">';
                html += '<div class="navbar '+name+'_navbar">';
                html += this.createNavbar(name);
                html += '</div>';
                html += '<div class="pages">';
                html += this.createPage(name);
                html += '</div>';
                html += '</div>';
                return html;
            },
            createPage : function(name){
                var html = '';
                html += '<div class="page '+name+'_page" data-page="'+name+'"></div>';
                return html;
            },
            createNavbar : function(name){
                var html = '';
                html += '<div class="navbar-inner '+name+'_navbar-inner"></div>';
                return html;
            },
            //头部标题-若内容较少，建议使用该方式；否则，建议使用html模板方式加载
            navbarTemplateByType : function(type,param){
                var html = '';
                if(type == "back"){
                    html += '<a href="#" class="back"><i class="icon f7-icons">chevron_left</i></a>';
                    html += '<span class="toptitle_solution_detail_isdt">'+param.title+'</span>';
                }else if(type == "close-popup"){
                    html += '<a href="#" class="close-popup"><i class="icon f7-icons">chevron_left</i></a>';
                    html += '<span class="toptitle_solution_detail_isdt"> '+param.title+'</span>';
                }
                
                return html;
            },
            createPopover : function(param){
                var html = '';
                html+='<div class="popover popover-menu-top" style="width: 120px;">'
                html+='<div class="popover-angle"></div>'    
                html+='<div class="popover-inner">'    
                html+='<div class="list-block">'        
                html+=    '<ul style="background: white;">'            
                html+=        '<li><a href="#"class="list-button item-link ">我的方案</a></li>'                
                html+=        '<li><a href="#" class="list-button item-link ">数据管理</a></li>'                
                html+=        '<li><a href="#" class="list-button item-link ">数据维护</a></li>'                
                html+=        '<li><a href="#" class="list-button item-link ">退出</a></li>'                
                html+=    '</ul>'            
                html+='</div>'        
                html+='</div>'    
                html+='</div>'    
                return html;
            },
            initContent : function(page,template,data){
                var top_navbar = "."+page.name+"_navbar-inner";
                if(data.navbarData.type !== undefined){//非模板方式
                    $(page.container).parents(".view").find(top_navbar).html($UM.page.navbarTemplateByType(data.navbarData.type,data.navbarData.params));
                }else{
                    $(page.container).parents(".view").find(top_navbar).html($UM.template.apply(template.navbarTpl,data.navbarData.params));
                }
                $(page.container).html($UM.template.apply(template.pageTpl,data.pageData));
            }
        };
    }();

    window.$UM = _$UM;
})(window, $);
