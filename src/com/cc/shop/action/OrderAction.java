package com.cc.shop.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import com.cc.shop.dao.ProductDao;
import com.cc.shop.pojo.BillItem;
import com.cc.shop.pojo.Cart;
import com.cc.shop.pojo.Message;
import com.cc.shop.pojo.Order;
import com.cc.shop.pojo.OrderItem;
import com.cc.shop.pojo.Product;
import com.cc.shop.pojo.ShopCartItem;
import com.cc.shop.pojo.User;
import com.cc.shop.service.BillService;
import com.cc.shop.service.MessageService;
import com.cc.shop.service.OrderService;
import com.cc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单Action类
 * 
 * 
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	// 模型驱动使用的对象
	private Order order = new Order();
	
	private BillService billService;
	
	private MessageService messageService;
	
	private String my_txt;
	
	public String getMy_txt() {
		return my_txt;
	}

	public void setMy_txt(String my_txt) {
		this.my_txt = my_txt;
	}

	public Order getModel() {
		return order;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private CartAction cartAction;
	
	public CartAction getCartAction() {
		return cartAction;
	}

	public void setCartAction(CartAction cartAction) {
		this.cartAction = cartAction;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 注入OrderService
	private OrderService orderService;

	

	// 生成订单的执行的方法:
	public String saveOrder() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单的状态   1:未付款. 2已付款
		order.setState(1); 
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!您还没有登录!");
			return "msg";
		}
		order.setUser(existUser);
		order.setName(existUser.getName());
		order.setPhone(existUser.getPhone());
		order.setAddr(existUser.getAddr());
		// 设置订单项集合:
		for (ShopCartItem shopCartItem : cart.getList()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(shopCartItem.getPcount());
			orderItem.setSubtotal(shopCartItem.getPtotal());
			Product product = orderService.findByPid(shopCartItem.getPid());
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// 清空购物车:
		cartAction.clearCart();
		return "saveOrder";
	}
	
	// 查询我的订单:
	public String findByUid() {
		// 获得用户的id.
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		// 根据用户的id查询订单:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// 将PageBean数据带到页面上.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	// 根据订单id查询订单:
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}

	
	//模拟付账
	public String payOrder(){
		
		// 修改订单的状态:
		Order myOrder = orderService.findByOid(order.getOid());
		
		// 修改订单状态为2:已经付款:
		myOrder.setState(2);
		myOrder.setName(order.getName());
		myOrder.setPhone(order.getPhone());
		myOrder.setAddr(order.getAddr());
		orderService.update(myOrder);
		
		
		//根据订单中的商品信息，保存到销售记录中
		List<OrderItem> orderItemList =orderService.findOrderItem(order.getOid());
		if(orderItemList!=null)
		{
			for(int i=0;i<orderItemList.size();i++){
				//获取订单中的商品项
				OrderItem orderItem = orderItemList.get(i);
				//获取订单中每种商品的详细的信息，商品pid 商品数量count 这笔商品总价
				Integer pid = orderItem.getProduct().getPid();
				//商品数量
				Integer count = orderItem.getCount();
				//成本价
				Double market_price = orderItem.getProduct().getMarket_price();
				//该商品销售总价
				Double shopprice = orderItem.getSubtotal();
				//盈利金额等于总价减去成本价
				Double billmoney = (shopprice - market_price)*count;
				BillItem billItem = billService.findBillItemByPid(pid);
				
				//如果为空，商店中暂时还没销售统计该商品；不为空则表示之前销售过该商品，只需更新商品的数量和总售价
				if(billItem==null){
					BillItem newBillItem = new BillItem();
					newBillItem.setCount(count);
					
					newBillItem.setPtotal(billmoney);
					Product product = orderService.findByPid(orderItem.getProduct().getPid());
					newBillItem.setProduct(product);
					billService.saveBillItem(newBillItem);
				}else{
					//更新销售表中的数量总售价
					billItem.setCount(billItem.getCount()+count);
					billItem.setPtotal(billItem.getPtotal()+billmoney);
					billService.updateBillItem(billItem);
				}
				
			}
			
		}
		
		
		//订单编号
		int oid = myOrder.getOid();
		//订单支付总价格
		double total = myOrder.getTotal();
		//订单人
		String uname = myOrder.getName();
		//收货地址
		String orderAdderss = myOrder.getAddr();
		//联系方式
		String Phone = myOrder.getPhone();
		//下单时间
		Date ordertime = myOrder.getOrdertime();
		
		System.out.println("订单编号："+oid+"；总价格"+total+"；下单人："+uname+"；送货地址："+orderAdderss+"；联系方式："+Phone+";下单时间:"+ordertime.toString());
		//this.addActionMessage("支付成功!订单编号："+oid+"；总价格"+total+"；下单人："+uname+"；送货地址："+orderAdderss+"；联系方式："+Phone+";下单时间:"+ordertime.toString());
		this.addActionMessage("订单编号："+oid+"；");
		this.addActionMessage("下单人："+uname+"；");
		this.addActionMessage("总价格"+total+"；");
		this.addActionMessage("联系方式："+Phone+"；");
		this.addActionMessage("送货地址："+orderAdderss+"；");
		this.addActionMessage("下单时间:"+ordertime.toString()+"；");
		this.addActionMessage("请确认，如有问题请于工作人员联系!!!");
		return "msg";
	}
	
	public String evaluateOrder(){
		System.out.println("这是留言板");
		order = orderService.findByOid(order.getOid());
		return "evaluate";
	}
	
	
	// 修改订单的状态:
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
	public String saveMessage(){
		my_txt = my_txt.trim();
		if(my_txt.startsWith("<p>")){
			my_txt = my_txt.substring(3);
		}
		if(my_txt.endsWith("</p>")){
			my_txt = my_txt.substring(0, my_txt.length()-4);
		}
		Message message = new Message();
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		message.setUser(existUser);
		message.setOid(order.getOid());
		message.setMessage(my_txt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd--HH:mm");
        Date now = new Date();
        String systime = sdf.format(now);
        message.setMessagedate(systime);
		messageService.saveMessage(message);
		return "saveMessageSuccess";
		
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
