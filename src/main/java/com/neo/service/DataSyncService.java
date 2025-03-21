package com.neo.service;

import com.neo.model.ChildPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSyncService {

    @Autowired
    private CKBService ckbService;

    @Autowired
    private NostrService nostrService;

    // 定期同步数据的任务
    //@Scheduled(fixedRate = 30000)  // 每30秒执行一次
    public void syncData() {
        try {
            List<ChildPoints> ckbChildPoints = ckbService.getChildPointsFromCKB();
            List<ChildPoints> nostrChildPoints = nostrService.getChildPointsFromNostr();

            // 校验CKB和Nostr上的数据一致性
            for (ChildPoints ckbChild : ckbChildPoints) {
                for (ChildPoints nostrChild : nostrChildPoints) {
                    if (ckbChild.getName().equals(nostrChild.getName())) {
                        if (!(ckbChild.getPoints()==(nostrChild.getPoints()) ||
                                !(ckbChild.getExperience()==(nostrChild.getExperience())))) {
                            // 数据不一致，进行同步
                            nostrService.updateChildPoints(nostrChild);  // 假设Nostr服务提供了更新接口
                        }
                    }
                }
            }
        } catch (Exception e) {
            //log.error("同步数据时发生错误: ", e);
        }
    }

    // 当Nostr数据更新时，触发CKB数据更新
    /*public void onNostrUpdate(NostrChildPoints nostrChildPoints) {
        try {
            // 根据Nostr数据更新CKB
            ckbService.updateChildPointsInCKB(nostrChildPoints);
        } catch (Exception e) {
            log.error("更新CKB数据时发生错误: ", e);
        }
    }*/
}

