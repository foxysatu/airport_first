package mik.maev.yt_firebase_practic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Reis extends AppCompatActivity {
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private String str;
    private ArrayList<String> list;
    private ArrayList<TextView> text_list;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reis);
        sv =  (ScrollView) this.findViewById(R.id.scrollNews);
        init();
    }

    public void back_activity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void outReis(View view) {
        Intent intent = new Intent(this, BackAir.class);
        startActivity(intent);
    }

    private void init()
    {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb()
    {
        try {
            doc = Jsoup.connect("https://avia.travel.ru/timetable/domodedovo.html").get();
            Elements table = doc.getElementsByTag("tbody");
            Element fromTable = table.get(0);
            Elements ourTabl = fromTable.children();


            list = new ArrayList<>();
            for(int i=0; i<ourTabl.size()-1; i++) {
                Element ourTable1 = ourTabl.get(i);
                Elements inTable = ourTable1.children();
                Element numberReis = inTable.get(0);
                Element Put = inTable.get(1);
                Element time = inTable.get(3);
                String str = numberReis.text() + "       " + Put.text() + "      " + time.text();

                list.add(str);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void update(){
        LinearLayout ll = (LinearLayout) this.findViewById(R.id.ll);
        Log.d("MyLog", "news :" + list.size());
        text_list = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            text_list.add(new TextView(this));
        }
        for(int i = 0; i < text_list.size(); i++){
            text_list.get(i).setText(list.get(i));
            text_list.get(i).setTextSize(14);
            text_list.get(i).setPadding(20,5,15,20);
            text_list.get(i).setBackgroundResource(R.drawable.back);
            ll.addView(text_list.get(i));
        }
        Log.d("MyLog", "news :" + text_list.get(3));

    }
}