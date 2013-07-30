package com.kingsmeadbaptist.bible.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Kingsmead_Bible_Reader extends Activity {

    public int selBook = 0;
    public int selChapter = 0;
    public int selVerse = 0;
    public int mySelectedVerses = 0;
	public boolean secondcolumn = true;
	public String header;
	public String page;
    final int[] myChapters = new int[66];
    final int[][] myVerses = new int[66][200];
    final String[][][] strVerses = new String[66][200][200];
    final String[] strSelectedVerses = new String[500];
    final String[] books = new String[66];


    private static final Adapter Adapter = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingsmead__bible__reader);
        TextView t1 = (TextView)findViewById(R.id.textView1);
        t1.setText("TextView1");        
        //t1.setMovementMethod(new ScrollingMovementMethod());
        WebView engine = (WebView) findViewById(R.id.webView1);
        header = 	" "+
        			"<!DOCTYPE html> "+
        			"<html> "+
        			"<head> "+
					"<style> "+
					"@font-face "+
					"{ "+
					"font-family: myFirstFont; "+
					"src: url('FeDPrm2.ttf') "+
					"}     "+
					"div.columns       { width: 100%;  } "+
					"div.columns div   { width: 45%; height: 200px; float: left;font-family: myFirstFont; line-height:200%;} "+
					"div.middle        { width: 100%;  } "+
					"div.middle div    { width: 5%; height: 200px; float: left;font-family: myFirstFont; } "+
					"div.grey          { background-color: #cccccc; } "+
					"div.red           { background-color: #e14e32; } "+
					"div.clear         { clear: both; } "+
					"p:first-letter { float: left; color: #903; font-size: 75px; line-height: 60px; padding-top: 4px; padding-right: 8px; padding-left: 3px; } "+
					"</style> "+
					"</head>";
        			//"<body><div class=\"columns\">    <div>    <p>1<sub>1</sub>Lorem ipsum dolor sit amet, consectetur adipiscing elit.    <sub>2</sub>Etiam a massa vel turpis ultrices porttitor ac pellentesque orci.";
					//Proin ac elementum diam.    <sub>3</sub>Integer eget posuere urna. Nam nec gravida tortor, sit amet cursus odio.    <sub>4</sub>Sed placerat, nisi vel condimentum dapibus, elit turpis commodo metus, non tristique eros est at nulla.    <br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <sub>5</sub>Vivamus sed tincidunt sapien. Phasellus bibendum congue eros vitae convallis.    <sub>6</sub>Fusce rutrum varius tempus. Duis ac porttitor massa.    <sub>7</sub>Proin tempor luctus metus in malesuada. Cras feugiat libero vitae egestas eleifend.    <sub>8</sub>Nunc non lacus eu quam tempus dictum vel non mi.    <sub>9</sub>In consequat rhoncus lobortis.    <sub>10</sub>Aliquam vitae vestibulum nisi, eget euismod velit.    <sub>11</sub>In ultricies enim eu blandit porta.    <sub>12</sub>Praesent elementum euismod tempor.</p><p>2<sub>1</sub>Nulla vitae tristique dui, ac pulvinar lectus.    <sub>2</sub>Proin convallis at turpis id accumsan.    <sub>3</sub>Curabitur ac est faucibus, condimentum purus vulputate, aliquam nunc.    <br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <sub>4</sub>Aliquam et turpis elementum, commodo quam id, facilisis lectus.    <br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <sub>5</sub>Fusce purus nisl, placerat eu ante nec, bibendum eleifend turpis.    <br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <sub>6</sub>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.    <sub>7</sub>Sed ac magna neque. Etiam commodo vel urna nec dictum. In hac habitasse platea dictumst.    <sub>8</sub>Fusce orci dolor, ullamcorper id consequat sed, mollis sed dolor. Vestibulum euismod mi felis.    <sub>9</sub>Integer ullamcorper, tortor quis accumsan pellentesque, elit nisl lacinia leo, vel imperdiet purus nibh et purus.</p><p>3<sub>3</sub>Donec sagittis libero laoreet lacinia sodales. Vivamus mollis, leo vel auctor auctor, dolor mi rhoncus metus, eu placerat nibh tellus lacinia leo. Aenean non urna molestie libero tincidunt bibendum. Quisque faucibus mattis enim, a facilisis tortor fringilla nec. Curabitur pretium, metus ut auctor commodo, tortor dolor consectetur metus, nec euismod mauris libero a augue. Mauris id sollicitudin risus. Quisque condimentum lectus sed tristique semper. Aliquam ornare augue pulvinar, euismod ante a, consectetur ipsum. Maecenas vitae ullamcorper nulla. Phasellus eleifend nisi augue, nec faucibus est eleifend ut. Praesent ut sem sit amet lacus ullamcorper convallis. Ut mollis ligula blandit lacus imperdiet, sed porttitor augue rhoncus. Donec sed tortor eget velit vulputate sodales. Morbi laoreet bibendum nisi, dapibus faucibus sapien egestas ut. Vivamus enim purus, mattis eu dignissim viverra, egestas ut sapien.</p>        </div>    <div style=\"width: 5%;\">&nbsp</div>    <div class=\"columns\"><p>4<sub>1</sub>Cras vitae risus ullamcorper, sagittis est et, elementum eros.    <sub>2</sub>Fusce quis quam nec sapien venenatis commodo eu consectetur massa.    <sub>3</sub>Integer tempus id lorem quis faucibus.    <sub>4</sub>Aliquam in justo quis ante ullamcorper dictum.    <br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<sub>5</sub>Morbi dictum, elit et pulvinar tristique, enim nisi molestie felis, a faucibus dui libero nec nisl.    <sub>6</sub>Vestibulum erat sapien, condimentum vitae lacus vel, dignissim iaculis purus.    <sub>7</sub>Donec blandit, leo eu malesuada placerat, arcu magna vulputate massa, at congue quam est id erat.    <sub>8</sub>Sed non vestibulum massa, vel porta sem.</p>     <p>5<sub>1</sub>Integer in eros id orci pharetra cursus. Donec mattis nisl et justo egestas elementum.<sub>2</sub>Donec auctor lacinia suscipit.<sub>2</sub>Donec nunc purus, molestie convallis massa sed, malesuada condimentum velit.<sub>2</sub>Etiam ac tristique neque, ut consequat erat.<sub>2</sub>Donec ut tellus interdum, tempor justo eu, faucibus nisi.<sub>2</sub>In a ornare urna, ut sodales enim. Donec placerat porta tempus.<sub>2</sub>Integer ante libero, cursus ut diam in, adipiscing ultrices enim.<sub>2</sub>Suspendisse convallis accumsan aliquam. Etiam scelerisque libero et dui luctus, non cursus sem tincidunt.<sub>2</sub>Duis id libero vel justo iaculis imperdiet quis sed libero.<br />&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<sub>2</sub>Proin aliquam dui dolor, volutpat egestas ligula dapibus sit amet.</div></div><div class=\"clear\"></div></body></html>";";:
		
		engine.loadDataWithBaseURL("file:///android_asset/",header,"text/html","utf-8",null);  
    }
	
	public void next_verse(View view){
		int myBookPosition;
		int myChapterPosition;
		int myVersePosition;
		
		Spinner s1 = (Spinner)findViewById(R.id.spinner1); 
		Spinner s2 = (Spinner)findViewById(R.id.spinner2); 
		Spinner s3 = (Spinner)findViewById(R.id.spinner3); 
		
		myBookPosition = s1.getSelectedItemPosition();
		myChapterPosition = s2.getSelectedItemPosition();
		myVersePosition = s3.getSelectedItemPosition();
		
		myVersePosition++;
		if(myVersePosition<myVerses[selBook][selChapter])
		{
			s3.setSelection(myVersePosition);
		}
		else
		{
			myChapterPosition++;
			if(myChapterPosition<myChapters[selBook])
			{
				myVersePosition = 0;
				selVerse=-1;
				s2.setSelection(myChapterPosition);
				s3.setSelection(myVersePosition);
			}
		}
		
	}

    public void delete_verse(View view)
    {
        mySelectedVerses--;
        refreshPage(view);
    }

    public void refreshPage(View view){
        TextView t1 = (TextView)findViewById(R.id.textView1);
        t1.setText("");
        for (int i = 1; i <= mySelectedVerses; i++)
        {
            String[] data = strSelectedVerses[i].split(",");
            selBook = Integer.parseInt(data[0]);
            selChapter = Integer.parseInt(data[1]);
            selVerse = Integer.parseInt(data[2]);
            //t1.setText(t1.getText() + "[" + books[selBook] + " " + Integer.toString(selChapter) + ":" + Integer.toString(selVerse) + "] " + strVerses[selBook][selChapter][selVerse] + "\n");
			if (secondcolumn) {if (t1.getText().length() > 500) {t1.setText(t1.getText() + "</p></div><div><p>"); secondcolumn=false;}}
			t1.setText(t1.getText() + "<sup>" + Integer.toString(selVerse) + "</sup>" + strVerses[selBook][selChapter][selVerse]);
			page = header + "<div class=\"columns\"><div><p>1" + t1.getText() + "</p></div></div></body></html>";
			WebView engine = (WebView) findViewById(R.id.webView1);
			engine.loadDataWithBaseURL("file:///android_asset/",page,"text/html","utf-8",null);  
			}
    }
    
	
    public void import_data(View view){

        final TextView t1 = (TextView)findViewById(R.id.textView1);
        t1.setText(".");
        
        int myBooks = -1;
        //final int[] myChapters = new int[66];
        //final int[][] myVerses = new int[66][200];
        //final String[][][] strVerses = new String[66][200][200];
        

        
        BufferedReader reader = null;
        
        
		String BibleFile = "";
	
		switch(view.getId())
		{
			case 2131165185:
			BibleFile = "KJV.xmf";
			break;
			case 2131165186:
			BibleFile = "NLT.xmf";
			break;
		}
    		
		
        try {
        	reader = new BufferedReader (new InputStreamReader(getAssets().open(BibleFile)));
            for (String line; (line = reader.readLine()) != null;) {
            	if (line.indexOf("<b n=") != -1)
                {
            		myBooks++;
            		//a1.add(line);
            		books[myBooks] = line.substring(6,line.length()-2);
            		//t1.setText(line);
					myChapters[myBooks] = 0;
                }
            	
            	if (line.indexOf("<c n=") != -1)
                {
            		myChapters[myBooks]++;
					myVerses[myBooks][myChapters[myBooks]]=0;
                }
            
            	if (line.indexOf("<v n=") != -1)
                {
            		myVerses[myBooks][myChapters[myBooks]]++;
                    strVerses[myBooks][myChapters[myBooks]][myVerses[myBooks][myChapters[myBooks]]] = line.substring(line.indexOf(">")+1,line.indexOf("</"));
                }            	
            	
                	
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
        	//t1.setText(e.getMessage());
		} finally {
            if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
        }
        
        t1.setText(Integer.toString(view.getId()));
        
        
        final List<String> chapters = new ArrayList<String>();
        final List<String> verses = new ArrayList<String>();

        //chapters.add("test");        
        //chapters.add("test2");
        //chapters.add("test3");
        
        
		final ArrayAdapter<?> aa = new ArrayAdapter<Object>(
				this,
				android.R.layout.simple_spinner_item, 
				books);

    	final ArrayAdapter<String> aa2 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item, 
				chapters);
        
        final ArrayAdapter<String> aa3 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item, 
				verses);

        //chapters.add("Chapter");
        //verses.add("Verse");
        
		aa.setDropDownViewResource(
				   android.R.layout.simple_spinner_dropdown_item);        

    	aa2.setDropDownViewResource(
				   android.R.layout.simple_spinner_dropdown_item);        

        aa3.setDropDownViewResource(
				   android.R.layout.simple_spinner_dropdown_item);        
                   
        //aa4.setDropDownViewResource(
    			   //android.R.layout.simple_spinner_dropdown_item);    

        //aa2.add("Test");
        
        
        
        Spinner s1 = (Spinner)findViewById(R.id.spinner1);
        Spinner s2 = (Spinner)findViewById(R.id.spinner2);
        Spinner s3 = (Spinner)findViewById(R.id.spinner3);        
        
       
        
        s1.setAdapter(aa);
        s2.setAdapter(aa2);
        s3.setAdapter(aa3);
        //s4.setAdapter(aa4);
        

        
        // Book Spinner
    	s1.setOnItemSelectedListener(new OnItemSelectedListener() {
    		
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
                        
                    chapters.clear();
                    verses.clear();
                    selBook = arg2;
                    for (int i = 1; i <= myChapters[arg2]; i++)
                    {
                    	chapters.add(Integer.toString(i));
                    }
					aa2.notifyDataSetChanged();
                             
			}         

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
                
    	});

    	
        // Chapter Spinner
        s2.setOnItemSelectedListener(new OnItemSelectedListener() {
    		
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
                        
                    verses.clear();
                    selChapter = arg2+1;
                    for (int i = 1; i <= myVerses[selBook][selChapter]; i++)
                    {
                    	verses.add(Integer.toString(i));
                    }
                    aa3.notifyDataSetChanged();
                              
			}         

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
                
    	});


        // Verses Spinner
        s3.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
                        
                    selVerse = arg2+1;
                    mySelectedVerses++;
                    strSelectedVerses[mySelectedVerses] = (Integer.toString(selBook) + "," + Integer.toString(selChapter) + "," + Integer.toString(selVerse));
                    refreshPage(arg1);

                              
			}         

			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
                
    	});

     	

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kingsmead__bible__reader, menu);
        return true;
    }
}
