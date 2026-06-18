import java.time.LocalDate;

public class WaitingRequest {
    private int studentID;
    private String studentName;
    private int bookISBN;
    private boolean isGraduate;
    private LocalDate requestDate;

    public WaitingRequest(int studentID, String studentName,int bookISBN,boolean isGraduate,LocalDate requestDate){
        this.studentID=studentID;
        this.studentName=studentName;
        this.bookISBN=bookISBN;
        this.isGraduate=isGraduate;
        this.requestDate=requestDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(int bookISBN) {
        this.bookISBN = bookISBN;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public void setGraduate(boolean graduate) {
        isGraduate = graduate;
    }
}
