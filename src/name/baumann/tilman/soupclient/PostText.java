package name.baumann.tilman.soupclient;

import java.util.HashMap;

import org.apache.http.HttpResponse;

import name.baumann.tilman.soupclient.lib.SoupPoster;
import android.content.Context;
import android.content.SharedPreferences;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostText extends Activity {
	public static final String PREFS_NAME = "Preferences";

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_text);
    }
	public void post(View view){
		SharedPreferences settings = (SharedPreferences) getSharedPreferences(PREFS_NAME, 0);   
        String session_id = settings.getString("soup_session_id", null);
        String basedomain = settings.getString("soup_basedomain", null);
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		HashMap <String, String> params = new HashMap<String, String>();
		params.put("post[title]", ((EditText) findViewById(R.id.post_text_subject)).getText().toString());
		params.put("post[body]",  ((EditText) findViewById(R.id.post_text)).getText().toString());
		params.put("post[tags]",  ((EditText) findViewById(R.id.post_text_tags)).getText().toString());
		params.put("post[id]", "");
		params.put("post[type]", "PostRegular");
		params.put("commit", "Save");
		
		SoupPoster soupPoster = new SoupPoster(basedomain , session_id);
		try{
			// FIXME Either post in background or make better UI 
			HttpResponse response = soupPoster.post(params);
			// FIXME Error code handling.
			Toast toast = Toast.makeText(context, Integer.toString(response.getStatusLine().getStatusCode()), duration);
			toast.show();
		}catch(Exception e){
			CharSequence text = e.getMessage();
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		finish();
	}
}
