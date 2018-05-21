package com.smyhvae.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class HttpUtil {
    public static HttpClient httpClient = new DefaultHttpClient();
    public static HttpClient imageHttpClient = new DefaultHttpClient();
    public static final String BASE_URL = "http://"+ "192.168.31.220:8080" +"/future/app/entry.htm?query=";
    public static HttpPost post, imagePost;

    /**
     *
     * @param url
     *            发送请求的URL
     * @return 服务器响应字符串
     * @throws Exception
     */
    public static String getRequest(final String url) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 创建HttpGet对象。
                HttpGet get = new HttpGet(url);
                // 发送GET请求
                HttpResponse httpResponse = httpClient.execute(get);
                // 如果服务器成功地返回响应
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    // 获取服务器响应字符串
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    return result;
                } else {
                    Log.d("============", (new Integer(httpResponse.getStatusLine()
                            .getStatusCode())).toString());
                    return null;
                }
            }
        });
        new Thread(task).start();
        return task.get();


    }

    /**
     *
     * @param url
     *            发送请求的URL
     *            请求参数
     * @return 服务器响应字符串
     * @throws Exception
     */
    public static synchronized String postRequest(final String url, final Map<String, String> rawParams)
            throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result = null;
                // 创建HttpPost对象。
                post = new HttpPost(url);
                // 如果传递参数个数比较多的话可以对传递的参数进行封装
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : rawParams.keySet()) {
                    // 封装请求参数
                    params.add(new BasicNameValuePair(key, rawParams.get(key)));
                }
                // 设置请求参数

                post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                // 发送POST请求
                HttpResponse httpResponse = httpClient.execute(post);
                // 如果服务器成功地返回响应
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    // 获取服务器响应字符串
                    result = EntityUtils.toString(httpResponse.getEntity());
                    return result;
                }else if(httpResponse.getStatusLine().getStatusCode() == 503){
                    Log.d("HttpPost", "服务不可用");
                }else if(httpResponse.getStatusLine().getStatusCode() == 404){
                    Log.d("HttpPost", "请求的网页不存在");
                }else {
                    Log.d("HttpPost", "HttpPost方式请求失败");
                }
                return result;
            }
        });
        new Thread(task).start();
        return task.get();

    }



    public static String post(String url, Map<String, String> params, Map<String, File> files) throws IOException {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX ="--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA ="multipart/form-data";
        String CHARSET ="UTF-8";
        URL uri =new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(10* 1000);// 缓存的最长时间
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);// 不允许使用缓存
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection","keep-alive");
        conn.setRequestProperty("Charsert","UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA +";boundary=" + BOUNDARY);
        // 首先组拼文本类型的参数
        StringBuilder sb =new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\""+ entry.getKey() + "\""+ LINEND);
            sb.append("Content-Type: text/plain; charset="+ CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit"+ LINEND);
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }
        DataOutputStream outStream =new DataOutputStream(conn.getOutputStream());
        outStream.write(sb.toString().getBytes());
        // 发送文件数据
        if(files != null)
            for(Map.Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 =new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"fileTypefileName\"; filename=\""
                        + file.getValue().getName() +"\"" + LINEND);

                Log.i("TestLog","file.getValue().getName()"+file.getValue().getName());
                sb1.append("Content-Type: application/octet-stream; charset="+ CHARSET + LINEND);
                sb1.append(LINEND);
                outStream.write(sb1.toString().getBytes());
                InputStream is =new FileInputStream(file.getValue());
                byte[] buffer =new byte[1024];
                int len = 0;
                while((len = is.read(buffer)) != -1) {
                    outStream.write(buffer,0, len);
                }
                is.close();
                outStream.write(LINEND.getBytes());
            }
        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();
        // 得到响应码
        int res = conn.getResponseCode();
        InputStream in = conn.getInputStream();
        StringBuilder sb2 =new StringBuilder();
        if(res == 200) {
            int ch;
            while((ch = in.read()) != -1) {
                sb2.append((char) ch);
            }
        }
        outStream.close();
        conn.disconnect();
        return sb2.toString();
    }


    public static synchronized Bitmap postRequestPhoto(final String url, final Map<String, String> rawParams) {
        FutureTask<Bitmap> task = new FutureTask<Bitmap>(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                Bitmap result = null;
                // 创建HttpPost对象。
                imagePost = new HttpPost(url);
                // 如果传递参数个数比较多的话可以对传递的参数进行封装
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (String key : rawParams.keySet()) {
                    // 封装请求参数
                    params.add(new BasicNameValuePair(key, rawParams.get(key)));
                }
                // 设置请求参数
                imagePost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                // 发送POST请求
                HttpResponse httpResponse = imageHttpClient.execute(imagePost);
                // 如果服务器成功地返回响应
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    // 获取服务器响应字符串
                    //result = EntityUtils.toString();
                    //Log.d("HttpPost", "服务返回值"+httpResponse.getEntity().getContentLength());
                    //InputStream in =httpResponse.getEntity().getContent() ;
                    result = BitmapFactory.decodeStream(httpResponse.getEntity().getContent());
                    return result;
                }else if(httpResponse.getStatusLine().getStatusCode() == 503){
                    Log.d("HttpPost", "服务不可用");
                }else if(httpResponse.getStatusLine().getStatusCode() == 404){
                    Log.d("HttpPost", "请求的网页不存在");
                }else {
                    Log.d("HttpPost", "HttpPost方式请求失败");
                }
                return result;
            }
        });
        new Thread(task).start();
        try {
            return task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
