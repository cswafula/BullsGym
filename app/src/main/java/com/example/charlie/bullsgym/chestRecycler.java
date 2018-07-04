package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class chestRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    MealsAdapter adapter;

    List<Meals> mealsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_recycler);

        mealsList=new ArrayList<>();
        recyclerView=findViewById(R.id.chestrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mealsList.add(new Meals(1,"Flat Bench Press",R.drawable.flatbenchpress,"Barbell bench press. A major compound exercise that targets your lower (sternal) pectoralis major, while your upper (clavicular) pectoralis major, anterior deltoid, and triceps brachii work as synergists!\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your back on a flat bench. Bring your feet back, space them wide apart, and plant them firmly on the floor. Arch your back. Using a grip that is a little wider than shoulder width, dismount the bar from the rack. While holding the bar, retract your shoulder blades (push them together) and straighten your wrists. Keeping your elbows tucked in a little, lower the bar to your chest. Press the bar back up to the starting position. Repeat. Inhale as you lower the bar and exhale as you press it back up.\n" +
                "\n" +
                "Tip\n" +
                "\n" +
                "When you press the bar, get your entire body behind the press. Drive from your legs, through your body, and up through your arms. This is helped by planting your feet firmly on the floor (just your toes will do), arching your back, retracting your shoulder blades, keeping your elbows tucked in a little, and keeping your wrists straight. These measures provide a strong foundation from which to press, increase your leverage, and give you a mechanical advantage. Keeping your elbows tucked in also reduces the pressure on your shoulders, which are prone to injury.\n" +
                "\n" +
                "Warning!\n" +
                "\n" +
                "Never use a thumbless grip! If lifting heavy, use a spotter."));
        mealsList.add(new Meals(2,"Incline Bench Press",R.drawable.inclinebenchpress,"Incline bench press. A major compound exercise that targets your lower (sternal) pectoralis major, while your upper (clavicular) pectoralis major, anterior deltoid, and triceps brachii work as synergists!\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your back on a flat bench. Bring your feet back, space them wide apart, and plant them firmly on the floor. Arch your back. Using a grip that is a little wider than shoulder width, dismount the bar from the rack. While holding the bar, retract your shoulder blades (push them together) and straighten your wrists. Keeping your elbows tucked in a little, lower the bar to your chest. Press the bar back up to the starting position. Repeat. Inhale as you lower the bar and exhale as you press it back up.\n" +
                "\n" +
                "Tip\n" +
                "\n" +
                "When you press the bar, get your entire body behind the press. Drive from your legs, through your body, and up through your arms. This is helped by planting your feet firmly on the floor (just your toes will do), arching your back, retracting your shoulder blades, keeping your elbows tucked in a little, and keeping your wrists straight. These measures provide a strong foundation from which to press, increase your leverage, and give you a mechanical advantage. Keeping your elbows tucked in also reduces the pressure on your shoulders, which are prone to injury.\n" +
                "\n" +
                "Warning!\n" +
                "\n" +
                "Never use a thumbless grip! If lifting heavy, use a spotter."));
        mealsList.add(new Meals(3,"Decline Bench Press",R.drawable.declinebenchpress,"Decline bench press. A major compound exercise that targets your lower (sternal) pectoralis major, while your upper (clavicular) pectoralis major, anterior deltoid, and triceps brachii work as synergists!\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Lie on your back on a flat bench. Bring your feet back, space them wide apart, and plant them firmly on the floor. Arch your back. Using a grip that is a little wider than shoulder width, dismount the bar from the rack. While holding the bar, retract your shoulder blades (push them together) and straighten your wrists. Keeping your elbows tucked in a little, lower the bar to your chest. Press the bar back up to the starting position. Repeat. Inhale as you lower the bar and exhale as you press it back up.\n" +
                "\n" +
                "Tip\n" +
                "\n" +
                "When you press the bar, get your entire body behind the press. Drive from your legs, through your body, and up through your arms. This is helped by planting your feet firmly on the floor (just your toes will do), arching your back, retracting your shoulder blades, keeping your elbows tucked in a little, and keeping your wrists straight. These measures provide a strong foundation from which to press, increase your leverage, and give you a mechanical advantage. Keeping your elbows tucked in also reduces the pressure on your shoulders, which are prone to injury.\n" +
                "\n" +
                "Warning!\n" +
                "\n" +
                "Never use a thumbless grip! If lifting heavy, use a spotter."));
        mealsList.add(new Meals(4,"Flat Flys",R.drawable.flatflys,"Exercise: dumbbell fly\n" +
                "\n" +
                "Type: Isolation push\n" +
                "\n" +
                "Target: Lower Pectoralis Major\n" +
                "\n" +
                "Synergists: Upper Pectoralis Major, Anterior Deltoid, Biceps Brachii (short head only)\n" +
                "\n" +
                "Notes\n" +
                "\n" +
                "To preserve elbow health, keep your elbows a little flexed.\n" +
                "\n" +
                "Keep your upper arms internally rotated throughout the movement so that your elbows point out to the sides. This better isolates your lower pectoralis major by bringing the muscle’s origin and insertion into better alignment.\n" +
                "\n" +
                "The steeper the angle of the bench, the less your anterior deltoid will work as a synergist."));
        mealsList.add(new Meals(5,"Machine Chest Press",R.drawable.machinechestpress,"Machine chest press. A compound upper-body exercise that targets your lower pectoralis major. Your upper pectoralis major, anterior deltoid, and triceps brachii act as synergists.\n" +
                "\n" +
                "Instructions\n" +
                "\n" +
                "Sit in the machine with your back pressed against the back rest. Grasp the handles with an overhand grip. Press the levers until your arms are fully extended. Return the levers to the starting position. Repeat. Exhale as you press the levers; inhale as you lower them.\n" +
                "\n" +
                "Question\n" +
                "\n" +
                "Which are better, machines or free weights?\n" +
                "\n" +
                "Lets look at their pros and cons.\n" +
                "\n" +
                "Pros of Machines\n" +
                "\n" +
                "Effectively isolate and guide the target and synergistic muscles through a controlled range of motion.\n" +
                "\n" +
                "Easy to learn.\n" +
                "\n" +
                "Safer for beginners and the elderly.\n" +
                "\n" +
                "Allow you to use heavy weights without assistance.\n" +
                "\n" +
                "Cons of Machines\n" +
                "\n" +
                "Because they isolate the target and synergistic muscles, they reduce the recruitment of stabilizer muscles, especially in your core.\n" +
                "\n" +
                "Often put your body through unnatural or restricted movements.\n" +
                "\n" +
                "The constant repetitive movements without variation can lead to injury.\n" +
                "\n" +
                "Pros of Free Weights\n" +
                "\n" +
                "More freedom of movement.\n" +
                "\n" +
                "Recruit many more stabilizer muscles.\n" +
                "\n" +
                "Get your core more involved.\n" +
                "\n" +
                "Improve your balance and coordination.\n" +
                "\n" +
                "Much more versatile.\n" +
                "\n" +
                "Allow you to strengthen your primal movement patterns and thus develop more functional strength (i.e. strength that can carry over into your daily life and be useful in everyday activities) .\n" +
                "\n" +
                "Cons of Free Weights\n" +
                "\n" +
                "More difficult to learn.\n" +
                "\n" +
                "Can be more dangerous.\n" +
                "\n" +
                "Often need assistance from a spotter.\n" +
                "\n" +
                "Verdict\n" +
                "\n" +
                "Use both machines and free weights, but favor the latter, and focus on compound exercises. These are the most effective at building body-wide muscle, developing functional strength, and improving balance, coordination, and athleticism.\n"));

        adapter= new MealsAdapter(this,mealsList);
        recyclerView.setAdapter(adapter);
    }
}
