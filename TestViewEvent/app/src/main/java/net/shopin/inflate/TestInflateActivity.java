package net.shopin.inflate;

import android.app.AlertDialog;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import net.shopin.inflate.childpack.SingleInstance;
import net.shopin.testviewevent.R;

/**
 * 主要讲述setContentView和LayoutInflate渲染的流程
 */
public class TestInflateActivity extends AppCompatActivity {

    private String TAG = "TestInflateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_inflate);
        LayoutInflater inflater = LayoutInflater.from(this);
//        View mainView = inflater.inflate(R.layout.activity_test_inflate, null, true);
        View mainView = inflater.inflate(R.layout.activity_test_inflate, (ViewGroup) findViewById(android.R.id.content));
        XmlResourceParser resourceParser = getResources().getLayout(R.layout.activity_test_inflate);
        AttributeSet set = Xml.asAttributeSet(resourceParser);
        View decorView = getWindow().getDecorView();
        View viewById = decorView.findViewById(android.R.id.content);
        View viewById1 = viewById.findViewById(android.R.id.content);
        System.out.println(JSONObject.toJSONString("888"));
        if (SingleInstance.getInstance().equals("1213")) {
            Toast.makeText(TestInflateActivity.this, "onCreate: equals", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(TestInflateActivity.this, "onCreate: !equals", Toast.LENGTH_SHORT).show();
        }
        if (InflateInstance.getInflateInstance().equals("1213")) {
            Toast.makeText(TestInflateActivity.this, "onCreate: equals", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(TestInflateActivity.this, "onCreate: !equals", Toast.LENGTH_SHORT).show();
        }
        //startActivity(new Intent(this, TestPackActivity.class));
        AlertDialog dialog = new AlertDialog.Builder(this, R.style.dialog).create();
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_bg, null);
        dialog.setView(v);
        dialog.show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
