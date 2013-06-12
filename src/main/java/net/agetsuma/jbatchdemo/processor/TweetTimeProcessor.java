/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.jbatchdemo.processor;

import java.util.Calendar;
import java.util.Date;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import net.agetsuma.jbatchdemo.entity.Hours;
import net.agetsuma.jbatchdemo.entity.Tweet;

/**
 * 取得したツイート情報が４つの時間帯のうち
 * どれにあたるかを判定します。
 * @author Norito AGETSUMA
 */
@Named
public class TweetTimeProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Tweet tweet = (Tweet)item;
        Date createdAt = tweet.getCreatedAt();
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdAt);
        int tweetHourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        
        return hours(tweetHourOfDay);
    }
    
    private Hours hours(int hourOfDay) {
        // TODO もっと綺麗な書き方もあると思う
        if ((0 <= hourOfDay) && (hourOfDay <= 5)) {
            return Hours.MIDNIGHT;
        } else if ((6 <= hourOfDay) && (hourOfDay <= 11)) {
            return Hours.MORING;
        } else if ((12<= hourOfDay) && (hourOfDay <= 17)) {
            return Hours.NOON;
        } else {
            return Hours.EVENING;
        }
    }
    
}
