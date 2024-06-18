package org.example.hospital_imtihon.service;

import lombok.RequiredArgsConstructor;
import org.example.hospital_imtihon.entity.Room;
import org.example.hospital_imtihon.repo.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
    public Room findById(Integer roomId) {
         return roomRepository.findById(roomId).get();
    }
}
