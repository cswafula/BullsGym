package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LegsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    MealsAdapter adapter;

    List<Meals> mealsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_recycler);

        mealsList=new ArrayList<>();
        recyclerView=findViewById(R.id.legsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mealsList.add(new Meals(1,"Dumbel Pistol Squat",R.drawable.dumbelpistolsquat,"Note: The above illustration isn't optimal. He must squat far lower and hold the dumbbell in his opposite hand. Sorry!\n" +
                "\n" +
                "Exercise description\n" +
                "\n" +
                "Dumbbell single-leg squat (aka dumbbell pistol squat). A unilateral compound exercise that targets your quadriceps. Your gluteus maximus, adductor magnus, erector spinae, and soleus act as synergists. As with all squats, your hamstrings only work as dynamic stabilizers.\n" +
                "\n" +
                "Use this exercise to improve unilateral functional strength and balance, and to improve your barbell squat.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Stand holding a dumbbell by your side. Grasp something sturdy with your free hand. Raise the leg opposite the dumbbell in front of you.\n" +
                "\n" +
                "Keeping your torso upright and head up, squat as far as possible in a controlled manner. Return to the starting position. Repeat for more reps. Repeat with your other leg.\n" +
                "\n" +
                "Keep the foot and knee of your supporting leg pointing in the same direction. Inhale as you squat; exhale as you stand. Master the exercise without a dumbbell before trying it with a dumbbell."));
        mealsList.add(new Meals(2,"Dumbel Sumo Squat",R.drawable.dumbelsumosquat,"Dumbbell sumo squat. A compound lower-body exercise that targets your gluteus maximus and quadriceps (rectus femoris, vastus lateralis, vastus medialis, and vastus intermedius). Your adductor magnus and soleus act as synergists, while your gastrocnemius and hamstrings work as dynamic stabilizers.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Holding one end of a dumbbell with both hands, stand with your feet wide apart and pointing out to the sides. Keeping your torso upright and arms stationary, lower the dumbbell toward the floor by flexing your hips and knees. Descend at least until your thighs are parallel with the floor. Push yourself back up to a standing position. Repeat.\n" +
                "\n" +
                "Inhale as you squat; exhale as you stand back up. Keep your head up, torso upright, back straight, and feet flat. Keep your knees and feet pointing in the same direction.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "The dumbbell sumo squat is great for learning the basics of squat form, especially how to keep your torso upright as you descend. Since the center of gravity is low, the exercise is relatively simple, not to mention easy on your lower back, which makes it great for people who suffer from lower-back pain."));
        mealsList.add(new Meals(3,"Jefferson Squat",R.drawable.jeffersonsquat,"Jefferson squat. A compound lower-body exercise that targets your gluteus maximus and quadriceps (rectus femoris, vastus lateralis, vastus medialis, and vastus intermedius). Your adductor magnus and soleus act as synergists, while your gastrocnemius and hamstrings work as dynamic stabilizers. Note: I only highlight targets and synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Straddle the center of a barbell using a shoulder width (or wider) stance. Squat and grasp the barbell with one hand in front and the other hand behind. Keeping your head up and back straight, stand and lift the barbell between your legs. This is the starting position.\n" +
                "\n" +
                "Keeping your head up and back straight, squat down at least until your knees are bent at a 90-degree angle. Stand back up to the starting position, driving through your heels. Repeat.\n" +
                "\n" +
                "Inhale as you descend; exhale as you ascend. Keep your feet flat, your torso upright, and your shoulders back. Keep your knees and feet pointing in the same direction, which should be slightly out to the sides. Ensure that the weight is evenly distributed between your two legs.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "Use the Jefferson squat to improve your barbell squat and deadlift. You can also use the Jefferson squat as your primary squatting exercise if the barbell squat is too hard on your lower back."));
        mealsList.add(new Meals(4,"Leg Extension Crunch",R.drawable.legeextensioncrunch,"Leg extension crunch using an exercise ball. A compound core and leg exercise that targets your quadriceps and rectus abdominis. Your internal and external obliques, iliopsoas, tensor fasciae latae, pectineus, sartorius, adductor longus, and adductor brevis act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on the floor with your hands behind your head, your knees bent, and your feet clasping an exercise ball. Press your lower back against the floor so that it is supported. Raise the ball off the floor a little. This is the starting position.\n" +
                "\n" +
                "Simultaneously flex your abdomen and extend your knees so that your upper back is raised off the floor (crunch) and the exercise ball is raised into the air (leg extension). Simultaneously relax your abdomen and flex your knees to lower your upper back and the exercise ball to the starting position. Repeat.\n" +
                "\n" +
                "Don't let the ball touch the floor. Exhale as you crunch and extend your knees; inhale as you return to the starting position. Keep your lower back pressed against the floor. To ensure that there is resistance on your quads through the leg extension, keep your thighs slanted, not vertical.\n" +
                "\n" +
                "Tip\n" +
                "\n" +
                "Make the crunch more difficult by holding a plate behind your head or by extending your arms."));
        mealsList.add(new Meals(5,"Weighted Sissy Squat",R.drawable.weightedsissysquat,"Weighted sissy squat. No laughing! This is a serious exercise that isolates, and works hard, your quadriceps (vastus lateralis, vastus medialis, vastus intermedius, and rectus femoris). There are no synergistic muscles. Your calves (soleus and gastrocnemius), gluteus maximus, rectus abdominis, and obliques act as stabilizers. Note that I don't highlight stabilizers; I only highlight visible targets and synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Stand holding a plate across your chest with one arm. Grasp something secure, such as a bench or a fixed bar, at waist height with your free hand. Your feet should be shoulder-width apart, flat, and pointing slightly outward.\n" +
                "\n" +
                "Keeping your hips and back straight, bend your knees forward in the direction that your feet are pointing, and allow your body to tilt backward and your heels to rise off the floor. Descend until your knees are almost fully flexed, making sure not to fall backward. Reverse the movement and return to the starting position. Repeat.\n" +
                "\n" +
                "Keep your feet and knees pointing in the same direction. Keep your hips and back straight. Inhale as you descend; exhale as your ascend.\n" +
                "\n" +
                "Notes\n" +
                "\n" +
                "Start with just your body weight. Use additional weight once you have mastered balance. You will see that, even without the plate, this exercise hits your quads hard!\n" +
                "\n" +
                "Even so, use the weighted sissy squat only to warm up, pre-exhaust, or burn out your quads. Do not use it as your primary quad exercise, which should be the squat.\n" +
                "\n" +
                "Instead of starting off with their feet flat on the floor and allowing their heels to rise off the ground (as explained above), some people prefer to keep their heels off the floor throughout the exercise. Try both variations and see which one you prefer."));

        adapter= new MealsAdapter(this,mealsList);
        recyclerView.setAdapter(adapter);
    }
}
