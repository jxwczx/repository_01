package jc.com.utils;
/**
 * 返回结果类
 * 
 * */
public class ResultUtil {
	private boolean isFlag;
	private String result;
	
	public ResultUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultUtil(boolean isFlag,String result) {
		this.isFlag = isFlag;
		this.result = result;
	}
	
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
