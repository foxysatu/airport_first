package mik.maev.yt_firebase_practic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class News extends AppCompatActivity {

    private ArrayList<String> list;
    private ArrayList<TextView> text_list;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        sv =  (ScrollView) this.findViewById(R.id.scrollNews);
        init();
    }



    private void init()
    {
        Runnable runnable = new Runnable() {
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
        Thread secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb() {
        try {
            Document doc = Jsoup.connect("https://vdomodedovo.info/novosti").get();
            Elements table = doc.getElementsByTag("h3");

            list = new ArrayList<>( );
            for (int i = 0; i < table.size() - 1; i++) {
                Element fromTable = table.get(i);
                Elements ourTabl = fromTable.children();
                String str = ourTabl.text();
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
