package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import ch.epfl.sweng.qeeqbii.Constants_share.Extra;
import ch.epfl.sweng.qeeqbii.assist.FacebookEventObserver;

public class HomeActivity_share extends Activity {

	private FacebookEventObserver facebookEventObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_home);
		findViewById(R.id.button_share_facebook).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startFacebookActivity();
			}
		});

		facebookEventObserver = FacebookEventObserver.newInstance();
	}

	@Override
	public void onStart() {
		super.onStart();
		facebookEventObserver.registerListeners(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		facebookEventObserver.unregisterListeners();
	}

	private void startFacebookActivity() {
		Intent intent = new Intent(this, FacebookActivity.class);
		intent.putExtra(Extra.POST_MESSAGE, Constants_share.FACEBOOK_SHARE_MESSAGE);
		intent.putExtra(Extra.POST_LINK, Constants_share.FACEBOOK_SHARE_LINK);
		intent.putExtra(Extra.POST_LINK_NAME, Constants_share.FACEBOOK_SHARE_LINK_NAME);
		intent.putExtra(Extra.POST_LINK_DESCRIPTION, Constants_share.FACEBOOK_SHARE_LINK_DESCRIPTION);
		intent.putExtra(Extra.POST_PICTURE, Constants_share.FACEBOOK_SHARE_PICTURE);
		startActivity(intent);
	}

}