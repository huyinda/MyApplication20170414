package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_userName;
    private EditText edit_psw;
    private Button btn_login;
    private Button btn_resiger;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "b491270047e40042e205d2aaa83dfd9c");
        initUI();
        setUI();
    }

    private void initUI() {
        edit_userName = (EditText) findViewById(R.id.edit_userName);
        edit_psw = (EditText) findViewById(R.id.edit_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_resiger = (Button) findViewById(R.id.btn_resiger);
        tv_show = (TextView) findViewById(R.id.tv_show);
    }

    private void setUI() {
        btn_login.setOnClickListener(this);
        btn_resiger.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                    login();
                break;
            case R.id.btn_resiger:
                    register();
                break;
        }
    }

    private void register() {
        String userName = edit_userName.getText().toString();
        String userPsw = edit_psw.getText().toString();
        Person person = new Person();
        person.setUserName(userName);
        person.setUserPsw(userPsw);
        person.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        //添加了修改代码
        String userName = edit_userName.getText().toString();
        String editPsw = edit_psw.getText().toString();
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("90f8eb70ea", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                if(e == null){

                    Toast.makeText(MainActivity.this,"查询到了数据",Toast.LENGTH_SHORT).show();
                    tv_show.setText("当前查询的用户名是" + person.getUserName() + "  对应的密码是" + person.getUserPsw());
                }else{
                    Toast.makeText(MainActivity.this,"查询不到数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
