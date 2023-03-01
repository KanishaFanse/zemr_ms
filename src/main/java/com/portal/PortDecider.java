package com.portal;

public class PortDecider {
	
	public static String getPortNo(String storeCode) {
		String portNo = "";
		try {
			if(!BeanUtils.isNullOrEmpty(storeCode)) {
				switch (storeCode) {
				case "025":
					portNo = "21025";
					break;
				case "026":
					portNo = "21026";
					break;
				case "027":
					portNo = "21027";
					break;
				case "028":
					portNo = "21028";
					break;
				case "029":
					portNo = "21029";
					break;
				case "030":
					portNo = "21030";
					break;
				case "524":
					portNo = "21524";
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return portNo;
	}

}
