package com.map;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class PlaceApi {
	private static final String AK = "X0CfhuTVkr1Vb5QuHB9gqm3h";
	private static final String URL = "http://api.map.baidu.com/place/v2/search";
	private static final String OUTPUT = "json";

	private static Logger logs = Logger.getLogger(PlaceApi.class);

	public String query() throws Exception {
		String q="立水桥";
		System.out.println(URLEncoder.encode(q,"UTF-8"));
		String region="北京";
		InputStream inputStream = null;
		StringBuilder sb = new StringBuilder();
		HttpClient httpClient = new HttpClient();
		String ur=URL + "?q=" + URLEncoder.encode(q,"UTF-8") + "&region="+ URLEncoder.encode(region) + "&output=" + OUTPUT + "&ak=" + AK;
		
		logs.debug(ur);
		
		GetMethod getMethod = new GetMethod(ur);
		
//		HttpMethodParams param = new HttpMethodParams();
//		param.setParameter("q", q);
//		param.setParameter("region", region);
//		param.setParameter("output", OUTPUT);
//		param.setParameter("ak", AK);
		
//		getMethod.getParams().setParameter("q", q);
//		getMethod.getParams().setParameter("region", region);
//		getMethod.getParams().setParameter("output", OUTPUT);
//		getMethod.getParams().setParameter("ak", AK);
//		getMethod.setRequestHeader("q", q);
//		getMethod.setRequestHeader("region", region);
//		getMethod.setRequestHeader("output", OUTPUT);
//		getMethod.setRequestHeader("ak", AK);
		
//		getMethod.setParams(param);
//		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			logs.debug("---->" + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
//				BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsString().getResponseBodyAsStream(), "UTF-8")); 
//                String line; 
//                while ((line = reader.readLine()) != null) { 
//                        if (true) 
//                                sb.append(line).append(System.getProperty("line.separator"));
//                        else 
//                                sb.append(line); 
//                } 
//                reader.close(); 
				return getMethod.getResponseBodyAsString();
//				inputStream = getMethod.getResponseBodyAsStream();
//				byte[] b = new byte[1024];
//				int r_len=-1;
//				while ((r_len = inputStream.read(b)) != -1) {
//					sb.append(new String(b, 0, r_len, getMethod.getResponseCharSet()));
//				}
			}else{
				logs.debug("---->"+statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
			if(inputStream != null){
				inputStream.close();
			}
		}
		logs.debug(sb.toString());
		return null;
	}
}
