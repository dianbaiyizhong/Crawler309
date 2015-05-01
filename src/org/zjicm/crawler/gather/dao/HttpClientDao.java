package org.zjicm.crawler.gather.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.zjicm.crawler.url.pojo.UnVisitedUrl;

public class HttpClientDao {
	private static String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; InfoPath.1; .NET CLR 2.0.50727)";

	public StringBuilder HttpClient(String url) {

		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
		HttpParams params = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小
		HttpConnectionParams.setConnectionTimeout(params, 2 * 1000);
		HttpConnectionParams.setSoTimeout(params, 5 * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		// 设置重定向，缺省为 true
		HttpClientParams.setRedirecting(params, true);

		// 设置 user agent
		HttpProtocolParams.setUserAgent(params, userAgent);

		// 创建一个 HttpClient 实例
		org.apache.http.client.HttpClient httpClient = new DefaultHttpClient(
				params);
		try {
			// 创建 HttpGet 方法，该方法会自动处理 URL 地址的重定向
			HttpGet httpGet = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpGet);

			// 获得状态码
			int status = response.getStatusLine().getStatusCode();

			if (status != HttpStatus.SC_OK) {
				// 错误处理，例如可以在该请求正常结束前将其中断
				// System.out.println(response.getStatusLine().getStatusCode());
				httpGet.abort();

				return null;
			}

			// 读取更多信息
			Header[] headers = response.getAllHeaders();
			HttpEntity entity = response.getEntity();

			StringBuilder result = new StringBuilder(EntityUtils.toString(
					entity, getContentCharSet(entity)));

			return result;

		} catch (Exception e) {

			System.out.println("error:getting hrmlcode <" + url
					+ "> failure because it is overtime");
			// e.printStackTrace();
			//
		} finally {
			// 释放连接
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public StringBuilder HttpClient(UnVisitedUrl unVisitedUrl) {

		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
		HttpParams params = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小
		HttpConnectionParams.setConnectionTimeout(params, 2 * 1000);
		HttpConnectionParams.setSoTimeout(params, 5 * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);

		// 设置重定向，缺省为 true
		HttpClientParams.setRedirecting(params, true);

		// 设置 user agent
		HttpProtocolParams.setUserAgent(params, userAgent);

		// 创建一个 HttpClient 实例
		org.apache.http.client.HttpClient httpClient = new DefaultHttpClient(
				params);
		try {
			// 创建 HttpGet 方法，该方法会自动处理 URL 地址的重定向
			HttpGet httpGet = new HttpGet(unVisitedUrl.getUrl());

			HttpResponse response = httpClient.execute(httpGet);

			// 获得状态码
			int status = response.getStatusLine().getStatusCode();

			System.out.println("getting htmlcode <" + unVisitedUrl.getUrl()
					+ ">  " + "     depth:" + unVisitedUrl.getNowDepth()
					+ "     return status: " + status);

			if (status != HttpStatus.SC_OK) {
				// 错误处理，例如可以在该请求正常结束前将其中断
				// System.out.println(response.getStatusLine().getStatusCode());
				httpGet.abort();

				return null;
			}

			// 读取更多信息
			Header[] headers = response.getAllHeaders();
			HttpEntity entity = response.getEntity();

			StringBuilder result = new StringBuilder(EntityUtils.toString(
					entity, getContentCharSet(entity)));

			return result;

		} catch (Exception e) {

			System.out.println("error:getting hrmlcode <"
					+ unVisitedUrl.getUrl()
					+ "> failure because it is overtime");
			// e.printStackTrace();
			//
		} finally {
			// 释放连接
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	public static List<String> match(String source, String element, String attr) {
		List<String> result = new ArrayList<String>();
		String reg = "<" + element + "[^<>]*?\\s" + attr
				+ "=['\"]?(.*?)['\"]?\\s.*?>";
		Matcher m = Pattern.compile(reg).matcher(source);
		while (m.find()) {
			String r = m.group(1);
			result.add(r);
		}
		return result;
	}

	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (charset == null) {
			charset = "gb2312";
		}

		return charset;
	}

}