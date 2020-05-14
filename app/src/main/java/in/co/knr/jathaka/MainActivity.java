package in.co.knr.jathaka;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


public class MainActivity extends AppCompatActivity {
    TextView tv,tvkn;
    EditText etkn,etln,etlt;
    Handler hd;
    Bundle bdl,nbdl,mbdl;
    final String lf = "\n";
    final String[] pn = new String[25],
            sign =	new String[]{"Ari","Tau","Gem","Can","Leo","Vir",
                    "Lib","Sco","Sag","Cap","Aqu","Pis"},
            planet =  new  String[]{"Sun","Moon","Mars","Mercury","Jupiter",
                    "Venus","Saturn","Rahu","Ketu","Asc","II ","III","IV ","V  ","VI ",
                    "VII","VIII","IX ","X  ","XI ","XII","O"},
            raasi = new String[]{"Mesha","Vrushabha","Midhuna","Karkataka",
                    "Simha","Kanya","Thula","Vruschika","Dhanush",
                    "Makara","Kumbha","Meena"},
            star = new  String[]{"Aswini","Bharani","Krithika","Rohini",
                    "Mrugasira","Aarudra","Punarvasu","Pushyami","Aaslesha","Makha",
                    "Pubba","Uthara","Hastha","Chitha","Swathi","Visshakha","Anuradha",
                    "Jyeshta","Moolla","P Ashaada","U Ashadda","Sravanam","Dhanishta",
                    "Sathabhisham","P Bhadra","U Bhadra","Revathi"},
            raasyaadhiPathi = new String[]{"Kuja","Sukra","Budha","Chandra",
                    "Ravi","Budha","Sukra","Kuja","Guru","Shani","Shani","Guru"},
            dasa_nadhudu = new String[]{"Ket","Ven","Sun","Moo","Mar","Rah","Jup",
                    "Sat","Mer"};
    final static int meenam=11,mesha=0,vrishabham=1,midhunam=2,karkatakam=3,simham=4;
    final static int kanya=5,thula=6,vrischikam=7,dhanussu=8,makaram=9,kumbham=10,inf0=13;

    String rev="inav imhskalajar imhskalarav iramukiruog ujarakoon iradnusamos";
    TextView[] ttv = new TextView[16];
    Random rndm;
    SortedSet ss;
    ArrayList<Integer> alP;
    int[] dasaPeriod = new int[]{7,20,6,10,7,18,16,19,17};
    final int BODY=0,LONG=1,SIGN=2,STAR=3,STAR_LORD=4,SUB_LORD=5,SUB_SUB_LORD=6,
            DIGREES=7,MINUTES=8,SECUNDS=9,EXIT=0,HOROSCOPE=1,CLOCK=2,KPN=3,NOW=4;
    public int wi,hi;
    boolean kpNum_B = false,backKey=false,daramState;
    SweDate sd;
    SwissEph sw;
    int year=1959,month=07,day=17,hour=14,mints=22,secs=20,change_field=104;
    final static int CHANGE_YEAR=100,CHANGE_MONTH=101,CHANGE_DATE=102,CHANGE_HOUR=103,
            CHANGE_MINUTE=104,CHANGE_SECOND=105;
    int[] hn={11,0,1,2,10,12,13,3,9,14,15,4,8,7,6,5};

    //= 1962,
    //month,// = 2,
    //day;// = 4;
    double GMT_CORECTION=5.5,longitude,latitude,
            longitudeDig = 78,  // =83.01;// 80 + 17 / 60.0;    //
            longitudeMin = (double)46/60,
            longitudeSecs= (double)1/3600,
            latitudeDig= 17,
            latitudeMin = (double)40/60,
            latitudeSecs= (double)5/3600;// = 17.6833334;// + 5 / 60.0;
    double hours;// = 14 + 20. / 60. - 5.5; // IST
    String printString;
    String[] chang=new String[]{"YEAR","MONTH","DAY","HOUR","MIN","SEC"};
    Spinner spinner;
    SpinnerAdapter spa;
    Calendar  cl;
    TreeMap<Double,String> hmp;

