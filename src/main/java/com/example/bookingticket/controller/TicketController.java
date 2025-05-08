package com.example.bookingticket.controller;

import com.example.bookingticket.model.Movie;
import com.example.bookingticket.model.Seat;
import com.example.bookingticket.repository.SeatRepository;
import com.example.bookingticket.service.MovieService;
import com.example.bookingticket.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    private final TicketService ticketService;
    private final MovieService movieService;
    private final SeatRepository seatRepo;

    public TicketController(TicketService ticketService, MovieService movieService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.seatRepo = new SeatRepository(48);
    }

    @GetMapping("/movie")
    public String movieSelection(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie";
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) Long movieId, Model model) {
        if (movieId == null) {
            return "redirect:/movie";
        }
        
        Movie selectedMovie = movieService.getMovieById(movieId);
        if (selectedMovie == null) {
            return "redirect:/movie";
        }
            
        model.addAttribute("movie", selectedMovie);
        model.addAttribute("seats", seatRepo.getSeats(movieId));
        return "booking";
    }

    @PostMapping("/reserve")
    public String reserveSeat(@RequestParam Long movieId, @RequestParam Integer seatNumber, Model model) {
        if (seatNumber == null || movieId == null) {
            return "redirect:/movie";
        }

        Movie selectedMovie = movieService.getMovieById(movieId);
        if (selectedMovie == null) {
            return "redirect:/movie";
        }

        Seat seat = seatRepo.getSeat(movieId, seatNumber);
        if (seat == null) {
            return "redirect:/movie";
        }

        boolean success = ticketService.reserveSeat(seat);
        if (success) {
            model.addAttribute("message", "Booking successful for seat " + seatNumber);
        } else {
            model.addAttribute("message", "Seat " + seatNumber + " is already booked.");
        }
        
        model.addAttribute("movie", selectedMovie);
        model.addAttribute("seats", seatRepo.getSeats(movieId));
        return "booking";
    }
}
