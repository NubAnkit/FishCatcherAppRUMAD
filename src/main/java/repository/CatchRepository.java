package repository;

import model.Catch;
import model.FilterCatch;

import java.util.ArrayList;
import java.util.Iterator;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;


public class CatchRepository {
	private DynamoDB dynamoDb; 
	private String DYNAMODB_TABLE_NAME = "Catches";
	private Regions REGION = Regions.US_EAST_1;
	
	private void initDynamoDbClient() {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
	
	public CatchRepository() {
		initDynamoDbClient();
	}
	
	public ArrayList<Catch> filteredCatches(FilterCatch filter){
		Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME); 
		ArrayList<Catch> catches = new ArrayList<Catch>(); 
		ScanSpec scanSpec = new ScanSpec().withProjectionExpression(filter.projectionExpression())
				.withFilterExpression(filter.filterExpression())
				.withValueMap(filter.filterMap());
	
			ItemCollection<ScanOutcome> items = table.scan(scanSpec); 
			Iterator<Item> iter = items.iterator(); 
			
			while(iter.hasNext()) {
				Item item = iter.next(); 
				Catch catch1 = new Catch();
				catch1.setUserId(item.getString("userId"));
				catch1.setBaro(item.getString("baro"));
				catch1.setTemp(item.getString("tempr"));
				catch1.setHumid(item.getString("humid"));
				catch1.setLat(item.getString("lat"));
				catch1.setLon(item.getString("lon"));
				catch1.setSpecies(item.getString("species")); 
				catch1.setCity(item.getString("city"));;
				catch1.setLength(item.getString("length1"));
				catch1.setWidth(item.getString("width"));
				catch1.setWeight(item.getString("weight"));
				catch1.setDate(item.getString("date1"));
				catches.add(catch1); 
			}
		
										
		return catches; 
		
	}


	public PutItemOutcome save(Catch caught) {
		
		Item item = new Item();
		item.withPrimaryKey("userId", caught.getUserId());
		item.withNumber("tempr", toDouble(caught.getTemp()));
		item.withNumber("humid", toDouble(caught.getHumid()));
		item.withNumber("baro", toDouble(caught.getBaro()));
		item.withString("lat", caught.getLat());
		item.withString("lon", caught.getLon());
		item.withString("city", caught.getCity());
		item.withString("species", caught.getSpecies());
		item.withNumber("length1", toDouble(caught.getLength()));
		item.withNumber("width", toDouble(caught.getWidth()));
		item.withNumber("weight", toDouble(caught.getWeight()));
		item.withString("date1", caught.getDate()); 

		
		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).putItem(new PutItemSpec().withItem(item));
	
	}
	
	public double toDouble(String a) {
		return Double.parseDouble(a);
	}
	
	

}