    {
        hmp = new TreeMap<Double, String>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //     FloatingActionButton fab = findViewById(R.id.fab);
        //     fab.setOnClickListener(new View.OnClickListener() {
        //         @Override
        //         public void onClick(View view) {
        //             Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                     .setAction("Action", null).show();
        //         }
        //     });
        //

        wi = getWindowManager().getDefaultDisplay().getWidth();
        hi = getWindowManager().getDefaultDisplay().getHeight();
        //l(longitude+"  "+latitude);
        l("setview");
      //  setview();
	/*	now();
		setContentView(R.layout.main); // activity_testpage1);
		Button newb=(Button)findViewById(R.id.button1);
		Button open=(Button)findViewById(R.id.button2);
		Button now=(Button)findViewById(R.id.button3);
		Button incr=(Button)findViewById(R.id.buttonInc);
		Button dicr=(Button)findViewById(R.id.buttonDic);
		Button change=(Button)findViewById(R.id.buttonChange);
		kpNum_B=false;
		hd=new Handler();
		rndm = new Random();
		bdl = new Bundle();
		//    new CopyAssetfiles(".*\\.se1", getApplicationContext()).copy();
		//	Log.d("a","AplicationContext "+getApplicationContext());
		sw=new SwissEph("/mnt/sdcard/swe_ephe");
	//	sw = new SwissEph(getApplicationContext().getFilesDir() + File.separator + "/ephe");
		now();
	//	chart2();
		chart();
		disp();
	//	setContentView(R.layout.chart);
	//	daram dm = new daram();
	/*setContentView(R.layout.get);
	TextView tvDate=(TextView)findViewById(R.id.getTextViewDate);
		tvDate.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					setDate();
				}
			});
		TextView tvTime=(TextView)findViewById(R.id.getTextViewTime);
		tvTime.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					setTime();
				}
			});
            */

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       menu.add(0,NOW,0,"NOW");
       menu.add(0,KPN,0,"KP Number");
       menu.add(0,CLOCK,0,"Clock");
       menu.add(0,HOROSCOPE,0,"Horoscope");
       menu.add(0,EXIT,0,"EXIT");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
            l("  i­tem  "+item);
            daram dm=null;
            if(item.getItemId()==KPN)
            {	l(dm);
                //if(dm!=null)dm.stop();
                daramState = false;
                //	kpNum();
                l(dm);}

            if(item.getItemId()==HOROSCOPE){Bundle bb=new Bundle();
                l(dm);
                daramState = false;
                //if(dm !=  null)dm.stop();
                l(dm);
                // onCreate(bb);
                now();
                getAllplanets(sw,sd);
                getLagnainfo(sw,sd,longitude,latitude);
                chart();disp();
            }
            if(item.getItemId()==EXIT){android.os.Process.killProcess(android.os.Process.myPid());
                finish();}
            if(item.getItemId()==CLOCK){dm = new daram();
                l(dm);
                chart();
                dm.start();
                l(dm);}
            if(item.getItemId()==NOW){l("NOW");now();}
        return super.onOptionsItemSelected(item);
    }


   // public class MainActivity extends Activity{

        public void oc(View v){
            int id=v.getId();
            switch(id){
                case R.id.button1: break;
                case R.id.button2: break;
                case R.id.button3:now();disp(); break;
                case R.id.buttonDic:changeDic(); break;
                case R.id.buttonInc:changeInc(); break;
                case R.id.buttonChange:showDialog(0);
                    //	case R.id.button6: break;
            }
        }

