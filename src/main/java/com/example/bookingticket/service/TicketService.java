package com.example.bookingticket.service;

import com.example.bookingticket.model.Seat;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketService {
    private final ReentrantLock lock = new ReentrantLock();

    public boolean reserveSeat(Seat seat) {
        lock.lock();
        try {
            if (seat.isAvailable()) {
                seat.reserve(); // Đánh dấu ghế là đã bán
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
