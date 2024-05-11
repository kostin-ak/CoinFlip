package kostin.ak.coinflip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import  android.widget.Toast;
import java.util.Random;

import static kostin.ak.coinflip.R.drawable.heads;
import static kostin.ak.coinflip.R.drawable.tails;

import kostin.ak.coinflip.R;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private ImageView coinView;
    private TextView textView;
    private Activity activity = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        coinView = (ImageView) findViewById(R.id.coin);
        coinView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCoin();
            }

        });
    }

    private void flipCoin(){
        Animation fadeOut = AnimationUtils.loadAnimation(activity, R.anim.shrink_to_middle);
        Animation fadeOutText = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOutText.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);
        fadeOutText.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOutText.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                random = new Random();

                int side = random.nextInt(2);

                if (side == 1 ) {
                    coinView.setImageResource(tails);
                    textView.setText("TAILS");
                } else if (side == 0) {
                    coinView.setImageResource(heads);
                    textView.setText("HEADS");
//                    Toast.makeText(getApplicationContext(), "Heads", Toast.LENGTH_SHORT).show();
                }





                Animation fadeIn = AnimationUtils.loadAnimation(activity, R.anim.grow_from_middle);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(500);
                fadeIn.setFillAfter(true);

                Animation fadeInText = new AlphaAnimation(0, 1);
                fadeInText.setInterpolator(new DecelerateInterpolator());
                fadeInText.setDuration(1000);
                fadeInText.setFillAfter(true);

                coinView.startAnimation(fadeIn);
                textView.startAnimation(fadeInText);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        coinView.startAnimation(fadeOut);
        textView.startAnimation(fadeOutText);





    }
}
