package com.example.demo.service.recommendCalc;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

@Service
public class GetDistance {

    @Value("${security.neo4j.uri}")
    private static String neo4jUri;

    @Value("${security.neo4j.user}")
    private static String neo4jUser;

    @Value("${security.neo4j.password}")
    private static String neo4jPassword;


    public static float getDistance(String stack1, String stack2){

        float distance=0;

        try (Driver driver = GraphDatabase.driver(neo4jUri, AuthTokens.basic(neo4jUser, neo4jPassword));
             Session session = driver.session()) {
            String start="MATCH path=allShortestPaths((startNode:techStack {techStack: '";
            String middle="'})-[:relation*..10]-(endNode:techStack {techStack: '";
            String end="'}))" +
                    "RETURN REDUCE(s = 0, rel IN relationships(path) | s + rel.weight) AS totalWeight";

            String query=start+stack1+middle+stack2+end;

            Result result = session.run(query);

            Record record = result.next();
            org.neo4j.driver.Value totalWeight =  record.get("totalWeight");
            distance=totalWeight.asFloat();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return distance;
    }
}