package com.xm.jy.test.jdk.net.netprogram;

/**
 * Description:
 * ��վ: <a href="http://www.crazyit.org">���Java����</a><br>
 * Copyright (C), 2001-2018, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class MultiThreadDown
{
	public static void main(String[] args) throws Exception {
		// 初始化 DownUtil 对象
		final DownUtil downUtil = new DownUtil("https://mh.chaoxing.com/nginx_media/upload/2019/12/pic-13221113192303239_meitu_2-1576639708494.jpg"
				, "C:\\Users\\Administrator\\Desktop\\test.jpg", 40);
		// 开始下载
		downUtil.download();
		new Thread(() -> {
				while(downUtil.getCompleteRate() < 1) {
					// 每隔 0.1s 查询一次任务的完成进度
					System.out.println("已完成："
						+ downUtil.getCompleteRate());
					try {
						Thread.sleep(1);
					}catch (Exception ex){
						ex.printStackTrace();
					}
				}
		}).start();
	}
}
