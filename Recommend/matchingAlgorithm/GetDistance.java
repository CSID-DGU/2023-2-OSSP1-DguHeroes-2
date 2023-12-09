import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;

public class GetDistance {
  public static double getDistance(String stack1, String stack2){
    String uri = "neo4j+s://1fc435f2.databases.neo4j.io";
    String user = "neo4j";
    String password = "hgjtCyWMi5hVUIHhc0Au_VLVcOGkxBe0FM02DqUoX7k";
    double distance=0;

    try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        Session session = driver.session()) {
      String start="MATCH path=allShortestPaths((startNode:techStack {techStack: '";
      String middle="'})-[:relation*..10]-(endNode:techStack {techStack: '";
      String end="'}))" +
          "RETURN REDUCE(s = 0, rel IN relationships(path) | s + rel.weight) AS totalWeight";

      String query=start+stack1+middle+stack2+end;

      Result result = session.run(query);

      Record record = result.next();
      Value totalWeight = record.get("totalWeight");
      System.out.println("Total Weight: " + totalWeight.asDouble());
      distance=totalWeight.asDouble();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return distance;
  }
}