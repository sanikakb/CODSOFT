package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView input_t,result_t;
    MaterialButton bc,b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,
            bOpenbracket,bclosebracket,bdivide,bmultiply,bsum,bsub,bac,bdot,bequals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_t=findViewById(R.id.input_t);
        result_t=findViewById(R.id.result_t);
        assignId(bc,R.id.b_c);
        assignId(b1,R.id.b_1);
        assignId(b2,R.id.b_2);
        assignId(b3,R.id.b_3);
        assignId(b4,R.id.b_4);
        assignId(b5,R.id.b_5);
        assignId(b6,R.id.b_6);
        assignId(b7,R.id.b_7);
        assignId(b8,R.id.b_8);
        assignId(b9,R.id.b_9);
        assignId(b0,R.id.b_0);
        assignId(bOpenbracket,R.id.b_Open_bracket);
        assignId(bclosebracket,R.id.b_close_bracket);
        assignId(bdivide,R.id.b_divide);
        assignId(bmultiply,R.id.b_multiply);
        assignId(bsum,R.id.b_sum);
        assignId(bsub,R.id.b_sub);
        assignId(bac,R.id.b_ac);
        assignId(bdot,R.id.b_dot);
        assignId(bequals,R.id.b_equals);
    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText= button.getText().toString();
        String dataToCalculate=input_t.getText().toString();

        if(buttonText.equals("AC")){
            input_t.setText("");
            result_t.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            input_t.setText(result_t.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate=dataToCalculate+buttonText;
        }

        input_t.setText(dataToCalculate);
        String finalResult= getResult(dataToCalculate);
        if (!finalResult.equals("Error")){
            result_t.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
             String finalresult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
             return finalresult;
        }catch (Exception e){
            return "Error";
        }
    }

}