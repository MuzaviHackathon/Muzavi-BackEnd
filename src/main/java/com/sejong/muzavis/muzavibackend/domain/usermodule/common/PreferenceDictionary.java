package com.sejong.muzavis.muzavibackend.domain.usermodule.common;

import java.util.Map;
import java.util.Set;

public final class PreferenceDictionary {
    /** 카테고리 → 허용 옵션 Set */
    public static final Map<String, Set<String>> CATEGORIES = Map.of(
            "강의력", Set.of("강의력좋음", "강의력보통", "강의력나쁨"),
            "과제",   Set.of("과제많음", "과제보통", "과제적음"),
            "팀플",   Set.of("팀플많음", "팀플보통", "팀플적음"),
            "시험",   Set.of("시험어려움", "시험쉬움"),
            "출결",   Set.of("출결직접호명", "출결유체크")
    );

    private PreferenceDictionary() {} // util
}
