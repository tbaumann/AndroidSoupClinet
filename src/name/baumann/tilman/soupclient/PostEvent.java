package name.baumann.tilman.soupclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PostEvent extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Event tab");
        setContentView(textview);
    }
}