        public void changeInc(){
            l("changeInc()  "+chang[change_field-100]);
            Log.i("a",day+"-"+month+"-"+year+"   "+hour+":"+mints+":"+secs);
            switch(change_field){
                case CHANGE_YEAR:year++;break;
                case CHANGE_MONTH:month++;break;
                case CHANGE_DATE:day++;break;
                case CHANGE_HOUR:hour++;break;
                case CHANGE_MINUTE:mints++;break;
                case CHANGE_SECOND:secs=10+secs;break;
            }
            Log.i("a",day+"-"+month+"-"+year+"   "+hour+":"+mints+":"+secs);

            checkTime();
            disp();
        }
        public void changeDic(){
            l("changeDic()   "+chang[change_field-100]);
            Log.i("a",day+"-"+month+"-"+year+"   "+hour+":"+mints+":"+secs);

            switch(change_field){
                case CHANGE_YEAR:year--;break;
                case CHANGE_MONTH:month--;break;
                case CHANGE_DATE:day--;break;
                case CHANGE_HOUR:hour--;break;
                case CHANGE_MINUTE:mints--;break;
                case CHANGE_SECOND:secs=secs-10;break;
            }
            Log.i("a",day+"-"+month+"-"+year+"   "+hour+":"+mints+":"+secs);

            checkTime();
            disp();
        }

        public void checkTime(){

            Calendar cl=Calendar.getInstance();
            l(cl.getTime());
            cl.set(year,month-1,day,hour,mints,secs);
            l(cl.getTime());
            year=cl.get(Calendar.YEAR);
            month=cl.get(Calendar.MONTH)+1;
            day=cl.get(Calendar.DAY_OF_MONTH);
            hour=cl.get(Calendar.HOUR_OF_DAY);
            mints=cl.get(Calendar.MINUTE);
            secs=cl.get(Calendar.SECOND);
        }

