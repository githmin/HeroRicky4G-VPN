package com.herorickystudios.sockshttp.activities;

import android.os.Bundle;

import com.herorickystudios.sockshttp.R;

import com.herorickystudios.ultrasshservice.util.SkProtect;
import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.herorickystudios.sockshttp.preference.SettingsSSHPreference;

public class ConfigGeralActivity extends BaseActivity
	implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
{
	public static String OPEN_SETTINGS_SSH = "openSSHScreen";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);

		SettingsSSHPreference preference = new SettingsSSHPreference();
		Intent intent = getIntent();
		
		String action = intent.getAction();
		if (action != null && action.equals(OPEN_SETTINGS_SSH)) {
			setTitle(R.string.settings_ssh);
			preference = new SettingsSSHPreference();
		}
		
		// add preference settings
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.fragment_configLinearLayout, preference)
			.commit();

		// toolbar
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		SkProtect.CharlieProtect();
	}
	
	@Override
	public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
		// Instantiate the new Fragment
		final Bundle bundle = pref.getExtras();
		final Fragment fragment = Fragment.instantiate(this, pref.getFragment(), bundle);
        
		fragment.setTargetFragment(caller, 0);
       
		// Replace the existing Fragment with the new Fragment
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.fragment_configLinearLayout, fragment)
			.addToBackStack(null)
			.commit();
		
		return true;
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		onBackPressed();
		return true;
	}
}

