package com.example.changeposition.sprouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.aptlib.di.InjectActivity;
import com.example.aptlib.di.InjectView;
import com.example.testcomplier.MyFindViewByMainActivity;

//@FunPath(funName = "main")
@InjectActivity
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.hello_tv)
    public TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyFindViewByMainActivity.findById(this);
        helloTv.setText("哈哈哈哈哈");
        //HelloWorld.main(new String[]{});
    }
}
