package com.neo.listener;

import nostr.context.Context;

import nostr.event.json.codec.BaseMessageDecoder;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class NostrListener extends WebSocketListener {



    private Context context;

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        // nocheckin
        System.out.println(message);
        handleReceivedText(message);
    }


    private void handleReceivedText(String message) {
        var msg = new BaseMessageDecoder<>().decode(message);
        System.out.println(msg);
    }

}

