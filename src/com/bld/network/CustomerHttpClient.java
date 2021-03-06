package com.bld.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bld.task.HttpClientIO;

public class CustomerHttpClient {

	String debug = getClass().getSimpleName();

	private static final String CHARSET = HTTP.UTF_8;
	private static HttpClient customerHttpClient;

	private CustomerHttpClient() {
	}

	public static synchronized HttpClient getHttpClient() {
		if (null == customerHttpClient) {
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setUseExpectContinue(params, true);
//			HttpProtocolParams
//					.setUserAgent(
//							params,
//							"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
//									+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
			ConnManagerParams.setTimeout(params, 10000);

			HttpConnectionParams.setConnectionTimeout(params, 10000);

			HttpConnectionParams.setSoTimeout(params, 10000);

			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));

			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
					params, schReg);
			customerHttpClient = new DefaultHttpClient(conMgr, params);

			// return customerHttpClient��
		}
		return customerHttpClient;
	}

	public static String getContent(String url) throws Exception {
		return getContent(url, null);
	}

	public static String getContent(String url, HttpClientIO hcio)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		HttpClient client = getHttpClient();
		HttpParams httpParams = client.getParams();

		HttpConnectionParams.setConnectionTimeout(httpParams, 6000);
		HttpConnectionParams.setSoTimeout(httpParams, 6000);
		HttpResponse response = client.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			InputStream is = entity.getContent();
			// 回调
			if (hcio != null) {
				hcio.setInputStream(is);
				hcio.setHttpClient(client);
				hcio.setHttpEntity(entity);
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 1024);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		}
		return sb.toString();
	}

	public static Bitmap getBitmap(String imageUrl)
			throws ClientProtocolException, IOException {
		return getBitmap(imageUrl, null);
	}

	public static Bitmap getBitmap(String imageUrl, HttpClientIO hcio)
			throws ClientProtocolException, IOException {
		HttpGet httpRequest = new HttpGet(imageUrl);
		HttpClient httpclient = getHttpClient();
		HttpResponse httpResponse;

		httpResponse = httpclient.execute(httpRequest);

		HttpEntity httpEntity = httpResponse.getEntity();

		InputStream is;
		is = httpEntity.getContent();
		// 回调
		if (hcio != null) {
			hcio.setInputStream(is);
			hcio.setHttpClient(httpclient);
			hcio.setHttpEntity(httpEntity);
		}
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		is.close();
		return bitmap;

	}

}