        @Override
        protected Dialog onCreateDialog(int id)
        {
            if(id==1){
                return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker p1, int p2, int p3, int p4)
                    {
                        TextView date=(TextView)findViewById(R.id.getTextViewDate);
                        date.setText(p4+"-"+p3+"-"+p2);
                    }
                }, year, month, day);}

            if(id==2){
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker p1, int p2, int p3)
                    {
                        // TODO: Implement this method
                    }
                }, hour, mints,true);
            }
            if(id==0){
                AlertDialog.Builder aldb=new AlertDialog.Builder(this);
                aldb.setTitle("Select which to change");
                aldb.setItems(chang, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        Toast.makeText(getBaseContext(),chang[p2],300).show();
                        TextView tv=(TextView)findViewById(R.id.buttonChange);
                        tv.setText(chang[p2]);
                        change_field=100+p2;
                    }
                });
                Dialog dlg= new Dialog(this);
                dlg=aldb.create();
                dlg.show();
            }
            return super.onCreateDialog(id);
        }
        void setview(){
            LinearLayout ll=new LinearLayout(this);
            ll.setBackgroundColor(Color.YELLOW);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            ll.setLayoutParams(lp);
            circle cir=new circle(this);
            cir.setBackgroundColor(Color.WHITE);
            ll.addView(cir,lp);
            setContentView(ll);
            cir.invalidate();
            //	for(;;);
        }
       // @Override
       // protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);}

        public void ocl(View v){
            Toast.makeText(getBaseContext(),"in OCL",300).show();
            int id=v.getId();
            switch(id){
                case R.id.getButtonOk: break;
                case R.id.getTextViewDate:setDate(); break;
                case R.id.getTextViewTime:setTime(); break;
                case R.id.getEditTextPlace: break;
                case R.id.buttonGPS:gps(); break;
            }
        }

        private void setDate(){showDialog(1);}
        private void setTime(){showDialog(2);}
        private void setPlace(){}
        private void gps(){}
        private void getInput()
        {}

        public void chart()
        {//	l("chaart");
            int w = wi < hi ? wi:hi;
            w = w-w/5;
            LinearLayout[] ll = new LinearLayout[6];
            ttv = new TextView[17];
            int x = 0,y = 0,z=-1;
            ll[5]=new LinearLayout(this);
            ll[5].setOrientation(LinearLayout.VERTICAL);
            ll[5].setPadding(5,5,5,5);
            for(x=0;x<4;x++){
                ll[x]=new LinearLayout(this);
                for(y=0;y<4;y++){
                    z++;
                    ttv[hn[z]]=new TextView(this);
                    ttv[hn[z]].setBackgroundResource(R.drawable.line);
                    ttv[hn[z]].setWidth(w/4);
                    ttv[hn[z]].setHeight(w/4);
                    ttv[hn[z]].setTextColor(Color.BLUE);
                    ttv[hn[z]].setTextSize(14);
                    ttv[hn[z]].setScroller(new Scroller(this));

                    if(x==1|x==2){
                        if(y==1){continue;}
                        if(y==2){ttv[hn[z]].setWidth(w/2);
                            ttv[hn[z]].setBackgroundColor(Color.WHITE);}}
                    ll[x].addView(ttv[hn[z]]);			}
                ll[5].addView(ll[x]);}

            setContentView(R.layout.layout);
            LinearLayout llHolder=(LinearLayout)findViewById(R.id.holder);
            llHolder.addView(ll[5]);

            disp();
        }

        public void chart2(){
            setContentView(R.layout.chart);
            ttv[mesha]=(TextView)findViewById(R.id.mesham);
            ttv[vrishabham]=(TextView)findViewById(R.id.vrishabham);
            ttv[midhunam]=(TextView)findViewById(R.id.midhunam);
            ttv[karkatakam]=(TextView)findViewById(R.id.karkatakam);
            ttv[simham]=(TextView)findViewById(R.id.simham);
            ttv[kanya]=(TextView)findViewById(R.id.kanya);
            ttv[thula]=(TextView)findViewById(R.id.thula);
            ttv[vrischikam]=(TextView)findViewById(R.id.vrischikam);
            ttv[dhanussu]=(TextView)findViewById(R.id.dhanussu);
            ttv[makaram]=(TextView)findViewById(R.id.makaram);
            ttv[kumbham]=(TextView)findViewById(R.id.kumbham);
            ttv[meenam]=(TextView)findViewById(R.id.meenam);
            ttv[inf0]=(TextView)findViewById(R.id.info);
            for(int x=0;x<12;x++){ttv[x].setWidth(wi/4);ttv[x].setHeight(120);}
            disp();
        }
        public class daram extends  Thread{
            //	new Thread(new Runnable(){
            @Override
            public void run(){daramState=true;
                //	for(;;)
                while(daramState){
                    //	long time=System.currentTimeMillis();
	/*	cl = Calendar.getInstance();//l(cl);
		year=cl.get(Calendar.YEAR);month=cl.get(Calendar.MONTH)+1;
		day=cl.get(Calendar.DAY_OF_MONTH);
		hour=cl.get(Calendar.HOUR);//l(hour);
		double min=cl.get(Calendar.MINUTE);//l(min+(min/60));
		double sec=cl.get(Calendar.SECOND);//l(sec);
		hour=hour+(min/60)+(sec/3600)-5.5 ;//l(hour);
		sd=null;
		sd = new SweDate(year, month, day, hour);*/
                    now();
                    getAllplanets(sw,sd);
                    getLagnainfo(sw, sd, longitude, latitude);
                    hd.post(new Runnable(){
                        @Override
                        public void run()
                        {disp();
                            //	chart();
                        }
                    });
                    try{Thread.sleep(5000);}catch(Exception e){}
                }
                //  }}).start();
            }

        }
        public void now(){
            cl = Calendar.getInstance();//l(cl);
            year=cl.get(Calendar.YEAR);
            month=cl.get(Calendar.MONTH)+1;
            day=cl.get(Calendar.DAY_OF_MONTH);
            hour=cl.get(Calendar.HOUR_OF_DAY);//+GMT_CORECTION;//l(hour);
            mints=cl.get(Calendar.MINUTE);//l(min+(min/60));
            secs=cl.get(Calendar.SECOND);//l(sec);
            hours=(double)hour+(double)(mints/60)+(double)(secs/3600)-GMT_CORECTION;//l(hour);
            l("now  "+day+":"+month+":"+year+"  "+hour+":"+mints+":"+secs+"  hours  "+hours);
            //	year=1962;month=2;day=4;hour=14;mints=20;
        }
        //	year=1962;month=2;day=4;hour=14+(20/60)-5.5;

        public void disp(){
            l("in disp()");
            sd=null;
            double dMins=(double)mints/60;l("dMins  "+dMins);
            double dSecs=(double)secs/3600;l("dSecs  "+dSecs);
            hours=(double)hour+dMins+dSecs -GMT_CORECTION;
            Log.i("a",day+"-"+month+"-"+year+"   "+hour+":"+mints+":"+secs+"  "+hours+"  "+
                    (mints/60)+"  "+(secs/3600));
            longitude=longitudeDig+longitudeMin+longitudeSecs;
            latitude=latitudeDig+latitudeMin+latitudeSecs;
            l("longitude=  "+longitudeDig+"-"+longitudeMin+"-"+longitudeSecs+"\n"+
                    " latitude= "+latitudeDig+"-"+latitudeMin+"-"+latitudeSecs);
            //	sw = new SwissEph(getApplicationContext().getFilesDir() + File.separator + "/ephe");
//            sw.swe_set_sid_mode(SweConst.SE_SIDM_KRISHNAMURTI,0,0);
            sd = new SweDate(year, month, day, hours);
            getAllplanets(sw,sd);
            getLagnainfo(sw,sd,longitude,latitude);
            for(int x=0;x<12;x++){ttv[x].setText("");}
            Set  bs=  hmp.entrySet();   //bdl.keySet();
            Iterator it = bs.iterator();

            while(it.hasNext())
            {Map.Entry me = (Map.Entry)it.next();
                String pp =  me.getValue().toString();
                double lon = Double.parseDouble(me.getKey().toString());
                int raasi  =  (int)lon/30;
                int[] ar=rdms(lon);
                if(pp.equals("VIII")){pp="VIII";}
                else{pp= pp.substring(0,3);}
                String str = String.format("%d°%d'%d\"",
                        ar[DIGREES],ar[MINUTES],ar[SECUNDS]);
                pp="  "+pp+" "+str+lf;
				/*	if(pp.equals("Asc")){int[] ar=rdms(lon);
				String str = String.format("%d°%d'%d\"",
										   ar[DIGREES],ar[MINUTES],ar[SECUNDS]);
				pp=" "+lf+pp+" "+str+lf;}
			else if(pp.equals("VIII")){pp="VIII";}
			else{pp= pp.substring(0,3);}
			*/
                ttv[raasi].append(pp+" ");}
            //	ttv[13].setTextAlignment(A
            //	ttv[13].setGravity(Gravity.CENTER);
            //	ttv[15].setGravity(Gravity.CENTER);
            //	ttv[15].setText(day+"-"+month+"-"+year+"  "+hour+":"+mints+":"+secs);
            ttv[inf0].setText(day+"-"+month+"-"+year+"  "+hour+":"+mints+":"+secs+"\n"+"Long "+longitude+" Lat "+latitude);
            ttv[inf0].setText(getDateinfo(sd)+getLocationinfo(longitude,latitude)+getAyanamsainfo(sw,sd));

        }

        void l(Object o){try{Log.d("a",o.toString());}
        catch(Exception  e){l(e);}}

        public String ymd(double d){d*=0.3333333334;
            int  y = (int)d;
            d=(d-y)*12;
            int m = (int)d;
            d=(d-m)*30;
            int dd = (int)d;
            String stt = String.format("%02d %02d %02d",y,m,dd);
            return stt;
        }
        private int[] rdms(double l){int[] ret=new int[10];
            double d=l+0.5/3600/1000;
            double  ts=d/30;
            ret[SIGN]=(int)ts;
            double td=(ts-ret[SIGN])*30;
            ret[DIGREES]=(int)td;
            double tm = (td-ret[DIGREES])*60;
            ret[MINUTES]=(int)tm;
            td=tm-ret[MINUTES];
            ret[SECUNDS]=(int)(td*60);
            ret[STAR]=(int)(d/13.3333333334);
            ret[STAR_LORD]=(int)((d/13.333333333)%9);
            return	ret;
        }

        private void computeChart() {
            /*Instances of utility classes */

            // Set sidereal mode:
            //  sw.swe_set_sid_mode(SweConst.SE_SIDM_LAHIRI, 0, 0);
            sw.swe_set_sid_mode(SweConst.SE_SIDM_KRISHNAMURTI,0,0);

            // Print input de-tails:1
            //---   printString = getDateinfo(sd);
            //---   printString += getLocationinfo(longitude, latitude);

            //////////////////////////////////////////////
            // Output ayanamsa value:
            //////////////////////////////////////////////
            //--   printString += "\n" + getAyanamsainfo(sw, sd);
            //  --- /
            printString += getLagnainfo(sw, sd, longitude, latitude);
            printString += "\n" + getAllplanets(sw, sd);
            //tv.append(printString);
        }


        public int colr(){int r,g,b;
            r=rndm.nextInt(255);
            g=rndm.nextInt(255);
            b=rndm.nextInt(255);
            return Color.argb(255,r,g,b);
        }

        private String getAllplanets(SwissEph sw, SweDate sd) {

            double[] xp = new double[6];
            StringBuffer serr = new StringBuffer();
            String s = "";

            int[] planets = { SweConst.SE_SUN,
                    SweConst.SE_MOON,
                    SweConst.SE_MARS,
                    SweConst.SE_MERCURY,
                    SweConst.SE_JUPITER,
                    SweConst.SE_VENUS,
                    SweConst.SE_SATURN,
                    SweConst.SE_URANUS,
                    SweConst.SE_NEPTUNE,
                    SweConst.SE_PLUTO,
                    SweConst.SE_TRUE_NODE };	// Some systems prefer SE_MEAN_NODE

            int flags = SweConst.SEFLG_SWIEPH |        // slow and least accurate calculation method
                    SweConst.SEFLG_SIDEREAL |    // sidereal zodiac
                    SweConst.SEFLG_NONUT |        // will be set automatically for sidereal calculations, if not set here
                    SweConst.SEFLG_SPEED;        // to determine retrograde vs. direct motion
            boolean retrograde = false;
            hmp=null;
            hmp = new TreeMap<Double,String>();
            //	for(int  z=0;z<20;z++)l(sw.swe_get_planet_name(z));
            for(int p = 0; p < planets.length; p++) {
                int planet = planets[p];
                String planetName = sw.swe_get_planet_name(planet);
                if(planetName.contains("Node"))planetName="Rahu";
                //	l("iap "+sd);
                pn[planet]=planetName;
                int ret = sw.swe_calc_ut(sd.getJulDay(),
                        planet,
                        flags,
                        xp,
                        serr);

                if (ret != flags) {
                    if (serr.length() > 0) {
                        System.err.println("Warning: " + serr);
                    } else {
                        System.err.println(
                                String.format("Warning, different flags used (0x%x)", ret));
                    }
                }

                retrograde = (xp[3] < 0);
                char c=' ';
                if(planet==SweConst.SE_SUN | planet==SweConst.SE_MOON|planet==SweConst.SE_TRUE_NODE){
                    c=c;
                }else{c=retrograde ? 'R' : 'D';}
                s += String.format("%-9s %s %c\n",
                        planetName, toDMS(xp[0]),c);// (retrograde ? 'R' : 'D'));
                bdl.putDouble(planetName,xp[0]);
                hmp.put(xp[0], planetName);
            }
            // KETU
            xp[0] = (xp[0] + 180.0) % 360;
            String planetName = "Ketu";
            bdl.putDouble(planetName,xp[0]);
            hmp.put(xp[0], planetName);
            //	hmp.put(
            s += String.format("%-9s %s %c\n",
                    planetName, toDMS(xp[0]),32);
            return s;
        }

        private String getAyanamsainfo(SwissEph sw, SweDate sd) {
            double ayanamsa = sw.swe_get_ayanamsa_ut(sd.getJulDay());
            return "Ayanamsa  " + toDMS(ayanamsa) + "\n";
        }

        private String getLocationinfo(double longitude, double latitude) {
            return "\nLocation  " +
                    toDMS(longitude) + (longitude > 0 ? "E" : "W") +
                    "\n          " +
                    toDMS(latitude) + (latitude > 0 ? "N" : "S");
        }

        private String getDateinfo(SweDate sd) {
            double hour = sd.getHour() + 0.5/3600.;
            int min = (int)((hour - (int)hour) * 60);
            int sec = (int)(((hour - (int)hour) * 60 - min) * 60);
            return String.format(Locale.US,
                    "Date: %4d-%02d-%02d, %d:%02d:%02d %s\n",
                    sd.getYear(),
                    sd.getMonth(),
                    sd.getDay(),
                    (int)hour,
                    min,
                    sec,
                    " ");

        }

        private String getLagnainfo(SwissEph sw, SweDate sd, double longitude, double latitude) {
            sw = new SwissEph(getApplicationContext().getFilesDir() + File.separator + "/ephe");
            int flags = SweConst.SEFLG_SIDEREAL;
            double[] cusps = new double[13];
            double[] acsc = new double[10];

            int result = sw.swe_houses(sd.getJulDay(),
                    flags,
                    latitude,
                    longitude,
                    'P',
                    cusps,
                    acsc);
            String st = "houses ";
            for(int x = 1; x<cusps.length; x++){
                st =st+ "\ncusps "+x+" = "+toDMS(cusps[x]);
                //	l("cusp "+x+" "+planet[x+8]+"  "+toDMS(cusps[x])+"  "+cusps[x]);
                String planetname = planet[x+8];
                bdl.putDouble(planetname,cusps[x]);
                hmp.put(cusps[x], planetname);
            }
            //	for(int x=0;x<acsc.length;x++){l("cusp "+x+" "+planet[x]+"  "+toDMS(acsc[x])+"  "+acsc[x]);}
            return st;
        }

        static String toDMS(double d) {
            d += 0.5/3600./10000.;	// round to 1/1000 of a second
            int deg = (int) d;
            d = (d - deg) * 60;
            int min = (int) d;
            d = (d - min) * 60;
            int sec = (int)d;

            return String.format("%3d° %02d' %02d\"", deg, min, sec);
        }
        private  double kp(int kpn){
            int x=0, y=0;
            double p=0;
            ArrayList<Integer> al = new ArrayList<Integer>();
            alP = new ArrayList<Integer>();
            for(x=0;x<9;x++){al.add(dasaPeriod[x]);alP.add(x);}
            x=0;
            //for(x=1;x<250;x++)
            while(x < kpn){x++;//l(x);
                //	if(x<100)	Log.d("a","x "+x+"  p  "+p);
                int[]  ar = rdms(p);
                String str = String.format("%d°  %d'  %d\"",ar[DIGREES],ar[MINUTES],ar[SECUNDS]);
                if(y>8){y=0;al.add(al.get(0));al.remove(0);
                    alP.add(alP.get(0));alP.remove(0);}
                if(!kpNum_B){if(p>0){tv.append("    "+str+"  "+ymd(p)+"\n");}
                    tv.append("  "+String.format("%03d", x)+"   ");
                    tv.append(sign[ar[SIGN]]+"  ");
                    tv.append("   "+star[ar[STAR]]+"   ");
                    tv.append(dasa_nadhudu[ar[STAR_LORD]]);
                    tv.append("     "+dasa_nadhudu[alP.get(y)]);
                    tv.append("    "+str);}
                //toDMS(p%30)+"   -  ");
                if(x==22|x==105|x==188){p+= 0.777777778;continue;}
                if(x==23|x==106|x==189){p+=1.2222222222;y++;continue;}
                if(x==62|x==145|x==228){p+=0.5555555556;continue;}
                if(x==63|x==146|x==229){p+=0.5555555556;y++;continue;}
                p+=al.get(y)*0.11111111111;y++;	}
            tv.append(toDMS(p%30)+"\n");
            return p;

        }
        public int sub(double p){int sl=0;
            int dn = (int)(p/13.333333334)%9;
            p=p-(p/13.3333334);
            return sl;
        }

 /*       @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            menu.add(0,NOW,0,"NOW");
            menu.add(0,KPN,0,"KP Number");
            menu.add(0,CLOCK,0,"Clock");
            menu.add(0,HOROSCOPE,0,"Horoscope");
            menu.add(0,EXIT,0,"EXIT");
            return super.onCreateOptionsMenu(menu);
        }

       @Override
        public boolean onMenuItemSelected(MenuItem item)
        {
            l("featurreid "+featureId+"  i­tem  "+item);
            daram dm=null;
            if(item.getItemId()==KPN)
            {	l(dm);
                //if(dm!=null)dm.stop();
                daramState = false;
                //	kpNum();
                l(dm);}

            if(item.getItemId()==HOROSCOPE){Bundle bb=new Bundle();
                l(dm);
                daramState = false;
                //if(dm !=  null)dm.stop();
                l(dm);
                // onCreate(bb);
                now();
                getAllplanets(sw,sd);
                getLagnainfo(sw,sd,longitude,latitude);
                chart();disp();
            }
            if(item.getItemId()==EXIT){android.os.Process.killProcess(android.os.Process.myPid());
                finish();}
            if(item.getItemId()==CLOCK){dm = new daram();
                l(dm);
                chart();
                dm.start();
                l(dm);}
            if(item.getItemId()==NOW){l("NOW");now();}
            return super.onMenuItemSelected( item);
        }
*/
/*	private void kpNum()
	{	kpNum_B = true;backKey=true;
		setContentView(R.layout.kn);
		etkn = (EditText)findViewById(R.id.editText);
		etln = (EditText)findViewById(R.id.editText1);
		etlt = (EditText)findViewById(R.id.editText2);
		tvkn = (TextView)findViewById(R.id.textViewkn);
		etkn.setHint("K P Number 1  to  249");
		etln.setHint("Longitude");
		etlt.setHint("Latitude");
	//	while(backKey){}
	}
	*/

        public void go(View v){backKey=true;l("go backKey"+backKey);
            int	kpn = Integer.parseInt(etkn.getText().toString());
            tvkn.setText("     "+kpn+lf);
            double p =  kp(kpn);
            tvkn.append("kp number "+kpn+ "  Ascendent  "+p+"  "+toDMS(p)+lf);
            int[] ar = rdms(p);
            String str = String.format("%dÂ°  %d'  %d\"",ar[DIGREES],ar[MINUTES],ar[SECUNDS]);
            tvkn.append("  "+String.format("%03d", kpn)+"   "+
                    sign[ar[SIGN]]+" ( "+raasi[ar[SIGN]]+" ) "
                    +raasyaadhiPathi[ar[SIGN]]+"   "
                    +star[ar[STAR]]+"    "
                    +dasa_nadhudu[ar[STAR_LORD]]+"     "
                    +dasa_nadhudu[alP.get(SUB_LORD)]+"      "+str);
            now();
            getLagnainfo(sw,sd,longitude,latitude);
            double kptime=p-Double.parseDouble(bdl.get("Asc").toString());
            tvkn.append(lf+kptime);
            //  +toDMS(p%30));
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event)
        {
            // TODO: Implement this method
            l("keyCokeyode "+keyCode+"  keyEvent  "+event);
            if(keyCode==KeyEvent.KEYCODE_BACK)backKey=false;
            return super.onKeyDown(keyCode, event);
        }
        void circ(){
	/*	angle =	Math.toRadians(44);

		//	r-=50;
		p1=x+r*(float)Math.cos(angle);
		p2=y+(r)*(float)Math.sin(angle);
		pt.setColor( col());
		*/
        }
        public class Gv extends View {
            Gv(Context cc){
                super(cc);
                Paint pt=new Paint(Paint.ANTI_ALIAS_FLAG);
                pt.setColor(Color.RED);
            }

            @Override
            protected void onDraw(Canvas canvas)
            {
                ///	canvas.drawArc(
                super.onDraw(canvas);
            }


        }
    }


