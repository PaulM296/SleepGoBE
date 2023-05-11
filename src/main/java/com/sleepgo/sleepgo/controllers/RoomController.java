package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomTypeNotFound;
import com.sleepgo.sleepgo.models.RoomModel;
import com.sleepgo.sleepgo.services.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @GetMapping("/{id}")
    public RoomModel getRoomById(@PathVariable("id") int roomId) throws RoomNotFoundException {
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/hotel/{id}")
    public List<RoomModel> getRoomsByHotelId(@PathVariable("id") int hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @PutMapping("/{id}")
    public RoomModel updateRoom(@PathVariable("id") int roomId, @RequestBody RoomModel room) throws RoomNotFoundException, HotelNotFoundException {
        return roomService.updateRoom(roomId, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") int id) throws RoomNotFoundException {
        roomService.deleteRoom(id);
    }

    @GetMapping("/roomtype/{roomType}")
    public List<RoomModel> findAllRoomsByRoomType(@PathVariable String roomType) throws RoomTypeNotFound {
        return roomService.findAllRoomsByRoomType(roomType);
    }
}
