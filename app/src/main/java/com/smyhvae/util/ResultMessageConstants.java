package com.smyhvae.util;

public final class ResultMessageConstants  {

	public final static String INIT_SUCCESS="初始化成功";
	
	public final static String INIT_FAILED="初始化失败";
	
	public final static String INIT_SCORE_SUCCESS="初始化积分成功";
		
	public final static String REGISTER_SUCCESS="注册成功";
	
	public final static String LOGIN_SUCCESS="登录成功";

	public final static String LOGIN_FAILED1="密码错误";

	public final static String LOGIN_FAILED2="登陆编码不存在";
	
	public final static String RESET_PASSWORD_SUCCESS="密码重置成功";
	
	public final static String ACTIVATION_SUCCESS="激活成功"; 
	
	public final static String MODIFY_PASSWORD_SUCCESS="修改密码成功";
	
	public final static String REQUEST_SUCCESS="操作成功";
	
	public final static String REQUEST_FAILED="请求失败";
	
	public final static String REQUEST_UNMEANINGFUL="无效操作";

	public final static String ADD_SUCCESS="新增成功";
	
	public final static String ADD_FAILED="新增失败";
	
	public final static String DELETE_SUCCESS="删除成功";
	
	public final static String DELETE_FAILED="删除失败";
	
	public final static String DISABLE_SUCCESS="停用/启用成功";
	
	public final static String ON_MARKET_SUCCESS="上架成功";

	public final static String OFF_MARKET_SUCCESS="下架成功";

	public final static String UPDATE_SUCCESS="修改成功";
	
	public final static String TRANSFER_COMPLETE="调拨成功";

	public final static String CHECK_FAILED="该条记录已存在";
	
	public final static String INCLUDING_INVALID_STRING="请不要输入非法字符";

	public final static String UPLOAD_IMAGE_SUCCESS="图片上传成功";
	
	public final static String UPLOAD_IMAGE_FAILED="图片上传失败";
	
	public final static String RETURN_REFURBISH = "返回刷新";

	public final static String NO_RESULT="无数据";
	
	public final static String BILL_EXECUTED="单据已执行";
	
	public final static String IS_LOCK="门店已被锁，暂时无法操作库存";
	
	public final static String DISABLE_BILL_SUCCESS="作废成功,但未影响库存";
	
	public final static String ACCESS_KEY_VALIDATION_FAILD="已超时,请重新启动程序";//不可变（与App端完全匹配）

	public final static String CODE_EXISTING="款号已存在";

	
	public final static String PAID_UP="已付清";
	public final static String NOT_PAID="未付清";
	
	public final static String CHECK_BILL_MODEL_STRING="盘点单";
	public final static String PURCHASE_BILL_MODEL_STRING="采购单";
	public final static String SALES_BILL_MODEL_STRING="销售单";
	public final static String TRANSFER_BILL_FROMINV_MODEL_STRING="调出单";
	public final static String TRANSFER_BILL_TOINV_MODEL_STRING="调入单";
	
	public final static String ROLE_STRING_CEO="总经理";
	public final static String ROLE_STRING_SHOPKEEPER="店长";
	public final static String ROLE_STRING_TREASURER="财务员";
	public final static String ROLE_STRING_SHOP_ASSISTANT="营业员";
	public final static String ROLE_STRING_INVENTORY_MANAGER="门店经理";
	public final static String ROLE_STRING_SALESBILL="开单员";
	public final static String ROLE_STRING_SHOPKEEPER_2="店长2";
	public final static String ROLE_STRING_SHOPKEEPER_3="店长3";
	public final static String WAREHOUSE_KEEPER="仓管员";
	public final static String POST_RUNNER="后道员";
	
	public final static String DEFAULT_SELECTION_INVENTORY="公司初仓";
	
	public final static String DEFAULT_INVENTORY="默认店";
	
	public final static String SHOULD_HAVE_ROLE_STRING_CEO="该账套下至少有一个总经理";
	
	public final static String VERIFIEDSALESBILL_CAN_NOT_DISABLE="该单据为被销账单据无法作废,请先作废销账单,后重试";

	public final static String VERIFIEDPURCHASEBILL_CAN_NOT_DISABLE="该单据为被销账单据无法作废,请先作废销账单,后重试";

	public final static String DISABLEDBILL="该单据为作废单据或无效单据";
	
	public final static String VERIFIEDBILL="该单据为被销账单据";
	
	public final static String VERIFYBILL="该单据为销账单据";
	
	public final static String LOGISTICSCOLLECTIONBILL="该单据为物流代收单";
	
	public final static String INTEGRALBILL="该单为已积分单";
	
	public final static String INTEGRALDETAILBILL="该单包含积分明细";
	
	public final static String DO_NOT_HAVE_AUTHORITY="无权限";
	
	public final static String MUST_AFTER_LAST_CHECKBILL="该操作必须在最新盘点之后";
	
	public final static String DO_NOT_DISABLE_ANY_TIME_BILL="不允许作废历史单据";
	
	public final static String ORDER_BILL="该单据为发货单，请作废重开";
	
	public final static String TEMP_TEMP="散客";
	
	public final static String TEMP_SUPPLIER="散厂";
	
	public final static String SOURCEACCOUNT_NOT_EQUAL_TARGETACCOUNT="账套必须为同一企业";
	
	public final static String DELIVERY_BILL="该单据已发货";
	
	public final static String NEGATIVE_STOCK="库存不足";
	
	public final static String TRANSFERRED_BILL="该单据已接收";
	
	public final static String DISABLEMARKBILL="该单据为终结单";
	
	public final static String STORAGEBILL="该单据已入库";
	
	public final static String COLLATE_BILL_CLIENT_CODE="clientcode";
	public final static String COLLATE_BILL_CLIENT_DETAIL="clientdetail";
	public final static String COLLATE_BILL_SUPPLIER_CODE="suppliercode";
	public final static String COLLATE_BILL_SUPPLIER_DETAIL="supplierdetail";
	
	private ResultMessageConstants(){
		
	}

}
