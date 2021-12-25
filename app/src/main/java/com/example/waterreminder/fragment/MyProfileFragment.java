package com.example.waterreminder.fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.waterreminder.MainActivity;
import com.example.waterreminder.ProfileActivity;
import com.example.waterreminder.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyProfileFragment extends Fragment {
    private TextView tv_name, tv_age, tv_heigh, tv_weight;
    private Button btn_edit;
    private ImageView img_remind;
    private MainActivity mainActivity;
    private TextView tv_Profile ;

    private IsenData mIsenData;
    public interface IsenData{
        void senDataf(String fweight, String fname, String fage, String fheigh);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mIsenData = (IsenData) getActivity();

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        tv_name = view.findViewById(R.id.tv_name);
        tv_age = view.findViewById(R.id.tv_age);
        tv_heigh = view.findViewById(R.id.tv_heigh);
        tv_weight = view.findViewById(R.id.tv_weight);
        tv_Profile = view.findViewById(R.id.tv_profile);

        btn_edit = view.findViewById(R.id.btn_edit);

        img_remind = view.findViewById(R.id.img_remind);
        mainActivity = (MainActivity) getActivity();
//        settext
        tv_name.setText(mainActivity.getName());
        tv_age.setText(mainActivity.getAge());
        tv_heigh.setText(mainActivity.getHeigh());
        tv_weight.setText(mainActivity.getWeight());



        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senWeight();

//                startActivity(new Intent(getActivity(), ProfileActivity.class));
//                Dialog dialog = new Dialog(getActivity());
//                dialog.setContentView(R.layout.update_data);
//                EditText edtName = dialog.findViewById(R.id.edt_name_update);
//                EditText edtAge = dialog.findViewById(R.id.edt_age_update);
//                EditText edtHeigh = dialog.findViewById(R.id.edt_heigh_update);
//                EditText edtWeight = dialog.findViewById(R.id.edt_weight_update);
//                Button btnUpdate = dialog.findViewById(R.id.btn_update);
//
//                edtName.setText(tv_name.getText());
//                edtAge.setText(tv_age.getText());
//                edtHeigh.setText(tv_heigh.getText());
//                edtWeight.setText(tv_weight.getText());
//
//                dialog.show();
//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tv_name.setText(edtName.getText());
//                        tv_age.setText(edtAge.getText());
//                        tv_heigh.setText(edtHeigh.getText());
//                        tv_weight.setText(edtWeight.getText());
//
//                    }
//                });                        dialog.dismiss();

            }
        });



        return view;
    }


    private void senWeight() {
        String fWeight = tv_weight.getText().toString().trim();
        String fName = tv_name.getText().toString().trim();
        String fAge = tv_age.getText().toString().trim();
        String fHeigh = tv_heigh.getText().toString().trim();
        mIsenData.senDataf(fWeight,fName,fAge,fHeigh);
    }



//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    public void onResume() {
//        super.onResume();
////        setNotification();
//    }


//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    public void setNotification() {
//        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
//        tv_Profile.setText(currentTime);
//
//        if ( currentTime.equals("12:23")|| currentTime.equals("8:00")|| currentTime.equals("9:00") || currentTime.equals("11:00")
//                || currentTime.equals("13:00") || currentTime.equals("15:00") || currentTime.equals("17:00") || currentTime.equals("19:00") )
//        {
//            startNotificition();
//            img_remind.setImageResource(R.drawable.img_bad_tree);
//        } else
//        {
//            cancelNotification();
//            img_remind.setImageResource(R.drawable.img_good_tree);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    public void startNotificition() {
//        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
//        String s = "";
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Notification notification = new NotificationCompat.Builder(getActivity(), "1")
//                .setSmallIcon(R.drawable.ic_water_reminder)
//                .setContentTitle("Time to drink water!!")
//                .setContentText(currentTime)
//                .setColor(getResources().getColor(R.color.notification))
////                .setSound(alarmSound)
//                .build();
//
//        notification.sound =Uri.parse("android.resource://"
//                + getActivity().getPackageName() + "/" + R.raw.notification_police);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(1, notification);
//    }
//
//    public void cancelNotification() {
//        String ns = NOTIFICATION_SERVICE;
//        NotificationManager manager = (NotificationManager)
//                getActivity().getApplicationContext().getSystemService(ns);
//        manager.cancel(1);
//    }
}
