package com.neo.service;


import nostr.api.NIP01;
import nostr.base.UserProfile;
import nostr.event.BaseTag;
import nostr.event.impl.TextNoteEvent;
import nostr.event.tag.PubKeyTag;
import nostr.id.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NostrService {


    private static final Identity RECIPIENT = Identity.create("54d6e4a483ab0172a4031baba78c830919abe9311b7f590d0c737f65453877c7");
    private static final Identity SENDER = Identity.create("7876a3f9a1830e2b0b5a8cbba7bba819eae23591e7970cab1f3f29de305bb964");

    private static final UserProfile PROFILE = new UserProfile(SENDER.getPublicKey(), "Nostr Guy", "guy@nostr-java.io", "It's me!", null);
    private final static Map<String, String> RELAYS = Map.of("lol", "nos.lol", "damus", "relay.damus.io", "ZBD",
            "nostr.zebedee.cloud", "taxi", "relay.taxi", "mom", "nostr.mom");


    private static TextNoteEvent sendTextNoteEvent() {

        List<BaseTag> tags = new ArrayList<>(List.of(new PubKeyTag(RECIPIENT.getPublicKey())));

        NIP01<TextNoteEvent> nip01 = new NIP01<TextNoteEvent>(SENDER);
        nip01.createTextNoteEvent(tags, "Hello world, I'm here on nostr-java API!")
                .sign()
                .send(RELAYS);

        return nip01.getEvent();
    }

    public static void main(String[] args) {
        Identity RECIPIENT = Identity.generateRandomIdentity();
        System.out.println(RECIPIENT.getPrivateKey());
    }


}
