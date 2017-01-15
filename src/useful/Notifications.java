package useful;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import database.AccountBean;

public class Notifications {

	private static final String configFile = "/home/jboss/notifications.properties";
	private String firebaseURL;
	private String firebaseServerKey;

	public Notifications() {
		loadNotificationsProperties();
	}

	public boolean sendNotification(AccountBean account, String title, String message, String data) {
		return sendNotification(account.getToken(), title, message, data);
	}

	public boolean sendNotificationToTopic(String topic, String title, String message, String data) {
		return sendNotification("/topics/" + topic, title, message, data);
	}

	private boolean sendNotification(String to, String title, String message, String data) {
		try {
			URL url = new URL(firebaseURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + firebaseServerKey);
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes("{\"notification\":{\"title\":\"" + title + "\",\"text\":\""
					+ message + "\"},\"to\":\"" + to + "\",\"data\":{\"id\":\"" + data + "\"}}");
			wr.flush();
			wr.close();
			conn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void loadNotificationsProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(configFile);
			prop.load(input);
			firebaseURL = prop.getProperty("firebaseURL");
			firebaseServerKey = prop.getProperty("firebaseServerKey");
		} catch (FileNotFoundException e) {
			System.err.println("Creating notifications config file. Edit and restart the server.");
			OutputStream output = null;
			try {
				output = new FileOutputStream(configFile);
				prop.setProperty("firebaseURL", "https://fcm.googleapis.com/fcm/send");
				prop.setProperty("firebaseServerKey", "");
				prop.store(output, null);
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading notifications config file!!!");
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
