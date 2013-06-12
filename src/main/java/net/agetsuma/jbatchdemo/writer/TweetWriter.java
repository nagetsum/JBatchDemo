package net.agetsuma.jbatchdemo.writer;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.agetsuma.jbatchdemo.entity.Tweet;
import twitter4j.Status;

/**
 * Chunk処理のItemWriter実装。
 * Tweet情報をデータベースに書き込みます。
 * @author Norito AGETSUMA
 */
@Named
public class TweetWriter extends AbstractItemWriter {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void writeItems(List<Object> items) throws Exception {
       for (Object obj : items) {
           Status status = (Status)obj;
           Tweet tweet = new Tweet(status.getId(), status.getCreatedAt(), status.getText());
           em.persist(tweet);
       }
    }
    
    // open close checkpointは空実装。
    
}
