package com.github.martinhermes86.asterixapi;

import lombok.With;
import org.springframework.data.annotation.Id;

@With
public record Character(@Id String id, String name, Integer age, String occupation) {
}
