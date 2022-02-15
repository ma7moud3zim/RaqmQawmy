package com.azim.raqamqawmy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText num;
    private TextView gender;
    private TextView birth;
    private TextView age;
    private TextView city;
    private String raqm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn =(Button) findViewById(R.id.btn);
        num = (EditText) findViewById(R.id.num);

        gender = (TextView) findViewById(R.id.gender);
        birth = (TextView) findViewById(R.id.brthDate);
        age = (TextView) findViewById(R.id.age);
        city = (TextView) findViewById(R.id.city);


        String []arr = new String[28];
        for (String s : arr = new String[]{"القاهرة","الأسكندرية","بورسعيد", "السويس","دمياط","الدقهلية","الشرقية","القليوبية", "كفر الشيخ",  "الغربية",  "المنوفية","البحيرة", "الإسماعيلية", "الجيزة", "بني سويف", "الفيوم", "المنيا", "أسيوط", "سوهاج", "قنا", "أسوان", "الأقصر",  "البحر الأحمر", "الوادى الجديد", "مطروح", "شمال سيناء", "جنوب سيناء", "خارج الجمهورية"}) {};

        String[] finalArr = arr;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                raqm = num.getText().toString();
                if(raqm.length() != 14){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("ضع رقمًا صحيحًا");
                    builder.setMessage("الرقم القومي يجب أن يكون 14 رقم على الأقل");

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                              builder.show();
                }

                else{
                    // determine the gender of the person
                    String gen;
                    if(raqm.charAt(12)%2 ==0) gen = "أنثى" ;
                    else gen =  "ذكر" ;
                    gender.setText(  "الجنس:  " + gen);

                    // Get the birth date from the national number
                    String cen;
                    if(raqm.charAt(0) != '2') cen = "20";
                    else cen = "19";

                    String d = cen+raqm.charAt(1)+raqm.charAt(2)+ " / "+raqm.charAt(3)+raqm.charAt(4)
                            +" / "+raqm.charAt(5)+raqm.charAt(6);
                    birth.setText("تاريخ الميلاد:  " + d);

                    // Get the age of the person
                    String year = cen+raqm.charAt(1)+ raqm.charAt(2);
                    int resT = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(year);
                    String res = String.valueOf(resT);
                    age.setText("العمر:  " + res);

                    // Determine the city of the person
                    int Scode = raqm.charAt(7)-48;
                    System.out.println(Scode);
                    int Pcode = raqm.charAt(8)-48;
                    System.out.println(Pcode);
                    int code = Scode*10 + Pcode;
                    if(code>4 && code<20) code = code - 7;
                    else if(code>20 && code<37) code = code - 8;
                    else if(code>30) code = code - 9;
                    else if(code == 88) code = 28;
                    String cty = finalArr[code];
                    city.setText("المحافظة:  "+ cty);

                    

                }
            }
        });
    }

}