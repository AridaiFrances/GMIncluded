package com.example.gmincluded;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class SendMailTask extends AsyncTask {
    public String[] adjuntos;
    private String asunto;
    private String contenido;


    private Activity sendMailActivity;


    public SendMailTask(Activity activity, String asunto, String contenido) {
        sendMailActivity = activity;

        this.asunto = asunto;
        this.contenido = contenido;

    }

    protected void onPreExecute() {

    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            sendEmail("correoDestino@gmail.com","From",asunto,contenido,adjuntos);


        } catch (Exception e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
            Toast.makeText(sendMailActivity, "Oops, algo no ha ido bien!", Toast.LENGTH_LONG).show();
        }

        return null;
    }


    @Override
    public void onProgressUpdate(Object... values) {


    }

    @Override
    public void onPostExecute(Object result) {
        Toast.makeText(sendMailActivity, "Mensaje enviado correctamente!", Toast.LENGTH_LONG).show();


    }

    public static boolean sendEmail(String to, String from, String subject,
                                    String message,String[] attachements) throws Exception {
        Mail mail = new Mail();
        if (subject != null && subject.length() > 0) {
            mail.setSubject(subject);
        } else {
            mail.setSubject("Correo recibido desde app GMIncluded");
        }

        if (message != null && message.length() > 0) {
            mail.setBody(message);
        } else {
            mail.setBody(" ");
        }

        mail.setTo(new String[] {to});

        if (attachements != null) {
            for (String attachement : attachements) {
                mail.addAttachment(attachement);
            }
        }
        return mail.send();
    }


    public static interface OnIntegerChangeListener
    {
        public void onIntegerChanged(int newValue);
    }

    public static class ObservableInteger
    {
        private OnIntegerChangeListener listener;

        private int value;

        public void setOnIntegerChangeListener(OnIntegerChangeListener listener)
        {
            this.listener = listener;
        }

        public int get()
        {
            return value;
        }

        public void set(int value)
        {
            this.value = value;

            if(listener != null)
            {
                listener.onIntegerChanged(value);
            }
        }
    }

}