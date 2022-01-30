package example.app15chatting.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;

public class RestHttpClient {
	///Field
	///Constructor
	public RestHttpClient(){
	}
	///Method
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public User  getJsonUser01(String userId) throws Exception{

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url= 	"http://192.168.0.137:8080/user/json/getUser/"+userId.trim();

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println("JSON : "+ serverData);

		if( serverData == null){
			return null;
		}

		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println("JSON Simple Object : " + jsonobj);

		User user = new User();
		user.setUserId( jsonobj.get("userId").toString() );

		return user;
	}


	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public User  getJsonUser02(String userId) throws Exception{

		// HttpClient : Http Protocol 의 client 추상화
		HttpClient httpClient = new DefaultHttpClient();

		String url= 	"http://192.168.0.137:8080/user/json/getUser/"+userId.trim();

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		//==> 다른 방법으로 serverData 처리
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);

		//==> API 확인 : Stream 객체를 직접 전달
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("JSON Simple Object : " + jsonobj);

		if( jsonobj == null){
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);

		return user;
	}
}