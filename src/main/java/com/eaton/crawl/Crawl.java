package com.eaton.crawl;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

/**
 * Created by Eaton on 1/23/2018.
 */
public class Crawl {

    public static void getUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
//            System.out.println(doc);
            //得到html的所有东西
            Element content = doc.getElementById("content");
            //分离出html下<a>...</a>之间的所有东西
//            Elements links = content.getElementsByTag("a");
            Elements links = doc.select("a[href]");
            // 扩展名为.png的图片
            Elements pngs = doc.select("img[src$=.png]");
            // class等于masthead的div标签
            Element masthead = doc.select("div.masthead").first();

            for (Element link : links) {
                //得到<a>...</a>里面的网址
                String linkHref = link.attr("href");
                //得到<a>...</a>里面的汉字
                String linkText = link.text();
                System.out.println(linkText);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //将抓取的网页变成html文件，保存在本地
    public static void saveHtml(String url) {
        try {
            File dest = new File("src/main/resources/" + "web.html");
            //接收字节输入流
            InputStream is;
            //字节输出流
            FileOutputStream fos = new FileOutputStream(dest);

            URL temp = new URL(url);
            is = temp.openStream();

            //为字节输入流加缓冲
            BufferedInputStream bis = new BufferedInputStream(is);
            //为字节输出流加缓冲
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int length;

            byte[] bytes = new byte[1024 * 20];
            while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
            }

            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //解析本地的html
    public static void parseLocalhtml(String path) {

        //读取本地html的路径
        File file = new File(path);
        //生成一个数组用来存储这些路径下的文件名
        File[] array = file.listFiles();
        //写个循环读取这些文件的名字

        for(int i=0;i<array.length;i++){
            try{
                if(array[i].isFile()){
                    //文件名字
                    System.out.println("正在解析网址：" + array[i].getName());

                    //下面开始解析本地的html
                    Document doc = Jsoup.parse(array[i], "UTF-8");
                    //得到html的所有东西
                    Element content = doc.getElementById("content");
                    //分离出html下<a>...</a>之间的所有东西
//                    Elements links = content.getElementsByTag("a");
                    Elements links = doc.select("a[href]");
                    // 扩展名为.png的图片
                    Elements pngs = doc.select("img[src$=.png]");
                    // class等于masthead的div标签
                    Element masthead = doc.select("div.masthead").first();

                    for (Element link : links) {
                        //得到<a>...</a>里面的网址
                        String linkHref = link.attr("href");
                        //得到<a>...</a>里面的汉字
                        String linkText = link.text();
                        System.out.println(linkText);
                    }
                }
            }catch (Exception e) {
                System.out.println("网址：" + array[i].getName() + "解析出错");
                e.printStackTrace();
                continue;
            }
        }
    }


    public static void main(String[] args) {
        String urlBlog = "http://www.cnblogs.com/eatongeng/";
        String urlCSDN = "http://blog.csdn.net/Eaton18";
        String localPath = "src/main/resources/";
        String poolLogPath = "http://appmon.vip.ebay.com/logview/environment/prod/pool/r1opmssvc/machine/lvsopmssvc-1316073.stratus.lvs.ebay.com/rawLog?datetime=2018/01/18%2020:00&thread=195";
        saveHtml(poolLogPath);
//        getUrl(poolLogPath);
        // parseLocalhtml(localPath);
    }

}
