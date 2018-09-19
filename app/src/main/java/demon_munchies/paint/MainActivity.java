package demon_munchies.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.random:
                Intent goToRandom = new Intent(getApplicationContext(), RandomMunchies.class);
                startActivity(goToRandom);
                break;
            case R.id.paint:
                Intent goToPaint = new Intent(getApplicationContext(), PaintingMunchies.class);
                startActivity(goToPaint);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
