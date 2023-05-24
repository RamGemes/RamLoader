package net.ramgames.modloader;

import java.util.List;

public record DisplayMod(String name,
                         String description,
                         String author,
                         boolean isClientOnly,
                         String modLoader,
                         int downloads,
                         String href,
                         List<String> tags

) {
}
