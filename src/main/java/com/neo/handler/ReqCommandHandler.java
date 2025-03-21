package com.neo.handler;

import nostr.base.Command;
import nostr.base.annotation.DefaultHandler;
import nostr.command.CommandHandler;
import nostr.context.CommandContext;
import nostr.context.impl.DefaultCommandContext;
import nostr.event.BaseMessage;


//处理message
@DefaultHandler(command = Command.EVENT)
public class ReqCommandHandler implements CommandHandler {

    @Override
    public void handle(CommandContext context) {
        if (context instanceof DefaultCommandContext){
           BaseMessage message = ((DefaultCommandContext) context).getMessage();
           //message.get
        }
        System.out.println(context);
    }
}
