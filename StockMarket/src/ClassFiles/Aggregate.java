package ClassFiles;

import java.util.Arrays;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Aggregate {

	public static Object AggregateDocument(String input) {
		
		//Get mongo set up
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("market");
		MongoCollection<Document> collection = database.getCollection("stocks");
		
		//Get the documents set up for each section of the aggregate command
		//Set up the Match
		Document match = new Document("$match", new Document("Sector", input));
		//Set up the Project
		Document project1 = new Document("_id", 1).append("Shares Outstanding", 1).append("Industry", 1).append("Sector", 1);
		Document project = new Document("$project", project1);
		//Set up the Group
		Document group1 = new Document("_id", "$Industry").append("Total Outstanding Shares", new Document("$sum", "$Shares Outstanding"));
		Document group = new Document("$group", group1);
				
		//Call the aggregate command
		AggregateIterable<Document> agg = collection.aggregate(Arrays.asList(match, project, group));

		//Print out the result
		for (Document printAggs : agg)
		{
		    System.out.println(printAggs);
		}
	    
	    return agg;
	}
}
