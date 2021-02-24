import java.util.ArrayList;
import java.util.function.UnaryOperator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CardParser 
{

	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>();
		
		URLReader hearthstoneURLReader = new URLReader (this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		
		
		if(obj instanceof JSONArray)
		{
			JSONArray array = (JSONArray)obj;
			
			for(int i = 0 ; i< array.size(); i++)
			{
				JSONObject cardData = (JSONObject)array.get(i);
				if(cardData.containKey("cost") && cardData.containKey("name"));
				{
					if(cardData.containKey("type") && cardData.get("type").equarls("MINION"));
					{
						String name = (String)cardData.get("name");
						int cost = Integer.parseInt(cardData.get("cost").toStiring());
						int attack = Integer.parseInt(cardData.get("attack").toString());
						int defense = Integer.parseInt(cardData.get("defense").toString());
						HearthstoneCard temp = new HearthstoneCard(name, cost, attack, defense);
						theMinions.add(temp);
					}
				}
			}
		}
	}
	/*
	int highestCost(String urlString)
	{
		URLReader hearthstoneURLReader = new URLReader (this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		JSONArray array = (JSONArray)obj;
		
		int Hcost = 0;
		
		for(int i = 0; i<array.size()-1; i++)
		{
			JSONObject cardData1 = (JSONObject)array.get(i);
			JSONObject cardData2 = (JSONObject)array.get(i+1);
			if(cardData1.get("cost")<cardData2.get("cost"))
			{
				Hcost = Integer.parseInt(cardData2.get("cost").toStiring());	
			}
			else
			{
				Hcost = Integer.parseInt(cardData1.get("cost").toStiring());
			}
			
		}
		
		return Hcost;
	}
	*/
	void sortLowerCostToHighestCost()
	{
		for (int i =0; i < theMinions.size(); i++ )
		{
			for (int j = i+1; j < theMinions.size(); j++)
			{
				JSONArray cardDataA = (JSONObject)theMinions.get(i);
				JSONArray cardDataB = (JSONObject)theMinions.get(j);
				if(cardDataA.get("cost")<cardDataB.get("cost"))
				{
					JSONArray temp = theMinions.get(i);
					theMinions.remove(i);
					theMinions.add(i ,theMinions.get(j));
					theMinions.add(j,temp);
				}
			}	
		}
		
		for(int k = 0; k< this.theMinions.size(); k++)
		{
			this.theMinions.get(k).display();
		}
		
	}
	
}
