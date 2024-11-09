package it.upgraded.winner.features.player;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.jboss.logging.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;

import it.upgraded.winner.features.player.rest.PlayerRestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlayerService {

    private final MongoClient mongoClient;
    private static final Logger logger = Logger.getLogger(PlayerService.class);

    @Inject
    public PlayerService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void savePlayer(PlayerRestDto player) {
        logger.info(player);
        Document document = new Document().append("name", player.getName()).append("role", player.getRole())
                .append("team", player.getTeam());
        InsertOneResult insertOne = getCollection().insertOne(document);
        logger.info(insertOne);
    }

    public List<PlayerRestDto> fetchPlayerByName() {
        List<PlayerRestDto> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                PlayerRestDto player = new PlayerRestDto();
                player.setName(document.getString("name"));
                player.setRole(document.getString("role"));
                player.setTeam(document.getString("team"));
                list.add(player);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void updatePlayer() {
        throw new IllegalStateException("Not implemented yet");
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("upgraded-winner").getCollection("players");
    }

}
