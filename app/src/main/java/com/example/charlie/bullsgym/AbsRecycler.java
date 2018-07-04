package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AbsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    MealsAdapter adapter;

    List<Meals> mealsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_recycler);

        mealsList=new ArrayList<>();
        recyclerView=findViewById(R.id.absrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mealsList.add(new Meals(1,"Side Bend On Stability Ball",R.drawable.sidebendonstabilityball,"Side bend on stability ball. An auxiliary core exercise that targets your internal and external obliques, strengthens your core, and improves your spinal flexibility. A variety of deep core muscles act as synergists, such as your quadratus lumborum (a lower-back lateral flexor), psoas major (a hip flexor), iliocastalis lumborum, and iliocastalis thoracis (both extensors, lateral flexors, and rotators of your spine) .\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your side on the ball with the ball under your lower waist and hip. Extend your feet out straight, straighten your body, and place your hands behind your neck. Raise your upper body off the ball by laterally flexing your waist. Hold for a count of two. Lower your upper body to the starting position. Repeat for the desired number of repetitions, before performimg the exercise on your other side.\n" +
                "\n" +
                "Do not raise and lower your upper body beyond feeling a mild stretch in your side. Do not strain your neck; keep it level with your body. To increase stability, spread your legs out wide, one in front of you and the other behind you. To make the exercise easier, position your hip low on the ball. To make it more difficult, either position your hip high on the ball or hold a weight plate behind your head.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "Your lower (lumbar) spine is built for stability, whereas your upper (thoracic) spine (which includes your rib cage) is built for mobility. When most people perform the side bend, most of the movement is in their lumbar spine. When you perform the exercise, make sure that there is also movement in your thoracic spine."));
        mealsList.add(new Meals(2,"High Reverse Plank",R.drawable.highrevereplank,"High reverse plank. A bodyweight core and lower-body exercise. There is no target muscle. Your gluteus maximus, erector spinae (which run up your spine), hamstrings, rectus abdominis, obliques, posterior deltoid, and lateral deltoid all act to stabilize your body. Since there is no movement, the muscles are exercised isometrically.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Sit on the floor with your legs extended and held together. Place your hands behind you, on the floor. Your fingers can point forward, sideways, backward -- whichever is most comfortable. Raise your hips off the floor until your body is perfecly straight, and hold this position, breathing naturally. Your arms should be vertical. Do not allow your shoulders to hunch or your hips to sag.\n" +
                "\n" +
                "Tips\n" +
                "\n" +
                "If your wrists hurt, support your body on your elbows instead of on your hands. However, the exercise will then become the standard or low reverse plank.\n" +
                "\n" +
                "To make the exercise more difficult, either place a weight on your abdomen or elevate your feet.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "The high reverse plank is an effective isometric bodyweight exercise for strengthening your core and the key muscles of your posterior chain (erector spinae, gluteus maximus, and hamstrings). Most people only perform the front plank. It's a great idea to superset the front plank with the reverse plank. In other words, after performing the front plank, move straight into the reverse plank without resting. This is known as the 2-way plank"));
        mealsList.add(new Meals(3,"Captain's Chair Raise",R.drawable.captainschairraise,"Captain's chair leg and hip raise. A compound hip-and-waist-flexion exercise that targets your rectus abdominis. The other labeled muscles, along with your adductor brevis, act as synergists.\n" +
                "\n" +
                "This is a brilliant core exercise that should be included in everyone's training program!\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Get into the captain's chair with your forearms on the pads and your hands on the handles. Press your lower back against the back support.\n" +
                "\n" +
                "Keeping your feet together, raise your knees towards your chest by flexing your knees, hips, AND waist. Try to hold the end position for a second. Lower your feet to the starting position. Repeat.\n" +
                "\n" +
                "Exhale as you raise your legs; inhale as you lower them. Keep your lower back pressed against the back support. Keep the movement slow and controlled; don't use momentum.\n" +
                "\n" +
                "Notes\n" +
                "\n" +
                "Your rectus abdominis and obliques only act as stabilizers unless you flex your waist and raise your hips at the top of the movement. Without waist flexion, your iliopsoas is the target.\n" +
                "\n" +
                "Keeping your lower back pressed against the back support is very important. Your legs are raised by your hip flexors, especially your iliopsoas. Your iliopsoas originates on your lower back/spine and attaches to your thigh bone. To raise your legs, your iliopsoas must pull on your lower back, forcing it to extend. If you do not support your lower back, the constant tugging it experiences could leave you with lower-back problems."));
        mealsList.add(new Meals(4,"Machine Leg Raise Crunch",R.drawable.machinelegraisecrunch,"Machine leg raise crunch. An auxiliary core exercise that targets your rectus abdominis, while your internal and external obliques act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Adjust the machine for optimal comfort and range of motion. Sit in the machine, place your feet under the foot pads, and grasp the handles by the sides of your head. Keeping your back pressed against the back rest, flex your abdomen and pelvis to pull the handles down and the foot pads up into a crunch. Relax your abdomen and pelvis to return to the starting position. Repeat.\n" +
                "\n" +
                "Exhale as you crunch; inhale as you relax. Don't hunch your back. Don't pull with your arms; keep them stiff. All of the movement should be produced by abdominal and pelvic flexion.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "This is a machine leg raise crunch and not just a machine crunch because the back segment of the machine moves forward (crunch) while the seat moves upward (leg raise). With other ab machines, only the back segment moves forward (machine crunch) .\n" +
                "\n" +
                "I am not a fan of crunch machines. Core training is very important. Your \"core\" refers to all of the muscles in your torso, especially the deep ones that attach to your spine and pelvis. Your core is important for stabilizing your body, especially when you lift heavy. It is important for transferring weight/energy from one side of your body to the other, or from your legs to your arms. Your core is also where you generate most of your power. Isolating your abs and obliques with a machine is not effective core training! Proper core training involves performing major compound and functional exercises, such as squats, deadlifts, clean and jerks, lunges, weighted push-ups, pull-ups, wheel rollouts, bicycle crunches, hanging leg and hip raises, cable woodchops, and lying side hip raises."));
        mealsList.add(new Meals(5,"Reverse Crunch",R.drawable.reversecrunch,"Reverse crunch. An auxiliary core exercise that targets your rectus abdominis. Your iliopsoas (hip flexors) and internal and external obliques work as synergists.\n" +
                "\n" +
                "Note\n" +
                "\n" +
                "In one study, the American Council on Exercise (ACE) found that compared with the standard crunch, the reverse crunch produces 9% more mean activity in the abs and 140% more mean activity in the obliques! So don't waste your time with the crunch! In fact, the reverse crunch isn't particularly good, either. "));
        mealsList.add(new Meals(6,"Vertical Leg Crunch",R.drawable.verticallegcrunch,"Vertical leg crunch. A brilliant exercise for your rectus abdominis (learn why below)! Your internal and external obliques act as synergists.\n" +
                "\n" +
                "Lie supine (on your back) with your lower back pressed against the floor. Raise your legs straight up into the air. Then, try to touch your ankles with your hands by contracting your abs and flexing your waist. Hold for a count of two, before returning to the starting position in a controlled manner. Repeat.\n" +
                "\n" +
                "Keep your head level with your torso. You can bend your knees a little and/or cross your legs if it's easier.\n" +
                "\n" +
                "In one classic study (ACE, 2001), the vertical leg crunch was found to produce 29% more mean activity in the rectus abdominis than the regular crunch! The exercise ball crunch was found to be even more effective, producing 39% more mean activity. But of all the 13 exercises that were compared, the very best were found to be the captain's chair and the bicycle crunch, producing 112% and 148% more mean activity in the rectus abdominis than the regular crunch! These last two exercises were also found to be the best for your obliques. Therefore, stop wasting time with the regular crunch! For your abs and obliques, use the bicycle crunch, captain's chair, exercise ball crunch, and vertical leg crunch."));

        adapter= new MealsAdapter(this,mealsList);
        recyclerView.setAdapter(adapter);
    }
}
