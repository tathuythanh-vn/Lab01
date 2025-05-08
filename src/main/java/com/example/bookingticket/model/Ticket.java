package com.example.bookingticket.model;

public class Ticket {
    private Movie movie;
    Seat seat;

    public Ticket(Movie movie, Seat seat) {
        this.movie = movie;
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
