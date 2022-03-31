package server.api;

import commons.Emoji;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmojiController {
    @MessageMapping("/emojis") //app/emojis
    @SendTo("/topic/emojis")
    public Emoji addEmoji( Emoji e){
        return e;
    }


}
