/*CoreUtil*/
/*工具类，类似java静态工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
    /*ajax请求*/
    coreUtil.sendAjax = function (url, params, ft, method, headers, async, contentType) {
        debugger
        var roleSaveLoading = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        layui.jquery.ajax({
            url: url,
            cache: false,
            async: async === undefined ? true : async,
            data: params,
            type: method === undefined ? "POST" : method,
            contentType: contentType === undefined ? 'application/json; charset=UTF-8': contentType
            ,
            dataType: "json",
            beforeSend:function (request){
                if(headers===undefined){

                } else if(headers){
                    request.setRequestHeader("authorization",CoreUtil.getData("access_token"));
                    request.setRequestHeader("refresh_token",CoreUtil.getData("refresh_token"));
                } else {
                    request.setRequestHeader("authorization",CoreUtil.getData("access_token"));
                }
            },
            success: function (res) {
                if(res.code==4010001){
                    top.window.location.href="/index/login";
                }
                top.layer.close(roleSaveLoading);
                if (typeof ft == "function") {
                    if(res.code==0) {
                        if(ft!=null&&ft!=undefined){
                            ft(res);
                        }
                    } else {
                        layer.msg(res.msg)
                    }
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                debugger
                top.layer.close(roleSaveLoading);
                if(XMLHttpRequest.status===404){
                    top.window.location.href="/index/404";
                }else{
                    layer.msg("服务器好像除了点问题！请稍后试试");
                }
            }
        });
    };
    /*存入本地缓存*/
    coreUtil.setData = function(key, value){
        layui.sessionData('LocalData',{
            key :key,
            value: value
        })
    };
    /*从本地缓存拿数据*/
    coreUtil.getData = function(key){
        var localData = layui.sessionData('LocalData');
        return localData[key];
    };
    return coreUtil;
})(CoreUtil, window);