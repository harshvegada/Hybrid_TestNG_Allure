package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

	private static int count = 0;
	private static int maxTry = 0;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (count < maxTry) {
				count++;
				System.out.println(iTestResult.getMethod().getConstructorOrMethod().getName() + " " + count
						+ " rety on failure..");
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				iTestResult.setStatus(ITestResult.FAILURE);
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}
