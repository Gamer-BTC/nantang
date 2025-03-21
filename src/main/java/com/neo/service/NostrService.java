package com.neo.service;


import com.neo.model.ChildPoints;
import nostr.api.NIP01;
import nostr.base.UserProfile;
import nostr.event.BaseTag;
import nostr.event.Kind;
import nostr.event.impl.Filters;
import nostr.event.impl.TextNoteEvent;
import nostr.event.tag.PubKeyTag;
import nostr.id.Identity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class NostrService {


    private static final Identity RECIPIENT = Identity.create("54d6e4a483ab0172a4031baba78c830919abe9311b7f590d0c737f65453877c7");
    private static final Identity SENDER = Identity.create("7876a3f9a1830e2b0b5a8cbba7bba819eae23591e7970cab1f3f29de305bb964");

    private static final UserProfile PROFILE;
    private final static Map<String, String> RELAYS = Map.of("damus", "wss://relay.damus.io");

    static {
        try {
            PROFILE = new UserProfile(SENDER.getPublicKey(), "Nostr Guy", "guy@nostr-java.io", "It's me!", new URL("https://a-simple-demo.spore.pro/api/media/0x3eb3c6de24a0ed0a57c1f3e84e22ffa7fa59b30cec516ff58f32507d95a43196"));
            filters();
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public static TextNoteEvent sendTextNoteEvent(String message) {

        List<BaseTag> tags = new ArrayList<>(List.of(new PubKeyTag(RECIPIENT.getPublicKey())));

        NIP01<TextNoteEvent> nip01 = new NIP01<TextNoteEvent>(SENDER);
        nip01.createTextNoteEvent(tags, message)
                .sign()
                .send(RELAYS);

        return nip01.getEvent();
    }


    //监听消息
    public static void filters() throws InterruptedException {

        var kinds = List.of(Kind.EPHEMEREAL_EVENT, Kind.TEXT_NOTE);
        var authors = new ArrayList<>(List.of(SENDER.getPublicKey()));

        var date = Calendar.getInstance();
        var subId = "subId" + date.getTimeInMillis();
        date.add(Calendar.DAY_OF_MONTH, -5);
        Filters filters = Filters.builder()
                .authors(authors)
                .kinds(kinds)
                .since(date.getTimeInMillis()/1000)
                .build();

        NIP01<TextNoteEvent> nip01 = new NIP01<TextNoteEvent>(SENDER);
        nip01.setRelays(RELAYS).send(filters, subId);
        System.out.println(nip01.getEvent());
        Thread.sleep(5000);
    }

    public static void main(String[] args) throws InterruptedException {
       // sendTextNoteEvent();
        filters();
        System.out.println();
    }


    public List<ChildPoints> getChildPointsFromNostr() {
        return new ArrayList<>();
    }

    public void updateChildPoints(ChildPoints nostrChild) {
    }
}
