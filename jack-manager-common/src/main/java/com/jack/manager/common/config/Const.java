package com.jack.manager.common.config;

/**
 * 参数配置常量类
 * @author liuJack
 *
 */
public class Const {

	/**
	 * Redis相关
	 * @author liuJack
	 *
	 */

	public static final class Page{

		/**默认页大小*/
		public static Integer pageSize;

		/**默认当前页*/
		public static Integer currentPage;
	}

	public static final class RSA{

		/**系数*/
		public static String modulus;
		
		/**公钥指数*/
		public static String publicExponent;
		
		/**私钥指数*/
		public static String privateExponent;

	}
	
	
	public static final class ServletConfig{
		/**作用域中,登录后保存用户的名称*/
		public static final String SessionUser = "session_user";
	}
	
	
	public static final class DIC_DATA{
		
		/**结算方式/类别*/
		public static final String PAY_TYPE = "DIC_PAY_TYPE";
		
		/**凭证号类型*/
		public static final String DIC_PINGZHENGHAO_TYPE = "DIC_PINGZHENGHAO_TYPE";
		
		/**出纳费用类别*/
		public static final String DIC_CHUNA_TYPE = "DIC_CHUNA_TYPE";
		
		/**出纳业务类型*/
		public static final String DIC_CHUNA_F_BUSINESS_TYPE = "DIC_CHUNA_F_BUSINESS_TYPE";
		
		/**用户自定义类别*/
		public static final String DIC_OTHER_TYPE = "DIC_OTHER_TYPE";
		
	}
	
	/**
	 * 当前发布模式
	 */
	public static final boolean IS_TEST = true;
	
	/**
	 * 公司编号长度
	 */
	public static final int COMPANY_NO_LEN = 8;
	
	/**
	 * 用户默认密码
	 */
	public static final String USER_DEFAULT_PWD = "123456";
	
	/**户号的长度*/
	public static final Integer NO_LENGTH = 12;
	
	public static final class FTP{
		
		public static final String LoginName = "jack";
		
		public static final String password = "water123456Ftp@";
		
		public static final String IP = "114.115.179.255";
		
		public static final Integer PROT = 2121;
		
		public static final String staticImages = "http://114.115.179.255/static_images/";
		
	}
}
