package com.Ambalaj.Ambalaj.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanPriorityNumber {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    private final int value;

    public static PlanPriorityNumber getByValue(int value) {
        for (PlanPriorityNumber priority : PlanPriorityNumber.values()) {
            if (priority.getValue() == value) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid plan priority value: " + value);
    }
}
