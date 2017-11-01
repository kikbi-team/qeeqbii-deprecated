package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;


public class HomeActivity_share extends Activity {

	CallbackManager callbackManager;
	ShareDialog shareDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);

		findViewById(R.id.button_share_facebook).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogue();
			}
		});

	}


	public void dialogue () {
		callbackManager = CallbackManager.Factory.create();
		shareDialog = new ShareDialog(this);
		if (ShareDialog.canShow(ShareLinkContent.class)) {
			ShareLinkContent linkContent = new ShareLinkContent.Builder()
					.setContentUrl(Uri.parse("http://developers.facebook.com/android"))
					.build();
			shareDialog.show(linkContent);
		}
	}


}