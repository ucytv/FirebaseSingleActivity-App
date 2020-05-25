package com.ucy.firebaseapp.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ucy.firebaseapp.MainActivity;
import com.ucy.firebaseapp.Model.User;
import com.ucy.firebaseapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ImageView exit,back;
    private TextInputEditText name, nick, mail, pass, phone;
    private DatabaseReference database;
    private TextInputLayout layout;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        typeCasting(view);
        setDisabled();
        getAndSetData();

        return view;
    }

    private void setDisabled() {
        name.setEnabled(false);
        nick.setEnabled(false);
        mail.setEnabled(false);
        pass.setEnabled(false);
        phone.setEnabled(false);
    }

    private void getAndSetData() {
        final String mNick = ((MainActivity) getActivity()).getTinyNick();
        Toast.makeText(getActivity(), "" + mNick, Toast.LENGTH_SHORT).show();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot info : dataSnapshot.getChildren()) {
                    if (mNick.equals(info.getKey())) {
                        User user = info.getValue(User.class);
                        name.setText(user.getName());
                        nick.setText(mNick);
                        mail.setText(user.getMail());
                        pass.setText(user.getPass());
                        phone.setText(user.getPhone());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void typeCasting(View view) {
        name = view.findViewById(R.id.show_name);
        nick = view.findViewById(R.id.show_nick);
        mail = view.findViewById(R.id.show_mail);
        pass = view.findViewById(R.id.show_pass);
        phone = view.findViewById(R.id.show_phone);

        database = FirebaseDatabase.getInstance().getReference().child("users");

        exit = view.findViewById(R.id.button_exit);
        back = view.findViewById(R.id.button_back);
        exit.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.button_back:
                ((MainActivity) getActivity()).goToFragment("login");
                break;
            case R.id.button_exit:
                ((MainActivity) getActivity()).clearTinyData();
                ((MainActivity) getActivity()).goToFragment("login");
                break;

        }
    }
}
