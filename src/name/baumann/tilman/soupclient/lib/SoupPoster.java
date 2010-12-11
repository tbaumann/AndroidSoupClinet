package name.baumann.tilman.soupclient.lib;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.BasicCookieStore;

public class SoupPoster {
	private String basedomain;
	private String soup_session_id;

    public SoupPoster(String basedomain, String soup_session_id) {
		super();
		this.basedomain = basedomain;
		this.soup_session_id = soup_session_id;
	}

	public HttpResponse post(HashMap<String, String> params)
    	throws IOException {

                HttpClient httpClient = new DefaultHttpClient();

                HttpPost postRequest = new HttpPost("http://" + basedomain + "/save");

                httpClient.getParams().setParameter("httpclient.useragent", "AndroidClient");
              


             // Create a local instance of cookie store
                BasicCookieStore cookieStore = new BasicCookieStore();
                // Populate cookies if needed
                BasicClientCookie cookie = new BasicClientCookie("soup_session_id", soup_session_id);
 //               cookie.setVersion(0);
                cookie.setDomain(basedomain);
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
                // Set the store 
                ((AbstractHttpClient) httpClient).setCookieStore(cookieStore);

                
                // FIXME paramerters map
                MultipartEntity multipartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                
                Iterator<String> i = params.keySet().iterator();
                while(i.hasNext()){
                	String key = i.next();
                	String value = params.get(key);

                	multipartContent.addPart(key, new StringBody(value, "text/plain", Charset.forName( "UTF-8" )) );
                }
          
                postRequest.setEntity(multipartContent);
                
                HttpResponse response = httpClient.execute(postRequest);


                // Response content probably never needed anywhere?
                response.getEntity().getContent().close();
                return response;
	}

}
