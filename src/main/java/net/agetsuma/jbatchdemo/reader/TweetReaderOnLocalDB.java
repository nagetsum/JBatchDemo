package net.agetsuma.jbatchdemo.reader;

import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.queries.ScrollableCursor;

/**
 * EclipseLinkのカーソル機能を使って、
 * １件ずつDBからツイート情報を読み込みます。
 * @author Norito AGETSUMA
 */
@Named
public class TweetReaderOnLocalDB extends AbstractItemReader {
    
    @PersistenceContext
    private EntityManager em;
    
    private ScrollableCursor scrollableCursor;
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        // ScrollableCursorにキャストするため、TypedQueryは使用しない
        Query query = em.createQuery("select t from Tweet t");
        query.setHint("eclipselink.cursor.scrollable", true);
        scrollableCursor = (ScrollableCursor)query.getSingleResult();
    }

    @Override
    public Object readItem() throws Exception {
        if (scrollableCursor.hasNext()) {
            return scrollableCursor.next();
        }
        return null;
    }
    
    @Override
    public void close() throws Exception {
        scrollableCursor.close();
    }
    
}
