function httpRequest(obj, successfun, errFun) {
    var xmlHttp = null;
    //创建 XMLHttpRequest 对象，老版本的 Internet Explorer （IE5 和 IE6）
    //使用 ActiveX 对象：xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")
    if (window.XMLHttpRequest) {
        //code for all new browsers
        xmlHttp = new XMLHttpRequest;
    } else if (window.ActiveXObject) {
        //code for IE5 and IE6
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    //判断是否支持请求
    if (xmlHttp == null) {
        alert("浏览器不支持xmlHttp");
        return;
    }
    //请求方式， 转换为大写
    var httpMethod = (obj.method || "Get").toUpperCase();
    //数据类型
    var httpDataType = obj.dataType || 'json';
    //url
    var httpUrl = obj.url || '';
    //异步请求
    var async = true;
    //post请求时参数处理
    if (httpMethod == "POST") {
        requestData = JSON.stringify(obj.data)
        console.log(requestData);
    }
    //onreadystatechange 是一个事件句柄。它的值 (state_Change) 是一个函数的名称，
    //当 XMLHttpRequest 对象的状态发生改变时，会触发此函数。
    //状态从 0 (uninitialized) 到 4 (complete) 进行变化。仅在状态为 4 时，我们才执行代码
    xmlHttp.onreadystatechange = function () {
        //complete
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                //请求成功执行的回调函数
                successfun(xmlHttp.responseText);
            } else {
                //请求失败的回调函数
                errFun();
            }
        }
    }
    //请求接口
    if (httpMethod == 'GET') {
        xmlHttp.open("GET", httpUrl, async);
        xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlHttp.send(null);
    } else if (httpMethod == "POST") {
        xmlHttp.open("POST", httpUrl, async);
        xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlHttp.send(requestData);
    }
}

function sleep(delay) {
    var start = (new Date()).getTime();
    while ((new Date()).getTime() - start < delay) {

    }
}

function showButton() {
    var button = document.createElement("button");
    button.id = "down_ali_pics";
    button.textContent = "download";
    button.style = "position:fixed;right:100px;bottom:100px;z-index:999999999";
    document.body.appendChild(button);
    document.getElementById("down_ali_pics").addEventListener("click", () => {
        var bbs = document.querySelectorAll(".item3line1 .item .detail .J_TGoldData");
        for(var i = 0; i < bbs.length; i++){
            console.log(bbs[i].getAttribute("href"));
            window.open(bbs[i].getAttribute("href"),"_blank")
            sleep(11000);
        }
    })
}