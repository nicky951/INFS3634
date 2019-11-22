package com.example.infs3634country;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link quiz.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quiz extends Fragment {

    private TextView question;
    private EditText editText;
    private Button check;
    private TextView streak;

    public quiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        question = view.findViewById(R.id.question);
        editText = view.findViewById(R.id.answer);
        check = view.findViewById(R.id.check);
        streak= view.findViewById(R.id.point);

        CountryDatabase db = Room.databaseBuilder(getContext(), CountryDatabase.class , "database_name").allowMainThreadQueries().build();

        final List<Country> countryBank = db.countryDao().getCountry();

        final Random random = new Random();

        final int countryRandom = random.nextInt(countryBank.size() - 1);
        int questionRandom = random.nextInt(4 - 1) + 1;

        final String[] questions = {""};
        final String[] answers = {""};

        if (questionRandom == 1) {
            questions[0] = "What is the Capital of " + countryBank.get(countryRandom).getName() + " ?";
            answers[0] = countryBank.get(countryRandom).getCapital();
        } else if (questionRandom == 2) {
            questions[0] = "What is the Population of " + countryBank.get(countryRandom).getName() + " ?";
            answers[0] = countryBank.get(countryRandom).getPopulation();
        } else if (questionRandom == 3) {
            questions[0] = countryBank.get(countryRandom).getName()  + " is part of which region?";
            answers[0] = countryBank.get(countryRandom).getRegion();
        }

        question.setText(questions[0]);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userAnswer = editText.getText().toString();

                int streakscore = 0;
                int randomQuestion;
                int randomCountry;

                if(!userAnswer.equalsIgnoreCase(answers[0])) {

                    Toast.makeText(getContext(),"The answer is " + answers[0], Toast.LENGTH_LONG).show();
                    streak.setText(String.valueOf(streakscore));

                    randomCountry = random.nextInt(countryBank.size() - 1);
                    randomQuestion = random.nextInt(4 - 1) + 1;

                    if (randomQuestion == 1) {
                        questions[0] = "What is the Capital of " + countryBank.get(randomCountry).getName() + " ?";
                        answers[0] = countryBank.get(randomCountry).getCapital();
                    } else if (randomQuestion == 2) {
                        questions[0] = "What is the Population of " + countryBank.get(randomCountry).getName() + " ?";
                        answers[0] = countryBank.get(randomCountry).getPopulation();
                    } else if (randomQuestion == 3) {
                        questions[0] = countryBank.get(randomCountry).getName()  + " is part of which region?";
                        answers[0] = countryBank.get(randomCountry).getRegion();
                    }

                    question.setText(questions[0]);
                    editText.setText("");

                } else if (userAnswer.equalsIgnoreCase(answers[0])){
                    Toast.makeText(getContext(),"Correct!", Toast.LENGTH_SHORT).show();
                    streakscore += 1;
                    streak.setText(String.valueOf(streakscore));

                    randomCountry = random.nextInt(countryBank.size() - 1);
                    randomQuestion = random.nextInt(4 - 1) + 1;

                    if (randomQuestion == 1) {
                        questions[0] = "What is the Capital of " + countryBank.get(randomCountry).getName() + " ?";
                        answers[0] = countryBank.get(randomCountry).getCapital();
                    } else if (randomQuestion == 2) {
                        questions[0] = "What is the Population of " + countryBank.get(randomCountry).getName() + " ?";
                        answers[0] = countryBank.get(randomCountry).getPopulation();
                    } else if (randomQuestion == 3) {
                        questions[0] = countryBank.get(randomCountry).getName()  + " is part of which region?";
                        answers[0] = countryBank.get(randomCountry).getRegion();
                    }

                    question.setText(questions[0]);
                }
            }
        });

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static quiz newInstance(String param1, String param2) {
        quiz fragment = new quiz();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
