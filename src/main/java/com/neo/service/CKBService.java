package com.neo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.model.ChildPoints;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CKBService {

    private static final String CKB_RPC_URL = "https://fullnode-rpc.testnet.nervos.org/";
    private static final String PRIVATE_KEY = "your-private-key"; // 私钥需加密存储！

    public List<ChildPoints> getChildPointsFromCKB() {
        return new ArrayList<>();
    }

    public static void tokenBalanceQuery(String[] args) throws Exception {
        String address = "nervos1qy..."; // 替换为目标地址
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(CKB_RPC_URL + "/v1/account/" + address + "/balance");

        HttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(result, Map.class);

        // 解析代币余额（示例：查询 NFT 或 Token）
        List<Map<String, Object>> outputs = (List<Map<String, Object>>) data.get("outputs");
        for (Map<String, Object> output : outputs) {
            String cellType = (String) output.get("cell_type");
            if (cellType.equals("Token")) { // 根据实际 CellType 过滤
                String tokenId = (String) output.get("token_id");
                long amount = (Long) output.get("amount");
                System.out.println("Token ID: " + tokenId + ", Amount: " + amount);
            }
        }

        httpClient.close();
    }


    public static void dataUpload(String message) throws Exception {
        // 构造 Cell 数据
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        /*CellOutput cellOutput = CellOutput.builder()
                .capacity(200000) // 单位：CKB
                .lockScript("data_hash".getBytes()) // 简化示例，需实际锁脚本
                .typeScript(data)
                .build();

        // 构造交易
        Transaction tx = Transaction.builder()
                .version(1)
                .inputs(List.of(Input.empty())) // 需替换为真实 UTXO
                .outputs(List.of(cellOutput))
                .witnesses(List.of(Witness.empty()))
                .build();

        // 签名交易
        Signer signer = new ECDSASigner(PRIVATE_KEY);
        tx.sign(signer);

        // 提交交易
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(CKB_NODE_URL + "/v1/transactions/send");
        request.setEntity(new StringEntity(tx.to_json(), ContentType.APPLICATION_JSON));

        HttpResponse response = httpClient.execute(request);
        String txHash = EntityUtils.toString(response.getEntity());
        System.out.println("Transaction Hash: " + txHash);*/
    }

}
