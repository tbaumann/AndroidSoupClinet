package name.baumann.tilman.soupclient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PostVideo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Video tab");
        setContentView(textview);
    }
}
