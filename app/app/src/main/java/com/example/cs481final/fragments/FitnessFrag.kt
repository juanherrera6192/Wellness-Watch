package com.example.cs481final.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.cs481final.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FitnessFrag : Fragment(R.layout.fragment_fitness) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val findWorkout = view.findViewById<Button>(R.id.find_workout)
        findWorkout.setOnClickListener {
            val muscleGroup = view.findViewById<Spinner>(R.id.muscle_group)
            val group = muscleGroup.selectedItem
            val workoutList = getWorkout(group.toString())
            val works = workoutList.reduce { str, item -> str + '\n' + item }
            val workouts = view.findViewById<TextView>(R.id.workouts)
            workouts.text = works
        }
    }

    private fun getWorkout(group: String): List<String> {
        return when (group) {
            "Chest" -> listOf("Dumbbell Bench Press" , "Steps: 1.Lie back on a flat bench with a dumbbell in each hand.\n" +
                    "2.Hold the weights at shoulder-level\n" +
                    "3.Then press the weights straight up.  " , " ", "Banded Chest Flye" , "Steps: 1.Wrap a band around your back and hold an end in each hand.\n" +
                    "2.Open your arms so you feel a stretch in your pecs.\n" +
                    "3.Keeping your elbows slightly bent, bring your hands together in front of your chest as if you were giving someone a bear hug.")
            "Back" -> listOf("T Raises", "Steps: 1.Stand with feet hip-width apart and have a dumbbell in each hand.\n" +
                    "2.Slightly bend knees, shift hips back, and lower torso until it's parallel to the floor.\n" +
                    "3.Bring weights together. Keeping arms straight, lift weights up to shoulder height then lower back down." , " ", "Dumbbell Row" , "Steps: 1.Holding a dumbbell in one hand, stand with feet hip-width apart, bend knees, and shift hips back.\n" +
                    "2.Lower your torso until nearly parallel with the ground and place right hand on a wall in front for balance.\n" +
                    "3.Draw weight toward chest by bending left elbow straight up toward the ceiling.")
            "Shoulders" -> listOf("Lateral Raise", "Steps: 1.Stand tall with your feet hip-width apart and your arms at your side, holding a dumbbell in each hand.\n" +
                    "2.Raise your arms to your sides until they’re level with your shoulders. Keep your palms facing downward.\n" +
                    "3.Slowly lower your arms, and repeat for reps." , " ", "Shoulder Shrugs" , "Steps: 1.Hold arms straight down with dumbbells.\n" +
                    "2.Raise both of your shoulders as high as you can, as if you were trying to touch them to your ears.\n" +
                    "3.Keep your head and neck still and relaxed. Repeat.")
            "Arms" -> listOf("Dumbbell Curl", "Steps: 1.Stand by holding a dumbbell in each hand with your arms hanging by your sides.\n" +
                    "2.Keep elbows close to your torso and your shoulders unshrugged.\n" +
                    "3.Keep your upper arms stationary, curl the weights up to shoulder level, rotating your wrists outwards. Bring back down slowly." , " ", "Push Ups" , "Steps: 1.Get into plank position.\n" +
                    "2.Bend your elbows 90 degrees and slowly move your upper body toward the floor while keeping your back straight.\n" +
                    "3.Use your arms to push your body slowly back to your starting position.")
            "Abs" -> listOf("Russian Twists", "Steps: 1.Lie down with your legs bent at the knees.\n" +
                    "2.Elevate your upper body so that it creates a V shape with your thighs.\n" +
                    "3.Twist your torso to the right, and then reverse the motion, twisting it to the left. Repeat." , " ", "Flutter Kicks" , "Steps: 1.Lay on your back, extend your legs at a 45-degree angle.\n" +
                    "2.Keep arms at your sides and head, shoulders, and legs off the ground.\n" +
                    "3.Kick your legs up and down, alternating as you go. Flutter your legs at a pace you can maintain whilst also keeping your core still.")
            "Legs" -> listOf("Squats", "Steps: 1.Stand with feet shoulder-width apart, toes facing front.\n" +
                    "2.Drive the hips back, bend at the knees and ankles, and press your knees slightly open as you squat.\n" +
                    "3.Sit down into a squat position, keeping your heels and toes on the ground, chest up, and shoulders back." , " ", "Lunges" , "Steps: 1.Stand tall with feet hip-width apart and take a big step forward with right leg.\n" +
                    "2.Shift your weight forward so heel hits the floor first and lower your body until right thigh is parallel to the floor and right shin is vertical.\n" +
                    "3.Lightly tap left knee to the floor while keeping weight in right heel and press into right heel to drive back up to starting position.")
            else -> listOf("Cardio", " ", "Stretch")
        }
    }
}