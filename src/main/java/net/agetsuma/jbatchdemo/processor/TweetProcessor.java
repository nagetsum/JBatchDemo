/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.jbatchdemo.processor;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import twitter4j.Status;

/**
 * Chunk処理のItemProcessor実装。
 * 特にやることはありません。
 * @author Norito AGETSUMA
 */
@Named
public class TweetProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        Status status = (Status)item;
        return item;
    }
    
}
