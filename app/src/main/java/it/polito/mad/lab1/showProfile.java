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

/*        final SharedPreferences sharedPref = this.getSharedPreferences("shared_id",Context.MODE_PRIVATE); //to save and load small data
        final Button button = findViewById(R.id.editButton);

        String name_saved = new String();
        String mail_saved = new String();
        String bio_saved = new String();

        name_saved = sharedPref.getString("name", null);  //get data from shared preferences
        mail_saved = sharedPref.getString("mail", null);  //if the key is not present, it is created with the
        bio_saved = sharedPref.getString("bio",null);     //given default value (2nd parameter)

        final TextView name =  (TextView) findViewById(R.id.name);   //edit text object instances
        name.setText(name_saved);                                    //text object initialization
        final TextView mail =  (TextView) findViewById(R.id.mail);
        mail.setText(mail_saved);
        final TextView bio =  (TextView) findViewById(R.id.bio);
        bio.setText(bio_saved);


        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(
                        getApplicationContext(),
                        editProfile.class);

                startActivity(intent);

            }
        });

*/

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
