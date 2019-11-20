package com.example.notas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notas.Clases.NumberTextWatcherForThousand;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FragmentInicio extends Fragment {

    View view;
    EditText pp, sp, tp, ex;
    TextView p1, p2, p3, exa, notaF, falta;
    Button calcular, limpiar;
    LinearLayout layoutNotas, layoutFinal;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inicio, null);

        pp = (EditText) view.findViewById(R.id.txtPP);
        sp = (EditText) view.findViewById(R.id.txtSP);
        tp = (EditText) view.findViewById(R.id.txtTP);
        ex = (EditText) view.findViewById(R.id.txtEX);
        p1 = (TextView) view.findViewById(R.id.primerPrevio);
        p2 = (TextView) view.findViewById(R.id.segundoPrevio);
        p3 = (TextView) view.findViewById(R.id.tercerPrevio);
        exa = (TextView) view.findViewById(R.id.examen);
        notaF = (TextView) view.findViewById(R.id.notaFinal);
        calcular = (Button) view.findViewById(R.id.btnCalcular);
        limpiar = (Button) view.findViewById(R.id.btnLimpiar);
        layoutNotas = (LinearLayout) view.findViewById(R.id.layoutNotas);
        layoutFinal = (LinearLayout) view.findViewById(R.id.layoutFinal);
        layoutFinal.setVisibility(View.INVISIBLE);
        layoutNotas.setVisibility(View.INVISIBLE);

        /****
        ====== Lineas de codigo para formatear el texto y colocar punto decimal ======
        pp.addTextChangedListener(new NumberTextWatcherForThousand(pp));
        NumberTextWatcherForThousand.trimCommaOfString(pp.getText().toString());
        *****/

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularNotas();
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarPantalla();
            }
        });
        return view;
    }

    private void calcularNotas(){
        float suma = 0f;
        float notaFinal = 0f;
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);

        if (!pp.getText().toString().isEmpty()){
            if (sp.getText().toString().isEmpty() && tp.getText().toString().isEmpty() && ex.getText().toString().isEmpty()) {
                if(Float.parseFloat(pp.getText().toString()) >= 0 && Float.parseFloat(pp.getText().toString()) <= 5){
                    suma = Float.parseFloat(pp.getText().toString());
                    notaFinal = (12 - suma) / 3;
                    p1.setText(formato1.format(Float.parseFloat(pp.getText().toString())));
                    p2.setText(String.valueOf(formato1.format(notaFinal)));
                    p3.setText(String.valueOf(formato1.format(notaFinal)));
                    exa.setText(String.valueOf(formato1.format(notaFinal)));
                    layoutNotas.setVisibility(View.VISIBLE);
                    layoutFinal.setVisibility(View.INVISIBLE);
                } else Toast.makeText(getActivity(), "Digite un numero del 1 al 5", Toast.LENGTH_SHORT).show();
            } else if(tp.getText().toString().isEmpty() && ex.getText().toString().isEmpty()){
                if(Float.parseFloat(pp.getText().toString()) >= 0 && Float.parseFloat(pp.getText().toString()) <= 5
                        && Float.parseFloat(sp.getText().toString()) >= 0 && Float.parseFloat(sp.getText().toString()) <= 5){
                    suma = Float.parseFloat(pp.getText().toString()) + Float.parseFloat(sp.getText().toString());
                    notaFinal = (12 - suma) / 2;
                    p1.setText(formato1.format(Float.parseFloat(pp.getText().toString())));
                    p2.setText(formato1.format(Float.parseFloat(sp.getText().toString())));
                    p3.setText(String.valueOf(formato1.format(notaFinal)));
                    exa.setText(String.valueOf(formato1.format(notaFinal)));
                    layoutNotas.setVisibility(View.VISIBLE);
                    layoutFinal.setVisibility(View.INVISIBLE);
                } else Toast.makeText(getActivity(), "Digite un numero del 1 al 5", Toast.LENGTH_SHORT).show();
            } else if(ex.getText().toString().isEmpty()){
                if(Float.parseFloat(pp.getText().toString()) >= 0 && Float.parseFloat(pp.getText().toString()) <= 5
                        && Float.parseFloat(sp.getText().toString()) >= 0 && Float.parseFloat(sp.getText().toString()) <= 5
                        && Float.parseFloat(tp.getText().toString()) >= 0 && Float.parseFloat(tp.getText().toString()) <= 5){
                    suma = Float.parseFloat(pp.getText().toString()) + Float.parseFloat(sp.getText().toString()) + Float.parseFloat(tp.getText().toString());
                    notaFinal = (12 - suma) / 1;
                    p1.setText(formato1.format(Float.parseFloat(pp.getText().toString())));
                    p2.setText(formato1.format(Float.parseFloat(sp.getText().toString())));
                    p3.setText(formato1.format(Float.parseFloat(tp.getText().toString())));
                    exa.setText(String.valueOf(formato1.format(notaFinal)));
                    layoutNotas.setVisibility(View.VISIBLE);
                    layoutFinal.setVisibility(View.INVISIBLE);
                } else Toast.makeText(getActivity(), "Digite un numero del 1 al 5", Toast.LENGTH_SHORT).show();
            } else if (!pp.getText().toString().isEmpty() && !sp.getText().toString().isEmpty() && !tp.getText().toString().isEmpty() && !ex.getText().toString().isEmpty()){
                if(Float.parseFloat(pp.getText().toString()) >= 0 && Float.parseFloat(pp.getText().toString()) <= 5
                        && Float.parseFloat(sp.getText().toString()) >= 0 && Float.parseFloat(sp.getText().toString()) <= 5
                        && Float.parseFloat(tp.getText().toString()) >= 0 && Float.parseFloat(tp.getText().toString()) <= 5
                        && Float.parseFloat(ex.getText().toString()) >= 0 && Float.parseFloat(ex.getText().toString()) <= 5){
                    float p = Float.parseFloat(formato1.format(Float.parseFloat(pp.getText().toString())));
                    float s = Float.parseFloat(formato1.format(Float.parseFloat(sp.getText().toString())));
                    float t = Float.parseFloat(formato1.format(Float.parseFloat(tp.getText().toString())));
                    float e = Float.parseFloat(formato1.format(Float.parseFloat(ex.getText().toString())));

                    float setenta = ((p + s + t) / 3) * 0.7f;
                    notaFinal = setenta + (e * 0.3f);
                    layoutFinal.setVisibility(View.VISIBLE);
                    layoutNotas.setVisibility(View.INVISIBLE);
                    notaF.setText(String.valueOf(formato1.format(notaFinal)));
                    if(notaFinal >= 2.96) {
                        Toast.makeText(getActivity(), "Felicitaciones has pasado la materia. :)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Has perdido la materia. :'(", Toast.LENGTH_SHORT).show();
                        float faltante = 3 - notaFinal;
                        falta.setText(String.valueOf(formato1.format(faltante)));
                    }
                } else Toast.makeText(getActivity(), "Digite un numero del 1 al 5", Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(getActivity(), "Debe de llenar el primer previo", Toast.LENGTH_SHORT).show();
    }

    private void limpiarPantalla(){
        pp.setText("");
        sp.setText("");
        tp.setText("");
        ex.setText("");
        layoutNotas.setVisibility(View.INVISIBLE);
    }
}
