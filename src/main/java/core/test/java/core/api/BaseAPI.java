package core.test.java.core.api;

import core.test.java.core.enumobject.EnumObject.APIType;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



public class BaseAPI {
	private Map<Object, Object> param;
	private Map<Object, Object> header;
	private String uri;
	private APIType type;

	public BaseAPI() {
		param = new HashMap<>();
		header = new HashMap<>();
	}
	
	public BaseAPI(String uri, APIType type)
	{
		param = new HashMap<>();
		header = new HashMap<>();
		this.uri = uri;
		this.type = type;
	}
	public void addHeader(String key, String value) {
		header.put(key, value);
	}

	public void addParam(String key, String value) {
		param.put(key, value);
	}

	public HttpResponse<String> sendRequest() {
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		Builder builder = HttpRequest.newBuilder();
		switch (type) {
			case GET:
				builder = builder.GET();
				break;
			case POST:
				builder = builder.POST(buildFormDataFromMap(param));
				break;
			case DELETE:
				builder = builder.DELETE();
		}
		builder = builder.uri(URI.create(uri));
		builder = addHeaderToRequestBuilder(builder, header);
		HttpRequest request = builder.build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}

	}

	private Builder addHeaderToRequestBuilder(Builder builder, Map<Object, Object> header) {
		for (Map.Entry<Object, Object> entry : header.entrySet()) {
			builder = builder.setHeader(entry.getKey().toString(), entry.getValue().toString());
		}

		return builder;
	}

	private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<Object, Object> entry : data.entrySet()) {
			if (builder.length() > 0) {
				builder.append("&");
			}
			builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
			builder.append("=");
			builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
		}
		System.out.println(builder.toString());
		return HttpRequest.BodyPublishers.ofString(builder.toString());
	}

}
