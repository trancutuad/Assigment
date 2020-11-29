package vn.edu.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacebookActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private CircleImageView profile_image;
    private TextView profile_email,profile_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        profile_image=findViewById(R.id.profile_image);
        profile_email=findViewById(R.id.profile_email);
        profile_name=findViewById(R.id.profile_name);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

//        try {
//
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),
//
//                    PackageManager.GET_SIGNATURES);
//
//            for (Signature signature : info.signatures) {
//
//                MessageDigest md = MessageDigest.getInstance("SHA");
//
//                md.update(signature.toByteArray());
//
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//
//                Log.e("ABC", "printHashKey() Hash Key: " + hashKey);
//
//            }
//
//        } catch (NoSuchAlgorithmException e) {
//
//            Log.e("ABC", "printHashKey()", e);
//
//        } catch (Exception e) {
//
//            Log.e("ABC", "printHashKey()", e);
//
//        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken==null){
                profile_email.setText("");
                profile_name.setText("");
                profile_image.setImageResource(0);
                Toast.makeText(FacebookActivity.this,"Người dùng đăng xuất",Toast.LENGTH_SHORT).show();
            }else
                LoadProfile(currentAccessToken);
        }
    };

   private void LoadProfile(AccessToken accessToken){
       GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
           @Override
           public void onCompleted(JSONObject object, GraphResponse response)
           {
               try {
                   String first_name = object.getString("first_name");
                   String last_name = object.getString("last_name");
                   String email = object.getString("email");
                   String id = object.getString("id");
                   String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                   profile_email.setText(email);
                   profile_name.setText(last_name +" "+first_name);
                   RequestOptions requestOptions = new RequestOptions();
                   requestOptions.dontAnimate();

                   Glide.with(FacebookActivity.this).load(image_url).into(profile_image);


               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       });

       Bundle parameters = new Bundle();
       parameters.putString("fields","first_name,last_name,email,id");
       request.setParameters(parameters);
       request.executeAsync();
   }

    public void share(View view){
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();

        ShareDialog shareDialog=new ShareDialog(this);
        shareDialog.show(content,ShareDialog.Mode.AUTOMATIC);
    }

}
