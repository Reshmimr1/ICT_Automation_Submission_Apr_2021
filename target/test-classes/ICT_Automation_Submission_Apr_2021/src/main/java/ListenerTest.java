import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }

    @Override
    public void onTestFailure(ITestResult Result)
    {
        System.out.println("The failed testcase is :"+Result.getName());
        Reports.extentTest.log(Status.FAIL,"The failed testcase is "+Result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("The Skipped testcase is :"+Result.getName());
    }

    @Override
    public void onTestStart(ITestResult Result)
    {
        System.out.println(Result.getName()+" test case started");
    }

    @Override
    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("The testcase passed is :"+Result.getName());
    }
}
