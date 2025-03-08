public class Problem {
    private int problemID;
    private String problemName;
    private String problemDescription;
    public Problem(int problemID, String problemName, String problemDescription) {
        this.problemID = problemID;
        this.problemName = problemName;
        this.problemDescription = problemDescription;
    }
    public int getProblemID() {
        return problemID;
    }
    public String getProblemName() {
        return problemName;
    }
    public String getProblemDescription() {
        return problemDescription;
    }
}
