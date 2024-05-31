package com.ulp.logininmo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Llamada extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String numeroTelefono = "2664731516";

        Intent intentLlamada = new Intent(Intent.ACTION_CALL);
        intentLlamada.setData(Uri.parse(numeroTelefono));
        intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(intentLlamada);
        } catch (SecurityException e) {
            Toast.makeText(context, "Permiso de llamada no concedido", Toast.LENGTH_SHORT).show();
        }
    }
}

