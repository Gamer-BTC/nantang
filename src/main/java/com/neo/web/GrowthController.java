package com.neo.web;


/**
 * 儿童成长空间 controller
 */

import com.neo.base.GlobalCache;
import com.neo.model.ArtNFT;
import com.neo.model.ChildPoints;
import com.neo.model.GrowthRecord;
import com.neo.service.CKBService;
import com.neo.service.NostrService;
import nostr.base.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GrowthController {

    static GlobalCache<String, List<GrowthRecord>> cache = new GlobalCache<>();

    static GlobalCache<String, List<ArtNFT>> artCache = new GlobalCache<>();

    static GlobalCache<String, List<ChildPoints>> childrenPointsCache = new GlobalCache<>();

    @Autowired
    private CKBService ckbService;

    static {
        List<GrowthRecord> growthRecords = new ArrayList<>();
        growthRecords.add(new GrowthRecord("2025-03-20", "Hello, 南塘儿童空间第一次!"));
        growthRecords.add(new GrowthRecord("2025-03-20", "Hello, 南塘儿童空间第一次!"));
        growthRecords.add(new GrowthRecord("2025-03-20", "Hello, 南塘儿童空间第一次!"));
        growthRecords.add(new GrowthRecord("2025-03-20", "小美今天过生日!"));
        growthRecords.add(new GrowthRecord("2025-03-20", "小美今天过生日!"));
        growthRecords.add(new GrowthRecord("2025-03-21", "2025-03-21"));
        growthRecords.add(new GrowthRecord("2025-03-21", "2025-03-21"));
        growthRecords.add(new GrowthRecord("2025-03-21", "小芳你好"));
        cache.put("GrowthRecord", growthRecords);

        List<ArtNFT> artNFTs = Arrays.asList(new ArtNFT("艺术作品1", "https://a-simple-demo.spore.pro/api/media/0x3eb3c6de24a0ed0a57c1f3e84e22ffa7fa59b30cec516ff58f32507d95a43196"), new ArtNFT("艺术作品2", "https://a-simple-demo.spore.pro/api/media/0x3eb3c6de24a0ed0a57c1f3e84e22ffa7fa59b30cec516ff58f32507d95a43196"));

        artCache.put("ArtNFTs", artNFTs);

        List<ChildPoints> childrenPoints = Arrays.asList(
                new ChildPoints("张三", 120, 80),
                new ChildPoints("李四", 150, 95),
                new ChildPoints("王五", 200, 110)
        );
        childrenPointsCache.put("childrenPoints", childrenPoints);

    }



    @RequestMapping("/index")
    public String display(Model model) {
        // 模拟多个儿童的积分和经验数据
        List<ChildPoints> childrenPoints = childrenPointsCache.get("childrenPoints");
        List<ArtNFT> artNFTs = artCache.get("ArtNFTs");
        List<GrowthRecord> growthRecords = cache.get("GrowthRecord");

        model.addAttribute("childrenPoints", childrenPoints);
        model.addAttribute("artNFTs", artNFTs);
        model.addAttribute("growthRecords", growthRecords);

        return "index";
    }

    @RequestMapping("/record")
    public String record(Model model) {

        return "record";
    }

    // 处理更新请求
    @PostMapping("/update")
    public String updateGrowthRecord(@RequestParam String record,
                                     @RequestParam String date,
                                     @RequestParam(required = false) boolean permanentStorage,
                                     Model model) {
        // 创建并保存儿童成长记录
        GrowthRecord updatedRecord = new GrowthRecord(date, record);

        // 根据是否永久存储来决定是否存储到ckb上
        if (permanentStorage) {
            // 这里假设永久存储，实际可以保存到数据库
            System.out.println("记录已永久存储: " + ", "
                    + updatedRecord.getRecord() + ", " + updatedRecord.getDate());
        }
        NostrService.sendTextNoteEvent(updatedRecord.getRecord());


        List<GrowthRecord> records = cache.get("GrowthRecord");

        if (records == null){
            records = new ArrayList<>();
        }

        records.add(updatedRecord);
        cache.put("GrowthRecord", records);
        System.out.println("记录未永久存储: "  + ", "
                + updatedRecord.getRecord() + ", " + updatedRecord.getDate());
        // 将更新后的记录添加到模型，并返回确认页面
        model.addAttribute("growthRecord", updatedRecord);
        return "record";  // 返回更新确认页面
    }
}

