package name.baumann.tilman.soupclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SoupClientMain extends Activity {
	private SharedPreferences settings;
	public static final String PREFS_NAME = "Preferences";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toggleLoginState();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, Menu.FIRST, 0, R.string.login);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
            	Intent i = new Intent(this, Login.class);
                startActivityForResult(i, 1);
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	toggleLoginState();
    }
    
    public void toggleLoginState(){
        // Restore preferences
        settings = (SharedPreferences) getSharedPreferences(PREFS_NAME, 0);   
        String session_id = settings.getString("soup_session_id","");

        // FIXME Change main view

        if(session_id != null){

        }else{
        	
        }
        
    }
    
    // Probably overkill but what the heck why not?
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	toggleLoginState();
    }
    
    public void startPostActivity(View view){
    	Intent i = new Intent(this, SoupPostTabActivity.class);
        startActivity(i);
    }
    	
}