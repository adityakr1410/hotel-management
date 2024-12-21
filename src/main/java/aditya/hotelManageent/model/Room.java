package aditya.hotelManageent.model;


import jakarta.persistence.*;

@Entity(name = "Room")
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "room_no")
    private int roomNo;

    @Column(name = "is_ac")
    private int isAc;

    @Column(name = "is_deluxe")
    private int isDeluxe;

    @ManyToOne
    @JoinColumn(name = "assigned_customer_id", referencedColumnName = "id", nullable = true)
    private Customer assignedCustomer;


    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = true)
    private Booking bookingId;

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getIsAc() {
        return isAc;
    }

    public void setIsAc(int isAc) {
        this.isAc = isAc;
    }

    public int getIsDeluxe() {
        return isDeluxe;
    }

    public void setIsDeluxe(int isDeluxe) {
        this.isDeluxe = isDeluxe;
    }

    public Customer getAssignedCustomer() {
        return assignedCustomer;
    }

    public void setAssignedCustomer(Customer assignedCustomer) {
        this.assignedCustomer = assignedCustomer;
    }
}
