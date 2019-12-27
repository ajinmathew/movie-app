package com.cs.ajinmathew.project.movieapp.ui.dashboard;

import android.app.DownloadManager;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    EditText edSerActor,edSerActress,edSerDirector,edSerDistribuer,edSerProducer,edSerCamera,edSerYear,edSerMovie;

    Button btnSearch;
    DatabaseReference reference;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnSearch=(Button)root.findViewById( R.id.search );
        edSerActor=(EditText)root.findViewById( R.id.actorSer );
        edSerActress=(EditText)root.findViewById( R.id.actressSer );
        edSerCamera=(EditText)root.findViewById( R.id.camSer );
        edSerDirector=(EditText)root.findViewById( R.id.directorSer );
        edSerDistribuer=(EditText)root.findViewById( R.id.distSer );
        edSerMovie=(EditText)root.findViewById( R.id.movieSer );
        edSerProducer=(EditText)root.findViewById( R.id.prodSer );
        edSerYear=(EditText)root.findViewById( R.id.yearSer );
        reference= FirebaseDatabase.getInstance().getReference().child( "Movie" );

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                btnSearch.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String moviename=edSerMovie.getText().toString().trim();

                        Query query=reference.orderByChild( "movie_name" ).equalTo( moviename );
                        query.addListenerForSingleValueEvent( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnap:dataSnapshot.getChildren())
                                {
                                    Movie movie=dataSnap.getValue(Movie.class);
                                    edSerActor.setText( movie.actor_name );
                                    edSerActress.setText( movie.actress_name );
                                    edSerCamera.setText( movie.camera );
                                    edSerDirector.setText( movie.director );
                                    edSerDistribuer.setText( movie.distributer );
                                    edSerProducer.setText( movie.producer );
                                    edSerYear.setText( movie.year );
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        } );
                    }
                } );
            }
        });
        return root;
    }
}