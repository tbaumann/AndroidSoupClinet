/**
 * 
 */
package name.baumann.tilman.soupclient;


import name.baumann.tilman.soupclient.lib.CookieReader;
import android.app.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
//import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends Activity {
	private WebView mWebView;
	public static final String PREFS_NAME = "Preferences";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        class HelloWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
/*
 * Ok, toggling Button clickable is stupid because there is no visible feedback. 
 * Since there is no enabled/disabled I scrap this idea for now.
 * FIXME Find better button toggle or scrap it
            @Override
            public void onPageFinished (WebView view, String url) {
            	Button mButton = (Button) findViewById(R.id.login_done_button);
               	CookieReader soupCookie =  (CookieReader) new CookieReader();
               	soupCookie.parseText(CookieManager.getInstance().getCookie(url));
               	String session_id = (String) soupCookie.get("soup_session_id");
               	if(session_id == null ){
               		mButton.setClickable(false);
               	}else{
               		mButton.setClickable(true);
               	}
            }
*/
        }
        
        mWebView = (WebView) findViewById(R.id.login_webview);
        mWebView.setWebViewClient(new HelloWebViewClient());
        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.soup.io/login"); 
    }


/*  
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/
    

    public void doneButtonPressed(View view){
    	String url = mWebView.getUrl();
    	String basedomain = null;
    	
    	CookieReader soupCookie =  (CookieReader) new CookieReader();
       	soupCookie.parseText(CookieManager.getInstance().getCookie(url));
       	String session_id = (String) soupCookie.get("soup_session_id");
       	
       	Pattern pat = Pattern.compile("http://([^/]*)/.*");
   		Matcher mat = pat.matcher(url);
   		if (mat.find()){
   			basedomain = mat.group(1);
    	}
   		
       	if(session_id == null || basedomain == null){
       		Toast.makeText(getApplicationContext(), R.string.no_session_id_found, Toast.LENGTH_SHORT).show();
       		setResult(RESULT_CANCELED);
       	}else{
       		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
       		SharedPreferences.Editor editor = settings.edit();
       		editor.putString("soup_session_id", session_id);
       		editor.putString("soup_url", url);
       		editor.putString("soup_basedomain", basedomain);
       		editor.commit();
       		setResult(RESULT_OK);
       		Toast.makeText(getApplicationContext(), "Found session ID: " + session_id, Toast.LENGTH_SHORT).show();
       	}
        finish();
    }
}
