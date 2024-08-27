package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.AccoutReqDataset;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.TemplateParams;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kotlin.jvm.internal.Lambda;

public class AccountRequestAdapter extends RecyclerView.Adapter<AccountReqViewHolder> {
    ArrayList<AccoutReqDataset> arrayList;
    Context context;
    ArrayList<String> keys;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("pendingUser");
    DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child("activeUser");

    public AccountRequestAdapter(ArrayList<AccoutReqDataset> arrayList, Context context, ArrayList<String> keys) {
        this.arrayList = arrayList;
        this.context = context;
        this.keys = keys;
    }

    @NonNull
    @Override
    public AccountReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountReqViewHolder(LayoutInflater.from(context).inflate(R.layout.row,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountReqViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AccoutReqDataset dataset = arrayList.get(position);

        holder.nameUser.setText(dataset.getName());
        holder.cleass.setText(dataset.getCleass());
        holder.phone.setText(dataset.getNumber());
        holder.idUser.setText(dataset.getId());
        holder.roll.setText(dataset.getRoll());
        holder.section.setText(dataset.getSection());
        holder.approve.setOnClickListener((v)->{
            AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                    .setTitle("Are you sure?")
                    .setMessage("Are you sure that you want to approve the account?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            auth.createUserWithEmailAndPassword(dataset.getEmail(), dataset.getPassword()).addOnSuccessListener((s)->{
                                Toast.makeText(context, "User created successfully!", Toast.LENGTH_SHORT).show();
                                userref.push().setValue(dataset).addOnSuccessListener((sn)->{
                                    reference.child(keys.get(position)).setValue(null);
                                }).addOnFailureListener((e)->{
                                    Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }).addOnFailureListener((e)->{
                                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            dialog.show();
        });
        holder.reject.setOnClickListener((v)->{
            final AlertDialog.Builder dia = new AlertDialog.Builder(context);
            dia.setTitle("Are you sure?");
            dia.setMessage("Are you sure that you want reject the request");
            dia.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    reference.child(keys.get(position)).setValue(null).addOnSuccessListener((s)->{
                        Toast.makeText(context, "Request Rejected", Toast.LENGTH_SHORT).show();
                        String email  = "rcbscapp@gmail.com";
                        String pass = "shobarsherasadidbro";
                        Properties properties = new Properties();
                        properties.put("mail.smtp.auth","true");
                        properties.put("mail.smtp.starttls.enable","true");
                        properties.put("mail.smtp.host","smtp.gmail.com");
                        properties.put("mail.smtp.port","587");

                        Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(email,pass);
                            }
                        });
                        Message message = new MimeMessage(session);
                        try {
                            message.setFrom(new InternetAddress(email));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dataset.getEmail()));
                            message.setSubject("test");
                            message.setText("test");
                            new SendEmailNew().execute(message);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
            dia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dia.show();
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private class SendEmailNew extends AsyncTask<Message,String,String> {
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            }
            catch (MessagingException e){
                return "Error";
            }
        }
    }
}
