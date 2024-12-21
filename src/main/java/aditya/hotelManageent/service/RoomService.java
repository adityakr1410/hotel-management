package aditya.hotelManageent.service;


import aditya.hotelManageent.model.Room;
import aditya.hotelManageent.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(Long id){
        return roomRepository.findById(id);
    }

    public Room save(Room room){
        return roomRepository.save(room);
    }

    public void deleteById(Long id){
        roomRepository.deleteById(id);
    }


}
