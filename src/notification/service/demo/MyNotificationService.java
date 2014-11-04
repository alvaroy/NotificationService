package notification.service.demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyNotificationService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "OnCreate()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String message = intent.getStringExtra("msg");
		Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Intent notificationItent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationItent, 0);
		NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(this)
	                .setSmallIcon(R.drawable.ic_launcher)
	                .setContentTitle("New Notification")
	                .setStyle(new NotificationCompat.BigTextStyle()
	                    .bigText(message))
	                .setAutoCancel(true)
	                .setContentIntent(pendingIntent)
	                .setContentText(message);
		Notification noti = mBuilder.build();
		noti.tickerText="You have a new message";
		notificationManager.notify(1337, noti);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
