package com.example.gmincluded;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

public class SendMailTask extends AsyncTask {
    public String[] nombre = new String[1];


    private Activity sendMailActivity;
    static String resultado ;

    public SendMailTask(Activity activity) {
        sendMailActivity = activity;

    }

    protected void onPreExecute() {

    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            sendEmail("destino@correo.com","From","asunto a capon","cuerpo a capon",nombre);
            resultado="Asunto "+" enviado correctamente.";

        } catch (Exception e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
            resultado="Ha ocurrido un error: ";
        }
        return null;
    }


    @Override
    public void onProgressUpdate(Object... values) {


    }

    @Override
    public void onPostExecute(Object result) {



    }

    public static boolean sendEmail(String to, String from, String subject,
                                    String message,String[] attachements) throws Exception {
        Mail mail = new Mail();
        if (subject != null && subject.length() > 0) {
            mail.setSubject(subject);
        } else {
            mail.setSubject("ASUNTO");
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


    public static void mostrar(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Resultado del mensaje");
        builder.setMessage(resultado);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });


        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
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