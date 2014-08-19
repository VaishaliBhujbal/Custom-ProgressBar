package com.yuva.android.progressbardemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ProgressBar mProgressBar;
	private TextView mLoadingText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mProgressBar=(ProgressBar) findViewById(R.id.progressBar1);
		mLoadingText=(TextView) findViewById(R.id.loadingText);
		new ProgressAsync().execute();
	}

	public class ProgressAsync extends AsyncTask<Void,Integer,Void>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (int i = 1; i <=100; i++) {
				if (isCancelled()) {
					break;
				} else {

					publishProgress(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			return null;
		}
		
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			mProgressBar.setProgress(values[0]);
			mLoadingText.setText("Loading - "+values[0]+"/100");
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mLoadingText.setText("Loading Completed");
			Toast.makeText(MainActivity.this,"Progress Complete...",Toast.LENGTH_SHORT).show();
		}
	}
}