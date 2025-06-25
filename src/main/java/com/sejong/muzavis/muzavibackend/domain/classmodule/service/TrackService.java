package com.sejong.muzavis.muzavibackend.domain.classmodule.service;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Track;
import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.TrackSubject;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.TrackRepository;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.TrackSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final TrackSubjectRepository trackSubjectRepository;

    @Transactional
    public void registerTrackWithSubjectNames(String department, String trackName, List<String> subjectNames) {
        Track track = trackRepository.findByName(trackName)
                .orElseGet(() -> {
                    Track newTrack = new Track();
                    newTrack.setName(trackName);
                    newTrack.setDepartment(department);
                    return trackRepository.save(newTrack);
                });

        for (String subjectName : subjectNames) {
            TrackSubject subject = new TrackSubject();
            subject.setSubjectName(subjectName);
            subject.setTrack(track);
            trackSubjectRepository.save(subject);
        }
    }


}
