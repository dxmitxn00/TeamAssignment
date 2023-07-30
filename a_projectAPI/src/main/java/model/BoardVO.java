package model;

public class BoardVO {
	private int bNum;
	private String bTitle;
	private String bContent;
	private int oNum;
	// tmp
	private String oName;
	
	public int getbNum() {
		return bNum;
	}
	
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	
	public String getbTitle() {
		return bTitle;
	}
	
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	
	public String getbContent() {
		return bContent;
	}
	
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	
	public int getoNum() {
		return oNum;
	}
	
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	// tmp
	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}
	
	@Override
	public String toString() {
		return bNum + ". " + bTitle + " " + bContent + " ";
	}

	
	
}
