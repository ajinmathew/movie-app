package com.cs.ajinmathew.project.movieapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cs.ajinmathew.project.movieapp.Movie;
import com.cs.ajinmathew.project.movieapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    EditText edMovieName,edRelesedYear,edActor,edActress,edDirector,edProducer,edCamera,edDistributer;
    Button btnInsert;

    DatabaseReference databaseReference;
    Movie movie;




    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        edMovieName=(EditText)root.findViewById( R.id.movienameAdd );
        edRelesedYear=(EditText)root.findViewById( R.id.releasedyearAdd );
        edActor=(EditText)root.findViewById( R.id.actornameAdd );
        edActress=(EditText)root.findViewById( R.id.actressnameAdd );
        edDirector=(EditText)root.findViewById( R.id.directornameAdd );
        edProducer=(EditText)root.findViewById( R.id.producerAdd );
        edCamera=(EditText)root.findViewById( R.id.cameraAdd );
        edDistributer=(EditText)root.findViewById( R.id.distributerAdd );
        btnInsert=(Button) root.findViewById( R.id.insert );

        databaseReference= FirebaseDatabase.getInstance().getReference().child( "Movie" );


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                btnInsert.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        movie=new Movie(  );
                        movie.setActor_name( edActor.getText().toString().trim() );
                        movie.setActress_name( edActress.getText().toString().trim() );
                        movie.setCamera( edCamera.getText().toString().trim() );
                        movie.setDirector( edDirector.getText().toString().trim() );
                        movie.setDistributer( edDistributer.getText().toString().trim() );
                        movie.setMovie_name( edMovieName.getText().toString().trim() );
                        movie.setProducer( edProducer.getText().toString().trim() );
                        movie.setYear( edRelesedYear.getText().toString().trim() );

                        databaseReference.push().setValue( movie ).addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText( getActivity(),"Data Inserted Successfully",Toast.LENGTH_LONG ).show();
                                }else {
                                    Toast.makeText( getActivity(),"insertion Failed",Toast.LENGTH_LONG ).show();
                                }
                            }
                        } );
                    }
                } );

            }
        });
        return root;
    }
}