package model;

public class OrderVO {
	private int oNum;
	private String oName;
	private String oPhone;
	private String oAddress;
	private String oAddressDet;
	private int pNum;
	// tmp
	private String pName;
	private int pPrice;
	private String pImage;
	
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoPhone() {
		return oPhone;
	}
	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public String getoAddressDet() {
		return oAddressDet;
	}
	public void setoAddressDet(String oAddressDet) {
		this.oAddressDet = oAddressDet;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	// tmp
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	@Override
	public String toString() {
		return oNum + ". " + oName + " " + oPhone + " " + oAddress + " " + oAddressDet + " " + pNum + " " + pName + " " + pPrice;		 
	}
	
	
	
	
}
