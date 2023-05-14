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
@RequestMapping("/api/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @GetMapping("/{id}")
    public RoomModel getRoomById(@PathVariable("id") int roomId) throws RoomNotFoundException {
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/getByHotelId")
    public List<RoomModel> getRoomsByHotelId(@RequestParam int hotelId) {
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

    @PostMapping
    public RoomModel addRoom(@RequestBody RoomModel room) throws HotelNotFoundException {
        return roomService.addRoom(room);
    }
}
