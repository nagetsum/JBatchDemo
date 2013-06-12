/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.jbatchdemo.job;

/**
 * ジョブ一覧を示すenum。
 * @author Norito AGETSUMA
 */
public enum Jobs {
    COLLECT_TWEET("correct_tweet");
    
    private final String jobId;
    
    Jobs(String jobId) {
        this.jobId = jobId;
    }
    
    @Override
    public String toString() {
        return jobId;
    }
}
