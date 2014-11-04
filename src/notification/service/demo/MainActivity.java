package notification.service.demo;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends Activity implements OnClickListener{
	
	EditText editMsg;
	DatePicker datepicker;
	TimePicker timepicker;
	Button btnSetNotification;
	Button btnCancelAllNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1337);
        editMsg = (EditText) findViewById(R.id.editText1);
        datepicker = (DatePicker) findViewById(R.id.DatePicker1);
        timepicker = (TimePicker) findViewById(R.id.timePicker1);
        btnSetNotification = (Button) findViewById(R.id.buttonSetNotification);
        
        btnSetNotification.setOnClickListener(this);
        btnCancelAllNotification.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		String message = editMsg.getText().toString();
		Calendar calendar = Calendar.getInstance();
		calendar.set(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(), timepicker.getCurrentHour(), timepicker.getCurrentMinute());
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		intent.setClass(this, MyNotificationService.class);
		intent.putExtra("msg", message);
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
}
