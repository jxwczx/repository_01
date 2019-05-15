(function(window, undefined){
    var _$MF = {};
    _$MF.App = function(){
        var _myApp, _views={};
        return {
            init:function(param){
                _myApp = new Framework7({
                    router:false,//关闭默认路由
                    smartSelectInPopup:true,//弹出层方式select
                    onPageInit:function(app,page){
                        //调用每个页面的初始化方法，每个页面初始化方法命名规则  “每个页面的data-page的值（即page.name）+_page_init”
//                        console.info(page);
                        var params = {data:param.data};
                        $MF[page.name+"_page_init"](app,page,params);    
                    }
                });
                
                $MF.App.createView(param.viewSelector,_myApp);
            },
            createView:function(name,app,callback){
                if(_views[name] == null||_views[name] === undefined){
                    if(callback === undefined || callback == null){
                        callback = {
                                dynamicNavbar: true,
                                swipeBackPage:true,
                                domCache:true //所有的page都会缓存在页面中，不会删除
                        };
                    }
                    _views[name] = app.addView(name, callback);
                }
            },
            removeView:function(name){
                delete _views[name];
            },
            getView:function(name){ 
                return _views[name];
            },
            getApp:function(){
                return _myApp;
            }
        }; 
    }();
    
    /**
     * DCHtml 扩展功能
     * 
     * */
    _$MF.extend = function() {//arguments是实参的集合[param1,param2....]
        var copy, name, options = arguments[0] || {} ,target = this ;
        //判断需要扩展的对象是object还是function，不是 则赋对象 {}
        if ( typeof options !== "object" && (!typeof options === "function") ) {
            options = {};
        }
        // 处理 不是null/undefined的参数
        if ( options != null ) {//--浅拷贝
            // 扩展对象
            for ( name in options ) {
                copy = options[ name ];
//                if(target[ name ] != null&&target[ name ] !== undefined){//针对已存在的对象扩展（非覆盖）
//                    for(innername in copy){
//                        target[ name ][innername] = copy[innername];
//                    }
//                }else{
                    // 等于其本身 结束拷贝
                    if ( target === copy ) {
                        continue;
                    }
                    if ( copy !== undefined ) {
                        target[ name ] = copy;
                    }
//                }
            }
        }
        return target;
    };
    
    window.$MF = _$MF;
})(window);