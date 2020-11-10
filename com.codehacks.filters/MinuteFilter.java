/**
 * Write a description of MinuteFilter here.
 * 
 * @author Rhume
 * @version 1.0 
 * @date 10 October, 2020
 */
public class MinuteFilter implements Filter {

    private int minTime;
    private int maxTime;
    
    public MinuteFilter(int minTime, int maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
    
    @Override
    public boolean satisfies(String id) {        
        int time = MovieDatabase.getMinutes(id);
        if (time >= minTime && time <= maxTime) {
            return true;
        }
        return false;
    }
}
