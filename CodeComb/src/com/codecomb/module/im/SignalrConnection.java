package com.codecomb.module.im;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.OperationApplicationException;
import android.util.Log;

import com.codecomb.MyApplication;
import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.infrastructure.wabapi.net.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zsoft.SignalA.Hubs.HubConnection;
import com.zsoft.SignalA.Hubs.HubInvokeCallback;
import com.zsoft.SignalA.Hubs.HubOnDataCallback;
import com.zsoft.SignalA.Hubs.IHubProxy;
import com.zsoft.SignalA.Transport.StateBase;
import com.zsoft.SignalA.Transport.Longpolling.JSONHelper;
import com.zsoft.SignalA.Transport.Longpolling.LongPollingTransport;

public class SignalrConnection {

	protected static final String TAG = SignalrConnection.class.getSimpleName();

	private HubConnection connection;
	private IHubProxy mobileHub;

	public static SignalrConnection getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {
		private static final SignalrConnection instance = new SignalrConnection();
	}

	private SignalrConnection() {

		connection = new HubConnection(Constants.SIGNALR_ADDRESS, MyApplication
				.getInstance().getApplicationContext(),

		new LongPollingTransport()) {
			@Override
			public void OnError(Exception exception) {

			}

			@Override
			public void OnStateChanged(StateBase oldState, StateBase newState) {

				switch (newState.getState()) {
					case Connected :
						
						Log.e(TAG, "与SignalR服务器连接成功");
						
						register();


						break;
					case Disconnected :
						Log.e(TAG, "与SignalR服务器断开连接");

						break;
					default :
						break;
				}

			}
		};

	}

	public void connect() throws Exception {

		mobileHub = connection.CreateHubProxy("mobileHub");

		mobileHub.On("onClarificationsRequested", new HubOnDataCallback() {

			@Override
			public void OnReceived(JSONArray args) {

				Log.e(TAG, "收到问题请求:" + args.toString());

			}
		});

		mobileHub.On("onClarificationsResponsed", new HubOnDataCallback() {

			@Override
			public void OnReceived(JSONArray args) {

				Log.e(TAG, "收到问题回复:" + args.toString());

			}
		});

		mobileHub.On("onMessageReceived", new HubOnDataCallback() {

			@Override
			public void OnReceived(JSONArray args) {

				Log.e(TAG, "收到消息:" + args.toString());

				

				
				
			}
		});

		mobileHub.On("onBroadCast", new HubOnDataCallback() {

			@Override
			public void OnReceived(JSONArray args) {

				
				
				Log.e(TAG, "收到广播:" + args.toString());
				
				

			}
		});

		connection.Start();

	}

	public void register() {

		List<String> list = new ArrayList<String>(1);
		list.add(SettingsManager.getInstance().getAccessToken());

		mobileHub.Invoke("RegisterSignalR", list, new HubInvokeCallback() {

			@Override
			public void OnResult(boolean succeeded, String response) {

				if (succeeded) {
					Log.e(TAG, "注册推送服务成功:" + response);

				} else {
					Log.e(TAG, "注册推送服务失败:" + response);

				}

			}

			@Override
			public void OnError(Exception ex) {
				ex.printStackTrace();

			}
		});

	}


	public void stopSignalR() {
		if (connection != null) {
			connection.Stop();

		}
	}

}
