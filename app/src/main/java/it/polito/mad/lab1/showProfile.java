package it.polito.mad.lab1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class showProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPref = this.getSharedPreferences("shared_id", Context.MODE_PRIVATE);

        String name = sharedPref.getString("name", null);
        String mail = sharedPref.getString("mail", null);
        String bio = sharedPref.getString("bio", null);

        TextView show_name = (TextView) findViewById(R.id.name);
        TextView show_email = (TextView) findViewById(R.id.mail);
        TextView show_bio = (TextView) findViewById(R.id.bio);

        show_name.setText(name);
        show_email.setText(mail);
        show_bio.setText(bio);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.editButton:
                editProfile();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void editProfile(){
        Intent intent = new Intent(getApplicationContext(), editProfile.class);
        startActivity(intent);
    }

}
