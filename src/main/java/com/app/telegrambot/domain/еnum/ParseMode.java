package com.app.telegrambot.domain.еnum;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ParseMode {
    @JsonProperty("MarkdownV2") MARKDOWN_V2,
    @JsonProperty("HTML") HTML,
    @JsonProperty("Markdown") MARKDOWN
}
