package net.agetsuma.jbatchdemo.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import net.agetsuma.jbatchdemo.entity.Tweet;
import net.agetsuma.jbatchdemo.entity.TweetTimes;

/**
 * Tweet情報に関するサービスクラス。
 * @author Norito AGETSUMA
 */
@Stateless
public class TweetService {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * 時間帯ごとのTweet件数リストを取得します。
     * @return 時間帯ごとのTweet件数リスト
     */
    public List<TweetTimes> getTweetTimes() {
        TypedQuery<TweetTimes> query = em.createNamedQuery("TweetTimes.findAll", TweetTimes.class);
        return query.getResultList();
    }
    
    /**
     * データベース上のTweet情報および、時間帯別Tweet回数情報を
     * クリアします。
     */
    public void clear() {
        em.createNamedQuery("Tweet.deleteAll", Tweet.class).executeUpdate();
        em.createNamedQuery("TweetTimes.deleteAll", TweetTimes.class).executeUpdate();
    }
    
}
