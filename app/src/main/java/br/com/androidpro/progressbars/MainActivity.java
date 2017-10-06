package br.com.androidpro.progressbars;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar circularBar;
    private ProgressBar linearBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularBar = (ProgressBar) findViewById(R.id.circularBar);
        circularBar.setVisibility(View.GONE);

        linearBar = (ProgressBar) findViewById(R.id.linearBar);
        linearBar.setProgress(0);
        linearBar.setSecondaryProgress(0);
        linearBar.setMax(100);

        new AsyncCircular().execute();
    }

    public class AsyncCircular extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            circularBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = 0; i < 100; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            linearBar.setProgress(values[0]);
            linearBar.setSecondaryProgress(values[0] + 15);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            circularBar.setVisibility(View.GONE);
        }
    }
}
