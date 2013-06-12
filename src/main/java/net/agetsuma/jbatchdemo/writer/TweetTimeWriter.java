/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.jbatchdemo.writer;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.agetsuma.jbatchdemo.entity.Hours;
import net.agetsuma.jbatchdemo.entity.TweetTimes;

/**
 * 時間帯ごとのツイート回数情報を更新する。
 * @author Norito AGETSUMA
 */
@Named
public class TweetTimeWriter extends AbstractItemWriter {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object obj : items) {
            Hours hour = (Hours)obj;
            TweetTimes tweetTimes = em.find(TweetTimes.class, hour);
            if (tweetTimes == null) {
                tweetTimes = new TweetTimes(hour, 0);
                em.persist(tweetTimes);
            }
            tweetTimes.add();
        }
    }
    
}
