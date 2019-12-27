package com.cs.ajinmathew.project.movieapp.ui.notifications;

import android.content.Intent;
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

public class NotificationsFragment extends Fragment {
    EditText edSerActor,edSerActress,edSerDirector,edSerDistribuer,edSerProducer,edSerCamera,edSerYear,edSerMovie;
    String movie_name;
    Button btnList,btnUpdate,btnDelete;
    DatabaseReference reference;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        btnList=(Button)root.findViewById( R.id.list );
        btnDelete=(Button)root.findViewById( R.id.delete );
        btnUpdate=(Button)root.findViewById( R.id.update );
        edSerActor=(EditText)root.findViewById( R.id.actorUp );
        edSerActress=(EditText)root.findViewById( R.id.actressUp );
        edSerCamera=(EditText)root.findViewById( R.id.camUp );
        edSerDirector=(EditText)root.findViewById( R.id.directorUp );
        edSerDistribuer=(EditText)root.findViewById( R.id.distUp );
        edSerMovie=(EditText)root.findViewById( R.id.movieUp);
        edSerProducer=(EditText)root.findViewById( R.id.prodUp );
        edSerYear=(EditText)root.findViewById( R.id.yearUp );
        reference= FirebaseDatabase.getInstance().getReference().child( "Movie" );

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                btnList.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        movie_name=edSerMovie.getText().toString().trim();

                        Query query=reference.orderByChild( "movie_name" ).equalTo( movie_name );
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
                btnUpdate.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        movie_name=edSerMovie.getText().toString();

                        Query query=reference.orderByChild( "movie_name" ).equalTo( movie_name );
                        query.addListenerForSingleValueEvent( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot datasnap:dataSnapshot.getChildren())
                                {
                                    datasnap.getRef().child( "actor_name" ).setValue( edSerActor.getText().toString() );
                                    datasnap.getRef().child( "actress_name" ).setValue( edSerActress.getText().toString() );
                                    datasnap.getRef().child( "camera" ).setValue( edSerCamera.getText().toString() );
                                    datasnap.getRef().child( "director" ).setValue( edSerDirector.getText().toString() );
                                    datasnap.getRef().child( "distributer" ).setValue( edSerDistribuer.getText().toString() );
                                    datasnap.getRef().child( "producer" ).setValue( edSerProducer.getText().toString() );
                                    datasnap.getRef().child( "year" ).setValue( edSerYear.getText().toString() );

                                }

                                Toast.makeText( getActivity(),"Data Updated",Toast.LENGTH_LONG ).show();
                            }



                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        } );
                    }
                } );
                btnDelete.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        movie_name=edSerMovie.getText().toString();

                        Query query=reference.orderByChild( "movie_name" ).equalTo( movie_name );
                        query.addListenerForSingleValueEvent( new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot datadelete:dataSnapshot.getChildren())
                                {
                                    datadelete.getRef().removeValue();
                                    Toast.makeText( getActivity(),"Data Deleted",Toast.LENGTH_LONG ).show();
                                    edSerActor.setText( "" );
                                    edSerActress.setText( "" );
                                    edSerCamera.setText( "" );
                                    edSerDirector.setText( "" );
                                    edSerDistribuer.setText( "" );
                                    edSerProducer.setText( "" );
                                    edSerYear.setText( "" );
                                    edSerMovie.setText( "" );
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