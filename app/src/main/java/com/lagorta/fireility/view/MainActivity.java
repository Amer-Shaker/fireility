//package com.lagorta.fireility.view;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
////
////    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
////        @Override
////        public void onReceive(Context context, Intent intent) {
////            Log.d("Broadcast", "Subscribing to test-library topic");
////
////            // Extract data included in the Intent
////            String t = intent.getStringExtra("value1");
////            String t1 = intent.getStringExtra("value2");
////            //alert data here
////        }
////    };
////
////    @Override
////    public void onResume() {
////        super.onResume();
////        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("myFunction"));
////    }
////
////    @Override
////    public void onPause() {
////        super.onPause();
////        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(mMessageReceiver);
////    }
//}
