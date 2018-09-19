package demon_munchies.paint;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RandomMunchies extends AppCompatActivity {

    int red;
    int green;
    int blue;

    Button color;

    TextView text;
    TextView redVal;
    TextView greenVal;
    TextView blueVal;
    TextView HTML;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_munchies);
        color = (Button) findViewById(R.id.button);

        text = (TextView) findViewById(R.id.changeText);
        redVal = (TextView) findViewById(R.id.red);
        greenVal = (TextView) findViewById(R.id.green);
        blueVal = (TextView) findViewById(R.id.blue);
        HTML = (TextView) findViewById(R.id.htmlColor);

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red = (int)(Math.random()*256);
                green = (int)(Math.random()*256);
                blue = (int)(Math.random()*256);
                text.setTextColor(Color.rgb(red, green, blue));

                redVal.setText("" + red);
                greenVal.setText("" + green);
                blueVal.setText("" + blue);

                String hex = String.format("#%02x%02x%02x", red, green, blue);
                HTML.setText(hex);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.random, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.back:
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
