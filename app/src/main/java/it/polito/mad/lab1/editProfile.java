package it.polito.mad.lab1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;


public class editProfile extends AppCompatActivity {

    //private String name_saved = new String();
    //private String mail_saved = new String();
    //private String bio_saved = new String();

    static final int GALLERY_REQ = 0;
    static final int CAMERA_REQ = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        String name_saved = null;
        String mail_saved = null;
        String bio_saved = null;

        EditText name =  (EditText) findViewById(R.id.name);
        EditText mail =  (EditText) findViewById(R.id.mail);
        EditText bio =  (EditText) findViewById(R.id.bio);

        //final SharedPreferences sharedPref = this.getSharedPreferences("shared_id",Context.MODE_PRIVATE); //to save and load small data
        //final SharedPreferences.Editor editor = sharedPref.edit();  //to modify shared preferences
        if (savedInstanceState != null) {
            name_saved = savedInstanceState.getString("name");
            mail_saved = savedInstanceState.getString("mail");
            bio_saved = savedInstanceState.getString("bio");
        }else {
            SharedPreferences sharedPref = this.getSharedPreferences("shared_id", Context.MODE_PRIVATE);
            name_saved = sharedPref.getString("name", null);
            mail_saved = sharedPref.getString("mail", null);
            bio_saved = sharedPref.getString("bio", null);
        }


     //   name_saved = sharedPref.getString("name", null);  //get data from shared preferences
     //   mail_saved = sharedPref.getString("mail", null);  //if the key is not present, it is created with the
     //   bio_saved = sharedPref.getString("bio",null);     //given default value (2nd parameter)


     //   final Button button = findViewById(R.id.button_done);  //button instances

     //   final EditText name =  (EditText) findViewById(R.id.name);   //edit text object instances
        name.setText(name_saved);                                    //text object initialization
     //   final EditText mail =  (EditText) findViewById(R.id.mail);
        mail.setText(mail_saved);
     //   final EditText bio =  (EditText) findViewById(R.id.bio);
        bio.setText(bio_saved);


/*        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(
                        getApplicationContext(),
                        showProfile.class);


                editor.putString("name", name.getText().toString());
                editor.putString("mail", mail.getText().toString());
                editor.putString("bio", bio.getText().toString());



                editor.apply();

                startActivity(intent);

            }
        });
*/
        final Button photoButton = findViewById(R.id.photoSel);

        photoButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                selectImage();
            }

        });


    }

    public void saveData() {
        SharedPreferences sharedPref = this.getSharedPreferences("shared_id",Context.MODE_PRIVATE); //to save and load small data
        SharedPreferences.Editor editor = sharedPref.edit();  //to modify shared preferences

        EditText edit_name =  (EditText) findViewById(R.id.name);   //edit text object instances
        EditText edit_mail =  (EditText) findViewById(R.id.mail);
        EditText edit_bio =  (EditText) findViewById(R.id.bio);

        editor.putString("name", edit_name.getText().toString());
        editor.putString("mail", edit_mail.getText().toString());
        editor.putString("bio", edit_bio.getText().toString());

        Toast.makeText(getApplicationContext(), R.string.saveMessage, Toast.LENGTH_SHORT).show();

        editor.apply();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.doneButton:
                saveData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    //protected void onSaveInstanceState(Bundle outState) {
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState, outPersistentState);

        EditText edit_name =  (EditText) findViewById(R.id.name);
        EditText edit_mail =  (EditText) findViewById(R.id.mail);
        EditText edit_bio =  (EditText) findViewById(R.id.bio);

        //outState.putString("name", name_saved);
        outState.putString("name", edit_name.getText().toString());
        //outState.putString("mail", mail_saved);
        outState.putString("mail", edit_mail.getText().toString());
        //outState.putString("bio", bio_saved);
        outState.putString("bio", edit_bio.getText().toString());


    }
/*
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("name", name_saved);
        savedInstanceState.getString("mail", mail_saved);
        savedInstanceState.getString("bio", bio_saved);
    }
*/
    private void selectImage() {
        final CharSequence[] items = {getResources().getString(R.string.gallery),
                getResources().getString(R.string.photo),
                getResources().getString(R.string.cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(editProfile.this);
        //builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                //camera
                if (item == GALLERY_REQ) gallery();
                //gallery
                if (item == CAMERA_REQ) camera();

                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void gallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),GALLERY_REQ);
    }

    private void camera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQ && resultCode == RESULT_OK)
        {
            Bitmap bm = null;
            try {  bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData()); }
            catch (IOException e) {};
            ImageView imgView = findViewById(R.id.img);
            imgView.setImageBitmap(bm);

        }

        else if (requestCode == CAMERA_REQ && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            final ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageBitmap(imageBitmap);
        }
    }
}
