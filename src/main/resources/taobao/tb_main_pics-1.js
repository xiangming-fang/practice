// ==UserScript==
// @name         淘宝宝贝详情图
// @namespace    http://tampermonkey.net/
// @version      2.53
// @description  详情图 & 颜色图
// @author       fxm
// @license      https://www.apache.org/licenses/LICENSE-2.0
// @match        http://*/*
// @include      https://*/*
// @include      file:///*
// @grant        GM_setValue
// @grant        GM_getValue
// @grant        GM_deleteValue
// ==/UserScript==
(() => {

    function sleep(delay) {
        var start = (new Date()).getTime();
        while ((new Date()).getTime() - start < delay) {

        }
    }

    function download() {

        var protocol = "https:";
        var fmf = "_400x400.jpg";

        // 标题
        var elementById = document.getElementById("J_Title");
        var title = elementById.getElementsByClassName("tb-main-title")[0].getAttribute("data-title");
        title = title.substring(0, 15);

        // 详情图
        var main_pics = document.getElementById("J_DivItemDesc");
        if (main_pics == null) {
            console.log("此页没有主图")
            return;
        }
        var imgs = main_pics.getElementsByTagName("img");
        if (imgs == null) return;
        // 待下载主图名称数组[name,url]
        var pending_download_pic_name_urls = [];
        var k = 0;
        for (let i = 0; i < imgs.length; i++) {
            var picUrl = imgs[i].getAttribute("src");
            if (picUrl == null) {
                continue;
            }
            var suffix = picUrl.split(".");
            var name = (i + 1) + "." + suffix[suffix.length - 1];
            var download_url = protocol + picUrl.substring(0, picUrl.length - 10);
            pending_download_pic_name_urls[k++] = [name, download_url];
        }

        // 颜色图
        var color_pics = document.getElementsByClassName("J_TSaleProp tb-img tb-clearfix")[0];
        if (color_pics == null){
            console.log("此页没有颜色图");
            return;
        }
        var color_lis = color_pics.getElementsByTagName("li");
        for (let i = 0; i < color_lis.length; i++) {
            var color_picUrl = color_lis[i].getElementsByTagName("a")[0].getAttribute("style");
            var s = color_picUrl.indexOf("/");
            var l = color_picUrl.indexOf("jpg") + 3;
            color_picUrl = protocol + color_picUrl.substring(s, l) + fmf;
            var color = color_lis[i].getElementsByTagName("a")[0].getElementsByTagName("span")[0].textContent + ".jpg";
            pending_download_pic_name_urls[k++] = [color, color_picUrl];
        }
        for (let i = 0; i < pending_download_pic_name_urls.length; i++) {
            var fileName = pending_download_pic_name_urls[i][0];
            var url = pending_download_pic_name_urls[i][1];
            console.log(i+1 + " " +  fileName + " " + url);
            getBlob(url, title + "-" + fileName);
        }

    }

    // 第一步，要先通过当前url 取得 blob 对象（必须）
    async function getBlob(url, fileName) {
        // fetch 为ES6新增，不支持IE兼容，请注意分辨
        await fetch(url)
            .then((response) => {
                return response.blob() // 转为文件流 请求类型类型 或其他类型
            })
            .then(res => {
                let blob = new Blob([res]) // 转为blob
                downloadFileName(blob, fileName) // 下载并重命名函数
            })
    }

    function downloadFileName(blob, fileName) {
        let link = document.createElement('a') // 创建a标签
        link.href = window.URL.createObjectURL(blob) // 获取blob 下载url
        link.download = fileName  // a标签新增属性  download 用于指定文件名
        link.click() // 点击下载
        URL.revokeObjectURL(link.href)
        document.body.removeChild(link)
    }

    function downloadPage(){
        if (window.parent.location.href.startsWith("https://item.taobao.com")){

            // 慢慢滚动到底部，因为详情页的图片是懒加载的，等滚动到那个位置才会加载图片链接，不然获取不到
            scrollToTop(document.documentElement.scrollHeight - window.innerHeight);

            setTimeout(download,6000)

            // 给它十秒钟下载时间，足够了吧？然后关掉页面
            // setTimeout(window.close,15000)
        }
    }

    // 封装一个回到底部或者顶部的函数
    function scrollToTop(position) {
        // 使用requestAnimationFrame，如果没有则使用setTimeOut
        if(!window.requestAnimationFrame) {
            window.requestAnimationFrame = function(callback) {
                return setTimeout(callback, 20)
            }
        }

        // 获取当前元素滚动的距离； a || b  获取a、b中第一个不为0的
        let scrollTopDistance = document.documentElement.scrollTop || document.body.scrollTop;

        function smoothScroll() {
            // 如果你要滚到顶部，那么position传过来的就是0，下面这个distance肯定就是负值。
            let distance = position - scrollTopDistance;
            // 每次滚动的距离要不一样，制造一个缓冲效果
            scrollTopDistance = scrollTopDistance + distance / 5;
            // 判断条件
            if(Math.abs(distance) < 1) {
                window.scrollTo(0, position);
            }else {
                window.scrollTo(0, scrollTopDistance);
                requestAnimationFrame(smoothScroll);
            }
        }

        smoothScroll();
    }

    setTimeout(downloadPage,5000);

})();
