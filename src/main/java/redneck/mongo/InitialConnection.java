package redneck.mongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * The basics, from the Mongo Java driver example page. Assumes MongoDB is
 * installed and running on the current system. <a href=
 * "http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/#getting-started-with-java-driver"
 * >Source</a>
 * 
 * @author Robb
 * 
 */
public class InitialConnection {

	private final static Logger log = Logger.getLogger(InitialConnection.class);

	private final static String MONGO_HOST = "localhost"; // Default
	private final static int MONGO_PORT = 27017; // Default

	/**
	 * First stab hack and whack, no tests or anything. Just get it up and running.
	 * 
	 * @throws UnknownHostException
	 */
	public void run() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
		// by default, Mongo starts up with a "test" DB, just use that for now....
		DB db = mongoClient.getDB("test");

		Set<String> colls = db.getCollectionNames();

		for (String s : colls) {
			log.info(String.format("Collection: %s", s));
		}

		if (!db.collectionExists("names"))
			db.createCollection("names", null);

		DBCollection names = db.getCollection("names");
		Date today = new Date();
		BasicDBObject testName = new BasicDBObject("name", "Testing123").append("create_date", today);
		names.insert(testName);

		DBCursor cursor = names.find();
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			log.info(String.format(
					"Collection has this name: %s, with creation date of %s",
					o.get("name"), o.get("create_date")));
		}

		names.remove(testName);
	}

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		new InitialConnection().run();
	}

}
