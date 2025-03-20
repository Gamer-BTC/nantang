package com.neo.web;


/**
 * 儿童成长空间 controller
 */

import com.neo.model.ArtNFT;
import com.neo.model.ChildPoints;
import com.neo.model.GrowthRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class GrowthController {

    @RequestMapping("/index")
    public String display(Model model) {
        // 模拟多个儿童的积分和经验数据
        List<ChildPoints> childrenPoints = Arrays.asList(
                new ChildPoints("张三", 120, 80),
                new ChildPoints("李四", 150, 95),
                new ChildPoints("王五", 200, 110)
        );
        List<ArtNFT> artNFTs = Arrays.asList(new ArtNFT("艺术作品1", "https://a-simple-demo.spore.pro/api/media/0x3eb3c6de24a0ed0a57c1f3e84e22ffa7fa59b30cec516ff58f32507d95a43196"), new ArtNFT("艺术作品2", "https://a-simple-demo.spore.pro/api/media/0x3eb3c6de24a0ed0a57c1f3e84e22ffa7fa59b30cec516ff58f32507d95a43196"));
        List<GrowthRecord> growthRecords = Arrays.asList(
                new GrowthRecord("2025-03-01", "成长记录1"),
                new GrowthRecord("2025-03-02", "成长记录2")
        );

        model.addAttribute("childrenPoints", childrenPoints);
        model.addAttribute("artNFTs", artNFTs);
        model.addAttribute("growthRecords", growthRecords);

        return "index";
    }
}

