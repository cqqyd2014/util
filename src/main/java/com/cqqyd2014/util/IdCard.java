package com.cqqyd2014.util;

public class IdCard {
	public static  char doVerify(String id) 
	{ 
	char pszSrc[]=id.toCharArray(); 
	int iS = 0; 
	int iW[]={7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; 
	char szVerCode[] = new char[]{'1','0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; 
	int i; 
	for(i=0;i<17;i++) 
	{ 
	iS += (int)(pszSrc[i]-'0') * iW[i]; 
	} 
	int iY = iS%11; 
	return szVerCode[iY]; 
	}
	public static boolean checkID(String str) throws Exception{
		boolean b=true;
		//身份证必须18位
		if (str.length()!=18){
			b=false;
			throw new Exception("身份证必须18位");
			
		}
		//身份证前面17位只能包含数字
		if (!str.substring(0,16).matches("^[0-9]*$")){
			b=false;
			throw new Exception("身份证前面17位只能包含数字");
		}
		//身份证前面7-10位必须是有效年份1900-2020
		if (Integer.parseInt(str.substring(6, 10))<1900||Integer.parseInt(str.substring(6, 10))>2020){
			b=false;
			
			throw new Exception("身份证前面7-10位必须是有限年份1900-2020");
		}
		//身份证前面11-12位必须是有效月份1-12
		
		if (Integer.parseInt(str.substring(10, 12))<1||Integer.parseInt(str.substring(10, 12))>12){
			
			b=false;
			
			throw new Exception("身份证前面11-12位必须是有效月份1-12");
		}
		//身份证前面12-13位必须是有效天数1-31

		if (Integer.parseInt(str.substring(12, 14))<1||Integer.parseInt(str.substring(12, 14))>31){
			b=false;
			throw new Exception("身份证前面12-13位必须是有效天数1-31");
		}
		//身份证最后一位只能是数字或者X
		if (!(str.substring(16, 17).matches("^[0-9]*$")||str.substring(16, 17).equals("X"))){
			b=false;
			throw new Exception("身份证最后一位只能是数字或者X");
		}
		//身份证最后一位校验码错误
		char end=str.charAt(17);
		if (end!=doVerify(str)){
			b=false;
			throw new Exception("身份证校验码错误");
		}

		return b;
	}

}
