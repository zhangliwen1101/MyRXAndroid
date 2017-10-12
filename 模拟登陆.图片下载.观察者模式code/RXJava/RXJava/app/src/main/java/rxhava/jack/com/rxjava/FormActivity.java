package rxhava.jack.com.rxjava;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rxhava.jack.com.rxjava.rxjavaform.LoginUtils;

public class FormActivity extends AppCompatActivity {
    private  final  static  String TAG = FormActivity.class.getSimpleName();
    @BindView(R.id.e_username)
    EditText e_username;
    @BindView(R.id.e_password)
    EditText e_password;
    private LoginUtils loginUtils;
    ProgressDialog pDialog;
    private String ServicePath = "http://192.168.100.21:8080/Login/servlet/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        loginUtils= new LoginUtils();
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("login...");
    }

    public  void  onclick(View view){
        switch (view.getId()){
            case R.id.login:
                Map<String,String> params = new HashMap<>();
                params.put("username",e_username.getText().toString().trim());
                params.put("password",e_password.getText().toString().trim());
                loginUtils.login(ServicePath,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                pDialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG,e.getMessage());
                            }

                            @Override
                            public void onNext(String s) {
                                pDialog.show();
                                Log.i(TAG,s);
                            }
                        });
                break;
        }
    }
}
