public class Solution {
    private int SolutionID;
    private String SolutionDescription;
    public Solution(int SolutionID, String SolutionDescription) {
        this.SolutionID = SolutionID;
        this.SolutionDescription = SolutionDescription;
    }
    public int getSolutionID() {
        return SolutionID;
    }
    public String getSolutionDescription() {
        return SolutionDescription;
    }
}
