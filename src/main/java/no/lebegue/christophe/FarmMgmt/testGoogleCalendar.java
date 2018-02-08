package no.lebegue.christophe.FarmMgmt;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class testGoogleCalendar {
	/** Application name. */
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/calendar-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/calendar-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = testGoogleCalendar.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Calendar client service.
	 * 
	 * @return an authorized Calendar client service
	 * @throws IOException
	 */
	public static com.google.api.services.calendar.Calendar getCalendarService() throws IOException {
		Credential credential = authorize();
		return new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	public static void main(String[] args) throws IOException {
		// Build a new authorized API client service.
		// Note: Do not confuse this class with the
		// com.google.api.services.calendar.model.Calendar class.
		com.google.api.services.calendar.Calendar service = getCalendarService();

		// List the next 10 events from the primary calendar.
		
		Event event = new Event().setSummary("test event")
				.setDescription("test event created from FarmMgmt");

		java.util.Calendar beginTime = java.util.Calendar.getInstance();
		beginTime.set(2018, 1, 7, 14, 30);
		
		DateTime startDateTime = new DateTime(beginTime.getTimeInMillis());
		EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("Europe/Oslo");
		event.setStart(start);

		java.util.Calendar endTime = java.util.Calendar.getInstance();
		endTime.set(2018, 1, 7, 15, 30);
		
		DateTime endDateTime = new DateTime(endTime.getTimeInMillis());
		EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("Europe/Oslo");
		event.setEnd(end);

		//String[] recurrence = new String[] { "RRULE:FREQ=DAILY;COUNT=2" };
		//event.setRecurrence(Arrays.asList(recurrence));

		//String calendarId = "primary";
		//event = service.events().insert(calendarId, event).execute();

		Events events = service.events().list("primary").setMaxResults(10).setOrderBy("startTime")
				.setSingleEvents(true).execute();

		List<Event> items = events.getItems();

		if (items.size() == 0) {
			System.out.println("No upcoming events found.");
		} else {
			System.out.println("Upcoming events");
			for (Event itEvent : items) {
				DateTime eventStart = itEvent.getStart().getDateTime();
				if (eventStart == null) {
					eventStart = itEvent.getStart().getDate();
				}
				System.out.println(itEvent.getId() +" - "+ itEvent.getSummary() +" - "+ eventStart);
			}
		}
		
		event = service.events().get("primary", "18qvdga9bk39judmp3286ninko").execute();
		System.out.println(event.getId() +" - "+ event.getSummary());
		
		
	}

}