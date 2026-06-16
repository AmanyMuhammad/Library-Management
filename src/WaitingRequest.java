public class WaitingRequest {
    private String studentName;
    private int bookISBN;
    private boolean isGraduate;

    public WaitingRequest(String studentName,int bookISBN,boolean isGraduate){
        this.studentName=studentName;
        this.bookISBN=bookISBN;
        this.isGraduate=isGraduate;
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
