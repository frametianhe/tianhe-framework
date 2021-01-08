package com.tianhe.framework.commons.study.utils;

/**
 * for循环演示，ean-13条验证码计算 条码应用
 */
/**
 * en-13 条码验证计算 验证条码公13位，代表了一位商品编码，前12位(692116851128)有具体代表意义
 * 第13位是通过如下 步骤算出来的
 * 第一部：取出该数的奇数位和，c1=6+2+1+8+1+2=20
 * 第二部：取出该数的偶数位和，c2=9+1+6+5+1+8=30
 * 第三步：将奇数和与偶数和的3倍相加,cc=c1+c2*3＝20+30*3＝110
 * 第四步：取结果的个位数（对10取余）：110的个位数为：110％10＝0
 * 第五步：用10减去这个个位数，10－0＝10
 * 第六步：对得到的数在取个位数(对10取余）10％10＝0所以校验码为0
 */
public class BarCodeVerification {
	public static void main(String[] args) {
		String bar = "6921168511280";
		boolean b = check(bar);//调用验证方法
		System.out.println(b);
	}
	
	/**传入一个13位的ean码，检查验证码最后一位是否正确*/
	public static boolean check(String ean13){
		int sum1 = 0;//所有奇数位数字的和
		int sum2 = 0;//所有偶数位数字的和
		//char c = bar.charAt(0);//从字符串中取到字符'6'
		for(int i = 0;i<12;i+=2){
			char c = ean13.charAt(i);
			sum1 += c -'0';//奇数位转数字，累加
			sum2 += ean13.charAt(i+1) - '0';//偶数位转数字，累加
		}
		int sum3 = sum1 + sum2*3;//３将奇数位和偶数位的3倍相加
		int sum4 = sum3%10;//４　对10取余，取到个位数
		int code = (10-sum4)%10;//5 6在取余个位数  通过计算得到的验证码
		int eanCode = ean13.charAt(12) - '0';//传入字符串ean13的验证码
		if(code==eanCode){
			return true;
		}
		return false;
	}
}
