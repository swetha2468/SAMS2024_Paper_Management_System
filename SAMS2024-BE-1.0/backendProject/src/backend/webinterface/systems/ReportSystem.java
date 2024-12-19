package backend.webinterface.systems;

public class ReportSystem
{


    public Integer[] GetAvailablePCMs()
    {
        return  new Integer[]{1,2,3};
    }
    public Integer[] GetPapersToRate()
    {
        return  new Integer[]{1,2,3};
    }

    public void AssignPaper(Integer[] pcms)
    {
        //TODO : CONNECT TO DATABASE
    }

    public void InformConflicts(Integer pcmId, Integer reviewId)
    {
        //TODO : CONNECT TO DATABASE
    }
    public void RatePaper()
    {

    }

}
