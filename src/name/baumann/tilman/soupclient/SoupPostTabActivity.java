package name.baumann.tilman.soupclient;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class SoupPostTabActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.message_post_tabs);

	    Resources res = getResources(); 
	    TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;  
	    Intent intent; 

	    intent = new Intent().setClass(this, PostText.class);
	    spec = tabHost.newTabSpec("message").setIndicator("Message", res.getDrawable(R.drawable.ic_tab_text)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, PostLink.class);
	    spec = tabHost.newTabSpec("link").setIndicator("Link", res.getDrawable(R.drawable.ic_tab_link)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostQuote.class);
	    spec = tabHost.newTabSpec("quote").setIndicator("Quote", res.getDrawable(R.drawable.ic_tab_quote)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostImage.class);
	    spec = tabHost.newTabSpec("image").setIndicator("Image", res.getDrawable(R.drawable.ic_tab_image)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostVideo.class);
	    spec = tabHost.newTabSpec("video").setIndicator("Video", res.getDrawable(R.drawable.ic_tab_video)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostLink.class);
	    spec = tabHost.newTabSpec("file").setIndicator("File", res.getDrawable(R.drawable.ic_tab_file)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostReview.class);
	    spec = tabHost.newTabSpec("review").setIndicator("Review", res.getDrawable(R.drawable.ic_tab_review)).setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PostEvent.class);
	    spec = tabHost.newTabSpec("event").setIndicator("Event", res.getDrawable(R.drawable.ic_tab_event)).setContent(intent);
	    tabHost.addTab(spec);

	    
	    tabHost.setCurrentTab(0);
	}
}
