package com.sejong.muzavis.muzavibackend.domain.classmodule.data;

import com.sejong.muzavis.muzavibackend.domain.classmodule.service.TrackService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackDataInitializer {

    private final TrackService trackService;

    @PostConstruct
    public void saveTrackSubjects() {
        // 컴퓨터공학과
        trackService.registerTrackWithSubjectNames("컴퓨터공학과", "인공지능시스템", List.of(
                "K-MOOC:모두를 위한 머신러닝", "K-MOOC:생성형 인공지능 입문", "인공지능", "패턴인식",
                "Human-AI Interaction(구: HCI 개론)", "지능형 정보검색(구: 정보검색)", "인공지능시스템",
                "지능형에이전트", "강화학습", "자연어처리", "고성능인공지능프로그래밍", "딥러닝"
        ));

        trackService.registerTrackWithSubjectNames("컴퓨터공학과", "메타버스 플랫폼", List.of(
                "K-MOOC:멀티미디어", "컴퓨터그래픽스", "디지털신호처리", "가상현실", "영상처리",
                "Human-AI Interaction(구: HCI 개론)", "웹기반시스템", "멀티모드프로그래밍",
                "컴퓨터비전(컴퓨터비전실습)", "메타버스시스템", "메타버스데이터처리", "블록체인"
        ));

        trackService.registerTrackWithSubjectNames("컴퓨터공학과", "클라우드 컴퓨팅", List.of(
                "웹프로그래밍", "문제해결실습:JAVA", "운영체제", "데이터베이스", "컴퓨터네트워크",
                "리눅스프로그래밍실습(구: Unix프로그래밍)", "웹기반시스템", "XML프로그래밍",
                "무선통신", "정보보호개론", "지능형네트워크프로그래밍(구: 네트워크프로그래밍)", "AI 네트워킹"
        ));

        // 콘텐츠소프트웨어학과
        trackService.registerTrackWithSubjectNames("콘텐츠소프트웨어학과", "공간비주얼 SW", List.of(
                "디지털이미지프로그래밍(구: 멀티미디어프로그래밍)", "일반물리및실험1", "실시간컴퓨터그래픽스",
                "응용수치해석시각화(구: 수치해석)", "실세계모델링및렌더링", "컴퓨터애니메이션", "딥러닝개론(구: 인공지능)",
                "가상현실", "컴퓨터비전캡스톤(구: 컴퓨터비전실습)", "생성AI", "디지털트윈", "증강현실"
        ));

        trackService.registerTrackWithSubjectNames("콘텐츠소프트웨어학과", "인터랙티브 플랫폼", List.of(
                "객체지향프로그래밍:C++(구: 문제해결실습:C++)", "객체지향프로그래밍:JAVA(구: 문제해결실습:JAVA)",
                "실시간컴퓨터그래픽스", "웹프로그래밍", "게임프로그래밍", "객체지향XR프로그래밍",
                "VR엔진프로그래밍", "딥러닝개론(구: 인공지능)", "자연어처리", "음성인공지능", "영상처리",
                "컴퓨터애니메이션", "웹서비스설계", "UI/UX디자인", "메타버스시스템", "HCI(구: HCI개론)"
        ));

        // 인공지능데이터사이언스학과
        trackService.registerTrackWithSubjectNames("인공지능데이터사이언스학과", "지능형에이전트", List.of(
                "기계학습개론", "고급인공지능활용", "웹프로그래밍", "딥러닝실습(구: 파이썬/딥러닝)",
                "딥러닝개론", "AI캡스톤", "시계열분석예측", "패턴인식", "자연어처리", "강화학습", "인공지능문제해결실습"
        ));

        trackService.registerTrackWithSubjectNames("인공지능데이터사이언스학과", "AI 콘텐츠", List.of(
                "기계학습개론", "인공지능수학1,2", "디지털신호처리", "딥러닝실습(구: 파이썬/딥러닝)",
                "딥러닝개론", "영상처리", "컴퓨터그래픽스", "컴퓨터비전", "AR/VR/MR", "Human-AI Interaction"
        ));

        trackService.registerTrackWithSubjectNames("인공지능데이터사이언스학과", "데이터인텔리전스", List.of(
                "기계학습개론", "데이터분석개론", "딥러닝개론", "데이터베이스", "시계열분석(구: 시계열분석예측)",
                "딥러닝실습(구: 파이썬/딥러닝)", "데이터시각화", "설명가능한인공지능", "대용량데이터처리",
                "데이터문제해결실습"
        ));
    }

}
