package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArmsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    MealsAdapter adapter;

    List<Meals> mealsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_recycler);

        mealsList=new ArrayList<>();
        recyclerView=findViewById(R.id.armsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mealsList.add(new Meals(1,"Barbel Spider Curl",R.drawable.barbelspidercurl,"Prone incline barbell curl (aka barbell spider curl). An auxiliary upper-arm isolation exercise that targets your brachialis. Your biceps brachii and brachioradialis act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie face down (prone) on an inclined bench. You can kneel on the seat or lower the seat to produce a ramp-like flat surface to lean against. Get a training partner to hand you the barbell. Grasp the barbell using a shoulder-width underhand grip and allow the barbell to hang downward.\n" +
                "\n" +
                "Keeping your upper arms still, curl the barbell upward until your elbows are fully flexed. Hold for a second and squeeze your biceps. Lower the barbell in a controlled manner to the starting position. Repeat, making sure to inhale as you lower the barbell and exhale as you curl it.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "Arm curling exercises in which you use an underhand grip and keep your elbows in front of your body (e.g. this exercise, the concentration curl, and the preacher curl) target your brachialis, not your biceps brachii. What's more, the long (outer) head of your biceps brachii is activated much more than is the short (inner) head."));
        mealsList.add(new Meals(2,"Decline Skull Crusher",R.drawable.declineskullcrusher,"Decline bent-arm barbell pull-over. An isolation exercise that targets your latissimus dorsi and lower pectoralis major. The other labeled muscles, along with your rhomboids and pectoralis minor, act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your back on a declined bench holding a barbell, or get a partner to hand you the barbell after you lie down and secure your feet under the pads. Grasp the barbell using a narrow overhand grip and press it up over your chest. Flex your elbows to a 90-degree angle.\n" +
                "\n" +
                "Keeping your elbows flexed at 90 degrees, lower the bar backward, behind your head, until you feel a mild stretch in your triceps. Reverse the motion and return the bar to the starting position. Repeat.\n" +
                "\n" +
                "Inhale as you lower the bar; exhale as you raise it. Keep your elbows flexed; only your upper arms should move. Using an EZ-curl bar might be easier on your wrists."));
        mealsList.add(new Meals(3,"Tate Press",R.drawable.tatepress,"Tate press (aka elbows-out triceps extension). An isolation exercise that targets your triceps brachii, while your pectoralis major and anterior deltoid work synergistically.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on a flat bench and raise a pair of dumbbells into the air. Pronate your wrists so that your palms face forward. The sides of the dumbbells should be close together or touching. Spread your legs and put your feet flat on the floor.\n" +
                "\n" +
                "Slowly lower the dumbbells down to your chest by flexing your elbows. Hold the dumbbells over your chest for a second. Do not rest them on your chest. Press the dumbbells back up to the starting position by extending your elbows. Repeat.\n" +
                "\n" +
                "Inhale as you lower the dumbbells; exhale as you press them up. Keep the dumbbells either close together or in contact with each other. The latter can help to keep them stable. Keep the movement of your upper arms to a minimum. Don't lock out your elbows.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "Popularized by competitive powerlifter Dave Tate, the Tate press works the same muscles as the bench press. However, instead of your pecs, it targets your triceps. When you bench press, your triceps come more into action at the top of the press. If you struggle at the top of the press, your triceps need improvement. The Tate press is great at improving the performance of your triceps during the bench press, which makes it popular amongst powerlifters.\n" +
                "\n" +
                "Tip\n" +
                "\n" +
                "Since your elbows are kept high, you don't need a bench for the Tate press. You can perform it on the floor.\n"));
        mealsList.add(new Meals(4,"Dumbell Hammer Preacher Curl",R.drawable.dumbbellhammerpreachercurl,"Dumbbell hammer preacher curl. An isolation exercise that targets your brachioradialis, not your biceps brachii, which, along with your brachialis, acts as a synergist.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Sit on the preacher bench with a pair of dumbbells. With your elbows bent and palms facing each other, position the backs of your upper arms flat on the arm rest. Lower the dumbbells until your elbows are almost fully extended. Curl the dumbbells back up toward your shoulders. At the top of the movement, hold and squeeze your biceps for a second, before lowering the dumbbells again. Repeat.\n" +
                "\n" +
                "Maintain a neutral (hammer grip). Inhale as you lower the dumbbells; exhale as you curl them back up. Ensure that the backs of your upper arms, from armpits to elbows, are resting flat on the arm rest. This usually means having to lower the seat.\n" +
                "\n" +
                "You can perform this exercise with one arm alone or both arms at the same time, as described. Alternate to add variation and get the benefits of both worlds.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "Your brachioradialis is the target instead of your biceps brachii because (1) the brachioradialis is the primary elbow flexor when you use a pronated or a neutral (hammer) grip, and (2) your biceps (especially the inner head) is put in a state of mechanical disadvantage (active insufficiency) when your elbow is positioned in front of your body."));
        mealsList.add(new Meals(5,"Standing Concentration Curl",R.drawable.standingdumbellconcentrationcurl,"Standing dumbbell concentration curl. An auxiliary arm exercise that targets your brachialis. Your biceps brachii and brachioradialis act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Stand holding a dumbbell in one hand. Bend forward at the hips and knees until your torso is parallel to the floor, and allow the dumbbell to hang straight down. Place your free hand on your knee to support your back. Keeping your upper arm still and your back straight, curl the dumbbell upward. Squeeze your biceps at the top of the movement. Lower the dumbbell to the starting position. Repeat for the desired number of reps. Repeat the exercise with your other arm.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "When it comes to arm curls:\n" +
                "\n" +
                "If your elbow is by your side or behind you and you use a supinated (underhand) grip, you target your biceps. Your brachialis and brachioradialis act as synergists. E.g. incline dumbbell curl.\n" +
                "\n" +
                "If your elbow is in front of you and you use a supinated grip, you target your brachialis. Your biceps and brachioradialis act as synergists. E.g. preacher curl and concentration curl.\n" +
                "\n" +
                "If your elbow is by your side, behind you, or in front of you and you use a hammer grip or reverse grip, you target your brachioradialis. Your biceps and brachialis act as synergists. E.g. hammer curl and reverse curl."));
        mealsList.add(new Meals(7,"Decline Tricep Extension",R.drawable.declinebumbelltricepextension,"Exercise: Decline dumbbell triceps extension\n" +
                "\n" +
                "Type: Compound push\n" +
                "\n" +
                "Target muscle: Triceps brachii\n" +
                "\n" +
                "Synergists: None\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your back on a decline bench with a dumbbell in each hand. Hook your feet under the pads. Raise the dumbbells perpendicular to the floor and hold them using a neutral grip.\n" +
                "\n" +
                "Keeping your upper arms fixed, flex your elbows and lower the dumbbells to the sides of your head in a semicircular motion. Extend your elbows and push the dumbbells back up to the starting position. Repeat.\n" +
                "\n" +
                "Inhale as you lower the dumbbells; exhale as you push them up."));

        adapter= new MealsAdapter(this,mealsList);
        recyclerView.setAdapter(adapter);
    }
}
